package dev.bjarne.statisticsui.stats;

import dev.bjarne.statisticsui.StatisticsUIPlugin;
import dev.bjarne.statisticsui.menu.MenuBuilder;
import dev.bjarne.statisticsui.menu.StatsMenuHolder;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Resolves a snapshot for a target and opens the menu. Online players are read live on the main
 * thread (hopefully cheap) and never cached, so their statistics are always current.
 * Offline players are read from disk on an async thread behind a loading screen, and those results
 * are cached since offline statistics do not change while the player is away anyways :D
 */
public final class StatsService {

    private final StatisticsUIPlugin plugin;
    private final SnapshotCache cache;

    public StatsService(StatisticsUIPlugin plugin, long cacheMillis) {
        this.plugin = plugin;
        this.cache = new SnapshotCache(cacheMillis);
    }

    public void cacheMillis(long millis) {
        cache.ttlMillis(millis);
    }

    public void clearCache() {
        cache.clear();
    }

    /** Opens the statistics menu for {@code target}. Must be called on the main thread. */
    public void open(Player viewer, UUID target, String name) {
        Player online = Bukkit.getPlayer(target);
        if (online != null) {
            viewer.openInventory(MenuBuilder.build(buildSnapshot(online, true)));
            return;
        }

        PlayerSnapshot cached = cache.get(target);
        if (cached != null) {
            viewer.openInventory(MenuBuilder.build(cached));
            return;
        }

        viewer.openInventory(MenuBuilder.loading(target, name));
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            OfflinePlayer offline = Bukkit.getOfflinePlayer(target);
            PlayerSnapshot snapshot = buildSnapshot(offline, false);
            Bukkit.getScheduler().runTask(plugin, () -> deliver(viewer, target, snapshot));
        });
    }

    private void deliver(Player viewer, UUID target, PlayerSnapshot snapshot) {
        cache.put(target, snapshot);
        if (!viewer.isOnline()) {
            return;
        }
        Inventory top = viewer.getOpenInventory().getTopInventory();
        if (top.getHolder() instanceof StatsMenuHolder holder && holder.target().equals(target)) {
            MenuBuilder.populate(top, snapshot);
            viewer.updateInventory();
        }
    }

    private static PlayerSnapshot buildSnapshot(OfflinePlayer player, boolean online) {
        PlayerMeta meta = meta(player, online);
        return new PlayerSnapshot(meta, new PanelRenderer(player, meta).render());
    }

    @SuppressWarnings("deprecation")
    private static PlayerMeta meta(OfflinePlayer player, boolean online) {
        UUID uuid = player.getUniqueId();
        String name = player.getName() != null ? player.getName() : uuid.toString().substring(0, 8);
        long firstPlayed = player.getFirstPlayed();
        int level = 0;
        int totalExperience = 0;
        long lastPlayed = 0L;
        if (online && player instanceof Player p) {
            level = p.getLevel();
            totalExperience = p.getTotalExperience();
        } else {
            lastPlayed = player.getLastPlayed();
        }
        return new PlayerMeta(uuid, name, online, level, totalExperience, firstPlayed, lastPlayed);
    }
}

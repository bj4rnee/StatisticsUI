package dev.bjarne.statisticsui.command;

import dev.bjarne.statisticsui.StatisticsUIPlugin;
import dev.bjarne.statisticsui.config.Settings;
import dev.bjarne.statisticsui.stats.StatsService;
import dev.bjarne.statisticsui.util.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public final class StatsCommand implements CommandExecutor, TabCompleter {

    private final StatisticsUIPlugin plugin;
    private final StatsService service;
    private final Settings settings;

    public StatsCommand(StatisticsUIPlugin plugin, StatsService service, Settings settings) {
        this.plugin = plugin;
        this.service = service;
        this.settings = settings;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            reload(sender);
            return true;
        }
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }
        if (args.length == 0) {
            if (denied(player, "statsui.view")) {
                return true;
            }
            plugin.countCommand();
            service.open(player, player.getUniqueId(), player.getName());
            return true;
        }
        if (args.length == 1) {
            if (denied(player, "statsui.view.others")) {
                return true;
            }
            openOther(player, args[0]);
            return true;
        }
        usage(player);
        return true;
    }

    private void openOther(Player viewer, String name) {
        Player online = Bukkit.getPlayerExact(name);
        if (online != null) {
            plugin.countCommand();
            service.open(viewer, online.getUniqueId(), online.getName());
            return;
        }
        // Match against players who have data on this server. Reading local cache off the main
        // thread avoids the disk stall and the Mojang profile lookup
        // getOfflinePlayer() used to be problematic here
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            OfflinePlayer offline = findPlayed(name);
            Bukkit.getScheduler().runTask(plugin, () -> {
                if (!viewer.isOnline()) {
                    return;
                }
                if (offline == null) {
                    viewer.sendMessage(Text.color("&cPlayer '" + name + "' was not found."));
                    return;
                }
                plugin.countCommand();
                service.open(viewer, offline.getUniqueId(), offline.getName());
            });
        });
    }

    private static OfflinePlayer findPlayed(String name) {
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            if (name.equalsIgnoreCase(player.getName())) {
                return player;
            }
        }
        return null;
    }

    private void reload(CommandSender sender) {
        if (!sender.hasPermission("statsui.reload")) {
            sender.sendMessage(Text.color("&cYou do not have permission: &lstatsui.reload"));
            return;
        }
        plugin.reload();
        sender.sendMessage(Text.color("&aStatisticsUI configuration reloaded."));
    }

    private boolean denied(Player player, String permission) {
        if (!settings.permissionsEnabled() || player.hasPermission(permission)) {
            return false;
        }
        player.sendMessage(Text.color("&cYou do not have permission: &l" + permission));
        return true;
    }

    private void usage(Player player) {
        player.sendMessage(Text.color("&l&aUsage:"));
        player.sendMessage(Text.color("&a/stats &7- view your own statistics."));
        player.sendMessage(Text.color("&a/stats <player> &7- view another player's statistics."));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length != 1) {
            return suggestions;
        }
        String prefix = args[0].toLowerCase(Locale.ROOT);
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.getName().toLowerCase(Locale.ROOT).startsWith(prefix)) {
                suggestions.add(online.getName());
            }
        }
        if (sender.hasPermission("statsui.reload") && "reload".startsWith(prefix)) {
            suggestions.add("reload");
        }
        return suggestions;
    }
}

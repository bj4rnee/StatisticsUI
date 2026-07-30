package dev.bjarne.statisticsui.menu;

import dev.bjarne.statisticsui.stats.PlayerMeta;
import dev.bjarne.statisticsui.stats.PlayerSnapshot;
import dev.bjarne.statisticsui.stats.RenderedPanel;
import dev.bjarne.statisticsui.util.Text;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/** turn snapshots into inventories. Every method here creates items, so it must run on the main thread :/ */
public final class MenuBuilder {

    public static final int SIZE = 54;
    private static final int HEAD_SLOT = 4;

    private MenuBuilder() {
    }

    public static Inventory loading(UUID target, String name) {
        Inventory inventory = create(target, name);
        ItemStack loading = simpleItem(Material.BLACK_STAINED_GLASS_PANE,
                "&c&lLoading Statistics", "&7This may take a moment...");
        for (int slot = 0; slot < SIZE; slot++) {
            inventory.setItem(slot, loading);
        }
        return inventory;
    }

    public static Inventory build(PlayerSnapshot snapshot) {
        Inventory inventory = create(snapshot.meta().uuid(), snapshot.meta().name());
        populate(inventory, snapshot);
        return inventory;
    }

    public static void populate(Inventory inventory, PlayerSnapshot snapshot) {
        ItemStack filler = simpleItem(Material.WHITE_STAINED_GLASS_PANE, " ", null);
        for (int slot = 0; slot < SIZE; slot++) {
            inventory.setItem(slot, filler);
        }
        inventory.setItem(HEAD_SLOT, head(snapshot.meta()));
        for (RenderedPanel panel : snapshot.panels()) {
            inventory.setItem(panel.slot(), toItem(panel));
        }
    }

    private static Inventory create(UUID target, String name) {
        StatsMenuHolder holder = new StatsMenuHolder(target);
        Inventory inventory = Bukkit.createInventory(holder, SIZE, Text.color("Statistics of &d&l" + name));
        holder.inventory(inventory);
        return inventory;
    }

    private static ItemStack toItem(RenderedPanel panel) {
        ItemStack item = new ItemStack(panel.icon());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(panel.title());
        meta.setLore(panel.lore());
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack head(PlayerMeta meta) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) head.getItemMeta();
        skull.setOwningPlayer(Bukkit.getOfflinePlayer(meta.uuid()));
        skull.setDisplayName(Text.color("&d&l" + meta.name()));
        skull.setLore(List.of(Text.color("&7" + meta.uuid())));
        head.setItemMeta(skull);
        return head;
    }

    private static ItemStack simpleItem(Material material, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Text.color(name));
        if (lore != null) {
            meta.setLore(List.of(Text.color(lore)));
        }
        item.setItemMeta(meta);
        return item;
    }
}

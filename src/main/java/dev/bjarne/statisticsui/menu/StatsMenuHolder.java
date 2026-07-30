package dev.bjarne.statisticsui.menu;

import java.util.UUID;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/** Mark an inventory as ours and records whose statistics it shows, so event handler is more reliable */
public final class StatsMenuHolder implements InventoryHolder {

    private final UUID target;
    private Inventory inventory;

    public StatsMenuHolder(UUID target) {
        this.target = target;
    }

    public UUID target() {
        return target;
    }

    void inventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}

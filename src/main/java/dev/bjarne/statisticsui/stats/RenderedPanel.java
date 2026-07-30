package dev.bjarne.statisticsui.stats;

import java.util.List;
import org.bukkit.Material;

/** finished panel as plain data */
public record RenderedPanel(int slot, Material icon, String title, List<String> lore) {
}

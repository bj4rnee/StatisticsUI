package dev.bjarne.statisticsui.stats;

import java.util.List;

/** cachable meta stuff */
public record PlayerSnapshot(PlayerMeta meta, List<RenderedPanel> panels) {
}

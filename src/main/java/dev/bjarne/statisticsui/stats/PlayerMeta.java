package dev.bjarne.statisticsui.stats;

import java.util.UUID;

/** Player statistics that are not bukkit statistics. Experience only known while the player is online. */
public record PlayerMeta(UUID uuid, String name, boolean online,
                         int level, int totalExperience,
                         long firstPlayed, long lastPlayed) {
}

package dev.bjarne.statisticsui.config;

import org.bukkit.configuration.file.FileConfiguration;

/** someone once told me typed views are better than config.yml. Is it true? idk */
public final class Settings {

    private boolean permissionsEnabled;
    private long cacheMillis;

    public Settings(FileConfiguration config) {
        load(config);
    }

    public void load(FileConfiguration config) {
        permissionsEnabled = config.getBoolean("permissions.enabled", false);
        cacheMillis = Math.max(0, config.getLong("cache.seconds", 60)) * 1000L;
    }

    public boolean permissionsEnabled() {
        return permissionsEnabled;
    }

    public long cacheMillis() {
        return cacheMillis;
    }
}

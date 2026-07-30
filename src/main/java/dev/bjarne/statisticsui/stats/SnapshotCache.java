package dev.bjarne.statisticsui.stats;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Per player snapshot cache with TTL. zero disables caching entirely */
public final class SnapshotCache {

    private record Entry(PlayerSnapshot snapshot, long expiresAt) {
    }

    private final ConcurrentHashMap<UUID, Entry> entries = new ConcurrentHashMap<>();
    private volatile long ttlMillis;

    public SnapshotCache(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    public PlayerSnapshot get(UUID uuid) {
        if (ttlMillis <= 0) {
            return null;
        }
        Entry entry = entries.get(uuid);
        if (entry == null) {
            return null;
        }
        if (System.currentTimeMillis() > entry.expiresAt()) {
            entries.remove(uuid, entry);
            return null;
        }
        return entry.snapshot();
    }

    public void put(UUID uuid, PlayerSnapshot snapshot) {
        if (ttlMillis > 0) {
            entries.put(uuid, new Entry(snapshot, System.currentTimeMillis() + ttlMillis));
        }
    }

    public void ttlMillis(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    public void clear() {
        entries.clear();
    }
}

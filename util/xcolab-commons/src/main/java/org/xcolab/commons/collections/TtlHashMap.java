package org.xcolab.commons.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableSet;

/**
 * A hash map that prevents itself from growing too large by evicting entries after a certain time.
 *
 * The eviction happens only when new entries are added and is based on the last accessed time.
 * Each entry will stay in the map for at least the specified ttl, but may stay for longer
 * if no new entries are added.
 *
 * @param <K> type of the key
 * @param <V> type of the values
 */
public class TtlHashMap<K, V> implements Map<K, V> {

    private final ConcurrentHashMap<K, V> store = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<K, Long> lastAccessedTimes = new ConcurrentHashMap<>();
    private final long ttlInMillis;

    public TtlHashMap(long ttlValue, TimeUnit ttlUnit) {
        this.ttlInMillis = ttlUnit.toMillis(ttlValue);
    }

    @Override
    public V get(Object key) {
        V value = this.store.get(key);

        if (value != null) {
            refreshTimeToLive(key);
        }
        return value;
    }

    @Override
    public V put(K key, V value) {
        removeExpiredEntries();
        return putInternal(key, value);
    }

    private V putInternal(K key, V value) {
        refreshTimeToLive(key);
        return store.put(key, value);
    }

    private void refreshTimeToLive(Object key) {
        //noinspection unchecked
        lastAccessedTimes.put((K) key, System.currentTimeMillis());
    }

    private void removeExpiredEntries() {
        for (K key : store.keySet()) {
            if (expired(key)) {
                store.remove(key);
                lastAccessedTimes.remove(key);
            }
        }
    }

    private boolean expired(K key) {
        final long lastAccessedTime = lastAccessedTimes.get(key);
        return (System.currentTimeMillis() - lastAccessedTime) > this.ttlInMillis;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        removeExpiredEntries();
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            this.putInternal(e.getKey(), e.getValue());
        }
    }

    @Override
    public V remove(Object key) {
        lastAccessedTimes.remove(key);
        return store.remove(key);
    }

    @Override
    public void clear() {
        lastAccessedTimes.clear();
        store.clear();
    }


    // Delegating methods

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return store.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return store.containsValue(value);
    }

    @Override
    public Set<K> keySet() {
        return unmodifiableSet(store.keySet());
    }

    @Override
    public Collection<V> values() {
        return unmodifiableCollection(store.values());
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        return unmodifiableSet(store.entrySet());
    }
}

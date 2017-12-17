package org.xcolab.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class GroupingHelper {

    private GroupingHelper() {
    }

    /**
     * Groups a list of values by an extracted key, allowing more than one value per key.
     *
     * If you know that there are no duplicates in the list, you can also use
     * {@link #groupByUnique(List, Function)}.
     * If multiple keys can be extracted from a single value, use
     * {@link #groupByWithDuplicateKeysAndValues(List, Function)}.
     *
     * @param list The values to be grouped
     * @param keyExtractor A function to extract the key from a value
     * @param <K> The type of the key
     * @param <V> The type of the value
     * @return A map of values grouped by their respective keys
     */
    public static <K, V> Map<K, List<V>> groupByWithDuplicateValues(List<V> list,
            Function<V, K> keyExtractor) {
        Map<K, List<V>> groupedEntities = new HashMap<>();
        for (V value : list) {
            final K key = keyExtractor.apply(value);
            List<V> valuesForKey = groupedEntities.computeIfAbsent(key, k -> new ArrayList<>());
            valuesForKey.add(value);
        }
        return groupedEntities;
    }

    /**
     * Groups a list of values by an extracted key, allowing more than one value per key and more
     * than one key per value.
     *
     * If you know that there are no duplicates in the list, you can also use
     * {@link #groupByUnique(List, Function)}.
     * If values always have exactly one key, use
     * {@link #groupByWithDuplicateValues(List, Function)}.
     *
     * @param list The values to be grouped
     * @param keysExtractor A function to extract all possible keys from a value
     * @param <K> The type of the key
     * @param <V> The type of the value
     * @return A map of values grouped by their respective keys
     */
    public static <K, V> Map<K, List<V>> groupByWithDuplicateKeysAndValues(List<V> list,
            Function<V, List<K>> keysExtractor) {
        Map<K, List<V>> groupedEntities = new HashMap<>();
        for (V value : list) {
            final List<K> keys = keysExtractor.apply(value);
            for (K key : keys) {
                List<V> valuesForKey = groupedEntities.computeIfAbsent(key, k -> new ArrayList<>());
                valuesForKey.add(value);
            }
        }
        return groupedEntities;
    }

    /**
     * Groups a list of values by an extracted key, allowing only one value per key.
     *
     * This function throws an exception if two values have the same key. If you want to allow
     * duplicates, you an use {@link #groupByWithDuplicateValues(List, Function)} (List, Function)}.
     *
     * @param list The values to be grouped
     * @param keyExtractor A function to extract the key from a value
     * @param <K> The type of the key
     * @param <V> The type of the value
     * @return A key-to-value map
     */
    public static <K, V> Map<K, V> groupByUnique(List<V> list, Function<V, K> keyExtractor) {
        Map<K, V> groupedEntities = new HashMap<>();
        for (V value : list) {
            final K key = keyExtractor.apply(value);
            if (groupedEntities.get(key) != null) {
                throw new DuplicateElementException(key, list);
            }
            groupedEntities.put(key, value);
        }
        return groupedEntities;
    }

    public static class DuplicateElementException extends RuntimeException {

        DuplicateElementException(Object key, List<?> elements) {
            super("Duplicate element  for key " + key + ": " + elements);
        }
    }
}

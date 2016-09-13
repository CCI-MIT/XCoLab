package org.xcolab.util;

import org.xcolab.util.functions.Function;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class GroupingUtil {

    public static <K, V> Map<K, List<V>> groupByWithDuplicates(List<V> list, Function<V, K> keyExtractor) {
        Map<K, List<V>> groupedEntities = new LinkedHashMap<>();
        for (V value : list) {
            final K key = keyExtractor.apply(value);
            List<V> valuesForKey = groupedEntities.get(key);
            if (valuesForKey == null) {
                valuesForKey = new ArrayList<V>();
                groupedEntities.put(key, valuesForKey);
            }
            valuesForKey.add(value);
        }
        return groupedEntities;
    }

    public static <K, V> Map<K, V> groupBy(List<V> list, Function<V, K> keyExtractor) {
        Map<K, V> groupedEntities = new LinkedHashMap<>();
        for (V value : list) {
            groupedEntities.put(keyExtractor.apply(value), value);
        }
        return groupedEntities;
    }

    public static <K, V> Map<K, V> groupByUnique(List<V> list, Function<V, K> keyExtractor)
            throws DuplicateElementException {
        Map<K, V> groupedEntities = new LinkedHashMap<>();
        for (V value : list) {
            final K key = keyExtractor.apply(value);
            if (groupedEntities.get(key) != null) {
                throw new DuplicateElementException(key, list);
            }
            groupedEntities.put(key, value);
        }
        return groupedEntities;
    }

    public static class DuplicateElementException extends Exception {
        DuplicateElementException(Object key, List<?> elements) {
            super("Duplicate element  for key " + key + ": " + elements);
        }
    }
}

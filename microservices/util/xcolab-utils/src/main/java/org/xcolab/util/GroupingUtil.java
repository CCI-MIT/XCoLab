package org.xcolab.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class GroupingUtil {

    private GroupingUtil() {
    }

    public static <K, V> Map<K, List<V>> groupByWithDuplicates(List<V> list, Function<V, K> keyExtractor) {
        Map<K, List<V>> groupedEntities = new LinkedHashMap<>();
        for (V value : list) {
            final K key = keyExtractor.apply(value);
            List<V> valuesForKey = groupedEntities.computeIfAbsent(key, k -> new ArrayList<V>());
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

    public static <SearchKey, MapKey, MapVal> Map<MapKey, MapVal> getInnerMapOrCreate(
            SearchKey searchKey, Map<SearchKey, Map<MapKey, MapVal>> searchMap) {
        return searchMap.computeIfAbsent(searchKey, k -> new HashMap<>());
    }

    public static <SearchKey, ListVal> List<ListVal> getInnerListOrCreate(
            SearchKey searchKey, Map<SearchKey, List<ListVal>> searchMap) {
        return searchMap.computeIfAbsent(searchKey, k -> new ArrayList<>());
    }
}

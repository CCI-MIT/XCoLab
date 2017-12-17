package org.xcolab.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public final class GroupingHelper<ValueT> {

    private final Collection<ValueT> collection;

    public GroupingHelper(Collection<ValueT> collection) {
        this.collection = collection;
    }

    /**
     * Groups a collection of values by an extracted key, allowing more than one value per key.
     *
     * If you know that there are no duplicates in the collection, you can also use
     * {@link #groupUnique(Function)}.
     * If multiple keys can be extracted from a single value, use
     * {@link #groupWithDuplicateKeysAndValues(Function)}.
     *
     * @param keyExtractor A function to extract the key from a value
     * @param <KeyT> The type of the key
     * @return A map of values grouped by their respective keys
     */
    public <KeyT> Map<KeyT, Set<ValueT>> groupWithDuplicateValues(
            Function<ValueT, KeyT> keyExtractor) {
        Map<KeyT, Set<ValueT>> groupedEntities = new HashMap<>();
        for (ValueT value : collection) {
            final KeyT key = keyExtractor.apply(value);
            Set<ValueT> valuesForKey = groupedEntities.computeIfAbsent(key, k -> new HashSet<>());
            valuesForKey.add(value);
        }
        return groupedEntities;
    }

    /**
     * Groups a collection of values by an extracted key, allowing more than one value per key and more
     * than one key per value.
     *
     * If you know that there are no duplicates in the collection, you can also use
     * {@link #groupUnique(Function)}.
     * If values always have exactly one key, use
     * {@link #groupWithDuplicateValues(Function)}.
     *
     * @param keysExtractor A function to extract all possible keys from a value
     * @param <KeyT> The type of the key
     * @return A map of values grouped by their respective keys
     */
    public <KeyT> Map<KeyT, Set<ValueT>> groupWithDuplicateKeysAndValues(Function<ValueT,
            Collection<KeyT>> keysExtractor) {
        Map<KeyT, Set<ValueT>> groupedEntities = new HashMap<>();
        for (ValueT value : collection) {
            final Collection<KeyT> keys = keysExtractor.apply(value);
            for (KeyT key : keys) {
                Set<ValueT> valuesForKey = groupedEntities.computeIfAbsent(key, k -> new HashSet<>());
                valuesForKey.add(value);
            }
        }
        return groupedEntities;
    }

    /**
     * Groups a collection of values by an extracted key, allowing only one value per key.
     *
     * This function throws an exception if two values have the same key. If you want to allow
     * duplicates, you an use {@link #groupWithDuplicateValues(Function)}.
     *
     * @param keyExtractor A function to extract the key from a value
     * @param <KeyT> The type of the key
     * @return A key-to-value map
     */
    public <KeyT> Map<KeyT, ValueT> groupUnique(Function<ValueT, KeyT> keyExtractor) {
        Map<KeyT, ValueT> groupedEntities = new HashMap<>();
        for (ValueT value : collection) {
            final KeyT key = keyExtractor.apply(value);
            if (groupedEntities.get(key) != null) {
                throw new DuplicateElementException(key, collection);
            }
            groupedEntities.put(key, value);
        }
        return groupedEntities;
    }

    public static class DuplicateElementException extends RuntimeException {

        DuplicateElementException(Object key, Collection<?> elements) {
            super("Duplicate element  for key " + key + ": " + elements);
        }
    }
}

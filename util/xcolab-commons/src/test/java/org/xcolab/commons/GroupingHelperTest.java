package org.xcolab.commons;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GroupingHelperTest {

    private static final GroupingHelper<Integer> GROUPING_HELPER = new GroupingHelper<>(
            Arrays.asList(1, 2, 3));

    @Test
    public void groupWithDuplicateValues() {
        Map<Integer, Set<Integer>> expected = new HashMap<>();
        expected.put(0, new HashSet<>(Collections.singletonList(1)));
        expected.put(1, new HashSet<>(Arrays.asList(1, 2)));
        expected.put(2, new HashSet<>(Arrays.asList(2, 3)));
        expected.put(3, new HashSet<>(Collections.singletonList(3)));

        final Map<Integer, Set<Integer>> result =
                GROUPING_HELPER.groupWithDuplicateKeysAndValues(
                        number -> Arrays.asList(number, number -1));
        Assert.assertEquals("Unexpected map content", expected, result);
    }

    @Test
    public void groupWithDuplicateKeysAndValues() {
        Map<Integer, Set<Integer>> expected = new HashMap<>();
        expected.put(0, new HashSet<>(Collections.singletonList(1)));
        expected.put(1, new HashSet<>(Arrays.asList(2, 3)));

        final Map<Integer, Set<Integer>> result =
                GROUPING_HELPER.groupWithDuplicateValues(number -> number / 2);
        Assert.assertEquals("Unexpected map content", expected, result);
    }

    @Test
    public void groupUnique() {
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(2, 1);
        expected.put(4, 2);
        expected.put(6, 3);

        final Map<Integer, Integer> result =
                GROUPING_HELPER.groupUnique(number -> number * 2);
        Assert.assertEquals("Unexpected map content", expected, result);
    }
}

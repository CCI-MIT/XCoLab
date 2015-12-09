package org.xcolab.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by johannes on 12/5/15.
 */
public class ListUtil {
    /**
     * Merges k sorted lists with a total of n entries of type T into a single list preserving order in O(n log k).
     *
     * @param inLists list of k lists already sorted using the same comparator given as second parameter
     * @param comparator the comparator to be used. has to provide the same ordering as the input lists
     * @param <T> the type of the list elements
     * @return a sorted list combining the values of all input lists
     */
    public static <T> List<T> mergeSortedLists(List<List<T>> inLists, Comparator<T> comparator) {
        PriorityQueue<ListContainer<T>> minHeap = new PriorityQueue<>(inLists.size());
        int size = 0;
        for (List<T> inList : inLists) {
            if (!inList.isEmpty()) {
                minHeap.add(new ListContainer<>(inList, comparator));
                size += inList.size();
            }
        }
        List<T> outList = new ArrayList<>(size);

        while (!minHeap.isEmpty()) {
            ListContainer<T> minContainer = minHeap.poll();
            outList.add(minContainer.poll());
            if (minContainer.hasNext()) {
                minHeap.add(minContainer);
            }
        }

        return outList;
    }

    private static class ListContainer<T> implements Comparable<ListContainer<T>> {

        private final Comparator<T> comparator;
        private final Iterator<T> iterator;
        private T currentItem;

        ListContainer(List<T> list, Comparator<T> comparator) {
            this.comparator = comparator;
            this.iterator = list.iterator();
            currentItem = iterator.next();
        }

        T poll() {
            final T ret = currentItem;
            currentItem = iterator.next();
            return ret;
        }

        T peek() {
            return currentItem;
        }

        boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public int compareTo(ListContainer<T> o) {
            return comparator.compare(currentItem, o.peek());
        }
    }
}

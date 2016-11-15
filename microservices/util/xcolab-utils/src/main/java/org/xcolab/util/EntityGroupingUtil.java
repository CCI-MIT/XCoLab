package org.xcolab.util;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a utility class to group entities by certain other criteria.
 * It's in a separate class, because the liferay service layer does not support returning maps!
 */

public final class EntityGroupingUtil {

    private EntityGroupingUtil() { }



    public static <SearchKey, MapKey, MapVal> Map<MapKey, MapVal> getInnerMapOrCreate(
            SearchKey searchKey, Map<SearchKey, Map<MapKey, MapVal>> searchMap) {
        Map<MapKey, MapVal> innerMap = searchMap.get(searchKey);
        if (innerMap == null) {
            innerMap = new HashMap<>();
            searchMap.put(searchKey, innerMap);
        }
        return innerMap;
    }

    public static <SearchKey, ListVal> List<ListVal> getInnerListOrCreate(
            SearchKey searchKey, Map<SearchKey, List<ListVal>> searchMap) {
        List<ListVal> innerList = searchMap.get(searchKey);
        if (innerList == null) {
            innerList = new ArrayList<>();
            searchMap.put(searchKey, innerList);
        }
        return innerList;
    }
}

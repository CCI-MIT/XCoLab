package org.xcolab.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for common conversions of ids lists to strings or models.
 */
//TODO: this is currently an incomplete copy of the equivalent in liferay's service layer
public final class IdListUtil {
    private final static Logger _log = LoggerFactory.getLogger(IdListUtil.class);

    private IdListUtil() { }

    /**
     * Converts a string representation of id lists into an actual list
     * The list should only consists of numbers, commas, and (optionally) any amount of spaces.
     */
    //TODO: improve error handling
    public static List<Long> getIdsFromString(String commaSeparated) {
        if (StringUtils.isEmpty(commaSeparated)) {
            return Collections.emptyList();
        }
        String[] stringIds = commaSeparated.trim().split("\\s*,\\s*");
        List<Long> longsIds = new ArrayList<>(stringIds.length);
        for (String stringId : stringIds) {
            try {
                longsIds.add(Long.parseLong(stringId));
            } catch (NumberFormatException e) {
                _log.error(String.format("Could not parse id %s in id list %s", stringId, Arrays.asList(stringIds)), e);
            }
        }
        return longsIds;
    }

    /**
     * Converts a list of ids into a comma separated string
     */
    public static String getStringFromIds(List<Long> ids) {
        return StringUtils.join(ids, ',');
    }

    public static abstract class IdListObjectConverter<T> {

        public final List<T> fromIdList(List<Long> idList) {
            List<T> objects = new ArrayList<>(idList.size());
            for (long id : idList) {
                objects.add(getObject(id));
            }
            return objects;
        }

        public final List<Long> toIdList(List<T> objectList) {
            List<Long> ids = new ArrayList<>(objectList.size());
            for (T object : objectList) {
                ids.add(getId(object));
            }
            return ids;
        }

        public final String toIdString(List<T> objects) {
            return getStringFromIds(toIdList(objects));
        }

        public final List<T> fromIdString(String idString) {
            return fromIdList(getIdsFromString(idString));
        }

        /**
         * Retrieves a single object of type T from an id.
         * @param id the id of the object to be fetched
         * @return an object of type T
         */
        public abstract T getObject(long id);

        /**
         * Gets the id of a single object of type T
         * @param object the object
         * @return the object's id
         */
        public abstract long getId(T object);
    }


}
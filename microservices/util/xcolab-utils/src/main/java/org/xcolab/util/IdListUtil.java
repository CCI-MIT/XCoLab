package org.xcolab.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for common conversions of ids lists to strings or models.
 */
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
    public static String getStringFromIds(Collection<Long> ids) {
        return StringUtils.join(ids, ',');
    }
}
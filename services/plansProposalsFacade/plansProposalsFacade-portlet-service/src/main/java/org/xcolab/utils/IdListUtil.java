package org.xcolab.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by johannes on 11/4/15.
 */
public class IdListUtil {

    private final static Log _log = LogFactoryUtil.getLog(IdListUtil.class);

    public static List<Long> getIdsFromString(String commaSeparated) {
        String[] stringIds = commaSeparated.split("\\s*,\\s*");
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

    public static String getStringFromIds(List<Long> ids) {
        return StringUtils.join(ids, ',');
    }
}

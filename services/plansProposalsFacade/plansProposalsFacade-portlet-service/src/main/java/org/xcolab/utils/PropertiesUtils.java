package org.xcolab.utils;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.util.portlet.PortletProps;
import org.apache.commons.lang3.StringUtils;

public final class PropertiesUtils {

    private PropertiesUtils() { }

    public static String get(String key) {
        String val = PortletProps.get(key);
        if (StringUtils.isBlank(val)) {
            return PropsUtil.get(key);
        }
        return val;
    }
}

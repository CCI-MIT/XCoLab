package org.xcolab.utils;

import org.apache.commons.lang.StringUtils;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.util.portlet.PortletProps;

public class PropertiesUtils {
    public static String get(String key) {
        String val = PortletProps.get(key);
        if (StringUtils.isBlank(val)) {
            return PropsUtil.get(key);
        }
        return val;
    }
}

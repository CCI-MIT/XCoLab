package org.xcolab.commons.utils;

import org.apache.commons.lang3.StringUtils;

import com.liferay.portal.util.PropsUtil;
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

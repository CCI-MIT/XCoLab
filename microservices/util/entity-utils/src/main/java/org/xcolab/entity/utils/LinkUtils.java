package org.xcolab.entity.utils;

import org.apache.commons.lang3.StringUtils;

import javax.portlet.PortletRequest;

public final class LinkUtils {

    private LinkUtils() {
    }

    public static String getBaseUri(PortletRequest request) {
        String baseUri = String.format("%s://%s", request.getScheme(), request.getServerName());
        int port = request.getServerPort();
        if (port != 80 && port != 443) {
            baseUri += ":" + port;
        }
        return baseUri;
    }

    public static String getNonBlankStringOrDefault(String string, String defaultString) {
        if (StringUtils.isNotBlank(string)) {
            return string;
        }
        return defaultString;
    }
}

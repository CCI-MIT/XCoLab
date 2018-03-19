package org.xcolab.commons.http.servlet;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtil {

    private RequestUtil() {
    }

    public static String getOriginalUri(HttpServletRequest request) {
        String forwardedRequestUri = (String) request
                .getAttribute("javax.servlet.forward.request_uri");
        if (StringUtils.isNotEmpty(forwardedRequestUri)) {
            return forwardedRequestUri;
        }

        return request.getRequestURI();
    }

}

package org.xcolab.commons.http.servlet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    public static HttpServletRequest getRequest() {
        final ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }
}

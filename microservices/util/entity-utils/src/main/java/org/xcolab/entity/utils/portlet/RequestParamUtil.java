package org.xcolab.entity.utils.portlet;

import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestParamUtil {

    public static Long getLong(HttpServletRequest request, String paramName, Long defaultValue) {
        String value = getString(request, paramName);
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static Long getLong(HttpServletRequest request, String paramName) {
        return getLong(request, paramName, 0L);
    }

    public static Boolean getBoolean(HttpServletRequest request, String paramName) {

        String value = getString(request, paramName);
        if (value != null) {
            return Boolean.valueOf(value);
        }
        return false;
    }

    public static String getString(HttpServletRequest request, String paramName,
            String defaultValue) {
        //noinspection unchecked
        Map<String, String> pathVariables = (Map) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (pathVariables != null && pathVariables.get(paramName) != null) {
            return pathVariables.get(paramName);
        }
        if (request.getParameter(paramName) != null) {
            return request.getParameter(paramName);
        }
        return defaultValue;
    }

    public static String getString(HttpServletRequest request, String paramName) {
        return getString(request, paramName, null);
    }

    public static Integer getInteger(HttpServletRequest request, String paramName) {
        return getInteger(request, paramName, 0);
    }

    public static Integer getInteger(HttpServletRequest request, String paramName,
            Integer defaultValue) {
        String value = getString(request, paramName);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}

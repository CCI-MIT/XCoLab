package org.xcolab.entity.utils.portlet;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class RequestParamUtil {

    public static Long getLong(HttpServletRequest request, String paramName, HashMap<String,String> pathParams) {
        return getLong(request, paramName, pathParams ,0L);
    }

    public static Long getLong(HttpServletRequest request, String paramName) {
        return getLong(request, paramName, null ,0L);
    }

    public static Long getLong(HttpServletRequest request, String paramName,
            HashMap<String, String> pathParameters, Long defaultValue) {
        String value = (request.getAttribute(paramName)!=null)?((String)request.getAttribute(paramName)):(request.getParameter(paramName));
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
            }
        }
        if (pathParameters != null && pathParameters.get(paramName) != null) {
            try {
                return Long.parseLong(pathParameters.get(paramName));
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static Boolean getBoolean(HttpServletRequest request, String paramName) {

        String value = (request.getAttribute(paramName)!=null)?((String)request.getAttribute(paramName)):(request.getParameter(paramName));
        if (value != null) {
            return Boolean.valueOf(value);
        }
        return false;
    }

    public static String getString(HttpServletRequest request, String paramName,
            HashMap<String, String> pathParams,
            String defaultValue) {

        String value = (request.getAttribute(paramName)!=null)?((String)request.getAttribute(paramName)):(request.getParameter(paramName));
        if (value != null) {
            return value;
        }
        if (pathParams != null && pathParams.get(paramName)!=null ) {
            return pathParams.get(paramName);
        }
        return defaultValue;

    }
    public static String getString(HttpServletRequest request, String paramName,HashMap<String, String> pathParams ) {
        return getString(request, paramName,pathParams, null);
    }
    public static String getString(HttpServletRequest request, String paramName) {
        return getString(request, paramName,null, null);
    }

    public static Integer getInteger(HttpServletRequest request, String paramName, HashMap<String, String> pathParams,
            Integer defaultValue) {
        String value = (request.getAttribute(paramName)!=null)?((String)request.getAttribute(paramName)):(request.getParameter(paramName));
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
            }
        }

        if (pathParams != null && pathParams.get(paramName)!= null) {
            try {
                return Integer.parseInt(pathParams.get(paramName));
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    public static Integer getInteger(HttpServletRequest request, String paramName) {
        return getInteger(request, paramName,null, 0);
    }
    public static Integer getInteger(HttpServletRequest request, String paramName, HashMap<String, String> pathParams) {
        return getInteger(request, paramName,pathParams, 0);
    }
}

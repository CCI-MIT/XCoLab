package org.xcolab.entity.utils.portlet;

import javax.servlet.http.HttpServletRequest;

public class RequestParamUtil {
    public static Long getLong(HttpServletRequest request, String paramName){
        return getLong(request, paramName, 0L);
    }
    public static Long getLong(HttpServletRequest request, String paramName, Long defaultValue){
        String value = request.getParameter(paramName);
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e){
            }
        }
        return defaultValue;
    }

    public static Boolean getBoolean(HttpServletRequest request, String paramName){

        String value = request.getParameter(paramName);
        if (value != null) {
            return Boolean.valueOf(value);
        }
        return false;
    }

    public static String getString(HttpServletRequest request, String paramName, String defaultValue) {
        String value = request.getParameter(paramName);
        if(value!=null) {
            return value;
        }else{
            return defaultValue;
        }
    }
    public static String getString(HttpServletRequest request, String paramName) {
        return getString(request,paramName, null);
    }

    public static Integer getInteger(HttpServletRequest request, String paramName, Integer defaultValue) {
        String value = request.getParameter(paramName);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e){
            }
        }
        return defaultValue;
    }
    public static Integer getInteger(HttpServletRequest request, String paramName){
        return getInteger(request, paramName,0);
    }
}

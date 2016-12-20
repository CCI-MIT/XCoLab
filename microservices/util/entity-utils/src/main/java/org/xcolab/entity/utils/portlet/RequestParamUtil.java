package org.xcolab.entity.utils.portlet;

import javax.portlet.PortletRequest;

public class RequestParamUtil {
    public static Long getLong(PortletRequest portletRequest, String paramName){
        return getLong(portletRequest,paramName,0l);
    }
    public static Long getLong(PortletRequest portletRequest, String paramName, Long defaultValue){
        String value = portletRequest.getParameter(paramName);
        if(value!=null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e){
            }
        }
        return defaultValue;
    }

    public static Boolean getBoolean(PortletRequest portletRequest, String paramName){

        String value = portletRequest.getParameter(paramName);
        if(value!=null) {
            return Boolean.valueOf(value);
        }
        return false;
    }

    public static String getString(PortletRequest portletRequest, String paramName, String defaultValue) {
        String value = portletRequest.getParameter(paramName);
        if(value!=null) {
            return value;
        }else{
            return defaultValue;
        }
    }
    public static String getString(PortletRequest portletRequest, String paramName) {
        return getString(portletRequest,paramName, null);
    }

    public static Integer getInteger(PortletRequest portletRequest, String paramName, Integer defaultValue) {
        String value = portletRequest.getParameter(paramName);
        if(value!=null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e){
            }
        }
        return defaultValue;
    }
    public static Integer getInteger(PortletRequest portletRequest, String paramName){
        return getInteger(portletRequest,paramName,0);
    }
}

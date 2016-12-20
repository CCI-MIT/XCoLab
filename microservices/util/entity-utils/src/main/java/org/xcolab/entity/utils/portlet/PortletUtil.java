package org.xcolab.entity.utils.portlet;

import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class PortletUtil {

    private PortletUtil() {
    }

    public static HttpServletRequest getHttpServletRequest(PortletRequest portletRequest) {
        return (HttpServletRequest) portletRequest.getAttribute("javax.servlet.request");
    }



    public static String getCurrentUrl(PortletRequest portletRequest) {
        return (String)portletRequest.getAttribute("CURRENT_URL");
    }
    public static void setPageTitle(String title, HttpServletRequest portletRequest){
        //To be done using new template engine
        portletRequest.setAttribute("LIFERAY_SHARED_PAGE_TITLE", title);
    }

    public static void setPageDescription(String pageDescription, HttpServletRequest portletRequest){
        portletRequest.setAttribute("LIFERAY_SHARED_PAGE_DESCRIPTION", pageDescription);
    }
    public static void setPageSubtitle(String pageSubTitle, HttpServletRequest portletRequest){
        portletRequest.setAttribute("LIFERAY_SHARED_PAGE_SUBTITLE", pageSubTitle);
    }


}

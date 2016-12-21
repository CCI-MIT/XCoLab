package org.xcolab.entity.utils.portlet;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

public final class PortletUtil {

    private PortletUtil() {
    }

    public static HttpServletRequest getHttpServletRequest(PortletRequest portletRequest) {
        return (HttpServletRequest) portletRequest.getAttribute("javax.servlet.request");
    }



    public static String getCurrentUrl(PortletRequest portletRequest) {
        return (String)portletRequest.getAttribute("CURRENT_URL");
    }
    public static void setPageTitle(String title, HttpServletRequest httpServletRequest){
        //To be done using new template engine
        if(httpServletRequest!=null) {
            //httpServletRequest.setAttribute("LIFERAY_SHARED_PAGE_TITLE", title);
        }
    }

    public static void setPageDescription(String pageDescription, HttpServletRequest httpServletRequest){
        if(httpServletRequest!=null) {
            //httpServletRequest.setAttribute("LIFERAY_SHARED_PAGE_DESCRIPTION", pageDescription);
        }
    }
    public static void setPageSubtitle(String pageSubTitle, HttpServletRequest httpServletRequest){
        if(httpServletRequest!=null) {
            //httpServletRequest.setAttribute("LIFERAY_SHARED_PAGE_SUBTITLE", pageSubTitle);
        }
    }


}

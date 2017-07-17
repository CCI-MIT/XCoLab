package org.xcolab.view.util.entity.portlet.session;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

public class SessionErrors {

    private static final String _CLASS_NAME = "com.liferay.portal.kernel.servlet.";

    public static void add(HttpServletRequest servletRequest, String key){
        SessionHelper.add(servletRequest, key, _CLASS_NAME);
    }

    public static Iterator<String> iterator(HttpServletRequest servletRequest){
        return SessionHelper.iterator(servletRequest.getSession(true),_CLASS_NAME);
    }

    public static void clear(HttpServletRequest servletRequest) {
        SessionHelper.clear(servletRequest.getSession(true),_CLASS_NAME);
    }

    public static boolean isEmpty(HttpServletRequest servletRequest) {
        return SessionHelper.isEmpty(servletRequest.getSession(true),_CLASS_NAME);
    }
}

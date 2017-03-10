package org.xcolab.view.util.entity.portlet.session;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

public class SessionMessages {

    private static final String CLASS_NAME = "com.liferay.portal.kernel.servlet.SessionMessages";

    public static void add(HttpServletRequest request, String key) {
        SessionHelper.add(request, key, CLASS_NAME);
    }

    public static Iterator<String> iterator(HttpServletRequest request) {
        return SessionHelper.iterator(request.getSession(), CLASS_NAME);
    }

    public static void clear(HttpServletRequest request) {
        SessionHelper.clear(request.getSession(), CLASS_NAME);
    }

}

package org.xcolab.entity.utils.portlet.session;

import org.xcolab.entity.utils.portlet.PortletUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelper {
    public static void add(PortletRequest portletRequest, String key, String className){
        add(PortletUtil.getHttpServletRequest(portletRequest),key, className);
    }
    public static void add(HttpServletRequest servletRequest, String key, String className){
        Map map = _getMap(servletRequest.getSession(false), true, className);
        if(map != null) {
            map.put(key, key);
        }
    }

    private static Map<String, Object> _getMap(HttpSession session, boolean createIfAbsent,String className) {
        if(session == null) {
            return null;
        } else {
            Object map = null;

            try {
                map = (Map)session.getAttribute(className);
                if(map == null && createIfAbsent) {
                    map = new LinkedHashMap();
                    session.setAttribute(className, map);
                }
            } catch (IllegalStateException var3) {
                ;
            }

            return (Map)map;
        }
    }
    public static void clear(HttpSession session, String className) {
        Map map = _getMap(session, false, className);
        if(map != null) {
            map.clear();
        }

    }
    public static boolean isEmpty(HttpSession session, String className){
         Map map = _getMap(session,false,className);
        if(map!=null ){
            return map.isEmpty();
        }else{
            return false;
        }
    }
    public static Iterator<String> iterator(HttpSession session,String className) {
        Map map = _getMap(session, false, className);
        if(map == null) {
            List set1 = Collections.emptyList();
            return set1.iterator();
        } else {
            Set set = Collections.unmodifiableSet(map.keySet());
            return set.iterator();
        }
    }
}

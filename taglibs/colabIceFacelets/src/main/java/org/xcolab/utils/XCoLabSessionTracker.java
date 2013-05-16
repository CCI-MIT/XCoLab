package org.xcolab.utils;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;
import com.liferay.portal.kernel.servlet.PortletSessionListenerManager;

public class XCoLabSessionTracker implements HttpSessionListener, Serializable{
    public static void extendSession(HttpSession s) {
        _instance._extend(s);
    }
    
    public static void add(HttpSession session) {
        _instance._add(session);
    }
    
    private void _extend(HttpSession s) {
        Set<HttpSession> sessions = _sessions.get(s.getId());
        if (sessions != null) {
            for (HttpSession session: sessions) {
                session.setAttribute(getClass().getName() + "EXTEND_SESSION", Boolean.TRUE);
                session.removeAttribute(getClass().getName() + "EXTEND_SESSION");
            }
        }
        
    }

    private XCoLabSessionTracker() {
        _sessions = new ConcurrentHashMap<String, Set<HttpSession>>();

        PortletSessionListenerManager.addHttpSessionListener(this);
    }
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        _instance._add(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        _instance._remove(se.getSession().getId());
    }
    
    private void _remove(String sessionId) {

        _sessions.remove(sessionId);
    }
    
    private void _add(HttpSession session) {
        String sessionId = session.getId();

        Set<HttpSession> sessions = _sessions.get(sessionId);

        if (sessions == null) {
            sessions = new ConcurrentHashSet<HttpSession>();

            Set<HttpSession> previousSessions = _sessions.putIfAbsent(
                sessionId, sessions);

            if (previousSessions != null) {
                sessions = previousSessions;
            }
        }

        sessions.add(session);
    }

    private static XCoLabSessionTracker _instance =
        new XCoLabSessionTracker();

    private transient ConcurrentMap<String, Set<HttpSession>> _sessions;

}

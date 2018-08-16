package org.xcolab.view.config.spring.logging;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
public class SessionListener implements HttpSessionListener {

    private static final Logger log = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        log.debug("Session {} created.", StringUtils.abbreviate(event.getSession().getId(), 10));
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        log.debug("Session {} destroyed.", StringUtils.abbreviate(event.getSession().getId(), 10));
    }
}

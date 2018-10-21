package org.xcolab.view.config.sentry;

import io.sentry.event.EventBuilder;
import io.sentry.event.helper.EventBuilderHelper;
import io.sentry.servlet.SentryServletRequestListener;

import javax.servlet.http.HttpServletRequest;

public class CustomHttpEventBuilderHelper implements EventBuilderHelper {

    @Override
    public void helpBuildingEvent(EventBuilder eventBuilder) {
        HttpServletRequest servletRequest = SentryServletRequestListener.getServletRequest();
        if (servletRequest == null) {
            return;
        }

        addHttpInterface(eventBuilder, servletRequest);
    }

    private void addHttpInterface(EventBuilder eventBuilder, HttpServletRequest servletRequest) {
        eventBuilder.withSentryInterface(new CustomSentryHttpInterface(servletRequest), false);
    }
}

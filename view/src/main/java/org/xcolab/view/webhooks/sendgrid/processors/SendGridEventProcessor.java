package org.xcolab.view.webhooks.sendgrid.processors;

import org.xcolab.view.webhooks.sendgrid.model.EventType;
import org.xcolab.view.webhooks.sendgrid.model.SendGridEvent;

public interface SendGridEventProcessor {

    boolean handles(EventType eventType);

    void process(SendGridEvent event);

}

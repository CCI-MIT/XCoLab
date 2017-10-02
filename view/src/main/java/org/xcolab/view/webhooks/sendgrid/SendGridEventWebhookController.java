package org.xcolab.view.webhooks.sendgrid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.view.webhooks.sendgrid.model.SendGridEvent;
import org.xcolab.view.webhooks.sendgrid.processors.SendGridEventProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/webhooks/send-grid")
public class SendGridEventWebhookController {

    private static final Logger log = LoggerFactory.getLogger(SendGridEventWebhookController.class);

    private final List<SendGridEventProcessor> eventProcessors;

    @Autowired
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public SendGridEventWebhookController(Optional<List<SendGridEventProcessor>> eventProcessors) {
        this.eventProcessors = eventProcessors.orElseGet(ArrayList::new);
    }

    @PostMapping("events")
    public void receiveEvents(HttpServletRequest request, HttpServletResponse response,
            @RequestBody List<SendGridEvent> events) {
        log.debug("Received {} events from SendGrid.", events.size());
        for (SendGridEvent event : events) {
            eventProcessors.stream()
                    .filter(processor -> processor.handles(event.getEvent()))
                    .forEach(processor -> processor.process(event));
        }
    }
}

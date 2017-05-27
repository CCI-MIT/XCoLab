package org.xcolab.view.crons;

import org.ocpsoft.common.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.view.util.entity.activityEntry.ActivitySubscriptionEmailHelper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@ConditionalOnProperty(prefix = "xcolab.crons", matchIfMissing = true,
        name = {"enabled", "notifications.enabled"})
public class NotificationCron {

    private static final Logger _log = LoggerFactory.getLogger(NotificationCron.class);

    private static final long RATE = 60_000;
    private static final long DELAY = 30_000;

    private final ActivitySubscriptionEmailHelper activitySubscriptionEmailHelper;

    public NotificationCron(ActivitySubscriptionEmailHelper activitySubscriptionEmailHelper) {
        Assert.notNull(activitySubscriptionEmailHelper,
                "ActivitySubscriptionEmailHelper bean is required");
        this.activitySubscriptionEmailHelper = activitySubscriptionEmailHelper;
        _log.info("Initializing notification cron with rate = {}s", RATE / 1000);
    }

    @Scheduled(fixedRate = RATE, initialDelay = DELAY)
    public void sendNotifications() {
        _log.debug("Sending notifications...");
        activitySubscriptionEmailHelper.sendEmailNotifications();
    }

    @Controller
    @ConditionalOnProperty(prefix = "xcolab.crons", matchIfMissing = true,
        name = "notifications.http-endpoint.enabled")
    public static class TriggerController {

        private final NotificationCron notificationCron;

        @Autowired
        public TriggerController(NotificationCron notificationCron) {
            _log.info("Initializing http endpoint to trigger notifications.");
            Assert.notNull(notificationCron, "NotificationCron bean is required");
            this.notificationCron = notificationCron;
        }

        @RequestMapping("/crons/notifications/run")
        public ResponseEntity<String> sendEmailNotifications(HttpServletRequest request,
                HttpServletResponse response) throws IOException {
            _log.debug("Cron triggered via http endpoint.");
            notificationCron.sendNotifications();
            return ResponseEntity.ok("Emails sent.");
        }
    }
}

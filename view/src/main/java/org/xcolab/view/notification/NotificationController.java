package org.xcolab.view.notification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.pojo.Notification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class NotificationController {

    @GetMapping("/notificationMessage")
    public NotificationResponse showNotification(HttpServletRequest request,
            HttpServletResponse response) {

        Notification firstNotification = AdminClient.getFirstNotification();

        if (firstNotification != null) {
            return new NotificationResponse(firstNotification.getNotificationId(),
                    firstNotification.getNotificationText(), true);
        }
        return new NotificationResponse(0L, "", false);
    }

    public static class NotificationResponse {

        private final long notificationId;
        private final String notificationText;
        private final boolean success;

        public NotificationResponse(long notificationId, String notificationText, boolean success) {
            this.notificationId = notificationId;
            this.notificationText = notificationText;
            this.success = success;
        }

        public long getNotificationId() {
            return notificationId;
        }

        public String getNotificationText() {
            return notificationText;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}

package org.xcolab.view.activities;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.view.util.entity.activityEntry.ActivitySubscriptionEmailHelper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ActivitySubscriptionEmailFilter {

    @RequestMapping("/emails/sendNotifications")
    public ResponseEntity<String> sendEmailNotifications(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ActivitySubscriptionEmailHelper.sendEmailNotifications();
        return ResponseEntity.ok("Emails sent.");
    }
}

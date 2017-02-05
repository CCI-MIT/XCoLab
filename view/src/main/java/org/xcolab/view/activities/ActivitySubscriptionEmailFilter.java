package org.xcolab.view.activities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.entity.utils.activityEntry.ActivitySubscriptionEmailHelper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ActivitySubscriptionEmailFilter {

    @RequestMapping("/emails/sendNotifications")
    public void sendEmailNotifications(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ActivitySubscriptionEmailHelper.sendEmailNotifications();
        response.setStatus(204);
    }
}

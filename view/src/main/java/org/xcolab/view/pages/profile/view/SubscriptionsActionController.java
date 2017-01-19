package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.view.pages.profile.wrappers.ActivitySubscriptionWrapper;
import org.xcolab.view.pages.profile.wrappers.UserSubscriptionsWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{memberId}/subscriptions")
public class SubscriptionsActionController {

    @PostMapping("remove")
    public void handleRemoveSubscriptionAction(HttpServletRequest request, Model model, HttpServletResponse response,
            @ModelAttribute("userSubscriptions") UserSubscriptionsWrapper userSubscriptions,
            @PathVariable long memberId) throws IOException {

        for (ActivitySubscriptionWrapper subscription : userSubscriptions.getSubscriptions()) {
            if (subscription.getSelected()) {
                ActivitiesClientUtil.deleteSubscription(subscription.getSubscriptionPk());
            }
        }
        response.sendRedirect("/members/profile/" + memberId + "/subscriptions/manage");
    }
}

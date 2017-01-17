package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.view.pages.profile.wrappers.ActivitySubscriptionWrapper;
import org.xcolab.view.pages.profile.wrappers.UserSubscriptionsWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class SubscriptionsActionController {

    @RequestMapping(params = {"action=removeSubscription"})
    public void handleRemoveSubscriptionAction(HttpServletRequest request, Model model, HttpServletResponse response,
            @ModelAttribute("userSubscriptions") UserSubscriptionsWrapper userSubscriptions,
            @RequestParam Long userId) throws IOException {

        for (ActivitySubscriptionWrapper subscription : userSubscriptions.getSubscriptions()) {
            if (subscription.getSelected()) {
                ActivitiesClientUtil.deleteSubscription(subscription.getSubscriptionPk());
            }
        }
        response.sendRedirect("/web/guest/member/-/member/userId/" + userId.toString() + "/page/subscriptionsManage");
    }
}

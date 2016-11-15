package org.xcolab.portlets.userprofile.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.portlets.userprofile.wrappers.ActivitySubscriptionWrapper;
import org.xcolab.portlets.userprofile.wrappers.UserSubscriptionsWrapper;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class SubscriptionsActionController {

    @RequestMapping(params = {"action=removeSubscription"})
    public void handleRemoveSubscriptionAction(ActionRequest request, Model model, ActionResponse response,
            @ModelAttribute("userSubscriptions") UserSubscriptionsWrapper userSubscriptions,
            @RequestParam Long userId) throws PortalException, SystemException, IOException {

        for (ActivitySubscriptionWrapper subscription : userSubscriptions.getSubscriptions()) {
            if (subscription.getSelected()) {
                ActivitiesClientUtil.deleteSubscription(subscription.getSubscriptionPk());
            }
        }
        response.sendRedirect("/web/guest/member/-/member/userId/" + userId.toString() + "/page/subscriptionsManage");
    }
}

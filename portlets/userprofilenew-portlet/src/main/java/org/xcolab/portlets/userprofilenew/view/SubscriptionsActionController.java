package org.xcolab.portlets.userprofilenew.view;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.userprofilenew.wrappers.ActivitySubscriptionWrapper;
import org.xcolab.portlets.userprofilenew.wrappers.UserSubscriptionsWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class SubscriptionsActionController {


    @RequestMapping(params = {"action=removeSubscription"})
    public void handleRemoveSubscriptionAction(ActionRequest request, Model model, ActionResponse response,
                                               @ModelAttribute("userSubscriptions") UserSubscriptionsWrapper userSubscriptions,
                                               @RequestParam(required = true) Long userId
    ) throws PortalException, SystemException, IOException {

        for (ActivitySubscriptionWrapper subscription : userSubscriptions.getSubscriptions()) {
            if (subscription.getSelected()) {
                ActivitySubscriptionLocalServiceUtil.delete(ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscription.getSubscriptionPk()));
            }
        }
        response.sendRedirect("/web/guest/member/-/member/userId/" + userId.toString()+ "/page/subscriptionsManage");

    }

    /*
    @RequestMapping(params = {"action=changeSubscriptionFilter"})
    public void handleChangeSubscriptionFilterAction(ActionRequest request, Model model, ActionResponse response,
                             @RequestParam(required = false) String filterType)
            throws PortalException, SystemException {

    }*/

    @RequestMapping(params = {"action=unlinkSSO"})
    public void handleUnlinkSSOAction(ActionRequest request, Model model, ActionResponse response,
                                               @RequestParam(required = true) String userId,
                                               @RequestParam(required = true) String accountType
    ) throws PortalException, SystemException, IOException {

        User u = UserLocalServiceUtil.getUser(Long.parseLong(userId));
        if (accountType.equalsIgnoreCase("FACEBOOK")) u.setFacebookId(0);
        else if (accountType.equalsIgnoreCase("GOOGLE")) u.setOpenId("");
        UserLocalServiceUtil.updateUser(u);

        response.sendRedirect("/web/guest/member/-/member/userId/" + userId + "/page/edit");

    }

}

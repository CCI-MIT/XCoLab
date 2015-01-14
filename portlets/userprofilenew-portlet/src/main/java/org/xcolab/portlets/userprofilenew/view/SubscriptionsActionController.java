package org.xcolab.portlets.userprofilenew.view;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.userprofilenew.wrappers.ActivitySubscriptionWrapper;
import org.xcolab.portlets.userprofilenew.wrappers.UserSubscriptionsWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
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

}

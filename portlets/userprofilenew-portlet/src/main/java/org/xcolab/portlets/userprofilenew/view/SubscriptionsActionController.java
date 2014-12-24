package org.xcolab.portlets.userprofilenew.view;

import com.ext.portlet.Activity.SubscriptionType;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.userprofilenew.beans.UserBean;
import org.xcolab.portlets.userprofilenew.wrappers.ActivitySubscriptionWrapper;
import org.xcolab.portlets.userprofilenew.wrappers.UserSubscriptionsWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("view")
public class SubscriptionsActionController {


    @RequestMapping(params = {"action=removeSubscription"})
    public void handleRemoveSubscriptionAction(ActionRequest request, Model model, ActionResponse response,
                                               @ModelAttribute("userSubscriptions") UserSubscriptionsWrapper userSubscriptions,
                                                    @RequestParam(required = true) Long userId)
                                                throws PortalException, SystemException, IOException {

        for (ActivitySubscriptionWrapper subscription : userSubscriptions.getSubscriptions()) {
            if (subscription.getSelected()) {
                ActivitySubscriptionLocalServiceUtil.delete(ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscription.getSubscriptionPk()));
            }
        }
        response.sendRedirect("/web/guest/member/-/member/userId/" + userId.toString()+"/page/subscriptionsManage");

    }



    /*
    @RequestMapping(params = {"action=changeSubscriptionFilter"})
    public void handleChangeSubscriptionFilterAction(ActionRequest request, Model model, ActionResponse response,
                             @RequestParam(required = false) String filterType)
            throws PortalException, SystemException {


        response.sendRedirect("/web/guest/member/-/member/userId/" + _currentUserProfile.getUserId().toString()+
                "/page/subscriptionsManage/"+filterType);
        // TODO implement remove
        //ActivitySubscriptionLocalServiceUtil.delete(subscription.getWrapped());
    }*/


    @RequestMapping(params = {"action=unlinkSSO"})
    public void handleUnlinkSSOAction(ActionRequest request, Model model, ActionResponse response,
                                               @RequestParam(required = true) String userId,
                                               @RequestParam(required = true) String accountType)
            throws PortalException, SystemException {

        User u = UserLocalServiceUtil.getUser(Long.parseLong(userId));
        if (accountType.equalsIgnoreCase("FACEBOOK")) u.setFacebookId(0);
        else if (accountType.equalsIgnoreCase("GOOGLE")) u.setOpenId("");
        UserLocalServiceUtil.updateUser(u);

        //response.sendRedirect("/web/guest/member/-/member/userId/" + userId);

    }

}

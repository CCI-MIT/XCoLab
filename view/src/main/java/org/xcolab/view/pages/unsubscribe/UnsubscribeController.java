package org.xcolab.view.pages.unsubscribe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.exceptions.ActivitySubscriptionNotFoundException;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.util.entity.NotificationUnregisterUtils;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.ErrorMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/notifications/unsubscribe")
public class UnsubscribeController {

    private static final String UNSUBSCRIBE_TITLE = "You have been unsubscribed";
    private static final String UNSUBSCRIBE_INDIVIDUAL_SUBSCRIPTION_RESPONSE_TEXT =
            "You may still receive email notifications if you are subscribed to other activity on"
                    + " the Climate CoLab.  "
                    + "To manage your subscriptions, please log in to your account, select “My "
                    + "profile”, and select the “Manage” "
                    + "button underneath “Subscribed Activity” on the right-hand side.";


    @GetMapping("member/{memberId}/subscription/{subscriptionId}/token/{token}/type/{typeId}")
    public String unsubscribe(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable long memberId, @PathVariable long subscriptionId,
            @PathVariable String token, @PathVariable long typeId) {

        Member member = null;
        boolean error = false;
        if (memberId > 0) {
            try {
                member = MembersClient.getMember(memberId);
                error = !NotificationUnregisterUtils.isTokenValid(token, member)
                        || typeId != NotificationUnregisterUtils.ACTIVITY_TYPE;
            } catch (MemberNotFoundException e) {
                return ErrorMessage.error("Please make sure you copied the link correctly or "
                        + "contact an administrator.").withTitle("Invalid unsubscribe link")
                        .flashAndReturnView(request);
            }
        }
        ActivitySubscription subscription = null;
        if (subscriptionId > 0) {
            try {
                subscription = ActivitiesClientUtil.getActivitySubscription(subscriptionId);
                //ActivitySubscriptionLocalServiceUtil.getActivitySubscription(subscriptionId);
                error = !NotificationUnregisterUtils.isTokenValid(token, subscription);
            } catch (ActivitySubscriptionNotFoundException e) {
                //already unsubscribed
            }
        }

        if (error) {
            return ErrorMessage.error("Please make sure you copied the link correctly or "
                    + "contact an administrator.").withTitle("Your unsubscribe token is invalid")
                    .flashAndReturnView(request);
        }

        String responseText = null;
        // unregister user
        if (subscription != null) {
            ActivitiesClientUtil.deleteSubscriptionById(subscription.getPk());
            responseText = UNSUBSCRIBE_INDIVIDUAL_SUBSCRIPTION_RESPONSE_TEXT;
        }

        if (member != null) {
            NotificationUnregisterHandler handler = getUnregisterUserHandler((int) typeId);
            if (handler != null) {
                handler.unregister(member);
                responseText = handler.getSuccessResponse();
            }
        }

        AlertMessage.success("You have successfully unsubscribed!").flash(request);
        model.addAttribute("responseTitle", UNSUBSCRIBE_TITLE);
        model.addAttribute("responseText", responseText);

        return "unsubscribe/view";
    }

    private NotificationUnregisterHandler getUnregisterUserHandler(int type) {
        if (type == NotificationUnregisterUtils.ACTIVITY_TYPE) {
            return new ActivityDailyDigestNotificationUnregisterHandler();
        } else {
            return null;
        }
    }
}


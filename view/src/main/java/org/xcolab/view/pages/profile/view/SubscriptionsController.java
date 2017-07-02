package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.profile.beans.MessageBean;
import org.xcolab.view.pages.profile.utils.UserProfilePermissions;
import org.xcolab.view.pages.profile.wrappers.ActivitySubscriptionWrapper;
import org.xcolab.view.pages.profile.wrappers.UserProfileWrapper;
import org.xcolab.view.pages.profile.wrappers.UserSubscriptionsWrapper;
import org.xcolab.view.util.pagination.PageNavigation;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{memberId}/subscriptions")
public class SubscriptionsController {

    @GetMapping
    public String showUserProfileSubscriptions(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long memberId, Member loggedInMember,
            @RequestParam(required = false, defaultValue = "1") int page) {
        UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
        if (!permissions.getCanAdminProfile(memberId)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(memberId, request);
            populateUserWrapper(currentUserProfile, model);
            currentUserProfile.setSubscriptionsPaginationPageId(page);
            model.addAttribute("pageNavigation", new PageNavigation(
                    "/members/profile/" + memberId + "/subscriptions",
                    page, currentUserProfile.getSubscriptionsPaginationPageMax()));
            return "profile/showUserSubscriptions";
        } catch (MemberNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
    }

    @GetMapping("manage")
    public String showUserSubscriptionsManage(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long memberId, @RequestParam(required = false) String typeFilter) {
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(memberId, request);
            populateUserWrapper(currentUserProfile, model);
            if (typeFilter != null) {
                currentUserProfile.getUserSubscriptions().setFilterType(typeFilter);
            }
            model.addAttribute("userSubscriptions", currentUserProfile.getUserSubscriptions());

            final long contestTypeId = ConfigurationAttributeKey
                    .DEFAULT_CONTEST_TYPE_ID.get();
            final ContestType contestType = ContestTypeClient
                    .getContestType(contestTypeId);
            model.addAttribute("contestType", contestType);

            if (!currentUserProfile.isViewingOwnProfile()) {
                return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
            }
            return "profile/showUserSubscriptionsManage";
        } catch ( MemberNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
    }

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

    private void populateUserWrapper(UserProfileWrapper currentUserProfile, Model model) {
        model.addAttribute("currentUserProfile", currentUserProfile);
        model.addAttribute("userBean", currentUserProfile.getUserBean());
        model.addAttribute("messageBean", new MessageBean());
    }
}

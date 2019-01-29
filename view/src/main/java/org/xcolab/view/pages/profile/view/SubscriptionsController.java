package org.xcolab.view.pages.profile.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.activityentry.ActivityEntryHelper;
import org.xcolab.view.errors.AccessDeniedPage;
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
@RequestMapping("/members/profile/{userId}/subscriptions")
public class SubscriptionsController {

    private final ActivityEntryHelper activityEntryHelper;
    private final IActivityClient activityClient;
    private final IContestTypeClient contestTypeClient;

    @Autowired
    public SubscriptionsController(ActivityEntryHelper activityEntryHelper,
            IContestTypeClient contestTypeClient,
            IActivityClient activityClient) {
        this.activityEntryHelper = activityEntryHelper;
        this.contestTypeClient = contestTypeClient;
        this.activityClient = activityClient;
    }

    @GetMapping
    public String showUserProfileSubscriptions(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper loggedInMember,
            @PathVariable long userId, @RequestParam(defaultValue = "1") int page) {
        UserProfilePermissions permissions = new UserProfilePermissions(loggedInMember);
        if (!permissions.getCanEditMemberProfile(userId)) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(userId,
                    loggedInMember, activityEntryHelper);
            populateUserWrapper(currentUserProfile, model);
            currentUserProfile.setSubscriptionsPaginationPageId(page);
            model.addAttribute("pageNavigation", new PageNavigation(
                    "/members/profile/" + userId + "/subscriptions",
                    page, currentUserProfile.getSubscriptionsPaginationPageMax()));
            return "profile/showUserSubscriptions";
        } catch (MemberNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
    }

    @GetMapping("manage")
    public String showUserSubscriptionsManage(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper loggedInMember,
            @PathVariable long userId, @RequestParam(required = false) String typeFilter) {
        try {
            UserProfileWrapper currentUserProfile = new UserProfileWrapper(userId,
                    loggedInMember, activityEntryHelper);
            populateUserWrapper(currentUserProfile, model);
            if (typeFilter != null) {
                currentUserProfile.getUserSubscriptions().setFilterType(typeFilter);
            }
            model.addAttribute("userSubscriptions", currentUserProfile.getUserSubscriptions());

            final long contestTypeId = ConfigurationAttributeKey
                    .DEFAULT_CONTEST_TYPE_ID.get();
            final ContestType contestType = contestTypeClient.getContestType(contestTypeId);
            model.addAttribute("contestType", contestType);

            if (!currentUserProfile.isViewingOwnProfile()) {
                return new AccessDeniedPage(loggedInMember).toViewName(response);
            }
            return "profile/showUserSubscriptionsManage";
        } catch ( MemberNotFoundException e) {
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
    }

    @PostMapping("remove")
    public void handleRemoveSubscriptionAction(HttpServletRequest request, Model model, HttpServletResponse response,
            @ModelAttribute("userSubscriptions") UserSubscriptionsWrapper userSubscriptions,
            @PathVariable long userId) throws IOException {

        for (ActivitySubscriptionWrapper subscription : userSubscriptions.getSubscriptions()) {
            if (subscription.getSelected()) {
                activityClient.deleteActivitySubscription(subscription.getSubscriptionPk());
            }
        }
        response.sendRedirect("/members/profile/" + userId + "/subscriptions/manage");
    }

    private void populateUserWrapper(UserProfileWrapper currentUserProfile, Model model) {
        model.addAttribute("currentUserProfile", currentUserProfile);
        model.addAttribute("userBean", currentUserProfile.getUserBean());
        model.addAttribute("messageBean", new MessageBean());
    }
}

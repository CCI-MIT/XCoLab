package org.xcolab.portlets.userprofile.view;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.SpamReport;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.SpamReportLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.portlets.userprofile.utils.UserProfileAuthorizationException;
import org.xcolab.portlets.userprofile.utils.UserProfilePermissions;
import org.xcolab.portlets.userprofile.wrappers.SpamReportsWrapper;
import org.xcolab.portlets.userprofile.wrappers.UserProfileWrapper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class SpamReportController {

    @Autowired
    private UserProfileController userProfileController;

    @RenderMapping(params = "page=spamReport")
    public String showSpamReport(PortletRequest request, PortletResponse response, Model model,
            @RequestParam long userId)
            throws SystemException, PortalException, UserProfileAuthorizationException, MemberNotFoundException {
        UserProfilePermissions permissions = new UserProfilePermissions(request);
        permissions.checkCanAdminSpamReports();

        userProfileController.populateUserWrapper(new UserProfileWrapper(userId, request), model);
        model.addAttribute("spamReportsWrapper", new SpamReportsWrapper(userId));

        return "showUserProfile";
    }

    @RenderMapping(params = "page=spamReports")
    public String showSpamReports(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, UserProfileAuthorizationException {
        UserProfilePermissions permissions = new UserProfilePermissions(request);
        permissions.checkCanAdminSpamReports();

        return "spamReports/spamReportList";
    }

    @RenderMapping(params = "action=deleteComment")
    public String deleteComment(
            PortletRequest request, PortletResponse response, Model model,
            @RequestParam long messageId)
            throws UserProfileAuthorizationException, SystemException, PortalException {

        UserProfilePermissions permissions = new UserProfilePermissions(request);
        permissions.checkCanAdminSpamReports();

        DiscussionMessage message = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);
        DiscussionMessageLocalServiceUtil.delete(message);

        for (SpamReport spamReport : SpamReportLocalServiceUtil.getByDiscussionMessageId(messageId)) {
            SpamReportLocalServiceUtil.deleteSpamReport(spamReport);
        }

        model.addAttribute("success", true);
        model.addAttribute("messageType", "deletedComment");

        return "spamReports/actionResults";
    }

    @RenderMapping(params = "action=deleteUser")
    public String deleteUser(
            PortletRequest request, PortletResponse response, Model model,
            @RequestParam long userId,
            @RequestParam(required = false, defaultValue = "false") boolean deleteMessages)
            throws UserProfileAuthorizationException, PortalException, SystemException, UnsupportedEncodingException {

        UserProfilePermissions permissions = new UserProfilePermissions(request);
        permissions.checkCanAdminSpamReports();

        List<Long> recipients = new ArrayList<>();
        recipients.add(userId);
        try {
            String content =
                    "This account has been terminated due to violation of the Climate CoLab Community Philosophy & Policy.<br/>"
                            +
                            "Link: <a href=\"http://climatecolab.org/resources/-/wiki/Main/Community+philosophy+and+policies\">"
                            +
                            "http://climatecolab.org/resources/-/wiki/Main/Community+philosophy+and+policies</a>";
            MessageUtil.sendMessage("Message from Climate CoLab Administrator", content,
                    permissions.getCurrentUser().getUserId(), permissions.getCurrentUser().getUserId(), recipients);
        } catch (MailEngineException | AddressException e) {
            throw new SystemException("Failed to send message to user", e);
        }

        UserLocalServiceUtil
                .updateStatus(userId, WorkflowConstants.STATUS_INACTIVE, ServiceContextFactory.getInstance(request));

        if (deleteMessages) {
            List<DiscussionMessage> messages = DiscussionMessageLocalServiceUtil.getByAuthorId(userId);

            for (DiscussionMessage message : messages) {
                DiscussionMessageLocalServiceUtil.delete(message);
            }

            for (SpamReport spamReport : SpamReportLocalServiceUtil.getBySpamUserId(userId)) {
                SpamReportLocalServiceUtil.deleteSpamReport(spamReport);
            }
        }

        model.addAttribute("success", true);
        model.addAttribute("messageType", "deletedUser");

        return "spamReports/actionResults";
    }
}

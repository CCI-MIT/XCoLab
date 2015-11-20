package org.xcolab.portlets.userprofile.view;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.SpamReport;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.SpamReportLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.mail.MailEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.portlets.userprofile.wrappers.SpamReportsWrapper;
import org.xcolab.portlets.userprofile.wrappers.UserProfileWrapper;
import org.xcolab.utils.judging.EmailTemplateWrapper;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johannes on 11/19/15.
 */
@Controller
@RequestMapping("view")
public class SpamReportController {

    @Autowired
    private UserProfileController userProfileController;

    @RequestMapping(params = "page=spamReport")
    public String showSpamReport(PortletRequest request, PortletResponse response, Model model,
                                 @RequestParam(required = true) String userId) throws SystemException, PortalException {
        userProfileController.populateUserWrapper(new UserProfileWrapper(userId, request), model);
        model.addAttribute("spamReportsWrapper", new SpamReportsWrapper(Long.parseLong(userId)));
        return "showUserProfile";
    }

    @RequestMapping(params = "page=spamReports")
    public String showSpamReports(PortletRequest request, PortletResponse response, Model model) {
        return "spamReports/spamReportList";
    }

    @RequestMapping(params = "action=deleteComment")
    public void deleteComment(
            PortletRequest request, PortletResponse response,
            @RequestParam long messageId)
            throws IOException, PortalException, SystemException {

        //TODO: check permissions
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        DiscussionMessage message = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);
        DiscussionMessageLocalServiceUtil.delete(message);

        for (SpamReport spamReport : SpamReportLocalServiceUtil.getByDiscussionMessageId(messageId)) {
            SpamReportLocalServiceUtil.deleteSpamReport(spamReport);
        }

//        response.sendRedirect("/web/guest/member/-/member/spamReport/"+message.getAuthorId());
//        return "";
    }

    @RequestMapping(params = "action=deleteUser")
    public void deleteUser(
            PortletRequest request, PortletResponse response,
            @RequestParam long userId,
            @RequestParam(required = false, defaultValue = "false") boolean deleteMessages)
            throws IOException, PortalException, SystemException {

        //TODO: check permissions
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        List<Long> recipients = new ArrayList<>();
        recipients.add(userId);
        try {
            String content = "This account has been terminated due to violation of the Climate CoLab Community Philosophy & Policy.<br/>" +
                    "Link: <a href=\"http://climatecolab.org/resources/-/wiki/Main/Community+philosophy+and+policies\">" +
                    "http://climatecolab.org/resources/-/wiki/Main/Community+philosophy+and+policies</a>";
            MessageUtil.sendMessage("Message from Climate CoLab Administrator", content,
                    themeDisplay.getUserId(), themeDisplay.getUserId(), recipients, request);
        } catch (MailEngineException | AddressException e) {
            throw new SystemException("Failed to send message to user", e);
        }

        UserLocalServiceUtil.updateStatus(userId, WorkflowConstants.STATUS_INACTIVE, ServiceContextFactory.getInstance(request));

        if (deleteMessages) {
            List<DiscussionMessage> messages = DiscussionMessageLocalServiceUtil.getByAuthorId(userId);

            for (DiscussionMessage message : messages) {
                DiscussionMessageLocalServiceUtil.delete(message);
            }

            for (SpamReport spamReport : SpamReportLocalServiceUtil.getBySpamUserId(userId)) {
                SpamReportLocalServiceUtil.deleteSpamReport(spamReport);
            }
        }

//        response.sendRedirect("/web/guest/member/-/member/spamReport/"+userId);
    }
}

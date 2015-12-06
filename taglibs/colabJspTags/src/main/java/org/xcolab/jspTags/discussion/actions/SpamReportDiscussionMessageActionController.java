package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.SpamReport;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.SpamReportLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionsException;
import org.xcolab.jspTags.discussion.wrappers.DiscussionMessageWrapper;
import org.xcolab.mail.EmailToAdminDispatcher;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

/**
 * Created by johannes on 11/19/15.
 */
@Controller
@RequestMapping("view")
public class SpamReportDiscussionMessageActionController extends BaseDiscussionsActionController {

    @RequestMapping(params = "action=reportSpam")
    public void reportSpam(ActionRequest request, ActionResponse response,
                             @RequestParam long discussionId, @RequestParam long messageId )
            throws IOException, PortalException, SystemException, DiscussionsException {

        checkPermissions(request, "User not allowed to report message as spam", discussionId, 0L);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);
        DiscussionMessage message = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);
        DiscussionPermissions permissions = new DiscussionPermissions(request, dcg);

        SpamReportLocalServiceUtil.create(messageId, message.getAuthorId(), themeDisplay.getUserId(), permissions.getCanAdmin());
        new EmailToAdminDispatcher("New spam report on Climate CoLab",
                String.format("User %s reported a <a href=\"%s/web/guest/member/-/member/spamReport/%d\">new spam message</a>",
                        themeDisplay.getUser().getScreenName(), PortalUtil.getPortalURL(request), message.getAuthorId()),
                EmailToAdminDispatcher.VERBOSITY_DEBUG).sendMessage();

        redirectToReferer(request, response);
    }

    @RequestMapping(params = "action=removeSpamReport")
    public void removeSpamReport(ActionRequest request, ActionResponse response,
                             @RequestParam long discussionId, @RequestParam long messageId )
            throws IOException, PortalException, SystemException, DiscussionsException {

        checkPermissions(request, "User not allowed to admin spam reports", discussionId, 1L);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);
        DiscussionMessage message = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);
        DiscussionPermissions permissions = new DiscussionPermissions(request, dcg);

        if (!permissions.getCanRemoveSpamReport(new DiscussionMessageWrapper(message))) {
            throw new DiscussionsException(String.format("User %d is not allowed to remove spam reports for message %d by user %d",
                    themeDisplay.getUserId(), messageId, message.getAuthorId()));
        }

        for (SpamReport spamReport : SpamReportLocalServiceUtil.getByDiscussionMessageId(messageId)) {
            SpamReportLocalServiceUtil.deleteSpamReport(spamReport);
        }

        redirectToReferer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId) {
        if (additionalId == 0L) {
            return permissions.getCanReportSpam();
        }
        return permissions.getCanAdminSpamReports();
    }
}

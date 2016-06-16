package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.model.SpamReport;
import com.ext.portlet.service.SpamReportLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.jspTags.discussion.exceptions.DiscussionException;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class SpamReportDiscussionMessageActionController extends BaseDiscussionsActionController {

    @RequestMapping(params = "action=reportSpam")
    public void reportSpam(ActionRequest request, ActionResponse response,
            @RequestParam long commentId )
            throws DiscussionAuthorizationException, SystemException,
            CommentNotFoundException, IOException {

        checkPermissions(request, "User not allowed to report message as spam", 0L);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Comment comment = CommentClient.getComment(commentId);
        DiscussionPermissions permissions = new DiscussionPermissions(request);

        SpamReportLocalServiceUtil.create(commentId,
                comment.getAuthorId(), themeDisplay.getUserId(), permissions.getCanAdminAll());

        redirectToReferrer(request, response);
    }

    @RequestMapping(params = "action=removeSpamReport")
    public void removeSpamReport(ActionRequest request, ActionResponse response,
            @RequestParam long commentId )
            throws IOException, DiscussionException, SystemException, CommentNotFoundException {

        checkPermissions(request, "User not allowed to admin spam reports", 1L);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Comment comment = CommentClient.getComment(commentId);
        DiscussionPermissions permissions = new DiscussionPermissions(request);

        if (!permissions.getCanRemoveSpamReport(comment)) {
            throw new DiscussionException(String.format("User %d is not allowed to remove spam reports for message %d by user %d",
                    themeDisplay.getUserId(), commentId, comment.getAuthorId()));
        }

        for (SpamReport spamReport : SpamReportLocalServiceUtil.getByDiscussionMessageId(commentId)) {
            SpamReportLocalServiceUtil.deleteSpamReport(spamReport);
        }

        redirectToReferrer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId) {
        if (additionalId == 0L) {
            return permissions.getCanReport();
        }
        return permissions.getCanAdminSpamReports();
    }
}

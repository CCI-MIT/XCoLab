package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions
        .DiscussionAuthorizationException;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DeleteDiscussionMessageFlagActionController extends BaseDiscussionsActionController {


   @GetMapping("/discussions/deleteDiscussionMessageFlag")
    public void handleAction(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam long commentId,
            @RequestParam(value = "contestId", required = false) String contestId)
            throws IOException, DiscussionAuthorizationException {

        checkPermissions(request, "User isn't allowed to delete message", 0L);

        final CommentClient commentClient;

        if (contestId != null && !contestId.equals("")) {
            Long contestIdLong = Long.parseLong(contestId);
            Contest contest = null;
            try {
                contest = ContestClientUtil.getContest(contestIdLong);
            } catch (ContestNotFoundException ignored) {

            }
            if (contest != null && contest.getIsSharedContestInForeignColab()) {
                RestService commentsService = new RefreshingRestService(CoLabService.COMMENT,
                        ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE
                );

                commentClient = CommentClient.fromService(commentsService);
            } else {
                commentClient = CommentClientUtil.getClient();
            }
        }else {
            commentClient = CommentClientUtil.getClient();
        }

        commentClient.deleteComment(commentId);
        redirectToReferrer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId) {
        return permissions.getCanAdminMessages();
    }
}

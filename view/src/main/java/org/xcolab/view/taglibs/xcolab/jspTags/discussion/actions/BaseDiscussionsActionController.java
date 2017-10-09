package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseDiscussionsActionController {

    public void checkPermissions(HttpServletRequest request, String accessDeniedMessage,
            long additionalId) throws DiscussionAuthorizationException {

        DiscussionPermissions permissions = new DiscussionPermissions(request);

        try {
            if (!isUserAllowed(permissions, additionalId)) {
                throw new DiscussionAuthorizationException(accessDeniedMessage);
            }
        } catch (CommentNotFoundException e) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
    }

    protected CommentClient getCommentClient(Long contestId) {
        CommentClient commentClient;
        if (contestId != null) {
            Contest contest = null;
            try {
                contest = ContestClientUtil.getContest(contestId);
            } catch (ContestNotFoundException ignored) {

            }
            if (contest != null && contest.getIsSharedContestInForeignColab()) {
                RestService commentsService = new RefreshingRestService(CoLabService.COMMENT,
                        ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE);

                commentClient = CommentClient.fromService(commentsService);
            } else {
                commentClient = CommentClientUtil.getClient();
            }
        } else {
            commentClient = CommentClientUtil.getClient();
        }
        return commentClient;
    }

    public abstract boolean isUserAllowed(DiscussionPermissions permissions, long additionalId)
            throws CommentNotFoundException;
}

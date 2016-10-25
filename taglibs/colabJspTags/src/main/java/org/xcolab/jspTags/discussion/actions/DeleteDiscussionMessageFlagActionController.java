package org.xcolab.jspTags.discussion.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class DeleteDiscussionMessageFlagActionController extends BaseDiscussionsActionController {

    @RequestMapping(params = "action=deleteDiscussionMessageFlag")
    public void handleAction(
            ActionRequest request, ActionResponse response,
            @RequestParam long commentId)
            throws IOException, DiscussionAuthorizationException {

        checkPermissions(request, "User isn't allowed to delete message", 0L);
        CommentClientUtil.deleteComment(commentId);
        redirectToReferrer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId) {
        return permissions.getCanAdminMessages();
    }
}

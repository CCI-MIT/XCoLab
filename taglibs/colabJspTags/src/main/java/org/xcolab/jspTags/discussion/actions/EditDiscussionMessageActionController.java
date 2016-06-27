package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.NoSuchDiscussionMessageException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.util.HtmlUtil;
import org.xcolab.utils.LinkUtils;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class EditDiscussionMessageActionController extends BaseDiscussionsActionController {

    @RequestMapping(params = "action=editComment")
    public void handleAction(ActionRequest request, ActionResponse response,
            @RequestParam long commentId,
            @RequestParam("comment") String content)
            throws IOException, SystemException, DiscussionAuthorizationException,
            NoSuchDiscussionMessageException, CommentNotFoundException {

        checkPermissions(request, "User isn't allowed to edit message", commentId);
        Comment comment = CommentClient.getComment(commentId);
        comment.setContent(HtmlUtil.cleanSome(content, LinkUtils.getBaseUri(request)));
        CommentClient.updateComment(comment);

        redirectToReferrer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId)
            throws CommentNotFoundException {
        final Comment comment = CommentClient.getComment(additionalId);
        return permissions.getCanAdminMessage(comment);
    }
}

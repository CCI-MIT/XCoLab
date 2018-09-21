package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DeleteDiscussionMessageActionController extends BaseDiscussionsActionController {

    @PostMapping("/discussions/deleteDiscussionMessageFlag")
    public void deleteMessage(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long commentId,
            @RequestParam(value = "contestId", required = false) Long contestId)
            throws IOException, CommentNotFoundException {

        final CommentClient commentClient = CommentClientUtil.getClient();
        Comment comment = commentClient.getComment(commentId);

        DiscussionPermissions discussionPermissions = getDiscussionPermissions(request,
                comment.getThread());

        if (!discussionPermissions.getCanAdminMessage(comment)) {
            AlertMessage.danger("You are not allowed to delete this message.").flash(request);
            String redirectUrl = request.getHeader(HttpHeaders.REFERER);
            response.sendRedirect(redirectUrl);
            return;
        }

        commentClient.deleteComment(commentId);
        String redirectUrl = request.getHeader(HttpHeaders.REFERER);
        response.sendRedirect(redirectUrl);
    }
}

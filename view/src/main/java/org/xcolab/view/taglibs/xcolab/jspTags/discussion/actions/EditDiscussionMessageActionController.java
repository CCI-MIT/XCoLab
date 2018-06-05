package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions
        .DiscussionAuthorizationException;
import org.xcolab.commons.servlet.flash.AlertMessage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class EditDiscussionMessageActionController extends BaseDiscussionsActionController {

    @PostMapping("/discussions/editComment")
    public void handleAction(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long commentId, @RequestParam("comment") String content,
            @RequestParam(value = "contestId", required = false) Long contestId)
            throws IOException, DiscussionAuthorizationException, CommentNotFoundException {

        try {
            checkPermissions(request, "User isn't allowed to edit message", commentId);
        } catch (DiscussionAuthorizationException e) {
            AlertMessage.danger("You are not allowed to edit this message.").flash(request);
            String redirectUrl = request.getHeader(HttpHeaders.REFERER);
            response.sendRedirect(redirectUrl);
            return;
        }

        final CommentClient commentClient;
        if (contestId != null) {
            commentClient = getCommentClient(contestId);
        } else {
            commentClient = CommentClientUtil.getClient();
        }

        Comment comment = commentClient.getComment(commentId);

        final String baseUri = PlatformAttributeKey.COLAB_URL.get();
        comment.setContent(HtmlUtil.cleanSome(content, baseUri));
        commentClient.updateComment(comment);

        String redirectUrl = request.getHeader(HttpHeaders.REFERER);
        response.sendRedirect(redirectUrl);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId)
            throws CommentNotFoundException {
        final Comment comment = CommentClientUtil.getComment(additionalId);
        return permissions.getCanAdminMessage(comment);
    }
}

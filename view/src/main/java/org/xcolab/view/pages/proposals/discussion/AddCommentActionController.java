package org.xcolab.view.pages.proposals.discussion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions
        .AddDiscussionMessageActionController;

import javax.servlet.http.HttpServletRequest;

//-- @Controller
//--  @RequestMapping("view")
public class AddCommentActionController extends AddDiscussionMessageActionController {

    @Override
    public void updateAnalyticsAndActivities(CommentThread thread, Comment comment, long userId, HttpServletRequest request) {
        super.updateAnalyticsAndActivities(thread, comment, userId, request);
    }
}

package org.xcolab.portlets.proposals.discussion;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.jspTags.discussion.actions.AddDiscussionMessageActionController;

import javax.portlet.ActionRequest;

@Controller
@RequestMapping("view")
public class AddCommentActionController extends AddDiscussionMessageActionController {
    private static final String DEFAULT_GROUP_NAME = "Guest";

    @Override
    public void updateAnalyticsAndActivities(CommentThread thread, Comment comment, long userId, ActionRequest request)
            throws SystemException, PortalException {
        super.updateAnalyticsAndActivities(thread, comment, userId, request);

    }
}

package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.utils.HtmlUtil;
import org.xcolab.utils.LinkUtils;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("view")
public class EditDiscussionMessageActionController extends BaseDiscussionsActionController  {

    @RequestMapping(params = "action=editComment")
    public void handleAction(ActionRequest request, ActionResponse response,
                @RequestParam long discussionId,
                @RequestParam("messageId") long messageId,
                @RequestParam("comment") String comment)
            throws IOException, PortalException, SystemException, DiscussionAuthorizationException {

        checkPermissions(request, "User isn't allowed to edit message", messageId);
        DiscussionMessage m = DiscussionMessageLocalServiceUtil.getMessageByMessageId(messageId);
        m.setBody(HtmlUtil.cleanSome(comment, LinkUtils.getBaseUri(request)));
        DiscussionMessageLocalServiceUtil.updateDiscussionMessage(m);

        redirectToReferrer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId)
            throws SystemException, NoSuchDiscussionMessageException, CommentNotFoundException {
        final Comment comment = CommentClient.getComment(additionalId);
        return permissions.getCanAdminMessage(comment);
    }
}

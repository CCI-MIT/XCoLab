package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.jspTags.discussion.wrappers.DiscussionMessageWrapper;
import org.xcolab.utils.HtmlUtil;
import org.xcolab.utils.LinkUtils;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class EditDiscussionMessageActionController extends BaseDiscussionsActionController  {

    @RequestMapping(params = "action=editComment")
    public void handleAction(ActionRequest request, ActionResponse response,
                @RequestParam long discussionId,
                @RequestParam("messageId") long messageId,
                @RequestParam("comment") String comment)
            throws IOException, PortalException, SystemException, DiscussionAuthorizationException {

        checkPermissions(request, "User isn't allowed to edit message", discussionId, messageId);
        DiscussionMessage m = DiscussionMessageLocalServiceUtil.getMessageByMessageId(messageId);
        m.setBody(HtmlUtil.cleanSome(comment, LinkUtils.getBaseUri(request)));
        DiscussionMessageLocalServiceUtil.updateDiscussionMessage(m);

        redirectToReferrer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId)
            throws SystemException, NoSuchDiscussionMessageException {
        final DiscussionMessage message = DiscussionMessageLocalServiceUtil.getMessageByMessageId(additionalId);
        final DiscussionMessageWrapper messageWrapper = new DiscussionMessageWrapper(message);
        return permissions.getCanAdminMessage(messageWrapper);
    }
}

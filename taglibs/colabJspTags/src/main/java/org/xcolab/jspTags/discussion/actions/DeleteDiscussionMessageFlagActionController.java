package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionsException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class DeleteDiscussionMessageFlagActionController extends BaseDiscussionsActionController {

        @RequestMapping(params = "action=deleteDiscussionMessageFlag")
        public void handleAction(
                ActionRequest request, ActionResponse response,
                @RequestParam long discussionId, @RequestParam long messageId)
                throws IOException, PortalException, SystemException, DiscussionsException {
            
            checkPermissions(request, "User isn't allowed to delete message", discussionId, 0L);
            DiscussionMessage message = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);
            DiscussionMessageLocalServiceUtil.delete(message);
            redirectToReferer(request, response);
        }

        @Override
        public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId) {
            return permissions.getCanAdminMessages();
        }
        
}

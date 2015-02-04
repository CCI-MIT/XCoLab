package org.xcolab.jsp.tags.discussion.actions;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.jsp.tags.discussion.DiscussionPermissions;
import org.xcolab.jsp.tags.discussion.exceptions.DiscussionsException;

import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("view")
public class DeleteDiscussionMessageFlagActionController extends BaseDiscussionsActionController {

        @RequestMapping(params = "action=deleteDiscussionMessageFlag")
        public void handleAction(ActionRequest request, ActionResponse response, 
                @RequestParam long discussionId, @RequestParam long messageId) 
                throws IOException, PortalException, SystemException, DiscussionsException {
            
            checkPermissions(request, "User isn't allowed to delete message", discussionId);
            DiscussionMessage message = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);
            DiscussionMessageLocalServiceUtil.delete(message);
            redirectToReferer(request, response);
        }

        @Override
        public boolean isUserAllowed(DiscussionPermissions permissions) {
            return permissions.getCanAdminMessages();
        }
        
}

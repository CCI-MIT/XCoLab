package org.xcolab.jsp.tags.discussion.actions;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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
public class ToggleDiscussionMessageFlagActionController extends BaseDiscussionsActionController {

        @RequestMapping(params = "action=toggleDiscussionMessageFlag")
        public void handleAction(ActionRequest request, ActionResponse response, 
                @RequestParam long discussionId, @RequestParam long messageId, @RequestParam String flag) 
                throws IOException, PortalException, SystemException, DiscussionsException {
            
            checkPermissions(request, "User isn't allowed to modify message flags", discussionId);
            
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            DiscussionMessage message = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);
            if (DiscussionMessageLocalServiceUtil.hasFlag(messageId, flag)) {
                DiscussionMessageLocalServiceUtil.removeFlag(message, flag);
            }
            else {
                DiscussionMessageLocalServiceUtil.addFlag(message, flag, null, themeDisplay.getUser());
            }
            
            redirectToReferer(request, response);
        }

        @Override
        public boolean isUserAllowed(DiscussionPermissions permissions) {
            return permissions.getCanAdminMessages();
        }
        
}

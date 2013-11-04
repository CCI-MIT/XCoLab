package org.xcolab.jsp.tags.discussion.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.jsp.tags.discussion.DiscussionPermissions;
import org.xcolab.jsp.tags.discussion.exceptions.DiscussionsException;
import org.xcolab.jsp.tags.discussion.wrappers.NewMessageWrapper;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

@Controller
@RequestMapping("view")
public class AddDiscussionMessageActionController extends BaseDiscussionsActionController {

    @RequestMapping(params = "action=addDiscussionMessage")
    public void handleAction(ActionRequest request, ActionResponse response, NewMessageWrapper newMessage)
            throws IOException, PortalException, SystemException, DiscussionsException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(newMessage.getDiscussionId());

        checkPermissions(request, "User isn't allowed to add comment", newMessage.getDiscussionId());

        DiscussionCategoryGroupLocalServiceUtil.addComment(dcg, newMessage.getTitle(), newMessage.getDescription(), themeDisplay.getUser());

        redirectToReferer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions) {
        return permissions.getCanAddComment();
    }

}

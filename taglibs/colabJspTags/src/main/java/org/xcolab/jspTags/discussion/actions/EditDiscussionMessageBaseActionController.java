package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.jspTags.discussion.DiscussionPermission;
import org.xcolab.jspTags.discussion.exceptions.DiscussionsException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 05/11/13
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("view")
public class EditDiscussionMessageBaseActionController extends DiscussionsBaseActionController {

    @RequestMapping(params = "action=editComment")
    public void handleAction(ActionRequest request, ActionResponse response, @RequestParam("messageId") long messageId, @RequestParam("comment") String comment)
            throws IOException, PortalException, SystemException, DiscussionsException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if (!themeDisplay.getPermissionChecker().isOmniadmin())
            throw new DiscussionsException("User isn't allowed to edit comment");

        DiscussionMessage m = DiscussionMessageLocalServiceUtil.getMessageByMessageId(messageId);
        m.setBody(comment);
        DiscussionMessageLocalServiceUtil.updateDiscussionMessage(m);

        redirectToReferer(request, response);
    }

    @Override
    public boolean isUserAllowed(DiscussionPermission permissions) {
        return permissions.getCanAdminMessages();
    }

}

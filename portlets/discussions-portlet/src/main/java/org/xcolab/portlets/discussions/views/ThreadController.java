package org.xcolab.portlets.discussions.views;

import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionsException;
import org.xcolab.jspTags.discussion.wrappers.CategoryWrapper;
import org.xcolab.jspTags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jspTags.discussion.wrappers.ThreadWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by johannes on 12/1/15.
 */
@Controller
@RequestMapping("view")
public class ThreadController extends BaseDiscussionController {

    @RenderMapping(params = "action=showThread")
    public String showThread(PortletRequest request, PortletResponse response, Model model,
                             @RequestParam long threadId)
            throws SystemException, PortalException, DiscussionsException {

        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);

        DiscussionPermissions permission = new DiscussionPermissions(request, categoryGroupWrapper.getWrapped());

        if (!permission.getCanAddComment()) {
            throw new DiscussionsException("User does not have the necessary permissions to create a thread");
        }

        DiscussionMessage thread = DiscussionMessageLocalServiceUtil.fetchDiscussionMessage(threadId);

        if (thread.getCategoryGroupId() != categoryGroupWrapper.getId()) {
            throw new DiscussionsException("Access Denied: User tried to access thread outside of portlet's" +
                    "configured discussionCategoryGroupId "+ categoryGroupWrapper.getId());
        }

        model.addAttribute("thread", new ThreadWrapper(thread));

        return "thread";
    }

    @RenderMapping(params = "action=createThread")
    public String createThread(PortletRequest request, PortletResponse response, Model model)
            throws SystemException, PortalException, DiscussionsException {

        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);
        DiscussionPermissions permission = new DiscussionPermissions(request, categoryGroupWrapper.getWrapped());

        if (!permission.getCanAddComment()) {
            throw new DiscussionsException("User does not have the necessary permissions to create a thread");
        }


        List<CategoryWrapper> categories = categoryGroupWrapper.getCategories();

        model.addAttribute("categories", categories);

        return "thread_add";
    }

    @ActionMapping(params = "action=createThread")
    public void createThreadAction(ActionRequest request, ActionResponse response, Model model,
                                     @RequestParam long categoryId, @RequestParam String title,
                                     @RequestParam String body)
            throws SystemException, PortalException, IOException, DiscussionsException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);

        DiscussionPermissions permission = new DiscussionPermissions(request, categoryGroupWrapper.getWrapped());

        if (!permission.getCanAddComment()) {
            throw new DiscussionsException("User does not have the necessary permissions to create a thread");
        }

        final DiscussionMessage thread = DiscussionMessageLocalServiceUtil.addThread(categoryGroupWrapper.getId(),
                categoryId, title, body, themeDisplay.getUser());

        response.sendRedirect(new ThreadWrapper(thread).getLinkUrl());
    }
}

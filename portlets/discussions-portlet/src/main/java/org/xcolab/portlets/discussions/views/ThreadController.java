package org.xcolab.portlets.discussions.views;

import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
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
import org.xcolab.jspTags.discussion.wrappers.CategoryWrapper;
import org.xcolab.jspTags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jspTags.discussion.wrappers.ThreadWrapper;
import org.xcolab.portlets.discussions.DiscussionPreferences;

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
public class ThreadController {

    @RenderMapping(params = "action=showThread")
    public String showThread(PortletRequest request, PortletResponse response, Model model,
                             @RequestParam long threadId)
            throws SystemException, PortalException {

        DiscussionMessage thread = DiscussionMessageLocalServiceUtil.fetchDiscussionMessage(threadId);
        model.addAttribute("thread", new ThreadWrapper(thread));

        return "thread";
    }

    @RenderMapping(params = "action=createThread")
    public String createThread(PortletRequest request, PortletResponse response, Model model)
            throws SystemException, PortalException {

        DiscussionPreferences preferences = new DiscussionPreferences(request);
        DiscussionCategoryGroupWrapper categoryGroupWrapper = new DiscussionCategoryGroupWrapper(
                DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(preferences.getCategoryGroupId()));

        List<CategoryWrapper> categories = categoryGroupWrapper.getCategories();

        model.addAttribute("categories", categories);

        return "thread_add";
    }

    @ActionMapping(params = "action=createThread")
    public void createThreadAction(ActionRequest request, ActionResponse response, Model model,
                                     @RequestParam long categoryId, @RequestParam String title,
                                     @RequestParam String body)
            throws SystemException, PortalException, IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionPreferences preferences = new DiscussionPreferences(request);

        DiscussionCategoryGroupWrapper categoryGroupWrapper = new DiscussionCategoryGroupWrapper(
                DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(preferences.getCategoryGroupId()));

        final DiscussionMessage thread = DiscussionMessageLocalServiceUtil.addThread(categoryGroupWrapper.getId(),
                categoryId, title, body, themeDisplay.getUser());

        response.sendRedirect(new ThreadWrapper(thread).getLinkUrl());
    }
}

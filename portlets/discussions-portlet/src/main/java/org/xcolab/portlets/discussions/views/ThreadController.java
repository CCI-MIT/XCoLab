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
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
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

    public static final String CREATE_THREAD_URL = "/web/guest/discussion/-/discussion/threads/create";

    @RenderMapping(params = "action=showThread")
    public String showThread(PortletRequest request, PortletResponse response, Model model,
                             @RequestParam long threadId)
            throws SystemException, PortalException, DiscussionAuthorizationException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);
        DiscussionMessage thread = DiscussionMessageLocalServiceUtil.getDiscussionMessage(threadId);

        checkCanView(request,
                String.format("Thread %d has discussionCategoryGroupId %d, which does not match the portlet's configured id %d",
                        threadId, thread.getCategoryGroupId(), categoryGroupWrapper.getId()),
                categoryGroupWrapper.getWrapped(), threadId);

        final ThreadWrapper threadWrapper = new ThreadWrapper(thread);
        model.addAttribute("thread", threadWrapper);
        model.addAttribute("isSubscribed", threadWrapper.isSubscribed(themeDisplay.getUserId()));

        return "thread";
    }

    @RenderMapping(params = "action=createThread")
    public String createThread(PortletRequest request, PortletResponse response, Model model)
            throws SystemException, PortalException, DiscussionAuthorizationException {

        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);
        checkCanEdit(request, "User does not have the necessary permissions to create a thread ",
                categoryGroupWrapper.getWrapped(), 0L);

        List<CategoryWrapper> categories = categoryGroupWrapper.getCategories();

        model.addAttribute("categories", categories);

        return "thread_add";
    }

    @ActionMapping(params = "action=createThread")
    public void createThreadAction(ActionRequest request, ActionResponse response,
                                     @RequestParam long categoryId, @RequestParam String title,
                                     @RequestParam String body)
            throws SystemException, PortalException, IOException, DiscussionAuthorizationException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);

        checkCanEdit(request, "User does not have the necessary permissions to create a thread ",
                categoryGroupWrapper.getWrapped(), 0L);

        if (title.length() > 0 && body.length() > 0) {
            final DiscussionMessage thread = DiscussionMessageLocalServiceUtil.addThread(categoryGroupWrapper.getId(),
                    categoryId, title, body, themeDisplay.getUser());

            response.sendRedirect(new ThreadWrapper(thread).getLinkUrl());
        } else {
            response.sendRedirect(CREATE_THREAD_URL);
        }
    }

    @ActionMapping(params = "action=subscribeThread")
    public void subscribeThread(ActionRequest request, ActionResponse response, @RequestParam long threadId)
            throws DiscussionAuthorizationException, PortalException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);
        checkCanView(request, "You do not have permissions to view this category", categoryGroupWrapper.getWrapped(), 0L);

        ThreadWrapper threadWrapper = new ThreadWrapper(threadId);

        if (!themeDisplay.getUser().isDefaultUser()) {
            if (threadId > 0) {
                DiscussionMessageLocalServiceUtil.subscribe(themeDisplay.getUserId(),
                        categoryGroupWrapper.getId(), threadWrapper.getCategory().getId(), threadId);
            }
        }
    }

    @ActionMapping(params = "action=unsubscribeThread")
    public void unsubscribeThread(ActionRequest request, ActionResponse response, @RequestParam long threadId)
            throws DiscussionAuthorizationException, PortalException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);
        checkCanView(request, "You do not have permissions to view this category", categoryGroupWrapper.getWrapped(), 0L);

        ThreadWrapper threadWrapper = new ThreadWrapper(threadId);

        if (!themeDisplay.getUser().isDefaultUser()) {
            if (threadId > 0) {
                DiscussionMessageLocalServiceUtil.unsubscribe(themeDisplay.getUserId(),
                        categoryGroupWrapper.getId(), threadWrapper.getCategory().getId(), threadId);
            }
        }
    }

    @Override
    public boolean getCanView(DiscussionPermissions permissions, long additionalId) throws SystemException {
        return permissions.getCanViewThread(additionalId);
    }

    @Override
    public boolean getCanEdit(DiscussionPermissions permissions, long additionalId) throws SystemException {
        return permissions.getCanAddComment();
    }
}

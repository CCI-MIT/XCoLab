package org.xcolab.portlets.discussions.views;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;


import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.util.html.HtmlUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ThreadController extends BaseDiscussionController {

    public static final String CREATE_THREAD_URL = "/web/guest/discussion/-/discussion/threads/create";

    @RenderMapping(params = "action=showThread")
    public String showThread(PortletRequest request, PortletResponse response, Model model,
                             @RequestParam long threadId)
            throws DiscussionAuthorizationException, ThreadNotFoundException {

        CategoryGroup categoryGroup = getCategoryGroup(request);
        CommentThread thread = CommentClient.getThread(threadId);

        checkCanView(request,
                String.format("Thread %d is not in the portlet's configured category group %d",
                        threadId, categoryGroup.getGroupId()),
                categoryGroup, threadId);

        model.addAttribute("thread", thread);
        model.addAttribute("isSubscribed", false); //threadWrapper.isSubscribed(themeDisplay.getUserId()));

        return "thread";
    }

    @RenderMapping(params = "action=createThread")
    public String createThread(PortletRequest request, PortletResponse response, Model model)
            throws DiscussionAuthorizationException {

        CategoryGroup categoryGroup = getCategoryGroup(request);
        checkCanEdit(request, "User does not have the necessary permissions to create a thread ",
                categoryGroup, 0L);

        List<Category> categories = categoryGroup.getCategories();

        model.addAttribute("categories", categories);

        return "thread_add";
    }

    @ActionMapping(params = "action=createThread")
    public void createThreadAction(ActionRequest request, ActionResponse response,
                                     @RequestParam long categoryId, @RequestParam String title,
                                     @RequestParam String body)
            throws IOException, DiscussionAuthorizationException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        CategoryGroup categoryGroup = getCategoryGroup(request);

        checkCanEdit(request, "User does not have the necessary permissions to create a thread ",
                categoryGroup, 0L);

        final long userId = themeDisplay.getUserId();

        if (!title.isEmpty() && !body.isEmpty()) {
            CommentThread thread = new CommentThread();
            thread.setCategoryId(categoryId);
            thread.setTitle(HtmlUtil.cleanAll(title));
            thread.setAuthorId(userId);
            thread.setIsQuiet(false);
            thread = CommentClient.createThread(thread);

            Comment comment = new Comment();
            comment.setThreadId(thread.getThreadId());
            comment.setContent(HtmlUtil.cleanSome(body, ""));
            comment.setAuthorId(userId);
            comment = CommentClient.createComment(comment);

            if( !thread.getIsQuiet()) {
                ActivityEntryHelper.createActivityEntry(userId, categoryId, (comment.getCommentId()+""),
                        ActivityProvidersType.DiscussionAddedActivityEntry.getType());
            }

            response.sendRedirect(thread.getLinkUrl());
        } else {
            response.sendRedirect(CREATE_THREAD_URL);
        }
    }

    @Override
    public boolean getCanView(DiscussionPermissions permissions, CategoryGroup categoryGroup, long additionalId) {
        try {
            CommentThread thread = CommentClient.getThread(additionalId);
            return thread.getCategory().getCategoryGroup().getGroupId()
                    .equals(categoryGroup.getGroupId());
        } catch (ThreadNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean getCanEdit(DiscussionPermissions permissions, CategoryGroup categoryGroup, long additionalId) {
        return permissions.getCanAddComment();
    }
}

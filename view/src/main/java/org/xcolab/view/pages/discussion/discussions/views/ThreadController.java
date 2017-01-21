package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions
        .DiscussionAuthorizationException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ThreadController extends BaseDiscussionController {

    public static final String CREATE_THREAD_URL = "/discussion/threads/create";



    @GetMapping({"/web/guest/discussion/thread/{threadId}","/discussion/thread/{threadId}"})
    public String showThread(HttpServletRequest request, HttpServletResponse response, Model model,
                             @PathVariable Long threadId)
            throws DiscussionAuthorizationException, ThreadNotFoundException {


        CategoryGroup categoryGroup = getCategoryGroup(request);
        CommentThread thread = ThreadClientUtil.getThread(threadId);

        checkCanView(request,
                String.format("Thread %d is not in the portlet's configured category group %d",
                        threadId, categoryGroup.getGroupId()),
                categoryGroup, threadId);

        model.addAttribute("thread", thread);
        model.addAttribute("isSubscribed", false); //threadWrapper.isSubscribed(themeDisplay.getUserId()));

        return "/discussion/thread";
    }


    @ModelAttribute("communityTopContentArticleId")
    public Long getCommunityTopContentArticleId(){
        return  COMMUNITY_TOP_CONTENT_ARTICLE_ID;
    }
    @GetMapping("/discussion/threads/create")
    public String createThread(HttpServletRequest request, HttpServletResponse response, Model model)
            throws DiscussionAuthorizationException {


        CategoryGroup categoryGroup = getCategoryGroup(request);
        checkCanEdit(request, "User does not have the necessary permissions to create a thread ",
                categoryGroup, 0L);

        List<Category> categories = categoryGroup.getCategories();

        model.addAttribute("categories", categories);

        return "/discussion/thread_add";
    }


    @PostMapping("/discussion/thread/createThread")
    public void createThreadAction(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam long categoryId, @RequestParam String title,
                                     @RequestParam String body)
            throws IOException, DiscussionAuthorizationException {

        CategoryGroup categoryGroup = getCategoryGroup(request);

        checkCanEdit(request, "User does not have the necessary permissions to create a thread ",
                categoryGroup, 0L);

        long memberId = MemberAuthUtil.getMemberId(request);

        if (!title.isEmpty() && !body.isEmpty()) {
            CommentThread thread = new CommentThread();
            thread.setCategoryId(categoryId);
            thread.setTitle(HtmlUtil.cleanAll(title));
            thread.setAuthorId(memberId);
            thread.setIsQuiet(false);
            thread = ThreadClientUtil.createThread(thread);

            Comment comment = new Comment();
            comment.setThreadId(thread.getThreadId());
            comment.setContent(HtmlUtil.cleanSome(body, ""));
            comment.setAuthorId(memberId);
            comment = CommentClientUtil.createComment(comment);

            if( !thread.getIsQuiet()) {
                ActivityEntryHelper.createActivityEntry(ActivitiesClientUtil.getClient(),memberId, categoryId, (comment.getCommentId()+""),
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
            CommentThread thread = ThreadClientUtil.getThread(additionalId);
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

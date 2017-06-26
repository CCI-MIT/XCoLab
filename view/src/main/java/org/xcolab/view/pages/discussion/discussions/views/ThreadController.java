package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.admin.enums.PlatformAttributeKey;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ThreadController extends BaseDiscussionController {

    @GetMapping("/discussion/thread/{threadId}")
    public String showThread(HttpServletRequest request, HttpServletResponse response, Model model,
                             @PathVariable Long threadId)
            throws DiscussionAuthorizationException, ThreadNotFoundException {

        CategoryGroup categoryGroup = getCategoryGroup(request);
        CommentThread thread = ThreadClientUtil.getThread(threadId);

        DiscussionPermissions permissions = new DiscussionPermissions(request);
        if (!getCanView(permissions, categoryGroup, threadId)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        // should not be reached
        checkCanView(request,
                String.format("Thread %d is not in the configured category group %d",
                        threadId, categoryGroup.getGroupId()),
                categoryGroup, threadId);

        model.addAttribute("thread", thread);
        model.addAttribute("isSubscribed", false); //threadWrapper.isSubscribed(themeDisplay.getUserId()));

        model.addAttribute("_activePageLink", "community");
        return "/discussion/thread";
    }

    @GetMapping("/discussion/threads/create")
    public String createThread(HttpServletRequest request, HttpServletResponse response, Model model)
            throws DiscussionAuthorizationException {


        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions permissions = new DiscussionPermissions(request);
        if (!getCanEdit(permissions, categoryGroup, 0L)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        // should not be reached
        checkCanEdit(request, "User does not have the necessary permissions to create a thread ",
                categoryGroup, 0L);

        List<Category> categories = categoryGroup.getCategories();

        model.addAttribute("categories", categories);

        model.addAttribute("_activePageLink", "community");
        return "/discussion/thread_add";
    }


    @PostMapping("/discussion/thread/create")
    public String createThreadAction(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam long categoryId, @RequestParam String title,
            @RequestParam String body)
            throws IOException, DiscussionAuthorizationException {

        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions permissions = new DiscussionPermissions(request);
        if (!getCanEdit(permissions, categoryGroup, 0L)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        // should not be reached
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
            final String baseUri = PlatformAttributeKey.PLATFORM_COLAB_URL.get();
            comment.setContent(HtmlUtil.cleanSome(body, baseUri));
            comment.setAuthorId(memberId);
            comment = CommentClientUtil.createComment(comment);

            if(!thread.getIsQuiet()) {
                ActivityEntryHelper.createActivityEntry(ActivitiesClientUtil.getClient(),memberId, categoryId, (comment.getCommentId()+""),
                        ActivityProvidersType.DiscussionAddedActivityEntry.getType());
            }

            return "redirect:" + thread.getLinkUrl();
        } else {
            return createThread(request, response, model);
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

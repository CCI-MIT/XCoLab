package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.util.activities.enums.DiscussionThreadActivityType;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ThreadController extends BaseDiscussionController {

    @GetMapping("/discussion/thread/{threadId}")
    public String showThread(HttpServletRequest request, HttpServletResponse response, Model model,
            Member member, @PathVariable Long threadId)
            throws DiscussionAuthorizationException, ThreadNotFoundException {

        CategoryGroup categoryGroup = getCategoryGroup(request);
        CommentThread thread = ThreadClientUtil.getThread(threadId);

        DiscussionPermissions permissions = new DiscussionPermissions(request);
        if (!getCanView(permissions, categoryGroup, threadId)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("thread", thread);
        model.addAttribute("isSubscribed", false); //threadWrapper.isSubscribed(themeDisplay.getUserId()));

        model.addAttribute("_activePageLink", "community");
        return "/discussion/thread";
    }

    @GetMapping("/discussion/threads/create")
    public String createThread(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member)
            throws DiscussionAuthorizationException {


        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions permissions = new DiscussionPermissions(request);
        if (!getCanEdit(permissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        List<Category> categories = categoryGroup.getCategories();

        model.addAttribute("categories", categories);

        model.addAttribute("_activePageLink", "community");
        return "/discussion/thread_add";
    }


    @PostMapping("/discussion/thread/create")
    public String createThreadAction(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @RequestParam long categoryId, @RequestParam String title,
            @RequestParam String body)
            throws DiscussionAuthorizationException {

        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions permissions = new DiscussionPermissions(request);
        if (!getCanEdit(permissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        long userId = MemberAuthUtil.getuserId(request);

        if (!title.isEmpty() && !body.isEmpty()) {
            CommentThread thread = new CommentThread();
            thread.setCategoryId(categoryId);
            thread.setTitle(HtmlUtil.cleanAll(title));
            thread.setAuthorUserId(userId);
            thread.setIsQuiet(false);
            thread = ThreadClientUtil.createThread(thread);

            Comment comment = new Comment();
            comment.setThreadId(thread.getId());
            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            comment.setContent(HtmlUtil.cleanSome(body, baseUri));
            comment.setAuthorUserId(userId);
            comment = CommentClientUtil.createComment(comment);

            if (!thread.getIsQuiet()) {
                final ActivitiesClient activityClient = ActivitiesClientUtil.getClient();
                activityClient.createActivityEntry(DiscussionThreadActivityType.CREATED, userId,
                        thread.getId());
            }

            return "redirect:" + thread.getLinkUrl();
        } else {
            return createThread(request, response, model, member);
        }
    }

    @Override
    public boolean getCanView(DiscussionPermissions permissions, CategoryGroup categoryGroup, long additionalId) {
        try {
            CommentThread thread = ThreadClientUtil.getThread(additionalId);
            return thread.getCategory().getCategoryGroup().getId()
                    .equals(categoryGroup.getId());
        } catch (ThreadNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean getCanEdit(DiscussionPermissions permissions, CategoryGroup categoryGroup, long additionalId) {
        return permissions.getCanAddComment();
    }
}

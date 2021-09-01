package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.comment.pojo.tables.pojos.Comment;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.servlet.flash.ErrorPage;
import org.xcolab.util.activities.enums.DiscussionThreadActivityType;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ThreadController extends BaseDiscussionController {

    @Autowired
    private IActivityClient activityClient;

    @Autowired
    private IThreadClient threadClient;

    @Autowired
    private ICommentClient commentClient;

    @Autowired
    private ICategoryClient categoryClient;

    @GetMapping("/discussion/thread/{threadId}")
    public String showThread(HttpServletRequest request, HttpServletResponse response, Model model,
            UserWrapper member, @PathVariable Long threadId)
            throws DiscussionAuthorizationException, ThreadNotFoundException {

        ICategoryGroup categoryGroup = getCategoryGroup(request);
        IThread thread = threadClient.getThread(threadId);

        if(thread.getDeletedAt()!=null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ErrorPage.error("Discussion not found")
                    .withTitle("Not found")
                    .flashAndReturnView(request);

        }
        DiscussionPermissions permissions = new DiscussionPermissions();
        if (!getCanView(permissions, categoryGroup, threadId)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("thread", thread);
        model.addAttribute("isSubscribed", false); //threadWrapper.isSubscribed(themeDisplay.getUserId()));

        model.addAttribute("_activePageLink", "community");

        model.addAttribute("recaptchaDataSiteKey",PlatformAttributeKey.GOOGLE_RECAPTCHA_SITE_KEY.get());
        model.addAttribute("isCommentCaptchaOn",PlatformAttributeKey.GOOGLE_RECAPTCHA_IS_ACTIVE_FOR_COMMENTS.get());

        return "/discussion/thread";
    }

    @GetMapping("/discussion/threads/create")
    public String createThread(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member)
            throws DiscussionAuthorizationException {


        ICategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions permissions = new DiscussionPermissions();
        if (!getCanEdit(permissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        List<ICategory> categories = categoryGroup.getCategories();

        model.addAttribute("categories", categories);

        model.addAttribute("_activePageLink", "community");
        return "/discussion/thread_add";
    }


    @PostMapping("/discussion/thread/create")
    public String createThreadAction(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @RequestParam long categoryId, @RequestParam String title,
            @RequestParam String body)
            throws DiscussionAuthorizationException {

        ICategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions permissions = new DiscussionPermissions();
        if (!getCanEdit(permissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        long userId = MemberAuthUtil.getUserId();

        if (!title.isEmpty() && !body.isEmpty()) {
            IThread thread = new org.xcolab.client.comment.pojo.tables.pojos.Thread();
            thread.setCategoryId(categoryId);
            thread.setTitle(HtmlUtil.cleanAll(title));
            thread.setAuthorUserId(userId);
            thread.setIsQuiet(false);
            thread = threadClient.createThread(thread);

            IComment comment = new Comment();
            comment.setThreadId(thread.getId());
            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            comment.setContent(HtmlUtil.cleanSome(body, baseUri));
            comment.setAuthorUserId(userId);
            comment = commentClient.createComment(comment);

            if (!thread.isIsQuiet()) {
                activityClient.createActivityEntry(DiscussionThreadActivityType.CREATED, userId,
                        thread.getId());
            }

            return "redirect:" + thread.getLinkUrl();
        } else {
            return createThread(request, response, model, member);
        }
    }

    @Override
    public boolean getCanView(DiscussionPermissions permissions, ICategoryGroup categoryGroup,
            long additionalId) {
        try {
            IThread thread = threadClient.getThread(additionalId);
            Long categoryGroupId =
                    categoryClient.getCategoryGroup(thread.getCategory().getGroupId()).getId();
            return categoryGroupId.equals(categoryGroup.getId());
        } catch (ThreadNotFoundException | CategoryGroupNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean getCanEdit(DiscussionPermissions permissions, ICategoryGroup categoryGroup, long additionalId) {
        return permissions.getCanAddComment();
    }
}

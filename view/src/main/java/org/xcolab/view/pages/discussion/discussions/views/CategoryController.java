package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import java.io.IOException;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CategoryController extends BaseDiscussionController {

    @GetMapping("/discussion")
    public String showCategories(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam(required = false) String sortColumn,
            @RequestParam(defaultValue = "false") boolean sortAscending) {
        model.addAttribute("_activePageLink", "community");
        long userId = MemberAuthUtil.getuserId(request);

        ThreadSortColumn threadSortColumn;
        try {
            threadSortColumn = ThreadSortColumn.from(sortColumn);
        } catch (ThreadSortColumn.NoSuchThreadSortColumnException e) {
            threadSortColumn = ThreadSortColumn.DATE;
        }

        CategoryGroup categoryGroup = getCategoryGroup(request);
        List<Category> categories = categoryGroup.getCategories();
        List<CommentThread> threads = categoryGroup.getThreads(threadSortColumn, sortAscending);

        model.addAttribute("categoryGroup", categoryGroup);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", threads);
        model.addAttribute("sortColumn", threadSortColumn);
        model.addAttribute("sortAscending", sortAscending);

        model.addAttribute("isSubscribed", ActivitiesClientUtil.isSubscribedToActivity(
                userId, ActivityCategory.DISCUSSION, categoryGroup.getId(), ""));

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        model.addAttribute("discussionPermissions", discussionPermissions);

        model.addAttribute("_activePageLink", "community");
        return "/discussion/category";
    }

    @GetMapping("/discussion/category/{categoryId}")
    public String showCategory(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long categoryId,
            @RequestParam(required = false) String sortColumn,
            @RequestParam boolean sortAscending)
            throws DiscussionAuthorizationException, CategoryNotFoundException {

        long userId = MemberAuthUtil.getuserId(request);

        ThreadSortColumn threadSortColumn;
        try {
            threadSortColumn = ThreadSortColumn.from(sortColumn);
        } catch (ThreadSortColumn.NoSuchThreadSortColumnException e) {
            threadSortColumn = ThreadSortColumn.DATE;
        }

        CategoryGroup categoryGroup = getCategoryGroup(request);

        List<Category> categories = categoryGroup.getCategories();
        Category currentCategory = CategoryClientUtil.getCategory(categoryId);

        model.addAttribute("categoryGroup", categoryGroup);
        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", currentCategory.getThreads(threadSortColumn, sortAscending));
        model.addAttribute("sortColumn", threadSortColumn.name());
        model.addAttribute("sortAscending", sortAscending);

        model.addAttribute("isSubscribed", ActivitiesClientUtil.isSubscribedToActivity(userId,
                ActivityCategory.DISCUSSION, currentCategory.getId()));


        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        model.addAttribute("discussionPermissions", discussionPermissions);

        model.addAttribute("_activePageLink", "community");
        return "/discussion/category";
    }

    // @ActionMapping(params = "action=sortCategory")
    @GetMapping("/discussion/sortCategory")
    public void sortCategory(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(required = false) Long categoryId, @RequestParam String sortColumn,
                             @RequestParam String currentSortColumn, @RequestParam boolean currentSortAscending)
            throws IOException, CategoryNotFoundException {

        final String baseUrl;
        if (categoryId != null && categoryId > 0) {
            Category category = CategoryClientUtil.getCategory(categoryId);
            baseUrl = category.getLinkUrl();
        } else {
            baseUrl = "/discussion/categories";
        }

        String sortAscendingString = !sortColumn.equalsIgnoreCase(currentSortColumn)
                || !currentSortAscending ? "" : "/descending";

        response.sendRedirect(baseUrl + "/sort/" + sortColumn + sortAscendingString);
    }

    // @RenderMapping(params = "action=createCategory")
    public String createCategory(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member, @RequestParam long categoryId)
            throws DiscussionAuthorizationException {

        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        if (!getCanEdit(discussionPermissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("_activePageLink", "community");
        return "/discussion/category_add";
    }

    // @ActionMapping(params = "action=createCategory")
    public String createCategoryAction(HttpServletRequest request, HttpServletResponse response,
            Member member, @RequestParam String title, @RequestParam String description)
            throws IOException, DiscussionAuthorizationException, OperationNotSupportedException {

        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        if (!getCanEdit(discussionPermissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        throw new OperationNotSupportedException("Not implemented");
    }


    @GetMapping("/discussion/subscribeCategory")
    public String subscribeCategory(HttpServletRequest request, HttpServletResponse response,
            Member member, @RequestParam long categoryId)
            throws DiscussionAuthorizationException, IOException {

        long userId = MemberAuthUtil.getuserId(request);
        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        if (!getCanView(discussionPermissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (userId > 0) {
            if (categoryId > 0) {
                ActivitiesClientUtil.addSubscription(userId,
                        ActivityCategory.DISCUSSION, categoryId, Long.toString(categoryId));
                return "redirect:/discussion/category/"+categoryId;
            } else {
                ActivitiesClientUtil.addSubscription(userId,
                        ActivityCategory.DISCUSSION, categoryGroup.getId(), "");
                return "redirect:/discussion";
            }
        }
        return "redirect:/discussion";
    }


    @GetMapping("/discussion/unsubscribeCategory")
    public String unsubscribeCategory(HttpServletRequest request, HttpServletResponse response,
            Member member, @RequestParam long categoryId)
            throws DiscussionAuthorizationException, IOException {

        long userId = MemberAuthUtil.getuserId(request);
        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        if (!getCanView(discussionPermissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (userId > 0) {
            if (categoryId > 0) {
                ActivitiesClientUtil.deleteSubscription(userId,
                        ActivityCategory.DISCUSSION,categoryId);
                return "redirect:/discussion/category/"+categoryId;

            } else {
                ActivitiesClientUtil.deleteSubscription(userId,
                        ActivityCategory.DISCUSSION, categoryGroup.getId());
                return "redirect:/discussion";
            }
        }
        return "redirect:/discussion";
    }

    @Override
    public boolean getCanView(DiscussionPermissions permissions, CategoryGroup categoryGroup, long additionalId) {
        return true; //not used
    }

    @Override
    public boolean getCanEdit(DiscussionPermissions permissions, CategoryGroup categoryGroup, long additionalId) {
        return permissions.getCanCreateCategory();
    }
}

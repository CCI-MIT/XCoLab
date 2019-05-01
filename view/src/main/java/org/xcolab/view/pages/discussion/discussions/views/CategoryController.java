package org.xcolab.view.pages.discussion.discussions.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
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

    @Autowired
    private IActivityClient activityClient;

    @Autowired
    private ICategoryClient categoryClient;

    @Autowired
    private IThreadClient threadClient;

    @GetMapping("/discussion")
    public String showCategories(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam(required = false) String sortColumn,
            @RequestParam(defaultValue = "false") boolean sortAscending) {
        model.addAttribute("_activePageLink", "community");
        long userId = MemberAuthUtil.getUserId();

        ThreadSortColumn threadSortColumn;
        try {
            threadSortColumn = ThreadSortColumn.from(sortColumn);
        } catch (ThreadSortColumn.NoSuchThreadSortColumnException e) {
            threadSortColumn = ThreadSortColumn.DATE;
        }

        ICategoryGroup categoryGroup = getCategoryGroup(request);
        List<ICategory> categories = categoryGroup.getCategories();
        List<IThread> threads = categoryGroup.getThreads(threadSortColumn, sortAscending);

        model.addAttribute("categoryGroup", categoryGroup);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", threads);
        model.addAttribute("sortColumn", threadSortColumn);
        model.addAttribute("sortAscending", sortAscending);

        model.addAttribute("isSubscribed", activityClient.isSubscribed(
                userId, ActivityCategory.DISCUSSION, categoryGroup.getId()));

        DiscussionPermissions discussionPermissions = new DiscussionPermissions();
        model.addAttribute("discussionPermissions", discussionPermissions);

        model.addAttribute("_activePageLink", "community");
        return "/discussion/category";
    }

    @GetMapping("/discussion/category/{categoryId}")
    public String showCategory(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long categoryId,
            @RequestParam(required = false) String sortColumn,
            @RequestParam (required= false, defaultValue = "true") boolean sortAscending)
            throws DiscussionAuthorizationException, CategoryNotFoundException {

        long userId = MemberAuthUtil.getUserId();

        ThreadSortColumn threadSortColumn;
        try {
            threadSortColumn = ThreadSortColumn.from(sortColumn);
        } catch (ThreadSortColumn.NoSuchThreadSortColumnException e) {
            threadSortColumn = ThreadSortColumn.DATE;
        }

        ICategoryGroup categoryGroup = getCategoryGroup(request);

        List<ICategory> categories = categoryGroup.getCategories();
        ICategory currentCategory = categoryClient.getCategory(categoryId);

        model.addAttribute("categoryGroup", categoryGroup);
        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", threadClient.listThreads(0, Integer.MAX_VALUE,
                currentCategory.getId(), null, threadSortColumn, sortAscending));
        model.addAttribute("sortColumn", threadSortColumn.name());
        model.addAttribute("sortAscending", sortAscending);

        model.addAttribute("isSubscribed", activityClient.isSubscribed(userId,
                ActivityCategory.DISCUSSION, currentCategory.getId()));


        DiscussionPermissions discussionPermissions = new DiscussionPermissions();
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
            ICategory category = categoryClient.getCategory(categoryId);
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
            Model model, UserWrapper member, @RequestParam long categoryId)
            throws DiscussionAuthorizationException {

        ICategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions();
        if (!getCanEdit(discussionPermissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("_activePageLink", "community");
        return "/discussion/category_add";
    }

    // @ActionMapping(params = "action=createCategory")
    public String createCategoryAction(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, @RequestParam String title, @RequestParam String description)
            throws IOException, DiscussionAuthorizationException, OperationNotSupportedException {

        ICategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions();
        if (!getCanEdit(discussionPermissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        throw new OperationNotSupportedException("Not implemented");
    }


    @GetMapping("/discussion/subscribeCategory")
    public String subscribeCategory(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, @RequestParam long categoryId)
            throws DiscussionAuthorizationException, IOException {

        long userId = MemberAuthUtil.getUserId();
        ICategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions();
        if (!getCanView(discussionPermissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (userId > 0) {
            if (categoryId > 0) {
                activityClient.addSubscription(userId,
                        ActivityCategory.DISCUSSION, categoryId);
                return "redirect:/discussion/category/"+categoryId;
            } else {
                activityClient.addSubscription(userId,
                        ActivityCategory.DISCUSSION, categoryGroup.getId());
                return "redirect:/discussion";
            }
        }
        return "redirect:/discussion";
    }


    @GetMapping("/discussion/unsubscribeCategory")
    public String unsubscribeCategory(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, @RequestParam long categoryId)
            throws DiscussionAuthorizationException, IOException {

        long userId = MemberAuthUtil.getUserId();
        ICategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions();
        if (!getCanView(discussionPermissions, categoryGroup, 0L)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (userId > 0) {
            if (categoryId > 0) {
                activityClient.deleteSubscription(userId,
                        ActivityCategory.DISCUSSION,categoryId);
                return "redirect:/discussion/category/"+categoryId;

            } else {
                activityClient.deleteSubscription(userId,
                        ActivityCategory.DISCUSSION, categoryGroup.getId());
                return "redirect:/discussion";
            }
        }
        return "redirect:/discussion";
    }

    @Override
    public boolean getCanView(DiscussionPermissions permissions, ICategoryGroup categoryGroup, long additionalId) {
        return true; //not used
    }

    @Override
    public boolean getCanEdit(DiscussionPermissions permissions, ICategoryGroup categoryGroup, long additionalId) {
        return permissions.getCanCreateCategory();
    }
}

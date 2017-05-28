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
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CategoryController extends BaseDiscussionController {

    @GetMapping("/discussion")
    public String showCategories(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("_activePageLink", "community");
        return showCategories(request, response, model, ThreadSortColumn.DATE.name(), false);
    }

    @GetMapping("/discussion/categories/sort/{sortColumn}")
    public String showCategoriesSort(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable String sortColumn) {
        model.addAttribute("_activePageLink", "community");
        return showCategories(request, response, model, sortColumn,false);
    }

    private String showCategories(HttpServletRequest request, HttpServletResponse response,
            Model model, String sortColumn, Boolean sortAscending) {
        long memberId = MemberAuthUtil.getMemberId(request);

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

        model.addAttribute("isSubscribed", ActivitiesClientUtil.isSubscribedToActivity(memberId,
                ActivityEntryType.DISCUSSION.getPrimaryTypeId(), categoryGroup.getGroupId(),0, ""));

        model.addAttribute("_activePageLink", "community");
        return "/discussion/category";
    }

    @GetMapping("/discussion/category/{categoryId}")
    public String showCategory(HttpServletRequest request, HttpServletResponse response, Model model,
                               @PathVariable long categoryId, @RequestParam(required = false) String sortColumn,
                               @RequestParam(required = false) boolean sortAscending)
            throws DiscussionAuthorizationException, CategoryNotFoundException {

        long memberId = MemberAuthUtil.getMemberId(request);

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
        model.addAttribute("isSubscribed", ActivitiesClientUtil.isSubscribedToActivity(memberId,
                ActivityEntryType.DISCUSSION.getPrimaryTypeId(), categoryGroup.getGroupId(),0,Long.toString(categoryId) ));

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
    public String createCategory(HttpServletRequest request, HttpServletResponse response, Model model,
                               @RequestParam long categoryId)
            throws DiscussionAuthorizationException {

        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        if (!getCanEdit(discussionPermissions, categoryGroup, 0L)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        // should not be reached
        checkCanEdit(request, "User does not have the necessary permissions to create a category",
                categoryGroup, 0L);

        model.addAttribute("_activePageLink", "community");
        return "/discussion/category_add";
    }

    // @ActionMapping(params = "action=createCategory")
    public String createCategoryAction(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String title, @RequestParam String description)
            throws IOException, DiscussionAuthorizationException, OperationNotSupportedException {

        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        if (!getCanEdit(discussionPermissions, categoryGroup, 0L)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        // should not be reached
        checkCanEdit(request, "User does not have the necessary permissions to create a category",
                categoryGroup, 0L);

        throw new OperationNotSupportedException("Not implemented");
    }


    @GetMapping("/discussion/subscribeCategory")
    public String subscribeCategory(HttpServletRequest request, HttpServletResponse response, @RequestParam long categoryId)
            throws DiscussionAuthorizationException, IOException {

        long memberId = MemberAuthUtil.getMemberId(request);
        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        if (!getCanView(discussionPermissions, categoryGroup, 0L)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        // should not be reached
        checkCanView(request, "You do not have permissions to view this category",
                categoryGroup, 0L);

        if (memberId > 0) {
            if (categoryId > 0) {
                ActivitiesClientUtil.addSubscription(memberId,
                        ActivityEntryType.DISCUSSION, categoryId, Long.toString(categoryId));
            } else {
                ActivitiesClientUtil.addSubscription(memberId,
                        ActivityEntryType.DISCUSSION, categoryGroup.getGroupId(), "");
            }
        }
        return "redirect:/discussion";
    }


    @GetMapping("/discussion/unsubscribeCategory")
    public String unsubscribeCategory(HttpServletRequest request, HttpServletResponse response, @RequestParam long categoryId)
            throws DiscussionAuthorizationException, IOException {

        long memberId = MemberAuthUtil.getMemberId(request);
        CategoryGroup categoryGroup = getCategoryGroup(request);

        DiscussionPermissions discussionPermissions = new DiscussionPermissions(request);
        if (!getCanView(discussionPermissions, categoryGroup, 0L)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        // should not be reached
        checkCanView(request, "You do not have permissions to view this category", categoryGroup, 0L);

        if (memberId > 0) {
            if (categoryId > 0) {
                ActivitiesClientUtil.deleteSubscription(memberId,
                        ActivityEntryType.DISCUSSION, categoryGroup.getGroupId(),
                        Long.toString(categoryId));

            } else {
                ActivitiesClientUtil.deleteSubscription(memberId,
                        ActivityEntryType.DISCUSSION, categoryGroup.getGroupId(), "");
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

    //TODO: move
    public static Comparator<CommentThread> getComparator(final ThreadSortColumn sortColumn, final boolean sortAscending) {
        return new Comparator<CommentThread>() {

            @Override
            public int compare(CommentThread o1, CommentThread o2) {
                int ret;

                switch (sortColumn) {
                    case TITLE:
                        ret = o1.getTitle().compareToIgnoreCase(o2.getTitle());
                        break;
                    case REPLIES:
                        ret = o1.getCommentsCount() - o2.getCommentsCount();
                        break;
                    case LAST_COMMENTER:
                        final long lastActivityAuthorId1 = ThreadClientUtil
                                .getLastActivityAuthorId(o1.getThreadId());
                        final long lastActivityAuthorId2 = ThreadClientUtil
                                .getLastActivityAuthorId(o2.getThreadId());
                        try {
                            final Member lastActivityAuthor1 = MembersClient.getMember(
                                    lastActivityAuthorId1);
                            final Member lastActivityAuthor2 = MembersClient.getMember(
                                    lastActivityAuthorId2);
                            if (lastActivityAuthor1 != null && lastActivityAuthor2 != null) {
                                ret = lastActivityAuthor1.getScreenName()
                                        .compareToIgnoreCase(lastActivityAuthor2.getScreenName());
                            } else {
                                ret = (int) (lastActivityAuthorId1 - lastActivityAuthorId2);
                            }
                        } catch (MemberNotFoundException e) {
                            ret = (int) (lastActivityAuthorId1 - lastActivityAuthorId2);
                        }
                        break;
                    case DATE:
                    default:
                        ret = ThreadClientUtil.getLastActivityDate(o1.getThreadId())
                                .compareTo(ThreadClientUtil.getLastActivityDate(o2.getThreadId()));
                        break;
                }

                return sortAscending ? -ret : ret;
            }
        };
    }
}

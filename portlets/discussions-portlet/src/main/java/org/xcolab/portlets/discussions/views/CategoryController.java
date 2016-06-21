package org.xcolab.portlets.discussions.views;

import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
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
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadSortColumn;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.jspTags.discussion.wrappers.CategoryWrapper;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class CategoryController extends BaseDiscussionController {

    @RenderMapping
    public String showCategories(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {
        return showCategories(request, response, model, ThreadSortColumn.DATE.name(), false);
    }

    @RenderMapping(params = "action=showCategories")
    public String showCategories(PortletRequest request, PortletResponse response, Model model,
                                 @RequestParam String sortColumn,
                                 @RequestParam boolean sortAscending)
            throws SystemException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

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

        model.addAttribute("isSubscribed", ActivitiesClient.isSubscribedToActivity(themeDisplay.getUserId(),
                ActivityEntryType.DISCUSSION.getPrimaryTypeId(), categoryGroup.getGroupId(),0, ""));

        return "category";
    }

    @RenderMapping(params = "action=showCategory")
    public String showCategory(PortletRequest request, PortletResponse response, Model model,
                               @RequestParam long categoryId, @RequestParam(required = false) String sortColumn,
                               @RequestParam(required = false) boolean sortAscending)
            throws SystemException, PortalException, DiscussionAuthorizationException, CategoryNotFoundException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        ThreadSortColumn threadSortColumn;
        try {
            threadSortColumn = ThreadSortColumn.from(sortColumn);
        } catch (ThreadSortColumn.NoSuchThreadSortColumnException e) {
            threadSortColumn = ThreadSortColumn.DATE;
        }

        CategoryGroup categoryGroup = getCategoryGroup(request);

        List<Category> categories = categoryGroup.getCategories();
        Category currentCategory = CommentClient.getCategory(categoryId);

        model.addAttribute("categoryGroup", categoryGroup);
        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", currentCategory.getThreads(threadSortColumn, sortAscending));
        model.addAttribute("sortColumn", threadSortColumn.name());
        model.addAttribute("sortAscending", sortAscending);
        model.addAttribute("isSubscribed", ActivitiesClient.isSubscribedToActivity(themeDisplay.getUserId(),
                ActivityEntryType.DISCUSSION.getPrimaryTypeId(), categoryGroup.getGroupId(),0,Long.toString(categoryId) ));

        return "category";
    }

    @ActionMapping(params = "action=sortCategory")
    public void sortCategory(ActionRequest request, ActionResponse response,
                             @RequestParam(required = false) Long categoryId, @RequestParam String sortColumn,
                             @RequestParam String currentSortColumn, @RequestParam boolean currentSortAscending)
            throws SystemException, PortalException, IOException {

        final String baseUrl;
        if (categoryId != null && categoryId > 0) {
            DiscussionCategory category = DiscussionCategoryLocalServiceUtil.fetchDiscussionCategory(categoryId);
            baseUrl = new CategoryWrapper(category).getLinkUrl();
        } else {
            baseUrl = "/web/guest/discussion/-/discussion/categories";
        }

        String sortAscendingString = !sortColumn.equalsIgnoreCase(currentSortColumn) || !currentSortAscending ? "" : "/descending";

        response.sendRedirect(baseUrl + "/sort/" + sortColumn + sortAscendingString);
    }

    @RenderMapping(params = "action=createCategory")
    public String createCategory(PortletRequest request, PortletResponse response, Model model,
                               @RequestParam long categoryId)
            throws SystemException, PortalException, DiscussionAuthorizationException {

        CategoryGroup categoryGroup = getCategoryGroup(request);
        checkCanEdit(request, "User does not have the necessary permissions to create a category",
                categoryGroup, 0L);

        return "category_add";
    }

    @ActionMapping(params = "action=createCategory")
    public void createCategoryAction(ActionRequest request, ActionResponse response,
                                   @RequestParam String title, @RequestParam String description)
            throws SystemException, PortalException, IOException, DiscussionAuthorizationException, OperationNotSupportedException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        CategoryGroup categoryGroup = getCategoryGroup(request);

        checkCanEdit(request, "User does not have the necessary permissions to create a category",
                categoryGroup, 0L);

        throw new OperationNotSupportedException("Not implemented");
//        final DiscussionCategory category = DiscussionCategoryLocalServiceUtil.createDiscussionCategory(categoryGroup.getGroupId(), title, description, themeDisplay.getUser());
//
//        response.sendRedirect(new CategoryWrapper(category).getLinkUrl());
    }

    @ActionMapping(params = "action=subscribeCategory")
    public void subscribeCategory(ActionRequest request, ActionResponse response, @RequestParam long categoryId)
            throws SystemException, DiscussionAuthorizationException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        CategoryGroup categoryGroup = getCategoryGroup(request);
        checkCanView(request, "You do not have permissions to view this category", categoryGroup, 0L);

        if (!themeDisplay.getUser().isDefaultUser()) {
            final long memberId = themeDisplay.getUserId();
            if (categoryId > 0) {
                ActivitiesClient.addSubscription(memberId,
                        ActivityEntryType.DISCUSSION, categoryId, Long.toString(categoryId));
            } else {
                ActivitiesClient.addSubscription(memberId,
                        ActivityEntryType.DISCUSSION, categoryGroup.getGroupId(), "");
            }
        }
    }

    @ActionMapping(params = "action=unsubscribeCategory")
    public void unsubscribeCategory(ActionRequest request, ActionResponse response, @RequestParam long categoryId)
            throws SystemException, DiscussionAuthorizationException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        CategoryGroup categoryGroup = getCategoryGroup(request);
        checkCanView(request, "You do not have permissions to view this category", categoryGroup, 0L);

        if (!themeDisplay.getUser().isDefaultUser()) {
            final long memberId = themeDisplay.getUserId();
            if (categoryId > 0) {
                ActivitiesClient.deleteSubscription(memberId,
                        ActivityEntryType.DISCUSSION, categoryGroup.getGroupId(),
                        Long.toString(categoryId));

            } else {
                ActivitiesClient.deleteSubscription(memberId,
                        ActivityEntryType.DISCUSSION, categoryGroup.getGroupId(), "");
            }
        }
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
                        final long lastActivityAuthorId1 = CommentClient
                                .getLastActivityAuthorId(o1.getThreadId());
                        final long lastActivityAuthorId2 = CommentClient
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
                        ret = CommentClient.getLastActivityDate(o1.getThreadId())
                                .compareTo(CommentClient.getLastActivityDate(o2.getThreadId()));
                        break;
                }

                return sortAscending ? -ret : ret;
            }
        };
    }
}

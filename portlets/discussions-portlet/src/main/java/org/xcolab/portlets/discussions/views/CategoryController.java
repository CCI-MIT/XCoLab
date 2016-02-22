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
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.ThreadSortColumn;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.jspTags.discussion.wrappers.CategoryWrapper;
import org.xcolab.jspTags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jspTags.discussion.wrappers.ThreadWrapper;
import org.xcolab.utils.ListUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johannes on 12/1/15.
 */
@Controller
@RequestMapping("view")
public class CategoryController extends BaseDiscussionController {

    @RenderMapping
    public String showCategories(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {
        return showCategories(request, response, model, ThreadSortColumn.DATE.name(), true);
    }

    @RenderMapping(params = "action=showCategories")
    public String showCategories(PortletRequest request, PortletResponse response, Model model,
                                 @RequestParam String sortColumn,
                                 @RequestParam boolean sortAscending)
            throws SystemException, PortalException {

        ThreadSortColumn threadSortColumn;
        try {
            threadSortColumn = ThreadSortColumn.from(sortColumn);
        } catch (ThreadSortColumn.NoSuchThreadSortColumnException e) {
            threadSortColumn = ThreadSortColumn.DATE;
        }

        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);

        List<CategoryWrapper> categories = categoryGroupWrapper.getCategories(threadSortColumn, sortAscending);

        List<List<ThreadWrapper>> threadsList = new ArrayList<>();

        if (categories != null) {
            for (CategoryWrapper category : categories) {
                threadsList.add(new ArrayList<>(category.getThreads()));
            }
        }
        List<ThreadWrapper> threads = ListUtil.mergeSortedLists(threadsList,
                ThreadWrapper.getComparator(threadSortColumn, sortAscending));

        model.addAttribute("categoryGroup", categoryGroupWrapper);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", threads);
        model.addAttribute("sortColumn", threadSortColumn);
        model.addAttribute("sortAscending", sortAscending);

        return "category";
    }

    @RenderMapping(params = "action=showCategory")
    public String showCategory(PortletRequest request, PortletResponse response, Model model,
                               @RequestParam long categoryId, @RequestParam(required = false) String sortColumn,
                               @RequestParam(required = false) boolean sortAscending)
            throws SystemException, PortalException, DiscussionAuthorizationException {

        ThreadSortColumn threadSortColumn;
        try {
            threadSortColumn = ThreadSortColumn.from(sortColumn);
        } catch (ThreadSortColumn.NoSuchThreadSortColumnException e) {
            threadSortColumn = ThreadSortColumn.DATE;
        }

        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);

        List<CategoryWrapper> categories = categoryGroupWrapper.getCategories(threadSortColumn, sortAscending);
        CategoryWrapper currentCategory = new CategoryWrapper(DiscussionCategoryLocalServiceUtil.fetchDiscussionCategory(categoryId),
                threadSortColumn, sortAscending);

        model.addAttribute("categoryGroup", categoryGroupWrapper);
        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", currentCategory.getThreads());
        model.addAttribute("sortColumn", threadSortColumn.name());
        model.addAttribute("sortAscending", sortAscending);

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

        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);
        checkCanEdit(request, "User does not have the necessary permissions to create a category",
                categoryGroupWrapper.getWrapped(), 0L);

        return "category_add";
    }

    @ActionMapping(params = "action=createCategory")
    public void createCategoryAction(ActionRequest request, ActionResponse response,
                                   @RequestParam String title, @RequestParam String description)
            throws SystemException, PortalException, IOException, DiscussionAuthorizationException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroupWrapper categoryGroupWrapper = getDiscussionCategoryGroupWrapper(request);

        checkCanEdit(request, "User does not have the necessary permissions to create a category",
                categoryGroupWrapper.getWrapped(), 0L);

        final DiscussionCategory category = DiscussionCategoryLocalServiceUtil.createDiscussionCategory(categoryGroupWrapper.getId(), title, description, themeDisplay.getUser());

        response.sendRedirect(new CategoryWrapper(category).getLinkUrl());
    }

    @Override
    public boolean getCanView(DiscussionPermissions permissions, long additionalId) {
        return true; //not used
    }

    @Override
    public boolean getCanEdit(DiscussionPermissions permissions, long additionalId) {
        return permissions.getCanCreateCategory();
    }
}

package org.xcolab.portlets.discussions.views;

import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
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
import org.xcolab.jspTags.discussion.ThreadSortColumn;
import org.xcolab.jspTags.discussion.wrappers.CategoryWrapper;
import org.xcolab.jspTags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jspTags.discussion.wrappers.ThreadWrapper;
import org.xcolab.portlets.discussions.DiscussionPreferences;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by johannes on 12/1/15.
 */
@Controller
@RequestMapping("view")
public class CategoryController {

    @RenderMapping
    public String showCategories(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {

        DiscussionPreferences preferences = new DiscussionPreferences(request);

        DiscussionCategoryGroupWrapper categoryGroupWrapper = new DiscussionCategoryGroupWrapper(
                DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(preferences.getCategoryGroupId()));

        List<CategoryWrapper> categories = categoryGroupWrapper.getCategories();

        List<List<ThreadWrapper>> threadsList = new ArrayList<>();

        for (CategoryWrapper category : categories) {
            threadsList.add(new ArrayList<>(category.getThreads()));
        }
        List<ThreadWrapper> threads = mergeSortedLists(threadsList,
                ThreadWrapper.getComparator(ThreadSortColumn.DATE, true));

        model.addAttribute("categoryGroup", categoryGroupWrapper);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", threads);

        return "category";
    }

    private <T> List<T> mergeSortedLists(List<List<T>> inLists, Comparator<T> comparator) {
        PriorityQueue<ListContainer<T>> minHeap = new PriorityQueue<>(inLists.size());
        int size = 0;
        for (List<T> inList : inLists) {
            minHeap.add(new ListContainer<>(inList, comparator));
            size += inList.size();
        }
        List<T> outList = new ArrayList<>(size);

        while (!minHeap.isEmpty()) {
            ListContainer<T> minContainer = minHeap.poll();
            outList.add(minContainer.poll());
            if (minContainer.hasNext()) {
                minHeap.add(minContainer);
            }
        }

        return outList;
    }

    private static class ListContainer<T> implements Comparable<ListContainer<T>> {

        private final Comparator<T> comparator;
        private final Iterator<T> iterator;
        private T currentItem;

        public ListContainer(List<T> list, Comparator<T> comparator) {
            this.comparator = comparator;
            this.iterator = list.iterator();
            currentItem = iterator.next();
        }

        public T poll() {
            final T ret = currentItem;
            currentItem = iterator.next();
            return ret;
        }

        public T peek() {
            return currentItem;
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public int compareTo(ListContainer<T> o) {
            return comparator.compare(currentItem, o.peek());
        }
    }

    @RenderMapping(params = "action=showCategory")
    public String showCategory(PortletRequest request, PortletResponse response, Model model,
                               @RequestParam long categoryId)
            throws SystemException, PortalException {

        DiscussionPreferences preferences = new DiscussionPreferences(request);

        DiscussionCategoryGroupWrapper categoryGroupWrapper = new DiscussionCategoryGroupWrapper(
                DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(preferences.getCategoryGroupId()));

        List<CategoryWrapper> categories = categoryGroupWrapper.getCategories();
        CategoryWrapper currentCategory = new CategoryWrapper(DiscussionCategoryLocalServiceUtil.fetchDiscussionCategory(categoryId));

        model.addAttribute("categoryGroup", categoryGroupWrapper);
        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("categories", categories);
        model.addAttribute("threads", currentCategory.getThreads());

        return "category";
    }

    @RenderMapping(params = "action=createCategory")
    public String createCategory(PortletRequest request, PortletResponse response, Model model,
                               @RequestParam long categoryId)
            throws SystemException, PortalException {

        DiscussionPreferences preferences = new DiscussionPreferences(request);

        DiscussionCategoryGroupWrapper categoryGroupWrapper = new DiscussionCategoryGroupWrapper(
                DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(preferences.getCategoryGroupId()));

        return "category_add";
    }

    @ActionMapping(params = "action=createCategory")
    public void createCategoryAction(ActionRequest request, ActionResponse response, Model model,
                                   @RequestParam String title, @RequestParam String description)
            throws SystemException, PortalException, IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionPreferences preferences = new DiscussionPreferences(request);

        DiscussionCategoryGroupWrapper categoryGroupWrapper = new DiscussionCategoryGroupWrapper(
                DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(preferences.getCategoryGroupId()));

//        final DiscussionCategory category = DiscussionCategoryLocalServiceUtil.createDebateCategory() addThread(categoryGroupWrapper.getId(),
//                categoryId, title, body, themeDisplay.getUser());
//
//        response.sendRedirect(new ThreadWrapper(thread).getLinkUrl());
    }
}

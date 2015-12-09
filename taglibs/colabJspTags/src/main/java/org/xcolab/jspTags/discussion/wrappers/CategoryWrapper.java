package org.xcolab.jspTags.discussion.wrappers;

import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.utils.userInput.UserInputException;
import com.ext.utils.userInput.service.UserInputFilterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.jspTags.discussion.ThreadSortColumn;
import org.xcolab.util.HumanTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryWrapper implements Serializable {
	private static final long serialVersionUID = 1L;
	private User author;
    private String title;
    private String description;
    private final DiscussionCategory wrapped;
    private String filteredDescription;

    private final ThreadSortColumn sortColumn;
    private final boolean sortAscending;

    private final List<ThreadWrapper> threads = new ArrayList<>();

    public CategoryWrapper(DiscussionCategory category, ThreadSortColumn sortColumn, boolean sortAscending) throws SystemException, PortalException {
        this.wrapped = category;
        this.sortColumn = sortColumn;
        this.sortAscending = sortAscending;
        for (DiscussionMessage thread: DiscussionCategoryLocalServiceUtil.getThreads(category)) {
            this.threads.add(new ThreadWrapper(thread));
        }
        resort();
        this.title = category.getName();
        this.description = category.getDescription();
        //TODO: port
//        setFilteredDescription(ContentFilterHelper.filterContent(description));
    }

    public CategoryWrapper(DiscussionCategory category) throws SystemException, PortalException {
        this(category, ThreadSortColumn.DATE, true);
    }

    public long getId() {
        return wrapped.getCategoryId();
    }
    
    public User getAuthor() {
        return author;
    }
    
    public void setAuthor(User author) {
        this.author = author;
    }
    
    public String getTitle() {
        return wrapped != null ? wrapped.getName() : title;
    }
    
    public void setTitle(String title) {
        this.title = title.substring(0, Math.min(255, title.length()));
    }
    
    public String getDescription() {
        return wrapped != null ? wrapped.getDescription() : description;
    }
    
    public void setDescription(String description) throws UserInputException {
        this.description = description;
        description = UserInputFilterUtil.filterHtml(description);
        //TODO: port
//        setFilteredDescription(ContentFilterHelper.filterContent(description));
    }

    public List<ThreadWrapper> getThreads() {
        return threads;
    }
    
    public int getThreadsCount() {
        return threads.size();
    }

    public DiscussionCategory getWrapped() {
        return wrapped;
    }
    
    public long getAuthorId() {
        return wrapped.getAuthorId();
    }
    
    public long getLastActivityAuthorId() {
        return wrapped.getLastActivityAuthorId();
    }
    
    public String getLastActivityDate() {
        Date lastActivity = wrapped.getLastActivityDate();
        if (lastActivity == null) {
            lastActivity = wrapped.getCreateDate();
        }
        return HumanTime.exactly(new Date().getTime() - lastActivity.getTime());
    }
    
    public User getLastActivityAuthor() throws PortalException, SystemException {
        return DiscussionCategoryLocalServiceUtil.getLastActivityAuthor(wrapped);
    }

    public void setFilteredDescription(String filteredDescription) {
        this.filteredDescription = filteredDescription;
    }

    public String getFilteredDescription() {
        return filteredDescription;
    }

    public void resort() {
        ThreadWrapper.sort(threads, sortColumn, sortAscending);
    }

    public Boolean getSortAscending() {
        return sortAscending;
    }

    public ThreadSortColumn getSortColumn() {
        return sortColumn;
    }

    public String getLinkUrl() {
        return "/web/guest/discussion/-/discussion/category/"+wrapped.getCategoryId();
    }

}

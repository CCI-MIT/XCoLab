package org.climatecollaboratorium.facelets.discussions.support;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.DiscussionActivityKeys;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.utils.userInput.UserInputException;
import com.ext.utils.userInput.service.UserInputFilterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.facelets.discussions.ThreadSortColumns;
import org.climatecollaboratorium.utils.ContentFilterHelper;
import org.climatecollaboratorium.utils.Helper;
import org.climatecollaboratorium.utils.HumanTime;
import org.climatecollaboratorium.validation.CategoryNameValidator;
import org.climatecollaboratorium.validation.ValueRequiredValidator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;
import javax.faces.event.ActionEvent;

public class CategoryWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User author;
    private String title;
    private String description;
    private DiscussionBean discussionBean;
    private DiscussionCategory wrapped;
    private boolean editing;
    private String filteredDescription;
    private boolean goTo;
    private boolean added = false;
    private boolean byNewest = true;

    private String sortColumn = ThreadSortColumns.DATE.name();
    private Boolean sortAscending = true;



    private List<MessageWrapper> threads = new ArrayList<MessageWrapper>();
    
    public CategoryWrapper(DiscussionCategory category, DiscussionBean discussionBean) throws SystemException {
        this.wrapped = category;
        this.discussionBean = discussionBean;
        for (DiscussionMessage thread: DiscussionCategoryLocalServiceUtil.getThreads(category)) {
            this.threads.add(new MessageWrapper(thread, this, discussionBean, 1));
        }
        resort(null);
        
        this.title = category.getName();
        this.description = category.getDescription();
        setFilteredDescription(ContentFilterHelper.filterContent(description));
    }

    public CategoryWrapper(DiscussionBean discussionBean) {
        this.discussionBean = discussionBean;
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
        setFilteredDescription(ContentFilterHelper.filterContent(description));
    }

    public List<MessageWrapper> getThreads() {
        return threads;
    }
    
    public int getThreadsCount() {
        return threads.size();
    }
    
    public User getLastPostAuthor() {
        return null;
    }
    
    public void save(ActionEvent e) throws SystemException, PortalException {
        if (!added && discussionBean.getPermissions().getCanAddCategory()) {
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("categoryName"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !CategoryNameValidator.validateComponent(nameInput)) {
                return;
            }
            
            wrapped = DiscussionCategoryGroupLocalServiceUtil.addCategory(discussionBean.getDiscussion(), 
                    title, description, Helper.getLiferayUser());
            discussionBean.categoryAdded(this);
            added = true;
            
            Helper.sendInfoMessage("Category \"" + title + "\" has been added.");
            goTo = true;
            

            ThemeDisplay td = Helper.getThemeDisplay();
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    DiscussionCategoryGroup.class.getName(), discussionBean.getDiscussionId(), 
                    DiscussionActivityKeys.ADD_CATEGORY.id(),
                    ActivityUtil.getExtraDataForIds(wrapped.getCategoryId()), 0);


        }
    }

    public void threadAdded(MessageWrapper thread) throws SystemException {
        threads.add(thread);
        discussionBean.threadAdded(thread);
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
    
    public void delete(ActionEvent e) throws SystemException {
        if (discussionBean.getPermissions().getCanAdminCategories()) {
            DiscussionCategoryLocalServiceUtil.delete(wrapped);
            discussionBean.categoryDeleted(this);
            Helper.sendInfoMessage("Category \"" + wrapped.getName() + "\" has been deleted.");
        }
    }
    
    public void messageDeleted(MessageWrapper messageWrapper) {
        if (threads != null) {
            threads.remove(messageWrapper);
            discussionBean.messageDeleted(messageWrapper);
        }
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public void toggleEdit(ActionEvent e) {
        editing = !editing;
    }
    
    public void update(ActionEvent e) throws SystemException {
        if (discussionBean.getPermissions().getCanAdmin()) {
            UIInput descriptionInput = (UIInput) e.getComponent().getParent().findComponent("categoryDescription"); 
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("categoryName"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !ValueRequiredValidator.validateComponent(descriptionInput) || 
                    !CategoryNameValidator.validateComponent(nameInput)) {
                return;
            }
            
            DiscussionCategoryLocalServiceUtil.update(wrapped, title, description);
            editing = !editing;
        }        
    }

    public void setFilteredDescription(String filteredDescription) {
        this.filteredDescription = filteredDescription;
    }

    public String getFilteredDescription() {
        return filteredDescription;
    }

    
    public boolean isGoTo() {
        return goTo;
    }

    public void setGoTo(boolean goTo) {
        this.goTo = goTo;
    }
    
    public boolean isByNewest() {
        return byNewest;
    }
    
    public void resort(ActionEvent e) {
        
        Collections.sort(threads, new Comparator<MessageWrapper>() {

            @Override
            public int compare(MessageWrapper o1, MessageWrapper o2) {
                int ret = 0;
                
                if (sortColumn.equals(ThreadSortColumns.QUESTION.name())) {
                    ret = o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
                else if (sortColumn.equals(ThreadSortColumns.REPLIES.name())) {
                    try {
                        ret = o1.getThreadMessagesCount() - o2.getThreadMessagesCount();
                    } catch (SystemException e) {
                        // ignore
                    }
                }
                else if (sortColumn.equals(ThreadSortColumns.LAST_COMMENT.name())) {
                    try {
                        ret = o1.getLastActivityAuthor().getScreenName().compareToIgnoreCase(o2.getLastActivityAuthor().getScreenName());
                    } catch (PortalException e) {
                        // ignore
                    } catch (SystemException e) {
                        // ignore
                    }
                }
                else {
                    ret = o1.getLastActivityDate().compareTo(o2.getLastActivityDate());
                }
                return sortAscending ? -ret : ret;
            }
        });
    }


    public void setSortAscending(Boolean sortAscending) {
        this.sortAscending = sortAscending;
        resort(null);
    }

    public Boolean getSortAscending() {
        return sortAscending;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
        resort(null);
    }

    public String getSortColumn() {
        return sortColumn;
    }
    
}

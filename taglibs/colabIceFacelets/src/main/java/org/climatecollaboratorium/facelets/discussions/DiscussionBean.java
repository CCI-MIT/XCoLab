package org.climatecollaboratorium.facelets.discussions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.events.EventHandler;
import org.climatecollaboratorium.events.HandlerRegistration;
import org.climatecollaboratorium.facelets.discussions.permissions.DefaultDiscussionsPermissionsImpl;
import org.climatecollaboratorium.facelets.discussions.permissions.DiscussionsPermissions;
import org.climatecollaboratorium.facelets.discussions.permissions.DiscussionsPermissionsConfig;
import org.climatecollaboratorium.facelets.discussions.support.CategoryWrapper;
import org.climatecollaboratorium.facelets.discussions.support.MessageWrapper;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.NoSuchDiscussionCategoryException;
import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DiscussionBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final DiscussionPageType DEFAULT_PAGE_TYPE = DiscussionPageType.DISCUSSIONS;
    private Long discussionId;
    private Long categoryId;
    private Long threadId;
    private Long owningGroupId;
    private DiscussionPageType pageType = DiscussionPageType.DISCUSSIONS;
    private Boolean showOnlyComments = false;

    private Long lastInitDiscussionId;
    private Long lastInitOwningGroupId;
    private EventBus eventBus;
    private CategoryWrapper currentCategory;
    private MessageWrapper currentThread;
    private MessageWrapper commentsThread = new MessageWrapper(this);
    private List<CategoryWrapper> categories;
    private List<MessageWrapper> threads;
    private Map<Long, CategoryWrapper> categoriesById;
    private Map<Long, MessageWrapper> threadsById;
    private DiscussionPageType prevPage;
    private MessageWrapper newComment = new MessageWrapper(this);

    private CategoryWrapper newCategory;
    private String searchQuery;
    private List<MessageWrapper> searchResults = new ArrayList<MessageWrapper>();
    private List<SelectItem> categoriesItems;
    private MessageWrapper newThread = new MessageWrapper(this);

    private String sortColumn = ThreadSortClumns.DATE.name();
    private Boolean sortAscending = true;

    private DiscussionCategoryGroup discussion;
    private static Log _log = LogFactoryUtil.getLog(DiscussionBean.class);
    private DiscussionsPermissions permissions;
    private DiscussionsPermissionsConfig permissionsConfig;
    private List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
    private boolean byNewest = true;


	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		_log.info("writing discussion bean");
		System.out.println("writing discussion bean");
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		_log.info("reading discussion bean");
		System.out.println("reading discussion bean");
		in.defaultReadObject();
	}

    
    public DiscussionBean() {
    }
    
    /**
     * Method initializes all elements within the bean. If bean wasn't correctly
     * initialized (there is no such discussion) false is returned. True
     * otherwise.
     * 
     * @param discussionId
     * @param categoryId
     * @param threadId
     * @param messageId
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public boolean init(Long discussionId, Long owningGroupId, DiscussionsPermissions permissions, Boolean comments) 
    throws PortalException, SystemException {
        if (discussionId == null) {
            return false;
        }

        if ((discussionId == null && lastInitDiscussionId == null || discussionId.equals(lastInitDiscussionId)) &&
                ((owningGroupId == null && lastInitOwningGroupId == null || owningGroupId.equals(lastInitOwningGroupId)))) {
            // initialization with the same parameters, do nothing as this would
            // cause reset to internal discussion state
            return discussion != null;
        }
        lastInitDiscussionId = discussionId;
        lastInitOwningGroupId = owningGroupId;
        showOnlyComments = comments != null ? comments : false;
        
        this.discussionId = discussionId;
        this.owningGroupId = owningGroupId;
        
        try {
            discussion = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);

            categories = null;
            threads = null;
            commentsThread = null;

            updatePageType();
            
            if (DiscussionCategoryGroupLocalServiceUtil.getCommentThread(discussion) != null) { 
                commentsThread = new MessageWrapper(DiscussionCategoryGroupLocalServiceUtil.getCommentThread(discussion), null, this, 0);
            }
        } catch (Exception e) {
            _log.error("Error when initializing discussion bean", e);
            return false;
        }

        if (permissions != null) {
            this.permissions = permissions;
        } else {
            if (this.permissions == null) {
                this.permissions = new DefaultDiscussionsPermissionsImpl(this);
            }
        }
        updateDisplay();

        return true;
    }

    private void updatePageType() throws NoSuchDiscussionCategoryException, SystemException,
            NoSuchDiscussionMessageException {
        if (threadId != null) {
            getThreads();
            currentThread = threadsById.get(threadId);
            pageType = DiscussionPageType.THREAD;

        } else if (categoryId != null) {
            getCategories();
            currentCategory = categoriesById.get(categoryId);
            pageType = DiscussionPageType.CATEGORY;
        }

    }

    public DiscussionPageType getPageType() {
        return pageType;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        bindEvents();
    }

    private void bindEvents() {
        for (HandlerRegistration registration: handlerRegistrations) {
            registration.unregister();
        }
        handlerRegistrations.clear();
        
        
        handlerRegistrations.add(eventBus.registerHandler(NavigationEvent.class, new EventHandler<NavigationEvent>() {

            @Override
            public void onEvent(NavigationEvent event) {
                final String discussionSource = "discussion"; 
                if (event.hasSource(discussionSource)) {

                    if (event.getParameters(discussionSource).containsKey("pageType")) {
                        try {
                            pageType = DiscussionPageType.valueOf(event.getParameters(discussionSource).get("pageType"));
                        }
                        catch (IllegalArgumentException ex) {
                            _log.error("Can't find specified page type", ex);
                        }
                    }
                    else {
                        // default page type
                        pageType = DEFAULT_PAGE_TYPE;
                    }
                    if (event.getParameters(discussionSource).containsKey("threadId")) {
                        try {
                            threadId = Long.parseLong(event.getParameters(discussionSource).get("threadId"));
                        }
                        catch (NumberFormatException ex) {
                            _log.error("Can't parse threadId", ex);
                        }
                    }
                    if (event.getParameters(discussionSource).containsKey("categoryId")) {
                        try {
                            categoryId = Long.parseLong(event.getParameters(discussionSource).get("categoryId"));
                        }
                        catch (NumberFormatException ex) {
                            _log.error("Can't parse categoryId", ex);
                        }
                    }
                    try {
                        updateDisplay();
                    }
                    catch (SystemException e) {
                        _log.error("Can't update display", e);
                    } catch (PortalException e) {
                        _log.error("Can't update display", e);
                    }
                }
                else {
                    pageType = DEFAULT_PAGE_TYPE;
                }
            }
            
        }));
        
    }

    public void changePageType(ActionEvent e) throws SystemException,
            PortalException {
        if (e.getComponent().getAttributes().containsKey("pageType")) {
            try {
                Object obj = e.getComponent().getAttributes().get("pageType");
                if (obj != null && obj.toString().trim().length() > 0) {
                    pageType = DiscussionPageType.valueOf(e.getComponent().getAttributes().get("pageType").toString());
                }
            } catch (IllegalArgumentException ex) {
                _log.error("Can't find specified page type", ex);
            }
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("categoryId")) {
            try {
                Object obj = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoryId");
                if (obj != null && obj.toString().trim().length() > 0) {
                
                    categoryId = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext()
                            .getRequestParameterMap().get("categoryId").toString());
                }
            } catch (NumberFormatException ex) {
                _log.error("Can't read category id from request", ex);
            }
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("threadId")) {
            try {
                String threadIdStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("threadId").toString();
                threadId = threadIdStr != null && threadIdStr.trim().length() > 0 ? Long.parseLong(threadIdStr) : null;
            } catch (NumberFormatException ex) {
                _log.error("Can't read thread id from request", ex);
            }
        }
        updateDisplay();
    }

    private void updateDisplay() throws SystemException, PortalException {
        if (discussion == null) {
            return;
        }
        if (showOnlyComments) {
            pageType = DiscussionPageType.COMMENTS;
            if (commentsThread == null && DiscussionCategoryGroupLocalServiceUtil.getCommentThread(discussion) != null) { 
                commentsThread = new MessageWrapper(DiscussionCategoryGroupLocalServiceUtil.getCommentThread(discussion), null, this, 0);
            }
        }
        else if (pageType == DiscussionPageType.CATEGORY) {
            getCategories();
            currentCategory = categoriesById.get(categoryId);
        } else if (pageType == DiscussionPageType.THREAD) {
            getThreads();
            getCategories();
            currentThread = threadsById.get(threadId);
            if (currentThread != null) {
                currentCategory = categoriesById.get(currentThread.getWrapped().getCategoryId());
            }
        } else if (pageType == DiscussionPageType.CATEGORY_ADD) {
            newCategory = new CategoryWrapper(this);
        } else if (pageType == DiscussionPageType.THREAD_ADD) {
            newThread = new MessageWrapper(this);
            if (prevPage == DiscussionPageType.CATEGORY || prevPage == DiscussionPageType.THREAD) {
                newThread.setCategoryId(currentCategory.getId());
            }
        }
        prevPage = pageType;
    }

    public List<MessageWrapper> getThreads() throws SystemException {
        if (threads == null && discussion != null) {
            threads = new ArrayList<MessageWrapper>();
            threadsById = new HashMap<Long, MessageWrapper>();
            for (CategoryWrapper catWrapper : getCategories()) {
                for (MessageWrapper thread : catWrapper.getThreads()) {
                    threads.add(thread);
                    threadsById.put(thread.getId(), thread);
                }
            }
            resort(null);
        }
        return threads;
    }

    public List<CategoryWrapper> getCategories() throws SystemException {
        if (categories == null && discussion != null) {
            categories = new ArrayList<CategoryWrapper>();
            categoriesById = new HashMap<Long, CategoryWrapper>();
            for (DiscussionCategory category : DiscussionCategoryGroupLocalServiceUtil.getCategories(discussion)) {
                CategoryWrapper catWrapper = new CategoryWrapper(category, this);
                categories.add(catWrapper);
                categoriesById.put(catWrapper.getId(), catWrapper);
            }
        }
        return categories;
    }

    public CategoryWrapper getCurrentCategory() {
        return currentCategory;
    }

    public MessageWrapper getCurrentThread() {
        return currentThread;

    }

    public CategoryWrapper getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(CategoryWrapper newCategory) {
        this.newCategory = newCategory;
    }

    public DiscussionCategoryGroup getDiscussion() {
        return discussion;
    }

    public void categoryAdded(CategoryWrapper category) throws SystemException {
        getCategories().add(category);
        categoriesById.put(category.getId(), category);
        //this.pageType = DiscussionPageType.CATEGORY;
        //currentCategory = category;
        if (categoriesItems != null) {
            categoriesItems.add(new SelectItem(category.getId(), category.getTitle()));
        }
    }

    public void threadAdded(MessageWrapper thread) throws SystemException {
        getThreads().add(thread);
        threadsById.put(thread.getId(), thread);
        //this.pageType = DiscussionPageType.THREAD;
        this.currentThread = thread;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void search(ActionEvent e) throws SystemException {
        if (searchQuery.trim().length() == 0) {
            // do nothing
            return;
        }
        pageType = DiscussionPageType.SEARCH_RESULTS;
        searchResults.clear();
        for (DiscussionMessage message : DiscussionMessageLocalServiceUtil.search(searchQuery, discussion.getId())) {
            searchResults.add(new MessageWrapper(message, null, this, 0));
        }
    }

    public List<MessageWrapper> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<MessageWrapper> searchResults) {
        this.searchResults = searchResults;
    }

    public List<SelectItem> getCategoriesItems() throws SystemException {
        if (categoriesItems == null) {
            categoriesItems = new ArrayList<SelectItem>();
            for (CategoryWrapper category : getCategories()) {
                categoriesItems.add(new SelectItem(category.getId(), category.getTitle()));
            }
        }
        return categoriesItems;
    }

    public CategoryWrapper getCategoryById(Long categoryId) throws SystemException {
        getCategories();
        return categoriesById.get(categoryId);
    }

    public MessageWrapper getNewThread() {
        return newThread;
    }

    public void setNewThread(MessageWrapper newThread) {
        this.newThread = newThread;
    }

    public void categoryDeleted(CategoryWrapper categoryWrapper) {
        if (categories != null) {
            categories.remove(categoryWrapper);
            categoriesById.remove(categoryWrapper.getId());
            categoriesItems = null;
        }
        if (pageType == DiscussionPageType.CATEGORY && currentCategory.getId() == categoryWrapper.getId()) {
            pageType = DiscussionPageType.CATEGORIES;
        }
    }

    public void messageDeleted(MessageWrapper messageWrapper) {
        if (threads != null) {
            threads.remove(messageWrapper);
            threadsById.remove(messageWrapper.getId());
        }
        if (pageType == DiscussionPageType.THREAD && currentThread.getId() == messageWrapper.getId()) {
            pageType = DiscussionPageType.CATEGORY;
            currentCategory = currentThread.getCategory();
        }
    }

    public DiscussionsPermissions getPermissions() {
        return permissions;
    }

    public DiscussionsPermissionsConfig getPermissionsConfig() throws SystemException {
        if (permissionsConfig == null) {
            permissionsConfig = new DiscussionsPermissionsConfig(this);
        }
        return permissionsConfig;
    }

    public Long getOwningGroupId() {
        if (owningGroupId == null || owningGroupId <= 0) {
            owningGroupId = Helper.getThemeDisplay().getScopeGroupId();
        }
        return owningGroupId;
    }
    
    public void subscribe(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            //ActivityUtil.addSubscription(DiscussionActivityKeys.ALL, Helper.getLiferayUser().getUserId(), discussionId);
            StringBuilder extraData = new StringBuilder();
            if (pageType == DiscussionPageType.CATEGORY) {
                extraData.append(currentCategory.getId());
            }
            else if (pageType == DiscussionPageType.THREAD) {
                extraData.append(currentThread.getCategoryId());
                extraData.append(",");
                extraData.append(currentThread.getId());
                
            }
            if (isSubscribed()) {
                // user is subscribed, usubscribe
                ActivitySubscriptionLocalServiceUtil.deleteSubscription(Helper.getLiferayUser().getUserId(), 
                        DiscussionCategoryGroup.class, getDiscussionId(), 0, extraData.toString());
            }
            else {
                ActivitySubscriptionLocalServiceUtil.addSubscription(DiscussionCategoryGroup.class, getDiscussionId(), 
                        0, extraData.toString(), Helper.getLiferayUser().getUserId());
            }
        }
    }
    
    public boolean isSubscribed() throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            StringBuilder extraData = new StringBuilder();
            if (pageType == DiscussionPageType.CATEGORY && currentCategory != null) {
                extraData.append(currentCategory.getId());
            }
            else if (pageType == DiscussionPageType.THREAD && currentThread != null) {
                extraData.append(currentThread.getCategoryId());
                extraData.append(",");
                extraData.append(currentThread.getId());   
            }
            else if (pageType == DiscussionPageType.THREAD && currentThread == null) {
                return false;
            }
            return ActivitySubscriptionLocalServiceUtil.isSubscribed(
                    Helper.getLiferayUser().getUserId(), DiscussionCategoryGroup.class, getDiscussionId(), 0, extraData.toString());
            
        }
        return false;
    }
    
    public Long getDiscussionId() {
        return discussionId;
    }

    public MessageWrapper getCommentsThread() {
        return commentsThread;
    }
    
    public void comentsThreadDeleted() throws PortalException, SystemException {
        discussion = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);
        discussion.setCommentsThread(0);
        DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(discussion);
        
        commentsThread = new MessageWrapper(this);
    }
    
    public int getCommentsCount() throws SystemException, PortalException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(discussion);
    }
    
    public int getThreadsCount() throws SystemException {
        if (threads == null) {
            getThreads();
        }
        return threads.size();
    }

    public void commentAdded(MessageWrapper messageWrapper) throws SystemException {
        if (commentsThread.isNewMsg()) {
            commentsThread = messageWrapper;
            messageWrapper.setTitle(messageWrapper.getTitle() + " " + 1);
        }
        else {
            messageWrapper.setTitle(messageWrapper.getTitle() + " " + commentsThread.getThreadMessagesCount() + 2);
            getCommentsThread().addMessage(messageWrapper);
        }
        newComment = new MessageWrapper(this);
    }
    
    public MessageWrapper getNewComment() {
        return newComment;
    }
    
    public boolean getHasComments() throws SystemException {
        return commentsThread.getWrapped() != null;  
    }
    
    public boolean isByNewest() {
        return byNewest;
    }
    
    public void resort(ActionEvent e) {
        
        Collections.sort(threads, new Comparator<MessageWrapper>() {

            @Override
            public int compare(MessageWrapper o1, MessageWrapper o2) {
                int ret = 0;
                
                if (sortColumn.equals(ThreadSortClumns.QUESTION.name())) {
                    ret = o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
                else if (sortColumn.equals(ThreadSortClumns.REPLIES.name())) {
                    try {
                        ret = o1.getThreadMessagesCount() - o2.getThreadMessagesCount();
                    } catch (SystemException e) {
                        // ignore
                    }
                }
                else if (sortColumn.equals(ThreadSortClumns.LAST_COMMENT.name())) {
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
    
    public boolean isThreadNull() {
        return currentThread == null;
    }
    
    public boolean isUserLoggedIn() {
        return Helper.isUserLoggedIn();
    }
    
    public String getDiscussionUrl() {
        return discussion.getUrl();
    }
    
}

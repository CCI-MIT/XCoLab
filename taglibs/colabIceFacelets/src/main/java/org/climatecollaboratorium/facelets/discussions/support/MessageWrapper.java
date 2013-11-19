package org.climatecollaboratorium.facelets.discussions.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIInput;
import javax.faces.event.ActionEvent;

import com.ext.portlet.DiscussionActivityKeys;
import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.utils.ContentFilterHelper;
import org.climatecollaboratorium.utils.Helper;
import org.climatecollaboratorium.utils.HumanTime;
import org.climatecollaboratorium.validation.ValueRequiredValidator;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.discussions.DiscussionMessageFlagType;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.DiscussionMessageFlag;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.utils.userInput.UserInputException;
import com.ext.utils.userInput.service.UserInputFilterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class MessageWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
    private String description;
    private DiscussionMessage wrapped;
    private CategoryWrapper category;
    private List<MessageWrapper> messages;
    private MessageWrapper newMessage;
    private MessageWrapper thread;
    private Long categoryId;
    private DiscussionBean discussionBean;
    private boolean editing;
    private String filteredDescription;
    private boolean empty = false;


    private String shortDescription;
    private boolean goTo;
    private boolean added = false;
    private int messageNum;
    private boolean oldestFirst = true;
    
    public MessageWrapper(DiscussionMessage wrapped, CategoryWrapper category, DiscussionBean discussionBean, 
            MessageWrapper thread, int messageNum) {
        this(wrapped, category, discussionBean, messageNum);
        this.thread = thread;
        
    }
    
    public MessageWrapper(DiscussionMessage wrapped, CategoryWrapper category, DiscussionBean discussionBean, int messageNum) {
        this.category = category;
        this.wrapped = wrapped;

        title = wrapped.getSubject();
        description = wrapped.getBody();
        filteredDescription = ContentFilterHelper.filterContent(description);
        shortDescription = ContentFilterHelper.getShortString(description);
        this.discussionBean = discussionBean;
        this.messageNum = messageNum;
        if (category != null) {
            categoryId = category.getId();
        }
        
        
        if (wrapped.getThreadId() <= 0) {
            // message represents a thread create placeholder for new message
            newMessage = new MessageWrapper(this);
        }
    }

    public MessageWrapper(DiscussionBean discussionBean) {
        this.discussionBean = discussionBean; 
        empty = true;
        messages = new ArrayList<MessageWrapper>();
    }
    
    public MessageWrapper(MessageWrapper thread) {
        this.thread = thread;
        this.discussionBean = thread.discussionBean;
    }
    
    public Long getId() {
        return wrapped.getMessageId();
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title.substring(0, Math.min(255, title.length()));
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) throws UserInputException {
        this.description = UserInputFilterUtil.filterHtml(description);
    }
    
    public String getFilteredDescription() {
        return filteredDescription;
    }

    
    
    public List<MessageWrapper> getThreadMessages() throws SystemException {
        if (messages == null || messages.size() == 0) {
            messages = new ArrayList<MessageWrapper>();
            messages.add(this);
            for (DiscussionMessage message: DiscussionMessageLocalServiceUtil.getThreadMessages(wrapped)) {
                messages.add(new MessageWrapper(message, category, discussionBean, 0));
            }
            
            Collections.sort(messages, new Comparator<MessageWrapper>() {

                @Override
                public int compare(MessageWrapper o1, MessageWrapper o2) {
                    return o1.getCreateDate().compareTo(o2.getCreateDate());
                }
            });
            int messageNum = 1;
            for (MessageWrapper message: messages) {
                message.messageNum = messageNum;
                messageNum++;
            }
        }
        return messages;
    }
    
    public CategoryWrapper getCategory() {
        return category;
    }
    
    public int getThreadMessagesCount() throws SystemException {
        return DiscussionMessageLocalServiceUtil.getThreadMessagesCount(wrapped);
    }
    
    public void save(ActionEvent e) throws SystemException, PortalException {
        if (!added && discussionBean.getPermissions().getCanAddThread()) {

            UIInput messageInput = (UIInput) e.getComponent().getParent().findComponent("messageContent"); 
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("messageTitle"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }
            
            
            category = discussionBean.getCategoryById(categoryId);
            wrapped = DiscussionCategoryLocalServiceUtil.addThread(category.getWrapped(), 
                    title, description, Helper.getLiferayUser());
            added = true;
            category.threadAdded(this);
            newMessage = new MessageWrapper(this);
            filteredDescription = ContentFilterHelper.filterContent(description);
            Helper.sendInfoMessage("Message \"" + title + "\" has been added.");
            goTo = true;
            ThemeDisplay td = Helper.getThemeDisplay();

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    DiscussionCategoryGroup.class.getName(), discussionBean.getDiscussionId(), 
                    DiscussionActivityKeys.ADD_DISCUSSION.id(),
                    ActivityUtil.getExtraDataForIds(wrapped.getCategoryId(), wrapped.getMessageId()), 0);
        }
    }
    
    public void addMessageToThread(ActionEvent e) throws SystemException, PortalException {
        if (!added && discussionBean.getPermissions().getCanAddMessage()) {

            UIInput messageInput = (UIInput) e.getComponent().getParent().findComponent("messageContent"); 
            //UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("messageTitle"); 
            /*if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }*/
            if (!ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }
            // title set by default
            title = "message title";
            
            wrapped = DiscussionMessageLocalServiceUtil.addThreadMessage(thread.getWrapped(), title, description, 
                    Helper.getLiferayUser());
            added = true;
            thread.addMessage(this);
            filteredDescription = ContentFilterHelper.filterContent(description);
            Helper.sendInfoMessage("Message has been added.");

            ThemeDisplay td = Helper.getThemeDisplay();
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    DiscussionCategoryGroup.class.getName(), discussionBean.getDiscussionId(), 
                    DiscussionActivityKeys.ADD_COMMENT.id(), 
                    ActivityUtil.getExtraDataForIds(wrapped.getCategoryId(), getThreadId(wrapped), wrapped.getMessageId()), 0);
        }
    }
    
    public void addComment(ActionEvent e) throws SystemException, PortalException {
        try {
        if (!added && discussionBean.getPermissions().getCanAddComment()) {

            UIInput messageInput = (UIInput) e.getComponent().getParent().findComponent("messageContent"); 
            
           
            if (!ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }
            title = discussionBean.getDiscussion().getDescription() + " comment"; 
            wrapped = DiscussionCategoryGroupLocalServiceUtil.addComment(discussionBean.getDiscussion(), title, description, Helper.getLiferayUser());
            added = true;

            discussionBean.commentAdded(this);
            empty = false;
            
            filteredDescription = ContentFilterHelper.filterContent(description);
            Helper.sendInfoMessage("Comment \"" + title + "\" has been added.");

            /*
            ThemeDisplay td = Helper.getThemeDisplay();
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    DiscussionCategoryGroup.class.getName(), discussionBean.getDiscussionId(), 
                    DiscussionActivityKeys.ADD_DISCUSSION_COMMENT.id(), 
                    ActivityUtil.getExtraDataForIds(wrapped.getCategoryGroupId(), getThreadId(wrapped), wrapped.getMessageId()), 0);
                    
            */
        }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateMessage(ActionEvent e) throws SystemException {
        try {
        if (discussionBean.getPermissions().getCanAdminMessages()) {

            UIInput messageInput = (UIInput) e.getComponent().getParent().findComponent("messageContent"); 
            UIInput nameInput = (UIInput) e.getComponent().getParent().findComponent("messageTitle"); 
            if (!ValueRequiredValidator.validateComponent(nameInput) || 
                    !ValueRequiredValidator.validateComponent(messageInput)) {
                return;
            }
            
            
            DiscussionMessageLocalServiceUtil.update(wrapped, title, description);
            filteredDescription = ContentFilterHelper.filterContent(description);
            editing = false;
        }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private Long getThreadId(DiscussionMessage msg) {
        return msg.getThreadId() > 0 ? msg.getThreadId() : msg.getMessageId();
    }
    
    public MessageWrapper getNewMessage() {
        return newMessage;
    }

    public User getAuthor() throws PortalException, SystemException {
        return DiscussionMessageLocalServiceUtil.getAuthor(wrapped);
    }
    
    public Date getCreateDate() {
        return wrapped.getCreateDate();
    }
    
    public void addMessage(MessageWrapper messageWrapper) throws SystemException {
        if (messages == null) {
            getThreadMessages();
        }
        if (oldestFirst) {
            messageWrapper.messageNum = messages.get(messages.size() - 1).messageNum + 1;
            messages.add(messageWrapper);
        }
        else {
            messageWrapper.messageNum = messages.get(0).messageNum + 1;
            messages.add(0, messageWrapper);
        }
        messageWrapper.setThread(this);
        newMessage = new MessageWrapper(this);
    }

    private void setThread(MessageWrapper messageWrapper) {
        this.thread = messageWrapper;
        
    }

    public DiscussionMessage getWrapped() {
        return wrapped;
    }
    
    public Long getAuthorId() {
        return wrapped.getAuthorId();
    }
    
    public Long getLastActivityAuthorId() {
        return wrapped.getLastActivityAuthorId();
    }
    
    public String getLastActivityDateStr() {
        return HumanTime.exactly(new Date().getTime() - (wrapped.getLastActivityDate() != null ? wrapped.getLastActivityDate() : wrapped.getCreateDate()).getTime());
    }
    /*
    public String getLastActivityDateAgo() {
        //return Pretty
    }*/
    
    public Date getLastActivityDate() {
        return wrapped.getLastActivityDate() != null ? wrapped.getLastActivityDate() : wrapped.getCreateDate();
    }
    
    public User getLastActivityAuthor() throws PortalException, SystemException {
        return DiscussionMessageLocalServiceUtil.getLastActivityAuthor(wrapped);
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    
    public void delete(ActionEvent e) throws SystemException, PortalException {
        if (discussionBean.getPermissions().getCanAdminMessages()) {
            DiscussionMessageLocalServiceUtil.delete(wrapped);
            if (thread != null) {
                // this is a message within a thread
                thread.messageDeleted(this);
            }
            if (category != null) {
                category.messageDeleted(this);
            }
            if (discussionBean != null && discussionBean.getCommentsThread() == this) {
                discussionBean.comentsThreadDeleted();
            }
            
            
            //category.messageDeleted(this);
            Helper.sendInfoMessage("Message \"" + wrapped.getSubject() + "\" has been deleted.");
        }
    }
    
    public void messageDeleted(MessageWrapper messageWrapper) {
        messages.remove(messageWrapper);
        int i=1;
        for (MessageWrapper msg: messages) {
            msg.setMessageNum(i++);
        }
    }
    
    private void setMessageNum(int i) {
        messageNum = i;
    }

    public void toggleEdit(ActionEvent e) {
        editing = !editing;
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public Long getThreadId() {
        return wrapped.getThreadId() > 0 ? wrapped.getThreadId() : wrapped.getMessageId();
    }

    public void setGoTo(boolean goTo) {
        this.goTo = goTo;
    }

    public boolean isGoTo() {
        return goTo;
    }
    
    public int getMessageNum() {
        return messageNum;
    }
    
    public boolean isNewMsg() {
        return empty;
    }
    
    public boolean isOldestFirst() {
        return oldestFirst;
    }
    
    public void revertMessages(ActionEvent e) {
        Collections.reverse(messages);
        oldestFirst = !oldestFirst;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }
    
    public boolean isExpertReview() throws SystemException {
        return hasFlag(DiscussionMessageFlagType.EXPERT_REVIEW);
    }
    
    public boolean hasFlag(DiscussionMessageFlagType flagType) throws SystemException {
        return hasFlag(flagType.name());
    }
    
    public boolean hasFlag(String flagType) throws SystemException {
        if (wrapped != null && wrapped.getMessageId() > 0) {
            for (DiscussionMessageFlag flag: DiscussionMessageLocalServiceUtil.getFlags(wrapped)) {
                if (flag.getFlagType().equals(flagType)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void addFlag(ActionEvent e) throws SystemException {
        String flagType = e.getComponent().getAttributes().get("flagType").toString();
        
        DiscussionMessageLocalServiceUtil.addFlag(wrapped, flagType, null, Helper.getLiferayUser());
    }
    
    public void removeFlag(ActionEvent e) throws SystemException {
        String flagType = e.getComponent().getAttributes().get("flagType").toString();
        
        DiscussionMessageLocalServiceUtil.removeFlag(wrapped, flagType);
    }
}

package org.xcolab.portlets.messaging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class MessageBean {
    
    private Message message;
    private boolean selected;
    private List<User> receipients = new ArrayList<User>();

    public MessageBean(Message message) throws PortalException, SystemException {
        this.message = message;
        for (MessageRecipientStatus receipient: MessageLocalServiceUtil.getRecipients(message)) {
            receipients.add(UserLocalServiceUtil.getUser(receipient.getUserId()));
        }
    }
    
    public String getSubject() {
        return message.getSubject();
    }
    
    public String getContent() {
        return message.getContent();
    }
    
    public String getFilteredContent() {
        return Helper.filterLineBreaks(message.getContent());
    }
    
    public Date getCreateDate() {
        return message.getCreateDate();
    }
    
    public User getFrom() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(message.getFromId());
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public Message getMessage() {
        return message;
        
    }
    
    public List<User> getTo() {
        return receipients;
    }
    
    public Long getMessageId() {
        return message.getMessageId();
    }
}

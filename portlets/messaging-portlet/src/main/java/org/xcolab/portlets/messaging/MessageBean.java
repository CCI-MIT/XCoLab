package org.xcolab.portlets.messaging;

import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Message message;
    private boolean selected;
    private final List<User> recipients = new ArrayList<>();

    public MessageBean(Message message) throws PortalException, SystemException {
        this.message = message;
        for (MessageRecipientStatus recipient : MessageLocalServiceUtil.getRecipients(message)) {
            try {
                recipients.add(UserLocalServiceUtil.getUser(recipient.getUserId()));
            } catch (Exception e) {
                // User is not available anymore
            }
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Message getMessage() {
        return message;

    }

    public List<User> getTo() {
        return recipients;
    }

    public Long getMessageId() {
        return message.getMessageId();
    }
}

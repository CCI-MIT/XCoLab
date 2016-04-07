package org.xcolab.portlets.messaging.beans;

import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.util.HumanTime;
import org.xcolab.utils.HtmlUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<User> recipients = new ArrayList<>();
    private Message message;
    private long messageId;
    private boolean selected;

    @SuppressWarnings("unused")
    public MessageBean() { }

    public MessageBean(Message message) throws PortalException, SystemException {
        this.message = message;
        this.messageId = message.getMessageId();
        for (MessageRecipientStatus recipient : MessageLocalServiceUtil.getRecipients(message)) {
            try {
                recipients.add(UserLocalServiceUtil.getUser(recipient.getUserId()));
            } catch (PortalException e) {
                // User is not available anymore
            }
        }
    }

    public String getSubject() {
        return message.getSubject();
    }

    public String getContent() {
        if (!message.getContent().contains("<br/>")) {
            return HtmlUtil.addHtmlLineBreaks(message.getContent());
        }
        return message.getContent();
    }

    public String getCreateDateFormatted() {
        return HumanTime.exactly(new Date().getTime() - getCreateDate().getTime());
    }

    public Date getCreateDate() {
        return message.getCreateDate();
    }

    public String getLinkUrl() {
        return "/web/guest/messaging/-/messaging/message/" + getMessageId();
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
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

    public Message getMessage() throws SystemException, PortalException {
        if (message == null) {
            message = MessageLocalServiceUtil.getMessage(messageId);
        }
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<User> getTo() {
        return recipients;
    }
}

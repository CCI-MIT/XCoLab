package org.xcolab.portlets.messaging.beans;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.util.HumanTime;
import org.xcolab.utils.HtmlUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Member> recipients = new ArrayList<>();
    private Message message;
    private long messageId;
    private boolean selected;

    @SuppressWarnings("unused")
    public MessageBean() { }

    public MessageBean(Message message) throws PortalException, SystemException {
        this.message = message;
        this.messageId = message.getMessageId();
        this.recipients = MessagingClient.getMessageRecipients(message.getMessageId());
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

    public void markMessageAsOpened(long userId) {
        MessagingClient.setOpened(messageId, userId, true);
    }

    public boolean getIsOpened() throws MessageNotFoundException, PortalException, SystemException {
        return getMessage().getOpened();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Message getMessage() throws SystemException, PortalException, MessageNotFoundException {
        if (message == null) {
            message = MessagingClient.getMessage(messageId);
        }
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<Member> getTo() {
        return recipients;
    }
}

package org.xcolab.view.pages.messaging.beans;


import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.util.time.HumanTime;

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

    public MessageBean(Message message) {
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
        return "/messaging/message/" + getMessageId();
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public Member getFrom() {
        try {
            return MembersClient.getMember(message.getFromId());
        } catch (MemberNotFoundException e) {
            throw ReferenceResolutionException.toObject(Member.class, message.getFromId())
                    .fromObject(Message.class, message.getMessageId());
        }
    }

    public void markMessageAsOpened(long userId) {
        MessagingClient.setOpened(messageId, userId, true);
    }

    public boolean getIsOpened() throws MessageNotFoundException {
        return getMessage().getOpened();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Message getMessage() throws MessageNotFoundException {
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

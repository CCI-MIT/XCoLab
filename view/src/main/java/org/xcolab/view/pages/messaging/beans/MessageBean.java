package org.xcolab.view.pages.messaging.beans;


import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.MessagingClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.exceptions.MessageNotFoundException;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.user.pojo.Message;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.time.DurationFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Member> recipients = new ArrayList<>();
    private List<String> threads = new ArrayList<>();
    private Message message;
    private long messageId;
    private boolean selected;

    @SuppressWarnings("unused")
    public MessageBean() { }

    public MessageBean(Message message) {
        this.message = message;
        this.messageId = message.getId();
        this.recipients = MessagingClient.getMessageRecipients(message.getId());
        this.threads = MessagingClient.getMessageThreads(message.getId());
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

    public String getCreatedAtFormatted() {
        return DurationFormatter.forRequestLocale().format(getCreatedAt());
    }

    public Date getCreatedAt() {
        return message.getCreatedAt();
    }

    public String getLinkUrl() {
        return "/messaging/fullConversation/" + getMessageId() + "?threadId=";
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
                    .fromObject(Message.class, message.getId());
        }
    }

    public void markMessageAsOpened(long userId) {
        MessagingClient.setOpened(messageId, userId, true);
    }

    public boolean getIsOpened() throws MessageNotFoundException {
        return getMessage().getOpened();
    }

    public String getThreadId() throws MessageNotFoundException {
        return getMessage().getThreadId();
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

    public List<String> getThreads(){
        return this.threads;
    }

    public String getReplySubject() {
        return (this.getSubject()).startsWith("RE:") ? this.getSubject() : ("RE: "+this.getSubject());
    }
}

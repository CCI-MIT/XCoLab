package org.xcolab.view.pages.messaging.beans;


import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.exceptions.MessageNotFoundException;
import org.xcolab.client.user.pojo.wrapper.MessageWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.time.DurationFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<UserWrapper> recipients = new ArrayList<>();
    private List<String> threads = new ArrayList<>();
    private MessageWrapper message;
    private long messageId;
    private boolean selected;

    @SuppressWarnings("unused")
    public MessageBean() { }

    public MessageBean(MessageWrapper message) {
        this.message = message;
        this.messageId = message.getId();
        this.recipients = StaticUserContext.getMessagingClient().getMessageRecipients(message.getId());
        this.threads = StaticUserContext.getMessagingClient().getMessageThreads(message.getId());
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

    public UserWrapper getFrom() {
        try {
            return StaticUserContext.getUserClient().getMember(message.getFromId());
        } catch (MemberNotFoundException e) {
            throw ReferenceResolutionException.toObject(UserWrapper.class, message.getFromId())
                    .fromObject(MessageWrapper.class, message.getId());
        }
    }

    public void markMessageAsOpened(long userId) {
        StaticUserContext.getMessagingClient().setOpened(messageId, userId, true);
    }

    public boolean getIsOpened() throws MessageNotFoundException {
        return message.getOpened();//TODO FIGURE OUT HOW TO GET THIS
    }

    public String getThreadId() throws MessageNotFoundException {
        return message.getThreadId();//TODO FIGURE OUT HOW TO GET THIS
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public MessageWrapper getMessage() throws MessageNotFoundException {
        if (message == null) {
            try {
                message = StaticUserContext.getMessagingClient().getMessage(messageId);
            }catch (MessageNotFoundException mnfe){

            }
        }
        return message;
    }

    public void setMessage(MessageWrapper message) {
        this.message = message;
    }

    public List<UserWrapper> getTo() {
        return recipients;
    }

    public List<String> getThreads(){
        return this.threads;
    }

    public String getReplySubject() {
        return (this.getSubject()).startsWith("RE:") ? this.getSubject() : ("RE: "+this.getSubject());
    }
}

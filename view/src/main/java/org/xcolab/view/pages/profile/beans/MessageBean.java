package org.xcolab.view.pages.profile.beans;

import org.hibernate.validator.constraints.NotBlank;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.MessageWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<UserWrapper> recipients = new ArrayList<>();
    @NotBlank
    private String messageSubject;
    @NotBlank
    private String messageText;
    private MessageWrapper message;
    private boolean selected;


    public MessageBean() {
    }

    public MessageBean(MessageWrapper message) {
        this.message = message;
        this.recipients = StaticUserContext.getMessagingClient().getMessageRecipients(message.getId());
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSubject() {
        return message.getSubject();
    }

    public String getContent() {
        return message.getContent();
    }

    public Date getCreatedAt() {
        return message.getCreatedAt();
    }

    public long getDaysAgo() {
        final int millisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = message.getCreatedAt().getTime() / millisecondsInDay;
        long daysNow = new Date().getTime() / millisecondsInDay;
        return daysNow - createDay;
    }

    public UserWrapper getFrom() throws MemberNotFoundException {
        return StaticUserContext.getUserClient().getMember(message.getFromId());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public MessageWrapper getMessage() {
        return message;
    }

    public List<UserWrapper> getTo() {
        return recipients;
    }

    public void addRecipientUser(UserWrapper recipientUser) {
        recipients.add(recipientUser);
    }

    public Long getMessageId() {
        return message.getId();
    }

    public String getLinkUrl() {
        return "/messaging/fullConversation/" + getMessageId() + "?threadId=";
    }

    public String getThreadId() {
        return null;//getMessage().getThreadId(); //TODO FIGURE OUT HOW TO GET THIS
    }

}

package org.xcolab.portlets.userprofile.beans;

import org.hibernate.validator.constraints.NotBlank;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MessageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Member> recipients = new ArrayList<>();
    @NotBlank
    private String messageSubject;
    @NotBlank
    private String messageText;
    private String messageHoneypot;
    private int messageHoneypotPosition;
    private Message message;
    private boolean selected;

    public MessageBean() {
        messageHoneypotPosition = ((new Random()).nextInt(10)) % 2;
    }

    public MessageBean(Message message) {
        this.message = message;
        this.recipients = MessagingClient.getMessageRecipients(message.getMessageId());
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

    public String getMessageHoneypot() {
        return messageHoneypot;
    }

    public void setMessageHoneypot(String messageHoneypot) {
        this.messageHoneypot = messageHoneypot;
    }

    public int getMessageHoneypotPosition() {
        return messageHoneypotPosition;
    }

    public String getSubject() {
        return message.getSubject();
    }

    public String getContent() {
        return message.getContent();
    }

    public Date getCreateDate() {
        return message.getCreateDate();
    }

    public long getDaysAgo() {
        final int millisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = message.getCreateDate().getTime() / millisecondsInDay;
        long daysNow = new Date().getTime() / millisecondsInDay;
        return daysNow - createDay;
    }

    public Member getFrom() {
        return MembersClient.getMemberUnchecked(message.getFromId());
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

    public List<Member> getTo() {
        return recipients;
    }

    public void addRecipientUser(Member recipientUser) {
        recipients.add(recipientUser);
    }

    public Long getMessageId() {
        return message.getMessageId();
    }

}

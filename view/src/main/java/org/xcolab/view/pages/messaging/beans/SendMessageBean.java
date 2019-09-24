package org.xcolab.view.pages.messaging.beans;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.messaging.MessageLimitExceededException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.html.HtmlUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SendMessageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<UserWrapper> recipientList;
    private String userIdsRecipients;
    private String subject;
    private String messageContent;
    private MessagingBean messagingBean;
    private MessageBean replyMessage;
    private int numberOfMessagesLeft = Integer.MAX_VALUE;

    public SendMessageBean(MessageBean replyMessage) {
        this();
        this.userIdsRecipients = String.valueOf(replyMessage.getFrom().getId());
        this.recipientList.add(replyMessage.getFrom());

        this.subject = "RE: " + replyMessage.getSubject();
        this.messageContent = "<br /><br />-- original message begin --<br /><br />"
                + replyMessage.getContent() + "<br /><br />-- original message end --<br />";
        this.replyMessage = replyMessage;
    }

    public SendMessageBean(long userId) {
        this();
        numberOfMessagesLeft = StaticUserContext.getMessagingClient().getNumberOfMessagesLeft(userId);
    }

    public SendMessageBean() {
        this.recipientList = new ArrayList<>();
    }

    public void send(UserWrapper sender, String baseUri) throws MessageLimitExceededException {
        List<Long> recipientIds = IdListUtil.getIdsFromString(userIdsRecipients);

        StaticUserContext.getMessagingClient().checkLimitAndSendMessage(HtmlUtil.cleanAll(subject),
                HtmlUtil.cleanSome(messageContent, baseUri), sender.getId(), recipientIds);
    }

    public String getUserIdsRecipients() {
        return userIdsRecipients;
    }

    public void setUserIdsRecipients(String userIdsRecipients) {
        this.userIdsRecipients = userIdsRecipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public MessagingBean getMessagingBean() {
        return messagingBean;
    }

    public void setMessagingBean(MessagingBean messagingBean) {
        this.messagingBean = messagingBean;
    }

    public MessageBean getReplyMessage() {
        return replyMessage;
    }

    // to force screen unblocking
    public int getUnblockScreen() {
        return new Random().nextInt();
    }

    public List<UserWrapper> getRecipientList() {
        return recipientList;
    }

    public List<Long> getRecipientIdList() {
        List<Long> recipientIds = new ArrayList<>();
        for (UserWrapper recipient : recipientList) {
            recipientIds.add(recipient.getId());
        }
        return recipientIds;
    }

    public int getNumberOfMessagesLeft() {
        return numberOfMessagesLeft;
    }
}

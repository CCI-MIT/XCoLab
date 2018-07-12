package org.xcolab.view.pages.messaging.beans;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.ReplyingToManyException;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.html.HtmlUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SendMessageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Member> recipientList;
    private final int messageHoneypotPosition;
    private String userIdsRecipients;
    private String subject;
    private String messageContent;
    private MessagingBean messagingBean;
    private MessageBean replyMessage;
    //honeypot is a field supposed to be left blank by humans, and to be filled in by bots, in order to protect from spam.
    private String messageHoneypot;
    private int numberOfMessagesLeft = Integer.MAX_VALUE;

    public SendMessageBean(MessageBean replyMessage) {
        this();
        this.userIdsRecipients = String.valueOf(replyMessage.getFrom().getUserId());
        this.recipientList.add(replyMessage.getFrom());

        this.subject = "RE: " + replyMessage.getSubject();
        this.messageContent = "<br /><br />-- original message begin --<br /><br />"
                + replyMessage.getContent() + "<br /><br />-- original message end --<br />";
        this.replyMessage = replyMessage;
    }

    public SendMessageBean(long memberId) {
        this();
        numberOfMessagesLeft = MessagingClient.getNumberOfMessagesLeft(memberId);
    }

    public SendMessageBean() {
        this.messageHoneypotPosition = ((new Random()).nextInt(10)) % 2;
        this.recipientList = new ArrayList<>();
    }

    public void send(Member sender, String baseUri) throws MessageLimitExceededException, ReplyingToManyException {
        //TODO COLAB-2620: do we need this?
//        if (messageHoneypot != null && !messageHoneypot.isEmpty()) {
//            _log.info("Message was not sent because honeypot was filled - text: " + messageContent + " honeypot: "
//                    + messageHoneypot);
//            //trick bot into thinking message was sent
//            messagingBean.messageSent();
//            return;
//        }

        List<Long> recipientIds = IdListUtil.getIdsFromString(userIdsRecipients);

        MessagingClient.checkLimitAndSendMessage(HtmlUtil.cleanAll(subject),
                HtmlUtil.cleanSome(messageContent, baseUri), sender.getUserId(), recipientIds);
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

    public String getMessageHoneypot() {
        return messageHoneypot;
    }

    public void setMessageHoneypot(String messageHoneypot) {
        this.messageHoneypot = messageHoneypot;
    }

    public int getMessageHoneypotPosition() {
        return messageHoneypotPosition;
    }

    public List<Member> getRecipientList() {
        return recipientList;
    }

    public List<Long> getRecipientIdList() {
        List<Long> recipientIds = new ArrayList<>();
        for (Member recipient : recipientList) {
            recipientIds.add(recipient.getId_());
        }
        return recipientIds;
    }

    public int getNumberOfMessagesLeft() {
        return numberOfMessagesLeft;
    }
}

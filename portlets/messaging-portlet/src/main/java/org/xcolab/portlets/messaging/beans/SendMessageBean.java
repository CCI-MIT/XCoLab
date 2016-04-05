package org.xcolab.portlets.messaging.beans;

import com.ext.portlet.messaging.MessageUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;
import org.xcolab.utils.HtmlUtil;

import javax.mail.internet.AddressException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SendMessageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<User> allUsers;
    private final List<User> recipientList;
    private final int messageHoneypotPosition;
    private String userIdsRecipients;
    private String subject;
    private String messageContent;
    private MessagingBean messagingBean;
    private MessageBean replyMessage;
    //honeypot is a field supposed to be left blank by humans, and to be filled in by bots, in order to protect from spam.
    private String messageHoneypot;

    public SendMessageBean(MessageBean replyMessage) throws SystemException, PortalException {
        this();
        this.userIdsRecipients = String.valueOf(replyMessage.getFrom().getUserId());
        this.recipientList.add(replyMessage.getFrom());

        this.subject = "RE: " + replyMessage.getSubject();
        this.messageContent = "<br /><br />-- original message begin --<br /><br />"
                + replyMessage.getContent() + "<br /><br />-- original message end --<br />";
        this.replyMessage = replyMessage;
    }

    public SendMessageBean() throws SystemException {
        this.messageHoneypotPosition = ((new Random()).nextInt(10)) % 2;

        this.allUsers = UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE);
        this.recipientList = new ArrayList<>();
    }

    public List<User> getUsersList() {
        return allUsers;
    }

    public boolean send(User sender, String baseUri)
            throws AddressException, SystemException, PortalException, MailEngineException,
            UnsupportedEncodingException {
        //TODO: do we need this?
//        if (messageHoneypot != null && !messageHoneypot.isEmpty()) {
//            _log.info("Message was not sent because honeypot was filled - text: " + messageContent + " honeypot: "
//                    + messageHoneypot);
//            //trick bot into thinking message was sent
//            messagingBean.messageSent();
//            return;
//        }

        List<Long> recipientIds = new ArrayList<>();

        for (String recipientId : userIdsRecipients.split(",")) {
            if (!recipientId.trim().equals("")) {
                recipientIds.add(Long.parseLong(recipientId));
            }
        }

        return MessageUtil.checkLimitAndSendMessage(subject,
                HtmlUtil.cleanSome(messageContent, baseUri), sender, recipientIds);
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

    public void setMessagingBean(MessagingBean messagingBean) throws PortalException, SystemException {
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

    public List<User> getRecipientList() {
        return recipientList;
    }
}

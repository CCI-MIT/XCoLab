package com.ext.portlet.messaging;

import com.ext.portlet.NoSuchConfigurationAttributeException;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;

import org.apache.commons.lang.StringEscapeUtils;
import org.joda.time.DateTime;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.legacy.enums.MessageConstants;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.utils.MessageLimitManager;
import org.xcolab.utils.TemplateReplacementUtil;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public final class MessageUtil {

    private static final Log _log = LogFactoryUtil.getLog(MessageUtil.class);

    private MessageUtil() {
    }

    public static int countMessages(long userId, MessageType type) {

        switch (type) {
            case INBOX:
                return MessagingClient.getMessageCountForUser(userId, false);
            case ARCHIVED:
                return MessagingClient.getMessageCountForUser(userId, true);
            case SENT:
                return MessagingClient.getSentMessageCountForUser(userId);
            default:
                return 0;
        }
    }

    public static List<Message> getMessages(long userId, int pagerStart, int pagerNext, MessageType type) {
        switch (type) {
            case INBOX:
                return MessagingClient.getMessagesForUser(pagerStart, pagerNext, userId, false);
            case ARCHIVED:
                return MessagingClient.getMessagesForUser(pagerStart, pagerNext, userId, true);
            case SENT:
                return MessagingClient.getSentMessagesForUser(pagerStart, pagerNext, userId);
            default:
                return Collections.emptyList();
        }
    }

    public static boolean checkLimitAndSendMessage(String subject, String content,
            User fromUser, Collection<Long> recipientIds)
            throws AddressException, PortalException, MailEngineException,
            SystemException, UnsupportedEncodingException {
        Long fromId = fromUser.getUserId();
        synchronized (MessageLimitManager.getMutex(fromId)) {
            // Send a validation problem mail to patrick if the daily limit is reached for a user
            if (!MessageLimitManager.canSendMessages(recipientIds.size(), fromUser)) {
                _log.warn("User exceeded validation limit " + fromId);

                // Only send the email once in 24h!
                if (MessageLimitManager.shouldSendValidationErrorMessage(fromUser)) {
                    recipientIds.clear();
                    recipientIds.add(1011659L); //patrick
                    sendMessage("VALIDATION PROBLEM  " + subject, "VALIDATION PROBLEM  " + content,
                            fromId, fromId, recipientIds);
                }
                return false;
            }

            sendMessage(subject, content, fromId, fromId, recipientIds);
            return true;
        }
    }

    public static void sendMessage(String subject, String content, Long fromId,
            Long replyToId, Collection<Long> recipientIds)
            throws MailEngineException, AddressException,
            UnsupportedEncodingException, SystemException, PortalException {
        long nextId = CounterLocalServiceUtil.increment("com.ext.portlet.model.Message");
        Message message = new Message();
        message.setMessageId(nextId);
        message.setSubject(StringEscapeUtils.unescapeXml(subject));
        message.setContent(content.replaceAll("\n", ""));
        message.setFromId(fromId);
        message.setCreateDate(new Timestamp(DateTime.now().getMillis()));
        message.setRepliesTo(replyToId);
        MessagingClient.createMessage(message);
        for (long user : recipientIds) {
            createRecipient(nextId, user);
            if (getMessagingPreferences(user).getEmailOnReceipt()) {
                copyRecipient(user, message);
            }
        }
    }

    private static void createRecipient(long messageId, long recipientId) throws SystemException {
        long nextId = CounterLocalServiceUtil.increment("com.ext.portlet.model.MessageRecipientStatus");
        MessagingClient.createRecipient(messageId, nextId, recipientId);
    }

    public static MessagingUserPreferences getMessagingPreferences(long userId) throws SystemException {
        MessagingUserPreferences prefs = MessagingUserPreferencesLocalServiceUtil.findByUser(userId);
        if (prefs == null) {
            long nextId = CounterLocalServiceUtil.increment(MessagingUserPreferencesLocalServiceUtil.class.getName());
            prefs = MessagingUserPreferencesLocalServiceUtil.createMessagingUserPreferences(nextId);
            prefs.setEmailOnReceipt(true);
            prefs.setEmailOnSend(false);
            prefs.setUserId(userId);
            prefs.setEmailOnActivity(true);
            prefs.setEmailActivityDailyDigest(true);
            MessagingUserPreferencesLocalServiceUtil.addMessagingUserPreferences(prefs);
        }

        return prefs;
    }

    private static void copyRecipient(Long userId, Message m)
            throws SystemException, PortalException, AddressException, MailEngineException,
            UnsupportedEncodingException {
        User from = UserLocalServiceUtil.getUser(m.getFromId());
        User to = UserLocalServiceUtil.getUser(userId);
        String subject = m.getSubject();
        if (subject.length() < 3) {
            subject = MessageConstants.EMAIL_MESSAGE_SUBJECT.replace(
                    MessageConstants.EMAIL_MESSAGE_VAR_USER, from.getScreenName());
            subject = TemplateReplacementUtil.replacePlatformConstants(subject);
        }
        String message = TemplateReplacementUtil.replacePlatformConstants(
                MessageConstants.EMAIL_MESSAGE_TEMPLATE.replace(
                        MessageConstants.EMAIL_MESSAGE_VAR_USER, from.getScreenName())
                        .replace(MessageConstants.EMAIL_MESSAGE_VAR_URL, createMessageURL(m))
                        .replace(MessageConstants.EMAIL_MESSAGE_VAR_SUBJECT, m.getSubject())
                        .replace(MessageConstants.EMAIL_MESSAGE_VAR_MESSAGE,
                                m.getContent()));

        InternetAddress fromEmail = TemplateReplacementUtil.getAdminFromEmailAddress();
        InternetAddress toEmail = new InternetAddress(to.getEmailAddress());
        EmailClient.sendEmail(fromEmail.getAddress(), toEmail.getAddress(), subject, message, true, fromEmail.getAddress());
    }

    private static String createMessageURL(Message m)
            throws SystemException, NoSuchConfigurationAttributeException {
        String home = ConfigurationAttributeKey.COLAB_URL.getStringValue();
        return home + MessageConstants.EMAIL_MESSAGE_URL_TEMPLATE + m.getMessageId();
    }
}

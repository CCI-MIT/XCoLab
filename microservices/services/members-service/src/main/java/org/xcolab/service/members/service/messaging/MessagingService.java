package org.xcolab.service.members.service.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.util.TemplateReplacementUtilPlaceholder;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.service.members.domain.member.MemberDao;
import org.xcolab.service.members.domain.messaging.MessageDao;
import org.xcolab.service.members.exceptions.MessageLimitExceededException;
import org.xcolab.service.members.exceptions.MessageRecipientException;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.exceptions.ReferenceResolutionException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MessagingService {

    private static final Logger log = LoggerFactory.getLogger(MessagingService.class);

    private final MessageDao messageDao;
    private final MemberDao memberDao;
    private final MessageLimitManager messageLimitManager;
    private final MessagingUserPreferencesService messagingUserPreferencesService;

    @Autowired
    public MessagingService(MessageDao messageDao, MemberDao memberDao,
            MessageLimitManager messageLimitManager,
            MessagingUserPreferencesService messagingUserPreferencesService) {
        this.messageDao = messageDao;
        this.memberDao = memberDao;
        this.messageLimitManager = messageLimitManager;
        this.messagingUserPreferencesService = messagingUserPreferencesService;
    }

    public Message sendMessage(Message message, Collection<Long> recipientIds, boolean checkLimit)
            throws MessageLimitExceededException {
        if (checkLimit) {
            Long fromId = message.getFromId();
            synchronized (messageLimitManager.getMutex(fromId)) {
                // Send a validation problem mail to patrick if the daily limit is reached for a user
                if (!messageLimitManager.canSendMessages(recipientIds.size(), fromId)) {
                    log.warn("User exceeded validation limit {}", fromId);

                    // Only send the email once in 24h!
                    if (messageLimitManager.shouldSendValidationErrorMessage(fromId)) {
                        Message validationErrorMessage = new Message();
                        validationErrorMessage.setSubject("VALIDATION PROBLEM  " + message.getSubject());
                        validationErrorMessage.setContent("VALIDATION PROBLEM  " + message.getContent());
                        validationErrorMessage.setFromId(fromId);
                        validationErrorMessage.setCreateDate(Timestamp.from(Instant.now()));
                        validationErrorMessage.setRepliesTo(message.getRepliesTo());
                        recipientIds.clear();
                        recipientIds.add(1011659L); //patrick
                        sendMessage(validationErrorMessage, recipientIds);
                    }
                    throw new MessageLimitExceededException(fromId);
                }

                return sendMessage(message, recipientIds);
            }
        } else {
            return sendMessage(message, recipientIds);
        }
    }

    private Message sendMessage(Message messageBean, Collection<Long> recipientIds) {
        if (messageBean.getSubject().isEmpty()) {
            messageBean.setSubject("(No Subject)");
        }
        final Message message = messageDao.createMessage(messageBean).orElseThrow(
                () -> new InternalException("Could not retrieve id of created message"));

        final Long messageId = message.getMessageId();
        if (recipientIds.isEmpty()) {
            messageDao.delete(messageId);
            throw MessageRecipientException.empty(messageId);
        }
        final Set<Long> recipientsFound = new HashSet<>();
        for (long memberId : recipientIds) {
            memberDao.getMember(memberId).ifPresent((recipientMember) -> {
                messageDao.createMessageRecipient(messageId, memberId);
                if (messagingUserPreferencesService.getByMemberId(memberId).getEmailOnReceipt()) {
                    copyRecipient(recipientMember, message);
                }
                recipientsFound.add(memberId);
            });

        }
        if (recipientsFound.size() < recipientIds.size()) {
            if (recipientsFound.isEmpty()) {
                messageDao.delete(messageId);
            }
            throw MessageRecipientException.notFound(messageId, recipientsFound, recipientIds);
        }

        return message;
    }

    private void copyRecipient(Member recipient, Message m) {
        Member from = memberDao.getMember(m.getFromId())
                .orElseThrow(() -> ReferenceResolutionException
                        .toObject(Member.class, m.getFromId())
                        .fromObject(Message.class, m.getMessageId()));

        String subject = m.getSubject();
        if (subject.length() < 3) {
            subject = MessageConstants.EMAIL_MESSAGE_SUBJECT.replace(
                    MessageConstants.EMAIL_MESSAGE_VAR_USER, from.getScreenName());
            subject = TemplateReplacementUtilPlaceholder.replacePlatformConstants(subject);
        }
        String message = TemplateReplacementUtilPlaceholder.replacePlatformConstants(
                MessageConstants.EMAIL_MESSAGE_TEMPLATE.replace(
                        MessageConstants.EMAIL_MESSAGE_VAR_USER, from.getScreenName())
                        .replace(MessageConstants.EMAIL_MESSAGE_VAR_URL, createMessageURL(m))
                        .replace(MessageConstants.EMAIL_MESSAGE_VAR_PROFILE_URL,
                                createProfileEditUrl(recipient))
                        .replace(MessageConstants.EMAIL_MESSAGE_VAR_SUBJECT, m.getSubject())
                        .replace(MessageConstants.EMAIL_MESSAGE_VAR_MESSAGE,
                                m.getContent()));

        String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
        EmailClient.sendEmail(fromEmail, recipient.getEmailAddress(), subject, message, true,
                fromEmail);
    }

    private static String createMessageURL(Message m) {
        String home = ConfigurationAttributeKey.COLAB_URL.get();
        return home + MessageConstants.EMAIL_MESSAGE_URL_TEMPLATE + m.getMessageId();
    }

    private static String createProfileEditUrl(Member member) {
        String home = ConfigurationAttributeKey.COLAB_URL.get();
        return home + "/members/profile/" + member.getId_() + "/edit";
    }

}

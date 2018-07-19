package org.xcolab.service.members.service.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.util.TemplateReplacementUtilPlaceholder;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.service.members.domain.member.MemberDao;
import org.xcolab.service.members.domain.messaging.MessageDao;
import org.xcolab.service.members.exceptions.MessageLimitExceededException;
import org.xcolab.service.members.exceptions.MessageRecipientException;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.commons.exceptions.ReferenceResolutionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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

    public Message sendMessage(Message message, Collection<Long> recipientIds, boolean checkLimit, String threadId)
            throws MessageLimitExceededException {
        if (checkLimit) {
            Long fromId = message.getFromId();
            synchronized (messageLimitManager.getMutex(fromId)) {

                if (!messageLimitManager.canSendMessages(recipientIds.size(), fromId)) {
                    log.warn("User exceeded validation limit {}", fromId);

                    if (!messageLimitManager.wasReportedRecently(fromId)) {
                        final List<String> reportRecipients =
                                ConfigurationAttributeKey.MESSAGING_SPAM_ALERT_EMAILS.get();
                        if (!reportRecipients.isEmpty()) {
                            Member from = memberDao.getMember(fromId)
                                    .orElseThrow(() -> new InternalException(
                                            "Sender does not exist: " + fromId));
                            final String subject = String.format("[%s] Member %s exceeded message limit",
                                    ConfigurationAttributeKey.COLAB_NAME.get(), from.getScreenName());
                            final String content = String.format(
                                    "Member %s tried to send the message \"%s\" to %d members "
                                            + "but has exceeded the daily limit of %d messages.",
                                    from.getScreenName(), message.getSubject(), recipientIds.size(),
                                    messageLimitManager.getMessageLimit(fromId));

                            final String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
                            EmailClient.sendEmail(fromEmail,ConfigurationAttributeKey.COLAB_NAME.get(), reportRecipients,
                                    subject, content, false, fromEmail,ConfigurationAttributeKey.COLAB_NAME.get(),message.getFromId());
                        }
                    }
                    throw new MessageLimitExceededException(fromId);
                }

                return sendMessage(message, recipientIds, threadId);
            }
        } else {
            return sendMessage(message, recipientIds, threadId);
        }
    }

    private Message sendMessage(Message messageBean, Collection<Long> recipientIds, String threadId) {
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
                 if ("-1".equals(threadId)){
                     messageDao.createMessageRecipient(messageId, memberId, String.valueOf(messageId)+"-"+String.valueOf(memberId));
                 }else{
                     messageDao.createMessageRecipient(messageId, memberId, threadId);
                 }
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
        EmailClient.sendEmail(fromEmail,ConfigurationAttributeKey.COLAB_NAME.get(), recipient.getEmailAddress(), subject, message, true,
                fromEmail,ConfigurationAttributeKey.COLAB_NAME.get(),m.getMessageId());
    }

    private static String createMessageURL(Message m) {
        String home = PlatformAttributeKey.COLAB_URL.get();
        return home + MessageConstants.EMAIL_MESSAGE_URL_TEMPLATE + m.getMessageId();
    }

    private static String createProfileEditUrl(Member member) {
        String home = PlatformAttributeKey.COLAB_URL.get();
        return home + "/members/profile/" + member.getId_() + "/edit";
    }

}

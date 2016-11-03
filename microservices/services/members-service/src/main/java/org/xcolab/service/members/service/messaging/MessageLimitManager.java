package org.xcolab.service.members.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.MessagingUserPreferences;
import org.xcolab.service.members.domain.messaging.MessageDao;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for handling verification if user is allowed to send
 * messages or maybe daily limit has been reached.
 * 
 * @author janusz
 */
@Service
public class MessageLimitManager {

    private static final Map<Long, Object> mutexes = new HashMap<>();
	private static final int MESSAGES_DAILY_LIMIT = 15;
	private static final Map<Long, LocalDateTime> lastValidationDateMap = new HashMap<>();

    private final MessagingUserPreferencesService messagingUserPreferencesService;
    private final MessageDao messageDao;

    @Autowired
    private MessageLimitManager(MessagingUserPreferencesService messagingUserPreferencesService,
            MessageDao messageDao) {
        this.messagingUserPreferencesService = messagingUserPreferencesService;
        this.messageDao = messageDao;
    }

    /**
     * Method responsible for checking if user is allowed to send given number
     * of messages.
     * 
     * @param messagesToSend
     *            number of messages that user wants to send
     */
    public boolean canSendMessages(int messagesToSend, long memberId) {
        synchronized (getMutex(memberId)) {

            MessagingUserPreferences messagingPreferences = messagingUserPreferencesService.getByMemberId(memberId);

            int messagesLimit;
            if (messagingPreferences.getDailyMessageLimit() != null) {
                messagesLimit = messagingPreferences.getDailyMessageLimit();
            } else {
                messagesLimit = MESSAGES_DAILY_LIMIT;
            }

            final Timestamp yesterday = Timestamp.from(Instant.now().minus(Duration.ofDays(1)));

            long count = messageDao.countByGiven(memberId, null, null, null, yesterday);

            return messagesLimit >= count + messagesToSend;
        }
    }

    public int getNumberOfMessagesLeft(long memberId) {
        synchronized (getMutex(memberId)) {

            MessagingUserPreferences messagingPreferences = messagingUserPreferencesService.getByMemberId(memberId);

            int messagesLimit;
            if (messagingPreferences.getDailyMessageLimit() != null) {
                messagesLimit = messagingPreferences.getDailyMessageLimit();
            } else {
                messagesLimit = MESSAGES_DAILY_LIMIT;
            }

            final Timestamp yesterday = Timestamp.from(Instant.now().minus(Duration.ofDays(1)));

            int count = messageDao.countByGiven(memberId, null, null, null, yesterday);

            return  messagesLimit - count;
        }
    }

    public synchronized Object getMutex(long senderId) {
        Object mutex = mutexes.get(senderId);
        if (mutex == null) {
            mutex = new Object();
            mutexes.put(senderId, mutex);
        }
        return mutex;
    }

	public boolean shouldSendValidationErrorMessage(Long memberId) {
        final LocalDateTime lastEmailSendDate = lastValidationDateMap.get(memberId);

        final LocalDateTime now = LocalDateTime.now();
        if (lastEmailSendDate == null || LocalDateTime.from(lastEmailSendDate).plusHours(24).isBefore(now)) {
            lastValidationDateMap.put(memberId, now);
            return true;
        } else {
            return false;
        }
	}
}

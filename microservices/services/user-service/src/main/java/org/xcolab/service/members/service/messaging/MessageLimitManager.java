package org.xcolab.service.members.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.service.members.domain.member.UserDao;
import org.xcolab.service.members.domain.messaging.MessageDao;
import org.xcolab.service.members.service.role.RoleService;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private static final int MESSAGES_DAILY_LIMIT_FIRST_DAYS = 2;
	private static final Map<Long, LocalDateTime> lastValidationDateMap = new HashMap<>();

    private final MessagingUserPreferenceService messagingUserPreferencesService;
    private final MessageDao messageDao;
    private final RoleService roleService;
    private final UserDao memberDao;

    @Autowired
    private MessageLimitManager(MessagingUserPreferenceService messagingUserPreferencesService,
            MessageDao messageDao, RoleService roleService, UserDao memberDao) {
        this.messagingUserPreferencesService = messagingUserPreferencesService;
        this.messageDao = messageDao;
        this.roleService = roleService;
        this.memberDao = memberDao;
    }

    /**
     * Method responsible for checking if user is allowed to send given number
     * of messages.
     * 
     * @param messagesToSend
     *            number of messages that user wants to send
     */
    public boolean canSendMessages(int messagesToSend, long userId) {
        synchronized (getMutex(userId)) {
            return getNumberOfMessagesLeft(userId) >= messagesToSend;
        }
    }

    public int getNumberOfMessagesLeft(long userId) {
        int messageLimit = getMessageLimit(userId);

        final Timestamp yesterday = Timestamp.from(Instant.now().minus(Duration.ofDays(1)));

        int count = messageDao.countByGiven(userId, null, null, null, yesterday);

        return messageLimit - count;
    }

    public int getMessageLimit(long userId) {
        if (roleService.isAdmin(userId)) {
            return Integer.MAX_VALUE;
        }

        MessagingUserPreferenceWrapper messagingPreferences = messagingUserPreferencesService.getByuserId(userId);

        int messagesLimit;
        if (messagingPreferences.getDailyMessageLimit() != null) {
            messagesLimit = messagingPreferences.getDailyMessageLimit();
        } else {
            final UserWrapper member = memberDao.getUser(userId)
                    .orElseThrow(() -> new IllegalStateException("Can't check limit for member "
                            + userId + ": member does not exist"));

            if (isMoreThan2DaysOld(member)) {
                messagesLimit = MESSAGES_DAILY_LIMIT;
            } else {
                messagesLimit = MESSAGES_DAILY_LIMIT_FIRST_DAYS;
            }
        }
        return messagesLimit + messagesReceivedInLast24Hours(userId);
    }

    private int messagesReceivedInLast24Hours(long userId) {
        final Timestamp yesterday = Timestamp.from(Instant.now().minus(Duration.ofDays(1)));

        return messageDao.countByGiven(null, userId, null, null, yesterday);
    }

    private boolean isMoreThan2DaysOld(UserWrapper member) {
        return member.getCreatedAt().toInstant()
                .plus(2, ChronoUnit.DAYS).isBefore(Instant.now());
    }

    public synchronized Object getMutex(long senderId) {
        return mutexes.computeIfAbsent(senderId, k -> new Object());
    }

	public boolean wasReportedRecently(Long userId) {
        final LocalDateTime lastEmailSendDate = lastValidationDateMap.get(userId);

        final LocalDateTime now = LocalDateTime.now();
        if (lastEmailSendDate == null || lastEmailSendDate.plusHours(24).isBefore(now)) {
            lastValidationDateMap.put(userId, now);
            return false;
        } else {
            return true;
        }
	}
}

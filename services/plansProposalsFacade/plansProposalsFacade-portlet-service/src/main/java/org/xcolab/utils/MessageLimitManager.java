package org.xcolab.utils;

import com.liferay.portal.model.User;
import org.joda.time.DateTime;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.MessagingUserPreferences;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for handling verification if user is allowed to send
 * messages or maybe daily limit has been reached.
 * 
 * @author janusz
 */
public final class MessageLimitManager {

    private static final Map<Long, Object> mutexes = new HashMap<>();
	private static final int MESSAGES_DAILY_LIMIT = 15;
	private static final Map<User, Date> lastValidationDateMap = new HashMap<>();

    private MessageLimitManager() { }

    /**
     * Method responsible for checking if user is allowed to send given number
     * of messages.
     * 
     * @param messagesToSend
     *            number of messages that user wants to send
     */
    public static boolean canSendMessages(int messagesToSend, long memberId) {
        synchronized (getMutex(memberId)) {

            MessagingUserPreferences messagingPreferences = MessagingClient.getMessagingPreferencesForMember(memberId);

            int messagesLimit;
            if (messagingPreferences.getDailyMessageLimit() != null) {
                messagesLimit = messagingPreferences.getDailyMessageLimit();
            } else {
                messagesLimit = MESSAGES_DAILY_LIMIT;
            }

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -1);
            final Date yesterday = c.getTime();

            long count = MessagingClient.getMessageCountForMemberSinceDate(memberId, yesterday);

            return messagesLimit >= count + messagesToSend;
        }
    }

    public static synchronized Object getMutex(long senderId) {
        Object mutex = mutexes.get(senderId);
        if (mutex == null) {
            mutex = new Object();
            mutexes.put(senderId, mutex);
        }
        return mutex;
    }

	public static boolean shouldSendValidationErrorMessage(User user) {
		if (user == null) {
			return false;
		}
        final Date lastEmailSendDate = lastValidationDateMap.get(user);

        // Send mail if the last email send was over 24h ago
        if (lastEmailSendDate == null || new DateTime(lastEmailSendDate).plusHours(24).isBeforeNow()) {
            lastValidationDateMap.put(user, new Date());
            return true;
        } else {
            return false;
        }
	}
}

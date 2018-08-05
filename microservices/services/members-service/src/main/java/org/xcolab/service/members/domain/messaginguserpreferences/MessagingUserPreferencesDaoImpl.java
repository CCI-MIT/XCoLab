package org.xcolab.service.members.domain.messaginguserpreferences;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.MessagingUserPreferences;

import java.util.Optional;

import static org.xcolab.model.Tables.MESSAGING_USER_PREFERENCES;

@Repository
public class MessagingUserPreferencesDaoImpl implements MessagingUserPreferencesDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public Optional<MessagingUserPreferences> get(long id) {
        final Record record = dslContext.select()
                .from(MESSAGING_USER_PREFERENCES)
                .where(MESSAGING_USER_PREFERENCES.MESSAGING_PREFERENCES_ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(MessagingUserPreferences.class));
    }

    @Override
    public Optional<MessagingUserPreferences> getByuserId(long userId) {
        final Record record = dslContext.select()
                .from(MESSAGING_USER_PREFERENCES)
                .where(MESSAGING_USER_PREFERENCES.USER_ID.eq(userId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(MessagingUserPreferences.class));
    }

    @Override
    public Optional<MessagingUserPreferences> create(MessagingUserPreferences messagingUserPreferences) {
        final Record record = dslContext
                .insertInto(MESSAGING_USER_PREFERENCES)
                .set(MESSAGING_USER_PREFERENCES.USER_ID, messagingUserPreferences.getUserId())
                .set(MESSAGING_USER_PREFERENCES.EMAIL_ON_SEND,
                        messagingUserPreferences.getEmailOnSend())
                .set(MESSAGING_USER_PREFERENCES.EMAIL_ON_RECEIPT,
                        messagingUserPreferences.getEmailOnReceipt())
                .set(MESSAGING_USER_PREFERENCES.EMAIL_ON_ACTIVITY,
                        messagingUserPreferences.getEmailOnActivity())
                .set(MESSAGING_USER_PREFERENCES.EMAIL_ACTIVITY_DAILY_DIGEST,
                        messagingUserPreferences.getEmailActivityDailyDigest())
                .returning(MESSAGING_USER_PREFERENCES.MESSAGING_PREFERENCES_ID)
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        messagingUserPreferences.setMessagingPreferencesId(
                record.getValue(MESSAGING_USER_PREFERENCES.MESSAGING_PREFERENCES_ID));
        return Optional.of(messagingUserPreferences);
    }

    @Override
    public boolean update(MessagingUserPreferences messagingUserPreferences) {
        return dslContext.update(MESSAGING_USER_PREFERENCES)
                .set(MESSAGING_USER_PREFERENCES.USER_ID, messagingUserPreferences.getUserId())
                .set(MESSAGING_USER_PREFERENCES.EMAIL_ON_SEND,
                        messagingUserPreferences.getEmailOnSend())
                .set(MESSAGING_USER_PREFERENCES.EMAIL_ON_RECEIPT,
                        messagingUserPreferences.getEmailOnReceipt())
                .set(MESSAGING_USER_PREFERENCES.EMAIL_ON_ACTIVITY,
                        messagingUserPreferences.getEmailOnActivity())
                .set(MESSAGING_USER_PREFERENCES.EMAIL_ACTIVITY_DAILY_DIGEST,
                        messagingUserPreferences.getEmailActivityDailyDigest())
                .where(MESSAGING_USER_PREFERENCES.MESSAGING_PREFERENCES_ID
                        .eq(messagingUserPreferences.getMessagingPreferencesId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long messagingPreferencesId) {
        return dslContext.deleteFrom(MESSAGING_USER_PREFERENCES)
                .where(MESSAGING_USER_PREFERENCES.MESSAGING_PREFERENCES_ID.eq(messagingPreferencesId))
                .execute() > 0;
    }
}

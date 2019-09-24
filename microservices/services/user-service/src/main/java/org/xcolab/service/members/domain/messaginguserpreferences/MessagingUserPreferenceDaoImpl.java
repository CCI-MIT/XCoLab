package org.xcolab.service.members.domain.messaginguserpreferences;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;

import java.util.Optional;

import static org.xcolab.model.Tables.MESSAGING_USER_PREFERENCE;

@Repository
public class MessagingUserPreferenceDaoImpl implements MessagingUserPreferenceDao {

    private final DSLContext dslContext;

    @Autowired
    public MessagingUserPreferenceDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Optional<MessagingUserPreferenceWrapper> get(long id) {
        final Record record = dslContext.select()
                .from(MESSAGING_USER_PREFERENCE)
                .where(MESSAGING_USER_PREFERENCE.ID.eq(id))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(MessagingUserPreferenceWrapper.class));
    }

    @Override
    public Optional<MessagingUserPreferenceWrapper> getByUserId(long userId) {
        final Record record = dslContext.select()
                .from(MESSAGING_USER_PREFERENCE)
                .where(MESSAGING_USER_PREFERENCE.USER_ID.eq(userId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(MessagingUserPreferenceWrapper.class));
    }

    @Override
    public Optional<MessagingUserPreferenceWrapper> create(
            MessagingUserPreferenceWrapper messagingUserPreferences) {
        final Record record = dslContext
                .insertInto(MESSAGING_USER_PREFERENCE)
                .set(MESSAGING_USER_PREFERENCE.USER_ID, messagingUserPreferences.getUserId())
                .set(MESSAGING_USER_PREFERENCE.EMAIL_ON_SEND,
                        messagingUserPreferences.getEmailOnSend())
                .set(MESSAGING_USER_PREFERENCE.EMAIL_ON_RECEIPT,
                        messagingUserPreferences.getEmailOnReceipt())
                .set(MESSAGING_USER_PREFERENCE.EMAIL_ON_ACTIVITY,
                        messagingUserPreferences.getEmailOnActivity())
                .set(MESSAGING_USER_PREFERENCE.EMAIL_ACTIVITY_DAILY_DIGEST,
                        messagingUserPreferences.getEmailActivityDailyDigest())
                .returning(MESSAGING_USER_PREFERENCE.ID)
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        messagingUserPreferences.setId(
                record.getValue(MESSAGING_USER_PREFERENCE.ID));
        return Optional.of(messagingUserPreferences);
    }

    @Override
    public boolean update(MessagingUserPreferenceWrapper messagingUserPreferences) {
        return dslContext.update(MESSAGING_USER_PREFERENCE)
                .set(MESSAGING_USER_PREFERENCE.USER_ID, messagingUserPreferences.getUserId())
                .set(MESSAGING_USER_PREFERENCE.EMAIL_ON_SEND,
                        messagingUserPreferences.getEmailOnSend())
                .set(MESSAGING_USER_PREFERENCE.EMAIL_ON_RECEIPT,
                        messagingUserPreferences.getEmailOnReceipt())
                .set(MESSAGING_USER_PREFERENCE.EMAIL_ON_ACTIVITY,
                        messagingUserPreferences.getEmailOnActivity())
                .set(MESSAGING_USER_PREFERENCE.EMAIL_ACTIVITY_DAILY_DIGEST,
                        messagingUserPreferences.getEmailActivityDailyDigest())
                .where(MESSAGING_USER_PREFERENCE.ID.eq(messagingUserPreferences.getId()))
                .execute() > 0;
    }

    @Override
    public boolean delete(long messagingPreferencesId) {
        return dslContext.deleteFrom(MESSAGING_USER_PREFERENCE)
                .where(MESSAGING_USER_PREFERENCE.ID.eq(messagingPreferencesId))
                .execute() > 0;
    }
}

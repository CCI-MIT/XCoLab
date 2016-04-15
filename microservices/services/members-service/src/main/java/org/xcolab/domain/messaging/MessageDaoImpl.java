package org.xcolab.domain.messaging;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.exceptions.NotFoundException;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.model.tables.pojos.User_;
import org.xcolab.wrappers.MessageReceived;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.xcolab.model.Tables.MESSAGE;
import static org.xcolab.model.Tables.MESSAGE_RECIPIENT_STATUS;
import static org.xcolab.model.Tables.USER_;

@Repository
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public Message getMessage(long messageId) throws NotFoundException {
        final Record record = dslContext.select()
                .from(MESSAGE)
                .where(MESSAGE.MESSAGE_ID.eq(messageId))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException("Message with id " + messageId + "does not exist");
        }
        return record.into(Message.class);
    }

    @Override
    public int countByGiven(Long senderId, Long recipientId, Boolean isArchived, Boolean isOpened) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(MESSAGE).getQuery();

        if (recipientId != null || isArchived != null || isOpened != null) {
            query.addJoin(MESSAGE_RECIPIENT_STATUS, MESSAGE.MESSAGE_ID.eq(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID));
        }
        if (senderId != null) {
            query.addConditions(MESSAGE.FROM_ID.eq(senderId));
        }
        if (recipientId != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(recipientId));
        }
        if (isArchived != null) {
            final byte isArchivedByte = (byte) (isArchived ? 1 : 0);
            query.addConditions(MESSAGE_RECIPIENT_STATUS.ARCHIVED.eq(isArchivedByte));
        }
        if (isOpened != null) {
            final byte isOpenedByte = (byte) (isOpened ? 1 : 0);
            query.addConditions(MESSAGE_RECIPIENT_STATUS.OPENED.eq(isOpenedByte));
        }

        return query.fetchOne(0, Integer.class);
    }

    @Override
    public int countBySendingUser(long userId) {
        return dslContext.selectCount()
                .from(MESSAGE)
                .where(MESSAGE.FROM_ID.equal(userId))
                .fetchOne(0, Integer.class);
    }

    @Override
    public int countByReceivingUserArchived(long userId, boolean isArchived) {
        final byte isArchivedByte = (byte) (isArchived ? 1 : 0);
        return dslContext.selectCount()
                .from(MESSAGE_RECIPIENT_STATUS)
                .where(MESSAGE_RECIPIENT_STATUS.USER_ID.equal(userId)
                        .and(MESSAGE_RECIPIENT_STATUS.ARCHIVED.equal(isArchivedByte))
                ).fetchOne(0, Integer.class);
    }

    @Override
    public int countByReceivingUserOpened(long userId, boolean isOpened) {
        final byte isOpenedByte = (byte) (isOpened ? 1 : 0);
        return dslContext.selectCount()
                .from(MESSAGE_RECIPIENT_STATUS)
                .where(MESSAGE_RECIPIENT_STATUS.USER_ID.equal(userId)
                        .and(MESSAGE_RECIPIENT_STATUS.OPENED.equal(isOpenedByte))
                ).fetchOne(0, Integer.class);
    }

    @Override
    public List<Message> findByGiven(int startRecord, int limitRecord,
            Long senderId, Long recipientId, Boolean isArchived, Boolean isOpened) {
        final SelectQuery<Record> query = dslContext.select()
                .from(MESSAGE).getQuery();

        if (recipientId != null || isArchived != null || isOpened != null) {
            query.addJoin(MESSAGE_RECIPIENT_STATUS, MESSAGE.MESSAGE_ID.eq(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID));
        }
        if (senderId != null) {
            query.addConditions(MESSAGE.FROM_ID.eq(senderId));
        }
        if (recipientId != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(recipientId));
        }
        if (isArchived != null) {
            final byte isArchivedByte = (byte) (isArchived ? 1 : 0);
            query.addConditions(MESSAGE_RECIPIENT_STATUS.ARCHIVED.eq(isArchivedByte));
        }
        if (isOpened != null) {
            final byte isOpenedByte = (byte) (isOpened ? 1 : 0);
            query.addConditions(MESSAGE_RECIPIENT_STATUS.OPENED.eq(isOpenedByte));
        }
        query.addOrderBy(MESSAGE.CREATE_DATE.desc());
        query.addLimit(startRecord, limitRecord);
        return query.fetchInto(recipientId != null ? MessageReceived.class : Message.class);
    }

    @Override
    public List<Message> findByReceivingUserArchived(int startRecord, int limitRecord, long userId, boolean isArchived) {
        final byte isArchivedByte = (byte) (isArchived ? 1 : 0);
        return dslContext.select()
                .from(MESSAGE)
                .join(MESSAGE_RECIPIENT_STATUS).on(MESSAGE.MESSAGE_ID.equal(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID))
                .where(MESSAGE_RECIPIENT_STATUS.USER_ID.equal(userId)
                        .and(MESSAGE_RECIPIENT_STATUS.ARCHIVED.equal(isArchivedByte))
                ).orderBy(MESSAGE.CREATE_DATE.desc())
                .limit(startRecord, limitRecord).fetchInto(MessageReceived.class);
    }

    @Override
    public List<Message> findByReceivingUser(int startRecord, int limitRecord, long userId) {
        return dslContext.select()
                .from(MESSAGE)
                .join(MESSAGE_RECIPIENT_STATUS).on(MESSAGE.MESSAGE_ID.equal(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID))
                .where(MESSAGE_RECIPIENT_STATUS.USER_ID.equal(userId))
                .orderBy(MESSAGE.CREATE_DATE.desc())
                .limit(startRecord, limitRecord).fetchInto(MessageReceived.class);
    }

    @Override
    public List<Message> findBySendingUser(int startRecord, int limitRecord, long userId) {
        return dslContext.select()
                .from(MESSAGE)
                .where(MESSAGE.FROM_ID.equal(userId))
                .orderBy(MESSAGE.CREATE_DATE.desc())
                .limit(startRecord, limitRecord).fetchInto(Message.class);
    }

    @Override
    public List<User_> getRecipients(long messageId) {
        return dslContext.select()
                .from(MESSAGE_RECIPIENT_STATUS)
                .join(USER_).on(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(USER_.USER_ID))
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId))
                .fetchInto(User_.class);
    }

    @Override
    public void setArchived(long messageId, long memberId, boolean isArchived) {
        final byte isArchivedByte = (byte) (isArchived ? 1 : 0);
        dslContext.update(MESSAGE_RECIPIENT_STATUS)
                .set(MESSAGE_RECIPIENT_STATUS.ARCHIVED, isArchivedByte)
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId)
                        .and(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(memberId)))
                .execute();
    }

    @Override
    public void setOpened(long messageId, long memberId, boolean isOpened) {
        final byte isOpenedByte = (byte) (isOpened ? 1 : 0);
        dslContext.update(MESSAGE_RECIPIENT_STATUS)
                .set(MESSAGE_RECIPIENT_STATUS.OPENED, isOpenedByte)
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId)
                        .and(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(memberId)))
                .execute();
    }

    @Override
    public void createMessage(long messageId, long senderId, long repliesToId, String subject, String content) {
        //TODO: messageId is currently still generated by liferay
        dslContext.insertInto(MESSAGE)
                .set(MESSAGE.MESSAGE_ID, messageId)
                .set(MESSAGE.FROM_ID, senderId)
                .set(MESSAGE.REPLIES_TO, repliesToId)
                .set(MESSAGE.SUBJECT, subject)
                .set(MESSAGE.CONTENT, content)
                .set(MESSAGE.CREATE_DATE, new Timestamp(Instant.now().toEpochMilli()))
                .execute();
    }

    @Override
    public void createMessageRecipient(long messageRecipientStatusId, long messageId, long recipientId) {
        //TODO: messageRecipientStatusId is currently still generated by liferay
        dslContext.insertInto(MESSAGE_RECIPIENT_STATUS)
                .set(MESSAGE_RECIPIENT_STATUS.MESSAGE_RECIPIENT_ID, messageRecipientStatusId)
                .set(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID, messageId)
                .set(MESSAGE_RECIPIENT_STATUS.USER_ID, recipientId)
                .set(MESSAGE_RECIPIENT_STATUS.ARCHIVED, (byte) 0)
                .set(MESSAGE_RECIPIENT_STATUS.OPENED, (byte) 0)
                .execute();
    }
}

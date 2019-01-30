package org.xcolab.service.members.domain.messaging;

import org.apache.commons.lang3.ArrayUtils;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.user.pojo.wrapper.MessageWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.SortColumn;
import org.xcolab.model.tables.records.MessageRecord;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.wrappers.MessageReceived;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MESSAGE;
import static org.xcolab.model.Tables.MESSAGE_RECIPIENT_STATUS;
import static org.xcolab.model.Tables.USER;

@Repository
public class MessageDaoImpl implements MessageDao {

    private final DSLContext dslContext;

    @Autowired
    public MessageDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public MessageWrapper getMessage(long messageId) throws NotFoundException {
        final Record record = dslContext.select()
                .from(MESSAGE)
                .where(MESSAGE.ID.eq(messageId))
                .fetchOne();
        if (record == null) {
            throw new NotFoundException("Message with id " + messageId + "does not exist");
        }
        return record.into(MessageWrapper.class);
    }

    @Override
    public List<MessageWrapper> getFullConversation(long messageId, String threadId) throws NotFoundException {
        List<MessageWrapper> messageList;
        if (threadId != null) {
            // There is thread info, get all the thread
            messageList = dslContext.select(MESSAGE.fields())
                    .from(MESSAGE
                        .join(MESSAGE_RECIPIENT_STATUS)
                        .on(MESSAGE.ID.eq(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID)))
                    .where(
                        MESSAGE_RECIPIENT_STATUS.THREAD_ID.eq(threadId)
                    )
                    .orderBy(MESSAGE.CREATED_AT.desc())
                    .fetchInto(MessageWrapper.class);

            if (messageList.isEmpty()) {
                throw new NotFoundException("Thread " + threadId + "does not exist or does not contain messages with id <= "+messageId);
            }
        } else {
            messageList = dslContext.select()
                    .from(MESSAGE)
                    .where(MESSAGE.ID.eq(messageId))
                    .fetchInto(MessageWrapper.class);
            if (messageList.isEmpty()) {
                throw new NotFoundException("Message with id " + messageId + "does not exist");
            }
        }
        return messageList;
    }

    @Override
    public int countByGiven(Long senderId, Long recipientId, Boolean isArchived, Boolean isOpened, Timestamp sinceDate) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(MESSAGE)
                .getQuery();

        if (recipientId != null || isArchived != null || isOpened != null) {
            query.addJoin(MESSAGE_RECIPIENT_STATUS,
                    MESSAGE.ID.eq(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID));
        }

        if (senderId != null) {
            query.addConditions(MESSAGE.FROM_ID.eq(senderId));
        }
        if (recipientId != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(recipientId));
        }
        if (isArchived != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.ARCHIVED.eq(isArchived));
        }
        if (isOpened != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.OPENED.eq(isOpened));
        }
        if (sinceDate != null) {
            query.addConditions(MESSAGE.CREATED_AT.gt(sinceDate));
        }

        return query.fetchOne(0, Integer.class);
    }

    @Override
    public List<MessageWrapper> findByGiven(PaginationHelper paginationHelper,
            Long senderId, Long recipientId, Boolean isArchived, Boolean isOpened, Timestamp sinceDate) {
        final Field<?>[] fields = ArrayUtils.addAll(MESSAGE.fields(),
                MESSAGE_RECIPIENT_STATUS.OPENED, MESSAGE_RECIPIENT_STATUS.ARCHIVED,
                MESSAGE_RECIPIENT_STATUS.THREAD_ID);
        final SelectQuery<Record> query = dslContext.select(fields)
                .from(MESSAGE)
                .join(MESSAGE_RECIPIENT_STATUS).on(MESSAGE.ID.eq(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID))
                .getQuery();

        if (senderId != null) {
            query.addConditions(MESSAGE.FROM_ID.eq(senderId));
        }
        if (recipientId != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(recipientId));
        }
        if (isArchived != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.ARCHIVED.eq(isArchived));
        }
        if (isOpened != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.OPENED.eq(isOpened));
        }
        if (sinceDate != null) {
            query.addConditions(MESSAGE.CREATED_AT.gt(sinceDate));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "senderId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? MESSAGE.FROM_ID.asc()
                            : MESSAGE.FROM_ID.desc());
                    break;
                case "recipientId":
                    query.addOrderBy(sortColumn.isAscending()
                            ? MESSAGE_RECIPIENT_STATUS.USER_ID.asc()
                            : MESSAGE_RECIPIENT_STATUS.USER_ID.desc());
                    break;
                case "isArchived":
                    query.addOrderBy(sortColumn.isAscending()
                            ? MESSAGE_RECIPIENT_STATUS.ARCHIVED.asc()
                            : MESSAGE_RECIPIENT_STATUS.ARCHIVED.desc());
                    break;
                case "isOpened":
                    query.addOrderBy(sortColumn.isAscending()
                            ? MESSAGE_RECIPIENT_STATUS.OPENED.asc()
                            : MESSAGE_RECIPIENT_STATUS.OPENED.desc());
                    break;
                case "createdAt":
                    query.addOrderBy(sortColumn.isAscending()
                            ? MESSAGE.CREATED_AT.asc()
                            : MESSAGE.CREATED_AT.desc());
                    break;
                default:

            }
        }
        query.addOrderBy(MESSAGE.CREATED_AT.desc());
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());

        return query.fetchInto(recipientId != null ? MessageReceived.class : MessageWrapper.class);
    }

    @Override
    public List<UserWrapper> getRecipients(long messageId) {
        return dslContext.select(USER.fields())
                .from(MESSAGE_RECIPIENT_STATUS)
                .join(USER).on(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(USER.ID))
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId))
                .fetchInto(UserWrapper.class);
    }

    @Override
    public List<String> getThreads(long messageId) {
        return dslContext.select()
                .from(MESSAGE_RECIPIENT_STATUS)
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId))
                .fetch().getValues(MESSAGE_RECIPIENT_STATUS.THREAD_ID);
    }


    @Override
    public boolean setArchived(long messageId, long userId, boolean isArchived) {
        return dslContext.update(MESSAGE_RECIPIENT_STATUS)
                .set(MESSAGE_RECIPIENT_STATUS.ARCHIVED, isArchived)
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId)
                        .and(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(userId)))
                .execute() > 0;
    }

    @Override
    public boolean setOpened(long messageId, long userId, boolean isOpened) {
        return dslContext.update(MESSAGE_RECIPIENT_STATUS)
                .set(MESSAGE_RECIPIENT_STATUS.OPENED, isOpened)
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId)
                        .and(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(userId)))
                .execute() > 0;
    }

    @Override
    public Optional<MessageWrapper> createMessage(MessageWrapper message) {
        final MessageRecord record = dslContext.insertInto(MESSAGE)
                .set(MESSAGE.FROM_ID, message.getFromId())
                .set(MESSAGE.REPLIES_TO, message.getRepliesTo())
                .set(MESSAGE.SUBJECT, message.getSubject())
                .set(MESSAGE.CONTENT, message.getContent())
                .set(MESSAGE.CREATED_AT, DSL.currentTimestamp())
                .returning(MESSAGE.ID)
                .fetchOne();
        if (record != null) {
            message.setId(record.getValue(MESSAGE.ID));
            return Optional.of(message);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void createMessageRecipient(long messageId, long recipientId, String threadId) {
        dslContext.insertInto(MESSAGE_RECIPIENT_STATUS)
            .set(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID, messageId)
            .set(MESSAGE_RECIPIENT_STATUS.USER_ID, recipientId)
            .set(MESSAGE_RECIPIENT_STATUS.THREAD_ID, threadId)
            .set(MESSAGE_RECIPIENT_STATUS.ARCHIVED, false)
            .set(MESSAGE_RECIPIENT_STATUS.OPENED, false)
            .execute();
    }

    @Override
    public boolean delete(long messageId) {
        dslContext.deleteFrom(MESSAGE_RECIPIENT_STATUS)
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId)).execute();
        return dslContext.deleteFrom(MESSAGE)
                .where(MESSAGE.ID.eq(messageId)).execute() > 0;
    }
}

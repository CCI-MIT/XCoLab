package org.xcolab.service.members.domain.messaging;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.model.tables.records.MessageRecord;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.wrappers.MessageReceived;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.commons.SortColumn;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.MEMBER;
import static org.xcolab.model.Tables.MESSAGE;
import static org.xcolab.model.Tables.MESSAGE_RECIPIENT_STATUS;

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
    public List<Message> getFullConversation(long messageId, String threadId) throws NotFoundException {
        List<Message> messageList;
        if (threadId != null) {
            // There is thread info, get all the thread
            messageList = dslContext.select()
                    .from(MESSAGE
                        .join(MESSAGE_RECIPIENT_STATUS)
                        .on(MESSAGE.MESSAGE_ID.eq(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID)))
                    .where(
                        MESSAGE_RECIPIENT_STATUS.THREAD_ID.eq(threadId)
                    )
                    .orderBy(MESSAGE.CREATE_DATE.desc())
                    .fetchInto(Message.class);

            if (messageList.isEmpty()) {
                throw new NotFoundException("Thread " + threadId + "does not exist or does not contain messages with id <= "+messageId);
            }
        } else {
            messageList = dslContext.select()
                    .from(MESSAGE)
                    .where(MESSAGE.MESSAGE_ID.eq(messageId))
                    .fetchInto(Message.class);
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
                    MESSAGE.MESSAGE_ID.eq(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID));
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
            query.addConditions(MESSAGE.CREATE_DATE.gt(sinceDate));
        }

        return query.fetchOne(0, Integer.class);
    }

    @Override
    public List<Message> findByGiven(PaginationHelper paginationHelper,
            Long senderId, Long recipientId, Boolean isArchived, Boolean isOpened, Timestamp sinceDate) {
        final SelectQuery<Record> query = dslContext.select()
                .from(MESSAGE)
                .join(MESSAGE_RECIPIENT_STATUS).on(MESSAGE.MESSAGE_ID.eq(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID))
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
            query.addConditions(MESSAGE.CREATE_DATE.gt(sinceDate));
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
                            ? MESSAGE_RECIPIENT_STATUS.MESSAGE_RECIPIENT_ID.asc()
                            : MESSAGE_RECIPIENT_STATUS.MESSAGE_RECIPIENT_ID.desc());
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
                            ? MESSAGE.CREATE_DATE.asc()
                            : MESSAGE.CREATE_DATE.desc());
                    break;
                default:

            }
        }
        query.addOrderBy(MESSAGE.CREATE_DATE.desc());
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());

        return query.fetchInto(recipientId != null ? MessageReceived.class : Message.class);
    }

    @Override
    public List<Member> getRecipients(long messageId) {
        return dslContext.select()
                .from(MESSAGE_RECIPIENT_STATUS)
                .join(MEMBER).on(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(MEMBER.ID))
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId))
                .fetchInto(Member.class);
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
    public Optional<Message> createMessage(Message message) {
        final MessageRecord record = dslContext.insertInto(MESSAGE)
                .set(MESSAGE.FROM_ID, message.getFromId())
                .set(MESSAGE.REPLIES_TO, message.getRepliesTo())
                .set(MESSAGE.SUBJECT, message.getSubject())
                .set(MESSAGE.CONTENT, message.getContent())
                .set(MESSAGE.CREATE_DATE, DSL.currentTimestamp())
                .returning(MESSAGE.MESSAGE_ID)
                .fetchOne();
        if (record != null) {
            message.setMessageId(record.getValue(MESSAGE.MESSAGE_ID));
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
                .where(MESSAGE.MESSAGE_ID.eq(messageId)).execute() > 0;
    }
}

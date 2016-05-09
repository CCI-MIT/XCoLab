package org.xcolab.service.members.domain.messaging;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.wrappers.MessageReceived;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

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
            query.addConditions(MESSAGE_RECIPIENT_STATUS.ARCHIVED.eq(isArchived));
        }
        if (isOpened != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.OPENED.eq(isOpened));
        }

        return query.fetchOne(0, Integer.class);
    }

    @Override
    public List<Message> findByGiven(PaginationHelper paginationHelper,
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
            query.addConditions(MESSAGE_RECIPIENT_STATUS.ARCHIVED.eq(isArchived));
        }
        if (isOpened != null) {
            query.addConditions(MESSAGE_RECIPIENT_STATUS.OPENED.eq(isOpened));
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
                case "createDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? MESSAGE.CREATE_DATE.asc()
                            : MESSAGE.CREATE_DATE.desc());
                    break;

            }
        }
        query.addOrderBy(MESSAGE.CREATE_DATE.desc());
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord());

        return query.fetchInto(recipientId != null ? MessageReceived.class : Message.class);
    }

    @Override
    public List<Member> getRecipients(long messageId) {
        return dslContext.select()
                .from(MESSAGE_RECIPIENT_STATUS)
                .join(MEMBER).on(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(MEMBER.ID_))
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId))
                .fetchInto(Member.class);
    }

    @Override
    public boolean setArchived(long messageId, long memberId, boolean isArchived) {
        return dslContext.update(MESSAGE_RECIPIENT_STATUS)
                .set(MESSAGE_RECIPIENT_STATUS.ARCHIVED, isArchived)
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId)
                        .and(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(memberId)))
                .execute() > 0;
    }

    @Override
    public boolean setOpened(long messageId, long memberId, boolean isOpened) {
        return dslContext.update(MESSAGE_RECIPIENT_STATUS)
                .set(MESSAGE_RECIPIENT_STATUS.OPENED, isOpened)
                .where(MESSAGE_RECIPIENT_STATUS.MESSAGE_ID.eq(messageId)
                        .and(MESSAGE_RECIPIENT_STATUS.USER_ID.eq(memberId)))
                .execute() > 0;
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
                .set(MESSAGE_RECIPIENT_STATUS.ARCHIVED, false)
                .set(MESSAGE_RECIPIENT_STATUS.OPENED, false)
                .execute();
    }
}

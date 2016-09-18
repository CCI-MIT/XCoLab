package org.xcolab.service.comments.domain.thread;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.model.tables.CommentTable;
import org.xcolab.model.tables.pojos.Thread;
import org.xcolab.model.tables.records.ThreadRecord;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CATEGORY;
import static org.xcolab.model.Tables.COMMENT;
import static org.xcolab.model.Tables.THREAD;
import static org.xcolab.model.Tables.PROPOSAL;

@Repository
public class ThreadDaoImpl implements ThreadDao {

    private final DSLContext dslContext;

    @Autowired
    public ThreadDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public List<Thread> findByGiven(PaginationHelper paginationHelper, Long authorId,
            Long categoryId, Long groupId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(THREAD)
                .getQuery();

        if (groupId != null) {
            query.addJoin(CATEGORY, CATEGORY.CATEGORY_ID.eq(THREAD.CATEGORY_ID));
            query.addConditions(CATEGORY.GROUP_ID.eq(groupId));
        }
        if (authorId != null) {
            query.addConditions(THREAD.AUTHOR_ID.eq(authorId));
        }
        if (categoryId != null) {
            query.addConditions(THREAD.CATEGORY_ID.eq(categoryId));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "title":
                    query.addOrderBy(sortColumn.isAscending()
                            ? THREAD.TITLE.asc() : THREAD.TITLE.desc());
                    break;
                case "comments":
                    Field<Object> commentCount = this.dslContext.selectCount()
                            .from(COMMENT)
                            .where(COMMENT.THREAD_ID.equal(THREAD.THREAD_ID))
                            .asField("commentCount");
                    query.addSelect(commentCount);
                    query.addSelect(THREAD.fields());
                    query.addOrderBy(sortColumn.isAscending()
                            ? commentCount.asc() : commentCount.desc());
                    break;
                case "activityDate":
                    final CommentTable a = COMMENT.as("a");
                    final CommentTable b = COMMENT.as("b");
                    query.addSelect(THREAD.fields());
                    query.addJoin(a, a.THREAD_ID.eq(THREAD.THREAD_ID));
                    query.addJoin(b, JoinType.LEFT_OUTER_JOIN, b.THREAD_ID.eq(THREAD.THREAD_ID)
                            .and(a.CREATE_DATE.lt(b.CREATE_DATE)));
                    query.addConditions(b.CREATE_DATE.isNull());
                    query.addOrderBy(sortColumn.isAscending()
                            ? a.CREATE_DATE.asc() : a.CREATE_DATE.desc());
                    break;
                //TODO: this currently sorts by id, not name
                case "activityAuthor":
                    final CommentTable a1 = COMMENT.as("a");
                    final CommentTable b1 = COMMENT.as("b");
                    query.addSelect(THREAD.fields());
                    query.addJoin(a1, a1.THREAD_ID.eq(THREAD.THREAD_ID));
                    query.addJoin(b1, JoinType.LEFT_OUTER_JOIN, b1.THREAD_ID.eq(THREAD.THREAD_ID)
                            .and(a1.CREATE_DATE.lt(b1.CREATE_DATE)));
                    query.addConditions(b1.CREATE_DATE.isNull());
                    query.addOrderBy(sortColumn.isAscending()
                            ? a1.AUTHOR_ID.asc() : a1.AUTHOR_ID.desc());
                    break;
                case "createDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? THREAD.CREATE_DATE.asc() : THREAD.CREATE_DATE.desc());
                    break;
            }
        }
        query.addConditions(THREAD.DELETED_DATE.isNull());
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Thread.class);
    }

    @Override
    public Thread get(long threadId) throws NotFoundException {
        final Record commentRecord = dslContext.select()
                .from(THREAD)
                .where(THREAD.THREAD_ID.eq(threadId))
                .fetchOne();
        if (commentRecord == null) {
            throw new NotFoundException("Comment with id " + threadId + " does not exist");
        }
        return commentRecord.into(Thread.class);
    }

    @Override
    public boolean exists(long threadId) {
        return dslContext.fetchExists(DSL.select()
                .from(THREAD)
                .where(THREAD.THREAD_ID.eq(threadId)));
    }

    @Override
    public Optional<Long> getSharedColabThreadId(long threadId) {
        final Record1<Long> record = dslContext.select(THREAD.SHARED_COLAB_THREAD_ID)
                .from(THREAD)
                .where(THREAD.THREAD_ID.eq(threadId))
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(Long.class));
    }

    @Override
    public boolean update(Thread thread) {
        return dslContext.update(THREAD)
                .set(THREAD.CREATE_DATE, thread.getCreateDate())
                .set(THREAD.AUTHOR_ID, thread.getAuthorId())
                .set(THREAD.DELETED_DATE, thread.getDeletedDate())
                .set(THREAD.TITLE, thread.getTitle())
                .set(THREAD.IS_QUIET, thread.getIsQuiet())
                .set(THREAD.CATEGORY_ID, thread.getCategoryId())
                .where(THREAD.THREAD_ID.equal(thread.getThreadId()))
                .execute() > 0;
    }

    @Override
    public Thread create(Thread thread) {
        final ThreadRecord threadRecord = dslContext.insertInto(THREAD)
                .set(THREAD.CREATE_DATE, thread.getCreateDate())
                .set(THREAD.AUTHOR_ID, thread.getAuthorId())
                .set(THREAD.DELETED_DATE, thread.getDeletedDate())
                .set(THREAD.TITLE, thread.getTitle())
                .set(THREAD.IS_QUIET, thread.getIsQuiet())
                .set(THREAD.CATEGORY_ID, thread.getCategoryId())
                .returning(THREAD.THREAD_ID)
                .fetchOne();
        if (threadRecord != null) {
            thread.setThreadId(threadRecord.getThreadId());
            return thread;
        }
        return null;
    }

    @Override
    public Timestamp lastActivityDate(long threadId) throws NotFoundException {
        final CommentTable a = COMMENT.as("a");
        final CommentTable b = COMMENT.as("b");
        final List<Timestamp> timestamps = dslContext.select(a.CREATE_DATE)
                .from(THREAD)
                .join(a).on(a.THREAD_ID.eq(THREAD.THREAD_ID)
                        .and(THREAD.THREAD_ID.eq(threadId))
                        .and(a.DELETED_DATE.isNull()))
                .join(b, JoinType.LEFT_OUTER_JOIN).on(b.THREAD_ID.eq(THREAD.THREAD_ID)
                        .and(b.DELETED_DATE.isNull())
                        .and(a.CREATE_DATE.lt(b.CREATE_DATE)))
                .where(b.CREATE_DATE.isNull())
                .orderBy(a.COMMENT_ID.desc())
                .fetchInto(Timestamp.class);
        if (timestamps.isEmpty()) {
            throw new NotFoundException();
        }
        return timestamps.get(0);
    }

    @Override
    public long lastActivityAuthor(long threadId) throws NotFoundException {
        final CommentTable a = COMMENT.as("a");
        final CommentTable b = COMMENT.as("b");
        final List<Long> timestamps = dslContext.select(a.AUTHOR_ID)
                .from(THREAD)
                .join(a).on(a.THREAD_ID.eq(THREAD.THREAD_ID)
                        .and(THREAD.THREAD_ID.eq(threadId))
                        .and(a.DELETED_DATE.isNull()))
                .join(b, JoinType.LEFT_OUTER_JOIN).on(b.THREAD_ID.eq(THREAD.THREAD_ID)
                        .and(b.DELETED_DATE.isNull())
                        .and(a.CREATE_DATE.lt(b.CREATE_DATE)))
                .where(b.CREATE_DATE.isNull())
                .orderBy(a.COMMENT_ID.desc())
                .fetchInto(Long.class);
        if (timestamps.isEmpty()) {
            throw new NotFoundException();
        }
        return timestamps.get(0);
    }

    @Override
    public Optional<Long> getProposalIdForThread(long threadId){
        Record record = dslContext
                .select(PROPOSAL.PROPOSAL_ID)
                .from(THREAD)
                .innerJoin(PROPOSAL).on(PROPOSAL.DISCUSSION_ID.eq(THREAD.THREAD_ID))
                .where(THREAD.THREAD_ID.eq(threadId)).fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(Long.class));
    }
}

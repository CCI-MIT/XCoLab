package org.xcolab.service.comment.domain.thread;

import org.jooq.DSLContext;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.SelectField;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.comment.pojo.tables.pojos.Comment;
import org.xcolab.client.comment.pojo.tables.pojos.Thread;
import org.xcolab.commons.SortColumn;
import org.xcolab.model.tables.CommentTable;
import org.xcolab.model.tables.records.ThreadRecord;
import org.xcolab.service.comment.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

import static org.xcolab.model.Tables.CATEGORY;
import static org.xcolab.model.Tables.COMMENT;
import static org.xcolab.model.Tables.THREAD;

@Repository
public class ThreadDaoImpl implements ThreadDao {

    private final DSLContext dslContext;

    @Autowired
    public ThreadDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public List<IThread> findByGiven(PaginationHelper paginationHelper, Long authorUserId,
            Long categoryId, Long groupId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(THREAD)
                .getQuery();

        if (groupId != null) {
            query.addJoin(CATEGORY, CATEGORY.ID.eq(THREAD.CATEGORY_ID));
            query.addConditions(CATEGORY.GROUP_ID.eq(groupId));
        }
        if (authorUserId != null) {
            query.addConditions(THREAD.AUTHOR_USER_ID.eq(authorUserId));
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
                case "comments": {
                    final CommentTable comment = COMMENT.as("comment");
                    query.addSelect((SelectField<?>[]) THREAD.fields());
                    query.addJoin(comment, comment.THREAD_ID.eq(THREAD.ID)
                            .and(comment.DELETED_AT.isNull()));
                    query.addGroupBy(THREAD.ID);
                    query.addOrderBy(sortColumn.isAscending()
                            ? DSL.count(comment.ID).asc()
                            : DSL.count(comment.ID).desc());
                    break;
                }
                case "activityDate": {
                    final CommentTable comment = COMMENT.as("comment");
                    query.addSelect((SelectField<?>[]) THREAD.fields());
                    query.addJoin(comment, comment.THREAD_ID.eq(THREAD.ID)
                            .and(comment.DELETED_AT.isNull()));
                    query.addGroupBy(THREAD.ID);
                    query.addOrderBy(sortColumn.isAscending()
                            ? DSL.max(comment.CREATED_AT).asc()
                            : DSL.max(comment.CREATED_AT).desc());
                    break;
                }
                //TODO COLAB-2601: this currently sorts by id, not name
                case "activityAuthor":
                    final CommentTable a1 = COMMENT.as("a");
                    final CommentTable b1 = COMMENT.as("b");
                    query.addSelect((SelectField<?>[]) THREAD.fields());
                    query.addJoin(a1, a1.ID.eq(THREAD.ID));
                    query.addJoin(b1, JoinType.LEFT_OUTER_JOIN, b1.ID.eq(THREAD.ID)
                            .and(a1.CREATED_AT.lt(b1.CREATED_AT)));
                    query.addConditions(b1.CREATED_AT.isNull());
                    query.addOrderBy(sortColumn.isAscending()
                            ? a1.AUTHOR_USER_ID.asc() : a1.AUTHOR_USER_ID.desc());
                    break;
                case "createdAt":
                    query.addOrderBy(sortColumn.isAscending()
                            ? THREAD.CREATED_AT.asc() : THREAD.CREATED_AT.desc());
                    break;
            }
        }
        query.addConditions(THREAD.DELETED_AT.isNull());
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Thread.class);
    }

    @Override
    public IThread get(long id) throws NotFoundException {
        final Record commentRecord = dslContext.select()
                .from(THREAD)
                .where(THREAD.ID.eq(id))
                .fetchOne();
        if (commentRecord == null) {
            throw new NotFoundException("Comment with id " + id + " does not exist");
        }
        return commentRecord.into(Thread.class);
    }

    @Override
    public boolean exists(long id) {
        return dslContext.fetchExists(DSL.select()
                .from(THREAD)
                .where(THREAD.ID.eq(id)));
    }

    @Override
    public boolean update(IThread thread) {
        return dslContext.update(THREAD)
                .set(THREAD.CREATED_AT, thread.getCreatedAt())
                .set(THREAD.AUTHOR_USER_ID, thread.getAuthorUserId())
                .set(THREAD.DELETED_AT, thread.getDeletedAt())
                .set(THREAD.TITLE, thread.getTitle())
                .set(THREAD.IS_QUIET, thread.isIsQuiet())
                .set(THREAD.CATEGORY_ID, thread.getCategoryId())
                .where(THREAD.ID.equal(thread.getId()))
                .execute() > 0;
    }

    @Override
    public IThread create(IThread thread) {
        final ThreadRecord threadRecord = dslContext.insertInto(THREAD)
                .set(THREAD.CREATED_AT, thread.getCreatedAt())
                .set(THREAD.AUTHOR_USER_ID, thread.getAuthorUserId())
                .set(THREAD.DELETED_AT, thread.getDeletedAt())
                .set(THREAD.TITLE, thread.getTitle())
                .set(THREAD.IS_QUIET, thread.isIsQuiet())
                .set(THREAD.CATEGORY_ID, thread.getCategoryId())
                .returning(THREAD.ID)
                .fetchOne();
        if (threadRecord != null) {
            thread.setId(threadRecord.getId());
            return thread;
        }
        return null;
    }

    @Override
    public Optional<IComment> getLastComment(long threadId) {
        final Record record = dslContext.select()
                .from(COMMENT)
                .where(COMMENT.THREAD_ID.eq(threadId)
                        .and(COMMENT.DELETED_AT.isNull()))
                .orderBy(COMMENT.CREATED_AT.desc())
                .limit(1)
                .fetchOne();
        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(Comment.class));
    }

    @Override
    public boolean delete(Long threadId) {
        boolean result = dslContext.deleteFrom(COMMENT)
                .where(COMMENT.THREAD_ID.eq(threadId))
                .execute() > 0;
        result = result || dslContext.deleteFrom(THREAD)
                .where(THREAD.ID.eq(threadId))
                .execute() > 0;
        return result;
    }
}

package org.xcolab.service.comment.domain.comment;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.commons.SortColumn;
import org.xcolab.client.comment.pojo.tables.pojos.Comment;
import org.xcolab.model.tables.records.CommentRecord;
import org.xcolab.service.comment.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.Collection;
import java.util.List;

import static org.xcolab.model.Tables.COMMENT;

@Repository
public class CommentDaoImpl implements CommentDao {

    private final DSLContext dslContext;

    @Autowired
    public CommentDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public int countByGiven(Long authorUserId, Collection<Long> threadIds) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(COMMENT)
                .getQuery();
        if (authorUserId != null) {
            query.addConditions(COMMENT.ID.eq(authorUserId));
        }
        if (threadIds != null) {
            query.addConditions(COMMENT.THREAD_ID.in(threadIds));
        }
        query.addConditions(COMMENT.DELETED_AT.isNull());
        return query.fetchOne().into(Integer.class);
    }

    @Override
    public List<IComment> findByGiven(PaginationHelper paginationHelper,
            Long authorUserId, Collection<Long> threadIds, boolean includeDeleted) {
        final SelectQuery<Record> query = dslContext.select()
                .from(COMMENT)
                .getQuery();

        if (authorUserId != null) {
            query.addConditions(COMMENT.AUTHOR_USER_ID.eq(authorUserId));
        }
        if (threadIds != null) {
            query.addConditions(COMMENT.THREAD_ID.in(threadIds));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "createdAt":
                    query.addOrderBy(sortColumn.isAscending()
                            ? COMMENT.CREATED_AT.asc() : COMMENT.CREATED_AT.desc());
                    break;
                //missing default case
                default:
                    // add default case
                    break;

            }
        }
        if (!includeDeleted) {
            query.addConditions(COMMENT.DELETED_AT.isNull());
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Comment.class);
    }

    @Override
    public IComment get(long commentId) throws NotFoundException {
        final Record commentRecord = dslContext.select()
                .from(COMMENT)
                .where(COMMENT.ID.eq(commentId))
                .fetchOne();
        if (commentRecord == null) {
            throw new NotFoundException("Comment with id " + commentId + " does not exist");
        }
        return commentRecord.into(Comment.class);
    }

    @Override
    public boolean exists(long commentId) {
        return dslContext.fetchExists(DSL.select()
                .from(COMMENT)
                .where(COMMENT.ID.eq(commentId)));
    }

    @Override
    public boolean update(IComment comment) {

        return dslContext.update(COMMENT)
                .set(COMMENT.THREAD_ID, comment.getThreadId())
                .set(COMMENT.CREATED_AT, comment.getCreatedAt())
                .set(COMMENT.UPDATED_AT, comment.getUpdatedAt())
                .set(COMMENT.AUTHOR_USER_ID, comment.getAuthorUserId())
                .set(COMMENT.CONTENT, comment.getContent())
                .set(COMMENT.DELETED_AT, comment.getDeletedAt())
                .where(COMMENT.ID.equal(comment.getId()))
                .execute() > 0;
    }

    @Override
    public IComment create(IComment comment) {
        final CommentRecord commentRecord = dslContext.insertInto(COMMENT)
                .set(COMMENT.THREAD_ID, comment.getThreadId())
                .set(COMMENT.CREATED_AT, comment.getCreatedAt())
                .set(COMMENT.UPDATED_AT, comment.getUpdatedAt())
                .set(COMMENT.AUTHOR_USER_ID, comment.getAuthorUserId())
                .set(COMMENT.CONTENT, comment.getContent())
                .set(COMMENT.DELETED_AT, comment.getDeletedAt())
                .returning(COMMENT.ID)
                .fetchOne();
        if (commentRecord != null) {
            comment.setId(commentRecord.getId());
            return comment;
        }
        return null;
    }
}

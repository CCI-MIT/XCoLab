package org.xcolab.service.comments.domain.comment;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.commons.SortColumn;
import org.xcolab.model.tables.pojos.Comment;
import org.xcolab.model.tables.records.CommentRecord;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.Collection;
import java.util.List;

import static org.xcolab.model.Tables.COMMENT;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public int countByGiven(Long authorUserid, Collection<Long> threadIds) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(COMMENT)
                .getQuery();
        if (authorUserid != null) {
            query.addConditions(COMMENT.ID.eq(authorUserid));
        }
        if (threadIds != null) {
            query.addConditions(COMMENT.THREAD_ID.in(threadIds));
        }
        query.addConditions(COMMENT.DELETED_AT.isNull());
        return query.fetchOne().into(Integer.class);
    }

    @Override
    public List<Comment> findByGiven(PaginationHelper paginationHelper,
            Long authorUserid, Collection<Long> threadIds, boolean includeDeleted) {
        final SelectQuery<Record> query = dslContext.select()
                .from(COMMENT)
                .getQuery();

        if (authorUserid != null) {
            query.addConditions(COMMENT.ID.eq(authorUserid));
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
            }
        }
        if (!includeDeleted) {
            query.addConditions(COMMENT.DELETED_AT.isNull());
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Comment.class);
    }

    @Override
    public Comment get(long commentId) throws NotFoundException {
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
    public boolean update(Comment comment) {

        return dslContext.update(COMMENT)
                .set(COMMENT.THREAD_ID, comment.getThreadId())
                .set(COMMENT.CREATED_AT, comment.getCreatedAt())
                .set(COMMENT.UPDATED_AT, comment.getUpdatedAt())
                .set(COMMENT.ID, comment.getAuthorUserId())
                .set(COMMENT.CONTENT, comment.getContent())
                .set(COMMENT.DELETED_AT, comment.getDeletedAt())
                .where(COMMENT.ID.equal(comment.getId()))
                .execute() > 0;
    }

    @Override
    public Comment create(Comment comment) {
        final CommentRecord commentRecord = dslContext.insertInto(COMMENT)
                .set(COMMENT.THREAD_ID, comment.getThreadId())
                .set(COMMENT.CREATED_AT, comment.getCreatedAt())
                .set(COMMENT.UPDATED_AT, comment.getUpdatedAt())
                .set(COMMENT.ID, comment.getAuthorUserId())
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

package org.xcolab.service.comments.domain.comment;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectQuery;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Comment;
import org.xcolab.model.tables.records.CommentRecord;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.util.List;

import static org.xcolab.model.Tables.COMMENT;
import static org.xcolab.model.Tables.PROPOSAL;
import static org.xcolab.model.Tables.PROPOSAL_2_PHASE;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public int countByGiven(Long authorId, Long threadId) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(COMMENT)
                .getQuery();
        if (authorId != null) {
            query.addConditions(COMMENT.AUTHOR_ID.eq(authorId));
        }
        if (threadId != null) {
            query.addConditions(COMMENT.THREAD_ID.eq(threadId));
        }
        query.addConditions(COMMENT.DELETED_DATE.isNull());
        return query.fetchOne().into(Integer.class);
    }

    @Override
    public int countByGiven(List<Long> threadIds) {
        final SelectQuery<Record1<Integer>> query = dslContext.selectCount()
                .from(COMMENT)
                .getQuery();
        if (threadIds == null || threadIds.isEmpty()) {
            return 0;
        }
        query.addConditions(COMMENT.THREAD_ID.in(threadIds));
        query.addConditions(COMMENT.DELETED_DATE.isNull());
        return query.fetchOne().into(Integer.class);
    }

    @Override
    public List<Comment> findByGiven(PaginationHelper paginationHelper,
            Long authorId, Long threadId, boolean includeDeleted) {
        final SelectQuery<Record> query = dslContext.select()
                .from(COMMENT)
                .getQuery();

        if (authorId != null) {
            query.addConditions(COMMENT.AUTHOR_ID.eq(authorId));
        }
        if (threadId != null) {
            query.addConditions(COMMENT.THREAD_ID.eq(threadId));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "createDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? COMMENT.CREATE_DATE.asc() : COMMENT.CREATE_DATE.desc());
                    break;
            }
        }
        if (!includeDeleted) {
            query.addConditions(COMMENT.DELETED_DATE.isNull());
        }
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getCount());
        return query.fetchInto(Comment.class);
    }

    @Override
    public Comment get(long commentId) throws NotFoundException {
        final Record commentRecord = dslContext.select()
                .from(COMMENT)
                .where(COMMENT.COMMENT_ID.eq(commentId))
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
                .where(COMMENT.COMMENT_ID.eq(commentId)));
    }

    @Override
    public boolean update(Comment comment) {

        return dslContext.update(COMMENT)
                .set(COMMENT.THREAD_ID, comment.getThreadId())
                .set(COMMENT.CREATE_DATE, comment.getCreateDate())
                .set(COMMENT.MODIFIED_DATE, comment.getModifiedDate())
                .set(COMMENT.AUTHOR_ID, comment.getAuthorId())
                .set(COMMENT.CONTENT, comment.getContent())
                .set(COMMENT.DELETED_DATE, comment.getDeletedDate())
                .where(COMMENT.COMMENT_ID.equal(comment.getCommentId()))
                .execute() > 0;
    }

    @Override
    public Comment create(Comment comment) {
        final CommentRecord commentRecord = dslContext.insertInto(COMMENT)
                .set(COMMENT.THREAD_ID, comment.getThreadId())
                .set(COMMENT.CREATE_DATE, comment.getCreateDate())
                .set(COMMENT.MODIFIED_DATE, comment.getModifiedDate())
                .set(COMMENT.AUTHOR_ID, comment.getAuthorId())
                .set(COMMENT.CONTENT, comment.getContent())
                .set(COMMENT.DELETED_DATE, comment.getDeletedDate())
                .returning(COMMENT.COMMENT_ID)
                .fetchOne();
        if (commentRecord != null) {
            comment.setCommentId(commentRecord.getCommentId());
            return comment;
        }
        return null;
    }

    @Override
    public int countProposalCommentsByContestPhase(Long contestPhaseId) {
        return dslContext.selectCount()
                .from(COMMENT)
                .join(PROPOSAL).on(PROPOSAL.DISCUSSION_ID.eq(COMMENT.THREAD_ID))
                .join(PROPOSAL_2_PHASE).on(PROPOSAL_2_PHASE.PROPOSAL_ID.eq(PROPOSAL.PROPOSAL_ID))
                .where(PROPOSAL_2_PHASE.CONTEST_PHASE_ID.eq(contestPhaseId)
                        .and(COMMENT.DELETED_DATE.isNull()))
                .fetchOne().into(Integer.class);
    }
}
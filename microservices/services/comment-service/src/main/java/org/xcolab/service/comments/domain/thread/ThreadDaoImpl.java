package org.xcolab.service.comments.domain.thread;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Thread;
import org.xcolab.model.tables.records.CommentRecord;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.service.utils.PaginationHelper.SortColumn;

import java.util.List;

import static org.xcolab.model.Tables.COMMENT;
import static org.xcolab.model.Tables.THREAD;

@Repository
public class ThreadDaoImpl implements ThreadDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<Thread> findByGiven(PaginationHelper paginationHelper, Long authorId,
            Long categoryId) {
        final SelectQuery<Record> query = dslContext.select()
                .from(THREAD)
                .getQuery();

        if (authorId != null) {
            query.addConditions(THREAD.AUTHOR_ID.eq(authorId));
        }
        if (categoryId != null) {
            query.addConditions(THREAD.THREAD_ID.eq(categoryId));
        }

        for (SortColumn sortColumn : paginationHelper.getSortColumns()) {
            switch (sortColumn.getColumnName()) {
                case "createDate":
                    query.addOrderBy(sortColumn.isAscending()
                            ? THREAD.CREATE_DATE.asc() : THREAD.CREATE_DATE.desc());
                    break;
            }
        }
        query.addConditions(THREAD.DELETED_DATE.isNull());
        query.addLimit(paginationHelper.getStartRecord(), paginationHelper.getLimitRecord());
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
        final CommentRecord threadRecord = dslContext.insertInto(COMMENT)
                .set(THREAD.CREATE_DATE, thread.getCreateDate())
                .set(THREAD.AUTHOR_ID, thread.getAuthorId())
                .set(THREAD.DELETED_DATE, thread.getDeletedDate())
                .set(THREAD.TITLE, thread.getTitle())
                .set(THREAD.IS_QUIET, thread.getIsQuiet())
                .set(THREAD.CATEGORY_ID, thread.getCategoryId())
                .returning(THREAD.THREAD_ID)
                .fetchOne();
        if (threadRecord != null) {
            thread.setThreadId(threadRecord.getCommentId());
            return thread;
        }
        return null;
    }
}

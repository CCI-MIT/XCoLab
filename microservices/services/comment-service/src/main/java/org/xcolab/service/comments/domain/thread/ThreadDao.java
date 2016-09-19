package org.xcolab.service.comments.domain.thread;

import org.xcolab.model.tables.pojos.Thread;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ThreadDao {

    List<Thread> findByGiven(PaginationHelper paginationHelper, Long authorId, Long categoryId,
            Long groupId);

    Thread get(long threadId) throws NotFoundException;

    boolean exists(long threadId);

    Optional<Long> getSharedColabThreadId(long threadId);

    boolean update(Thread thread);

    Thread create(Thread thread);

    Timestamp lastActivityDate(long threadId) throws NotFoundException;

    long lastActivityAuthor(long threadId) throws NotFoundException;

    Optional<Long> getProposalIdForThread(long threadId);
}

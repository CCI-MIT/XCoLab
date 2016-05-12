package org.xcolab.service.comments.domain.thread;

import org.xcolab.model.tables.pojos.Thread;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ThreadDao {

    List<Thread> findByGiven(PaginationHelper paginationHelper, Long authorId, Long categoryId);

    Thread get(long threadId) throws NotFoundException;
    boolean update(Thread thread);
    Thread create(Thread thread);
}

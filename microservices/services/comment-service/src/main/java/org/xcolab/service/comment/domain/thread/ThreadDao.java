package org.xcolab.service.comment.domain.thread;

import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.service.comment.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface ThreadDao {

    List<IThread> findByGiven(PaginationHelper paginationHelper, Long authorUserId, Long categoryId,
            Long groupId);

    IThread get(long threadId) throws NotFoundException;

    boolean exists(long threadId);

    boolean update(IThread thread);

    IThread create(IThread thread);

    Optional<IComment> getLastComment(long threadId);

    boolean delete(Long threadId);
}

package org.xcolab.service.comment.domain.comment;

import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.service.comment.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.Collection;
import java.util.List;

public interface CommentDao {

    int countByGiven(Long authorUserId, Collection<Long> threadIds);

    List<IComment> findByGiven(PaginationHelper paginationHelper, Long authorUserId,
            Collection<Long> threadIds, boolean includeDeleted);

    IComment get(long commentId) throws NotFoundException;

    boolean exists(long commentId);

    boolean update(IComment comment);

    IComment create(IComment comment);
}

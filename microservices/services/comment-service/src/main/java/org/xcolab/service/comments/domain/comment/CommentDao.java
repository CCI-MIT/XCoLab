package org.xcolab.service.comments.domain.comment;

import org.xcolab.model.tables.pojos.Comment;
import org.xcolab.service.comments.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface CommentDao {

    int countByGiven(Long authorId, Long threadId);

    List<Comment> findByGiven(PaginationHelper paginationHelper, Long authorId, Long threadId,boolean includeDeleted);

    Comment get(long commentId) throws NotFoundException;

    boolean exists(long commentId);

    boolean update(Comment comment);

    Comment create(Comment comment);

    int countProposalCommentsByContestPhase(Long contestPhaseId);

    int countByGiven(List<Long> threadIds);
}

package org.xcolab.client.comment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.IComment;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class CommentClientMockImpl implements ICommentClient {

    @Override
    public List<IComment> listComments(Integer startRecord, Integer limitRecord, String sort,
            Long authorUserId, List<Long> threadIds, boolean includeDeleted) {
        return Collections.emptyList();
    }

    @Override
    public int countComments(Long authorUserId, List<Long> threadIds) {
        return 0;
    }

    @Override
    public IComment getComment(Long commentId, boolean includeDeleted)
            throws CommentNotFoundException {
        return null;
    }

    @Override
    public boolean updateComment(IComment comment) {
        return false;
    }

    @Override
    public IComment createComment(IComment comment) {
        return null;
    }

    @Override
    public boolean deleteComment(Long commentId) {
        return false;
    }
}

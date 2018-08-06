package org.xcolab.client.comment;

import org.apache.commons.collections4.CollectionUtils;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentDto;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentClient {

    private final CommentServiceWrapper commentServiceWrapper;
    private final ServiceNamespace serviceNamespace;
    private static final Map<ServiceNamespace, CommentClient> instances = new HashMap<>();

    public CommentClient(ServiceNamespace serviceNamespace) {
        commentServiceWrapper = CommentServiceWrapper.fromService(serviceNamespace);
        this.serviceNamespace = serviceNamespace;
    }

    public List<Comment> listComments(int start, int last) {
        return listComments(start, last, null);
    }

    public List<Comment> listComments(int start, int last, Long threadId) {
        return DtoUtil.toPojos(
                commentServiceWrapper.listComments(start, last, "createdAt", null, threadId, null),
                serviceNamespace);
    }

    public int countComments(Long threadId) {
        if (threadId == null) {
            return 0;
        }
        return commentServiceWrapper.countComments(null, Collections.singleton(threadId));
    }

    public int countComments(Collection<Long> threadIds) {
        if (CollectionUtils.isEmpty(threadIds)) {
            return 0;
        }
        return commentServiceWrapper.countComments(null, threadIds);
    }

    public int countCommentsByAuthor(long authorUserId) {
        return commentServiceWrapper.countComments(authorUserId, null);
    }

    public Comment getComment(long commentId) throws CommentNotFoundException {
        return getComment(commentId, false);
    }

    public Comment getComment(long commentId, boolean includeDeleted)
            throws CommentNotFoundException {
        return commentServiceWrapper.getComment(commentId, includeDeleted, CacheName.MISC_REQUEST)
                .toPojo(serviceNamespace);
    }

    public boolean updateComment(Comment comment) {
        return commentServiceWrapper.updateComment(new CommentDto(comment));
    }

    public Comment createComment(Comment comment) {
        return commentServiceWrapper.createComment(new CommentDto(comment)).toPojo(serviceNamespace);
    }

    public boolean deleteComment(long commentId) {
        return commentServiceWrapper.deleteComment(commentId);
    }

    public static CommentClient fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, CommentClient::new);
    }
}

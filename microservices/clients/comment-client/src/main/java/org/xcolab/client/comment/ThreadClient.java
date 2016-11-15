package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.pojo.CommentThreadDto;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadClient {

    private final CommentServiceWrapper commentServiceWrapper;
    private final RestService commentService;

    private static final Map<RestService, ThreadClient> instances = new HashMap<>();

    public ThreadClient(RestService commentService) {
        commentServiceWrapper = CommentServiceWrapper.fromService(commentService);
        this.commentService = commentService;
    }

    public List<CommentThread> listThreads(int start, int last, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {
        return CommentThreadDto.toPojos(commentServiceWrapper.listThreads(start, last, sortColumn.getIdentifier(ascending),
                null, categoryId, groupId), commentService);
    }

    public CommentThread getThread(long threadId) throws ThreadNotFoundException {
        return commentServiceWrapper.getThread(threadId, CacheRetention.MEDIUM)
                .toPojo(commentService);
    }

    public Long getProposalIdForThread(long threadId) {
        return commentServiceWrapper.getProposalIdForThread(threadId, CacheRetention.RUNTIME);
    }

    public boolean updateThread(CommentThread thread) {
        return commentServiceWrapper.updateThread(new CommentThreadDto(thread));
    }

    public CommentThread createThread(CommentThread thread) {
        return commentServiceWrapper.createThread(new CommentThreadDto(thread))
                .toPojo(commentService);
    }

    public Date getLastActivityDate(long threadId) {
        return commentServiceWrapper.getLastActivityDate(threadId, CacheRetention.REQUEST);
    }

    public long getLastActivityAuthorId(long threadId) {
        return commentServiceWrapper.getLastActivityAuthorId(threadId, CacheRetention.REQUEST);
    }

    public static ThreadClient fromService(RestService contestService) {
        ThreadClient client = instances.get(contestService);
        if (client == null) {
            client = new ThreadClient(contestService);
            instances.put(contestService, client);
        }
        return client;
    }
}

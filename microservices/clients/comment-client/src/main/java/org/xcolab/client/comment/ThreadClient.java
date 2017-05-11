package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.pojo.CommentThreadDto;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.util.http.caching.CacheName;
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
        return commentServiceWrapper.getThread(threadId, CacheName.MISC_MEDIUM)
                .toPojo(commentService);
    }

    public boolean updateThread(CommentThread thread) {
        return commentServiceWrapper.updateThread(new CommentThreadDto(thread));
    }

    public CommentThread createThread(CommentThread thread) {
        return commentServiceWrapper.createThread(new CommentThreadDto(thread))
                .toPojo(commentService);
    }

    public Date getLastActivityDate(long threadId) {
        return commentServiceWrapper.getLastActivityDate(threadId, CacheName.MISC_REQUEST);
    }

    public long getLastActivityAuthorId(long threadId) {
        return commentServiceWrapper.getLastActivityAuthorId(threadId, CacheName.MISC_REQUEST);
    }

    public static ThreadClient fromService(RestService contestService) {
        return instances.computeIfAbsent(contestService, ThreadClient::new);
    }
}

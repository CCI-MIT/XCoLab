package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadClient {

    // Default instance when used statically
    private static final ThreadClient INSTANCE = new ThreadClient();

    private final CommentServiceWrapper commentServiceWrapper = new CommentServiceWrapper();

    private static final Map<ServiceNamespace, ThreadClient> instances = new HashMap<>();

    public static ThreadClient instance() {
        return INSTANCE;
    }

    public List<CommentThread> listThreads(int start, int last, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {
        return commentServiceWrapper.listThreads(start, last, sortColumn.getIdentifier(ascending),
                null, categoryId, groupId);
    }

    public CommentThread getThread(long threadId) throws ThreadNotFoundException {
        return commentServiceWrapper.getThread(threadId, CacheName.MISC_MEDIUM);
    }

    public boolean updateThread(CommentThread thread) {
        return commentServiceWrapper.updateThread(thread);
    }

    public CommentThread createThread(CommentThread thread) {
        return commentServiceWrapper.createThread(thread);
    }

    public void deleteThread(long threadId) {
        commentServiceWrapper.deleteThread(threadId);
    }

    public Date getLastActivityDate(long threadId) {
        return commentServiceWrapper.getLastActivityDate(threadId, CacheName.MISC_REQUEST);
    }

    public long getLastActivityauthorUserId(long threadId) {
        return commentServiceWrapper.getLastActivityauthorUserId(threadId, CacheName.MISC_REQUEST);
    }

    public static ThreadClient fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, serviceNamespace1 -> new ThreadClient());
    }
}

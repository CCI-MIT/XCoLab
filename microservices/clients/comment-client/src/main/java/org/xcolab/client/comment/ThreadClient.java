package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.util.http.caching.CacheName;

import java.util.Date;
import java.util.List;

public class ThreadClient {

    // Default instance when used statically
    private static final ThreadClient INSTANCE = new ThreadClient();

    private final CommentServiceWrapper commentServiceWrapper = new CommentServiceWrapper();

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
}

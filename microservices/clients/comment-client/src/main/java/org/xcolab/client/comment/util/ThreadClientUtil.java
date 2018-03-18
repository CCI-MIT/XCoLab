package org.xcolab.client.comment.util;

import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.commons.http.client.enums.ServiceNamespace;

import java.util.Date;
import java.util.List;

public final class ThreadClientUtil {

    private static final ThreadClient threadClient = new ThreadClient(ServiceNamespace.instance());

    private ThreadClientUtil() {
    }

    public static List<CommentThread> listThreads(int start, int last, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {
        return threadClient.listThreads(start, last, categoryId, groupId, sortColumn, ascending);
    }

    public static CommentThread getThread(long threadId) throws ThreadNotFoundException {
        return threadClient.getThread(threadId);
    }

    public static boolean updateThread(CommentThread thread) {
        return threadClient.updateThread(thread);
    }

    public static CommentThread createThread(CommentThread thread) {
        return threadClient.createThread(thread);
    }

    public static void deleteThread(long threadId) {
        threadClient.deleteThread(threadId);
    }

    public static void deleteProposalThreads(List<Long> proposalPKs) {
        threadClient.deleteProposalThreads(proposalPKs);
    }

    public static Date getLastActivityDate(long threadId) {
        return threadClient.getLastActivityDate(threadId);
    }

    public static long getLastActivityAuthorId(long threadId) {
        return threadClient.getLastActivityAuthorId(threadId);
    }

    public static ThreadClient getClient() {
        return threadClient;
    }
}

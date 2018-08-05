package org.xcolab.client.comment;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.pojo.CommentThreadDto;
import org.xcolab.client.comment.util.ThreadSortColumn;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DtoUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadClient {

    private final CommentServiceWrapper commentServiceWrapper;
    private final ServiceNamespace serviceNamespace;

    private static final Map<ServiceNamespace, ThreadClient> instances = new HashMap<>();

    public ThreadClient(ServiceNamespace serviceNamespace) {
        commentServiceWrapper = CommentServiceWrapper.fromService(serviceNamespace);
        this.serviceNamespace = serviceNamespace;
    }

    public List<CommentThread> listThreads(int start, int last, Long categoryId,
            Long groupId, ThreadSortColumn sortColumn, boolean ascending) {
        return DtoUtil.toPojos(commentServiceWrapper.listThreads(start, last, sortColumn.getIdentifier(ascending),
                null, categoryId, groupId), serviceNamespace);
    }

    public CommentThread getThread(long threadId) throws ThreadNotFoundException {
        return commentServiceWrapper.getThread(threadId, CacheName.MISC_MEDIUM)
                .toPojo(serviceNamespace);
    }

    public boolean updateThread(CommentThread thread) {
        return commentServiceWrapper.updateThread(new CommentThreadDto(thread));
    }

    public CommentThread createThread(CommentThread thread) {
        return commentServiceWrapper.createThread(new CommentThreadDto(thread))
                .toPojo(serviceNamespace);
    }

    public void deleteThread(long threadId) {
        commentServiceWrapper.deleteThread(threadId);
    }

    public Date getLastActivityDate(long threadId) {
        return commentServiceWrapper.getLastActivityDate(threadId, CacheName.MISC_REQUEST);
    }

    public long getLastActivityauthorUserid(long threadId) {
        return commentServiceWrapper.getLastActivityauthorUserid(threadId, CacheName.MISC_REQUEST);
    }

    public static ThreadClient fromService(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, ThreadClient::new);
    }
}

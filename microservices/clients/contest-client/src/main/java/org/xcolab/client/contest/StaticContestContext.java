package org.xcolab.client.contest;

import org.springframework.util.Assert;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;

public final class StaticContestContext {

    private static ICommentClient commentClient;
    private static ICategoryClient categoryClient;
    private static IThreadClient threadClient;

    private StaticContestContext() {}

    public static void setClients(ICommentClient commentClient, ICategoryClient categoryClient,
            IThreadClient threadClient) {
        Assert.notNull(commentClient, "commentClient must not be null!");
        Assert.notNull(categoryClient, "categoryClient must not be null!");
        Assert.notNull(threadClient, "threadClient must not be null!");
        StaticContestContext.commentClient = commentClient;
        StaticContestContext.categoryClient = categoryClient;
        StaticContestContext.threadClient = threadClient;
    }

    public static ICommentClient getCommentClient() {
        return commentClient;
    }

    public static ICategoryClient getCategoryClient() {
        return categoryClient;
    }

    public static IThreadClient getThreadClient() {
        return threadClient;
    }

    public static ContestClient getContestClient() {
        return ContestClientUtil.getClient();
    }
}

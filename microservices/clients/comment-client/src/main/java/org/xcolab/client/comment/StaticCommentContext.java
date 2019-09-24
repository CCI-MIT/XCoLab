package org.xcolab.client.comment;

import org.springframework.util.Assert;

public final class StaticCommentContext {

    private static ICommentClient commentClient;
    private static ICategoryClient categoryClient;
    private static IThreadClient threadClient;

    private StaticCommentContext() {}

    public static void setClients(ICommentClient commentClient, ICategoryClient categoryClient,
            IThreadClient threadClient) {
        Assert.notNull(commentClient, "commentClient must not be null!");
        Assert.notNull(categoryClient, "categoryClient must not be null!");
        Assert.notNull(threadClient, "threadClient must not be null!");
        StaticCommentContext.commentClient = commentClient;
        StaticCommentContext.categoryClient = categoryClient;
        StaticCommentContext.threadClient = threadClient;
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
}

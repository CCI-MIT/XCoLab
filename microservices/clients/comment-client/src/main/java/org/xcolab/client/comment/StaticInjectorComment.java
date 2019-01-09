package org.xcolab.client.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticInjectorComment {

    private static ICategoryClient categoryClient;
    private static ICommentClient commentClient;
    private static IThreadClient threadClient;

    @Autowired
    public StaticInjectorComment(ICategoryClient categoryClient, ICommentClient commentClient,
            IThreadClient threadClient) {
        StaticInjectorComment.categoryClient = categoryClient;
        StaticInjectorComment.commentClient = commentClient;
        StaticInjectorComment.threadClient = threadClient;
    }

    public static ICategoryClient getCategoryClient() {
        return categoryClient;
    }

    public static ICommentClient getCommentClient() {
        return commentClient;
    }

    public static IThreadClient getThreadClient() {
        return threadClient;
    }
}

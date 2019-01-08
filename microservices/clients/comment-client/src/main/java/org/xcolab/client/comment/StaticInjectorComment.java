package org.xcolab.client.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticInjectorComment {

    private static CategoryClient categoryClient;
    private static CommentClient commentClient;
    private static ThreadClient threadClient;

    @Autowired
    public StaticInjectorComment(CategoryClient categoryClient, CommentClient commentClient,
            ThreadClient threadClient) {
        StaticInjectorComment.categoryClient = categoryClient;
        StaticInjectorComment.commentClient = commentClient;
        StaticInjectorComment.threadClient = threadClient;
    }

    public static CategoryClient getCategoryClient() {
        return categoryClient;
    }

    public static CommentClient getCommentClient() {
        return commentClient;
    }

    public static ThreadClient getThreadClient() {
        return threadClient;
    }
}

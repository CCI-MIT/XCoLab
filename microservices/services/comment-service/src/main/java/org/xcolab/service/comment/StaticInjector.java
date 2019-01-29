package org.xcolab.service.comment;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.StaticCommentContext;

public class StaticInjector {

    @Autowired
    public StaticInjector(ICommentClient commentClient, ICategoryClient categoryClient,
            IThreadClient threadClient) {
        StaticCommentContext.setClients(commentClient, categoryClient, threadClient);
    }
}

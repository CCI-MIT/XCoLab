package org.xcolab.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.StaticCommentContext;

@Component
@Profile("!test")
public class StaticInjector {

    @Autowired
    public StaticInjector(ICommentClient commentClient, ICategoryClient categoryClient,
            IThreadClient threadClient) {
        StaticCommentContext.setClients(commentClient, categoryClient, threadClient);
    }
}

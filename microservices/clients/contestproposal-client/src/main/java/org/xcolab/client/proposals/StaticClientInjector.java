package org.xcolab.client.proposals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.proposals.pojo.Proposal;

@Component
public class StaticClientInjector {

    @Autowired
    public StaticClientInjector(ThreadClient threadClient, CommentClient commentClient) {
        Proposal.setThreadClient(threadClient);
        Proposal.setCommentClient(commentClient);
    }
}

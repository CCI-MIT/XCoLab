package org.xcolab.client.proposals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.proposals.pojo.Proposal;

@Component
public class StaticInjectorProposal {

    @Autowired
    public StaticInjectorProposal(IThreadClient threadClient, ICommentClient commentClient) {
        Proposal.setThreadClient(threadClient);
        Proposal.setCommentClient(commentClient);
    }
}

package org.xcolab.client.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.contest.pojo.Contest;

@Component
public class StaticInjectorContest {

    @Autowired
    public StaticInjectorContest(IThreadClient threadClient, ICommentClient commentClient) {
        Contest.setThreadClient(threadClient);
        Contest.setCommentClient(commentClient);
    }
}

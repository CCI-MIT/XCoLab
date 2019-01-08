package org.xcolab.client.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.contest.pojo.Contest;

@Component
public class StaticInjectorContest {

    @Autowired
    public StaticInjectorContest(ThreadClient threadClient, CommentClient commentClient) {
        Contest.setThreadClient(threadClient);
        Contest.setCommentClient(commentClient);
    }
}

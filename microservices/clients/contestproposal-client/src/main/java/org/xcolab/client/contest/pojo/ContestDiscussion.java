package org.xcolab.client.contest.pojo;

import org.xcolab.util.http.client.RestService;

public class ContestDiscussion extends AbstractContestDiscussion {

    private RestService contestService;

    public ContestDiscussion() {}

    public ContestDiscussion(ContestDiscussion value) {
        super(value);
    }

    public ContestDiscussion(Long discussionId, Long contestId, String tab) {
        super(discussionId, contestId, tab);
    }

    public ContestDiscussion(AbstractContestDiscussion abstractContestDiscussion, RestService contestService) {
        super(abstractContestDiscussion);
        this.contestService = contestService;
    }

}

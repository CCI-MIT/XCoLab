package org.xcolab.client.contest.pojo;

import org.xcolab.commons.http.client.enums.ServiceNamespace;

public class ContestDiscussion extends AbstractContestDiscussion {

    private ServiceNamespace serviceNamespace;

    public ContestDiscussion() {}

    public ContestDiscussion(ContestDiscussion value) {
        super(value);
    }

    public ContestDiscussion(Long discussionId, Long contestId, String tab) {
        super(discussionId, contestId, tab);
    }

    public ContestDiscussion(AbstractContestDiscussion abstractContestDiscussion, ServiceNamespace serviceNamespace) {
        super(abstractContestDiscussion);
        this.serviceNamespace = serviceNamespace;
    }

}

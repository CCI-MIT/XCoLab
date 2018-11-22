package org.xcolab.client.contest.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ContestDiscussion extends AbstractContestDiscussion {

    public static final TypeProvider<ContestDiscussion> TYPES = new TypeProvider<>(
            ContestDiscussion.class, new ParameterizedTypeReference<List<ContestDiscussion>>() {});

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

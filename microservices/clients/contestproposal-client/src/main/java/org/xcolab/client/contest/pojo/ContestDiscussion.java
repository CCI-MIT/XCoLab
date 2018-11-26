package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestDiscussion extends AbstractContestDiscussion implements Serializable {

    public static final TypeProvider<ContestDiscussion> TYPES = new TypeProvider<>(
            ContestDiscussion.class, new ParameterizedTypeReference<List<ContestDiscussion>>() {});

    public ContestDiscussion() {}

    public ContestDiscussion(ContestDiscussion value) {
        super(value);
    }

    public ContestDiscussion(Long discussionId, Long contestId, String tab) {
        super(discussionId, contestId, tab);
    }

    public ContestDiscussion(AbstractContestDiscussion abstractContestDiscussion) {
        super(abstractContestDiscussion);
    }

}

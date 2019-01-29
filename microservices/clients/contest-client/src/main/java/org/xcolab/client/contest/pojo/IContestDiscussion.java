package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ContestDiscussion;

@JsonDeserialize(as = ContestDiscussion.class)
public interface IContestDiscussion {

    Long getId();

    void setId(Long id);

    Long getContestId();

    void setContestId(Long contestId);

    String getTab();

    void setTab(String tab);
}

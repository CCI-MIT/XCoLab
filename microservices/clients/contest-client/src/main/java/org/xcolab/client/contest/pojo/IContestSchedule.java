package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.tables.pojos.ContestSchedule;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

@JsonDeserialize(as = ContestSchedule.class)
public interface IContestSchedule {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    String getStatus();

    void setStatus(String status);

    Long getBaseScheduleId();

    void setBaseScheduleId(Long baseScheduleId);

    default boolean isUsedInNonEmptyContest() {
        return StaticContestContext.getContestClient().getContestsByContestScheduleId(getId())
                .stream()
                .anyMatch(ContestWrapper::isNotEmpty);
    }
}

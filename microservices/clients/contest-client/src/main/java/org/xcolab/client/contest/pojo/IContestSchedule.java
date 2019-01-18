package org.xcolab.client.contest.pojo;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

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

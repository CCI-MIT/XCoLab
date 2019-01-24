package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ContestPhase;

import java.sql.Timestamp;

@JsonDeserialize(as = ContestPhase.class)
public interface IContestPhase {

    Long getId();

    void setId(Long id);

    Long getContestId();

    void setContestId(Long contestId);

    Long getContestPhaseTypeId();

    void setContestPhaseTypeId(Long contestPhaseTypeId);

    Long getContestScheduleId();

    void setContestScheduleId(Long contestScheduleId);

    String getContestPhaseAutopromote();

    void setContestPhaseAutopromote(String contestPhaseAutopromote);

    Timestamp getPhaseStartDate();

    void setPhaseStartDate(Timestamp phaseStartDate);

    Timestamp getPhaseEndDate();

    void setPhaseEndDate(Timestamp phaseEndDate);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);
}

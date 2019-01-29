package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ContestTeamMember;

@JsonDeserialize(as = ContestTeamMember.class)
public interface IContestTeamMember {

    Long getId();

    void setId(Long id);

    Long getContestId();

    void setContestId(Long contestId);

    Long getUserId();

    void setUserId(Long userId);

    Long getRoleId();

    void setRoleId(Long roleId);
}

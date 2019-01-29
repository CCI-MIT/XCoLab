package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.enums.ContestRole;
import org.xcolab.client.contest.pojo.tables.pojos.ContestTeamMemberRole;

@JsonDeserialize(as = ContestTeamMemberRole.class)
public interface IContestTeamMemberRole extends Comparable<IContestTeamMemberRole> {

    Long getId();

    void setId(Long id);

    String getRole();

    void setRole(String role);

    Integer getSort();

    void setSort(Integer sort);

    default ContestRole getContestRole() {
        return ContestRole.fromRoleId(getId());
    }

    default int compareTo(IContestTeamMemberRole other) {
        return this.getSort() - other.getSort();
    }
}

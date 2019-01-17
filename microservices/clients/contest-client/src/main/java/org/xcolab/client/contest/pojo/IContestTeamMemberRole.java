package org.xcolab.client.contest.pojo;

import org.xcolab.client.contest.enums.ContestRole;

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

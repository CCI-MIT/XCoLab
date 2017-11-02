package org.xcolab.client.contest.pojo.team;

import org.xcolab.client.contest.enums.ContestRole;
import org.xcolab.util.http.client.enums.ServiceNamespace;


public class ContestTeamMemberRole extends AbstractContestTeamMemberRole
        implements Comparable<ContestTeamMemberRole> {

    public ContestTeamMemberRole() {}

    public ContestTeamMemberRole(ContestTeamMemberRole value) {
        super(value);
    }

    public ContestTeamMemberRole(Long id_, String role, Integer sort) {
        super(id_, role, sort);
    }

    public ContestTeamMemberRole(AbstractContestTeamMemberRole abstractContestTeamMemberRole,
            ServiceNamespace serviceNamespace) {
        super(abstractContestTeamMemberRole);
    }

    public ContestRole getContestRole() {
        return ContestRole.fromRoleId(getId_());
    }

    @Override
    public int compareTo(ContestTeamMemberRole other) {
        return this.getSort() - other.getSort();
    }

}

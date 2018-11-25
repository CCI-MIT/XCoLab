package org.xcolab.client.contest.pojo.team;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.enums.ContestRole;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ContestTeamMemberRole extends AbstractContestTeamMemberRole
        implements Comparable<ContestTeamMemberRole> {

    public static final TypeProvider<ContestTeamMemberRole> TYPES =
            new TypeProvider<>(ContestTeamMemberRole.class,
                    new ParameterizedTypeReference<List<ContestTeamMemberRole>>() {});

    public ContestTeamMemberRole() {}

    public ContestTeamMemberRole(ContestTeamMemberRole value) {
        super(value);
    }

    public ContestTeamMemberRole(Long id, String role, Integer sort) {
        super(id, role, sort);
    }

    public ContestTeamMemberRole(AbstractContestTeamMemberRole abstractContestTeamMemberRole) {
        super(abstractContestTeamMemberRole);
    }

    public ContestRole getContestRole() {
        return ContestRole.fromRoleId(getId());
    }

    @Override
    public int compareTo(ContestTeamMemberRole other) {
        return this.getSort() - other.getSort();
    }

}

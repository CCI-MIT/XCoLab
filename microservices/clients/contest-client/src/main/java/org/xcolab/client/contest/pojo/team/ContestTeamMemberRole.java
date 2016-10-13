package org.xcolab.client.contest.pojo.team;

import org.xcolab.util.http.client.RestService;

public class ContestTeamMemberRole extends AbstractContestTeamMemberRole {

    public ContestTeamMemberRole() {}

    public ContestTeamMemberRole(ContestTeamMemberRole value) {
        super(value);
    }

    public ContestTeamMemberRole(Long id_, String role, Integer sort) {
        super(id_, role, sort);
    }

    public ContestTeamMemberRole(AbstractContestTeamMemberRole abstractContestTeamMemberRole,
            RestService restService) {
        super(abstractContestTeamMemberRole);
    }
}

package org.xcolab.client.contest.pojo.team;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ContestTeamMember extends AbstractContestTeamMember {

    public ContestTeamMember() {}

    public ContestTeamMember(ContestTeamMember value) {
        super(value);
    }

    public ContestTeamMember(Long id_, Long contestid, Long userid, Long roleid) {
        super(id_, contestid, userid, roleid);
    }

    public ContestTeamMember(AbstractContestTeamMember abstractContestTeamMember, ServiceNamespace serviceNamespace) {
        super(abstractContestTeamMember);
    }
}

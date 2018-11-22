package org.xcolab.client.contest.pojo.team;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ContestTeamMember extends AbstractContestTeamMember {

    public static final TypeProvider<ContestTeamMember> TYPES = new TypeProvider<>(
            ContestTeamMember.class, new ParameterizedTypeReference<List<ContestTeamMember>>() {});

    public ContestTeamMember() {}

    public ContestTeamMember(ContestTeamMember value) {
        super(value);
    }

    public ContestTeamMember(Long id, Long contestid, Long userid, Long roleid) {
        super(id, contestid, userid, roleid);
    }

    public ContestTeamMember(AbstractContestTeamMember abstractContestTeamMember, ServiceNamespace serviceNamespace) {
        super(abstractContestTeamMember);
    }
}

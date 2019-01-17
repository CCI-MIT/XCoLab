package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestTeamMember extends AbstractContestTeamMember implements Serializable {

    public static final TypeProvider<ContestTeamMember> TYPES = new TypeProvider<>(
            ContestTeamMember.class, new ParameterizedTypeReference<List<ContestTeamMember>>() {});

    public ContestTeamMember() {}

    public ContestTeamMember(ContestTeamMember value) {
        super(value);
    }

    public ContestTeamMember(Long id, Long contestid, Long userid, Long roleid) {
        super(id, contestid, userid, roleid);
    }

    public ContestTeamMember(AbstractContestTeamMember abstractContestTeamMember) {
        super(abstractContestTeamMember);
    }
}

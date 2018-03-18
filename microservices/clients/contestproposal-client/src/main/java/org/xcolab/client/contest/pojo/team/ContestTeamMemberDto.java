package org.xcolab.client.contest.pojo.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestTeamMemberDto extends AbstractContestTeamMember implements DataTransferObject<ContestTeamMember> {

    public static final TypeProvider<ContestTeamMemberDto> TYPES =
            new TypeProvider<>(ContestTeamMemberDto.class,
                    new ParameterizedTypeReference<List<ContestTeamMemberDto>>() {
                    });

    public ContestTeamMemberDto() {}

    public ContestTeamMemberDto(AbstractContestTeamMember value) {
        super(value);
    }

    @Override
    public ContestTeamMember toPojo(ServiceNamespace serviceNamespace) {
        return new ContestTeamMember(this, serviceNamespace);
    }
}

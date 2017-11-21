package org.xcolab.client.proposals.pojo.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class MembershipRequestDto extends AbstractMembershipRequest
        implements DataTransferObject<MembershipRequest> {

    public static final TypeProvider<MembershipRequestDto> TYPES =
            new TypeProvider<>(MembershipRequestDto.class,
                    new ParameterizedTypeReference<List<MembershipRequestDto>>() {
                    });

    public MembershipRequestDto() {}

    public MembershipRequestDto(AbstractMembershipRequest value) {
        super(value);
    }

    @Override
    public MembershipRequest toPojo(ServiceNamespace serviceNamespace) {
        return new MembershipRequest(this, serviceNamespace);
    }
}

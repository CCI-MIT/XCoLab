package org.xcolab.client.proposals.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SupportedProposalDto extends AbstractProposal
        implements DataTransferObject<SupportedProposal> {

    public static final TypeProvider<SupportedProposalDto> TYPES = new TypeProvider<>(
            SupportedProposalDto.class,
            new ParameterizedTypeReference<List<SupportedProposalDto>>() {});

    private Timestamp supportDate;
    private Long supporterUserId;

    public SupportedProposalDto() {}

    public SupportedProposalDto(AbstractProposal value) {
        super(value);
    }

    public Timestamp getSupportDate() {
        return supportDate;
    }

    public void setSupportDate(Timestamp supportDate) {
        this.supportDate = supportDate;
    }

    public Long getSupporterUserId() {
        return supporterUserId;
    }

    public void setSupporterUserId(Long supporterUserId) {
        this.supporterUserId = supporterUserId;
    }

    @Override
    public SupportedProposal toPojo(ServiceNamespace serviceNamespace) {
        return new SupportedProposal(this, serviceNamespace);
    }
}

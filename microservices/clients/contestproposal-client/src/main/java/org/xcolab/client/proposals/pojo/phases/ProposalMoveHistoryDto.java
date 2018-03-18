package org.xcolab.client.proposals.pojo.phases;

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
public class ProposalMoveHistoryDto extends AbstractProposalMoveHistory
        implements DataTransferObject<ProposalMoveHistory> {

    public static final TypeProvider<ProposalMoveHistoryDto> TYPES =
            new TypeProvider<>(ProposalMoveHistoryDto.class,
                    new ParameterizedTypeReference<List<ProposalMoveHistoryDto>>() {
                    });

    public ProposalMoveHistoryDto() {}

    public ProposalMoveHistoryDto(AbstractProposalMoveHistory value) {
        super(value);
    }

    @Override
    public ProposalMoveHistory toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalMoveHistory(this, serviceNamespace);
    }
}

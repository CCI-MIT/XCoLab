package org.xcolab.client.contest.pojo.phases;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestPhaseDto extends AbstractContestPhase implements DataTransferObject<ContestPhase> {

    public static final TypeProvider<ContestPhaseDto> TYPES = new TypeProvider<>(ContestPhaseDto.class,
            new ParameterizedTypeReference<List<ContestPhaseDto>>() {});

    public ContestPhaseDto() {
    }

    public ContestPhaseDto(AbstractContestPhase value) {
        super(value);
    }

    @Override
    public ContestPhase toPojo(RestService restService) {
        return new ContestPhase(this, restService);
    }
}

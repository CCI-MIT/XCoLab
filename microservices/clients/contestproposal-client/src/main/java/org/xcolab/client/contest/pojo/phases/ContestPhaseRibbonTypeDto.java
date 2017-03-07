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
public class ContestPhaseRibbonTypeDto extends AbstractContestPhaseRibbonType
        implements DataTransferObject<ContestPhaseRibbonType> {

    public static final TypeProvider<ContestPhaseRibbonTypeDto> TYPES = new TypeProvider<>(
            ContestPhaseRibbonTypeDto.class,
            new ParameterizedTypeReference<List<ContestPhaseRibbonTypeDto>>() {});

    public ContestPhaseRibbonTypeDto() {}

    public ContestPhaseRibbonTypeDto(AbstractContestPhaseRibbonType value) {
        super(value);
    }

    @Override
    public ContestPhaseRibbonType toPojo(RestService restService) {
        return new ContestPhaseRibbonType(this, restService);
    }
}

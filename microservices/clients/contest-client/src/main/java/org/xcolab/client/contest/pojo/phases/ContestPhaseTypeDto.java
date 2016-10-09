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
public class ContestPhaseTypeDto extends AbstractContestPhaseType implements DataTransferObject<ContestPhaseType> {

    public static final TypeProvider<ContestPhaseTypeDto> TYPES =
            new TypeProvider<>(ContestPhaseTypeDto.class,
                    new ParameterizedTypeReference<List<ContestPhaseTypeDto>>() {
                    });


    public ContestPhaseTypeDto() {}

    public ContestPhaseTypeDto(AbstractContestPhaseType value) {
        super(value);
    }

    @Override
    public ContestPhaseType toPojo(RestService restService) {
        return new ContestPhaseType(this, restService);
    }
}

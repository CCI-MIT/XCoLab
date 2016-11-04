package org.xcolab.client.contest.pojo;

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
public class ContestTypeDto extends AbstractContestType implements DataTransferObject<ContestType> {

    public static final TypeProvider<ContestTypeDto> TYPES = new TypeProvider<>(ContestTypeDto.class,
            new ParameterizedTypeReference<List<ContestTypeDto>>() {
            });

    public ContestTypeDto() {
    }

    public ContestTypeDto(AbstractContestType value) {
        super(value);
    }

    @Override
    public ContestType toPojo(RestService restService) {
        return new ContestType(this, restService);
    }
}

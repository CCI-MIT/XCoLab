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
public class ContestDto extends AbstractContest implements DataTransferObject<Contest> {

    public static final TypeProvider<ContestDto> TYPES = new TypeProvider<>(ContestDto.class,
                    new ParameterizedTypeReference<List<ContestDto>>() {});

    public ContestDto() {}

    public ContestDto(AbstractContest value) {
        super(value);
    }

    @Override
    public Contest toPojo(RestService restService) {
        return new Contest(this, restService);
    }
}

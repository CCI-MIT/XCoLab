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
public class ContestTranslationDto extends AbstractContestTranslation
        implements DataTransferObject<ContestTranslation> {

    public static final TypeProvider<ContestTranslationDto> TYPES = new TypeProvider<>(
            ContestTranslationDto.class,
            new ParameterizedTypeReference<List<ContestTranslationDto>>() {});

    public ContestTranslationDto() {
    }

    public ContestTranslationDto(AbstractContestTranslation value) {
        super(value);
    }

    @Override
    public ContestTranslation toPojo(RestService restService) {
        return new ContestTranslation(this, restService);
    }
}

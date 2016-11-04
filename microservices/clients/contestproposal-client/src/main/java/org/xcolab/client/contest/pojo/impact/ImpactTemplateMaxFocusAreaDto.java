package org.xcolab.client.contest.pojo.impact;

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
public class ImpactTemplateMaxFocusAreaDto extends AbstractImpactTemplateMaxFocusArea
        implements DataTransferObject<ImpactTemplateMaxFocusArea> {

    public static final TypeProvider<ImpactTemplateMaxFocusAreaDto> TYPES =
            new TypeProvider<>(ImpactTemplateMaxFocusAreaDto.class,
                    new ParameterizedTypeReference<List<ImpactTemplateMaxFocusAreaDto>>() {
                    });

    public ImpactTemplateMaxFocusAreaDto() {}

    public ImpactTemplateMaxFocusAreaDto(AbstractImpactTemplateMaxFocusArea value) {
        super(value);
    }

    @Override
    public ImpactTemplateMaxFocusArea toPojo(RestService restService) {
        return new ImpactTemplateMaxFocusArea(this, restService);
    }
}

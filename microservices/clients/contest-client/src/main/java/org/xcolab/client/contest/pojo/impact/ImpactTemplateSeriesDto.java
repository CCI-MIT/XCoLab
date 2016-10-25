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
public class ImpactTemplateSeriesDto extends AbstractImpactTemplateSeries
        implements DataTransferObject<ImpactTemplateSeries> {

    public static final TypeProvider<ImpactTemplateSeriesDto> TYPES =
            new TypeProvider<>(ImpactTemplateSeriesDto.class,
                    new ParameterizedTypeReference<List<ImpactTemplateSeriesDto>>() {
                    });

    public ImpactTemplateSeriesDto() {}

    public ImpactTemplateSeriesDto(AbstractImpactTemplateSeries value) {
        super(value);
    }

    @Override
    public ImpactTemplateSeries toPojo(RestService restService) {
        return new ImpactTemplateSeries(this, restService);
    }
}

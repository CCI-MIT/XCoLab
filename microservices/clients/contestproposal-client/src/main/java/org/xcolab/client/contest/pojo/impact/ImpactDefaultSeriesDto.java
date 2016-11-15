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
public class ImpactDefaultSeriesDto extends AbstractImpactDefaultSeries
        implements DataTransferObject<ImpactDefaultSeries> {

    public static final TypeProvider<ImpactDefaultSeriesDto> TYPES =
            new TypeProvider<>(ImpactDefaultSeriesDto.class,
                    new ParameterizedTypeReference<List<ImpactDefaultSeriesDto>>() {
                    });

    public ImpactDefaultSeriesDto() {}

    public ImpactDefaultSeriesDto(AbstractImpactDefaultSeries value) {
        super(value);
    }

    @Override
    public ImpactDefaultSeries toPojo(RestService restService) {
        return new ImpactDefaultSeries(this, restService);
    }
}

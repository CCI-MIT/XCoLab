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
public class ImpactDefaultSeriesDataDto extends AbstractImpactDefaultSeriesData
        implements DataTransferObject<ImpactDefaultSeriesData> {

    public static final TypeProvider<ImpactDefaultSeriesDataDto> TYPES =
            new TypeProvider<>(ImpactDefaultSeriesDataDto.class,
                    new ParameterizedTypeReference<List<ImpactDefaultSeriesDataDto>>() {
                    });
    public ImpactDefaultSeriesDataDto() {}

    public ImpactDefaultSeriesDataDto(AbstractImpactDefaultSeriesData value) {
        super(value);
    }

    @Override
    public ImpactDefaultSeriesData toPojo(RestService restService) {
        return new ImpactDefaultSeriesData(this, restService);
    }
}

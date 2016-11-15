package org.xcolab.client.proposals.pojo.points;

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
public class PointsDistributionConfigurationDto extends AbstractPointsDistributionConfiguration
        implements DataTransferObject<PointsDistributionConfiguration> {

    public static final TypeProvider<PointsDistributionConfigurationDto> TYPES =
            new TypeProvider<>(PointsDistributionConfigurationDto.class,
                    new ParameterizedTypeReference<List<PointsDistributionConfigurationDto>>() {
                    });

    public PointsDistributionConfigurationDto() {}

    public PointsDistributionConfigurationDto(AbstractPointsDistributionConfiguration value) {
        super(value);
    }

    @Override
    public PointsDistributionConfiguration toPojo(RestService restService) {
        return new PointsDistributionConfiguration(this, restService);
    }
}

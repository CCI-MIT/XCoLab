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
public class PointsDto extends AbstractPoints implements DataTransferObject<Points> {

    public static final TypeProvider<PointsDto> TYPES =
            new TypeProvider<>(PointsDto.class,
                    new ParameterizedTypeReference<List<PointsDto>>() {
                    });

    public PointsDto() {}

    public PointsDto(AbstractPoints value) {
        super(value);
    }

    @Override
    public Points toPojo(RestService restService) {
        return new Points(this, restService);
    }

}

package org.xcolab.client.proposals.pojo.points;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PointTypeDto extends AbstractPointType implements DataTransferObject<PointType> {

    public static final TypeProvider<PointTypeDto> TYPES =
            new TypeProvider<>(PointTypeDto.class,
                    new ParameterizedTypeReference<List<PointTypeDto>>() {
                    });

    public PointTypeDto() {}

    public PointTypeDto(AbstractPointType value) {
        super(value);
    }

    @Override
    public PointType toPojo(ServiceNamespace serviceNamespace) {
        return new PointType(this, serviceNamespace);
    }
}

package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

import java.util.List;

public class ModelPositionDto extends AbstractModelPosition
        implements DataTransferObject<ModelPosition> {

    public static final TypeProvider<ModelPositionDto> TYPES = new TypeProvider<>(
            ModelPositionDto.class,
            new ParameterizedTypeReference<List<ModelPositionDto>>() {});

    public ModelPositionDto() {
    }

    public ModelPositionDto(AbstractModelPosition value) {
        super(value);
    }

    @Override
    public ModelPosition toPojo(ServiceNamespace serviceNamespace) {
        return new ModelPosition(this, serviceNamespace);
    }
}

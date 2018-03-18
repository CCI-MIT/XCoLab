package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

import java.util.List;

public class ModelInputGroupDto extends AbstractModelInputGroup
        implements DataTransferObject<ModelInputGroup> {

    public static final TypeProvider<ModelInputGroupDto> TYPES = new TypeProvider<>(
            ModelInputGroupDto.class,
            new ParameterizedTypeReference<List<ModelInputGroupDto>>() {});

    public ModelInputGroupDto() {
    }

    public ModelInputGroupDto(AbstractModelInputGroup value) {
        super(value);
    }

    @Override
    public ModelInputGroup toPojo(ServiceNamespace serviceNamespace) {
        return new ModelInputGroup(this, serviceNamespace);
    }
}

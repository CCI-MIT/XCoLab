package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

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
    public ModelInputGroup toPojo(RestService restService) {
        return new ModelInputGroup(this, restService);
    }
}

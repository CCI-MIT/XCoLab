package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

import java.util.List;

public class ModelCategoryDto extends AbstractModelCategory
        implements DataTransferObject<ModelCategory> {

    public static final TypeProvider<ModelCategoryDto> TYPES = new TypeProvider<>(
            ModelCategoryDto.class,
            new ParameterizedTypeReference<List<ModelCategoryDto>>() {});

    public ModelCategoryDto() {
    }

    public ModelCategoryDto(AbstractModelCategory value) {
        super(value);
    }

    @Override
    public ModelCategory toPojo(ServiceNamespace serviceNamespace) {
        return new ModelCategory(this, serviceNamespace);
    }
}

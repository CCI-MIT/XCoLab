package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

import java.util.List;

public class ModelInputItemDto extends AbstractModelInputItem
        implements DataTransferObject<ModelInputItem> {

    public static final TypeProvider<ModelInputItemDto> TYPES = new TypeProvider<>(
            ModelInputItemDto.class,
            new ParameterizedTypeReference<List<ModelInputItemDto>>() {});

    public ModelInputItemDto() {
    }

    public ModelInputItemDto(AbstractModelInputItem value) {
        super(value);
    }

    @Override
    public ModelInputItem toPojo(ServiceNamespace serviceNamespace) {
        return new ModelInputItem(this, serviceNamespace);
    }
}

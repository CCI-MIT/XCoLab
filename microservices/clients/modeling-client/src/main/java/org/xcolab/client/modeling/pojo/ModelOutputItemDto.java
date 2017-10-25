package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

public class ModelOutputItemDto extends AbstractModelOutputItem
        implements DataTransferObject<ModelOutputItem> {

    public static final TypeProvider<ModelOutputItemDto> TYPES = new TypeProvider<>(
            ModelOutputItemDto.class,
            new ParameterizedTypeReference<List<ModelOutputItemDto>>() {});

    public ModelOutputItemDto() {
    }

    public ModelOutputItemDto(AbstractModelOutputItem value) {
        super(value);
    }

    @Override
    public ModelOutputItem toPojo(ServiceNamespace serviceNamespace) {
        return new ModelOutputItem(this, serviceNamespace);
    }
}

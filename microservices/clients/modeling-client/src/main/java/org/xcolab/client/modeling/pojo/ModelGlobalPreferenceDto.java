package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

public class ModelGlobalPreferenceDto extends AbstractModelGlobalPreference
        implements DataTransferObject<ModelGlobalPreference> {

    public static final TypeProvider<ModelGlobalPreferenceDto> TYPES = new TypeProvider<>(
            ModelGlobalPreferenceDto.class,
            new ParameterizedTypeReference<List<ModelGlobalPreferenceDto>>() {});

    public ModelGlobalPreferenceDto() {
    }

    public ModelGlobalPreferenceDto(
            AbstractModelGlobalPreference value) {
        super(value);
    }

    @Override
    public ModelGlobalPreference toPojo(ServiceNamespace serviceNamespace) {
        return new ModelGlobalPreference(this, serviceNamespace);
    }
}

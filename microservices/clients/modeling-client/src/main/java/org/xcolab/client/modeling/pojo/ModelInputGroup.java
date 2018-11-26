package org.xcolab.client.modeling.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ModelInputGroup extends AbstractModelInputGroup implements Serializable {

    public static final TypeProvider<ModelInputGroup> TYPES = new TypeProvider<>(ModelInputGroup.class,
            new ParameterizedTypeReference<List<ModelInputGroup>>() {});

    public ModelInputGroup() {}

    public ModelInputGroup(ModelInputGroup value) {
        super(value);
    }

    public ModelInputGroup(AbstractModelInputGroup modelInputGroup) {
        super(modelInputGroup);
    }
}

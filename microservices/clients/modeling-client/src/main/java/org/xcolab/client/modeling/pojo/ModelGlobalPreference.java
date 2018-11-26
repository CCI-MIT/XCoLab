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
public class ModelGlobalPreference extends AbstractModelGlobalPreference implements Serializable {

    public static final TypeProvider<ModelGlobalPreference> TYPES = new TypeProvider<>(ModelGlobalPreference.class,
            new ParameterizedTypeReference<List<ModelGlobalPreference>>() {});

    public ModelGlobalPreference() {}

    public ModelGlobalPreference(ModelGlobalPreference value) {
        super(value);
    }

    public ModelGlobalPreference(AbstractModelGlobalPreference modelGlobalPreference) {
        super(modelGlobalPreference);
    }
}

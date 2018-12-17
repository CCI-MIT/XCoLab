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
public class ModelPosition extends AbstractModelPosition implements Serializable {

    public static final TypeProvider<ModelPosition> TYPES = new TypeProvider<>(ModelPosition.class,
            new ParameterizedTypeReference<List<ModelPosition>>() {});

    public ModelPosition() {}

    public ModelPosition(ModelPosition value) {
        super(value);
    }

    public ModelPosition(AbstractModelPosition modelPosition) {
        super(modelPosition);
    }
}

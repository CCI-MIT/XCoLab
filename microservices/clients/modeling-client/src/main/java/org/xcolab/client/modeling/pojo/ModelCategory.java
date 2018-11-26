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
public class ModelCategory extends AbstractModelCategory implements Serializable {

    public static final TypeProvider<ModelCategory> TYPES = new TypeProvider<>(ModelCategory.class,
            new ParameterizedTypeReference<List<ModelCategory>>() {});

    public ModelCategory() {}

    public ModelCategory(ModelCategory value) {
        super(value);
    }

    public ModelCategory(AbstractModelCategory modelCategory) {
        super(modelCategory);
    }
}

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
public class ModelInputItem extends AbstractModelInputItem implements Serializable {

    public static final TypeProvider<ModelInputItem> TYPES = new TypeProvider<>(ModelInputItem.class,
            new ParameterizedTypeReference<List<ModelInputItem>>() {});

    public ModelInputItem() {}

    public ModelInputItem(ModelInputItem value) {
        super(value);
    }

    public ModelInputItem(AbstractModelInputItem modelInputItem) {
        super(modelInputItem);
    }
}

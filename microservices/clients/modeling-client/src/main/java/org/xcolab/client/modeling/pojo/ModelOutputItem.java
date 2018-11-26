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
public class ModelOutputItem extends AbstractModelOutputItem implements Serializable {

    public static final TypeProvider<ModelOutputItem> TYPES = new TypeProvider<>(ModelOutputItem.class,
            new ParameterizedTypeReference<List<ModelOutputItem>>() {});

    public ModelOutputItem() {}

    public ModelOutputItem(ModelOutputItem value) {
        super(value);
    }

    public ModelOutputItem(AbstractModelOutputItem modelOutputItem) {
        super(modelOutputItem);
    }
}

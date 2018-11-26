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
public class ModelDiscussion extends AbstractModelDiscussion implements Serializable {

    public static final TypeProvider<ModelDiscussion> TYPES = new TypeProvider<>(ModelDiscussion.class,
            new ParameterizedTypeReference<List<ModelDiscussion>>() {});

    public ModelDiscussion() {}

    public ModelDiscussion(ModelDiscussion value) {
        super(value);
    }

    public ModelDiscussion(AbstractModelDiscussion modelDiscussion) {
        super(modelDiscussion);
    }
}

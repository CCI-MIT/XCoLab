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
public class ModelOutputChartOrder extends AbstractModelOutputChartOrder implements Serializable {

    public static final TypeProvider<ModelOutputChartOrder> TYPES = new TypeProvider<>(ModelOutputChartOrder.class,
            new ParameterizedTypeReference<List<ModelOutputChartOrder>>() {});

    public ModelOutputChartOrder() {}

    public ModelOutputChartOrder(ModelOutputChartOrder value) {
        super(value);
    }

    public ModelOutputChartOrder(AbstractModelOutputChartOrder modelOutputChartOrder) {
        super(modelOutputChartOrder);
    }
}

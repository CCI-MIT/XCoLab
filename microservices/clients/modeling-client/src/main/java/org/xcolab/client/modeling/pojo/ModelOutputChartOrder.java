package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public class ModelOutputChartOrder extends AbstractModelOutputChartOrder {

    public static final TypeProvider<ModelOutputChartOrder> TYPES = new TypeProvider<>(ModelOutputChartOrder.class,
            new ParameterizedTypeReference<List<ModelOutputChartOrder>>() {});

    public ModelOutputChartOrder() {}

    public ModelOutputChartOrder(ModelOutputChartOrder value) {
        super(value);
    }

    public ModelOutputChartOrder(AbstractModelOutputChartOrder modelOutputChartOrder,
            ServiceNamespace serviceNamespace) {
        super(modelOutputChartOrder);
    }
}

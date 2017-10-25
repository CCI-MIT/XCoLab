package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.enums.ServiceNamespace;

public class ModelOutputChartOrder extends AbstractModelOutputChartOrder {

    public ModelOutputChartOrder() {}

    public ModelOutputChartOrder(ModelOutputChartOrder value) {
        super(value);
    }

    public ModelOutputChartOrder(AbstractModelOutputChartOrder modelOutputChartOrder,
            ServiceNamespace serviceNamespace) {
        super(modelOutputChartOrder);
    }
}

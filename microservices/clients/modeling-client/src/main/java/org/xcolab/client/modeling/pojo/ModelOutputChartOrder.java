package org.xcolab.client.modeling.pojo;

import org.xcolab.util.http.client.RestService;

public class ModelOutputChartOrder extends AbstractModelOutputChartOrder {

    public ModelOutputChartOrder() {}

    public ModelOutputChartOrder(ModelOutputChartOrder value) {
        super(value);
    }

    public ModelOutputChartOrder(AbstractModelOutputChartOrder modelOutputChartOrder,
            RestService restService) {
        super(modelOutputChartOrder);
    }
}

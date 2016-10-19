package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

public class ModelOutputChartOrderDto extends AbstractModelOutputChartOrder
        implements DataTransferObject<ModelOutputChartOrder> {

    public static final TypeProvider<ModelOutputChartOrderDto> TYPES = new TypeProvider<>(
            ModelOutputChartOrderDto.class,
            new ParameterizedTypeReference<List<ModelOutputChartOrderDto>>() {});

    public ModelOutputChartOrderDto() {
    }

    public ModelOutputChartOrderDto(
            AbstractModelOutputChartOrder value) {
        super(value);
    }

    @Override
    public ModelOutputChartOrder toPojo(RestService restService) {
        return new ModelOutputChartOrder(this, restService);
    }
}

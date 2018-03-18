package org.xcolab.client.modeling.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.client.types.TypeProvider;
import org.xcolab.commons.http.dto.DataTransferObject;

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
    public ModelOutputChartOrder toPojo(ServiceNamespace serviceNamespace) {
        return new ModelOutputChartOrder(this, serviceNamespace);
    }
}

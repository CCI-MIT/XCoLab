package org.xcolab.client.contest.pojo.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.dto.DataTransferObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PlanTemplateDto extends AbstractPlanTemplate
        implements DataTransferObject<PlanTemplate> {

    public static final TypeProvider<PlanTemplateDto> TYPES = new TypeProvider<>(PlanTemplateDto.class,
            new ParameterizedTypeReference<List<PlanTemplateDto>>() {
            });

    public PlanTemplateDto() {}

    public PlanTemplateDto(AbstractPlanTemplate value) {
        super(value);
    }

    @Override
    public PlanTemplate toPojo(ServiceNamespace serviceNamespace) {
        return new PlanTemplate(this, serviceNamespace);
    }
}

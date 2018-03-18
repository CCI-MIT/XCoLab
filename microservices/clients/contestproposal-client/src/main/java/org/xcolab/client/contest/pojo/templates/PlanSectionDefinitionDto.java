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
public class PlanSectionDefinitionDto extends AbstractPlanSectionDefinition
        implements DataTransferObject<PlanSectionDefinition> {

    public static final TypeProvider<PlanSectionDefinitionDto> TYPES = new TypeProvider<>(PlanSectionDefinitionDto.class,
            new ParameterizedTypeReference<List<PlanSectionDefinitionDto>>() {
            });

    public PlanSectionDefinitionDto() {}

    public PlanSectionDefinitionDto(AbstractPlanSectionDefinition value) {
        super(value);
    }

    @Override
    public PlanSectionDefinition toPojo(ServiceNamespace serviceNamespace) {
        return new PlanSectionDefinition(this, serviceNamespace);
    }
}

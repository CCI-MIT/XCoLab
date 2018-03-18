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
public class PlanTemplateSectionDto extends AbstractPlanTemplateSection
        implements DataTransferObject<PlanTemplateSection> {

    public static final TypeProvider<PlanTemplateSectionDto> TYPES = new TypeProvider<>(PlanTemplateSectionDto.class,
            new ParameterizedTypeReference<List<PlanTemplateSectionDto>>() {
            });

    public PlanTemplateSectionDto() {}

    public PlanTemplateSectionDto(AbstractPlanTemplateSection value) {
        super(value);
    }

    @Override
    public PlanTemplateSection toPojo(ServiceNamespace serviceNamespace) {
        return new PlanTemplateSection(this, serviceNamespace);
    }
}

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
public class ProposalTemplateSectionDefinitionDto extends AbstractProposalTemplateSectionDefinition
        implements DataTransferObject<ProposalTemplateSectionDefinition> {

    public static final TypeProvider<ProposalTemplateSectionDefinitionDto> TYPES = new TypeProvider<>(
            ProposalTemplateSectionDefinitionDto.class,
            new ParameterizedTypeReference<List<ProposalTemplateSectionDefinitionDto>>() {
            });

    public ProposalTemplateSectionDefinitionDto() {}

    public ProposalTemplateSectionDefinitionDto(AbstractProposalTemplateSectionDefinition value) {
        super(value);
    }

    @Override
    public ProposalTemplateSectionDefinition toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalTemplateSectionDefinition(this, serviceNamespace);
    }
}

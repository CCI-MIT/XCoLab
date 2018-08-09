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
public class ProposalTemplateSectionDto extends AbstractProposalTemplateSection
        implements DataTransferObject<ProposalTemplateSection> {

    public static final TypeProvider<ProposalTemplateSectionDto> TYPES = new TypeProvider<>(
            ProposalTemplateSectionDto.class,
            new ParameterizedTypeReference<List<ProposalTemplateSectionDto>>() {
            });

    public ProposalTemplateSectionDto() {}

    public ProposalTemplateSectionDto(AbstractProposalTemplateSection value) {
        super(value);
    }

    @Override
    public ProposalTemplateSection toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalTemplateSection(this, serviceNamespace);
    }
}

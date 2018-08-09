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
public class ProposalTemplateDto extends AbstractProposalTemplate
        implements DataTransferObject<ProposalTemplate> {

    public static final TypeProvider<ProposalTemplateDto> TYPES = new TypeProvider<>(
            ProposalTemplateDto.class,
            new ParameterizedTypeReference<List<ProposalTemplateDto>>() {
            });

    public ProposalTemplateDto() {}

    public ProposalTemplateDto(AbstractProposalTemplate value) {
        super(value);
    }

    @Override
    public ProposalTemplate toPojo(ServiceNamespace serviceNamespace) {
        return new ProposalTemplate(this, serviceNamespace);
    }
}

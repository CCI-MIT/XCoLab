package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalTemplateSection extends AbstractProposalTemplateSection
        implements Serializable {

    public static final TypeProvider<ProposalTemplateSection> TYPES =
            new TypeProvider<>(ProposalTemplateSection.class,
                    new ParameterizedTypeReference<List<ProposalTemplateSection>>() {});

    public ProposalTemplateSection() {}

    public ProposalTemplateSection(ProposalTemplateSection value) {
        super(value);
    }

    public ProposalTemplateSection(AbstractProposalTemplateSection abstractProposalTemplateSection) {
        super(abstractProposalTemplateSection);
    }
}

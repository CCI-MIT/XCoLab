package org.xcolab.client.contest.pojo.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ProposalTemplate extends AbstractProposalTemplate implements Serializable {

    public static final TypeProvider<ProposalTemplate> TYPES = new TypeProvider<>(
            ProposalTemplate.class, new ParameterizedTypeReference<List<ProposalTemplate>>() {});

    public ProposalTemplate() {}

    public ProposalTemplate(ProposalTemplate value) {
        super(value);
    }

    public ProposalTemplate(AbstractProposalTemplate abstractProposalTemplate) {
        super(abstractProposalTemplate);
    }
}

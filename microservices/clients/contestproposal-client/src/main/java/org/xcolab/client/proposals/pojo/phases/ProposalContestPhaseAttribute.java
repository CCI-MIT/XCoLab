package org.xcolab.client.proposals.pojo.phases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.Date;
import java.util.List;

public class ProposalContestPhaseAttribute  extends AbstractProposalContestPhaseAttribute{

    public static final TypeProvider<ProposalContestPhaseAttribute> TYPES =
            new TypeProvider<>(ProposalContestPhaseAttribute.class,
                    new ParameterizedTypeReference<List<ProposalContestPhaseAttribute>>() {});

    private final ServiceNamespace serviceNamespace;

    public ProposalContestPhaseAttribute() {
        serviceNamespace = ServiceNamespace.instance();
    }

    public ProposalContestPhaseAttribute(ProposalContestPhaseAttribute value) {
        super(value);
        serviceNamespace = ServiceNamespace.instance();
    }

    public ProposalContestPhaseAttribute(
            AbstractProposalContestPhaseAttribute abstractProposalContestPhaseAttribute,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalContestPhaseAttribute);
        this.serviceNamespace = serviceNamespace;
    }

    @JsonIgnore
    public Date getStartDate() {
        return ContestClient.fromNamespace(serviceNamespace)
                .getContestPhase(getContestPhaseId()).getPhaseStartDateDt();
    }
}

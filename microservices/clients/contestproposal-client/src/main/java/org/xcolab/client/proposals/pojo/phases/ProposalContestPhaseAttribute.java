package org.xcolab.client.proposals.pojo.phases;


import com.fasterxml.jackson.annotation.JsonIgnore;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.Date;

public class ProposalContestPhaseAttribute  extends AbstractProposalContestPhaseAttribute{

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

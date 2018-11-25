package org.xcolab.client.proposals.pojo.phases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.Date;
import java.util.List;

public class ProposalContestPhaseAttribute  extends AbstractProposalContestPhaseAttribute{

    public static final TypeProvider<ProposalContestPhaseAttribute> TYPES =
            new TypeProvider<>(ProposalContestPhaseAttribute.class,
                    new ParameterizedTypeReference<List<ProposalContestPhaseAttribute>>() {});


    public ProposalContestPhaseAttribute() {
    }

    public ProposalContestPhaseAttribute(ProposalContestPhaseAttribute value) {
        super(value);
    }

    public ProposalContestPhaseAttribute(
            AbstractProposalContestPhaseAttribute abstractProposalContestPhaseAttribute) {
        super(abstractProposalContestPhaseAttribute);
    }

    @JsonIgnore
    public Date getStartDate() {
        return ContestClientUtil.getClient()
                .getContestPhase(getContestPhaseId()).getPhaseStartDateDt();
    }
}

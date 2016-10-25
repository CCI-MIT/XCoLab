package org.xcolab.client.proposals.pojo.phases;

import org.xcolab.util.http.client.RestService;

public class ProposalContestPhaseAttribute  extends AbstractProposalContestPhaseAttribute{

    public ProposalContestPhaseAttribute() {}

    public ProposalContestPhaseAttribute(ProposalContestPhaseAttribute value) {
        super(value);
    }

    public ProposalContestPhaseAttribute(
            Long id_,
            Long proposalid,
            Long contestphaseid,
            String name,
            Long additionalid,
            Long numericvalue,
            String stringvalue,
            Double realvalue
    ) {
        super(id_, proposalid, contestphaseid, name, additionalid,
                numericvalue, stringvalue, realvalue);
    }

    public ProposalContestPhaseAttribute(
            AbstractProposalContestPhaseAttribute abstractProposalContestPhaseAttribute,
            RestService restService) {
        super(abstractProposalContestPhaseAttribute);
    }
}

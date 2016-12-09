package org.xcolab.portlets.proposals.wrappers;

import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.points.PointsTarget;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;

public class PointsTargetProposalWrapper {

    private final PointsTarget target;
    private final double percentageIn;

    public PointsTargetProposalWrapper(PointsTarget target, double percentageIn) {

        this.target = target;
        this.percentageIn = percentageIn;
    }

    public Proposal getProposal() {
        try {
            return ProposalClientUtil.getProposal(target.getProposalId());
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    public double getPercentage() {
        return target.getPercentage() * percentageIn;
    }
}

package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.points.PointsTarget;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;

public class PointsTargetProposalWrapper {

    private final PointsTarget target;
    private final double percentageIn;

    public PointsTargetProposalWrapper(PointsTarget target, double percentageIn) {

        this.target = target;
        this.percentageIn = percentageIn;
    }

    public ProposalWrapper getProposal() {
        try {
            return StaticProposalContext.getProposalClient().getProposal(target.getProposalId());
        } catch (ProposalNotFoundException ignored) {
            return null;
        }
    }

    public double getPercentage() {
        return target.getPercentage() * percentageIn;
    }
}

package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.io.Serializable;
import java.util.List;

public class ProposalRatingTypeWrapper implements Serializable {

    private final IProposalRatingType proposalRatingType;

    public ProposalRatingTypeWrapper(IProposalRatingType proposalRatingType) {
        this.proposalRatingType = proposalRatingType;
    }

    public List<IProposalRatingValue> getRatingValues() {
        return StaticProposalContext.getProposalJudgeRatingClient()
                .getProposalRatingValuesByProposalRatingTypeId(this.proposalRatingType.getId());
    }

    public Long getId() {
        return this.proposalRatingType.getId();
    }

    public String getLabel() {
        return this.proposalRatingType.getLabel();
    }

    public String getDescription() {
        return this.proposalRatingType.getDescription();
    }

    public boolean getIsActive()
    {
        return this.proposalRatingType.isIsActive();
    }

    public void setIsActive(boolean isActive){
        this.proposalRatingType.setIsActive(isActive);
    }

}

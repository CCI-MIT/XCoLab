package org.xcolab.portlets.proposals.wrappers;



import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingValue;

import java.util.List;

/**
 * Created by Manuel Thurner
 */
public class ProposalRatingTypeWrapper {
    private ProposalRatingType proposalRatingType;


    public ProposalRatingTypeWrapper(ProposalRatingType proposalRatingType) {
        this.proposalRatingType = proposalRatingType;
    }

    public List<ProposalRatingValue> getRatingValues() throws SystemException {

        return ProposalJudgeRatingClientUtil.getProposalRatingValuesByProposalRatingTypeId(this.proposalRatingType.getId_());
    }

    public Long getId() {
        return this.proposalRatingType.getId_();
    }

    public String getLabel() {
        return this.proposalRatingType.getLabel();
    }
    public String getDescription() {
        return this.proposalRatingType.getDescription();
    }

    public boolean getIsActive()
    {
        return this.proposalRatingType.getIsActive();
    }

    public void setIsActive(boolean isActive){
        this.proposalRatingType.setIsActive(isActive);
    }

}

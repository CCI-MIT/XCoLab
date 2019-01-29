package org.xcolab.view.pages.proposals.judging;

import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingValue;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

public class ProposalRatingWrapper {
    private ProposalRating proposalRating;

    public ProposalRatingWrapper(ProposalRating proposalRating) {
        this.proposalRating = proposalRating;
    }

    public ProposalRatingWrapper() {
    }

    public String getRatingValueName() {
        ProposalRatingValue ratingValue = this.getRatingValue();
        if (ratingValue != null) {
            return ratingValue.getName();
        } else {
            return "";
        }
    }

    public String getRatingTypeLabel() {
        ProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.getLabel();
        } else {
            return "";
        }
    }
    public Long getRatingTypeId() {
        ProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.getId();
        } else {
            return null;
        }
    }

    public ProposalRatingType getRatingType()  {
        ProposalRatingValue ratingValue = this.getRatingValue();
            if (ratingValue != null) {
                return ProposalJudgeRatingClientUtil.getProposalRatingType(ratingValue.getRatingTypeId());
            }

        return null;
    }

    public ProposalRatingValue getRatingValue()  {
        return ProposalJudgeRatingClientUtil.getProposalRatingValue(this.proposalRating.getRatingValueId());
    }

    public UserWrapper getUser()  {
        try {
            return StaticUserContext.getUserClient().getMember(this.proposalRating.getUserId());
        } catch (MemberNotFoundException e) {
            return null;
        }
    }

    public ProposalRating unwrap() {
        return proposalRating;
    }

    public void setRatingValueId(Long id) {
        this.proposalRating.setRatingValueId(id);
    }
}

package org.xcolab.view.pages.proposals.judging;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingValue;

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
            return ratingType.getId_();
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

    public Member getUser()  {
        try {
            return MembersClient.getMember(this.proposalRating.getUserId());
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

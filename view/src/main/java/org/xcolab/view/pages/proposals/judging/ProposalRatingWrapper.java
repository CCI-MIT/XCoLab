package org.xcolab.view.pages.proposals.judging;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

public class ProposalRatingWrapper {
    private org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper proposalRating;

    public ProposalRatingWrapper(
            org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper proposalRating) {
        this.proposalRating = proposalRating;
    }

    public ProposalRatingWrapper() {
    }

    public String getRatingValueName() {
        IProposalRatingValue ratingValue = this.getRatingValue();
        if (ratingValue != null) {
            return ratingValue.getName();
        } else {
            return "";
        }
    }

    public String getRatingTypeLabel() {
        IProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.getLabel();
        } else {
            return "";
        }
    }
    public Long getRatingTypeId() {
        IProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.getId();
        } else {
            return null;
        }
    }

    public IProposalRatingType getRatingType()  {
        IProposalRatingValue ratingValue = this.getRatingValue();
            if (ratingValue != null) {
                return StaticProposalContext.getProposalJudgeRatingClient()
                        .getProposalRatingType(ratingValue.getRatingTypeId());
            }

        return null;
    }

    public IProposalRatingValue getRatingValue()  {
        return StaticProposalContext.getProposalJudgeRatingClient()
                .getProposalRatingValue(this.proposalRating.getRatingValueId());
    }

    public Member getUser()  {
        try {
            return MembersClient.getMember(this.proposalRating.getUserId());
        } catch (MemberNotFoundException e) {
            return null;
        }
    }

    public org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper unwrap() {
        return proposalRating;
    }

    public void setRatingValueId(Long id) {
        this.proposalRating.setRatingValueId(id);
    }
}

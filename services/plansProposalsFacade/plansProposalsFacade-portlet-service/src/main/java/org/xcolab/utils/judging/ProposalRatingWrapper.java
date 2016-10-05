package org.xcolab.utils.judging;



import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalRatingClient;
import org.xcolab.client.proposals.pojo.ProposalRating;
import org.xcolab.client.proposals.pojo.ProposalRatingType;
import org.xcolab.client.proposals.pojo.ProposalRatingValue;

/**
 * Created by Manuel Thurner
 */
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
                ProposalRatingType ratingType = ProposalRatingClient.getProposalRatingType(ratingValue.getRatingTypeId());
                return ratingType;
            }

        return null;
    }

    public ProposalRatingValue getRatingValue()  {

            ProposalRatingValue ratingValue = ProposalRatingClient.getProposalRatingValue(this.proposalRating.getRatingValueId());
            return ratingValue;

    }

    public Member getUser()  {
        try {
            Member u = MembersClient.getMember(this.proposalRating.getUserId());
            return u;
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

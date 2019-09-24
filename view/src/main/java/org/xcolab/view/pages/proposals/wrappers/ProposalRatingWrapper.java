package org.xcolab.view.pages.proposals.wrappers;


import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.text.DecimalFormat;

/**
 * Created by Manuel Thurner
 */
public class ProposalRatingWrapper {
    private org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper proposalRating;
    private IProposalRatingType ratingType;
    private IProposalRatingValue ratingValue;
    private Long roundFactor = 1L;

    public ProposalRatingWrapper(
            org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper proposalRating) {
        this.proposalRating = proposalRating;
    }

    public ProposalRatingWrapper(
            org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper proposalRating, Long roundFactor) {
        this.proposalRating = proposalRating;
        this.roundFactor = roundFactor;
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

    public boolean getIsActive() {
        IProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.isIsActive();
        } else {
            return true;
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

    public IProposalRatingType getRatingType() {
        IProposalRatingValue ratingValue = this.getRatingValue();
        if (ratingValue != null) {
            if (ratingType == null)
                ratingType = StaticProposalContext.getProposalJudgeRatingClient()
                        .getProposalRatingType(ratingValue.getRatingTypeId());
            return ratingType;
        }

        return null;
    }

    public IProposalRatingValue getRatingValue() {
        if (ratingValue == null)
            if (roundFactor == null) {
                roundFactor = 1L;
            }
        ratingValue = StaticProposalContext.getProposalJudgeRatingClient()
                .getProposalRatingValue(this.proposalRating.getRatingValueId() / roundFactor);
        return ratingValue;
    }

    public double getNotRoundedRatingValue() {
        double ratingValueNotRounded = 0.;
        try {
            if (roundFactor == null) {
                roundFactor = 1L;
            }
            ratingValueNotRounded = (double) this.proposalRating.getRatingValueId() / (double) roundFactor;
            ratingValueNotRounded = ratingValueNotRounded / getRatingTypeId();
        } catch (Exception e) {
        }
        return ratingValueNotRounded;
    }

    public String getNotRoundedRatingValueFormatted() {
        DecimalFormat f = new DecimalFormat("#0.0");
        return f.format(getNotRoundedRatingValue());
    }

    public double getRatingValueInPercent() {
        double ratingValueInPercent = 0;
        Double proposalRatingValue = getNotRoundedRatingValue();
        if (proposalRatingValue != null) {
            ratingValueInPercent = proposalRatingValue / 5.0 * 100.0;
        }
        return ratingValueInPercent;
    }

    public org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper unwrap() {
        return proposalRating;
    }

    public void setRatingValueId(Long id) {
        this.proposalRating.setRatingValueId(id);
    }
}

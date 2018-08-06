package org.xcolab.view.pages.proposals.wrappers;


import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingValue;

import java.text.DecimalFormat;

/**
 * Created by Manuel Thurner
 */
public class ProposalRatingWrapper {
    private ProposalRating proposalRating;
    private ProposalRatingType ratingType;
    private ProposalRatingValue ratingValue;
    private Long roundFactor = 1L;

    public ProposalRatingWrapper(ProposalRating proposalRating) {
        this.proposalRating = proposalRating;
    }

    public ProposalRatingWrapper(ProposalRating proposalRating, Long roundFactor) {
        this.proposalRating = proposalRating;
        this.roundFactor = roundFactor;
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

    public boolean getIsActive() {
        ProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.getIsActive();
        } else {
            return true;
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

    public ProposalRatingType getRatingType() {
        ProposalRatingValue ratingValue = this.getRatingValue();
        if (ratingValue != null) {
            if (ratingType == null)
                ratingType = ProposalJudgeRatingClientUtil.getProposalRatingType(ratingValue.getRatingTypeId());
            return ratingType;
        }

        return null;
    }

    public ProposalRatingValue getRatingValue() {
        if (ratingValue == null)
            if (roundFactor == null) {
                roundFactor = 1L;
            }
        ratingValue = ProposalJudgeRatingClientUtil
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

    public ProposalRating unwrap() {
        return proposalRating;
    }

    public void setRatingValueId(Long id) {
        this.proposalRating.setRatingValueId(id);
    }
}

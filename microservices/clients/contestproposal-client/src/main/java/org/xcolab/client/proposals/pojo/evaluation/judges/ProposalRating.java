package org.xcolab.client.proposals.pojo.evaluation.judges;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.text.DecimalFormat;

public class ProposalRating extends AbstractProposalRating {

    private ProposalRatingType ratingType;
    private ProposalRatingValue ratingValue;
    private Long roundFactor = 1L;

    public ProposalRating() {}

    public ProposalRating(ProposalRating value) {
        super(value);
    }

    public ProposalRating(
            Long id,
            Long proposalid,
            Long contestphaseid,
            Long userid,
            Long ratingvalueid,
            String comment_,
            Boolean commentenabled,
            String otherdatastring,
            Boolean onlyforinternalusage
    ) {
        super(id, proposalid, contestphaseid, userid, ratingvalueid, comment_, commentenabled,
                otherdatastring, onlyforinternalusage);
    }

    public ProposalRating(AbstractProposalRating abstractProposalRating,
            ServiceNamespace serviceNamespace) {
        super(abstractProposalRating);
    }

    public boolean isRatingComplete() {
        final boolean commentComplete = !getCommentEnabled()
                || !StringUtils.isEmpty(this.getComment());
        return getRatingValueId() > 0 && commentComplete;
    }

    public ProposalRating(ProposalRating proposalRating, Long roundFactor) {
        super(proposalRating);
        this.roundFactor = roundFactor;
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
                .getProposalRatingValue(this.getRatingValueId() / roundFactor);
        return ratingValue;
    }

    public double getNotRoundedRatingValue() {
        double ratingValueNotRounded = 0.;
        try {
            if (roundFactor == null) {
                roundFactor = 1L;
            }
            ratingValueNotRounded = (double) this.getRatingValueId() / (double) roundFactor;
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
}

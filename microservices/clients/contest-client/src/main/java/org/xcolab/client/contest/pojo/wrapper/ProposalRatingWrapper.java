package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.IProposalRatingType;
import org.xcolab.client.contest.pojo.IProposalRatingValue;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalRating;
import org.xcolab.client.contest.proposals.StaticProposalContext;

import java.io.Serializable;
import java.text.DecimalFormat;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalRatingWrapper extends ProposalRating implements Serializable {

    private IProposalRatingType ratingType;
    private IProposalRatingValue ratingValue;
    private Long roundFactor = 1L;

    public ProposalRatingWrapper() {}

    public ProposalRatingWrapper(ProposalRatingWrapper value) {
        super(value);
    }

    public ProposalRatingWrapper(
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

    public ProposalRatingWrapper(ProposalRating abstractProposalRating) {
        super(abstractProposalRating);
    }

    public boolean isRatingComplete() {
        final boolean commentComplete = !isCommentEnabled()
                || !StringUtils.isEmpty(this.getComment());
        return getRatingValueId() > 0 && commentComplete;
    }

    public ProposalRatingWrapper(ProposalRatingWrapper proposalRating, Long roundFactor) {
        super(proposalRating);
        this.roundFactor = roundFactor;
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

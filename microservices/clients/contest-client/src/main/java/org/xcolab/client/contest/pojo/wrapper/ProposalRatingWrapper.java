package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public ProposalRatingWrapper(Long id, Long proposalId, Long contestPhaseId, Long userId,
            Long ratingValueId, String comment, Boolean commentEnabled, String otherDataString,
            Boolean onlyForInternalUsage) {
        super(id, proposalId, contestPhaseId, userId, ratingValueId, comment, commentEnabled,
                otherDataString, onlyForInternalUsage);
    }

    public ProposalRatingWrapper(ProposalRating abstractProposalRating) {
        super(abstractProposalRating);
    }

    @JsonIgnore
    public boolean isRatingComplete() {
        final boolean commentComplete =
                !isCommentEnabled() || !StringUtils.isEmpty(this.getComment());
        return getRatingValueId() > 0 && commentComplete;
    }

    @JsonIgnore
    public ProposalRatingWrapper(ProposalRatingWrapper proposalRating, Long roundFactor) {
        super(proposalRating);
        this.roundFactor = roundFactor;
    }

    @JsonIgnore
    public String getRatingValueName() {
        IProposalRatingValue ratingValue = this.getRatingValue();
        if (ratingValue != null) {
            return ratingValue.getName();
        } else {
            return "";
        }
    }

    @JsonIgnore
    public String getRatingTypeLabel() {
        IProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.getLabel();
        } else {
            return "";
        }
    }

    @JsonIgnore
    public boolean getIsActive() {
        IProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.isIsActive();
        } else {
            return true;
        }
    }

    @JsonIgnore
    public Long getRatingTypeId() {
        IProposalRatingType ratingType = this.getRatingType();
        if (ratingType != null) {
            return ratingType.getId();
        } else {
            return null;
        }
    }

    @JsonIgnore
    public IProposalRatingType getRatingType() {
        IProposalRatingValue ratingValue = this.getRatingValue();
        if (ratingValue != null) {
            if (ratingType == null) {
                ratingType = StaticProposalContext.getProposalJudgeRatingClient()
                        .getProposalRatingType(ratingValue.getRatingTypeId());
            }
            return ratingType;
        }

        return null;
    }

    @JsonIgnore
    public IProposalRatingValue getRatingValue() {
        if (ratingValue == null) {
            if (roundFactor == null) {
                roundFactor = 1L;
            }
        }
        ratingValue = StaticProposalContext.getProposalJudgeRatingClient()
                .getProposalRatingValue(this.getRatingValueId() / roundFactor);
        return ratingValue;
    }

    @JsonIgnore
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

    @JsonIgnore
    public String getNotRoundedRatingValueFormatted() {
        DecimalFormat f = new DecimalFormat("#0.0");
        return f.format(getNotRoundedRatingValue());
    }

    @JsonIgnore
    public double getRatingValueInPercent() {
        double ratingValueInPercent = 0;
        Double proposalRatingValue = getNotRoundedRatingValue();
        if (proposalRatingValue != null) {
            ratingValueInPercent = proposalRatingValue / 5.0 * 100.0;
        }
        return ratingValueInPercent;
    }
}

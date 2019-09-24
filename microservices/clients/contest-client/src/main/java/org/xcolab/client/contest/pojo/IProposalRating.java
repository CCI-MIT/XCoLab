package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.ProposalRatingWrapper;

@JsonDeserialize(as = ProposalRatingWrapper.class)
public interface IProposalRating {

    Long getId();

    void setId(Long id);

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getContestPhaseId();

    void setContestPhaseId(Long contestPhaseId);

    Long getUserId();

    void setUserId(Long userId);

    Long getRatingValueId();

    void setRatingValueId(Long ratingValueId);

    String getComment();

    void setComment(String comment);

    Boolean isCommentEnabled();

    void setCommentEnabled(Boolean commentEnabled);

    String getOtherDataString();

    void setOtherDataString(String otherDataString);

    Boolean isOnlyForInternalUsage();

    void setOnlyForInternalUsage(Boolean onlyForInternalUsage);
}

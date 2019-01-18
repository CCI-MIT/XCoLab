package org.xcolab.client.contest.pojo;

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

    Boolean getCommentEnabled();

    void setCommentEnabled(Boolean commentEnabled);

    String getOtherDataString();

    void setOtherDataString(String otherDataString);

    Boolean getOnlyForInternalUsage();

    void setOnlyForInternalUsage(Boolean onlyForInternalUsage);
}

package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMembershipRequestWrapper;

import java.sql.Timestamp;

@JsonDeserialize(as = ProposalTeamMembershipRequestWrapper.class)
public interface IProposalTeamMembershipRequest {

    Long getId();

    void setId(Long id);

    Long getUserId();

    void setUserId(Long userId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Long getProposalId();

    void setProposalId(Long proposalId);

    String getComments();

    void setComments(String comments);

    String getReplyComments();

    void setReplyComments(String replyComments);

    Timestamp getReplyDate();

    void setReplyDate(Timestamp replyDate);

    Long getReplierUserId();

    void setReplierUserId(Long replierUserId);

    Integer getStatusId();

    void setStatusId(Integer statusId);
}

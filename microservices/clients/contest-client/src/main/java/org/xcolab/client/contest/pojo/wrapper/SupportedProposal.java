package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.builder.ToStringBuilder;

import org.xcolab.client.contest.pojo.IProposalSupporter;

import java.io.Serializable;
import java.sql.Timestamp;

// TODO COLAB-2356: rethink inheritance structure
// the inheritance structure between this SupportedProposal(Dto) and Proposal(Dto) is limited
// by single inheritance as they both (should) inherit from abstract classes
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupportedProposal extends ProposalWrapper {

    private Timestamp supportDate;
    private Long supporterUserId;

    public SupportedProposal() {
    }

    public SupportedProposal(SupportedProposal value) {
        super(value);
        this.supportDate = value.getSupportDate();
        this.supporterUserId = value.getSupporterUserId();
    }

    public SupportedProposal(ProposalWrapper proposal, IProposalSupporter proposalSupporter) {
        super(proposal);
        setSupportDate(proposalSupporter.getCreatedAt());
        setSupporterUserId(proposalSupporter.getUserId());
    }

    public Long getSupporterUserId() {
        return supporterUserId;
    }

    public void setSupporterUserId(Long supporterUserId) {
        this.supporterUserId = supporterUserId;
    }

    public Timestamp getSupportDate() {
        return supportDate;
    }

    public void setSupportDate(Timestamp supportDate) {
        this.supportDate = supportDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("proposalId", getId())
                .append("supportDate", supportDate)
                .append("supporterUserId", supporterUserId).toString();
    }
}

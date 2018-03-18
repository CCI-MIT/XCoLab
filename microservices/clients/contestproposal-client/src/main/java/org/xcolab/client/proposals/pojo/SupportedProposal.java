package org.xcolab.client.proposals.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.sql.Timestamp;

// TODO COLAB-2356: rethink inheritance structure
// the inheritance structure between this SupportedProposal(Dto) and Proposal(Dto) is limited
// by single inheritance as they both (should) inherit from abstract classes

public class SupportedProposal extends Proposal {

    private Timestamp supportDate;
    private Long supporterUserId;

    public SupportedProposal() {
    }

    public SupportedProposal(SupportedProposalDto value, ServiceNamespace serviceNamespace) {
        super(value, serviceNamespace);
        this.supportDate = value.getSupportDate();
        this.supporterUserId = value.getSupporterUserId();
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
                .append("proposalId", getProposalId())
                .append("supportDate", supportDate)
                .append("supporterUserId", supporterUserId).toString();
    }
}

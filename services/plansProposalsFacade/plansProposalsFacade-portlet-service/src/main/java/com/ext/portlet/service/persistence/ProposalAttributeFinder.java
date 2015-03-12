package com.ext.portlet.service.persistence;

public interface ProposalAttributeFinder {
    public java.util.List<com.ext.portlet.model.ProposalAttribute> findByProposalIdVersionGreaterThanVersionWhenCreatedLessThanNameLikeImpact(
        long proposalId, long version, long versionWhenCreated);
}

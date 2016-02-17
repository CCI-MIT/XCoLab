package com.ext.portlet.service.persistence;

public interface ProposalFinder {
    public int getProposalMaterializedPoints(long proposalId);

    public int getProposalHypotheticalPoints(long proposalId);
}

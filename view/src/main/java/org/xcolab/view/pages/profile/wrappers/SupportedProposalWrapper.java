package org.xcolab.view.pages.profile.wrappers;

import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalSupporter;

import java.io.Serializable;
import java.util.Date;

public class SupportedProposalWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ProposalSupporter proposalSupporter;
    private Proposal proposalWrapper;

    public SupportedProposalWrapper(ProposalSupporter ps) {
        this.proposalSupporter = ps;
        try {
            this.proposalWrapper = (ProposalClientUtil.getProposal(ps.getProposalId()));
        } catch (ProposalNotFoundException ignored) {
            this.proposalWrapper = null;
        }
    }

    public Date getSupportedSinceDate() {
        return proposalSupporter.getCreateDate();
    }

    public Proposal getProposalWrapper() {
        return proposalWrapper;
    }
}

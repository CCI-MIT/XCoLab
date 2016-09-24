package org.xcolab.portlets.userprofile.wrappers;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.ProposalSupporter;
import org.xcolab.wrappers.BaseProposalWrapper;

import java.io.Serializable;
import java.util.Date;

public class SupportedProposalWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ProposalSupporter proposalSupporter;
    private  BaseProposalWrapper proposalWrapper;

    public SupportedProposalWrapper(ProposalSupporter ps) throws SystemException, PortalException {
        this.proposalSupporter = ps;
        try {
            this.proposalWrapper = new BaseProposalWrapper(ProposalsClient.getProposal(ps.getProposalId()));
        }catch (ProposalNotFoundException ignored){
            this.proposalWrapper = null;
        }
    }

    public Date getSupportedSinceDate() {
        return proposalSupporter.getCreateDate();
    }

    public BaseProposalWrapper getProposalWrapper() {
        return proposalWrapper;
    }
}

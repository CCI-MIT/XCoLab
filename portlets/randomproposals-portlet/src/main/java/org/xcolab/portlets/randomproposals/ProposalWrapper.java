package org.xcolab.portlets.randomproposals;

import com.ext.portlet.NoSuchContestException;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;


import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.wrappers.BaseProposalWrapper;

public class ProposalWrapper extends BaseProposalWrapper {

	public ProposalWrapper(Proposal proposal) throws NoSuchContestException {
		super(proposal);
	}

	public Long getImage() {
		return proposalAttributeHelper.getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0);
	}
}

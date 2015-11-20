package org.xcolab.portlets.randomproposals;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.wrappers.BaseProposalWrapper;

public class ProposalWrapper extends BaseProposalWrapper {

	public ProposalWrapper(Proposal proposal) throws NoSuchContestException {
		super(proposal);
	}

	public Long getImage() throws SystemException, PortalException {
		try {
			ProposalAttribute attr = ProposalLocalServiceUtil.getAttribute(
					proposal.getProposalId(), ProposalAttributeKeys.IMAGE_ID, 0);
			return attr == null ? 0 : attr.getNumericValue();
		} catch (NoSuchProposalAttributeException nsae) {
			return null;
		}
	}
}

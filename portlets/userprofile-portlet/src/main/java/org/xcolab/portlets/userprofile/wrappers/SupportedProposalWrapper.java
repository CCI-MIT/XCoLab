package org.xcolab.portlets.userprofile.wrappers;

import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.wrappers.BaseProposalWrapper;

import java.io.Serializable;
import java.util.Date;

public class SupportedProposalWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	private final ProposalSupporter proposalSupporter;
    private final BaseProposalWrapper proposalWrapper;

    public SupportedProposalWrapper(ProposalSupporter ps) throws SystemException, PortalException {
        this.proposalSupporter = ps;
        this.proposalWrapper = new BaseProposalWrapper(ProposalLocalServiceUtil.getProposal(ps.getProposalId()));
    }

    public Date getSupportSinceDate() {
        return proposalSupporter.getCreateDate();
    }

    public BaseProposalWrapper getProposalWrapper() {
        return proposalWrapper;
    }
}

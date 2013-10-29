package org.xcolab.portlets.userprofile;

import java.io.Serializable;
import java.util.Date;

import com.ext.portlet.NoSuchPlanItemException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class SupportedPlanBean implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProposalSupporter supportedPlanInfo;
    private Proposal proposal;
    
    public SupportedPlanBean(ProposalSupporter ps) throws SystemException, PortalException {
        this.supportedPlanInfo = ps;
        this.proposal = ProposalLocalServiceUtil.getProposal(ps.getProposalId());
    }
    
    public String getPlanName() throws SystemException, PortalException {
        return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();
    }
    
    public Date getCreatedDate() {
        return supportedPlanInfo.getCreateDate();
    }
    
    public Long getPlanId() {
        return proposal.getProposalId();
    }
    
    public Long getContestId() throws PortalException, SystemException {
        return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(proposal.getProposalId()).getContestPK();
    }
    
    

}

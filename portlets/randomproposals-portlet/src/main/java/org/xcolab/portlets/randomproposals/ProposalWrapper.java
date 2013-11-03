package org.xcolab.portlets.randomproposals;


import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ProposalWrapper {
    
    private Proposal wrapped;
    
    
    public ProposalWrapper(Proposal proposal) {
        wrapped = proposal;
    }
    
    public String getName() throws SystemException, PortalException {
        ProposalAttribute attr =  ProposalLocalServiceUtil.getAttribute(wrapped.getProposalId(), ProposalAttributeKeys.NAME,0);
        return attr == null ? "" : attr.getStringValue();
    }
    
    public Long getImage() throws SystemException, PortalException {
        ProposalAttribute attr =  ProposalLocalServiceUtil.getAttribute(wrapped.getProposalId(), ProposalAttributeKeys.IMAGE_ID,0);
        return attr == null ? 0 : attr.getNumericValue();
    }

    public String getPitch() throws SystemException, PortalException  {
        ProposalAttribute attr = ProposalLocalServiceUtil.getAttribute(wrapped.getProposalId(), ProposalAttributeKeys.PITCH,0);
        return attr == null ? "" : attr.getStringValue();
    }
    
    public Long getContestId() throws PortalException, SystemException {
        return Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(wrapped.getProposalId()).getContestPK();
    }
    
    public Long getProposalId() {
        return wrapped.getProposalId();
    }
}

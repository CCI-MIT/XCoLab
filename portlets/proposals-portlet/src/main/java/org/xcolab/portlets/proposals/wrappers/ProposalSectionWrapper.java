package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ProposalSectionWrapper {
    
    private PlanSectionDefinition definition;
    private Proposal proposal;
    
    public ProposalSectionWrapper(PlanSectionDefinition definition, Proposal proposal) {
        super();
        this.definition = definition;
        this.proposal = proposal;
    }
    
    
    public String getTitle() {
        return definition.getTitle();
    }
    
    public String getContent() throws PortalException, SystemException {
        try {
            return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), "SECTION", definition.getId()).getStringValue();
        }
        catch (NoSuchProposalAttributeException e) {
            return null;
        }
    }

}

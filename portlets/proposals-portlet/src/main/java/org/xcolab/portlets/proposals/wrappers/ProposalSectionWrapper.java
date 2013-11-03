package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ProposalSectionWrapper {
    
    private PlanSectionDefinition definition;
    private Proposal proposal;
    private Integer version;
    
    public ProposalSectionWrapper(PlanSectionDefinition definition, Proposal proposal) {
        super();
        this.definition = definition;
        this.proposal = proposal;
    }
    
    public ProposalSectionWrapper(PlanSectionDefinition definition, Proposal proposal, int version) {
        super();
        this.definition = definition;
        this.proposal = proposal;
        this.version = version;
    }
    
    
    public String getTitle() {
        return definition.getTitle();
    }
    
    public String getContent() throws PortalException, SystemException {
        try {
            if (version != null && version > 0) {
                return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), version, "SECTION", definition.getId()).getStringValue().trim();
            }
            else {
                return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), "SECTION", definition.getId()).getStringValue().trim();
            }
        }
        catch (NoSuchProposalAttributeException e) {
            return null;
        }
        catch (NoSuchProposalException e) {
            return null;
        }
    }


    public Long getSectionDefinitionId() {
        return definition.getId();
    }

    public boolean isLocked() {
        return definition.getLocked();
    }
    
    public int getCharacterLimit() {
        return definition.getCharacterLimit();
    }
    
    public String getHelpText() {
        return definition.getHelpText();
    }
}

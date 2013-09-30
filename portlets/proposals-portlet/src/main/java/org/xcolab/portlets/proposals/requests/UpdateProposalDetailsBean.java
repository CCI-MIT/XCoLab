package org.xcolab.portlets.proposals.requests;

import java.util.HashMap;
import java.util.Map;

import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class UpdateProposalDetailsBean {
    private Map<Object, ProposalSectionWrapper> sections = new HashMap<Object, ProposalSectionWrapper>();
    private Proposal proposal;
    
    public UpdateProposalDetailsBean(Proposal proposal) throws PortalException, SystemException {
        for (ProposalSectionWrapper section: new ProposalWrapper(proposal).getSections()) {
            sections.put(section.getSectionDefinitionId(), section);
        }
    }
    
    public Map<Object, ProposalSectionWrapper> getSections() {
        return sections;
    }

    public void setSections(Map<Object, ProposalSectionWrapper> sections) {
        this.sections = sections;
    }
    
    
}

package org.xcolab.portlets.proposals.requests;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class UpdateProposalDetailsBean {
    private Map<Long, String> sectionsContent = new HashMap<Long, String>();  
    private String pitch;
    
    public UpdateProposalDetailsBean(ProposalWrapper proposal) throws PortalException, SystemException {
        for (ProposalSectionWrapper section: proposal.getSections()) {
            sectionsContent.put(section.getSectionDefinitionId(), section.getContent());
        }
    }

    public UpdateProposalDetailsBean() {
    }
    public Map<Long, String> getSectionsContent() {
        return sectionsContent;
    }
    public void setSectionsContent(Map<Long, String> sectionsContent) {
        this.sectionsContent = sectionsContent;
    }
    public String getPitch() {
        return pitch;
    }
    public void setPitch(String pitch) {
        this.pitch = pitch;
    }
    
    
}

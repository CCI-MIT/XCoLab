package org.xcolab.portlets.proposals.requests;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class UpdateProposalDetailsBean {
    private Map<Long, String> sectionsContent = new HashMap<Long, String>();
    private String pitch;

    @NotBlank
    private String name;


    private String team;
    private long imageId;

    public UpdateProposalDetailsBean(ProposalWrapper proposal) throws PortalException, SystemException {
        for (ProposalSectionWrapper section : proposal.getSections()) {
            sectionsContent.put(section.getSectionDefinitionId(), section.getContent());
        }
        pitch = proposal.getPitch();
        name = proposal.getName();
        team = proposal.getTeam();
        imageId = proposal.getImageId();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }


}

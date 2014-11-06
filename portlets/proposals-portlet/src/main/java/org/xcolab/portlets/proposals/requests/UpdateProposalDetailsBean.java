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
    private long baseProposalId;
    private long baseProposalContestId;
    private Long moveToContestPhaseId;
    private long moveFromContestPhaseId;
    private boolean hideOnMove;

    // legacy
    private String description;
	private boolean move;


    public UpdateProposalDetailsBean(ProposalWrapper proposal) throws PortalException, SystemException {
        for (ProposalSectionWrapper section : proposal.getSections()) {
            sectionsContent.put(section.getSectionDefinitionId(), section.getContent());
        }
        pitch = proposal.getPitch();
        name = proposal.getName();
        team = proposal.getTeam();
        imageId = proposal.getImageId();
        description = proposal.getDescription();
    }
    
    public UpdateProposalDetailsBean(ProposalWrapper proposal, ProposalWrapper baseProposal) throws PortalException, SystemException {
        for (ProposalSectionWrapper section : baseProposal.getSections()) {
            sectionsContent.put(section.getSectionDefinitionId(), section.getContent());
        }
        pitch = baseProposal.getPitch();
        name = baseProposal.getName();
        team = baseProposal.getTeam();
        imageId = baseProposal.getImageId();
        description = baseProposal.getDescription();
        baseProposalId = baseProposal.getProposalId();
        baseProposalContestId = baseProposal.getContestPK();
    }
    
    public UpdateProposalDetailsBean(ProposalWrapper proposal, ProposalWrapper baseProposal, boolean move) throws PortalException, SystemException {
    	this(proposal, baseProposal);
    	this.move = move;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

	public long getBaseProposalId() {
		return baseProposalId;
	}

	public void setBaseProposalId(long baseProposalId) {
		this.baseProposalId = baseProposalId;
	}

	public long getBaseProposalContestId() {
		return baseProposalContestId;
	}

	public void setBaseProposalContestId(long baseProposalContestId) {
		this.baseProposalContestId = baseProposalContestId;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public long getMoveToContestPhaseId() {
		return moveToContestPhaseId;
	}

	public void setMoveToContestPhaseId(long moveToContestPhaseId) {
		this.moveToContestPhaseId = moveToContestPhaseId;
	}

    public boolean isHideOnMove() {
        return hideOnMove;
    }

    public void setHideOnMove(boolean hideOnMove) {
        this.hideOnMove = hideOnMove;
    }

    public Long getMoveFromContestPhaseId() {
        return moveFromContestPhaseId;
    }

    public void setMoveFromContestPhaseId(Long moveFromContestPhaseId) {
        this.moveFromContestPhaseId = moveFromContestPhaseId;
    }

}

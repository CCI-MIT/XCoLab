package org.xcolab.portlets.proposals.requests;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.portlets.proposals.utils.MoveType;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.util.HashMap;
import java.util.Map;


public class UpdateProposalDetailsBean {
    private Map<Long, String> sectionsContent = new HashMap<>();

    @Length(max = 140, message = "the pitch is limited to 140 characters")
    private String pitch;

    @NotBlank(message = "please enter a title")
    @Length(max = 80, message = "the title is limited to 80 characters")
    private String name;

    @Length(max = 20, message = "the team name is limited to 20 characters")
    private String team;
    private long imageId;
    private long baseProposalId;
    private long baseProposalContestId;
    private Long moveToContestId;
    private long moveFromContestPhaseId;

    private boolean isMove;
    private MoveType moveType;

    private String uuid;

    // legacy
    private String description;



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
    
    public UpdateProposalDetailsBean(ProposalWrapper proposal, ProposalWrapper baseProposal, boolean isMove, MoveType moveType) throws PortalException, SystemException {
    	this(proposal, baseProposal);
    	this.isMove = isMove;
        this.moveType = moveType;
    }

    public UpdateProposalDetailsBean() { }

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

	public boolean getIsMove() {
		return isMove;
	}

	public void setIsMove(boolean move) {
		this.isMove = move;
	}

	public long getMoveToContestId() {
		return moveToContestId == null ? -1 : moveToContestId;
	}

	public void setMoveToContestId(long moveToContestId) {
		this.moveToContestId = moveToContestId;
	}

    public Long getMoveFromContestPhaseId() {
        return moveFromContestPhaseId;
    }

    public void setMoveFromContestPhaseId(Long moveFromContestPhaseId) {
        this.moveFromContestPhaseId = moveFromContestPhaseId;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

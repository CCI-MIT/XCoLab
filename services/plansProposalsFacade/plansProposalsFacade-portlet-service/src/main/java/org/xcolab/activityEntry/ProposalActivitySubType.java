package org.xcolab.activityEntry;

public enum ProposalActivitySubType{
    PROPOSAL_ATTRIBUTE_REMOVED(2l),
    PROPOSAL_ATTRIBUTE_UPDATE(1l),
    PROPOSAL_CREATED(0l),
    PROPOSAL_MEMBER_ADDED(6l),
    PROPOSAL_MEMBER_REMOVED(7l),
    PROPOSAL_SUPPORTER_ADDED(8l),
    PROPOSAL_SUPPORTER_REMOVED(9l),
    PROPOSAL_VOTE(3l),
    PROPOSAL_VOTE_RETRACT(4l),
    PROPOSAL_VOTE_SWITCH(5l);

    private final Long secondaryTypeId;
    ProposalActivitySubType(Long type) {
        this.secondaryTypeId = type;
    }

    public Long getSecondaryTypeId(){
        return this.secondaryTypeId;
    }
}

package org.xcolab.activityEntry;

public enum ProposalActivitySubType{
    PROPOSAL_ATTRIBUTE_REMOVED(2L),
    PROPOSAL_ATTRIBUTE_UPDATE(1L),
    PROPOSAL_CREATED(0L),
    PROPOSAL_MEMBER_ADDED(6L),
    PROPOSAL_MEMBER_REMOVED(7L),
    PROPOSAL_SUPPORTER_ADDED(8L),
    PROPOSAL_SUPPORTER_REMOVED(9L),
    PROPOSAL_VOTE(3L),
    PROPOSAL_VOTE_RETRACT(4L),
    PROPOSAL_VOTE_SWITCH(5L);

    private final Long secondaryTypeId;
    ProposalActivitySubType(Long type) {
        this.secondaryTypeId = type;
    }

    public Long getSecondaryTypeId(){
        return this.secondaryTypeId;
    }
}

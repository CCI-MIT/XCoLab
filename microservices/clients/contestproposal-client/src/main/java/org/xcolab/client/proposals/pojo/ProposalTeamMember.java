package org.xcolab.client.proposals.pojo;


import org.xcolab.client.members.pojo.Member;

public class ProposalTeamMember {
    private final Proposal proposal;
    private final Member member;
    private ProposalMemberType memberType;

    public ProposalTeamMember(Proposal proposal, Member member) {
        super();
        this.proposal = proposal;
        this.member = member;
    }
    
    public String getFullName() {
        return member.getFullName();
    }
    
    public String getScreenName() {
        return member.getDisplayName();
    }
    
    public long getUserId() {
        return member.getId();
    }

    public Member getMember() {
        return member;
    }

    public long getPortraitId() {
        return member.getPortraitId();
    }
    
    public ProposalMemberType getMemberType() {
        if (memberType == null) {
            if (proposal.getauthorUserid() == member.getId()) {
                memberType = ProposalMemberType.OWNER;
            } else {
                memberType = ProposalMemberType.MEMBER;
            }
        }
        return memberType;
    }

    public String getMemberTypeForDisplay() {
        return getMemberType().getDescription();
    }
    
    public enum ProposalMemberType {
        OWNER("Owner"),
        MEMBER("Member");
        
        private final String description;

        ProposalMemberType(String description) {
            this.description = description;
        }
        
        public String getDescription() { return description; }
    }

    

}

package org.xcolab.wrappers;


import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;

public class BaseProposalTeamMemberWrapper {
    private final Proposal proposal;
    private final Member user;
    private ProposalMemberType memberType;
    
    public BaseProposalTeamMemberWrapper(Proposal proposal, Member user) {
        super();
        this.proposal = proposal;
        this.user = user;
    }
    
    public String getFullName() {
        return user.getFullName();
    }
    
    public String getScreenName() {
        return user.getScreenName(); 
    }
    
    public long getUserId() {
        return user.getUserId();
    }
    
    public String getMemberType() {
        if (memberType == null) {
            if (proposal.getAuthorId() == user.getUserId()) {
                memberType = ProposalMemberType.OWNER;
            } else {
                memberType = ProposalMemberType.MEMBER;
            }
        }
        return memberType.getDescription();
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

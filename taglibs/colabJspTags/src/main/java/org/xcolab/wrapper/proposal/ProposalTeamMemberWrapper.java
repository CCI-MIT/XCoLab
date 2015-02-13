package org.xcolab.wrapper.proposal;

import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;

public class ProposalTeamMemberWrapper {
    private Proposal proposal;
    private User user;
    private ProposalMemberType memberType;
    
    public ProposalTeamMemberWrapper(Proposal proposal, User user) {
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
            if (proposal.getAuthorId() == user.getUserId()) 
                memberType = ProposalMemberType.OWNER;
            else 
                memberType = ProposalMemberType.MEMBER;
        }
        return memberType.getDescription();
    }

    
    
    public enum ProposalMemberType {
        OWNER("Owner"),
        MEMBER("Member");
        
        private final String description;

        private ProposalMemberType(String description) {
            this.description = description;
        }
        
        public String getDescription() { return description; }
    }

    

}

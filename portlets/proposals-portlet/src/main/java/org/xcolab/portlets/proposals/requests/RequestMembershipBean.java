package org.xcolab.portlets.proposals.requests;

import org.hibernate.validator.constraints.NotBlank;

public class RequestMembershipBean {

    private String requestComment;

    public String getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(String requestComment) {
        this.requestComment = requestComment;
    }
    
    

}

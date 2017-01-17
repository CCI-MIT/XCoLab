package org.xcolab.view.pages.proposals.requests;

/**
 * Created by kmang on 08/04/14.
 */
public class RequestMembershipInviteBean {

    private String inviteComment;

    private String inviteRecipient;

    public String getInviteComment() {
        return inviteComment;
    }

    public void setInviteComment(String requestComment) {
        this.inviteComment = requestComment;
    }

    public String getInviteRecipient() {
        return inviteRecipient;
    }

    public void setInviteRecipient(String inviteRecipient) {
        this.inviteRecipient = inviteRecipient;
    }

}

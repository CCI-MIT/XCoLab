package org.xcolab.view.pages.proposals.requests;

public class RequestMembershipInviteBean {

    private String inviteComment;

    private String inviteRecipient;

    private boolean skipInvitation;

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

    public boolean isSkipInvitation() { return skipInvitation; }

    public void setSkipInvitation(boolean skipInvitation) { this.skipInvitation = skipInvitation; }
}

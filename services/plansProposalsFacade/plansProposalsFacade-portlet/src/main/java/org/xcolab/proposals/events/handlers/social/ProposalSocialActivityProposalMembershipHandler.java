package org.xcolab.proposals.events.handlers.social;

import org.xcolab.proposals.events.ProposalMemberAddedEvent;
import org.xcolab.proposals.events.ProposalMemberRemovedEvent;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.google.common.eventbus.Subscribe;

public class ProposalSocialActivityProposalMembershipHandler extends BaseProposalSocialActivityEventHandler {


    @Subscribe
    public void handleEvent(ProposalMemberAddedEvent event) {
        addSocialActivity(
                event, 
                ProposalActivityKeys.USER_ADD, 
                null);
    }
    
    @Subscribe
    public void handleEvent(ProposalMemberRemovedEvent event) {
        addSocialActivity(
                event, 
                ProposalActivityKeys.USER_REMOVE, 
                null);
    }

}

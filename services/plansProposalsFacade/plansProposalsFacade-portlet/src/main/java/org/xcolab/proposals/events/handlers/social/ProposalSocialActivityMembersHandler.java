package org.xcolab.proposals.events.handlers.social;

import org.xcolab.proposals.events.ProposalSupporterAddedEvent;
import org.xcolab.proposals.events.ProposalSupporterRemovedEvent;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.google.common.eventbus.Subscribe;

public class ProposalSocialActivityMembersHandler extends BaseProposalSocialActivityEventHandler {


    @Subscribe
    public void handleEvent(ProposalSupporterRemovedEvent event) {
        addSocialActivity(
                event, 
                ProposalActivityKeys.SUPPORTER_REMOVE, 
                null);
    }
    
    @Subscribe
    public void handleEvent(ProposalSupporterAddedEvent event) {
        addSocialActivity(
                event, 
                ProposalActivityKeys.SUPPORTER_ADD, 
                null);
    }

}

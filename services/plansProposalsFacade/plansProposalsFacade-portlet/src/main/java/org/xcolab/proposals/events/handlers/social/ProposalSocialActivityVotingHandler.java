package org.xcolab.proposals.events.handlers.social;

import org.xcolab.proposals.events.ProposalRemovedVoteEvent;
import org.xcolab.proposals.events.ProposalVotedOnEvent;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.google.common.eventbus.Subscribe;

public class ProposalSocialActivityVotingHandler extends BaseProposalSocialActivityEventHandler {


    @Subscribe
    public void handleEvent(ProposalVotedOnEvent event) {
        addSocialActivity(
                event, 
                event.isSwitched() ? ProposalActivityKeys.VOTE_SWITCH : ProposalActivityKeys.VOTE, 
                null);
    }
    
    @Subscribe
    public void handleEvent(ProposalRemovedVoteEvent event) {
        addSocialActivity(
                event, 
                ProposalActivityKeys.VOTE_RETRACT, 
                null);
    }

}

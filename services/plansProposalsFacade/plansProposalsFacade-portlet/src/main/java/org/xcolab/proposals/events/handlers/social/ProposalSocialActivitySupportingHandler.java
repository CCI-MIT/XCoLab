package org.xcolab.proposals.events.handlers.social;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.google.common.eventbus.Subscribe;
import org.xcolab.proposals.events.ProposalSupporterAddedEvent;
import org.xcolab.proposals.events.ProposalSupporterRemovedEvent;

/**
 * Created by kmang on 08/05/14.
 */
public class ProposalSocialActivitySupportingHandler extends BaseProposalSocialActivityEventHandler {

    @Subscribe
    public void handleEvent(ProposalSupporterAddedEvent event) {
        addSocialActivity(event, ProposalActivityKeys.SUPPORTER_ADD, "");
    }

    @Subscribe
    public void handleEvent(ProposalSupporterRemovedEvent event) {
        addSocialActivity(event, ProposalActivityKeys.SUPPORTER_REMOVE, "");
    }
}

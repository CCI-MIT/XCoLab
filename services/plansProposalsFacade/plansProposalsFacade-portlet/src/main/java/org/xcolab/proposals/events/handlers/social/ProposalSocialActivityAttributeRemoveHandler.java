package org.xcolab.proposals.events.handlers.social;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.google.common.eventbus.Subscribe;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.proposals.events.ProposalAttributeRemovedEvent;
import org.xcolab.proposals.events.ProposalAttributeUpdatedEvent;

public class ProposalSocialActivityAttributeRemoveHandler extends BaseProposalSocialActivityEventHandler {
    

    @Subscribe
    public void handleEvent(ProposalAttributeRemovedEvent event) throws PortalException, SystemException {


        addSocialActivity(
                event, 
                ProposalActivityKeys.ATTRIBUTE_REMOVE,
                event.getAttributeName());
    }
}

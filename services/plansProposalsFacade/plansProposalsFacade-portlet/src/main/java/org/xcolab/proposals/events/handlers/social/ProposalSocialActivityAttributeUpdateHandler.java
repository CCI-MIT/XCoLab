package org.xcolab.proposals.events.handlers.social;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.google.common.eventbus.Subscribe;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.proposals.events.ProposalAttributeUpdatedEvent;

public class ProposalSocialActivityAttributeUpdateHandler extends BaseProposalSocialActivityEventHandler {

    @Subscribe
    public void handleEvent(ProposalAttributeUpdatedEvent event) throws PortalException, SystemException {
        addSocialActivity(
                event, 
                ProposalActivityKeys.ATTRIBUTE_UPDATE,
                event.getAttributeName() + "," + event.getNewAttribute().getAdditionalId());
    }
}

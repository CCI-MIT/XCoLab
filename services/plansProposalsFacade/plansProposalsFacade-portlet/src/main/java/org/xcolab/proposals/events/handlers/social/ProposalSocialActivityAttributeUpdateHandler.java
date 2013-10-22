package org.xcolab.proposals.events.handlers.social;

import org.xcolab.proposals.events.ProposalAttributeUpdatedEvent;

import com.ext.portlet.ProposalActivityKeys;
import com.ext.portlet.model.Proposal;
import com.google.common.eventbus.Subscribe;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class ProposalSocialActivityAttributeUpdateHandler extends BaseProposalSocialActivityEventHandler {
    

    @Subscribe
    public void handleEvent(ProposalAttributeUpdatedEvent event) throws PortalException, SystemException {
        try {
            socialActivityService.addActivity(event.getUser().getUserId(), getDefaultGroup().getGroupId(),
                    Proposal.class.getName(), event.getProposal().getProposalId(), ProposalActivityKeys.ATTRIBUTE_UPDATE.ordinal(), 
                    event.getAttributeName() + "," + event.getNewAttribute().getAdditionalId(), 0);
        }
        catch (PortalException e) {
            _log.error("Can't add social activity", e);
        }
        catch (SystemException e) {
            _log.error("Can't add social activity", e);
        }
    }
    
    private final static Log _log = LogFactoryUtil.getLog(ProposalSocialActivityAttributeUpdateHandler.class);
}

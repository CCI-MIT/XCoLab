package org.xcolab.proposals.events.handlers.social;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.ext.portlet.model.Proposal;
import com.google.common.eventbus.Subscribe;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.model.User;
import org.xcolab.proposals.events.ProposalAssociatedWithContestPhaseEvent;

public class ProposalSocialActivityCreateHandler extends BaseProposalSocialActivityEventHandler {


    @Subscribe
    public void handleEvent(ProposalAssociatedWithContestPhaseEvent event) throws PortalException, SystemException {
        try {
            socialActivityService.addActivity(event.getUser().getUserId(), getDefaultGroup().getGroupId(),
                    Proposal.class.getName(), event.getProposal().getProposalId(), ProposalActivityKeys.PROPOSAL_CREATE.ordinal(), 
                    null, 0);

            // Reindex the user to update the activity count
            Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);
            indexer.reindex(event.getUser().getUserId());
        }
        catch (PortalException | SystemException e) {
            _log.error("Can't add social activity", e);
        }
    }
    
    private final static Log _log = LogFactoryUtil.getLog(ProposalSocialActivityAttributeUpdateHandler.class);

}

package org.xcolab.proposals.events.handlers.social;

import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.model.User;
import org.xcolab.proposals.events.BaseProposalUserActivityEvent;
import org.xcolab.proposals.events.handlers.BaseEventHandler;

import com.ext.portlet.ProposalActivityKeys;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.social.service.SocialActivityLocalService;

public class BaseProposalSocialActivityEventHandler extends BaseEventHandler {

    @BeanReference(type = SocialActivityLocalService.class)
    protected SocialActivityLocalService socialActivityService;
    
    protected void addSocialActivity(BaseProposalUserActivityEvent event, ProposalActivityKeys type, String extraData) {
        try {
            socialActivityService.addActivity(event.getUser().getUserId(), getDefaultGroup().getGroupId(), event.getUpdatedDate(),
                    Proposal.class.getName(), event.getProposal().getProposalId(), type.ordinal(), extraData, 0);

            // Reindex the user to update the activity count
            Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);
            indexer.reindex(event.getUser().getUserId());
        }
        catch (PortalException e) {
            _log.error("Can't add social activity", e);
        }
        catch (SystemException e) {
            _log.error("Can't add social activity", e);
        }
    }

    private final static Log _log = LogFactoryUtil.getLog(BaseProposalSocialActivityEventHandler.class);
}

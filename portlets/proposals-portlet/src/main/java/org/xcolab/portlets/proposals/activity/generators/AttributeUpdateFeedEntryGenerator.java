package org.xcolab.portlets.proposals.activity.generators;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

public class AttributeUpdateFeedEntryGenerator extends BaseProposalsFeedEntryGenerator {
    private final static String DEFAULT_TITLE = "Proposal attribute updated";

    @Override
    public SocialActivityFeedEntry generateFeedEntry(SocialActivity activity) throws PortalException, SystemException {
        return new SocialActivityFeedEntry(DEFAULT_TITLE, getUserLink(activity.getUserId()) + 
                " updated proposal " + getProposalLink(getProposal(activity)));
    }

}

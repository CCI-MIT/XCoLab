package org.xcolab.portlets.proposals.activity.generators;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

public class DefaultFeedEntryGenerator extends BaseProposalsFeedEntryGenerator {
    protected final String title;
    protected final String actionName;
    protected final String defaultFeedEntryPattern = "%s %s %s";
    
    

    public DefaultFeedEntryGenerator(String title, String actionName) {
        super();
        this.title = title;
        this.actionName = actionName;
    }


    @Override
    public SocialActivityFeedEntry generateFeedEntry(SocialActivity activity) throws PortalException, SystemException {
        System.out.println("generateFeedEntry");
        String body = String.format(
                defaultFeedEntryPattern,
                getUserLink(activity.getUserId()),
                actionName, 
                getProposalLink(getProposal(activity)));
        
        return new SocialActivityFeedEntry(title, body);
    }

}

package org.xcolab.portlets.proposals.activity;

import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

public interface ProposalActivityFeedEntryGenerator {
    SocialActivityFeedEntry generateFeedEntry(SocialActivity activity) throws Exception;
}

package org.xcolab.view.util.entity.subscriptions;

import org.xcolab.commons.activities.enums.ActivityType;
import org.xcolab.commons.activities.enums.ProposalActivityType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util class defining rules for certain types of SocialActivities where notifications to subscribed
 * users should be restricted (i.e. ProposalSupports are only shared with Proposal contributors)
 */
public class ActivitySubscriptionConstraint {

    private static final Map<ActivityType, ActivitySubscriptionWhitelistHandler> whitelistHandlers =
            new HashMap<>();

    static {
        whitelistHandlers.put(ProposalActivityType.SUPPORT_ADDED,
                new ActivitySubscriptionWhitelistProposalContributorHandler());
        whitelistHandlers.put(ProposalActivityType.SUPPORT_REMOVED,
                new ActivitySubscriptionWhitelistProposalContributorHandler());
        whitelistHandlers.put(ProposalActivityType.VOTE_ADDED,
                new ActivitySubscriptionWhitelistProposalContributorHandler());
        whitelistHandlers.put(ProposalActivityType.VOTE_SWITCHED,
                new ActivitySubscriptionWhitelistProposalContributorHandler());
        whitelistHandlers.put(ProposalActivityType.VOTE_RETRACTED,
                new ActivitySubscriptionWhitelistProposalContributorHandler());
    }

    private final ActivityType activityType;

    public ActivitySubscriptionConstraint(ActivityType activityType) {
        this.activityType = activityType;
    }

    public boolean areSubscribersConstrained() {
        return whitelistHandlers.containsKey(activityType);
    }

    public List<Long> getWhitelist(long categoryId) {
        if (areSubscribersConstrained()) {
            return whitelistHandlers.get(activityType).getWhitelistedUsers(categoryId);
        }

        return Collections.emptyList();
    }
}

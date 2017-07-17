package org.xcolab.view.util.entity.subscriptions;

import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.util.entity.activityEntry.ProposalActivitySubType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util class defining rules for certain types of SocialActivities where notifications to subscribed users should be restricted
 * (i.e. ProposalSupports are only shared with Proposal contributors)
 */
public class ActivitySubscriptionConstraint {

    private static final Map<String, ActivitySubscriptionWhitelistHandler> whitelistHandlers =
            new HashMap<>();

    static {
        whitelistHandlers.put(ActivityEntryType.PROPOSAL.getPrimaryTypeId() + "_" + ProposalActivitySubType.PROPOSAL_SUPPORTER_ADDED.getSecondaryTypeId(),
                new ActivitySubscriptionWhitelistProposalContributorHandler());
        whitelistHandlers.put(ActivityEntryType.PROPOSAL.getPrimaryTypeId() + "_" + ProposalActivitySubType.PROPOSAL_SUPPORTER_REMOVED.getSecondaryTypeId(),
                new ActivitySubscriptionWhitelistProposalContributorHandler());
        whitelistHandlers.put(ActivityEntryType.PROPOSAL.getPrimaryTypeId() + "_" + ProposalActivitySubType.PROPOSAL_VOTE.getSecondaryTypeId(),
                new ActivitySubscriptionWhitelistProposalContributorHandler());
        whitelistHandlers.put(ActivityEntryType.PROPOSAL.getPrimaryTypeId()+ "_" + ProposalActivitySubType.PROPOSAL_VOTE_SWITCH.getSecondaryTypeId(),
                new ActivitySubscriptionWhitelistProposalContributorHandler());
        whitelistHandlers.put(ActivityEntryType.PROPOSAL.getPrimaryTypeId() + "_" + ProposalActivitySubType.PROPOSAL_VOTE_RETRACT.getSecondaryTypeId(),
                new ActivitySubscriptionWhitelistProposalContributorHandler());
    }

    private final long classNameId;
    private final long activityType;

    public ActivitySubscriptionConstraint(long classNameId, long activityType) {
        this.classNameId = classNameId;
        this.activityType = activityType;
    }
    public boolean areSubscribersConstrained() {
        ActivitySubscriptionWhitelistHandler handler = whitelistHandlers.get(getClassNameId() + "_" + getActivityType());

        return handler != null;
    }

    public List<Long> getWhitelist(long classPk) {
        if (areSubscribersConstrained()) {
            return whitelistHandlers.get(getClassNameId() + "_" + getActivityType()).getWhitelistedUsers(classPk);
        }

        return new ArrayList<>();
    }

    public long getClassNameId() {
        return classNameId;
    }

    public long getActivityType() {
        return activityType;
    }

}

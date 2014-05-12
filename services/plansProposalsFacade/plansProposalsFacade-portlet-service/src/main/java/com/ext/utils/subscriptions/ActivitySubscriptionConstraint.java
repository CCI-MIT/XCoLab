package com.ext.utils.subscriptions;

import com.ext.portlet.Activity.ProposalActivityKeys;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util class defining rules for certain types of SocialActivities where notifications to subscribed users should be restricted
 * (i.e. ProposalSupports are only shared with Proposal contributors)
 *
 * Created by kmang on 08/05/14.
 */
public class ActivitySubscriptionConstraint {

    private static final Map<String, ActivitySubscriptionWhitelistHandler> whitelistHandlers =
            new HashMap<>();

    static {
        whitelistHandlers.put(ClassNameLocalServiceUtil.getClassNameId(Proposal.class) + "_" + ProposalActivityKeys.SUPPORTER_ADD.ordinal(),
                new ActivitySubscriptionWhitelistProposalSupportHandler());
        whitelistHandlers.put(ClassNameLocalServiceUtil.getClassNameId(Proposal.class) + "_" + ProposalActivityKeys.SUPPORTER_REMOVE.ordinal(),
                new ActivitySubscriptionWhitelistProposalSupportHandler());
        whitelistHandlers.put(ClassNameLocalServiceUtil.getClassNameId(Proposal.class) + "_" + ProposalActivityKeys.VOTE.ordinal(),
                new ActivitySubscriptionWhitelistProposalSupportHandler());
        whitelistHandlers.put(ClassNameLocalServiceUtil.getClassNameId(Proposal.class) + "_" + ProposalActivityKeys.VOTE_SWITCH.ordinal(),
                new ActivitySubscriptionWhitelistProposalSupportHandler());
        whitelistHandlers.put(ClassNameLocalServiceUtil.getClassNameId(Proposal.class) + "_" + ProposalActivityKeys.VOTE_RETRACT.ordinal(),
                new ActivitySubscriptionWhitelistProposalSupportHandler());
    }

    private long classNameId;
    private int activityType;

    public ActivitySubscriptionConstraint(long classNameId, int activityType) {
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

    public int getActivityType() {
        return activityType;
    }

}

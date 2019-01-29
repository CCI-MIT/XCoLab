package org.xcolab.view.util.entity.subscriptions;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.proposals.ProposalClientUtil;

import java.util.ArrayList;
import java.util.List;

public class ActivitySubscriptionWhitelistProposalContributorHandler
        implements ActivitySubscriptionWhitelistHandler {

    @Override
    public List<Long> getWhitelistedUsers(long categoryId) {
        List<Long> contributorIds = new ArrayList<>();

        List<UserWrapper> contributors = ProposalClientUtil.getProposalMembers(categoryId);
        for (UserWrapper contributor : contributors) {
            contributorIds.add(contributor.getId());
        }

        return contributorIds;
    }
}

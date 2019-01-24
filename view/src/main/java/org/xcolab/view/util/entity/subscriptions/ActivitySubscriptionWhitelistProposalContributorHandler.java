package org.xcolab.view.util.entity.subscriptions;

import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.members.pojo.Member;

import java.util.ArrayList;
import java.util.List;

public class ActivitySubscriptionWhitelistProposalContributorHandler
        implements ActivitySubscriptionWhitelistHandler {

    @Override
    public List<Long> getWhitelistedUsers(long categoryId) {
        List<Long> contributorIds = new ArrayList<>();

        List<Member> contributors = StaticProposalContext.getProposalClient()
                .getProposalMembers(categoryId);
        for (Member contributor : contributors) {
            contributorIds.add(contributor.getId());
        }

        return contributorIds;
    }
}

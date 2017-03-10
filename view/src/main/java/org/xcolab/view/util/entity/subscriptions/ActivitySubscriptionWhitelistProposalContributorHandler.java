package org.xcolab.view.util.entity.subscriptions;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;

import java.util.ArrayList;
import java.util.List;

public class ActivitySubscriptionWhitelistProposalContributorHandler implements ActivitySubscriptionWhitelistHandler {
    @Override
    public List<Long> getWhitelistedUsers(long classPk) {
        List<Long> contributorIds = new ArrayList<>();

        List<Member> contributors = ProposalClientUtil.getProposalMembers(classPk);
        for (Member contributor : contributors) {
            contributorIds.add(contributor.getUserId());
        }

        return contributorIds;
    }
}

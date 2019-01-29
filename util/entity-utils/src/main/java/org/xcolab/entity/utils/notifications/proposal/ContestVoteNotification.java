package org.xcolab.entity.utils.notifications.proposal;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ContestVoteNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_DEFAULT";

    public ContestVoteNotification(UserWrapper recipient, ContestWrapper contest, ProposalWrapper votedProposal,
                                   String baseUrl) {
        super(votedProposal, contest, recipient,
                LinkUtils.getNonBlankStringOrDefault(contest.getVoteTemplateString(), DEFAULT_TEMPLATE_STRING));
    }
}

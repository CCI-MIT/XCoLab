package org.xcolab.entity.utils.notifications.proposal;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.user.pojo.IUser;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ContestVoteNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_DEFAULT";

    public ContestVoteNotification(IUser recipient, Contest contest, Proposal votedProposal,
                                   String baseUrl) {
        super(votedProposal, contest, recipient,
                LinkUtils.getNonBlankStringOrDefault(contest.getVoteTemplateString(), DEFAULT_TEMPLATE_STRING));
    }
}

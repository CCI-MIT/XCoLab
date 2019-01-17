package org.xcolab.entity.utils.notifications.proposal;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ContestVoteNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_DEFAULT";

    public ContestVoteNotification(Member recipient, Contest contest, Proposal votedProposal,
                                   String baseUrl) {
        super(votedProposal, contest, recipient,
                LinkUtils.getNonBlankStringOrDefault(contest.getVoteTemplateString(), DEFAULT_TEMPLATE_STRING));
    }
}

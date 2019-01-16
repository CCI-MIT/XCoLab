package org.xcolab.entity.utils.notifications.proposal;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ProposalVoteNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_VOTE_DEFAULT";

    public ProposalVoteNotification(Proposal votedProposal, Contest contest, Member recipient) {
        super(votedProposal, contest, recipient,
                LinkUtils.getNonBlankStringOrDefault(contest.getProposalVoteTemplateString(),
                        DEFAULT_TEMPLATE_STRING));
    }
}

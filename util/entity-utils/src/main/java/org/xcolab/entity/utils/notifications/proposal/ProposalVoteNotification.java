package org.xcolab.entity.utils.notifications.proposal;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ProposalVoteNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_VOTE_DEFAULT";

    public ProposalVoteNotification(ProposalWrapper votedProposal, ContestWrapper contest, Member recipient) {
        super(votedProposal, contest, recipient,
                LinkUtils.getNonBlankStringOrDefault(contest.getProposalVoteTemplateString(),
                        DEFAULT_TEMPLATE_STRING));
    }
}

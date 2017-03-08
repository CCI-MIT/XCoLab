package org.xcolab.entity.utils.email.notifications.proposal;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.email.notifications.basic.ProposalNotification;

public class ProposalVoteNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_VOTE_DEFAULT";

    public ProposalVoteNotification(Proposal votedProposal, Contest contest, Member recipient) {
        super(votedProposal, contest, recipient,
                LinkUtils.getNonBlankStringOrDefault(contest.getProposalVoteTemplateString(),
                        DEFAULT_TEMPLATE_STRING), ConfigurationAttributeKey.COLAB_URL.get());
    }
}

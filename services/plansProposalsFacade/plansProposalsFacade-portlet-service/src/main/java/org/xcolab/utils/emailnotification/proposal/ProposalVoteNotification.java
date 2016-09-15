package org.xcolab.utils.emailnotification.proposal;



import com.liferay.portal.service.ServiceContext;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.utils.LinkUtils;
import org.xcolab.utils.emailnotification.basic.ProposalNotification;

public class ProposalVoteNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_VOTE_DEFAULT";

    public ProposalVoteNotification(Proposal votedProposal, Contest contest, Member recipient,
                                    ServiceContext serviceContext) {
        super(votedProposal, contest, recipient,
                LinkUtils.getNonBlankStringOrDefault(contest.getProposalVoteTemplateString(), DEFAULT_TEMPLATE_STRING),
                serviceContext);
    }
}

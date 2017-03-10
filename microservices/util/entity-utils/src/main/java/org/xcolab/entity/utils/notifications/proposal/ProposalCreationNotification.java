package org.xcolab.entity.utils.notifications.proposal;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ProposalCreationNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_CREATION_DEFAULT";

    public ProposalCreationNotification(Proposal createdProposal, Contest contest,
                                        String baseUrl) {
        super(createdProposal, contest, MembersClient.getMemberUnchecked(createdProposal.getAuthorId()),
                LinkUtils.getNonBlankStringOrDefault(contest.getProposalCreationTemplateString(),
                        DEFAULT_TEMPLATE_STRING),
                baseUrl);
    }
}

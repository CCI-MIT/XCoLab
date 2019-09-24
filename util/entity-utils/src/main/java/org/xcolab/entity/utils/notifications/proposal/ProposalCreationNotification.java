package org.xcolab.entity.utils.notifications.proposal;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.notifications.basic.ProposalNotification;

public class ProposalCreationNotification extends ProposalNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "PROPOSAL_CREATION_DEFAULT";

    public ProposalCreationNotification(ProposalWrapper createdProposal, ContestWrapper contest) {
        super(createdProposal, contest, StaticUserContext.getUserClient().getMemberUnchecked(createdProposal.getAuthorUserId()),
                LinkUtils.getNonBlankStringOrDefault(contest.getProposalCreationTemplateString(),
                        DEFAULT_TEMPLATE_STRING));
    }
}

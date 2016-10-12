package org.xcolab.mail;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

public class ContestPhasePromotionEmail {

    private static final long ADMINISTRATOR_USER_ID = 10144L;

    public static void contestPhasePromotionEmailNotifyProposalContributors(Proposal proposal, ContestPhase contestPhase, PortletRequest request) {

        String subject = "Judging Results on your Proposal " + ProposalAttributeClientUtil
                .getProposalAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0l).getStringValue();

        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String messageBody = reviewContentHelper.getPromotionComment(true);

        if (StringUtils.isNotEmpty(messageBody)) {
            MessagingClient
                    .sendMessage(subject, messageBody, ADMINISTRATOR_USER_ID, ADMINISTRATOR_USER_ID, getMemberUserIds(proposal));
        }
    }

    private static  List<Long> getMemberUserIds(Proposal proposal) {
        List<Long> recipientIds = new ArrayList<>();

        for (Member contributor : ProposalClientUtil.getProposalMembers(proposal.getProposalId())) {
            recipientIds.add(contributor.getUserId());
        }

        return recipientIds;
    }
}

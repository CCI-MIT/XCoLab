package org.xcolab.entity.utils.email;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.proposals.ProposalClientUtil;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.entity.utils.helper.ProposalJudgingCommentHelper;

import java.util.ArrayList;
import java.util.List;

public class ContestPhasePromotionEmail {

    private static final long ADMINISTRATOR_USER_ID = 10144L;

    public static void contestPhasePromotionEmailNotifyProposalContributors(Proposal proposal, ContestPhase contestPhase) {

        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String messageBody = reviewContentHelper.getPromotionComment(true);
        String subject =  reviewContentHelper.getSubject();
        if (StringUtils.isNotEmpty(messageBody)) {
            MessagingClient
                    .sendMessage(subject, messageBody, ADMINISTRATOR_USER_ID, null, getMemberUserIds(proposal));
        }
    }

    private static  List<Long> getMemberUserIds(Proposal proposal) {
        List<Long> recipientIds = new ArrayList<>();

        for (Member contributor : ProposalClientUtil.getProposalMembers(proposal.getId())) {
            recipientIds.add(contributor.getId());
        }

        return recipientIds;
    }
}

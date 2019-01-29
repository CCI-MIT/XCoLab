package org.xcolab.entity.utils.email;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.entity.utils.helper.ProposalJudgingCommentHelper;

import java.util.ArrayList;
import java.util.List;

public class ContestPhasePromotionEmail {

    private static final long ADMINISTRATOR_USER_ID = 10144L;

    public static void contestPhasePromotionEmailNotifyProposalContributors(ProposalWrapper proposal, ContestPhaseWrapper contestPhase) {

        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String messageBody = reviewContentHelper.getPromotionComment(true);
        String subject =  reviewContentHelper.getSubject();
        if (StringUtils.isNotEmpty(messageBody)) {
            StaticUserContext.getMessagingClient()
                    .sendMessage(subject, messageBody, ADMINISTRATOR_USER_ID, null, getMemberUserIds(proposal));
        }
    }

    private static  List<Long> getMemberUserIds(ProposalWrapper proposal) {
        List<Long> recipientIds = new ArrayList<>();

        for (UserWrapper contributor : StaticProposalContext.getProposalClient()
                .getProposalMembers(proposal.getId())) {
            recipientIds.add(contributor.getId());
        }

        return recipientIds;
    }
}

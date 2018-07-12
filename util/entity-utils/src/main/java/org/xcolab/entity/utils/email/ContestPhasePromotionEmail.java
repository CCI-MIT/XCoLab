package org.xcolab.entity.utils.email;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.ReplyingToManyException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
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
            try {
                MessagingClient
                        .sendMessage(subject, messageBody, ADMINISTRATOR_USER_ID, "-1", getMemberUserIds(proposal));
            } catch (ReplyingToManyException e) {
                //This should never be reached. TO-DO: Log a message to alert of this situation
            }

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

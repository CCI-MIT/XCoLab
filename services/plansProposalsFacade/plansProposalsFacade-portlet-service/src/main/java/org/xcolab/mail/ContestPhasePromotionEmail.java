package org.xcolab.mail;

import org.xcolab.client.proposals.enums.ProposalAttributeKeys;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.mail.MailEngineException;

import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.portlet.PortletRequest;

public class ContestPhasePromotionEmail {

    private static final long ADMINISTRATOR_USER_ID = 10144L;

    public static void contestPhasePromotionEmailNotifyProposalContributors(Proposal proposal, ContestPhase contestPhase, PortletRequest request)
            throws PortalException, SystemException, AddressException, MailEngineException, UnsupportedEncodingException {

        String subject = "Judging Results on your Proposal " + ProposalAttributeClient.getProposalAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0l).getStringValue();

        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String messageBody = reviewContentHelper.getPromotionComment(true);

        if (Validator.isNotNull(messageBody)) {
            MessagingClient
                    .sendMessage(subject, messageBody, ADMINISTRATOR_USER_ID, ADMINISTRATOR_USER_ID, getMemberUserIds(proposal));
        }
    }

    private static  List<Long> getMemberUserIds(Proposal proposal) throws PortalException, SystemException {
        List<Long> recipientIds = new ArrayList<>();

        for (Member contributor : ProposalsClient.getProposalMembers(proposal.getProposalId())) {
            recipientIds.add(contributor.getUserId());
        }

        return recipientIds;
    }
}

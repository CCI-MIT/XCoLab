package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.user.MessagingClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

public abstract class MessageMassAction extends AbstractContestMassAction {

    private static final long ADMINISTRATOR_USER_ID = 10144L;

    public MessageMassAction(String displayName) {
        super(displayName);
    }

    protected abstract List<Proposal> getProposalsToBeMessaged(Contest contest);

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalArgumentException {
        MassMessageBean massMessageBean = dataWrapper.getMassMessageBean();
        if (massMessageBean == null) {
            throw new IllegalArgumentException("No mass message bean provided.");
        }

        Set<Long> recipientIds = new HashSet<>();
        final StringBuilder contestNames = new StringBuilder();

        for (Contest contest : contests) {
            contestNames.append(contest.getTitle()).append("; ");
            List<Proposal> proposals = getProposalsToBeMessaged(contest);

            for (Proposal proposal : proposals) {
                List<Member> proposalMember =
                        ProposalClientUtil.getProposalMembers(proposal.getId());
                for (Member member : proposalMember) {
                    recipientIds.add(member.getId());
                }
            }
        }

        sendEmail(massMessageBean, recipientIds, contests, contestNames);
    }

    private static void sendEmail(MassMessageBean massMessageBean, Set<Long> recipientIds,
            List<Contest> contestList, StringBuilder contestNames) {
        final String messageSubject = massMessageBean.getSubject();
        final String messageBody = massMessageBean.getBody();
        MessagingClient.sendMessage(messageSubject, messageBody, ADMINISTRATOR_USER_ID,
                null, new ArrayList<>(recipientIds));
        final String emailSubject = "Mass message: " + messageSubject;
        final String emailBody = String.format(
                "The following message was sent to %d users in %d contests (%s): <br /><br /><br />",
                recipientIds.size(), contestList.size(), contestNames) + HtmlUtil
                .addHtmlLineBreaks(messageBody);

        final String adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        EmailClient.sendEmail(adminEmail,ConfigurationAttributeKey.COLAB_NAME.get(), adminEmail, emailSubject,
                emailBody, true, null,null,null);
    }
}

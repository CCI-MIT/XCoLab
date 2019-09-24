package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.email.StaticEmailContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
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

    protected abstract List<ProposalWrapper> getProposalsToBeMessaged(ContestWrapper contest);


    @Override
    public void execute(List<ContestWrapper> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalArgumentException {
        MassMessageBean massMessageBean = dataWrapper.getMassMessageBean();
        if (massMessageBean == null) {
            throw new IllegalArgumentException("No mass message bean provided.");
        }

        Set<Long> recipientIds = new HashSet<>();
        final StringBuilder contestNames = new StringBuilder();

        for (ContestWrapper contest : contests) {
            contestNames.append(contest.getTitle()).append("; ");
            List<ProposalWrapper> proposals = getProposalsToBeMessaged(contest);

            for (ProposalWrapper proposal : proposals) {
                List<UserWrapper> proposalMember =
                        StaticProposalContext.getProposalClient()
                                .getProposalMembers(proposal.getId());
                for (UserWrapper member : proposalMember) {
                    recipientIds.add(member.getId());
                }
            }
        }

        sendEmail(massMessageBean, recipientIds, contests, contestNames);
    }

    private static void sendEmail(MassMessageBean massMessageBean, Set<Long> recipientIds,
            List<ContestWrapper> contestList, StringBuilder contestNames) {
        final String messageSubject = massMessageBean.getSubject();
        final String messageBody = massMessageBean.getBody();
        StaticUserContext.getMessagingClient().sendMessage(messageSubject, messageBody, ADMINISTRATOR_USER_ID,
                null, new ArrayList<>(recipientIds));
        final String emailSubject = "Mass message: " + messageSubject;
        final String emailBody = String.format(
                "The following message was sent to %d users in %d contests (%s): <br /><br /><br />",
                recipientIds.size(), contestList.size(), contestNames) + HtmlUtil
                .addHtmlLineBreaks(messageBody);

        final String adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        StaticEmailContext.getEmailClient().sendEmail(adminEmail,ConfigurationAttributeKey.COLAB_NAME.get(), adminEmail, emailSubject,
                emailBody, true, null,null,null);
    }
}

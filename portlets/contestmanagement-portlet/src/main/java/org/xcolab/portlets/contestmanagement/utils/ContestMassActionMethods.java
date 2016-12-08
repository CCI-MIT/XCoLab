package org.xcolab.portlets.contestmanagement.utils;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.portlets.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.portlets.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.portlets.contestmanagement.beans.MassMessageBean;
import org.xcolab.portlets.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.util.html.HtmlUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

public class ContestMassActionMethods {

    private static final Long CLIMATE_COLAB_TEAM_USER_ID = 1431053L;
    private static final List<String> CSV_EXPORT_HEADER =
            Arrays.asList("Contest", "Proposal Title", "Proposal Link", "Username", "First Name",
                    "Last Name", "Email Address", "Role", "Last phase");

    public static void reportOfPeopleInCurrentPhase(List<Long> contestList, Object ResourceResponseObject,
                                                    PortletRequest request) throws IOException {

        ResourceResponse response = (ResourceResponse) ResourceResponseObject;
        CsvExportHelper csvExportHelper = new CsvExportHelper();
        csvExportHelper.addRowToExportData(CSV_EXPORT_HEADER);

        for (Long contestId : contestList) {
            List<Proposal> proposalsInActiveContestPhase = getProposalsInActiveContestPhase(contestId);
            ContestPhase activeContestPhase = ContestClientUtil.getActivePhase(contestId);
            csvExportHelper
                    .addProposalAndAuthorDetailsToExportData(proposalsInActiveContestPhase, activeContestPhase);
        }

        String exportFileName = "reportOfPeopleInCurrentPhase";
        csvExportHelper.initiateDownload(exportFileName, request, response);
    }

    public static void sendMassMessage(List<Long> contestList, Object massMessageWrapperObject, PortletRequest request) {

        MassMessageBean massMessageBean = (MassMessageBean) massMessageWrapperObject;
        Set<Long> recipientIds = new HashSet<>();
        final StringBuilder contestNames = new StringBuilder();

        for (Long contestId : contestList) {
            contestNames.append(ContestClientUtil.getContest(contestId).getContestShortName()).append("; ");
            List<Proposal> proposalsInActiveContestPhase = getProposalsInActiveContestPhase(contestId);

            for (Proposal proposal : proposalsInActiveContestPhase) {
                List<Member> proposalMember = ProposalClientUtil.getProposalMembers(proposal.getProposalId());
                for (Member member : proposalMember) {
                    if (!recipientIds.contains(member.getUserId())) {
                        recipientIds.add(member.getUserId());
                    }
                }
            }
        }

        final String messageSubject = massMessageBean.getSubject();
        final String messageBody = massMessageBean.getBody();
        MessagingClient.sendMessage(messageSubject, messageBody,
                CLIMATE_COLAB_TEAM_USER_ID, CLIMATE_COLAB_TEAM_USER_ID, new ArrayList<>(recipientIds));

        final String emailSubject = "Mass message: " + messageSubject;
        final String emailBody =
                String.format("The following message was sent to %d users in %d contests (%s): <br /><br /><br />",
                        recipientIds.size(), contestList.size(), contestNames) + HtmlUtil
                        .addHtmlLineBreaks(messageBody);

        final String adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        EmailClient.sendEmail(adminEmail, adminEmail, emailSubject,
                emailBody, true, null);
    }

    public static void changeSubscriptionStatus(List<Long> contestList, Object subscriptionStatusObject,
                                                PortletRequest request) {
        long memberId = MemberAuthUtil.getMemberId(request);
        for (Long contestId : contestList) {
            Boolean subscriptionStatus = (boolean) subscriptionStatusObject;
            if (subscriptionStatus) {
                ContestClientUtil.subscribeMemberToContest(contestId, memberId);
            } else {
                ContestClientUtil.unsubscribeMemberFromContest(contestId, memberId);
            }
        }
    }

    private static void deleteContestPhases(Long contestId) {
        List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(contestId);
        for (ContestPhase contestPhase : contestPhases) {
            ContestClientUtil.deleteContestPhase(contestPhase.getContestPhasePK());
        }
    }

    private static void deleteContestAndPhases(Long contestId) {
        deleteContestPhases(contestId);
        ContestClientUtil.deleteContest(contestId);
    }

    public static void deleteContest(List<Long> contestList, Object actionConfirmed, PortletRequest request)
            throws MassActionRequiresConfirmationException {
        if ((Boolean) actionConfirmed) {
            for (Long contestId : contestList) {
                ContestClientUtil.deleteContest(contestId);
            }
        } else {
            throw new MassActionRequiresConfirmationException();
        }
    }

    public static void deleteContestwithPhases(List<Long> contestList, Object actionConfirmed, PortletRequest request)
            throws MassActionRequiresConfirmationException {
        if ((Boolean) actionConfirmed) {
            for (Long contestId : contestList) {
                List<ContestPhase> contestPhases =
                        ContestClientUtil.getAllContestPhases(contestId);
                if (!contestPhases.isEmpty()) {
                    deleteContestAndPhases(contestId);
                } else {
                    ContestClientUtil.deleteContest(contestId);
                }
            }
        } else {
            throw new MassActionRequiresConfirmationException();
        }
    }

    public static void setFlag(List<Long> contestList, Object flagTexToolTipValue, PortletRequest request) {
        for (Long contestId : contestList) {
            try {
                Contest contest = ContestClientUtil.getContest(contestId);
                ContestFlagTextToolTipBean contestFlagTextToolTipBean = (ContestFlagTextToolTipBean) flagTexToolTipValue;
                contestFlagTextToolTipBean.persist(contest);
            } catch (ContestNotFoundException ignored) {

            }

        }
    }

    public static void setModelSettings(List<Long> contestList, Object modelSettings, PortletRequest request) {
        for (Long contestId : contestList) {
            try {
                Contest contest = ContestClientUtil.getContest(contestId);
                ContestModelSettingsBean contestModelSettingsBean = (ContestModelSettingsBean) modelSettings;
                contestModelSettingsBean.persist(contest);
            } catch (ContestNotFoundException ignored){

            }
        }
    }

    private static List<Proposal> getProposalsInActiveContestPhase(Long contestPK) {
        ContestPhase activeContestPhase = ContestClientUtil.getActivePhase(contestPK);
        return ProposalClientUtil.getActiveProposalsInContestPhase(activeContestPhase.getContestPhasePK());
    }
}

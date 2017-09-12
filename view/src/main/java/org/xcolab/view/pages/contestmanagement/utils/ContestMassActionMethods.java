package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ContestMassActionMethods {

    private static final Long CLIMATE_COLAB_TEAM_USER_ID = 1431053L;
    private static final List<String> CSV_EXPORT_HEADER =
            Arrays.asList("Contest", "Proposal Title", "Proposal Link", "Username", "First Name",
                    "Last Name", "Email Address", "Role", "Last phase");

    public static void reportOfPeopleInCurrentPhase(List<Long> contestList,
            Object ResourceResponseObject, HttpServletRequest request) throws IOException {

        HttpServletResponse response = (HttpServletResponse) ResourceResponseObject;
        CsvExportHelper csvExportHelper = new CsvExportHelper();
        csvExportHelper.addRowToExportData(CSV_EXPORT_HEADER);

        for (Long contestId : contestList) {
            Contest c = ContestClientUtil.getContest(contestId);
            if (!c.getIsSharedContestInForeignColab()) {
                List<Proposal> proposalsInActiveContestPhase =
                        getProposalsInActiveContestPhase(contestId);
                ContestPhase activeContestPhase = ContestClientUtil.getActivePhase(contestId);
                csvExportHelper
                        .addProposalAndAuthorDetailsToExportData(proposalsInActiveContestPhase,
                                activeContestPhase);
            }
        }

        String exportFileName = "reportOfPeopleInCurrentPhase";
        csvExportHelper.initiateDownload(exportFileName, response);
    }

    private static List<Proposal> getProposalsInOpenForSubmissionPhase(Long contestPK) {
        List<ContestPhase> allPhases = ContestClientUtil.getAllContestPhases(contestPK);
        Map<Long,Proposal> proposalsMap = new HashMap<>();
        for(ContestPhase cp : allPhases){
            ContestPhaseType cpt = ContestClientUtil.getContestPhaseType(cp.getContestPhaseType());
            if(cpt.getStatus().equals(ContestStatus.OPEN_FOR_SUBMISSION.name())){
                List<Proposal> proposals = ProposalClientUtil
                        .getActiveProposalsInContestPhase(cp.getContestPhasePK());
                for (Proposal p: proposals) {
                    proposalsMap.putIfAbsent(p.getProposalId(), p);
                }
            }


        }

        List<Proposal> ret = new ArrayList<>();
        ret.addAll(proposalsMap.values());
        return ret;
    }

    public static List<Proposal> getProposalsInActiveContestPhase(Long contestPK) {
        ContestPhase activeContestPhase = ContestClientUtil.getActivePhase(contestPK);
        return ProposalClientUtil
                .getActiveProposalsInContestPhase(activeContestPhase.getContestPhasePK());
    }

    public static void sendMassMessageToAllProposalAuthors(List<Long> contestList, Object massMessageWrapperObject,
            HttpServletRequest request) {

        MassMessageBean massMessageBean = (MassMessageBean) massMessageWrapperObject;
        Set<Long> recipientIds = new HashSet<>();
        final StringBuilder contestNames = new StringBuilder();

        for (Long contestId : contestList) {
            Contest c = ContestClientUtil.getContest(contestId);
            if (!c.getIsSharedContestInForeignColab()) {
                contestNames.append(c.getContestShortName()).append("; ");
                List<Proposal> proposalsInActiveContestPhase =
                        getProposalsInOpenForSubmissionPhase(contestId);

                for (Proposal proposal : proposalsInActiveContestPhase) {
                    List<Member> proposalMember =
                            ProposalClientUtil.getProposalMembers(proposal.getProposalId());
                    for (Member member : proposalMember) {
                        if (!recipientIds.contains(member.getUserId())) {
                            recipientIds.add(member.getUserId());
                        }
                    }
                }
            }
        }
        sendEmail(massMessageBean,recipientIds,contestList,contestNames);
    }
    private static void sendEmail(MassMessageBean massMessageBean, Set<Long> recipientIds,List<Long> contestList, StringBuilder contestNames){

        final String messageSubject = massMessageBean.getSubject();
        final String messageBody = massMessageBean.getBody();
        MessagingClient.sendMessage(messageSubject, messageBody,
                CLIMATE_COLAB_TEAM_USER_ID, CLIMATE_COLAB_TEAM_USER_ID,
                new ArrayList<>(recipientIds));

        final String emailSubject = "Mass message: " + messageSubject;
        final String emailBody =
                String.format(
                        "The following message was sent to %d users in %d contests (%s): <br "
                                + "/><br /><br />",
                        recipientIds.size(), contestList.size(), contestNames) + HtmlUtil
                        .addHtmlLineBreaks(messageBody);

        final String adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        EmailClient.sendEmail(adminEmail, adminEmail, emailSubject,
                emailBody, true, null,null);
    }

    public static void sendMassMessage(List<Long> contestList, Object massMessageWrapperObject,
            HttpServletRequest request) {

        MassMessageBean massMessageBean = (MassMessageBean) massMessageWrapperObject;
        Set<Long> recipientIds = new HashSet<>();
        final StringBuilder contestNames = new StringBuilder();

        for (Long contestId : contestList) {
            Contest c = ContestClientUtil.getContest(contestId);
            if (!c.getIsSharedContestInForeignColab()) {
                contestNames.append(c.getContestShortName()).append("; ");
                List<Proposal> proposalsInActiveContestPhase =
                        getProposalsInActiveContestPhase(contestId);

                for (Proposal proposal : proposalsInActiveContestPhase) {
                    List<Member> proposalMember =
                            ProposalClientUtil.getProposalMembers(proposal.getProposalId());
                    for (Member member : proposalMember) {
                        if (!recipientIds.contains(member.getUserId())) {
                            recipientIds.add(member.getUserId());
                        }
                    }
                }
            }
        }

        sendEmail(massMessageBean,recipientIds,contestList,contestNames);

    }

    public static void changeSubscriptionStatus(List<Long> contestList,
            Object subscriptionStatusObject,
            HttpServletRequest request) {
        long memberId = MemberAuthUtil.getMemberId(request);
        for (Long contestId : contestList) {
            Contest contest = ContestClientUtil.getContest(contestId);
            if (!contest.getIsSharedContestInForeignColab()) {
                boolean subscriptionStatus = (boolean) subscriptionStatusObject;
                if (subscriptionStatus) {
                    ContestClientUtil.subscribeMemberToContest(contestId, memberId);
                } else {
                    ContestClientUtil.unsubscribeMemberFromContest(contestId, memberId);
                }
            }
        }
    }

    public static void deleteContestwithPhases(List<Long> contestList, Object actionConfirmed,
            HttpServletRequest request)
            throws MassActionRequiresConfirmationException {
        if ((Boolean) actionConfirmed) {
            for (Long contestId : contestList) {
                Contest c = ContestClientUtil.getContest(contestId);
                if (!c.getIsSharedContestInForeignColab()) {
                    List<ContestPhase> contestPhases =
                            ContestClientUtil.getAllContestPhases(contestId);
                    if (!contestPhases.isEmpty()) {
                        deleteContestAndPhases(contestId);
                    } else {
                        ContestClientUtil.deleteContest(contestId);
                    }
                }
            }
        } else {
            throw new MassActionRequiresConfirmationException();
        }
    }

    private static void deleteContestAndPhases(Long contestId) {
        deleteContestPhases(contestId);
        ContestClientUtil.deleteContest(contestId);
    }

    private static void deleteContestPhases(Long contestId) {
        List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(contestId);
        for (ContestPhase contestPhase : contestPhases) {
            ContestClientUtil.deleteContestPhase(contestPhase.getContestPhasePK());
        }
    }

    public static void setFlag(List<Long> contestList, Object flagTexToolTipValue,
            HttpServletRequest request) {
        for (Long contestId : contestList) {
            try {
                //Contest contest = ContestClientUtil.getContest(contestId);
                //if(!contest.getIsSharedContestInForeignColab()) {
                ContestFlagTextToolTipBean contestFlagTextToolTipBean =
                        (ContestFlagTextToolTipBean) flagTexToolTipValue;
                contestFlagTextToolTipBean.persist(ContestClientUtil.getContest(contestId));
                //}
            } catch (ContestNotFoundException ignored) {

            }

        }
    }

    public static void setModelSettings(List<Long> contestList, Object modelSettings,
            HttpServletRequest request) {
        for (Long contestId : contestList) {
            try {
                Contest contest = ContestClientUtil.getContest(contestId);
                if (!contest.getIsSharedContestInForeignColab()) {
                    ContestModelSettingsBean contestModelSettingsBean =
                            (ContestModelSettingsBean) modelSettings;
                    contestModelSettingsBean.persist(contest);
                }
            } catch (ContestNotFoundException ignored) {

            }
        }
    }
}

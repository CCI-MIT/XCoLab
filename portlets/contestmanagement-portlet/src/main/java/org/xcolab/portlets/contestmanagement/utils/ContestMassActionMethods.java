package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngineException;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.portlets.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.portlets.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.portlets.contestmanagement.beans.MassMessageBean;
import org.xcolab.portlets.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.html.HtmlUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
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
            try {
                List<Proposal> proposalsInActiveContestPhase = getProposalsInActiveContestPhase(contestId);
                ContestPhase activeContestPhase = ContestClient.getActivePhase(contestId);
                csvExportHelper
                        .addProposalAndAuthorDetailsToExportData(proposalsInActiveContestPhase, activeContestPhase);
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            } catch (PortalException e) {
                throw new InternalException("Failed to export data to csv: ", e);
            }
        }

        String exportFileName = "reportOfPeopleInCurrentPhase";
        csvExportHelper.initiateDownload(exportFileName, request, response);
    }

    public static void sendMassMessage(List<Long> contestList, Object massMessageWrapperObject, PortletRequest request)
            throws PortalException, SystemException, MailEngineException, AddressException,
            UnsupportedEncodingException {

        MassMessageBean massMessageBean = (MassMessageBean) massMessageWrapperObject;
        Set<Long> recipientIds = new HashSet<>();
        final StringBuilder contestNames = new StringBuilder();

        for (Long contestId : contestList) {
            contestNames.append(ContestLocalServiceUtil.getContest(contestId).getContestShortName()).append("; ");
            List<Proposal> proposalsInActiveContestPhase = getProposalsInActiveContestPhase(contestId);

            for (Proposal proposal : proposalsInActiveContestPhase) {
                List<User> proposalMember = ProposalLocalServiceUtil.getMembers(proposal.getProposalId());
                for (User user : proposalMember) {
                    if (!recipientIds.contains(user.getUserId())) {
                        recipientIds.add(user.getUserId());
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
                                                PortletRequest request) throws PortalException, SystemException {
        Long loggedInUserId = PortalUtil.getUserId(request);
        for (Long contestId : contestList) {
            Boolean subscriptionStatus = (boolean) subscriptionStatusObject;
            if (subscriptionStatus) {
                ContestLocalServiceUtil.subscribe(contestId, loggedInUserId);
            } else {
                ContestLocalServiceUtil.unsubscribe(contestId, loggedInUserId);
            }
        }
    }

    private static void deleteContestPhases(Long contestId) throws PortalException, SystemException {


        List<ContestPhase> contestPhases = ContestClient.getAllContestPhases(contestId);
        for (ContestPhase contestPhase : contestPhases) {
            ContestClient.deleteContestPhase(contestPhase.getContestPhasePK());
        }
    }

    private static void deleteContestAndPhases(Long contestId) throws PortalException, SystemException {
        deleteContestPhases(contestId);
        ContestLocalServiceUtil.deleteContest(contestId);
    }

    public static void deleteContest(List<Long> contestList, Object deletePhasesObj, PortletRequest request)
            throws PortalException, SystemException, MassActionRequiresConfirmationException {
        System.out.println("deleteContest called");
        System.out.println("with contests: " +contestList.toString());
        Boolean deletePhases = (Boolean) deletePhasesObj;
        System.out.println("delete phases: " + deletePhases);
        Boolean showContestPhaseDeleteConfirmation = false;
        for (Long contestId : contestList) {
            System.out.println("ContestId: " + contestId.toString());
            if (deletePhases) {
                deleteContestAndPhases(contestId);
            } else {
                showContestPhaseDeleteConfirmation = true;
            }
        }
        if (showContestPhaseDeleteConfirmation) {
            throw new MassActionRequiresConfirmationException();
        }
    }

    public static void deleteContestwithPhases(List<Long> contestList, Object deletePhasesObj, PortletRequest request)
            throws PortalException, SystemException, MassActionRequiresConfirmationException {
        System.out.println("deleteContestWithPhases called");
        System.out.println("with contests: " +contestList.toString());
        Boolean deletePhases = (Boolean) deletePhasesObj;
        System.out.println("delete phases: " + deletePhases);
        Boolean showContestPhaseDeleteConfirmation = false;
        for (Long contestId : contestList) {
            List<ContestPhase> contestPhases =
                    ContestClient.getAllContestPhases(contestId);
            if (!contestPhases.isEmpty()) {
                deleteContestAndPhases(contestId);
            } else {
                ContestLocalServiceUtil.deleteContest(contestId);
            }
            showContestPhaseDeleteConfirmation = true;
        }
        if (showContestPhaseDeleteConfirmation) {
            throw new MassActionRequiresConfirmationException();
        }
    }

    public static void setFlag(List<Long> contestList, Object flagTexToolTipValue, PortletRequest request)
            throws PortalException, SystemException {
        for (Long contestId : contestList) {
            try{
                Contest contest = ContestClient.getContest(contestId);
                ContestFlagTextToolTipBean contestFlagTextToolTipBean = (ContestFlagTextToolTipBean) flagTexToolTipValue;
                contestFlagTextToolTipBean.persist(contest);
            }catch (ContestNotFoundException ignored){

            }

        }
    }

    public static void setModelSettings(List<Long> contestList, Object modelSettings, PortletRequest request)
            throws PortalException, SystemException {
        for (Long contestId : contestList) {
            try {
                Contest contest = ContestClient.getContest(contestId);
                ContestModelSettingsBean contestModelSettingsBean = (ContestModelSettingsBean) modelSettings;
                contestModelSettingsBean.persist(contest);
            }catch (ContestNotFoundException ignored){

            }
        }
    }

    private static List<Proposal> getProposalsInActiveContestPhase(Long contestPK)
            throws PortalException, SystemException {
        ContestPhase activeContestPhase = ContestClient.getActivePhase(contestPK);
        return ProposalLocalServiceUtil.getActiveProposalsInContestPhase(activeContestPhase.getContestPhasePK());
    }
}

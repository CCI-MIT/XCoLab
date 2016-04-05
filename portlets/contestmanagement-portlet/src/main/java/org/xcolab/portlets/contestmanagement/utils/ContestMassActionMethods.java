package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
import org.xcolab.portlets.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.portlets.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.portlets.contestmanagement.beans.MassMessageBean;
import org.xcolab.portlets.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.utils.HtmlUtil;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Thomas on 3/5/2015.
 */
public class ContestMassActionMethods {

    private final static Log _log = LogFactoryUtil.getLog(ContestMassActionMethods.class);
    private static final Long CLIMATE_COLAB_TEAM_USER_ID = 1431053L;
    private static final List<String> CSV_EXPORT_HEADER =
            Arrays.asList("Contest", "Proposal Title", "Proposal Link", "Username", "First Name",
                    "Last Name", "Email Address", "Role", "Last phase");

    public static void reportOfPeopleInCurrentPhase(List<Long> contestList, Object ResourceResponseObject,
            PortletRequest request) throws Exception {

        ResourceResponse response = (ResourceResponse) ResourceResponseObject;
        CsvExportHelper csvExportHelper = new CsvExportHelper();
        csvExportHelper.addRowToExportData(CSV_EXPORT_HEADER);

        for (Long contestId : contestList) {
            try {
                List<Proposal> proposalsInActiveContestPhase = getProposalsInActiveContestPhase(contestId);
                ContestPhase activeContestPhase =
                        ContestLocalServiceUtil.getActivePhase(ContestLocalServiceUtil.getContest(contestId));
                csvExportHelper
                        .addProposalAndAuthorDetailsToExportData(proposalsInActiveContestPhase, activeContestPhase);
            } catch (Exception e) {
                _log.warn("Failed to export data for csv: ", e);
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
        MessageUtil.sendMessage(messageSubject, messageBody,
                CLIMATE_COLAB_TEAM_USER_ID, CLIMATE_COLAB_TEAM_USER_ID, recipientIds, request);

        final String emailSubject = "Mass message: " + messageSubject;
        final String emailBody =
                String.format("The following message was sent to %d users in %d contests (%s): <br /><br /><br />",
                        recipientIds.size(), contestList.size(), contestNames) + HtmlUtil
                        .addHtmlLineBreaks(messageBody);

        InternetAddress adminEmail = new InternetAddress("admin@climatecolab.org");
        InternetAddress[] adminEmailArray = {adminEmail};
        MailEngine.send(adminEmail, adminEmailArray, null, null, null,
                emailSubject, emailBody, true, null, null, null);
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
        Contest contest = ContestLocalServiceUtil.getContest(contestId);
        List<ContestPhase> contestPhases = ContestLocalServiceUtil.getAllPhases(contest);
        for (ContestPhase contestPhase : contestPhases) {
            ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
        }
    }

    private static void deleteContestAndPhases(Long contestId) throws PortalException, SystemException {
        deleteContestPhases(contestId);
        ContestLocalServiceUtil.deleteContest(contestId);
    }

    public static void deleteContest(List<Long> contestList, Object deletePhasesObj, PortletRequest request)
            throws PortalException, SystemException, MassActionRequiresConfirmationException {
        Boolean deletePhases = (Boolean) deletePhasesObj;
        Boolean showContestPhaseDeleteConfirmation = false;
        for (Long contestId : contestList) {
            List<ContestPhase> contestPhases =
                    ContestLocalServiceUtil.getAllPhases(ContestLocalServiceUtil.getContest(contestId));
            if (!contestPhases.isEmpty()) {
                if (deletePhases) {
                    deleteContestAndPhases(contestId);
                } else {
                    showContestPhaseDeleteConfirmation = true;
                }
            } else {
                ContestLocalServiceUtil.deleteContest(contestId);
            }
        }
        if (showContestPhaseDeleteConfirmation) {
            throw new MassActionRequiresConfirmationException();
        }
    }

    public static void setFlag(List<Long> contestList, Object flagTexToolTipValue, PortletRequest request)
            throws PortalException, SystemException {
        for (Long contestId : contestList) {
            Contest contest = ContestLocalServiceUtil.getContest(contestId);
            ContestFlagTextToolTipBean contestFlagTextToolTipBean = (ContestFlagTextToolTipBean) flagTexToolTipValue;
            contestFlagTextToolTipBean.persist(contest);
        }
    }

    public static void setModelSettings(List<Long> contestList, Object modelSettings, PortletRequest request)
            throws PortalException, SystemException {
        for (Long contestId : contestList) {
            Contest contest = ContestLocalServiceUtil.getContest(contestId);
            ContestModelSettingsBean contestModelSettingsBean = (ContestModelSettingsBean) modelSettings;
            contestModelSettingsBean.persist(contest);
        }
    }

    private static List<Proposal> getProposalsInActiveContestPhase(Long contestPK)
            throws PortalException, SystemException {
        Contest contest = ContestLocalServiceUtil.getContest(contestPK);
        ContestPhase activeContestPhase = ContestLocalServiceUtil.getActivePhase(contest);
        return ProposalLocalServiceUtil.getActiveProposalsInContestPhase(activeContestPhase.getContestPhasePK());
    }
}

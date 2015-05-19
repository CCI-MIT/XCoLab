package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngine;
import org.xcolab.portlets.contestmanagement.beans.MassMessageBean;

import javax.mail.internet.InternetAddress;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas on 3/5/2015.
 */
public class ContestMassActionMethods {

    private final static Log _log = LogFactoryUtil.getLog(ContestMassActionMethods.class);
    private static final Long CLIMATE_COLAB_TEAM_USER_ID = 1431053L;
    private static final List<String> CSV_EXPORT_HEADER =
            Arrays.asList("Contest", "Proposal Title", "Proposal Link", "Username", "First Name",
                    "Last Name", "Email Address", "Role", "Last phase");

    public static void reportOfPeopleInCurrentPhase(List<Long> contestList, Object ResourceResponseObject, PortletRequest request) throws Exception{

        String exportFileName ="reportOfPeopleInCurrentPhase";
        ResourceResponse response = (ResourceResponse) ResourceResponseObject;
        CsvExportUtil csvExportUtil = new CsvExportUtil();
        csvExportUtil.addRowToExportData(CSV_EXPORT_HEADER);

        for(Long contestId : contestList){
            try {
                List<Proposal> proposalsInActiveContestPhase = getProposalsInActiveContestPhase(contestId);
                ContestPhase activeContestPhase = ContestLocalServiceUtil.getActivePhase(ContestLocalServiceUtil.getContest(contestId));
                csvExportUtil.addProposalAndAuthorDetailsToExportData(proposalsInActiveContestPhase, activeContestPhase);
            } catch (Exception e){
                _log.warn("Failed to export data for csv: ", e);
            }
        }

        csvExportUtil.initiateDownload(exportFileName, request, response);

    }
    public static void sendMassMessage(List<Long> contestList, Object massMessageWrapperObject, PortletRequest request) throws Exception{

        MassMessageBean massMessageBean = (MassMessageBean) massMessageWrapperObject;
        List<Long> recipientIds = new ArrayList<>();
        List<InternetAddress> recipientAddresses = new  ArrayList<>();

        for(Long contestId : contestList) {
            List<Proposal> proposalsInActiveContestPhase = getProposalsInActiveContestPhase(contestId);

            for (Proposal proposal : proposalsInActiveContestPhase) {
                List<User> proposalMember = ProposalLocalServiceUtil.getMembers(proposal.getProposalId());
                for (User user : proposalMember) {
                    if (!recipientIds.contains(user.getUserId())) {
                        recipientIds.add(user.getUserId());
                        recipientAddresses.add(new InternetAddress(user.getEmailAddress()));
                    }
                }
            }
        }

        String messageSubject = massMessageBean.getSubject();
        String messageBody = massMessageBean.getBody();
        MessageUtil.sendMessage(messageSubject, messageBody,
                CLIMATE_COLAB_TEAM_USER_ID, CLIMATE_COLAB_TEAM_USER_ID, recipientIds, request);

        /* Enable this for email notifications
        InternetAddress addressFrom = new InternetAddress("admin@climatecolab.org");
        InternetAddress replyTo[] = {addressFrom};
        MailEngine.send(addressFrom, (InternetAddress[]) recipientAddresses.toArray(), null, null, null,
                messageSubject, messageBody, false, replyTo, null, null);
        */
        // }

    }

    public static void sendSupport2VotesEmail(List<Long> contestList, Object tbd, PortletRequest request) throws Exception{
        for(Long contestId : contestList) {
            Contest contest = ContestLocalServiceUtil.getContest(contestId);
            ServiceContext serviceContext = new ServiceContext();
            serviceContext.setPortalURL(String.format("%s://%s%s", request.getScheme(), request.getServerName(),
                    request.getServerPort() != 80 ? ":" + request.getServerPort() : ""));
            ContestLocalServiceUtil.transferSupportsToVote(contest, serviceContext);
        }
    }

    public static void changeSubscriptionStatus(List<Long> contestList, Object subscriptionStatusObject, PortletRequest request) throws Exception{
        Long loggedInUserId = PortalUtil.getUserId(request);
        for(Long contestId : contestList) {
            Boolean subscriptionStatus = (boolean) subscriptionStatusObject;
            if (subscriptionStatus) {
                ContestLocalServiceUtil.subscribe(contestId, loggedInUserId);
            } else {
                ContestLocalServiceUtil.unsubscribe(contestId, loggedInUserId);
            }
        }
    }

    public static void deleteContest(List<Long> contestList, Object tbd, PortletRequest request)throws Exception{
        for(Long contestId : contestList) {
            Contest contest = ContestLocalServiceUtil.getContest(contestId);
            if (!ContestLocalServiceUtil.getAllPhases(contest).isEmpty()) {
                _log.warn("Could not delete contest because it has phases!");
                return;
            }
            // TODO check why we get an exception here
            Contest deletedContest = ContestLocalServiceUtil.deleteContest(contest);
        }
    }


    private static List<Proposal> getProposalsInActiveContestPhase(Long contestPK) throws Exception{
        Contest contest = ContestLocalServiceUtil.getContest(contestPK);
        ContestPhase activeContestPhase = ContestLocalServiceUtil.getActivePhase(contest);
        List<Proposal> proposalsInContestPhase =
                ProposalLocalServiceUtil.getActiveProposalsInContestPhase(activeContestPhase.getContestPhasePK());
        return proposalsInContestPhase;
    }
}

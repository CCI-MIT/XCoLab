package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngine;
import org.xcolab.portlets.contestmanagement.beans.MassMessageBean;

import javax.mail.internet.InternetAddress;
import javax.portlet.PortletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 3/5/2015.
 */
public class ContestMassActionMethods {

    private static final Long ADMIN_ID = 10144L;

    public static void sendMassMessage(Long contestPK, Object massMessageWrapperObject, PortletRequest request) throws Exception{
        MassMessageBean massMessageBean = (MassMessageBean) massMessageWrapperObject;

        List<Long> recipientIds = new ArrayList<>();
        List<InternetAddress> recipientAddresses = new ArrayList<>();
        Contest contest = ContestLocalServiceUtil.getContest(contestPK);
        ContestPhase activeContestPhase = ContestLocalServiceUtil.getActivePhase(contest);
        List<Proposal> proposalsInContestPhase =
                ProposalLocalServiceUtil.getActiveProposalsInContestPhase(activeContestPhase.getContestPhasePK());

        for(Proposal proposal : proposalsInContestPhase){
            List<User> proposalMember = ProposalLocalServiceUtil.getMembers(proposal.getProposalId());
            for(User user : proposalMember){
                if(!recipientIds.contains(user.getUserId())) {
                    recipientIds.add(user.getUserId());
                    recipientAddresses.add(new InternetAddress(user.getEmailAddress()));
                }
            }
        }

        String messageSubject = massMessageBean.getSubject();
        String messageBody = massMessageBean.getBody();
        MessageUtil.sendMessage(messageSubject, messageBody,
                ADMIN_ID, ADMIN_ID, recipientIds, request);

        // TODO check whether we want email notification {
        /*
        InternetAddress addressFrom = new InternetAddress("admin@climatecolab.org");
        InternetAddress replyTo[] = {addressFrom};
        MailEngine.send(addressFrom, (InternetAddress[]) recipientAddresses.toArray(), null, null, null,
                messageSubject, messageBody, false, replyTo, null, null);
        */
        // }

    }

    public static void sendSupport2VotesEmail(Long contestPK, Object tbd, PortletRequest request) throws Exception{
        Contest contest = ContestLocalServiceUtil.getContest(contestPK);
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(String.format("%s://%s%s", request.getScheme(), request.getServerName(),
                request.getServerPort() != 80 ? ":" + request.getServerPort() : ""));
        ContestLocalServiceUtil.transferSupportsToVote(contest, serviceContext);
    }

    public static void reportOfPeopleInCurrentPhase(Long contestPK, Object tbd, PortletRequest request){
        // TODO reportOfPeopleInCurrentPhase
        System.out.println("TODO reportOfPeopleInCurrentPhase");
    }

    public static void changeSubscriptionStatus(Long contestPK, Object subscriptionStatusObject, PortletRequest request) throws Exception{
        Long loggedInUserId = PortalUtil.getUserId(request);
        Boolean subscriptionStatus = (boolean) subscriptionStatusObject;
        if(subscriptionStatus) {
            ContestLocalServiceUtil.subscribe(contestPK,loggedInUserId);
        } else {
            ContestLocalServiceUtil.unsubscribe(contestPK,loggedInUserId);
        }
    }

    public static void deleteContest(Long contestPK, Object tbd, PortletRequest request)throws Exception{
        Contest contest = ContestLocalServiceUtil.getContest(contestPK);

        if (! ContestLocalServiceUtil.getAllPhases(contest).isEmpty()) {
            System.out.println("CONTEST HAS PHASES");
            return;
        }
        Contest deleteContest = ContestLocalServiceUtil.deleteContest(contest);
        //ContestLocalServiceUtil.updateContest(deleteContest);
    }

}

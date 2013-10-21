package org.xcolab.proposals.events.handlers;

import org.xcolab.proposals.events.ProposalAssociatedWithContestPhaseEvent;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ActivitySubscriptionLocalService;
import com.ext.portlet.service.ProposalLocalService;
import com.google.common.eventbus.Subscribe;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;


/**
 * <p>Event handler that reacts to ProposalCreatedEvent. For each user that is subscribed
 * to a contest to which this proposal belongs, it subscribes him/her to the newly created proposal too</p>
 * 
 * @author janusz
 *
 */
public class SubscribeProposalForAllContestSubscribersEventHandler extends BaseEventHandler {

    @BeanReference(type = ActivitySubscriptionLocalService.class) 
    private ActivitySubscriptionLocalService activitySubscriptionLocalService;

    @BeanReference(type = ProposalLocalService.class) 
    private ProposalLocalService proposalLocalService;
    
    @Subscribe
    public void handleProposalCreatedEvent(ProposalAssociatedWithContestPhaseEvent event) throws PortalException, SystemException {
        
        long proposalId = event.getProposal().getProposalId();
        long contestId = event.getContestPhase().getContestPK();
        
        // autosubscribe all users that have been subscribed to 
        for (User user: activitySubscriptionLocalService.getSubscribedUsers(Contest.class, contestId)) {
            proposalLocalService.subscribe(proposalId, user.getUserId(), true);
        }
    }

}

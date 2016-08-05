package org.xcolab.proposals.events.handlers;

import com.ext.portlet.service.ActivitySubscriptionLocalService;
import com.google.common.eventbus.Subscribe;
import com.liferay.portal.kernel.bean.BeanReference;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.proposals.events.ProposalAssociatedWithContestPhaseEvent;
import org.xcolab.util.enums.activity.ActivityEntryType;

import java.util.List;


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
    
    @Subscribe
    public void handleProposalCreatedEvent(ProposalAssociatedWithContestPhaseEvent event) {
        
        long proposalId = event.getProposal().getProposalId();
        long contestId = event.getContestPhase().getContestPK();

        final List<ActivitySubscription> activitySubscriptions = ActivitiesClient
                .getActivitySubscriptions(ActivityEntryType.CONTEST.getPrimaryTypeId(), contestId, null);

        for (ActivitySubscription activitySubscription : activitySubscriptions) {
            proposalLocalService.subscribe(proposalId, activitySubscription.getReceiverId(), true);
        }
    }

}

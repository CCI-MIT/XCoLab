package org.xcolab.view.pages.proposals.view.proposal;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.activities.pojo.ActivitySubscription;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.enums.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.filtering.FilteringClient;
import org.xcolab.client.filtering.exceptions.FilteredEntryNotFoundException;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.loginregister.SharedColabUtil;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.edit.ProposalCreationUtil;
import org.xcolab.view.pages.proposals.utils.edit.ProposalMoveUtil;
import org.xcolab.view.pages.proposals.utils.edit.ProposalUpdateHelper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public final class AddUpdateProposalControllerUtil {

    private AddUpdateProposalControllerUtil() {
    }

    public static String createOrUpdateProposal(HttpServletRequest request,
            UpdateProposalDetailsBean updateProposalSectionsBean, Proposal proposal,
            ProposalsContext proposalsContext) {
        long memberId = MemberAuthUtil.getMemberId(request);
        final Contest contest = proposalsContext.getContest(request);

        final ClientHelper clients = proposalsContext.getClients(request);

        Proposal proposalWrapper;
        boolean createNew = false;
        final ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        if (proposal != null) {
            proposalWrapper = proposalsContext.getProposalWrapped(request);
            if (updateProposalSectionsBean.getIsMove() && updateProposalSectionsBean.getMoveToContestId() > 0) {
                ProposalMoveUtil.moveProposal(updateProposalSectionsBean,
                        proposalWrapper, contestPhase, contest, memberId, request);
            }
        } else {
            createNew = true;
            proposalWrapper = ProposalCreationUtil
                    .createProposal(memberId, updateProposalSectionsBean, contest, contestPhase);
        }

        final Proposal2Phase p2p = proposalsContext.getProposal2Phase(request);
        ProposalUpdateHelper proposalUpdateHelper = new ProposalUpdateHelper(
                updateProposalSectionsBean, request, proposalWrapper, p2p, memberId);
        proposalUpdateHelper.updateProposal();

        final ActivitiesClient activitiesClient = clients.getActivitiesClient();
        if (createNew) {
            ProposalCreationUtil.sendAuthorNotification(PlatformAttributeKey.PLATFORM_COLAB_URL.get(),
                    proposalWrapper, contestPhase, request);

            final List<ActivitySubscription> activitySubscriptions = activitiesClient
                    .getActivitySubscriptions(ActivityEntryType.CONTEST.getPrimaryTypeId(),
                            contest.getContestPK(), null);
            for (ActivitySubscription activitySubscription : activitySubscriptions) {
                final Long receiverId = activitySubscription.getReceiverId();
                activitiesClient.addSubscription(receiverId, ActivityEntryType.PROPOSAL,
                        proposalWrapper.getProposalId(), "");
            }

		 	ActivityEntryHelper.createActivityEntry(activitiesClient, memberId, contest.getContestPK() , proposalWrapper.getProposalId().toString(),
                    ActivityProvidersType.ProposalCreatedActivityEntry.getType());
        } else {
            ActivityEntryHelper.createActivityEntry(activitiesClient, memberId, proposalWrapper.getProposalId(), null,
                    ActivityProvidersType.ProposalAttributeUpdateActivityEntry.getType());
        }
        SharedColabUtil.checkTriggerForAutoUserCreationInContest(contest.getContestPK(), memberId);

        if (ConfigurationAttributeKey.FILTER_PROFANITY.get()) {
            try {
                FilteredEntry filteredEntry = FilteringClient
                        .getFilteredEntryByUuid(updateProposalSectionsBean.getUuid());
                filteredEntry.setSourceId(proposalWrapper.getProposalId());
                filteredEntry.setAuthorId(memberId);
                FilteringClient.updateFilteredEntry(filteredEntry);
            } catch (FilteredEntryNotFoundException ignored) {
            }
        }

        proposalsContext.invalidateContext(request);
        return "redirect:" + proposalWrapper.getProposalUrl();
    }
    
}

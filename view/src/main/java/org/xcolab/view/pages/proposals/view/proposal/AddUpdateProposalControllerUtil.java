package org.xcolab.view.pages.proposals.view.proposal;


import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.activities.enums.ContestActivityType;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.utils.edit.ProposalCreationUtil;
import org.xcolab.view.pages.proposals.utils.edit.ProposalMoveUtil;
import org.xcolab.view.pages.proposals.utils.edit.ProposalUpdateHelper;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsEventType;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public final class AddUpdateProposalControllerUtil {

    private AddUpdateProposalControllerUtil() {
    }

    private static boolean isProposalListClosed(ContestPhaseWrapper contestPhase) {
        return contestPhase.getStatus() == ContestStatus.CLOSED
                || contestPhase.getStatus() == ContestStatus.COMPLETED;
    }

    public static String createOrUpdateProposal(HttpServletRequest request,
            UpdateProposalDetailsBean updateProposalSectionsBean, ProposalWrapper proposal,
            ProposalContext proposalContext) {
        long userId = MemberAuthUtil.getUserId();
        final ContestWrapper contest = proposalContext.getContest();

        final ClientHelper clients = proposalContext.getClients();

        boolean createNew = false;
        final ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();
        if (proposal != null) {
            if (updateProposalSectionsBean.getIsMove() && updateProposalSectionsBean.getMoveToContestId() > 0) {
                ContestWrapper moveToContest = StaticContestContext.getContestClient()
                        .getContest(updateProposalSectionsBean.getMoveToContestId());
                ProposalMoveUtil.moveProposal(proposalContext, updateProposalSectionsBean,
                        proposal, contestPhase, moveToContest, userId);
            }
        } else {
            createNew = true;
            proposal = ProposalCreationUtil
                    .createProposal(userId, updateProposalSectionsBean, contest, contestPhase);
        }

        final IProposal2Phase p2p = proposalContext.getProposal2Phase();
        ProposalUpdateHelper proposalUpdateHelper = new ProposalUpdateHelper(request,
                proposalContext, updateProposalSectionsBean, proposal, p2p, userId);
        proposalUpdateHelper.updateProposal();

        final IActivityClient activityClient = StaticActivityContext.getActivityClient();
        if (createNew) {
            boolean isAdmin = StaticUserContext.getPermissionClient().canAdminAll(userId);
            boolean isClosed = isProposalListClosed(contestPhase);
            if (isAdmin && isClosed) {
                ServiceRequestUtils.clearCache(CacheName.PROPOSAL_LIST_CLOSED);
            }

            final List<IActivitySubscription> activitySubscriptions = activityClient
                    .getActivitySubscriptions(ActivityCategory.CONTEST,
                            contest.getId(), null);
            for (IActivitySubscription activitySubscription : activitySubscriptions) {
                final Long receiverId = activitySubscription.getReceiverUserId();
                activityClient.addSubscription(receiverId, ActivityCategory.PROPOSAL,
                        proposal.getId());

            }

            activityClient.createActivityEntry(ContestActivityType.PROPOSAL_CREATED, userId,
                    contest.getId(), proposal.getId());

            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.CONTEST_ENTRY_CREATION);

        } else {
            activityClient.createActivityEntry(ProposalActivityType.UPDATED, userId,
                    proposal.getId());
        }

        return "redirect:" + proposal.getProposalLinkUrl(contest);
    }
}

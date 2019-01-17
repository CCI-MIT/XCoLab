package org.xcolab.service.activities.service;

import org.jooq.DeleteFinalStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.proposals.ProposalClientUtil;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ActivitiesService {

    private static final Logger log = LoggerFactory.getLogger(ActivitiesService.class);

    private final ActivitySubscriptionDao activitySubscriptionDao;

    @Autowired
    public ActivitiesService(ActivitySubscriptionDao activitySubscriptionDao) {
        this.activitySubscriptionDao = activitySubscriptionDao;
    }

    public ActivitySubscription subscribe(long userId,
            ActivityCategory activityCategory, long categoryId) {
        if (activitySubscriptionDao.isSubscribed(activityCategory, userId, categoryId)) {
            return activitySubscriptionDao
                    .get(activityCategory, userId, categoryId)
                    .orElseThrow(IllegalStateException::new);
        }
        switch (activityCategory) {
            case CONTEST:
                return subscribeContest(userId, categoryId);
            case PROPOSAL:
                return subscribeProposal(userId, categoryId, false);
            case DISCUSSION:
                return subscribeDiscussion(userId, categoryId, false);
            case MEMBER:
                return createSubscription(userId, activityCategory, categoryId, 0);
            default:
                throw new IllegalArgumentException(
                        "ActivityCategory " + activityCategory.name() + " not supported");
        }
    }

    private ActivitySubscription createSubscription(long receiverId,
            ActivityCategory activityCategory, long categoryId,
            int automaticSubscriptionCounter) {
        ActivitySubscription activitySubscription = new ActivitySubscription();
        activitySubscription.setReceiverUserId(receiverId);
        activitySubscription.setActivityCategory(activityCategory.name());
        activitySubscription.setCategoryId(categoryId);
        activitySubscription.setAutomaticSubscriptionCounter(automaticSubscriptionCounter);
        return activitySubscriptionDao.create(activitySubscription);
    }

    private ActivitySubscription subscribeContest(long userId, long contestId) {
        final ActivitySubscription contestSubscription = createSubscription(userId,
                ActivityCategory.CONTEST, contestId, 0);

        Contest contest = ContestClientUtil.getContest(contestId);

        subscribeDiscussion(userId,contest.getDiscussionGroupId(), true);

        final List<Proposal> proposals = ProposalClientUtil
                .listProposals(contestId);
        final Set<Long> processedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            if (!processedProposals.contains(proposal.getId())) {
                subscribeProposal(userId, proposal.getId(), true);
                processedProposals.add(proposal.getId());
            }
        }
        return contestSubscription;
    }

    private ActivitySubscription subscribeProposal(long userId, long proposalId,
            boolean automatic) {
        ActivitySubscription proposalSubscription;
        if (automatic) {
            final Optional<ActivitySubscription> automaticSubscription = activitySubscriptionDao
                    .get(ActivityCategory.PROPOSAL, userId, proposalId);
            automaticSubscription.ifPresent(activitySubscription -> {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();
                        activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                        activitySubscriptionDao.update(activitySubscription);
                    });
            proposalSubscription = automaticSubscription.orElseGet(() -> createSubscription(
                    userId, ActivityCategory.PROPOSAL, proposalId, 0));

        } else {
            activitySubscriptionDao.delete(ActivityCategory.PROPOSAL, userId,
                    proposalId);
            proposalSubscription = createSubscription(userId, ActivityCategory.PROPOSAL,
                    proposalId, 0);
        }
        try {
            Proposal proposal = ProposalClientUtil.getProposal(proposalId);
            subscribeDiscussion(userId, proposal.getDiscussionId(), true);
        } catch (ProposalNotFoundException e) {
            log.warn("Proposal {} not found", proposalId, e);
        }
        return proposalSubscription;
    }

    private ActivitySubscription subscribeDiscussion(long userId,
            long threadId, boolean automatic) {
        ActivitySubscription discussionSubscription;
        if (automatic) {
            Optional<ActivitySubscription> automaticSubscription = activitySubscriptionDao.get(
                    ActivityCategory.DISCUSSION, userId, threadId);
            automaticSubscription.ifPresent(activitySubscription -> {
                final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();
                activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                activitySubscriptionDao.update(activitySubscription);
            });
            discussionSubscription = automaticSubscription.orElseGet(() -> createSubscription(
                    userId, ActivityCategory.DISCUSSION, threadId, 0));
        } else {
            activitySubscriptionDao
                    .delete(ActivityCategory.DISCUSSION, userId, threadId);
            discussionSubscription = createSubscription(userId, ActivityCategory.DISCUSSION,
                    threadId, 0);
        }
        return discussionSubscription;
    }

    public boolean unsubscribe(long userId, ActivityCategory activityCategory, long categoryId) {
        switch (activityCategory) {
            case CONTEST:
                return unsubscribeContest(userId, categoryId);
            case PROPOSAL:
                activitySubscriptionDao.batch(
                        getProposalDeleteQueries(userId, categoryId));
                return true;
            case DISCUSSION:
                activitySubscriptionDao.batch(
                        getDiscussionDeleteQueries(userId, categoryId, false));
                return true;
            case MEMBER:
                return activitySubscriptionDao
                        .delete(activityCategory, userId, categoryId);
            default:
                throw new IllegalArgumentException("ActivityCategory " + activityCategory.name()
                        + " not supported");
        }
    }

    private boolean unsubscribeContest(long userId, long contestId) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();
        queries.add(activitySubscriptionDao
                .getDeleteQuery(ActivityCategory.CONTEST, userId, contestId));

        final List<Proposal> proposals = ProposalClientUtil
                .listProposals(contestId);
        final Set<Long> processedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            if (!processedProposals.contains(proposal.getId())) {
                queries.addAll(
                        getProposalDeleteQueries(userId, proposal.getId(), true));
                processedProposals.add(proposal.getId());
            }
        }
        Contest contest = ContestClientUtil.getContest(contestId);
        queries.addAll(getDiscussionDeleteQueries(userId,
                contest.getDiscussionGroupId(), true));

        activitySubscriptionDao.batch(queries);
        return true;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getDiscussionDeleteQueries(
            long userId, long threadId, boolean automatic) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();

        if (automatic) {
            activitySubscriptionDao.get(ActivityCategory.PROPOSAL, userId,
                    threadId)
                    .ifPresent(activitySubscription ->  {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();

                        if (counter == 1) {
                            queries.add(activitySubscriptionDao.getDeleteQuery(
                                    ActivityCategory.DISCUSSION, userId, threadId));
                        } else if (counter > 1) {
                            activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                            activitySubscriptionDao.update(activitySubscription);
                        }
                    });
        } else {
            queries.add(activitySubscriptionDao.getDeleteQuery(ActivityCategory.DISCUSSION,
                    userId, threadId));
        }

        return queries;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getProposalDeleteQueries(
            long userId, long proposalId, boolean automatic) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();

        if (automatic) {
            activitySubscriptionDao
                    .get(ActivityCategory.PROPOSAL, userId, proposalId)
                    .ifPresent(activitySubscription ->  {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();

                        if (counter == 1) {
                            queries.addAll(getProposalDeleteQueries(userId, proposalId));
                        } else if (counter > 1) {
                            activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                            activitySubscriptionDao.update(activitySubscription);
                        }
                    });
        } else {
            queries.addAll(getProposalDeleteQueries(userId, proposalId));
        }

        return queries;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getProposalDeleteQueries(
            long userId, long proposalId)  {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();
        queries.add(activitySubscriptionDao
                .getDeleteQuery(ActivityCategory.PROPOSAL, userId, proposalId));
        try {
            final Proposal proposal = ProposalClientUtil.getProposal(proposalId);
            queries.addAll(getDiscussionDeleteQueries(userId,
                    proposal.getDiscussionId(), true));
        } catch (ProposalNotFoundException ignored) {
            //orphaned activity - ignore
        }
        return queries;
    }
}

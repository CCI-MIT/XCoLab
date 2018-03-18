package org.xcolab.service.activities.service;

import org.jooq.DeleteFinalStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
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

    public ActivitySubscription subscribe(long memberId,
            ActivityCategory activityCategory, long categoryId) {
        if (activitySubscriptionDao.isSubscribed(activityCategory, memberId, categoryId)) {
            return activitySubscriptionDao
                    .get(activityCategory, memberId, categoryId)
                    .orElseThrow(IllegalStateException::new);
        }
        switch (activityCategory) {
            case CONTEST:
                return subscribeContest(memberId, categoryId);
            case PROPOSAL:
                return subscribeProposal(memberId, categoryId, false);
            case DISCUSSION:
                return subscribeDiscussion(memberId, categoryId, false);
            case MEMBER:
                return createSubscription(memberId, activityCategory, categoryId, 0);
            default:
                throw new IllegalArgumentException(
                        "ActivityCategory " + activityCategory.name() + " not supported");
        }
    }

    private ActivitySubscription createSubscription(long receiverId,
            ActivityCategory activityCategory, long categoryId,
            int automaticSubscriptionCounter) {
        ActivitySubscription activitySubscription = new ActivitySubscription();
        activitySubscription.setReceiverId(receiverId);
        activitySubscription.setActivityCategory(activityCategory.name());
        activitySubscription.setCategoryId(categoryId);
        activitySubscription.setAutomaticSubscriptionCounter(automaticSubscriptionCounter);
        return activitySubscriptionDao.create(activitySubscription);
    }

    private ActivitySubscription subscribeContest(long memberId, long contestId) {
        final ActivitySubscription contestSubscription = createSubscription(memberId,
                ActivityCategory.CONTEST, contestId, 0);

        Contest contest = ContestClientUtil.getContest(contestId);

        subscribeDiscussion(memberId,contest.getDiscussionGroupId(), true);

        final List<Proposal> proposals = ProposalClientUtil
                .listProposals(contestId);
        final Set<Long> processedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            if (!processedProposals.contains(proposal.getProposalId())) {
                subscribeProposal(memberId, proposal.getProposalId(), true);
                processedProposals.add(proposal.getProposalId());
            }
        }
        return contestSubscription;
    }

    private ActivitySubscription subscribeProposal(long memberId, long proposalId,
            boolean automatic) {
        ActivitySubscription proposalSubscription;
        if (automatic) {
            final Optional<ActivitySubscription> automaticSubscription = activitySubscriptionDao
                    .get(ActivityCategory.PROPOSAL, memberId, proposalId);
            automaticSubscription.ifPresent(activitySubscription -> {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();
                        activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                        activitySubscriptionDao.update(activitySubscription);
                    });
            proposalSubscription = automaticSubscription.orElseGet(() -> createSubscription(
                    memberId, ActivityCategory.PROPOSAL, proposalId, 0));

        } else {
            activitySubscriptionDao.delete(ActivityCategory.PROPOSAL, memberId,
                    proposalId);
            proposalSubscription = createSubscription(memberId, ActivityCategory.PROPOSAL,
                    proposalId, 0);
        }
        try {
            Proposal proposal = ProposalClientUtil.getProposal(proposalId);
            subscribeDiscussion(memberId, proposal.getDiscussionId(), true);
        } catch (ProposalNotFoundException e) {
            log.warn("Proposal {} not found", proposalId, e);
        }
        return proposalSubscription;
    }

    private ActivitySubscription subscribeDiscussion(long memberId,
            long threadId, boolean automatic) {
        ActivitySubscription discussionSubscription;
        if (automatic) {
            Optional<ActivitySubscription> automaticSubscription = activitySubscriptionDao.get(
                    ActivityCategory.DISCUSSION, memberId, threadId);
            automaticSubscription.ifPresent(activitySubscription -> {
                final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();
                activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                activitySubscriptionDao.update(activitySubscription);
            });
            discussionSubscription = automaticSubscription.orElseGet(() -> createSubscription(
                    memberId, ActivityCategory.DISCUSSION, threadId, 0));
        } else {
            activitySubscriptionDao
                    .delete(ActivityCategory.DISCUSSION, memberId, threadId);
            discussionSubscription = createSubscription(memberId, ActivityCategory.DISCUSSION,
                    threadId, 0);
        }
        return discussionSubscription;
    }

    public boolean unsubscribe(long memberId, ActivityCategory activityCategory, long categoryId) {
        switch (activityCategory) {
            case CONTEST:
                return unsubscribeContest(memberId, categoryId);
            case PROPOSAL:
                activitySubscriptionDao.batch(
                        getProposalDeleteQueries(memberId, categoryId));
                return true;
            case DISCUSSION:
                activitySubscriptionDao.batch(
                        getDiscussionDeleteQueries(memberId, categoryId, false));
                return true;
            case MEMBER:
                return activitySubscriptionDao
                        .delete(activityCategory, memberId, categoryId);
            default:
                throw new IllegalArgumentException("ActivityCategory " + activityCategory.name()
                        + " not supported");
        }
    }

    private boolean unsubscribeContest(long memberId, long contestId) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();
        queries.add(activitySubscriptionDao
                .getDeleteQuery(ActivityCategory.CONTEST, memberId, contestId));

        final List<Proposal> proposals = ProposalClientUtil
                .listProposals(contestId);
        final Set<Long> processedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            if (!processedProposals.contains(proposal.getProposalId())) {
                queries.addAll(
                        getProposalDeleteQueries(memberId, proposal.getProposalId(), true));
                processedProposals.add(proposal.getProposalId());
            }
        }
        Contest contest = ContestClientUtil.getContest(contestId);
        queries.addAll(getDiscussionDeleteQueries(memberId,
                contest.getDiscussionGroupId(), true));

        activitySubscriptionDao.batch(queries);
        return true;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getDiscussionDeleteQueries(
            long memberId, long threadId, boolean automatic) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();

        if (automatic) {
            activitySubscriptionDao.get(ActivityCategory.PROPOSAL, memberId,
                    threadId)
                    .ifPresent(activitySubscription ->  {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();

                        if (counter == 1) {
                            queries.add(activitySubscriptionDao.getDeleteQuery(
                                    ActivityCategory.DISCUSSION, memberId, threadId));
                        } else if (counter > 1) {
                            activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                            activitySubscriptionDao.update(activitySubscription);
                        }
                    });
        } else {
            queries.add(activitySubscriptionDao.getDeleteQuery(ActivityCategory.DISCUSSION,
                    memberId, threadId));
        }

        return queries;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getProposalDeleteQueries(
            long memberId, long proposalId, boolean automatic) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();

        if (automatic) {
            activitySubscriptionDao
                    .get(ActivityCategory.PROPOSAL, memberId, proposalId)
                    .ifPresent(activitySubscription ->  {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();

                        if (counter == 1) {
                            queries.addAll(getProposalDeleteQueries(memberId, proposalId));
                        } else if (counter > 1) {
                            activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                            activitySubscriptionDao.update(activitySubscription);
                        }
                    });
        } else {
            queries.addAll(getProposalDeleteQueries(memberId, proposalId));
        }

        return queries;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getProposalDeleteQueries(
            long memberId, long proposalId)  {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();
        queries.add(activitySubscriptionDao
                .getDeleteQuery(ActivityCategory.PROPOSAL, memberId, proposalId));
        try {
            final Proposal proposal = ProposalClientUtil.getProposal(proposalId);
            queries.addAll(getDiscussionDeleteQueries(memberId,
                    proposal.getDiscussionId(), true));
        } catch (ProposalNotFoundException ignored) {
            //orphaned activity - ignore
        }
        return queries;
    }
}

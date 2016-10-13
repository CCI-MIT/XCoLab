package org.xcolab.service.activities.service;

import org.jooq.DeleteFinalStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.util.enums.activity.ActivityEntryType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
            ActivityEntryType activityEntryType, long classPK, String extraInfo) {
        if (activitySubscriptionDao.isSubscribed(memberId,
                activityEntryType.getPrimaryTypeId(), classPK, 0, extraInfo)) {
            return activitySubscriptionDao
                    .get(memberId, activityEntryType.getPrimaryTypeId(), classPK, extraInfo)
                    .orElseThrow(IllegalStateException::new);
        }
        switch (activityEntryType) {
            case CONTEST:
                return subscribeContest(memberId, classPK, extraInfo);
            case PROPOSAL:
                return subscribeProposal(memberId, classPK, extraInfo, false);
            case DISCUSSION:
                return subscribeDiscussion(memberId, classPK, false);
            case MEMBER:
                return createSubscription(memberId, activityEntryType, classPK, extraInfo, 0);
            default:
                throw new IllegalArgumentException(
                        "ActivityEntryType " + activityEntryType.name()
                                + " not supported");
        }
    }

    private ActivitySubscription createSubscription(long memberId,
            ActivityEntryType activityEntryType, long classPK, String extraInfo,
            int automaticSubscriptionCounter) {
        ActivitySubscription activitySubscription = new ActivitySubscription();
        activitySubscription.setReceiverId(memberId);
        activitySubscription.setClassNameId(activityEntryType.getPrimaryTypeId());
        activitySubscription.setClassPK(classPK);
        activitySubscription.setType_(0);
        activitySubscription.setAutomaticSubscriptionCounter(automaticSubscriptionCounter);
        activitySubscription.setExtraData(extraInfo);
        activitySubscription.setCreateDate(new Timestamp(new Date().getTime()));
        activitySubscription.setModifiedDate(new Timestamp(new Date().getTime()));
        return activitySubscriptionDao.create(activitySubscription);
    }

    private ActivitySubscription subscribeContest(long memberId, long contestId, String extraInfo) {
        final ActivitySubscription contestSubscription = createSubscription(memberId,
                ActivityEntryType.CONTEST, contestId, extraInfo, 0);

        final List<Proposal> proposals = ProposalClientUtil
                .listProposals(contestId);
        final Set<Long> processedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            if (!processedProposals.contains(proposal.getProposalId())) {
                subscribeProposal(memberId, proposal.getProposalId(), "", true);
                processedProposals.add(proposal.getProposalId());
            }
        }
        return contestSubscription;
    }

    private ActivitySubscription subscribeProposal(long memberId, long proposalId, String extraInfo,
            boolean automatic) {
        ActivitySubscription proposalSubscription;
        if (automatic) {
            final Optional<ActivitySubscription> automaticSubscription = activitySubscriptionDao
                    .get(memberId, ActivityEntryType.PROPOSAL.getPrimaryTypeId(),
                            proposalId, extraInfo);
            automaticSubscription.ifPresent(activitySubscription -> {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();
                        activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                        activitySubscriptionDao.update(activitySubscription);
                    });
            proposalSubscription = automaticSubscription.orElseGet(() -> createSubscription(
                    memberId, ActivityEntryType.PROPOSAL, proposalId, extraInfo, 0));
        } else {
            activitySubscriptionDao.delete(memberId, ActivityEntryType.PROPOSAL.getPrimaryTypeId(),
                    proposalId, extraInfo);
            proposalSubscription = createSubscription(memberId, ActivityEntryType.PROPOSAL,
                    proposalId, extraInfo, 0);
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
                    memberId, ActivityEntryType.DISCUSSION.getPrimaryTypeId(), threadId, null);
            automaticSubscription.ifPresent(activitySubscription -> {
                final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();
                activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                activitySubscriptionDao.update(activitySubscription);
            });
            discussionSubscription = automaticSubscription.orElseGet(() -> createSubscription(
                    memberId, ActivityEntryType.DISCUSSION, threadId, null, 0));
        } else {
            activitySubscriptionDao
                    .delete(memberId, ActivityEntryType.DISCUSSION.getPrimaryTypeId(),
                            threadId, null);
            discussionSubscription = createSubscription(memberId, ActivityEntryType.DISCUSSION,
                    threadId, null, 0);
        }
        return discussionSubscription;
    }

    public boolean unsubscribe(long memberId, ActivityEntryType activityEntryType, long classPK,
            String extraInfo) {
        switch (activityEntryType) {
            case CONTEST:
                return unsubscribeContest(memberId, classPK, extraInfo);
            case PROPOSAL:
                activitySubscriptionDao.batch(
                        getProposalDeleteQueries(memberId, classPK, extraInfo, false));
                return true;
            case DISCUSSION:
                activitySubscriptionDao.batch(
                        getDiscussionDeleteQueries(memberId, classPK, false));
                return true;
            case MEMBER:
                return activitySubscriptionDao
                        .delete(memberId, activityEntryType.getPrimaryTypeId(),
                                classPK, extraInfo);
            default:
                throw new IllegalArgumentException("ActivityEntryType " + activityEntryType.name()
                        + " not supported");
        }
    }

    private boolean unsubscribeContest(long memberId, long contestId, String extraInfo) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();
        queries.add(activitySubscriptionDao
                .getDeleteQuery(memberId,
                        ActivityEntryType.CONTEST.getPrimaryTypeId(), contestId, extraInfo));

        final List<Proposal> proposals = ProposalClientUtil
                .listProposals(contestId);
        final Set<Long> processedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            if (!processedProposals.contains(proposal.getProposalId())) {
                queries.addAll(
                        getProposalDeleteQueries(memberId, proposal.getProposalId(), "", true));
                processedProposals.add(proposal.getProposalId());
            }
        }

        activitySubscriptionDao.batch(queries);
        return true;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getDiscussionDeleteQueries(
            long memberId, long threadId, boolean automatic) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();

        if (automatic) {
            activitySubscriptionDao.get(memberId, ActivityEntryType.PROPOSAL.getPrimaryTypeId(),
                            threadId, null)
                    .ifPresent(activitySubscription ->  {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();

                        if (counter == 1) {
                            queries.add(activitySubscriptionDao.getDeleteQuery(memberId,
                                    ActivityEntryType.DISCUSSION.getPrimaryTypeId(), threadId, null));
                        } else if (counter > 1) {
                            activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                            activitySubscriptionDao.update(activitySubscription);
                        }
                    });
        } else {
            queries.add(activitySubscriptionDao.getDeleteQuery(memberId,
                    ActivityEntryType.DISCUSSION.getPrimaryTypeId(), threadId, null));
        }

        return queries;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getProposalDeleteQueries(
            long memberId, long proposalId, String extraInfo, boolean automatic) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();

        if (automatic) {
            activitySubscriptionDao
                    .get(memberId, ActivityEntryType.PROPOSAL.getPrimaryTypeId(),
                            proposalId, extraInfo)
                    .ifPresent(activitySubscription ->  {
                        final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();

                        if (counter == 1) {
                            queries.addAll(getProposalDeleteQueries(memberId, proposalId, extraInfo));
                        } else if (counter > 1) {
                            activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                            activitySubscriptionDao.update(activitySubscription);
                        }
                    });
        } else {
            queries.addAll(getProposalDeleteQueries(memberId, proposalId, extraInfo));
        }

        return queries;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getProposalDeleteQueries(
            long memberId, long proposalId, String extraInfo)  {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();
        queries.add(activitySubscriptionDao
                .getDeleteQuery(memberId,
                        ActivityEntryType.PROPOSAL.getPrimaryTypeId(), proposalId, extraInfo));
        try {
            final Proposal proposal = ProposalClientUtil.getProposal(proposalId);
            queries.addAll(getDiscussionDeleteQueries(memberId,
                    proposal.getDiscussionId(), true));
        } catch (ProposalNotFoundException e) {
            //TODO: log failure
        }
        return queries;
    }
}

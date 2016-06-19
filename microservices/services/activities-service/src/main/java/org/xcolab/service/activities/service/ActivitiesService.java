package org.xcolab.service.activities.service;

import org.jooq.DeleteFinalStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.model.tables.records.ActivitySubscriptionRecord;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.service.activities.exceptions.NotFoundException;
import org.xcolab.util.enums.activities.ActivityEntryType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ActivitiesService {

    private final ActivitySubscriptionDao activitySubscriptionDao;

    @Autowired
    public ActivitiesService(ActivitySubscriptionDao activitySubscriptionDao) {
        this.activitySubscriptionDao = activitySubscriptionDao;
    }

    public ActivitySubscription subscribe(long memberId, ActivityEntryType activityEntryType,
            long classPK,
            String extraInfo) throws ContestNotFoundException, ProposalNotFoundException {
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
                throw new IllegalArgumentException("ActivityEntryType " + activityEntryType.name()
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

    private ActivitySubscription subscribeContest(long memberId, long contestId, String extraInfo)
            throws ContestNotFoundException {
        final ActivitySubscription contestSubscription = createSubscription(memberId,
                ActivityEntryType.CONTEST, contestId, extraInfo, 0);

        ContestClient.getContest(contestId);
        final List<Proposal> proposals = ProposalsClient
                .listProposals(0, Integer.MAX_VALUE, contestId);
        final Set<Long> processedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            try {
                if (!processedProposals.contains(proposal.getProposalId())) {
                    subscribeProposal(memberId, proposal.getProposalId(), "", true);
                    processedProposals.add(proposal.getProposalId());
                }
            } catch (ProposalNotFoundException ignored) {
            }
        }
        return contestSubscription;
    }

    private ActivitySubscription subscribeProposal(long memberId, long proposalId, String extraInfo,
            boolean automatic) throws ProposalNotFoundException {
        ActivitySubscription proposalSubscription;
        Proposal proposal = ProposalsClient.getProposal(proposalId);
        if (automatic) {
            try {
                proposalSubscription = activitySubscriptionDao.get(memberId,
                        ActivityEntryType.PROPOSAL.getPrimaryTypeId(), proposalId, extraInfo);
                final Integer counter = proposalSubscription.getAutomaticSubscriptionCounter();
                proposalSubscription.setAutomaticSubscriptionCounter(counter - 1);
                activitySubscriptionDao.update(proposalSubscription);
            } catch (NotFoundException e) {
                proposalSubscription = createSubscription(memberId, ActivityEntryType.PROPOSAL,
                        proposalId, extraInfo, 0);
            }
        } else {
            activitySubscriptionDao.delete(memberId, ActivityEntryType.PROPOSAL.getPrimaryTypeId(),
                    proposalId, extraInfo);
            proposalSubscription = createSubscription(memberId, ActivityEntryType.PROPOSAL, proposalId,
                    extraInfo, 0);
        }
        subscribeDiscussion(memberId, proposal.getDiscussionId(), true);
        return proposalSubscription;
    }

    private ActivitySubscription subscribeDiscussion(long memberId,
            long threadId, boolean automatic) {
        ActivitySubscription discussionSubscription;
        if (automatic) {
            try {
                discussionSubscription = activitySubscriptionDao.get(memberId,
                        ActivityEntryType.DISCUSSION.getPrimaryTypeId(), threadId, null);
                final Integer counter = discussionSubscription.getAutomaticSubscriptionCounter();
                discussionSubscription.setAutomaticSubscriptionCounter(counter - 1);
                activitySubscriptionDao.update(discussionSubscription);
            } catch (NotFoundException e) {
                discussionSubscription = createSubscription(memberId, ActivityEntryType.DISCUSSION,
                        threadId, null, 0);
            }
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
            String extraInfo)
            throws ProposalNotFoundException, ContestNotFoundException {
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

    private boolean unsubscribeContest(long memberId, long contestId, String extraInfo)
            throws ContestNotFoundException {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();
        queries.add(activitySubscriptionDao
                .getDeleteQuery(memberId,
                        ActivityEntryType.CONTEST.getPrimaryTypeId(), contestId, extraInfo));

        ContestClient.getContest(contestId);
        final List<Proposal> proposals = ProposalsClient
                .listProposals(0, Integer.MAX_VALUE, contestId);
        final Set<Long> processedProposals = new HashSet<>();
        for (Proposal proposal : proposals) {
            try {
                if (!processedProposals.contains(proposal.getProposalId())) {
                    queries.addAll(
                            getProposalDeleteQueries(memberId, proposal.getProposalId(), "", true));
                    processedProposals.add(proposal.getProposalId());
                }
            } catch (ProposalNotFoundException ignored) {
            }
        }

        activitySubscriptionDao.batch(queries);
        return true;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getDiscussionDeleteQueries(
            long memberId, long threadId, boolean automatic) {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();

        if (automatic) {
            try {
                final ActivitySubscription activitySubscription = activitySubscriptionDao
                        .get(memberId, ActivityEntryType.PROPOSAL.getPrimaryTypeId(),
                                threadId, null);
                final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();

                if (counter == 1) {
                    queries.add(activitySubscriptionDao.getDeleteQuery(memberId,
                            ActivityEntryType.DISCUSSION.getPrimaryTypeId(), threadId, null));
                } else if (counter > 1) {
                    activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                    activitySubscriptionDao.update(activitySubscription);
                }
            } catch (NotFoundException ignored) {
            }
        } else {
            queries.add(activitySubscriptionDao.getDeleteQuery(memberId,
                    ActivityEntryType.DISCUSSION.getPrimaryTypeId(), threadId, null));
        }

        return queries;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getProposalDeleteQueries(
            long memberId, long proposalId, String extraInfo, boolean automatic)
            throws ProposalNotFoundException {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();

        if (automatic) {
            try {
                final ActivitySubscription activitySubscription = activitySubscriptionDao
                        .get(memberId, ActivityEntryType.PROPOSAL.getPrimaryTypeId(),
                                proposalId, extraInfo);
                final Integer counter = activitySubscription.getAutomaticSubscriptionCounter();

                if (counter == 1) {
                    queries.addAll(getProposalDeleteQueries(memberId, proposalId, extraInfo));
                } else if (counter > 1) {
                    activitySubscription.setAutomaticSubscriptionCounter(counter - 1);
                    activitySubscriptionDao.update(activitySubscription);
                }
            } catch (NotFoundException ignored) {
            }

        } else {
            queries.addAll(getProposalDeleteQueries(memberId, proposalId, extraInfo));
        }

        return queries;
    }

    private List<DeleteFinalStep<ActivitySubscriptionRecord>> getProposalDeleteQueries(
            long memberId, long proposalId, String extraInfo)
            throws ProposalNotFoundException {
        final List<DeleteFinalStep<ActivitySubscriptionRecord>> queries = new ArrayList<>();
        final Proposal proposal = ProposalsClient.getProposal(proposalId);
        queries.add(activitySubscriptionDao
                .getDeleteQuery(memberId,
                        ActivityEntryType.PROPOSAL.getPrimaryTypeId(), proposalId, extraInfo));
        queries.addAll(getDiscussionDeleteQueries(memberId,
                proposal.getDiscussionId(), true));
        return queries;
    }
}

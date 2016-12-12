package org.xcolab.service.proposal.service.proposal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.UsersGroupsClientUtil;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.UsersGroups;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.model.tables.pojos.Group_;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.service.proposal.domain.group.GroupDao;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.activity.ActivityEntryType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class ProposalService {

    private final ProposalDao proposalDao;

    private final ProposalReferenceDao proposalReferenceDao;

    private final ProposalAttributeDao proposalAttributeDao;

    private final Proposal2PhaseDao proposal2PhaseDao;

    private final GroupDao groupDao;


    @Autowired
    public ProposalService(ProposalDao proposalDao, ProposalReferenceDao proposalReferenceDao, ProposalAttributeDao proposalAttributeDao, Proposal2PhaseDao proposal2PhaseDao, GroupDao groupDao) {
        this.proposalDao = proposalDao;
        this.proposalReferenceDao = proposalReferenceDao;
        this.proposalAttributeDao = proposalAttributeDao;
        this.proposal2PhaseDao = proposal2PhaseDao;
        this.groupDao = groupDao;
    }

    public Proposal create(long authorId, long contestPhaseId, boolean publishActivity) {
        try {
            Proposal proposal = new Proposal();
            proposal.setVisible(true);
            proposal.setAuthorId(authorId);
            proposal.setCreateDate(new Timestamp(new Date().getTime()));
            proposal.setCurrentVersion(1);

            ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
            final Contest contest = ContestClientUtil.getContest(contestPhase.getContestPK());
            ContestType contestType = ContestClientUtil.getContestType(contest.getContestTypeId());

            proposal = proposalDao.create(proposal);
            Long proposalId = proposal.getProposalId();
            // create discussions
            final String proposalEntityName = contestType.getProposalName() + " ";


            final CommentThread mainCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " main discussion",
                    authorId, false);

            proposal.setDiscussionId(mainCommentThread.getThreadId());


            final CommentThread resultsCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " results discussion",
                    authorId, true);

            proposal.setResultsDiscussionId(resultsCommentThread.getThreadId());


            final CommentThread judgeCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " judges discussion",
                    authorId, true);

            proposal.setJudgeDiscussionId(judgeCommentThread.getThreadId());


            final CommentThread advisorsCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " advisors discussion",
                    authorId, true);

            proposal.setAdvisorDiscussionId(advisorsCommentThread.getThreadId());


            final CommentThread fellowsCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " fellows discussion",
                    authorId, true);

            proposal.setFellowDiscussionId(fellowsCommentThread.getThreadId());

            // create group
            Group_ group = createGroupAndSetUpPermissions(authorId, proposalId, contest);
            proposal.setGroupId(group.getGroupId());

            proposalDao.update(proposal);

            UsersGroupsClientUtil.createUsersGroups(authorId, proposal.getGroupId());
            MembersClient.createUserGroupRole(authorId,proposal.getGroupId());

            if (contestPhaseId > 0) {
                // associate proposal with phase
                Proposal2Phase p2p = new Proposal2Phase();
                p2p.setProposalId(proposalId);
                p2p.setContestPhaseId(contestPhaseId);
                p2p.setVersionFrom(proposal.getCurrentVersion());
                p2p.setVersionTo(-1);
                proposal2PhaseDao.create(p2p);
                if (publishActivity) {
                    //TODO: Migrate the call from proposal portlet to here
                }
            }

            // Automatically subscribe author to own proposal
            subscribeMemberToProposal(proposalId, authorId, true);

            return proposal;
        } catch (ContestNotFoundException ignored) {

        }
        return null;
    }

    public void subscribeMemberToProposal(long proposalId, long userId, boolean automatic) {
        ActivitiesClientUtil.addSubscription(userId, ActivityEntryType.PROPOSAL, proposalId, null);
    }

    private Group_ createGroupAndSetUpPermissions(long authorId, long proposalId, Contest contest) {

        // create new group
        final ContestType contestType = ContestClientUtil.getContestType(contest.getContestTypeId());

        String groupName = contestType.getProposalName() + "_" + proposalId + "_" + new Date().getTime();

        final ContestType contestTypeForLiferay = ContestClientUtil.getContestType(contestType.getId_());
        final String groupDescription = "Group working on " + contestTypeForLiferay.getProposalName();

        Group_ group = new Group_();

        group.setCompanyId(10112l);
        group.setCreatorUserId(authorId);
        group.setClassNameId(10009l);
        group.setParentGroupId(0l);
        group.setLiveGroupId(0l);
        group.setName(groupName);
        group.setDescription(groupDescription);
        group.setType_(2);
        group.setFriendlyURL("/" + groupName);
        group.setActive_(true);
        group.setSite(true);
        group.setUuid_(UUID.randomUUID().toString());
        group.setManualMembership(true);
        group.setMembershipRestriction(0);
        group.setRemoteStagingGroupCount(0);
        group = groupDao.create(group);
        group.setTreePath("/" + group.getGroupId() + "/");
        group.setClassPK(group.getGroupId());
        groupDao.update(group);
        return group;
    }

    private CommentThread createCommentThreadForProposal(String title, Long authorId, boolean isQuiet) {
        CommentThread commentThread = new CommentThread();
        commentThread.setAuthorId(authorId);
        commentThread.setCategoryId(null);
        commentThread.setTitle(title);
        commentThread.setIsQuiet(isQuiet);
        commentThread = ThreadClientUtil.createThread(commentThread);
        return commentThread;
    }

    public ProposalReference getReferenceByProposalIdAndSubProposalId(long proposalId, long subProposalId) {
        ProposalReference ref = null;
        try {
            ref = proposalReferenceDao.get(proposalId, subProposalId);
        } catch (NotFoundException ignored) {
        }
        return ref;
    }

    public ProposalAttribute getProposalAttribute(long sectionAttributeId) {

        ProposalAttribute attribute = null;
        try {
            attribute = proposalAttributeDao.get(sectionAttributeId);
        } catch (NotFoundException ignored) {
        }
        return attribute;
    }

    public List<Proposal> getContestIntegrationRelevantSubproposals(long proposalId) {
        final boolean onlyWithContestIntegrationRelevance = true;
        final boolean includeProposalsInSameContest = false;
        return getSubproposals(proposalId, includeProposalsInSameContest, onlyWithContestIntegrationRelevance);
    }

    public List<Proposal> getSubproposals(long proposalId, boolean includeProposalsInSameContest) {
        final boolean onlyWithContestIntegrationRelevance = false;
        return getSubproposals(proposalId, includeProposalsInSameContest, onlyWithContestIntegrationRelevance);
    }

    public List<Proposal> getSubproposals(long proposalId, boolean includeProposalsInSameContest, boolean onlyWithContestIntegrationRelevance) {
        List<ProposalReference> proposalReferences = proposalReferenceDao.findByGiven(proposalId, null);

        List<Proposal> proposals = new ArrayList<>();
        for (ProposalReference proposalReference : proposalReferences) {
            try {
                if (onlyWithContestIntegrationRelevance) {
                    ProposalAttribute attribute = proposalAttributeDao.get(proposalReference.getSectionAttributeId());
                    PlanSectionDefinition psd = PlanTemplateClientUtil.getPlanSectionDefinition(attribute.getAdditionalId());
                    if (!psd.getContestIntegrationRelevance()) {
                        continue;
                    }
                }
                final long subProposalId = proposalReference.getSubProposalId();
                Proposal p = proposalDao.get(subProposalId);
                if (p != null) {
                    if (!includeProposalsInSameContest) {
                        if (getLatestProposalContestId(proposalId).equals(getLatestProposalContestId(subProposalId))) {
                            continue;
                        }
                    }
                    if (p.getProposalId() != proposalId) {
                        proposals.add(p);
                    }
                }
            } catch (NotFoundException ignored) {

            }
        }
        return proposals;
    }

    public Long getLatestContestPhaseIdInProposal(Long proposalId) {
        List<Proposal2Phase> allP2p = proposal2PhaseDao.findByGiven(proposalId, null, null);
        long newestVersionContestPhaseId = 0;
        int newestVersion = 0;
        for (Proposal2Phase p2p : allP2p) {
            long contestPhaseId = p2p.getContestPhaseId();
            if (p2p.getVersionTo() == -1) {
                return contestPhaseId;
            } else if (p2p.getVersionTo() > newestVersion) {
                newestVersion = p2p.getVersionTo();
                newestVersionContestPhaseId = contestPhaseId;
            }
        }

        if (newestVersion != 0 && newestVersionContestPhaseId != 0) {
            return newestVersionContestPhaseId;
        }
        return null;
    }

    public Long getLatestProposalContestId(Long proposalId) {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
        return contestPhase.getContestPhasePK();
    }


    public Contest getLatestProposalContest(Long proposalId) {
        Contest contest = null;
        try {
            //TODO: this looks very shady
            contest = ProposalClientUtil.getLatestContestInProposal(proposalId);
        } catch (ContestNotFoundException ignored) {
        }
        return contest;
    }

    public List<Member> getProposalMembers(Long proposalId) throws ProposalNotFoundException {

        try {
            Proposal proposal = proposalDao.get(proposalId);
            ArrayList<Member> members = new ArrayList<>();
            for (UsersGroups user : UsersGroupsClientUtil.getUserGroupsByUserIdGroupId(null, proposal.getGroupId())) {
                try {
                    members.add(MembersClient.getMember(user.getUserId()));
                } catch (MemberNotFoundException ignored) {

                }
            }
            return members;
        } catch (NotFoundException ignored) {
            throw new ProposalNotFoundException("Proposal with id : " + proposalId + " not found");
        }
    }

    public void removeProposalTeamMember(Long proposalId, Long userId) throws ProposalNotFoundException {
        try {
            Proposal proposal = proposalDao.get(proposalId);
            UsersGroupsClientUtil.deleteUsersGroups(userId, proposal.getGroupId());
        } catch (NotFoundException ignored) {
            throw new ProposalNotFoundException("Proposal with id : " + proposalId + " not found.");
        }
    }

    public List<Proposal> getMemberProposals(Long userId) {
        List<UsersGroups> ug = UsersGroupsClientUtil.getUserGroupsByUserIdGroupId(userId, null);
        List<Proposal> proposals = new ArrayList<>();
        for (UsersGroups ugroup : ug) {
            try {
                proposals.add(proposalDao.getByGroupId(ugroup.getGroupId()));
            } catch (NotFoundException ignored) {

            }
        }
        return proposals;
    }

    public Boolean isUserAMember(long proposalId, long userId) {

        try {
            Proposal proposal = proposalDao.get(proposalId);
            return UsersGroupsClientUtil.isUserInGroups(userId, proposal.getGroupId());
        } catch (NotFoundException ignored) {
            return false;
        }
    }

    public List<Proposal> getProposalsByCurrentContests(List<Long> contestTierIds, List<Long> contestTypeIds, String filterText) {
        HashSet<Proposal> proposals = new HashSet<>();
        PaginationHelper paginationHelper = new PaginationHelper(null, null, null);
        if(contestTypeIds != null && !contestTypeIds.isEmpty() && contestTierIds != null && !contestTierIds.isEmpty()) {
            for (Long contestTierId : contestTierIds) {
                List<Contest> contests = ContestClientUtil.getContestsMatchingTier(contestTierId);
                int count = 0;
                int countProposalsInContest = 0;
                for (Contest contest : contests) {
                    System.out.println("Search Proposals in Contest No. " + count + " with name: " + contest.getContestShortName());
                    count++;
                    countProposalsInContest = proposals.size();
                    if (contestTypeIds.contains(contest.getContestTypeId())) {

                        ContestPhase contestPhase =
                                ContestClientUtil.getActivePhase(contest.getContestPK());
                        System.out.println("Active Phase: " +contestPhase.getContestStatusStr());
                        proposals.addAll(proposalDao
                                .findByGiven(paginationHelper, filterText, null, null,
                                        contestPhase.getContestPhasePK(), null));
                        System.out.println("Added " + (proposals.size() - countProposalsInContest) + " Proposals");
                    }
                }
            }
        }
        return new ArrayList<>(proposals);
    }
}

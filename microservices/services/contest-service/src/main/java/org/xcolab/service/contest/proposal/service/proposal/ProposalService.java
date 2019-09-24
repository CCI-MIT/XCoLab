package org.xcolab.service.contest.proposal.service.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.IProposalReference;
import org.xcolab.client.contest.pojo.tables.pojos.Proposal2Phase;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalTeamMember;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.contest.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.contest.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.contest.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.contest.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.contest.proposal.domain.proposalteammember.ProposalTeamMemberDao;
import org.xcolab.service.contest.proposal.domain.proposalversion.ProposalVersionDao;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProposalService {

    private final ProposalDao proposalDao;
    private final ProposalReferenceDao proposalReferenceDao;
    private final ProposalAttributeDao proposalAttributeDao;
    private final Proposal2PhaseDao proposal2PhaseDao;
    private final ProposalVersionDao proposalVersionDao;
    private final ProposalTeamMemberDao proposalTeamMemberDao;
    private final IThreadClient threadClient;
    private final IUserClient userClient;

    @Autowired
    public ProposalService(ProposalDao proposalDao, ProposalReferenceDao proposalReferenceDao,
            ProposalAttributeDao proposalAttributeDao, Proposal2PhaseDao proposal2PhaseDao,
                           ProposalVersionDao proposalVersionDao, ProposalTeamMemberDao proposalTeamMemberDao,  IThreadClient threadClient,
                           IUserClient userClient) {
        this.proposalDao = proposalDao;
        this.proposalReferenceDao = proposalReferenceDao;
        this.proposalAttributeDao = proposalAttributeDao;
        this.proposal2PhaseDao = proposal2PhaseDao;
        this.proposalVersionDao = proposalVersionDao;
        this.proposalTeamMemberDao = proposalTeamMemberDao;
        this.threadClient = threadClient;
        this.userClient = userClient;
    }

    public ProposalWrapper create(long authorUserId, long contestPhaseId, boolean publishActivity) {
        try {
            ProposalWrapper proposal = new ProposalWrapper();
            proposal.setVisible(true);
            proposal.setAuthorUserId(authorUserId);

            ContestPhaseWrapper contestPhase = StaticContestContext.getContestClient()
                    .getContestPhase(contestPhaseId);
            final ContestWrapper contest = StaticContestContext.getContestClient()
                    .getContest(contestPhase.getContestId());
            ContestType contestType = StaticAdminContext.getContestTypeClient()
                    .getContestType(contest.getContestTypeId());

            proposal = proposalDao.create(proposal);
            Long proposalId = proposal.getId();
            // create discussions
            final String proposalEntityName = contestType.getProposalName() + " ";


            final IThread mainCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " main discussion",
                    authorUserId, false);

            proposal.setDiscussionId(mainCommentThread.getId());


            final IThread resultsCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " results discussion",
                    authorUserId, true);

            proposal.setResultsDiscussionId(resultsCommentThread.getId());

            proposalDao.update(proposal);

            proposalTeamMemberDao.addUserToTeam(proposalId, authorUserId);

            if (contestPhaseId > 0) {
                // associate proposal with phase
                IProposal2Phase p2p = new Proposal2Phase();
                p2p.setProposalId(proposalId);
                p2p.setContestPhaseId(contestPhaseId);
                p2p.setVersionFrom(proposalVersionDao.findMaxVersion(proposalId));
                p2p.setVersionTo(-1);
                proposal2PhaseDao.create(p2p);
                if (publishActivity) {
                    //TODO COLAB-2600: Migrate the call from proposal portlet to here
                }
            }

            // Automatically subscribe author to own proposal
            subscribeMemberToProposal(proposalId, authorUserId, true);

            return proposal;
        } catch (ContestNotFoundException ignored) {

        }
        return null;
    }

    public void subscribeMemberToProposal(long proposalId, long userId, boolean automatic) {
        StaticActivityContext.getActivityClient()
                .addSubscription(userId, ActivityCategory.PROPOSAL, proposalId);
    }

    private IThread createCommentThreadForProposal(String title, Long authorUserId, boolean isQuiet) {
        IThread commentThread = new org.xcolab.client.comment.pojo.tables.pojos.Thread();
        commentThread.setAuthorUserId(authorUserId);
        commentThread.setCategoryId(null);
        commentThread.setTitle(title);
        commentThread.setIsQuiet(isQuiet);
        commentThread = StaticCommentContext.getThreadClient().createThread(commentThread);
        return commentThread;
    }

    public IProposalReference getReferenceByProposalIdAndSubProposalId(long proposalId, long subProposalId) {
        IProposalReference ref = null;
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

    public List<ProposalWrapper> getContestIntegrationRelevantSubproposals(long proposalId) {
        final boolean onlyWithContestIntegrationRelevance = true;
        final boolean includeProposalsInSameContest = false;
        return getSubproposals(proposalId, includeProposalsInSameContest, onlyWithContestIntegrationRelevance);
    }

    public List<ProposalWrapper> getSubproposals(long proposalId, boolean includeProposalsInSameContest) {
        final boolean onlyWithContestIntegrationRelevance = false;
        return getSubproposals(proposalId, includeProposalsInSameContest, onlyWithContestIntegrationRelevance);
    }

    public List<ProposalWrapper> getSubproposals(long proposalId, boolean includeProposalsInSameContest, boolean onlyWithContestIntegrationRelevance) {
        List<IProposalReference> proposalReferences = proposalReferenceDao.findByGiven(proposalId, null);

        List<ProposalWrapper> proposals = new ArrayList<>();
        for (IProposalReference proposalReference : proposalReferences) {
            try {
                if (onlyWithContestIntegrationRelevance) {
                    ProposalAttribute attribute = proposalAttributeDao.get(proposalReference.getSectionAttributeId());
                    ProposalTemplateSectionDefinitionWrapper psd = StaticContestContext.getProposalTemplateClient().getProposalTemplateSectionDefinition(attribute.getAdditionalId());
                    if (!psd.isContestIntegrationRelevance()) {
                        continue;
                    }
                }
                final long subProposalId = proposalReference.getSubProposalId();
                ProposalWrapper p = proposalDao.get(subProposalId);
                if (p != null) {
                    if (!includeProposalsInSameContest) {
                        if (getLatestContestIdForProposal(proposalId).equals(
                                getLatestContestIdForProposal(subProposalId))) {
                            continue;
                        }
                    }
                    if (p.getId() != proposalId) {
                        proposals.add(p);
                    }
                }
            } catch (NotFoundException ignored) {

            }
        }
        return proposals;
    }

    public Long getLatestContestPhaseIdInProposal(Long proposalId) throws NotFoundException {
        List<IProposal2Phase> allP2p = proposal2PhaseDao.findByGiven(proposalId, null, null);
        long newestVersionContestPhaseId = 0;
        int newestVersion = 0;
        for (IProposal2Phase p2p : allP2p) {
            long contestPhaseId = p2p.getContestPhaseId();
            if (p2p.getVersionTo() == -1) {
                return contestPhaseId;
            } else if (p2p.getVersionTo() >= newestVersion) {
                newestVersion = p2p.getVersionTo();
                newestVersionContestPhaseId = contestPhaseId;
            }
        }

        if (newestVersion != 0 && newestVersionContestPhaseId != 0) {
            return newestVersionContestPhaseId;
        }
        throw new NotFoundException("ProposalWrapper " + proposalId  + " is not associated with any phases");
    }

    public Long getLatestContestIdForProposal(Long proposalId) {
        try {
            Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
            ContestPhaseWrapper contestPhase = StaticContestContext.getContestClient()
                    .getContestPhase(contestPhaseId);
            return contestPhase.getContestId();
        } catch (NotFoundException e) {
            return null;
        }
    }


    public ContestWrapper getLatestProposalContest(Long proposalId) {
        ContestWrapper contest = null;
        try {
            //TODO COLAB-2600: this looks very shady - we're calling the client from the service!
            contest = StaticProposalContext.getProposalClient()
                    .getLatestContestInProposal(proposalId);
        } catch (ContestNotFoundException ignored) {
        }
        return contest;
    }

    public List<UserWrapper> getProposalMembers(Long proposalId) throws ProposalNotFoundException {
        final List<UserWrapper> members = proposalTeamMemberDao.findByProposalId(proposalId).stream()
                .map(ProposalTeamMember::getUserId)
                .map(userClient::getMemberUnchecked)
                .collect(Collectors.toList());
        if (members.isEmpty()) {
            throw new ProposalNotFoundException("ProposalWrapper with id : " + proposalId + " not found");
        }
        return members;
    }

    public void removeProposalTeamMember(Long proposalId, Long userId) throws ProposalNotFoundException {
        final boolean success = proposalTeamMemberDao.delete(proposalId, userId);
        if (!success) {
            throw new ProposalNotFoundException("ProposalWrapper with id : " + proposalId + " not found.");
        }
    }

    public void promoteMemberToProposalOwner(Long proposalId, Long userId) throws ProposalNotFoundException {
        try {
            ProposalWrapper proposal = proposalDao.get(proposalId);
            proposal.setAuthorUserId(userId);
            proposalDao.update(proposal);
        } catch (NotFoundException ignored) {
            throw new ProposalNotFoundException("ProposalWrapper with id : " + proposalId + " not found.");
        }
    }

    public List<ProposalWrapper> getMemberProposals(Long userId) {
        final List<ProposalTeamMember> proposalTeamMembers = proposalTeamMemberDao.findByUserId(userId);
        return proposalTeamMembers.stream()
                .map(ProposalTeamMember::getProposalId)
                .map(proposalDao::getOpt)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public boolean isUserAMember(long proposalId, long userId) {
        return proposalTeamMemberDao.exists(proposalId, userId);
    }
}

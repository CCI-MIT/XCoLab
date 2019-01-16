package org.xcolab.service.proposal.service.proposal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ProposalTemplateClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.templates.ProposalTemplateSectionDefinition;
import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.model.tables.pojos.ProposalReference;
import org.xcolab.model.tables.pojos.ProposalTeamMember;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.proposal.domain.proposalattribute.ProposalAttributeDao;
import org.xcolab.service.proposal.domain.proposalreference.ProposalReferenceDao;
import org.xcolab.service.proposal.domain.proposalteammember.ProposalTeamMemberDao;
import org.xcolab.service.proposal.domain.proposalversion.ProposalVersionDao;
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


    @Autowired
    public ProposalService(ProposalDao proposalDao, ProposalReferenceDao proposalReferenceDao,
            ProposalAttributeDao proposalAttributeDao, Proposal2PhaseDao proposal2PhaseDao,
            ProposalVersionDao proposalVersionDao, ProposalTeamMemberDao proposalTeamMemberDao) {
        this.proposalDao = proposalDao;
        this.proposalReferenceDao = proposalReferenceDao;
        this.proposalAttributeDao = proposalAttributeDao;
        this.proposal2PhaseDao = proposal2PhaseDao;
        this.proposalVersionDao = proposalVersionDao;
        this.proposalTeamMemberDao = proposalTeamMemberDao;
    }

    public Proposal create(long authorUserId, long contestPhaseId, boolean publishActivity) {
        try {
            Proposal proposal = new Proposal();
            proposal.setVisible(true);
            proposal.setAuthorUserId(authorUserId);

            ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
            final Contest contest = ContestClientUtil.getContest(contestPhase.getContestId());
            ContestType contestType = ContestTypeClient.getContestType(contest.getContestTypeId());

            proposal = proposalDao.create(proposal);
            Long proposalId = proposal.getId();
            // create discussions
            final String proposalEntityName = contestType.getProposalName() + " ";


            final CommentThread mainCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " main discussion",
                    authorUserId, false);

            proposal.setDiscussionId(mainCommentThread.getId());


            final CommentThread resultsCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " results discussion",
                    authorUserId, true);

            proposal.setResultsDiscussionId(resultsCommentThread.getId());

            proposalDao.update(proposal);

            proposalTeamMemberDao.addUserToTeam(proposalId, authorUserId);

            if (contestPhaseId > 0) {
                // associate proposal with phase
                Proposal2Phase p2p = new Proposal2Phase();
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
        ActivitiesClientUtil.addSubscription(userId, ActivityCategory.PROPOSAL, proposalId, null);
    }

    private CommentThread createCommentThreadForProposal(String title, Long authorUserId, boolean isQuiet) {
        CommentThread commentThread = new CommentThread();
        commentThread.setAuthorUserId(authorUserId);
        commentThread.setCategoryId(null);
        commentThread.setTitle(title);
        commentThread.setIsQuiet(isQuiet);
        commentThread = ThreadClient.instance().createThread(commentThread);
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
                    ProposalTemplateSectionDefinition psd = ProposalTemplateClientUtil.getProposalTemplateSectionDefinition(attribute.getAdditionalId());
                    if (!psd.getContestIntegrationRelevance()) {
                        continue;
                    }
                }
                final long subProposalId = proposalReference.getSubProposalId();
                Proposal p = proposalDao.get(subProposalId);
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
        List<Proposal2Phase> allP2p = proposal2PhaseDao.findByGiven(proposalId, null, null);
        long newestVersionContestPhaseId = 0;
        int newestVersion = 0;
        for (Proposal2Phase p2p : allP2p) {
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
        throw new NotFoundException("Proposal " + proposalId  + " is not associated with any phases");
    }

    public Long getLatestContestIdForProposal(Long proposalId) {
        try {
            Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
            ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
            return contestPhase.getContestId();
        } catch (NotFoundException e) {
            return null;
        }
    }


    public Contest getLatestProposalContest(Long proposalId) {
        Contest contest = null;
        try {
            //TODO COLAB-2600: this looks very shady - we're calling the client from the service!
            contest = ProposalClientUtil.getLatestContestInProposal(proposalId);
        } catch (ContestNotFoundException ignored) {
        }
        return contest;
    }

    public List<Member> getProposalMembers(Long proposalId) throws ProposalNotFoundException {
        final List<Member> members = proposalTeamMemberDao.findByProposalId(proposalId).stream()
                .map(ProposalTeamMember::getUserId)
                .map(MembersClient::getMemberUnchecked)
                .collect(Collectors.toList());
        if (members.isEmpty()) {
            throw new ProposalNotFoundException("Proposal with id : " + proposalId + " not found");
        }
        return members;
    }

    public void removeProposalTeamMember(Long proposalId, Long userId) throws ProposalNotFoundException {
        final boolean success = proposalTeamMemberDao.delete(proposalId, userId);
        if (!success) {
            throw new ProposalNotFoundException("Proposal with id : " + proposalId + " not found.");
        }
    }

    public void promoteMemberToProposalOwner(Long proposalId, Long userId) throws ProposalNotFoundException {
        try {
            Proposal proposal = proposalDao.get(proposalId);
            proposal.setAuthorUserId(userId);
            proposalDao.update(proposal);
        } catch (NotFoundException ignored) {
            throw new ProposalNotFoundException("Proposal with id : " + proposalId + " not found.");
        }
    }

    public List<Proposal> getMemberProposals(Long userId) {
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

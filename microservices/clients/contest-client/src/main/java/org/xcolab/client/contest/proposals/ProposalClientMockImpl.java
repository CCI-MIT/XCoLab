package org.xcolab.client.contest.proposals;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IProposalReference;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ProposalClientMockImpl implements IProposalClient {

    @Override
    public ProposalWrapper createProposal(ProposalWrapper proposal) {
        return null;
    }

    @Override
    public List<ProposalWrapper> listProposals(Integer startRecord, Integer limitRecord,
            String filterText, List<Long> contestIds, List<Long> contestTierIds,
            List<Long> contestTypeIds, Boolean contestActive, Boolean contestPrivate,
            Boolean visible, Long contestPhaseId, List<Integer> ribbons, Long threadId,
            String sort) {
        return Collections.emptyList();
    }

    @Override
    public List<Long> listProposalIds(Integer startRecord, Integer limitRecord, Long contestId,
            Boolean visible, Long contestPhaseId, Integer ribbon) {
        return Collections.emptyList();
    }

    @Override
    public List<Long> listThreadIds(List<Long> proposalIds, Long contestId, Long contestPhaseId,
            Integer ribbon) {
        return Collections.emptyList();
    }

    @Override
    public List<UserWrapper> getProposalMembers(Long proposalId) {
        return Collections.emptyList();
    }

    @Override
    public boolean removeMemberFromProposalTeam(Long proposalId, Long userId) {
        return false;
    }

    @Override
    public boolean promoteMemberToProposalOwner(Long proposalId, Long userId) {
        return false;
    }

    @Override
    public Boolean isUserInProposalTeam(Long proposalId, Long memberUserId) {
        return null;
    }

    @Override
    public ProposalWrapper createProposal(Long authorUserId, Long contestPhaseId,
            Boolean publishActivity) {
        return null;
    }

    @Override
    public List<ProposalWrapper> getContestIntegrationRelevantSubproposals(Long proposalId) {
        return Collections.emptyList();
    }

    @Override
    public List<ProposalWrapper> getMemberProposals(Long userId) {
        return Collections.emptyList();
    }

    @Override
    public ProposalWrapper getProposal(Long proposalId, Boolean includeDeleted)
            throws ProposalNotFoundException {
        return null;
    }

    @Override
    public List<IProposalReference> getProposalReference(Long proposalId, Long subProposalId) {
        return Collections.emptyList();
    }

    @Override
    public List<ProposalWrapper> getSubproposals(Long proposalId,
            Boolean includeProposalsInSameContest) {
        return Collections.emptyList();
    }

    @Override
    public List<ProposalWrapper> getLinkingProposalsForProposalId(Long proposalId) {
        return Collections.emptyList();
    }

    @Override
    public Integer getProposalMaterializedPoints(Long proposalId) {
        return null;
    }

    @Override
    public int getNumberOfProposalsAlreadyJudgedForJudge(Long contestPhaseId, Long userId) {
        return 0;
    }

    @Override
    public int getNumberOfProposalsForJudge(Long contestPhaseId, Long userId) {
        return 0;
    }

    @Override
    public boolean updateProposal(ProposalWrapper proposal) {
        return false;
    }

    @Override
    public boolean deleteProposal(Long proposalId) {
        return false;
    }

    @Override
    public ProposalVersionWrapper getProposalVersionByProposalIdVersion(Long proposalId,
            Integer version) {
        return null;
    }

    @Override
    public List<ProposalVersionWrapper> getProposalVersionsGroupedVersionsByContest(Long proposalId,
            Long contestId, Integer start, Integer end) {
        return Collections.emptyList();
    }

    @Override
    public Integer getMaxVersion(Long proposalId) {
        return null;
    }

    @Override
    public int countProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId) {
        return 0;
    }

    @Override
    public int countProposalVersions(Long proposalId) {
        return 0;
    }

    @Override
    public List<ProposalVersionWrapper> getAllProposalVersions(Long proposalId) {
        return Collections.emptyList();
    }

    @Override
    public Long getLatestContestPhaseIdInProposal(Long proposalId) {
        return null;
    }

    @Override
    public void populateTableWithProposal(Long proposalId) {
        // empty
    }
}

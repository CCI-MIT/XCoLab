package org.xcolab.client.proposals;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.tiers.ProposalReference;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ProposalClientUtil {

    private static final RestService proposalService = new RestService("proposals-service");
    private static final ProposalClient client = ProposalClient.fromService(proposalService);

    public static ProposalClient getClient() {
        return client;
    }

    public static Proposal createProposal(
            Proposal proposal) {
        return client.createProposal(proposal);
    }

    public static List<Proposal> listProposals(long contestId) {
        return client.listProposals(contestId);
    }

    public static List<Proposal> listProposals(int start, int limit,
            Long contestId, Boolean visible, Long contestPhaseId, Integer ribbon) {
        return client.listProposals(start, limit, contestId, visible, contestPhaseId, ribbon);
    }

    public static List<Long> listProposalIds(int start, int limit,
            Long contestId, Boolean visible, Long contestPhaseId, Integer ribbon) {
        return client.listProposalIds(start, limit, contestId, visible, contestPhaseId, ribbon);
    }

    public static List<Long> listProposalThreadIds(int start, int limit,
            Long contestId, Boolean visible, Long contestPhaseId, Integer ribbon) {
        return client.listThreadIds(start, limit, contestId, visible, contestPhaseId, ribbon);
    }

    public static List<Proposal> getProposalsInContestPhase(
            Long contestPhaseId) {
        return client.getProposalsInContestPhase(contestPhaseId);
    }

    public static List<Proposal> getAllProposals() {
        return client.getAllProposals();
    }

    public static List<Proposal> getProposalsInContest(
            Long contestPK) {
        return client.getProposalsInContest(contestPK);
    }

    public static List<Member> getProposalMembers(
            Long proposalId) {
        return client.getProposalMembers(proposalId);
    }

    public static void removeUserFromProposalTeam(Long proposalId, Long memberUserId) {
        client.removeUserFromProposalTeam(proposalId, memberUserId);
    }

    public static Boolean isUserInProposalTeam(Long proposalId, Long memberUserId) {
        return client.isUserInProposalTeam(proposalId, memberUserId);
    }

    public static List<Proposal> getActiveProposalsInContestPhase(
            Long contestPhaseId) {
        return client.getActiveProposalsInContestPhase(contestPhaseId);
    }

    public static Proposal createProposal(long authorId, long contestPhaseId,
            boolean publishActivity) {
        return client.createProposal(authorId, contestPhaseId, publishActivity);
    }

    public static List<Proposal> getContestIntegrationRelevantSubproposals(
            Long proposalId) {
        return client.getContestIntegrationRelevantSubproposals(proposalId);
    }

    public static List<Proposal> getLinkingProposalsForUser(
            long userId) {
        return client.getLinkingProposalsForUser(userId);
    }

    public static List<Proposal> getMemberProposals(
            Long userId) {
        return client.getMemberProposals(userId);
    }

    public static List<Proposal> getLinkingProposals(
            long proposalId) {
        return client.getLinkingProposals(proposalId);
    }

    public static Proposal getProposal(long proposalId) throws ProposalNotFoundException {
        return client.getProposal(proposalId);
    }

    public static Proposal getProposal(long proposalId,
            boolean includeDeleted) throws ProposalNotFoundException {
        return client.getProposal(proposalId, includeDeleted);
    }

    public static List<ProposalReference> getProposalReference(
            Long proposalId, Long subProposalId) {
        return client.getProposalReference(proposalId, subProposalId);
    }

    public static List<Proposal> getSubproposals(
            Long proposalId, Boolean includeProposalsInSameContest) {
        return client.getSubproposals(proposalId, includeProposalsInSameContest);
    }

    public static Integer getProposalMaterializedPoints(Long proposalId) {
        return client.getProposalMaterializedPoints(proposalId);
    }

    public static Integer getNumberOfProposalsForJudge(Long userId, Long contestPhaseId) {
        return client.getNumberOfProposalsForJudge(userId, contestPhaseId);
    }

    public static ProposalReference getProposalReferenceByProposalIdSubProposalId(
            Long proposalId, Long subProposalId) {
        return client.getProposalReferenceByProposalIdSubProposalId(proposalId, subProposalId);
    }

    public static boolean updateProposal(Proposal proposal) {
        return client.updateProposal(proposal);
    }

    public static boolean deleteProposal(long proposalId) {
        return client.deleteProposal(proposalId);
    }

    public static ProposalVersion getProposalVersionByProposalIdVersion(
            Long proposalId, Integer version) {
        return client.getProposalVersionByProposalIdVersion(proposalId, version);
    }

    public static Integer countProposalVersions(Long proposalId) {
        return client.countProposalVersions(proposalId);
    }

    public static ProposalVersion getProposalVersionByProposal(
            Long proposalId) {
        return client.getProposalVersionByProposal(proposalId);
    }

    public static List<ProposalVersion> getAllProposalVersions(
            Long proposalId) {
        return client.getAllProposalVersions(proposalId);
    }

    public static ContestType getContestTypeFromProposalId(
            Long proposalId) {
        return client.getContestTypeFromProposalId(proposalId);
    }

    public static Contest getCurrentContestForProposal(
            Long proposalId) throws ContestNotFoundException {
        return client.getCurrentContestForProposal(proposalId);
    }

    public static Long getLatestContestPhaseIdInProposal(Long proposalId) {
        return client.getLatestContestPhaseIdInProposal(proposalId);
    }

    public static Contest getLatestContestInProposal(Long proposalId)
            throws ContestNotFoundException {
        return client.getLatestContestInProposal(proposalId);
    }

    public static ContestPhase getLatestContestPhaseInContest(
            Long proposalId) {
        return client.getLatestContestPhaseInContest(proposalId);
    }

    public static void populateTableWithProposal(long proposalId) {
        client.populateTableWithProposal(proposalId);
    }

    public static boolean isMemberSubscribedToProposal(long proposalId, long userId) {
        return client.isMemberSubscribedToProposal(proposalId, userId);
    }

    public static void subscribeMemberToProposal(long proposalId, long userId) {
        client.subscribeMemberToProposal(proposalId, userId);
    }

    public static void unsubscribeMemberFromProposal(long proposalId, long userId) {
        client.unsubscribeMemberFromProposal(proposalId, userId);
    }
}

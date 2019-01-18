package org.xcolab.client.contest.proposals;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;
import org.xcolab.client.contest.pojo.IProposalReference;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ProposalClientUtil {

    private static final ProposalClient client = new ProposalClient();

    public static ProposalClient getClient() {
        return client;
    }

    public static ProposalWrapper createProposal(
            ProposalWrapper proposal) {
        return client.createProposal(proposal);
    }

    public static List<ProposalWrapper> listProposals(long contestId) {
        return client.listProposals(contestId);
    }

    public static List<ProposalWrapper> listProposals(int start, int limit,
            Long contestId, Boolean visible, Long contestPhaseId, Integer ribbon) {
        return client.listProposals(start, limit, contestId, visible, contestPhaseId, ribbon);
    }

    public static List<Long> listProposalIds(int start, int limit,
            Long contestId, Boolean visible, Long contestPhaseId, Integer ribbon) {
        return client.listProposalIds(start, limit, contestId, visible, contestPhaseId, ribbon);
    }

    public static List<ProposalWrapper> getProposalsInContestPhase(
            Long contestPhaseId) {
        return client.getProposalsInContestPhase(contestPhaseId);
    }

    public static List<ProposalWrapper> getAllProposals() {
        return client.getAllProposals();
    }

    public static List<ProposalWrapper> listProposalsInActiveContests() {
        return client.listProposalsInActiveContests();
    }

    public static List<ProposalWrapper> listProposalsInCompletedContests(List<Integer> ribbons) {
        return client.listProposalsInCompletedContests(ribbons);
    }

    public static List<ProposalWrapper> getProposalsInContest(
            Long contestId) {
        return client.getProposalsInContest(contestId);
    }

    public static List<Member> getProposalMembers(Long proposalId) {
        return client.getProposalMembers(proposalId);
    }

    public static void removeMemberFromProposalTeam(Long proposalId, Long userId) {
        client.removeMemberFromProposalTeam(proposalId, userId);
    }

    public static Boolean isUserInProposalTeam(Long proposalId, Long memberUserId) {
        return client.isUserInProposalTeam(proposalId, memberUserId);
    }

    public static List<ProposalWrapper> getActiveProposalsInContestPhase(
            Long contestPhaseId) {
        return client.getActiveProposalsInContestPhase(contestPhaseId);
    }

    public static ProposalWrapper createProposal(long authorUserId, long contestPhaseId,
            boolean publishActivity) {
        return client.createProposal(authorUserId, contestPhaseId, publishActivity);
    }

    public static List<ProposalWrapper> getContestIntegrationRelevantSubproposals(
            Long proposalId) {
        return client.getContestIntegrationRelevantSubproposals(proposalId);
    }

    public static List<ProposalWrapper> getLinkingProposalsForUser(
            long userId) {
        return client.getLinkingProposalsForUser(userId);
    }

    public static List<ProposalWrapper> getMemberProposals(
            Long userId) {
        return client.getMemberProposals(userId);
    }

    public static List<ProposalWrapper> getLinkingProposals(
            long proposalId) {
        return client.getLinkingProposals(proposalId);
    }

    public static ProposalWrapper getProposalByThreadId(long threadId) throws ProposalNotFoundException {
        return client.getProposalByThreadId(threadId);
    }

    public static ProposalWrapper getProposal(long proposalId) throws ProposalNotFoundException {
        return client.getProposal(proposalId);
    }

    public static ProposalWrapper getProposal(long proposalId,
            boolean includeDeleted) throws ProposalNotFoundException {
        return client.getProposal(proposalId, includeDeleted);
    }

    public static List<IProposalReference> getProposalReference(
            Long proposalId, Long subProposalId) {
        return client.getProposalReference(proposalId, subProposalId);
    }

    public static List<ProposalWrapper> getSubproposals(
            Long proposalId, Boolean includeProposalsInSameContest) {
        return client.getSubproposals(proposalId, includeProposalsInSameContest);
    }

    public static Integer getProposalMaterializedPoints(Long proposalId) {
        return client.getProposalMaterializedPoints(proposalId);
    }

    public static Integer getNumberOfProposalsAlreadyJudgedForJudge(Long userId, Long contestPhaseId) {
        return client.getNumberOfProposalsAlreadyJudgedForJudge(userId, contestPhaseId);
    }
    public static Integer getNumberOfProposalsForJudge(Long userId, Long contestPhaseId) {
        return client.getNumberOfProposalsForJudge(userId, contestPhaseId);
    }

    public static IProposalReference getProposalReferenceByProposalIdSubProposalId(
            Long proposalId, Long subProposalId) {
        return client.getProposalReferenceByProposalIdSubProposalId(proposalId, subProposalId);
    }

    public static boolean updateProposal(ProposalWrapper proposal) {
        return client.updateProposal(proposal);
    }

    public static boolean deleteProposal(long proposalId) {
        return client.deleteProposal(proposalId);
    }

    public static ProposalVersionWrapper getProposalVersionByProposalIdVersion(
            Long proposalId, Integer version) {
        return client.getProposalVersionByProposalIdVersion(proposalId, version);
    }

    public static List<ProposalVersionWrapper> getProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId, int start , int end) {
        return client.getProposalVersionsGroupedVersionsByContest(proposalId,contestId, start, end);

    }
    public static Integer countProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId) {
        return client.countProposalVersionsGroupedVersionsByContest(proposalId,contestId);
    }

    public static Integer countProposalVersions(Long proposalId) {
        return client.countProposalVersions(proposalId);
    }

    public static ProposalVersionWrapper getProposalVersionByProposal(
            Long proposalId) {
        return client.getProposalVersionByProposal(proposalId);
    }

    public static List<ProposalVersionWrapper> getAllProposalVersions(
            Long proposalId) {
        return client.getAllProposalVersions(proposalId);
    }

    public static ContestType getContestTypeFromProposalId(
            Long proposalId) {
        return client.getContestTypeFromProposalId(proposalId);
    }

    public static ContestWrapper getCurrentContestForProposal(
            Long proposalId) throws ContestNotFoundException {
        return client.getCurrentContestForProposal(proposalId);
    }

    public static Long getLatestContestPhaseIdInProposal(Long proposalId) {
        return client.getLatestContestPhaseIdInProposal(proposalId);
    }

    public static ContestWrapper getLatestContestInProposal(Long proposalId)
            throws ContestNotFoundException {
        return client.getLatestContestInProposal(proposalId);
    }

    public static ContestPhaseWrapper getLatestContestPhaseInContest(
            Long proposalId) {
        return client.getLatestContestPhaseInProposal(proposalId);
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

    public static ProposalWrapper getProposalFromLinkUrl(String linkUrl) {
        List<Long> proposalIds = getProposalIdsFromLinksInText(linkUrl);
        if (!proposalIds.isEmpty()) {
            try {
                return ProposalClientUtil.getProposal(proposalIds.get(0));
            } catch (ProposalNotFoundException ignored) {
            }
        }

        return null;
    }
    public static  List<ProposalWrapper> getProposalsByCurrentContests(List<Long> contestTypeIds, List<Long> contestTierIds,
            String filterText) {
        return client.getProposalsInPublicContests(contestTypeIds, contestTierIds, filterText);
    }

    public static List<Long> getProposalIdsFromLinksInText(String text) {
        List<Long> proposalIds = new ArrayList<>();
        Pattern proposalLinkPattern = Pattern.compile(
                "(href=|https?://).*?/.+?/\\d{4}/[a-z0-9-]+?/(?:c|phase/\\d*)/.+?/(\\d*)");
        Matcher m = proposalLinkPattern.matcher(text);
        while (m.find()) {
            try {
                final long proposalId = Long.parseLong(m.group(2));
                proposalIds.add(proposalId);
            } catch (NumberFormatException ignored) {
            }
        }

        proposalIds.addAll(getProposalIdsFromLegacyLinksInText(text));
        return proposalIds;
    }

    private static List<Long> getProposalIdsFromLegacyLinksInText(String text) {
        List<Long> proposalIds = new ArrayList<>();
        Pattern proposalLinkPattern = Pattern.compile(
                "(href=|https?://).*?/-/plans/contestId/(\\d*)/(?:phaseId/\\d*/)?planId/(\\d*)");
        Matcher m = proposalLinkPattern.matcher(text);
        while (m.find()) {
            try {
                final long proposalId = Long.parseLong(m.group(3));
                proposalIds.add(proposalId);
            } catch (NumberFormatException ignored) {
            }
        }
        return proposalIds;
    }

    public static String getNonBlankStringOrDefault(String string, String defaultString) {
        if (StringUtils.isNotBlank(string)) {
            return string;
        }
        return defaultString;
    }

}

package org.xcolab.client.proposals;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalVersion;
import org.xcolab.client.proposals.pojo.group.Group_;
import org.xcolab.client.proposals.pojo.tiers.ProposalReference;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ProposalClientUtil {

    private static final RestService proposalService = new RestService(CoLabService.PROPOSAL,
            ServiceRequestUtils.getNamespace());
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

    public static List<ProposalVersion> getProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId, int start , int end) {
        return client.getProposalVersionsGroupedVersionsByContest(proposalId,contestId, start, end);

    }
    public static Integer countProposalVersionsGroupedVersionsByContest(Long proposalId, Long contestId) {
        return client.countProposalVersionsGroupedVersionsByContest(proposalId,contestId);
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

    public static Proposal getProposalFromLinkUrl(String linkUrl) {
        List<Long> proposalIds = getProposalIdsFromLinksInText(linkUrl);
        if (!proposalIds.isEmpty()) {
            try {
                return ProposalClientUtil.getProposal(proposalIds.get(0));
            } catch (ProposalNotFoundException ignored) {
            }
        }

        return null;
    }
    public static  List<Proposal> getProposalsByCurrentContests(List<Long> contestTypeIds, List<Long> contestTierIds,
            String filterText) {
            return client.getProposalsByCurrentContests(contestTypeIds, contestTierIds, filterText);
    }

    public static Group_ createGroup(Group_ group) {
        return client.createGroup(group);
    }
    public static boolean updateGroup(Group_ group) {
        return client.updateGroup(group);
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

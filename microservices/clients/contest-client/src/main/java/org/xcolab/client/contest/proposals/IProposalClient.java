package org.xcolab.client.contest.proposals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IProposalReference;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalVersionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FeignClient("xcolab-contest-service")
public interface IProposalClient {

    @PostMapping("/proposals")
    ProposalWrapper createProposal(@RequestBody ProposalWrapper proposal);

    default List<ProposalWrapper> listProposals(long contestId) {
        return listProposals(0, Integer.MAX_VALUE, contestId, null, null, null);
    }

    default List<ProposalWrapper> listProposals(int start, int limit, Long contestId,
            Boolean visible,
            Long contestPhaseId, Integer ribbon) {
        return listProposals(start, limit, null, contestId, null,
                null, visible, contestPhaseId, ribbon);
    }

    @GetMapping("/proposals")
    List<ProposalWrapper> listProposals(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "filterText", required = false) String filterText,
            @RequestParam(value = "contestIds", required = false) List<Long> contestIds,
            @RequestParam(value = "contestTierIds", required = false) List<Long> contestTierIds,
            @RequestParam(value = "contestTypeIds", required = false) List<Long> contestTypeIds,
            @RequestParam(value = "contestActive", required = false) Boolean contestActive,
            @RequestParam(value = "contestPrivate", required = false) Boolean contestPrivate,
            @RequestParam(value = "visible", required = false) Boolean visible,
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "ribbons", required = false) List<Integer> ribbons,
            @RequestParam(value = "threadId", required = false) Long threadId,
            @RequestParam(value = "sort", required = false) String sort);

    default List<ProposalWrapper> listProposalsInActiveContests() {
        return listProposals(0, Integer.MAX_VALUE, null, null, null, null, true, false, true, null,
                null, null, null);
    }

    default List<ProposalWrapper> listProposalsInCompletedContests(List<Integer> ribbons) {
        return listProposals(0, Integer.MAX_VALUE, null, null, null, null, false, false, true, null,
                ribbons, null, null);
    }

    default List<ProposalWrapper> listProposals(Integer start, Integer limit, String filterText,
            Long contestId,
            List<Long> contestTypeIds, List<Long> contestTierIds, Boolean visible,
            Long contestPhaseId,
            Integer ribbon) {
        return listProposals(start, limit, filterText, (contestId==null)?(null):(Collections.singletonList(contestId)),
                contestTierIds, contestTypeIds, null, null, visible, contestPhaseId,
                (ribbon==null)?(null):(Collections.singletonList(ribbon)), null, null);
    }

    default List<ProposalWrapper> getProposalsInPublicContests(List<Long> contestTypeIds,
            List<Long> contestTierIds, String filterText) {
        return listProposals(0, Integer.MAX_VALUE, filterText, null, contestTierIds, contestTypeIds,
                null, false, true, null, null, null, null);
    }

    @GetMapping("/proposalIds")
    List<Long> listProposalIds(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "contestId", required = false) Long contestId,
            @RequestParam(value = "visible", required = false) Boolean visible,
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "ribbon", required = false) Integer ribbon);

    @GetMapping("proposalThreadIds")
    List<Long> listThreadIds(
            @RequestParam(value = "proposalIds", required = false) List<Long> proposalIds,
            @RequestParam(value = "contestId", required = false) Long contestId,
            @RequestParam(value = "contestPhaseId", required = false) Long contestPhaseId,
            @RequestParam(value = "ribbon", required = false) Integer ribbon);

    default List<ProposalWrapper> getProposalsInContestPhase(Long contestPhaseId) {
        return listProposals(0, Integer.MAX_VALUE, null, true, contestPhaseId, null);
    }

    default List<ProposalWrapper> getAllProposals() {
        return listProposals(0, Integer.MAX_VALUE, null, true, null, null);
    }

    default List<ProposalWrapper> getProposalsInContest(Long contestId) {
        ContestPhaseWrapper cp = StaticContestContext.getContestClient().getActivePhase(contestId);
        return listProposals(0, Integer.MAX_VALUE, null, true, cp.getId(), null);
    }

    @GetMapping("/proposals/{proposalId}/allMembers")
    List<UserWrapper> getProposalMembers(@PathVariable("proposalId") Long proposalId);

    @DeleteMapping("/proposals/{proposalId}/removeMemberFromProposalTeam")
    boolean removeMemberFromProposalTeam(@PathVariable("proposalId") Long proposalId,
            @RequestParam("userId") Long userId);

    @PostMapping("/proposals/{proposalId}/promoteMemberToProposalOwner")
    boolean promoteMemberToProposalOwner(@PathVariable("proposalId") Long proposalId,
            @RequestParam("userId") Long userId);

    @GetMapping("/proposals/{proposalId}/isUserInProposalTeam")
    Boolean isUserInProposalTeam(@PathVariable("proposalId") Long proposalId,
            @RequestParam("memberUserId") Long memberUserId);

    default List<ProposalWrapper> getActiveProposalsInContestPhase(Long contestPhaseId) {
        return listProposals(0, Integer.MAX_VALUE, null, null, null, null, null, null, true,
                contestPhaseId, null, null, null);
    }

    @PostMapping("/proposals/createProposal")
    ProposalWrapper createProposal(@RequestParam("authorUserId") Long authorUserId,
            @RequestParam("contestPhaseId") Long contestPhaseId,
            @RequestParam("publishActivity") Boolean publishActivity);

    @GetMapping("/proposals/{proposalId}/contestIntegrationRelevantSubproposal")
    List<ProposalWrapper> getContestIntegrationRelevantSubproposals(
            @PathVariable("proposalId") Long proposalId);

    default List<ProposalWrapper> getLinkingProposalsForUser(Long userId) {
        final List<ProposalWrapper> userProposals = getMemberProposals(userId);
        List<ProposalWrapper> linkingProposals = new ArrayList<>();
        for (ProposalWrapper proposal : userProposals) {
            linkingProposals.addAll(getLinkingProposals(proposal.getId()));
        }
        return linkingProposals;
    }

    @GetMapping("/proposals/memberProposals")
    List<ProposalWrapper> getMemberProposals(@RequestParam("userId") Long userId);

    default List<ProposalWrapper> getLinkingProposals(long proposalId) {
        List<IProposalReference> proposalReferences = getProposalReference(null, proposalId);
        List<ProposalWrapper> linkingProposals = new ArrayList<>();
        for (IProposalReference proposalReference : proposalReferences) {
            try {
                final ProposalWrapper proposal = getProposal(proposalReference.getProposalId());
                if (!linkingProposals.contains(proposal)) {
                    linkingProposals.add(new ProposalWrapper(proposal));
                }
            } catch (ProposalNotFoundException ignored) {

            }
        }
        return linkingProposals;
    }

    default ProposalWrapper getProposalByThreadId(Long threadId) {
        return listProposals(null, null, null, null, null, null, null, null, null, null, null,
                threadId, null)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ProposalNotFoundException(
                        "No proposal with threadId = " + threadId));
    }

    default ProposalWrapper getProposal(long proposalId) throws ProposalNotFoundException {
        return getProposal(proposalId, false);
    }

    default void invalidateProposalCache(long proposalId) {
        ServiceRequestUtils.invalidateCache(CacheKeys.withClass(ProposalWrapper.class)
                .withParameter("proposalId", proposalId)
                .withParameter("includeDeleted", false).build(), CacheName.MISC_REQUEST);
    }

    @GetMapping("/proposals/{proposalId}")
    ProposalWrapper getProposal(@PathVariable("proposalId") Long proposalId,
            @RequestParam(value = "includeDeleted", required = false, defaultValue = "false")
                    Boolean includeDeleted) throws ProposalNotFoundException;

    @GetMapping("/proposalReferences")
    List<IProposalReference> getProposalReference(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "subProposalId", required = false) Long subProposalId);

    @GetMapping("/proposals/{proposalId}/subproposals")
    List<ProposalWrapper> getSubproposals(@PathVariable("proposalId") Long proposalId,
            @RequestParam("includeProposalsInSameContest") Boolean includeProposalsInSameContest);

    @GetMapping("/proposals/{proposalId}/listProposalLinks")
    List<ProposalWrapper> getLinkingProposalsForProposalId(
            @PathVariable(value = "proposalId", required = false) Long proposalId);

    @GetMapping("/proposals/{proposalId}/materializedPoints")
    Integer getProposalMaterializedPoints(@PathVariable("proposalId") Long proposalId);

    @GetMapping("/proposals/numberOfProposalsAlreadyJudgedForJudge")
    int getNumberOfProposalsAlreadyJudgedForJudge(
            @RequestParam("contestPhaseId") Long contestPhaseId,
            @RequestParam("userId") Long userId);

    @GetMapping("/proposals/numberOfProposalsForJudge")
    int getNumberOfProposalsForJudge(@RequestParam("contestPhaseId") Long contestPhaseId,
            @RequestParam("userId") Long userId);

    default IProposalReference getProposalReferenceByProposalIdSubProposalId(Long proposalId,
            Long subProposalId) {
        return getProposalReference(proposalId, subProposalId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/proposals")
    boolean updateProposal(@RequestBody ProposalWrapper proposal);

    @DeleteMapping("/proposals/{proposalId}")
    boolean deleteProposal(@PathVariable("proposalId") Long proposalId);

    @GetMapping("/proposalVersions")
    ProposalVersionWrapper getProposalVersionByProposalIdVersion(
            @RequestParam("proposalId") Long proposalId,
            @RequestParam(value = "version", required = false) Integer version);

    @GetMapping("/proposalVersions/groupedVersionsByContest")
    List<ProposalVersionWrapper> getProposalVersionsGroupedVersionsByContest(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "contestId", required = false) Long contestId,
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "end", required = false) Integer end);

    @GetMapping("/proposals/{proposalId}/maxVersion")
    Integer getMaxVersion(@PathVariable("proposalId") Long proposalId);

    @GetMapping("/count/proposalVersions/groupedVersionsByContest")
    int countProposalVersionsGroupedVersionsByContest(
            @RequestParam(value = "proposalId", required = false) Long proposalId,
            @RequestParam(value = "contestId", required = false) Long contestId);

    @GetMapping("/count/proposalVersions")
    int countProposalVersions(
            @RequestParam(value = "proposalId", required = false) Long proposalId);

    default ProposalVersionWrapper getProposalVersionByProposal(Long proposalId) {
        return getProposalVersionByProposalIdVersion(proposalId, null);
    }

    @GetMapping("/proposalVersions/{proposalId}")
    List<ProposalVersionWrapper> getAllProposalVersions(
            @PathVariable(value = "proposalId", required = false) Long proposalId);

    default ContestType getContestTypeFromProposalId(Long proposalId) {
        try {
            ContestWrapper contest = getCurrentContestForProposal(proposalId);
            return StaticAdminContext.getContestTypeClient()
                    .getContestType(contest.getContestTypeId());
        } catch (ContestNotFoundException e) {
            throw ReferenceResolutionException.toObject(ContestWrapper.class, "")
                    .fromObject(ProposalWrapper.class, proposalId);
        }
    }

    default ContestWrapper getCurrentContestForProposal(Long proposalId)
            throws ContestNotFoundException {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        ContestPhaseWrapper contestPhase = StaticContestContext.getContestClient()
                .getContestPhase(contestPhaseId);
        return StaticContestContext.getContestClient().getContest(contestPhase.getContestId());

    }

    @GetMapping("/proposals/{proposalId}/latestContestPhaseIdInProposal")
    Long getLatestContestPhaseIdInProposal(@PathVariable("proposalId") Long proposalId);

    default ContestWrapper getLatestContestInProposal(Long proposalId)
            throws ContestNotFoundException {
        return StaticContestContext.getContestClient()
                .getContest(getLatestContestPhaseInProposal(proposalId).getContestId());
    }

    default ContestPhaseWrapper getLatestContestPhaseInProposal(Long proposalId) {
        Long contestPhaseId = getLatestContestPhaseIdInProposal(proposalId);
        return StaticContestContext.getContestClient().getContestPhase(contestPhaseId);
    }

    @PostMapping("/proposalReferences/populateTableWithProposal")
    void populateTableWithProposal(@RequestParam("proposalId") Long proposalId);

    default boolean isMemberSubscribedToProposal(Long proposalId, Long userId) {
        return StaticActivityContext.getActivityClient()
                .isSubscribed(userId, ActivityCategory.PROPOSAL, proposalId);
    }

    default void subscribeMemberToProposal(long proposalId, long userId) {
        StaticActivityContext.getActivityClient()
                .addSubscription(userId, ActivityCategory.PROPOSAL, proposalId, null);
    }

    default void unsubscribeMemberFromProposal(long proposalId, long userId) {
        StaticActivityContext.getActivityClient()
                .deleteSubscription(userId, ActivityCategory.PROPOSAL, proposalId);
    }

    default ProposalWrapper getProposalFromLinkUrl(String linkUrl) {
        List<Long> proposalIds = getProposalIdsFromLinksInText(linkUrl);
        if (!proposalIds.isEmpty()) {
            try {
                return getProposal(proposalIds.get(0));
            } catch (ProposalNotFoundException ignored) {
            }
        }

        return null;
    }

    default List<Long> getProposalIdsFromLinksInText(String text) {
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

    default List<Long> getProposalIdsFromLegacyLinksInText(String text) {
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
}

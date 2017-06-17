package org.xcolab.view.pages.proposals.view.proposal.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.utils.picker.ProposalPickerFilterUtil;
import org.xcolab.view.pages.proposals.utils.picker.ProposalPickerSortingUtil;
import org.xcolab.view.pages.proposals.view.proposal.json.picker.ContestsResult;
import org.xcolab.view.pages.proposals.view.proposal.json.picker.ProposalsResult;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalPickerJSONController {

    private static final Logger _log = LoggerFactory.getLogger(ProposalPickerJSONController.class);

    @GetMapping("proposals/proposalPicker")
    public ProposalsResult proposalPicker(HttpServletRequest request, HttpServletResponse response,
        @PathVariable String contestYear, @PathVariable String contestUrlName,
        @RequestParam(value = "type", required = false) String requestType,
        @RequestParam(value = "filterKey", required = false) String filterType,
        @RequestParam(required = false) String filterText,
        @RequestParam(required = false) int start,
        @RequestParam(required = false) int end,
        @RequestParam(required = false) String sortOrder,
        @RequestParam(required = false) String sortColumn,
        @RequestParam(required = false) Long sectionId,
        @RequestParam(required = false) long contestPK) throws IOException {

        List<Proposal> proposals;
        final long memberId = MemberAuthUtil.getMemberId(request);

        switch (requestType.toUpperCase()) {
            case "SUBSCRIPTIONSANDSUPPORTING":
                proposals = ProposalPickerFilterUtil
                    .getFilteredSubscribedSupportingProposalsForUser(memberId, filterType,
                        sectionId, request);
                break;
            case "SUBSCRIPTIONS":
                proposals = ProposalPickerFilterUtil
                    .getFilteredSubscribedProposalsForUser(memberId, filterType, sectionId,
                        request);
                break;
            case "SUPPORTING":
                proposals = ProposalPickerFilterUtil
                    .getFilteredSupportingProposalsForUser(memberId, filterType, sectionId,
                        request);
                break;
            case "ALL":
            case "CONTESTS":
                proposals = ProposalPickerFilterUtil
                    .getFilteredAllProposals(filterText, filterType, sectionId, contestPK, request);
                break;
            default:
                _log.error("Proposal picker was loaded with unknown requestType {}", requestType);
                throw new InternalException("Unknown requestType " + requestType);
        }

        int totalCount = proposals.size();

        ProposalPickerSortingUtil.sortProposalsList(sortOrder, sortColumn, proposals);
        if (end >= totalCount && totalCount > 0) {
            end = totalCount;
        }
        if (totalCount > (end - start)) {
            proposals = proposals.subList(start, end);
        }

        return new ProposalsResult(proposals, totalCount);
    }

    @GetMapping("proposals/proposalPickerContests")
    public ContestsResult proposalPickerContests(HttpServletRequest request,
        HttpServletResponse response, @PathVariable String contestYear,
        @PathVariable String contestUrlName,
        @RequestParam(value = "type", required = false) String requestType,
        @RequestParam(value = "filterKey", required = false) String filterType,
        @RequestParam(required = false) String filterText,
        @RequestParam(required = false) int start,
        @RequestParam(required = false) int end,
        @RequestParam(required = false) String sortOrder,
        @RequestParam(required = false, value = "contestSortColumn") String sortColumn,
        @RequestParam(required = false) Long sectionId) throws IOException {

        List<Contest> contests =
            ProposalPickerFilterUtil.getTextFilteredContests(sectionId, filterText);

        int totalCount = contests.size();

        if (end >= contests.size() && !contests.isEmpty()) {
            end = contests.size();
        }
        ProposalPickerSortingUtil.sortContestsList(sortOrder, sortColumn, contests);
        if (contests.size() > (end - start)) {
            contests = contests.subList(start, end);
        }

        return new ContestsResult(contests, totalCount);
    }

    /**
     * This method is used to fill the counting bubbles for each tab
     */
    @GetMapping("proposals/proposalPickerCounter")
    public int proposalPickerCounter(HttpServletRequest request,
        HttpServletResponse response, @PathVariable String contestYear,
        @PathVariable String contestUrlName, @RequestParam String filterKey,
        @RequestParam long sectionId, @RequestParam Tab tab)
            throws IOException {
        long memberId = MemberAuthUtil.getMemberId(request);

        switch (tab) {
            case ALL_CONTESTS:
                return ProposalPickerFilterUtil.getTextFilteredContests(sectionId, "")
                        .size();
            case ALL_PROPOSALS:
                return ProposalPickerFilterUtil.getFilteredAllProposals(
                        "", filterKey, sectionId, 0L, request)
                        .size();
            case SUBSCRIBED_PROPOSALS:
                return ProposalPickerFilterUtil.getFilteredSubscribedProposalsForUser(
                        memberId, filterKey, sectionId, request)
                        .size();
            case SUPPORTED_PROPOSALS:
                return ProposalPickerFilterUtil.getFilteredSupportingProposalsForUser(
                        memberId, filterKey, sectionId, request)
                        .size();
            case SUBSCRIBED_SUPPORTED_PROPOSALS:
                return ProposalPickerFilterUtil.getFilteredSubscribedSupportingProposalsForUser(
                        memberId, filterKey, sectionId, request)
                        .size();
            default:
                throw new IllegalArgumentException("Unknown tab: " + tab);
        }
    }

    public enum Tab {
        ALL_CONTESTS, ALL_PROPOSALS, SUBSCRIBED_PROPOSALS, SUPPORTED_PROPOSALS,
        SUBSCRIBED_SUPPORTED_PROPOSALS;
    }
}

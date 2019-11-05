package org.xcolab.view.pages.proposals.view.proposal.json;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.utils.picker.ProposalPickerFilterUtil;
import org.xcolab.view.pages.proposals.utils.picker.ProposalPickerSortingUtil;
import org.xcolab.view.pages.proposals.view.proposal.json.picker.ContestsResult;
import org.xcolab.view.pages.proposals.view.proposal.json.picker.ProposalsResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalPickerJSONController {

    @GetMapping("proposals/proposalPicker")
    public ProposalsResult proposalPicker(HttpServletRequest request, HttpServletResponse response,
            UserWrapper user, ProposalContext proposalContext, @PathVariable String contestYear,
            @PathVariable String contestUrlName, @RequestParam(required = false) Tab tab,
            @RequestParam(value = "filterKey", required = false) String filterType,
            @RequestParam(required = false) String filterText,
            @RequestParam(required = false) int start, @RequestParam(required = false) int end,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) String sortColumn,
            @RequestParam(required = false) Long sectionId,
            @RequestParam(required = false) long contestId) throws IOException {

        List<ProposalWrapper> proposals;
        final long userId = user.getId();

        switch (tab) {
            case SUBSCRIBED_SUPPORTED_PROPOSALS:
                proposals = ProposalPickerFilterUtil
                        .getFilteredSubscribedSupportingProposalsForUser(proposalContext, userId,
                                filterType, sectionId);
                break;
            case SUBSCRIBED_PROPOSALS:
                proposals = ProposalPickerFilterUtil
                        .getFilteredSubscribedProposalsForUser(proposalContext, userId, filterType,
                                sectionId);
                break;
            case SUPPORTED_PROPOSALS:
                proposals = ProposalPickerFilterUtil
                        .getFilteredSupportingProposalsForUser(proposalContext, userId, filterType,
                                sectionId);
                break;
            case ALL_PROPOSALS:
            case ALL_CONTESTS:
                proposals = ProposalPickerFilterUtil
                        .getFilteredAllProposals(proposalContext, filterText, filterType, sectionId,
                                contestId);
                break;
            default:
                throw new InternalException("Unknown tab " + tab);
        }
        List<ProposalWrapper> reWrappedProposals = new ArrayList<>();
        for(ProposalWrapper pw: proposals){
            reWrappedProposals.add(new ProposalWrapper(pw));
        }
        proposals = reWrappedProposals;

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
            @PathVariable String contestUrlName, @RequestParam(required = false) Tab tab,
            @RequestParam(value = "filterKey", required = false) String filterType,
            @RequestParam(required = false) String filterText,
            @RequestParam(required = false) int start, @RequestParam(required = false) int end,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false, value = "contestSortColumn") String sortColumn,
            @RequestParam(required = false) Long sectionId) throws IOException {

        List<ContestWrapper> contests =
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
    public int proposalPickerCounter(HttpServletRequest request, HttpServletResponse response,
            UserWrapper user, ProposalContext proposalContext, @PathVariable String contestYear,
            @PathVariable String contestUrlName, @RequestParam String filterKey,
            @RequestParam long sectionId, @RequestParam Tab tab) throws IOException {
        long userId = user.getId();

        switch (tab) {
            case ALL_CONTESTS:
                return ProposalPickerFilterUtil.getTextFilteredContests(sectionId, "").size();
            case ALL_PROPOSALS:
                return ProposalPickerFilterUtil
                        .getFilteredAllProposals(proposalContext, "", filterKey, sectionId, 0L)
                        .size();
            case SUBSCRIBED_PROPOSALS:
                return ProposalPickerFilterUtil
                        .getFilteredSubscribedProposalsForUser(proposalContext, userId, filterKey,
                                sectionId).size();
            case SUPPORTED_PROPOSALS:
                return ProposalPickerFilterUtil
                        .getFilteredSupportingProposalsForUser(proposalContext, userId, filterKey,
                                sectionId).size();
            case SUBSCRIBED_SUPPORTED_PROPOSALS:
                return ProposalPickerFilterUtil
                        .getFilteredSubscribedSupportingProposalsForUser(proposalContext, userId,
                                filterKey, sectionId).size();
            default:
                throw new IllegalArgumentException("Unknown tab: " + tab);
        }
    }

    public enum Tab {
        ALL_CONTESTS,
        ALL_PROPOSALS,
        SUBSCRIBED_PROPOSALS,
        SUPPORTED_PROPOSALS,
        SUBSCRIBED_SUPPORTED_PROPOSALS
    }
}

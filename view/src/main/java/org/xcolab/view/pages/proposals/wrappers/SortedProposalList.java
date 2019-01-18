package org.xcolab.view.pages.proposals.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.enums.ProposalSortColumn;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedProposalList {

    private final List<ProposalWrapper> proposalsWithRibbons = new ArrayList<>();
    private final List<ProposalWrapper> proposalsWithoutRibbons = new ArrayList<>();

    public SortedProposalList(List<ProposalWrapper> proposals, final SortFilterPage sortFilterPage,
            ContestPhaseWrapper contestPhase) {
        if (sortFilterPage == null) {
            throw new IllegalArgumentException("SortFilterPage can't be null");
        }

        initProposalLists(proposals, contestPhase);
        sortProposalLists(sortFilterPage, contestPhase.getStatus());
    }

    private void initProposalLists(List<ProposalWrapper> proposals, ContestPhaseWrapper contestPhase) {
        final boolean proposalsCanHaveRibbons = contestPhase.isCompleted();
        if (!proposalsCanHaveRibbons) {
            // skip expensive ribbon check if proposals can't have ribbons
            proposalsWithoutRibbons.addAll(proposals);
        } else {
            for (ProposalWrapper proposal : proposals) {
                if (proposal.getRibbonWrapper().getRibbon() > 0) {
                    proposalsWithRibbons.add(proposal);
                } else {
                    proposalsWithoutRibbons.add(proposal);
                }
            }
        }
    }

    private void sortProposalLists(SortFilterPage sortFilterPage, ContestStatus phaseStatus) {
        final String sortColumn = sortFilterPage.getSortColumn();

        Comparator<ProposalWrapper> proposalComparator;
        boolean isSortColumnSet = StringUtils.isNotBlank(sortColumn);
        if (isSortColumnSet) {
            proposalComparator = getComparator(sortColumn, sortFilterPage.isSortAscending());
        } else {
            proposalComparator = phaseStatus.getDefaultProposalComparator();
        }

        proposalsWithRibbons.sort(isSortColumnSet ? proposalComparator
                : ProposalSortColumn.RIBBONS.getComparator().thenComparing(proposalComparator));
        proposalsWithoutRibbons.sort(proposalComparator);
    }

    private Comparator<ProposalWrapper> getComparator(String sortColumn, boolean isSortAscending) {
        final ProposalSortColumn proposalsColumn = ProposalSortColumn.valueOf(sortColumn);
        return isSortAscending ? proposalsColumn.getComparator()
                : proposalsColumn.getComparator().reversed();
    }

    public List<ProposalWrapper> getProposalsWithRibbons() {
        return Collections.unmodifiableList(proposalsWithRibbons);
    }

    public List<ProposalWrapper> getProposalsWithoutRibbons() {
        return Collections.unmodifiableList(proposalsWithoutRibbons);
    }

    public int getTotalSize() {
        return getSizeWithRibbons() + getSizeWithoutRibbons();
    }

    public int getSizeWithRibbons() {
        return proposalsWithRibbons.size();
    }

    public int getSizeWithoutRibbons() {
        return proposalsWithoutRibbons.size();
    }
}

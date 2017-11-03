package org.xcolab.view.pages.proposals.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.proposals.utils.ProposalSortColumn;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedProposalList {

    private final List<Proposal> proposalsWithRibbons = new ArrayList<>();
    private final List<Proposal> proposalsWithoutRibbons = new ArrayList<>();

    public SortedProposalList(List<Proposal> proposals, final SortFilterPage sortFilterPage,
            Comparator<Proposal> defaultComparator, boolean proposalsCanHaveRibbons) {
        if (sortFilterPage == null) {
            throw new IllegalArgumentException("SortFilterPage can't be null");
        }

        initProposalLists(proposals, proposalsCanHaveRibbons);
        sortProposalLists(sortFilterPage, defaultComparator);
    }

    private void initProposalLists(List<Proposal> proposals, boolean proposalsCanHaveRibbons) {
        if (!proposalsCanHaveRibbons) {
            // skip expensive ribbon check if proposals can't have ribbons
            proposalsWithoutRibbons.addAll(proposals);
        } else {
            for (Proposal contest : proposals) {
                if (contest.getRibbonWrapper().getRibbon() > 0) {
                    proposalsWithRibbons.add(contest);
                } else {
                    proposalsWithoutRibbons.add(contest);
                }
            }
        }
    }

    private void sortProposalLists(SortFilterPage sortFilterPage,
            Comparator<Proposal> defaultComparator) {
        final String sortColumn = sortFilterPage.getSortColumn();

        Comparator<Proposal> proposalComparator;
        boolean isSortColumnSet = StringUtils.isNotBlank(sortColumn);
        if (isSortColumnSet) {
            proposalComparator = getComparator(sortColumn, sortFilterPage.isSortAscending());
        } else {
            proposalComparator = defaultComparator;
        }

        proposalsWithRibbons.sort(isSortColumnSet ? proposalComparator
                : ProposalSortColumn.RIBBONS.getComparator().thenComparing(proposalComparator));
        proposalsWithoutRibbons.sort(proposalComparator);
    }

    private Comparator<Proposal> getComparator(String sortColumn, boolean isSortAscending) {
        final ProposalSortColumn proposalsColumn = ProposalSortColumn.valueOf(sortColumn);
        return isSortAscending ? proposalsColumn.getComparator()
                : proposalsColumn.getComparator().reversed();
    }

    public List<Proposal> getProposalsWithRibbons() {
        return Collections.unmodifiableList(proposalsWithRibbons);
    }

    public List<Proposal> getProposalsWithoutRibbons() {
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

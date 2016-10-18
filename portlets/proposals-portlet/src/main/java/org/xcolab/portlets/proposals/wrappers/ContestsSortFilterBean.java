package org.xcolab.portlets.proposals.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.proposals.utils.ContestsColumn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContestsSortFilterBean {
    private final List<ContestWrapper> contests;
    private ContestsColumn sortColumn;
    private Boolean showOnlyFeatured;
    private List<ContestWrapper> contestsFeatured = new ArrayList<ContestWrapper>();
    private List<ContestWrapper> contestsNormal = new ArrayList<ContestWrapper>();

    public ContestsSortFilterBean(Boolean showOnlyFeatured, List<ContestWrapper> contests, final SortFilterPage sortFilterPage) {
        this(showOnlyFeatured, contests, sortFilterPage, null);
    }

    public ContestsSortFilterBean(Boolean showOnlyFeatured, List<ContestWrapper> contests, final SortFilterPage sortFilterPage, ContestsColumn sortColumnConstruct) {
        super();
        List<ContestWrapper> filteredContests = contests;

        // filter contests
        if (StringUtils.isNotBlank(sortFilterPage.getFilter())) {
            String filterString = sortFilterPage.getFilter();
            filteredContests = new ArrayList<ContestWrapper>();
            for (ContestWrapper contest: contests) {
                if (contest.getContestName().toLowerCase().contains(filterString) ||
                        contest.getContestShortName().toLowerCase().contains(filterString)) {
                    filteredContests.add(contest);
                }
            }

        }
        this.showOnlyFeatured = showOnlyFeatured;
        this.contests = filteredContests;

        // sort contests

        if (StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            sortColumn = ContestsColumn.valueOf(sortFilterPage.getSortColumn());
        }
        else {
            sortColumn = sortColumnConstruct == null ? ContestsColumn.DEFAULT : sortColumnConstruct;
        }
        Collections.sort(this.contests, new Comparator<ContestWrapper>() {
            final
            @Override
            public int compare(ContestWrapper o1, ContestWrapper o2) {
                if (o1.isFeatured() && !o2.isFeatured()) {
                    return -1;
                }
                else if (! o1.isFeatured() && o2.isFeatured()) {
                    return 1;
                }
                if (sortFilterPage.isSortAscending()) {
                    return sortColumn.getColumnComparator().compare(o1, o2);
                }
                return sortColumn.getColumnComparator().compare(o2, o1);
            }
        });
        for (ContestWrapper contest: this.contests) {
            if (contest.isFeatured()) {
                contestsFeatured.add(contest);
            } else {
                contestsNormal.add(contest);
            }
        }
    }

    public List<ContestWrapper> getContestsFeatured() {
        return contestsFeatured;
    }

    public void setContestsFeatured(List<ContestWrapper> contestsFeatured) {
        this.contestsFeatured = contestsFeatured;
    }

    public List<ContestWrapper> getContestsNormal() {
        return contestsNormal;
    }

    public void setContestsNormal(List<ContestWrapper> contestsNormal) {
        this.contestsNormal = contestsNormal;
    }

    public List<ContestWrapper> getContests() {
        return contests;
    }

    public Boolean getShowOnlyFeatured() {
        return this.showOnlyFeatured;
    }


}

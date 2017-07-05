package org.xcolab.view.pages.proposals.wrappers;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.proposals.utils.ContestsColumn;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.ArrayList;
import java.util.List;

public class ContestsSortFilterBean {
    private final List<Contest> contests;
    private final ContestsColumn sortColumn;
    private List<Contest> contestsFeatured = new ArrayList<>();
    private List<Contest> contestsNormal = new ArrayList<>();

    public ContestsSortFilterBean(List<Contest> contests, final SortFilterPage sortFilterPage) {
        this(contests, sortFilterPage, null);
    }

    public ContestsSortFilterBean(List<Contest> contests, final SortFilterPage sortFilterPage, ContestsColumn sortColumnConstruct) {
        super();
        List<Contest> filteredContests = contests;

        // filter contests
        if (StringUtils.isNotBlank(sortFilterPage.getFilter())) {

            String filterString = sortFilterPage.getFilter().toLowerCase();
            filteredContests = new ArrayList<>();
            for (Contest contest: contests) {

                if (contest.getContestName().toLowerCase().contains(filterString) ||
                        contest.getContestShortName().toLowerCase().contains(filterString)) {
                    filteredContests.add(contest);
                }
            }

        }
        this.contests = filteredContests;

        // sort contests

        if (StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            sortColumn = ContestsColumn.valueOf(sortFilterPage.getSortColumn());
        }
        else {
            sortColumn = sortColumnConstruct == null ? ContestsColumn.DEFAULT : sortColumnConstruct;
        }
        this.contests.sort((o1, o2) -> {
            if (o1.isFeatured() && !o2.isFeatured()) {
                return -1;
            }
            if (!o1.isFeatured() && o2.isFeatured()) {
                return 1;
            }
            if (sortFilterPage.isSortAscending()) {
                return sortColumn.getColumnComparator().compare(o1, o2);
            }
            return sortColumn.getColumnComparator().compare(o2, o1);
        });
        for (Contest contest: this.contests) {
            if (contest.isFeatured()) {
                contestsFeatured.add(contest);
            } else {
                contestsNormal.add(contest);
            }
        }
    }

    public List<Contest> getContestsFeatured() {
        return contestsFeatured;
    }

    public void setContestsFeatured(List<Contest> contestsFeatured) {
        this.contestsFeatured = contestsFeatured;
    }

    public List<Contest> getContestsNormal() {
        return contestsNormal;
    }

    public void setContestsNormal(List<Contest> contestsNormal) {
        this.contestsNormal = contestsNormal;
    }

    public List<Contest> getContests() {
        return contests;
    }


}

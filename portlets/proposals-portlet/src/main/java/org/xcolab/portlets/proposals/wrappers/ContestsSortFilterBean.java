package org.xcolab.portlets.proposals.wrappers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.proposals.utils.ContestsColumn;

public class ContestsSortFilterBean {
    private final List<ContestWrapper> contests;
    private final SortFilterPage sortFilterPage;
    private ContestsColumn sortColumn;
    
    private List<ContestWrapper> contestsPartA = new ArrayList<ContestWrapper>();
    private List<ContestWrapper> contestsPartB = new ArrayList<ContestWrapper>();
    private List<ContestWrapper> contestsFeatured = new ArrayList<ContestWrapper>();
    private List<ContestWrapper> contestsNormal = new ArrayList<ContestWrapper>();

    public ContestsSortFilterBean(List<ContestWrapper> contests, final SortFilterPage sortFilterPage) {
        super();
        this.sortFilterPage = sortFilterPage;
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
        this.contests = filteredContests;
        
        // sort contests
        
        if (StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            sortColumn = ContestsColumn.valueOf(sortFilterPage.getSortColumn());
        }
        else {
            sortColumn = ContestsColumn.DEFAULT;
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
                int ret = sortColumn.getColumnComparator().compare(o1, o2);
                
                return sortFilterPage.isSortAscending() ? ret : - ret;
            }
        });
        boolean addToA = true;
        for (ContestWrapper contest: this.contests) {
            if (contest.isFeatured()) contestsFeatured.add(contest);
            else contestsNormal.add(contest);
            
            if (addToA) contestsPartA.add(contest);
            else contestsPartB.add(contest);
            
            addToA = !addToA;
        }
    }

    public List<ContestWrapper> getContestsPartA() {
        return contestsPartA;
    }

    public void setContestsPartA(List<ContestWrapper> contestsPartA) {
        this.contestsPartA = contestsPartA;
    }

    public List<ContestWrapper> getContestsPartB() {
        return contestsPartB;
    }

    public void setContestsPartB(List<ContestWrapper> contestsPartB) {
        this.contestsPartB = contestsPartB;
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
    
    

}

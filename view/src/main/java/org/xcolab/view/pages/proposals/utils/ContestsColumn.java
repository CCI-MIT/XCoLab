package org.xcolab.view.pages.proposals.utils;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.Comparator;

public enum ContestsColumn {
    CONTEST_NAME (Comparator.comparing(o -> o.getTitleWithEndYear().toLowerCase())),
    PROPOSALS_COUNT((o1, o2) -> (int) (o1.getProposalsCount() - o2.getProposalsCount())),
    COMMENTS_COUNT((o1, o2) -> (int) (o1.getTotalCommentsCount() - o2.getTotalCommentsCount())),
    VOTES_COUNT((o1, o2) -> (int) (o1.getVotesCount() - o2.getVotesCount())),
    WHAT((o1, o2) -> {
        String s1 = o1.getWhatName();
        String s2 = o2.getWhatName();
        return compareContestsByStringValues(o1, s1, o2, s2);
    }),
    WHERE((o1, o2) -> {
        String s1 = o1.getWhereName();
        String s2 = o2.getWhereName();
        return compareContestsByStringValues(o1, s1, o2, s2);
    }),
    WHO((o1, o2) -> {
        String s1 = o1.getWhoName();
        String s2 = o2.getWhoName();
        return compareContestsByStringValues(o1, s1, o2, s2);
    }),
    HOW((o1, o2) -> {
        String s1 = o1.getHowName();
        String s2 = o2.getHowName();
        return compareContestsByStringValues(o1, s1, o2, s2);
    }),

    REFERENCE_DATE ((o1, o2) -> {
        if(o2.getLastPhase() != null && o1.getLastPhase() != null) {
            return o2.getLastPhase().getPhaseReferenceDate().compareTo(o1.getLastPhase().getPhaseReferenceDate());
        }
        if(o2.getLastPhase() == null){
            return 1;
        }
        return 0;
    }),

    DEFAULT(Comparator.comparingInt(ContestWrapper::getWeight).reversed());
    
    private final Comparator<ContestWrapper> columnComparator;

    ContestsColumn(Comparator<ContestWrapper> columnComparator) {
        this.columnComparator = columnComparator;
    }
    
    public Comparator<ContestWrapper> getColumnComparator() {
        return columnComparator;
    }

    private static int compareContestsByStringValues(ContestWrapper c1, String s1, ContestWrapper c2, String s2) {
        if (s1.isEmpty()) {
            if (s2.isEmpty()) {
                return (int) (c1.getId() - c2.getId());
            }
            return -1;
        }
        if (s2.isEmpty()) {
            return 1;
        }
        return s1.toLowerCase().compareTo(s2.toLowerCase());
    }
}

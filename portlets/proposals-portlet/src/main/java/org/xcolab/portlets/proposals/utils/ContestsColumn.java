package org.xcolab.portlets.proposals.utils;

import jodd.util.StringUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


import org.xcolab.client.contest.pojo.Contest;

import java.util.Comparator;

public enum ContestsColumn {
    CONTEST_NAME (new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            return o1.getContestShortName().toLowerCase().compareTo(o2.getContestShortName().toLowerCase());
        }
    }),
    PROPOSALS_COUNT(new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            return (int) (o1.getProposalsCount() - o2.getProposalsCount());
        }
    }),
    COMMENTS_COUNT(new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            return (int) (o1.getCommentsCount() - o2.getCommentsCount());
        }
    }),
    VOTES_COUNT(new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            return (int) (o1.getVotesCount() - o2.getVotesCount());
        }
    }),
    WHAT(new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            String s1 = o1.getWhatName();
            String s2 = o2.getWhatName();
            return compareContestsByStringValues(o1, s1, o2, s2);
        }
    }),
    WHERE(new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            String s1 = o1.getWhereName();
            String s2 = o2.getWhereName();
            return compareContestsByStringValues(o1, s1, o2, s2);
        }
    }),
    WHO(new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            String s1 = o1.getWhoName();
            String s2 = o2.getWhoName();
            return compareContestsByStringValues(o1, s1, o2, s2);
        }
    }),
    HOW(new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            String s1 = o1.getHowName();
            String s2 = o2.getHowName();
            return compareContestsByStringValues(o1, s1, o2, s2);
        }
    }),

    REFERENCE_DATE (new Comparator<Contest>() {
        @Override
        public int compare(Contest o1, Contest o2) {
            if(o2.getLastPhase() != null && o1.getLastPhase() != null) {
                return o2.getLastPhase().getPhaseReferenceDate().compareTo(o1.getLastPhase().getPhaseReferenceDate());
            }
            if(o2.getLastPhase() == null){
                return 1;
            }
            return 0;
        }
    }),

    DEFAULT(new Comparator<Contest>() {

        @Override
        public int compare(Contest o1, Contest o2) {
            return o1.getWeight() - o2.getWeight();
        }
    });
    
    private final Comparator<Contest> columnComparator;

    ContestsColumn(Comparator<Contest> columnComparator) {
        this.columnComparator = columnComparator;
    }
    
    public Comparator<Contest> getColumnComparator() {
        return columnComparator;
    }

    private final static Log _log = LogFactoryUtil.getLog(ContestsColumn.class);

    private static int compareContestsByStringValues(Contest c1, String s1, Contest c2, String s2) {
        if (StringUtil.isEmpty(s1)) {
            if (StringUtil.isEmpty(s2)) {
                return (int) (c1.getContestPK() - c2.getContestPK());
            }
            return -1;
        }
        if (StringUtil.isEmpty(s2)) {
            return 1;
        }
        return s1.toLowerCase().compareTo(s2.toLowerCase());
    }
}

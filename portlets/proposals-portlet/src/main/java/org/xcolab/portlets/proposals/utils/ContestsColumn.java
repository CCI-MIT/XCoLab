package org.xcolab.portlets.proposals.utils;

import jodd.util.StringUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.portlets.proposals.wrappers.ContestWrapper;

import java.util.Comparator;

public enum ContestsColumn {
    CONTEST_NAME (new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            return o1.getContestShortName().toLowerCase().compareTo(o2.getContestShortName().toLowerCase());
        }
    }),
    PROPOSALS_COUNT(new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            return (int) (o1.getProposalsCount() - o2.getProposalsCount());
        }
    }),
    COMMENTS_COUNT(new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            return (int) (o1.getCommentsCount() - o2.getCommentsCount());
        }
    }),
    VOTES_COUNT(new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {

            try {
                return (int) (o1.getVotesCount() - o2.getVotesCount());
            } catch (PortalException | SystemException e) {
                _log.error("Can't get votes count", e);
            }
            return 0;
        }
    }),
    WHAT(new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            String s1 = o1.getWhatName();
            String s2 = o2.getWhatName();
            return compareContestsByStringValues(o1, s1, o2, s2);
        }
    }),
    WHERE(new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            String s1 = o1.getWhereName();
            String s2 = o2.getWhereName();
            return compareContestsByStringValues(o1, s1, o2, s2);
        }
    }),
    WHO(new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            String s1 = o1.getWhoName();
            String s2 = o2.getWhoName();
            return compareContestsByStringValues(o1, s1, o2, s2);
        }
    }),
    HOW(new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            String s1 = o1.getHowName();
            String s2 = o2.getHowName();
            return compareContestsByStringValues(o1, s1, o2, s2);
        }
    }),

    REFERENCE_DATE (new Comparator<ContestWrapper>() {
        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            if(o2.getLastPhase() != null && o1.getLastPhase() != null) {
                return o2.getLastPhase().getPhaseReferenceDate().compareTo(o1.getLastPhase().getPhaseReferenceDate());
            }
            if(o2.getLastPhase() == null){
                return 1;
            }
            return 0;
        }
    }),

    DEFAULT(new Comparator<ContestWrapper>() {

        @Override
        public int compare(ContestWrapper o1, ContestWrapper o2) {
            return o1.getWeight() - o2.getWeight();
        }
    });
    
    private final Comparator<ContestWrapper> columnComparator;

    ContestsColumn(Comparator<ContestWrapper> columnComparator) {
        this.columnComparator = columnComparator;
    }
    
    public Comparator<ContestWrapper> getColumnComparator() {
        return columnComparator;
    }

    private final static Log _log = LogFactoryUtil.getLog(ContestsColumn.class);

    private static int compareContestsByStringValues(ContestWrapper c1, String s1, ContestWrapper c2, String s2) {
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

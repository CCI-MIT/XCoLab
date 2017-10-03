package org.xcolab.view.pages.proposals.view.proposal.json.picker;

import org.xcolab.client.contest.pojo.Contest;

import java.util.List;
import java.util.stream.Collectors;

public class ContestsResult {

    private final List<SimpleContest> contests;
    private final int totalNumberOfContests;

    public ContestsResult(List<Contest> contests, int totalNumberOfContests) {
        this.contests = contests.stream()
            .map(SimpleContest::new)
            .collect(Collectors.toList());
        this.totalNumberOfContests = totalNumberOfContests;
    }

    public List<SimpleContest> getContests() {
        return contests;
    }

    public int getTotalNumberOfContests() {
        return totalNumberOfContests;
    }

    private static class SimpleContest {

        private final long id;
        private final String contestShortName;
        private final String contestName;
        private final long contestPK;
        private final String flagText;
        private final int flag;
        private final String flagTooltip;
        private final long proposalsCount;
        private final long totalCommentsCount;
        private final String what;
        private final String who;
        private final String where;
        private final long date;

        private SimpleContest(Contest contest) {
            this.id = contest.getContestPK();
            this.contestShortName = contest.getContestShortNameWithEndYear();
            this.contestName = contest.getContestName();
            this.contestPK = contest.getContestPK();
            this.flagText = contest.getFlagText();
            this.flag = contest.getFlag();
            this.flagTooltip = contest.getFlagTooltip();
            this.proposalsCount = contest.getProposalsCount();
            this.totalCommentsCount = contest.getTotalCommentsCount();
            this.what = contest.getWhatName();
            this.who = contest.getWhoName();
            this.where = contest.getWhereName();
            this.date = contest.getCreatedTime();
        }

        public long getId() {
            return id;
        }

        public String getContestShortName() {
            return contestShortName;
        }

        public String getContestName() {
            return contestName;
        }

        public long getContestPK() {
            return contestPK;
        }

        public String getFlagText() {
            return flagText;
        }

        public int getFlag() {
            return flag;
        }

        public String getFlagTooltip() {
            return flagTooltip;
        }

        public long getProposalsCount() {
            return proposalsCount;
        }

        public long getTotalCommentsCount() {
            return totalCommentsCount;
        }

        public String getWhat() {
            return what;
        }

        public String getWho() {
            return who;
        }

        public String getWhere() {
            return where;
        }

        public long getDate() {
            return date;
        }
    }
}

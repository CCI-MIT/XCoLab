package org.xcolab.view.pages.proposals.view.proposal.json.picker;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class ContestsResult {

    private final List<SimpleContest> contests;
    private final int totalNumberOfContests;

    public ContestsResult(List<ContestWrapper> contests, int totalNumberOfContests) {
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
        private final String title;
        private final String question;
        private final String flagText;
        private final int flag;
        private final String flagTooltip;
        private final long proposalsCount;
        private final long totalCommentsCount;
        private final String what;
        private final String who;
        private final String where;
        private final long date;

        private SimpleContest(ContestWrapper contest) {
            this.id = contest.getId();
            this.title = contest.getTitleWithEndYear();
            this.question = contest.getQuestion();
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

        public String getTitle() {
            return title;
        }

        public String getQuestion() {
            return question;
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

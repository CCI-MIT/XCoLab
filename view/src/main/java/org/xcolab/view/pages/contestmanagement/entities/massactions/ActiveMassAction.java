package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

public class ActiveMassAction extends ContestMassActionAdapter {

    private final boolean setActive;

    public ActiveMassAction() {
        this(false);
    }

    public ActiveMassAction(boolean setActive) {
        super(setActive ? "Active" : "Prior");
        this.setActive = setActive;
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed) {
        for (Contest contest : contests) {
            if (contest.getIsSharedContestInForeignColab()) {
                contest = ContestClientUtil.getContest(contest.getContestPK());
            }
            contest.setContestActive(setActive);
            contest.persist();
        }
    }
}

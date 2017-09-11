package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

public class ActiveMassAction extends ContestMassActionAdapter {

    private final boolean isSetActive;

    public ActiveMassAction() {
        this(false);
    }

    public ActiveMassAction(boolean isSetActive) {
        super(isSetActive ? "Active" : "Prior");
        this.isSetActive = isSetActive;
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed) {
        for (Contest contest : contests) {
            if (contest.getIsSharedContestInForeignColab()) {
                contest = ContestClientUtil.getContest(contest.getContestPK());
            }
            contest.setContestActive(isSetActive);
            contest.persist();
        }
    }
}

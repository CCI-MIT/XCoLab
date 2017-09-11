package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

public abstract class SetContestPropertyMassAction extends ContestMassActionAdapter {

    private final boolean setValue;

    public SetContestPropertyMassAction(boolean setValue, String trueDisplayName, String falseDisplayName) {
        super(setValue ? trueDisplayName : falseDisplayName);
        this.setValue = setValue;
    }

    abstract void setProperty(Contest contest, boolean setTrue);

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed) {
        for (Contest contest : contests) {
            if (contest.getIsSharedContestInForeignColab()) {
                contest = ContestClientUtil.getContest(contest.getContestPK());
            }
            setProperty(contest, setValue);
            contest.persist();
        }
    }
}

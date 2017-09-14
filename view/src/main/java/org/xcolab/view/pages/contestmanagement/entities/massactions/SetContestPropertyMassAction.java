package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public abstract class SetContestPropertyMassAction extends AbstractContestMassAction {

    private final boolean setValue;

    public SetContestPropertyMassAction(boolean setValue, String trueDisplayName,
            String falseDisplayName) {
        super(setValue ? trueDisplayName : falseDisplayName);
        this.setValue = setValue;
    }

    protected abstract void setProperty(Contest contest, boolean setTrue);

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) {
        for (Contest contest : contests) {
            if (contest.getIsSharedContestInForeignColab()) {
                contest = ContestClientUtil.getContest(contest.getContestPK());
            }
            setProperty(contest, setValue);
            contest.persist();
        }
    }
}

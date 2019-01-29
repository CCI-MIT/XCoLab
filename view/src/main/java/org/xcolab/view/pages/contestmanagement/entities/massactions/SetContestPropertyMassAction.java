package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public abstract class SetContestPropertyMassAction extends AbstractContestMassAction {

    private final boolean setValue;

    public SetContestPropertyMassAction(boolean setValue, String trueDisplayName,
            String falseDisplayName) {
        super(setValue ? trueDisplayName : falseDisplayName);
        this.setValue = setValue;
    }

    protected abstract void setProperty(ContestWrapper contest, boolean setTrue);

    @Override
    public void execute(List<ContestWrapper> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) {
        for (ContestWrapper contest : contests) {
            contest = StaticContestContext.getContestClient().getContest(contest.getId());
            setProperty(contest, setValue);
            contest.persist();
        }
    }
}

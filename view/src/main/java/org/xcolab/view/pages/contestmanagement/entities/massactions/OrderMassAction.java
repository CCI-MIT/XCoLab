package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class OrderMassAction extends AbstractContestMassAction {

    public OrderMassAction() {
        super("Order only");
    }

    @Override
    public void execute(List<ContestWrapper> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) {
        for (ContestWrapper contest : contests) {
            contest = StaticContestContext.getContestClient().getContest(contest.getId());
            StaticContestContext.getContestClient().updateContest(contest);
        }
    }

    public void execute(List<ContestWrapper> contests) {
        execute(contests, false, null, null);
    }
}

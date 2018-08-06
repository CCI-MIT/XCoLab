package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class OrderMassAction extends AbstractContestMassAction {

    public OrderMassAction() {
        super("Order only");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) {
        for (Contest contest : contests) {
            contest = ContestClientUtil.getContest(contest.getId());
            ContestClientUtil.updateContest(contest);
        }
    }

    public void execute(List<Contest> contests) {
        execute(contests, false, null, null);
    }
}

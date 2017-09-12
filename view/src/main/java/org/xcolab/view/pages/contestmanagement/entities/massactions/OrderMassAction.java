package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class OrderMassAction extends ContestMassActionAdapter {

    public OrderMassAction() {
        super("Order only");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) {
        for (Contest contestWrapper : contests) {
            Contest contest = contestWrapper.getWrapped();
            if (contest.getIsSharedContestInForeignColab()) {
                contest = ContestClientUtil.getContest(contest.getContestPK());
            }
            contest.setWeight(contestWrapper.getWeight());
            ContestClientUtil.updateContest(contest);
        }
    }
}

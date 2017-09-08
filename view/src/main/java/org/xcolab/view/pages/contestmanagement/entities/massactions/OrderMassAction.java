package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderMassAction extends ContestMassAction {

    public OrderMassAction() {
        super("Order only");
    }

    @Override
    void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper) {
        for (Contest contestWrapper : contestOverviewWrapper.getContestWrappers()) {
            Contest contest = contestWrapper.getWrapped();
            if (contest.getIsSharedContestInForeignColab()) {
                contest = ContestClientUtil.getContest(contest.getContestPK());
            }
            contest.setWeight(contestWrapper.getWeight());
            ContestClientUtil.updateContest(contest);
        }
    }
}

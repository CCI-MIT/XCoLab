package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class SubscribeMassAction extends ContestMassActionAdapter {

    private final boolean isSubscribe;

    public SubscribeMassAction(boolean isSubscribe) {
        super(isSubscribe ? "Subscribe to activity" : "Unsubscribe from activity");
        this.isSubscribe = isSubscribe;
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalStateException {
        Long memberId = dataWrapper.getMemberId();
        if (memberId == null) {
            throw new IllegalStateException("The mass action has not been setup yet.");
        }
        for (Contest contest : contests) {
            if (!contest.getIsSharedContestInForeignColab()) {
                if (isSubscribe) {
                    ContestClientUtil.subscribeMemberToContest(contest.getContestPK(), memberId);
                } else {
                    ContestClientUtil
                            .unsubscribeMemberFromContest(contest.getContestPK(), memberId);
                }
            }
        }
    }
}

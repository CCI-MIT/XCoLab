package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class SubscribeMassAction extends AbstractContestMassAction {

    private final boolean isSubscribe;

    public SubscribeMassAction(boolean isSubscribe) {
        super(isSubscribe ? "Subscribe to activity" : "Unsubscribe from activity");
        this.isSubscribe = isSubscribe;
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalStateException {
        Long userId = dataWrapper.getuserId();
        if (userId == null) {
            throw new IllegalStateException("The mass action has not been setup yet.");
        }
        for (Contest contest : contests) {
            if (isSubscribe) {
                ContestClientUtil.subscribeMemberToContest(contest.getId(), userId);
            } else {
                ContestClientUtil
                        .unsubscribeMemberFromContest(contest.getId(), userId);
            }
        }
    }
}

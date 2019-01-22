package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class SubscribeMassAction extends AbstractContestMassAction {

    private final boolean isSubscribe;

    public SubscribeMassAction(boolean isSubscribe) {
        super(isSubscribe ? "Subscribe to activity" : "Unsubscribe from activity");
        this.isSubscribe = isSubscribe;
    }

    @Override
    public void execute(List<ContestWrapper> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalStateException {
        Long userId = dataWrapper.getuserId();
        if (userId == null) {
            throw new IllegalStateException("The mass action has not been setup yet.");
        }
        for (ContestWrapper contest : contests) {
            if (isSubscribe) {
                StaticContestContext.getContestClient().subscribeMemberToContest(
                        contest.getId(), userId);
            } else {
                StaticContestContext.getContestClient().unsubscribeMemberFromContest(
                        contest.getId(), userId);
            }
        }
    }
}

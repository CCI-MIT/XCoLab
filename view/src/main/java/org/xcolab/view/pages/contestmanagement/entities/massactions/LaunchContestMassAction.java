package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class LaunchContestMassAction extends AbstractContestMassAction {

    private static final ActiveMassAction activeMassAction = new ActiveMassAction(true);
    private static final PrivateMassAction privateMassAction = new PrivateMassAction(false);
    private static final FeaturedMassAction featuredMassAction = new FeaturedMassAction(true);

    public LaunchContestMassAction() {
        super("Launch contests");
    }

    @Override
    public void execute(List<ContestWrapper> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) {
        activeMassAction.execute(contests, actionConfirmed, dataWrapper, response);
        privateMassAction.execute(contests, actionConfirmed, dataWrapper, response);
        featuredMassAction.execute(contests, actionConfirmed, dataWrapper, response);
    }
}

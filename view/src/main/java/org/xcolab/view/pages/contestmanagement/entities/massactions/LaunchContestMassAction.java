package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class LaunchContestMassAction extends ContestMassActionAdapter {
    static final ActiveMassAction activeMassAction = new ActiveMassAction(true);
    static final PrivateMassAction privateMassAction = new PrivateMassAction(false);
    static final FeaturedMassAction featuredMassAction= new FeaturedMassAction(true);

    public LaunchContestMassAction() {
        super("Launch contests");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) {
        activeMassAction.execute(contests, actionConfirmed, dataWrapper, response);
        privateMassAction.execute(contests, actionConfirmed, dataWrapper, response);
        featuredMassAction.execute(contests, actionConfirmed, dataWrapper, response);
    }
}

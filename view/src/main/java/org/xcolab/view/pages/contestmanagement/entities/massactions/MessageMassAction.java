package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageMassAction extends ContestMassActionAdapter {

    public MessageMassAction() {
        super("Message contributors in active phase");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalStateException {
        MassMessageBean massMessageBean = dataWrapper.getMassMessageBean();
        if (massMessageBean == null) {
            throw new IllegalStateException("The mass action has not been setup yet.");
        }
        // TODO: Remove intermediate solution.
        List<Long> contestIds =
                contests.stream().map(Contest::getContestPK).collect(Collectors.toList());

        ContestMassActionMethods.sendMassMessage(contestIds, massMessageBean, null);
    }
}

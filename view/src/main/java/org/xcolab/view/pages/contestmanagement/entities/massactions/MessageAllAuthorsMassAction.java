package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageAllAuthorsMassAction extends ContestMassActionAdapter {

    public MessageAllAuthorsMassAction() {
        super("Message all proposal authors");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) {
        MassMessageBean massMessageBean = dataWrapper.getMassMessageBean();
        if (massMessageBean == null) {
            throw new IllegalStateException("The mass action has not been setup yet.");
        }
        // TODO: Remove intermediate solution.
        List<Long> contestIds =
                contests.stream().map(Contest::getContestPK).collect(Collectors.toList());

        ContestMassActionMethods
                .sendMassMessageToAllProposalAuthors(contestIds, massMessageBean, null);
    }
}

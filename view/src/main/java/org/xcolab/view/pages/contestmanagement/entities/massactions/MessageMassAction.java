package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageMassAction extends ContestMassAction {

    public MessageMassAction() {
        super("Message contributors in active phase");
    }

    @Override
    void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper) {
        ContestMassActionMethods.sendMassMessage(contestOverviewWrapper.getSelectedContestIds(),
                contestOverviewWrapper.getMassMessageBean(), request);
    }
}

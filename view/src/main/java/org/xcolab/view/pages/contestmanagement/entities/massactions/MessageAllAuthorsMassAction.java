package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageAllAuthorsMassAction extends ContestMassAction {

    public MessageAllAuthorsMassAction() {
        super("Message all proposal authors");
    }

    @Override
    void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper) {
        ContestMassActionMethods.sendMassMessageToAllProposalAuthors(contestOverviewWrapper
                .getSelectedContestIds(), contestOverviewWrapper.getMassMessageBean(), request);
    }
}

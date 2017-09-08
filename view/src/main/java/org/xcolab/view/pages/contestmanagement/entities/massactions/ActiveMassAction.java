package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActiveMassAction extends ContestMassAction {

    public ActiveMassAction() {
        super("Active", "Prior");
    }

    @Override
    void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper)
            throws IOException, MassActionRequiresConfirmationException {
        Contest.setContestActive(true);
    }
}

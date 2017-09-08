package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMassAction extends ContestMassAction {

    public DeleteMassAction() {
        super("DELETE");
    }

    @Override
    void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper)
            throws IOException, MassActionRequiresConfirmationException {
        ContestMassActionMethods
                .deleteContest(contestOverviewWrapper.getSelectedContestIds(), true, request);
    }
}

package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteWithPhasesMassAction extends ContestMassAction {

    public DeleteWithPhasesMassAction() {
        super("Delete contests including phases");
    }

    @Override
    void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper)
            throws MassActionRequiresConfirmationException {
        ContestMassActionMethods
                .deleteContestwithPhases(contestOverviewWrapper.getSelectedContestIds(), true,
                        request);
    }
}

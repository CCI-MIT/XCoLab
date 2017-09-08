package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportPeopleInCurrentPhaseMassAction extends ContestMassAction {

    public ReportPeopleInCurrentPhaseMassAction() {
        super("Generate report of contributors in active phase");
    }

    @Override
    void execute(HttpServletRequest request, HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper) throws IOException {
        ContestMassActionMethods
                .reportOfPeopleInCurrentPhase(contestOverviewWrapper.getSelectedContestIds(),
                        response, request);
    }
}

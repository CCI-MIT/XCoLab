package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class DeleteWithPhasesMassAction extends AbstractContestMassAction {

    public DeleteWithPhasesMassAction() {
        super("Delete contests including phases");
    }

    @Override
    public void execute(List<ContestWrapper> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws MassActionRequiresConfirmationException {
        if (!actionConfirmed) {
            throw new MassActionRequiresConfirmationException();
        }

        for (ContestWrapper contest : contests) {
            Long contestId = contest.getId();
            StaticContestContext.getContestClient().deleteContest(contestId);
        }
    }
}

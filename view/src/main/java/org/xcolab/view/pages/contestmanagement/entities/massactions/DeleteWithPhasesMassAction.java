package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class DeleteWithPhasesMassAction extends AbstractContestMassAction {

    public DeleteWithPhasesMassAction() {
        super("Delete contests including phases");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws MassActionRequiresConfirmationException {
        if (!actionConfirmed) {
            throw new MassActionRequiresConfirmationException();
        }

        for (Contest contest : contests) {
            Long contestId = contest.getId();
            ContestClientUtil.deleteContest(contestId);
        }
    }
}

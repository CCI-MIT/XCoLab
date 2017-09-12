package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class DeleteMassAction extends ContestMassActionAdapter {

    public DeleteMassAction() {
        super("Delete contests");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws MassActionRequiresConfirmationException {
        if (!actionConfirmed) {
            throw new MassActionRequiresConfirmationException();
        }

        for (Contest contest : contests) {
            if (!contest.getIsSharedContestInForeignColab()) {
                ContestClientUtil.deleteContest(contest.getContestPK());
            }
        }
    }
}

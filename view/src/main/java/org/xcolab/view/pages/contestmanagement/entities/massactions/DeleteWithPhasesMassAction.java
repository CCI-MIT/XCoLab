package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteWithPhasesMassAction extends ContestMassActionAdapter {

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
            if (!contest.getIsSharedContestInForeignColab()) {
                Long contestId = contest.getContestPK();
                List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(contestId);
                for (ContestPhase contestPhase : contestPhases) {
                    ContestClientUtil.deleteContestPhase(contestPhase.getContestPhasePK());
                }
                ContestClientUtil.deleteContest(contestId);
            }
        }
    }
}

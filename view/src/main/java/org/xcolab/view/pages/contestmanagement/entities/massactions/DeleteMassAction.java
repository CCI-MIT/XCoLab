package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteMassAction extends ContestMassActionAdapter {

    public DeleteMassAction() {
        super("Delete contests");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed)
            throws MassActionRequiresConfirmationException {
        // TODO: Fix intermediate solution.
        List<Long> contestIds =
                contests.stream().map(Contest::getContestPK).collect(Collectors.toList());

        ContestMassActionMethods.deleteContest(contestIds, true, null);
    }
}

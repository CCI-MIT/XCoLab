package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;

import java.util.List;
import java.util.stream.Collectors;

public class FlagMassAction extends ContestMassActionAdapter {

    private ContestFlagTextToolTipBean contestFlagTextToolTipBean;

    public FlagMassAction(String displayName) {
        super(displayName);
    }

    @Override
    public void setup(MassActionDataWrapper dataWrapper) {
        contestFlagTextToolTipBean = dataWrapper.getContestFlagTextToolTipBean();
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed)
            throws IllegalStateException {
        if (contestFlagTextToolTipBean == null) {
            throw new IllegalStateException("The mass action has not been setup yet.");
        }
        // TODO: Fix intermediate solution.
        List<Long> contestIds =
                contests.stream().map(Contest::getContestPK).collect(Collectors.toList());
        ContestMassActionMethods.setFlag(contestIds, contestFlagTextToolTipBean, null);
    }
}

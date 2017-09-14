package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class FlagMassAction extends AbstractContestMassAction {

    public FlagMassAction() {
        super("Set contest flag");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalArgumentException {
        ContestFlagTextToolTipBean contestFlagTextToolTipBean =
                dataWrapper.getContestFlagTextToolTipBean();
        if (contestFlagTextToolTipBean == null) {
            throw new IllegalArgumentException("No contest flag text tool tip bean provided.");
        }

        for (Contest contest : contests) {
            contestFlagTextToolTipBean.persist(contest);
        }
    }
}

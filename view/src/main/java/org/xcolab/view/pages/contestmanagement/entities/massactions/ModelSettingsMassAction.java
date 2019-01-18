package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ModelSettingsMassAction extends AbstractContestMassAction {

    public ModelSettingsMassAction() {
        super("Set model settings");
    }

    @Override
    public void execute(List<ContestWrapper> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalArgumentException {
        ContestModelSettingsBean contestModelSettingsBean =
                dataWrapper.getContestModelSettingsBean();
        if (contestModelSettingsBean == null) {
            throw new IllegalArgumentException("No model settings bean provided");
        }

        for (ContestWrapper contest : contests) {
            contestModelSettingsBean.persist(contest);
        }
    }
}

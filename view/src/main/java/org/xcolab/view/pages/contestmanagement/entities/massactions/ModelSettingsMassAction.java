package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

public class ModelSettingsMassAction extends ContestMassActionAdapter {

    private ContestModelSettingsBean contestModelSettingsBean;

    public ModelSettingsMassAction() {
        super("Set model settings");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws IllegalStateException {
        ContestModelSettingsBean contestModelSettingsBean =
                dataWrapper.getContestModelSettingsBean();
        if (contestModelSettingsBean == null) {
            throw new IllegalStateException("The mass action has not been setup yet");
        }
        // TODO: Fix intermediate solution.
        List<Long> contestIds =
                contests.stream().map(Contest::getContestPK).collect(Collectors.toList());
        ContestMassActionMethods.setModelSettings(contestIds, contestModelSettingsBean, null);
    }
}

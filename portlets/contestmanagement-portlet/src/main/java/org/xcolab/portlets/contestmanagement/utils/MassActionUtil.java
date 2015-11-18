package org.xcolab.portlets.contestmanagement.utils;

import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;

/**
 * Created by Thomas on 10/23/2015.
 */
public class MassActionUtil {

    public static String getSelectedMassActionTitle(Long selectedMassAction) {
        String selectedMassActionTitle = "";
        Long selectedMassActionAbsolute = Math.abs(selectedMassAction);
        for (ContestMassActions contestMassAction : ContestMassActions.values()) {
            if (selectedMassActionAbsolute == contestMassAction.ordinal()) {
                if (selectedMassAction < 0) {
                    selectedMassActionTitle = contestMassAction.getReverseActionDisplayName();
                } else {
                    selectedMassActionTitle = contestMassAction.getActionDisplayName();
                }
                break;
            }
        }
        return selectedMassActionTitle;
    }
}

package org.xcolab.portlets.contestmanagement.utils;

import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;

public final class MassActionUtil {

    private MassActionUtil() { }

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

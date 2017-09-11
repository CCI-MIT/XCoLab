package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;

public interface MassActionDataWrapper {

    MassMessageBean getMassMessageBean();

    ContestModelSettingsBean getContestModelSettingsBean();

    ContestFlagTextToolTipBean getContestFlagTextToolTipBean();

    Long getMemberId();
}

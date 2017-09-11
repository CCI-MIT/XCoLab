package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;

public interface MassActionDataWrapper {

    public MassMessageBean getMassMessageBean();
    public ContestModelSettingsBean getContestModelSettingsBean();
    public ContestFlagTextToolTipBean getContestFlagTextToolTipBean();
    public Long getMemberId();
}

package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.ContestWrapper;

public class HideRibbonsMassAction extends SetContestPropertyMassAction {

    public HideRibbonsMassAction(boolean setValue) {
        super(setValue, "Hide contest ribbons", "Show contest ribbons");
    }

    @Override
    protected void setProperty(ContestWrapper contest, boolean setTrue) {
        contest.setHideRibbons(setTrue);
    }
}

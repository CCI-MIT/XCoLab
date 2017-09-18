package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;

public class HideRibbonsMassAction extends SetContestPropertyMassAction {

    public HideRibbonsMassAction(boolean setValue) {
        super(setValue, "Hide contest ribbons", "Show contest ribbons");
    }

    @Override
    protected void setProperty(Contest contest, boolean setTrue) {
        contest.setHideRibbons(setTrue);
    }
}

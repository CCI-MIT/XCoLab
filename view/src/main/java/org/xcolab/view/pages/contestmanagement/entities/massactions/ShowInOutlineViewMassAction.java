package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;

public class ShowInOutlineViewMassAction extends SetContestPropertyMassAction {

    public ShowInOutlineViewMassAction(boolean setValue) {
        super(setValue, "Show in outline view", "Hide in outline view");
    }

    @Override
    protected void setProperty(Contest contest, boolean setTrue) {
        contest.setShowInOutlineView(setTrue);
    }
}

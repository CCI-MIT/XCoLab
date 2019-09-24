package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

public class ShowInListViewMassAction extends SetContestPropertyMassAction {

    public ShowInListViewMassAction(boolean setValue) {
        super(setValue, "Show in list view", "Hide in list view");
    }

    @Override
    protected void setProperty(ContestWrapper contest, boolean setTrue) {
        contest.setShowInListView(setTrue);
    }
}

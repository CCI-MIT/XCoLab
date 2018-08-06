package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;

public class ShowInListViewMassAction extends SetContestPropertyMassAction {

    public ShowInListViewMassAction(boolean setValue) {
        super(setValue, "Show in list view", "Hide in list view");
    }

    @Override
    protected void setProperty(Contest contest, boolean setTrue) {
        contest.setShowInListView(setTrue);
    }
}

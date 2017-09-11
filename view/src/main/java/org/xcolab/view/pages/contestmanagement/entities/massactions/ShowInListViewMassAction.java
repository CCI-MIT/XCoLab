package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;

public class ShowInListViewMassAction extends SetContestPropertyMassAction {

    public ShowInListViewMassAction(boolean setValue) {
        super(setValue, "Show in list view", "Hide in list view");
    }

    @Override
    void setProperty(Contest contest, boolean setTrue) {
        contest.setShow_in_list_view(setTrue);
    }
}

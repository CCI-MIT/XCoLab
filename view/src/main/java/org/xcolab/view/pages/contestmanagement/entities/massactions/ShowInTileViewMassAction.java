package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

public class ShowInTileViewMassAction extends SetContestPropertyMassAction {

    public ShowInTileViewMassAction(boolean setValue) {
        super(setValue, "Show in tile view", "Hide in tile view");
    }

    @Override
    protected void setProperty(ContestWrapper contest, boolean setTrue) {
        contest.setShowInTileView(setTrue);
    }
}

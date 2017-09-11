package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;

public class ShowInTileViewMassAction extends SetContestPropertyMassAction {

    public ShowInTileViewMassAction(boolean setValue) {
        super(setValue, "Show in tile view", "Hide in tile view");
    }

    @Override
    void setProperty(Contest contest, boolean setTrue) {
        contest.setShow_in_tile_view(setTrue);
    }
}

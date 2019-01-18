package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

public class PrivateMassAction extends SetContestPropertyMassAction {

    public PrivateMassAction(boolean setValue) {
        super(setValue, "Private", "Public (not 'Private')");
    }

    @Override
    protected void setProperty(ContestWrapper contest, boolean setTrue) {
        contest.setContestPrivate(setTrue);
    }
}

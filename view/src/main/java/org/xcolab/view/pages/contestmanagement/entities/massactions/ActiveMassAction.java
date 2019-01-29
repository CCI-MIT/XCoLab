package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

public class ActiveMassAction extends SetContestPropertyMassAction {

    public ActiveMassAction(boolean setValue) {
        super(setValue, "Active", "Prior (not 'Active')");
    }

    @Override
    protected void setProperty(ContestWrapper contest, boolean setValue) {
        contest.setContestActive(setValue);
    }
}

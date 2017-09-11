package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;

public class ActiveMassAction extends SetContestPropertyMassAction {

    public ActiveMassAction(boolean setValue) {
        super(setValue, "Active", "Prior");
    }

    @Override
    void setProperty(Contest contest, boolean setValue) {
        contest.setContestActive(setValue);
    }
}

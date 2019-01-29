package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

public class FeaturedMassAction extends SetContestPropertyMassAction {

    public FeaturedMassAction(boolean setValue) {
        super(setValue, "Feature", "Remove Feature");
    }

    @Override
    protected void setProperty(ContestWrapper contest, boolean setTrue) {
        contest.setFeatured(setTrue);
    }
}

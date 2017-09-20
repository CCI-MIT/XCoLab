package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.pojo.Contest;

public class FeaturedMassAction extends SetContestPropertyMassAction {

    public FeaturedMassAction(boolean setValue) {
        super(setValue, "Feature", "Remove Feature");
    }

    @Override
    protected void setProperty(Contest contest, boolean setTrue) {
        contest.setFeatured_(setTrue);
    }
}

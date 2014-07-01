package org.xcolab.portlets.reporting.beans.activitiesbycontest;

import com.liferay.portal.model.User;

import java.util.List;

/**
* @author pdeboer
*         First created on 20/06/14 at 15:53
*/
public class UserActivityByContest {
    private final User user;
    private final List<ContestActivity> contestActivities;

    public UserActivityByContest(User user, List<ContestActivity> contestActivities) {
        this.user = user;
        this.contestActivities = contestActivities;
    }

    public List<ContestActivity> getContestActivities() {
        return contestActivities;
    }

    public User getUser() {
        return user;
    }
}

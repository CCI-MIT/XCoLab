package org.xcolab.view.pages.profile.wrappers;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.view.activityentry.ActivityEntryHelper;

import java.io.Serializable;
import java.util.Date;

public class UserActivityWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ActivityEntry activity;
    private String body;

    public UserActivityWrapper(ActivityEntry activity, ActivityEntryHelper activityEntryHelper) {
        this.activity = activity;


        if (this.activity != null) {
            body = activityEntryHelper.getActivityBody(this.activity);
            if (body != null) {
                body = body.trim().equals("") ? activity.getActivityEntryTitle() : body;
                body = body.replaceAll("c.my_sites[^\\\"]*",
                        "web/guest/member/-/member/userId/" + activity.getMemberId());
            }
        }
    }

    public Date getCreatedDate() {
        return new Date(activity.getCreateDate().getTime());
    }

    public String getBody() {
        return body;
    }

    public long getDaysAgo() {
        final int millisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = activity.getCreateDate().getTime() / millisecondsInDay;
        long daysNow = new Date().getTime() / millisecondsInDay;
        return daysNow - createDay;
    }
}

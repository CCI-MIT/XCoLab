package org.xcolab.view.pages.profile.wrappers;

import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.view.activityentry.ActivityEntryHelper;

import java.io.Serializable;
import java.util.Date;

public class UserActivityWrapper implements Serializable {

    private static final int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
    private static final long serialVersionUID = 1L;

    private final IActivityEntry activity;
    private String body;

    public UserActivityWrapper(IActivityEntry activity, ActivityEntryHelper activityEntryHelper) {
        this.activity = activity;

        if (this.activity != null) {
            body = activityEntryHelper.getActivityBody(this.activity);
            if (body != null) {
                body = body.replaceAll("c.my_sites[^\\\"]*",
                        "web/guest/member/-/member/userId/" + activity.getUserId());
            }
        }
    }

    public Date getCreatedAt() {
        return new Date(activity.getCreatedAt().getTime());
    }

    public String getBody() {
        return body;
    }

    public long getDaysAgo() {
        long createDay = activity.getCreatedAt().getTime() / MILLISECONDS_IN_DAY;
        long daysNow = new Date().getTime() / MILLISECONDS_IN_DAY;
        return daysNow - createDay;
    }
}

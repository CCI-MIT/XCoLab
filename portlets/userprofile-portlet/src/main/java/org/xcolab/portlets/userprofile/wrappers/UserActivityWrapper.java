package org.xcolab.portlets.userprofile.wrappers;

import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.activities.pojo.ActivityEntry;

import java.io.Serializable;
import java.util.Date;

public class UserActivityWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ActivityEntry activity;
    private String body;

    public UserActivityWrapper(ActivityEntry activity, ThemeDisplay themeDisplay) {
        this.activity = activity;


        if (this.activity != null) {
            body = activity.getActivityEntryBody();
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

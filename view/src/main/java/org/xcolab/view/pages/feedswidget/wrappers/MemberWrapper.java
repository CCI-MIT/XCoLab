package org.xcolab.view.pages.feedswidget.wrappers;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.time.DurationFormatter;

import java.io.Serializable;
import java.util.Date;

public class MemberWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
    private Member user;

    private int activitiesCount;
    private ActivityEntry activity;
    private String lastActivityBody;
    private Date lastActivityDate;

    public MemberWrapper(Member user, int activitiesCount) {
        this.user = user;
        this.activitiesCount = activitiesCount;
    }
    
    
    public MemberWrapper(Member user, ActivityEntry activity) {
        this.user = user;
        this.activity = activity;
        if (activity != null) {
            lastActivityBody = activity.getActivityEntryBody();
            lastActivityDate = new Date(activity.getCreateDate().getTime());
        }
    }
    
    public MemberWrapper(ActivityEntry activity) {
        this.activity = activity;
        if (activity != null) {
            lastActivityBody = this.activity.getActivityEntryBody();
            lastActivityDate = new Date(activity.getCreateDate().getTime());
            try {
                user = MembersClient.getMember(activity.getMemberId());
            } catch (MemberNotFoundException ignored) {
            }
        }
    }

    public int getActivitiesCount() {
        return activitiesCount;
    }
    
    public String getScreenName() {
        return user.getScreenName();
    }
    
    public String getLastActivity() {
        return "aaa";
    }
    
    public Long getUserId() {
        return user.getUserId();
    }

    public String getLastActivityBody() {
        return lastActivityBody;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public String getLastActivityDateAgo() {
        return DurationFormatter.forRequestLocale().format(lastActivityDate);
    }
}

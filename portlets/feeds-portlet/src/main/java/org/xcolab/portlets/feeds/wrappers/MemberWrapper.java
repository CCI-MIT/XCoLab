package org.xcolab.portlets.feeds.wrappers;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.ocpsoft.pretty.time.PrettyTime;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

import java.io.Serializable;
import java.util.Date;

import javax.portlet.PortletRequest;

public class MemberWrapper implements Serializable{
	private static final long serialVersionUID = 1L;
    private Member user;

    private int activitiesCount;
    private ActivityEntry activity;
    private String lastActivityBody;
    private Date lastActivityDate;
    private static PrettyTime timeAgoConverter = new PrettyTime();
    private PortletRequest request;
    
    public MemberWrapper(Member user, int activitiesCount, PortletRequest request) {
        this.user = user;
        this.activitiesCount = activitiesCount;
        this.request = request;
    }
    
    
    public MemberWrapper(Member user, ActivityEntry activity, PortletRequest request) {
        this.user = user;
        this.activity = activity;
        if (activity != null) {

            lastActivityBody = activity.getActivityEntryBody();

        }
        lastActivityDate = new Date(activity.getCreateDate().getTime());
        this.request = request;
    }
    
    public MemberWrapper(ActivityEntry activity, PortletRequest request) throws PortalException, SystemException {
        this.activity = activity;
        if (activity != null) {
            lastActivityBody = this.activity.getActivityEntryBody();
        }
        lastActivityDate = new Date(activity.getCreateDate().getTime());
        try {
            user = MembersClient.getMember(activity.getMemberId());
        } catch (MemberNotFoundException ignored) {
        }

        this.request = request;
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
        return timeAgoConverter.format(lastActivityDate);
    }
    
}

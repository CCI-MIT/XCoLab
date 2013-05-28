package org.xcolab.portlets.feeds.members;

import java.io.Serializable;
import java.util.Date;

import org.xcolab.portlets.feeds.Helper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.ocpsoft.pretty.time.PrettyTime;

public class MemberWrapper implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
    private int activitiesCount;
    private SocialActivity activity;
    private String lastActivityBody;
    private Date lastActivityDate;
    private static PrettyTime timeAgoConverter = new PrettyTime();
    
    public MemberWrapper(User user, int activitiesCount) {
        this.user = user;
        this.activitiesCount = activitiesCount;
    }
    
    
    public MemberWrapper(User user, SocialActivity activity) {
        this.user = user;
        this.activity = activity;
        if (activity != null) {
            SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, Helper.getThemeDisplay());
            lastActivityBody = entry != null ? entry.getBody() : null;
            if (lastActivityBody == null || lastActivityBody.trim().length() == 0) {
                lastActivityBody = entry != null ? entry.getTitle() : null;
            }
        }
        lastActivityDate = new Date(activity.getCreateDate());
    }
    
    public MemberWrapper(SocialActivity activity) throws PortalException, SystemException {
        this.activity = activity;
        if (activity != null) {
            SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, Helper.getThemeDisplay());
            lastActivityBody = entry != null ? entry.getBody() : null;
            if (lastActivityBody == null || lastActivityBody.trim().length() == 0) {
                lastActivityBody = entry != null ? entry.getTitle() : null;
            }
            
        }
        lastActivityDate = new Date(activity.getCreateDate());
        user = UserLocalServiceUtil.getUser(activity.getUserId());
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

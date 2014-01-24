package org.xcolab.portlets.feeds.wrappers;

import java.io.Serializable;
import java.util.Date;

import javax.portlet.PortletRequest;

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
	private static final long serialVersionUID = 1L;
	private User user;
    private int activitiesCount;
    private SocialActivity activity;
    private String lastActivityBody;
    private Date lastActivityDate;
    private static PrettyTime timeAgoConverter = new PrettyTime();
    private PortletRequest request;
    
    public MemberWrapper(User user, int activitiesCount, PortletRequest request) {
        this.user = user;
        this.activitiesCount = activitiesCount;
        this.request = request;
    }
    
    
    public MemberWrapper(User user, SocialActivity activity, PortletRequest request) {
        this.user = user;
        this.activity = activity;
        if (activity != null) {
            SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, Helper.getThemeDisplay(request));
            lastActivityBody = entry != null ? entry.getBody() : null;
            if (lastActivityBody == null || lastActivityBody.trim().length() == 0) {
                lastActivityBody = entry != null ? entry.getTitle() : null;
            }
        }
        lastActivityDate = new Date(activity.getCreateDate());
        this.request = request;
    }
    
    public MemberWrapper(SocialActivity activity, PortletRequest request) throws PortalException, SystemException {
        this.activity = activity;
        if (activity != null) {
            SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, Helper.getThemeDisplay(request));
            lastActivityBody = entry != null ? entry.getBody() : null;
            if (lastActivityBody == null || lastActivityBody.trim().length() == 0) {
                lastActivityBody = entry != null ? entry.getTitle() : null;
            }
            
        }
        lastActivityDate = new Date(activity.getCreateDate());
        user = UserLocalServiceUtil.getUser(activity.getUserId());
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

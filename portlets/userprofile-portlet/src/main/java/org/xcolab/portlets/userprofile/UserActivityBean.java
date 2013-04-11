package org.xcolab.portlets.userprofile;

import java.util.Date;

import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

public class UserActivityBean {
    
    private SocialActivity activity;
    private SocialActivityFeedEntry activityFeedEntry;
    private String body;
    
    public UserActivityBean(SocialActivity activity) {
        this.activity = activity;

        activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(activity,
                Helper.getThemeDisplay());
        
        if (activityFeedEntry != null) {
            body = activityFeedEntry.getBody();
            body = body != null && body.trim().equals("") ? activityFeedEntry.getTitle() : body;
            
            body = body.replaceAll("c.my_sites[^\\\"]*", "web/guest/member/-/member/userId/" + activity.getUserId());
        }
    }
    
    public Date getCreatedDate() {
        return new Date(activity.getCreateDate());
    }
    
    public String getBody() {
        return body;
    }
    
    public long getDaysAgo() {
        final int milisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = activity.getCreateDate() / milisecondsInDay;
        long daysNow = new Date().getTime() / milisecondsInDay;
        return daysNow - createDay;
    }

}

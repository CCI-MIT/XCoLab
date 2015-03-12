package org.xcolab.portlets.userprofile.wrappers;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

import java.io.Serializable;
import java.util.Date;

public class
        UserActivityWrapper implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private final static Log _log = LogFactoryUtil.getLog(UserActivityWrapper.class);
	private SocialActivity activity;
    private SocialActivityFeedEntry activityFeedEntry;
    private String body;

    public UserActivityWrapper(SocialActivity activity, ThemeDisplay themeDisplay) {
        this.activity = activity;

        try {
            activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, themeDisplay);
        } catch(Exception e){
            _log.warn("Unable to interpret activity", e);
        }
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

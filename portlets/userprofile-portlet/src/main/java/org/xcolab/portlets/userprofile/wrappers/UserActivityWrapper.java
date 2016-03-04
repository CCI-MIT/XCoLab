package org.xcolab.portlets.userprofile.wrappers;

import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;

import java.io.Serializable;
import java.util.Date;

public class UserActivityWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    private final SocialActivity activity;
    private String body;

    public UserActivityWrapper(SocialActivity activity, ThemeDisplay themeDisplay) {
        this.activity = activity;

        SocialActivityFeedEntry activityFeedEntry =
                SocialActivityInterpreterLocalServiceUtil.interpret(activity, themeDisplay);
        if (activityFeedEntry != null) {
            body = activityFeedEntry.getBody();
            if (body != null) {
                body = body.trim().equals("") ? activityFeedEntry.getTitle() : body;
                body = body.replaceAll("c.my_sites[^\\\"]*",
                        "web/guest/member/-/member/userId/" + activity.getUserId());
            }
        }
    }

    public Date getCreatedDate() {
        return new Date(activity.getCreateDate());
    }

    public String getBody() {
        return body;
    }

    public long getDaysAgo() {
        final int millisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = activity.getCreateDate() / millisecondsInDay;
        long daysNow = new Date().getTime() / millisecondsInDay;
        return daysNow - createDay;
    }
}

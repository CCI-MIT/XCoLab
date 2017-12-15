package org.xcolab.view.widgets.feeds.wrappers;

import org.xcolab.client.activities.enums.ContestActivityType;
import org.xcolab.client.activities.enums.ProposalActivityType;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.util.time.DurationFormatter;

import java.io.Serializable;
import java.util.Date;

public class SocialActivityWrapper implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

    private final ActivityEntry activity;
    private final int daysBetween;
    private final boolean indicateNewDate;
    private final boolean odd;
    private long daysAgo;
    private String body;

    public SocialActivityWrapper(ActivityEntry activity, int daysBetween, boolean indicateNewDate,
            boolean odd, int maxLength, String actBody) {
        this.activity = activity;

        this.daysBetween = daysBetween;
        this.indicateNewDate = indicateNewDate;

        long createDay = activity.getCreateDate().getTime() / MILLISECONDS_PER_DAY;
        long daysNow = new Date().getTime() / MILLISECONDS_PER_DAY;
        daysAgo = daysNow - createDay;
        body = actBody;
        if (body != null) {
            body = body.replaceAll("c.my_sites[^\\\"]*",
                    "web/guest/member/-/member/userId/" + activity.getMemberId());
        }

        this.odd = odd;
    }


    public String getBody() {
        return body;
    }

    public boolean isToday() {
        return daysBetween == 0;
    }

    public boolean isYesterday() {
        return daysBetween == 1;
    }

    public Date getCreateDate() {
        return new Date(activity.getCreateDate().getTime());
    }

    public boolean getIndicateNewDate() {
        return indicateNewDate;
    }

    public long getDaysAgo() {
        return daysAgo;
    }

    public void setDaysAgo(long daysAgo) {
        this.daysAgo = daysAgo;
    }

    public String getActivityDateAgo() {
        return DurationFormatter.forRequestLocale().format(activity.getCreateDate());
    }

    public boolean isOdd() {
        return odd;
    }

    public String getImageClassName() {
        switch (activity.getActivityCategoryEnum()) {
            case MEMBER:
                return "new_user";
            case DISCUSSION_THREAD:
                return "comment";
            case CONTEST:
                switch ((ContestActivityType) activity.getActivityTypeEnum()) {
                    case PROPOSAL_CREATED:
                        return "new";
                    case COMMENT_ADDED:
                        return "comment";
                    default: return "";
                }
            case PROPOSAL:
                switch ((ProposalActivityType) activity.getActivityTypeEnum()) {
                    case UPDATED:
                    case MEMBER_ADDED:
                    case MEMBER_REMOVED:
                        return "edit";
                    case VOTE_ADDED:
                    case VOTE_SWITCHED:
                    case VOTE_RETRACTED:
                    case SUPPORT_ADDED:
                    case SUPPORT_REMOVED:
                        return "up";
                    case COMMENT_ADDED:
                        return "comment";
                    default: return "";
                }
            default: return "";
        }
    }
}

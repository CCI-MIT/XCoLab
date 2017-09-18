package org.xcolab.view.widgets.feeds.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.time.DurationFormatter;
import org.xcolab.view.util.entity.activityEntry.DiscussionActivitySubType;
import org.xcolab.view.util.entity.activityEntry.MemberSubActivityType;
import org.xcolab.view.util.entity.activityEntry.ProposalActivitySubType;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SocialActivityWrapper implements Serializable {

    private final static Logger _log = LoggerFactory.getLogger(SocialActivityWrapper.class);
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

    public ActivityType getType() {
        return ActivityType
                .getType(activity.getPrimaryType() + "", activity.getSecondaryType() + "");
    }


    public enum ActivityType {
        VOTE("up", ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                + ProposalActivitySubType.PROPOSAL_VOTE.getSecondaryTypeId(),
                ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                        + ProposalActivitySubType.PROPOSAL_VOTE_RETRACT.getSecondaryTypeId(),
                ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                        + ProposalActivitySubType.PROPOSAL_VOTE_SWITCH.getSecondaryTypeId(),
                ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                        + ProposalActivitySubType.PROPOSAL_SUPPORTER_ADDED.getSecondaryTypeId(),
                ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                        + ProposalActivitySubType.PROPOSAL_SUPPORTER_REMOVED.getSecondaryTypeId()),
        EDIT("edit", ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                + ProposalActivitySubType.PROPOSAL_ATTRIBUTE_UPDATE.getSecondaryTypeId(),
                ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                        + ProposalActivitySubType.PROPOSAL_MEMBER_ADDED.getSecondaryTypeId(),
                ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                        + ProposalActivitySubType.PROPOSAL_MEMBER_REMOVED.getSecondaryTypeId()),
        NEW("new", ActivityEntryType.PROPOSAL.getPrimaryTypeId() + ""
                + ProposalActivitySubType.PROPOSAL_CREATED.getSecondaryTypeId()),
        COMMENT("comment", ActivityEntryType.DISCUSSION.getPrimaryTypeId() + ""
                + DiscussionActivitySubType.DISCUSSION_ADDED.getSecondaryTypeId(),
                ActivityEntryType.DISCUSSION.getPrimaryTypeId() + ""
                        + DiscussionActivitySubType.DISCUSSION_CATEGORY_ADDED.getSecondaryTypeId(),
                ActivityEntryType.DISCUSSION.getPrimaryTypeId() + ""
                        + DiscussionActivitySubType.DISCUSSION_PROPOSAL_COMMENT
                        .getSecondaryTypeId(), ActivityEntryType.DISCUSSION.getPrimaryTypeId() + ""
                + DiscussionActivitySubType.DISCUSSION_FORUM_COMMENT.getSecondaryTypeId()),
        USER("new_user", ActivityEntryType.MEMBER.getPrimaryTypeId() + ""
                + MemberSubActivityType.MEMBER_JOINED.getSecondaryTypeId());

        private final static Map<String, ActivityType> activityMap = new HashMap<>();
        private final static ActivityType defaultType = COMMENT;

        static {
            for (ActivityType t : ActivityType.values()) {
                for (String clasz : t.classes) {
                    activityMap.put(clasz, t);
                }
            }
        }

        private final String[] classes;
        private final String displayName;

        ActivityType(String displayName, String... classes) {
            this.displayName = displayName;
            this.classes = classes;
        }

        public static ActivityType getType(String clasz, String type) {
            ActivityType t = activityMap.get(clasz + type);
            return t == null ? defaultType : t;
        }

        public String getDisplayName() {
            return displayName;
        }

    }
}

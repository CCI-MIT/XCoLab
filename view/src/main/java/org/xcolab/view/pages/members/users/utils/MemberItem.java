package org.xcolab.view.pages.members.users.utils;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.MemberCategoryWrapper;
import org.xcolab.client.user.pojo.wrapper.RoleWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MemberItem implements Serializable {
    private final long activityCount;
    private final Date joinDate;
    private final Long userId;
    private final String displayName;
    private final String screenName;
    private final int points;
    private final MemberCategoryWrapper memberCategory;

    public MemberItem(UserWrapper member, String memberCategoryParam) {
        userId = member.getId();
        activityCount = StaticActivityContext.getActivityClient().countActivities(member.getId(), null);
        displayName = member.getDisplayName();
        screenName = member.getScreenName();
        joinDate = member.getCreatedAt();
        points = StaticUserContext.getUserClient().getMemberMaterializedPoints(userId);

        if (StringUtils.isNotEmpty(memberCategoryParam)) {
            memberCategory = StaticUserContext.getUserCategoryClient().getMemberCategory(memberCategoryParam);
        } else {
            List<RoleWrapper> roles = StaticUserContext.getUserClient().getUserRoles(userId,null);
            memberCategory = StaticUserContext.getUserClient().getHighestCategory(roles);
        }
    }

    public long getActivityCount() {
        return activityCount;
    }

    public String getActivityCountFormatted() {
        return String.format("%,d", activityCount);
    }

    public int getPoints() {
        return points;
    }

    public String getPointsFormatted() {
        return String.format("%,d", points);
    }

    public MemberCategoryWrapper getMemberCategory() {
        return memberCategory;
    }
    
    public String getMemberSince() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(joinDate);
    }
    
    public Comparable getColumnVal(MembersListColumns column) {
        switch (column) {
            case ACTIVITY:
                return activityCount;
            case MEMBER_CATEGORY:
                return memberCategory;
            case MEMBER_SINCE:
                return joinDate;
            default:
                return displayName;
        }
    }

    public Long getuserId() {
        return userId;
    }
    
    public String getDisplayName() {
    	return displayName;
    }

    public String getScreenName() {
        return screenName;
    }
}

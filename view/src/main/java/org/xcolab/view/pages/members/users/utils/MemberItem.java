package org.xcolab.view.pages.members.users.utils;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.user.pojo.MemberCategory;
import org.xcolab.client.user.pojo.Role;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MemberItem implements Serializable {
    private final long activityCount;
    private final Date joinDate;
    private final Long userId;
    private final String screenName;
    private final int points;
    private final MemberCategory memberCategory;

    public MemberItem(Member member, String memberCategoryParam) {

        userId = member.getId();
        activityCount = ActivitiesClientUtil.countActivities(member.getId(), null);
        screenName = member.getDisplayName();
        joinDate = member.getCreatedAt();
        points = MembersClient.getMemberMaterializedPoints(userId);

        if (StringUtils.isNotEmpty(memberCategoryParam)) {
            memberCategory = MembersClient.getMemberCategory(memberCategoryParam);
        } else {
            List<Role> roles = MembersClient.getMemberRoles(userId);
            memberCategory = MembersClient.getHighestCategory(roles);
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

    public MemberCategory getMemberCategory() {
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
                return screenName;
        }
    }

    public Long getuserId() {
        return userId;
    }
    
    public String getScreenName() {
    	return screenName;
    }
}

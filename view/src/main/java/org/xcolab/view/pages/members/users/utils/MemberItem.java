package org.xcolab.view.pages.members.users.utils;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MemberItem implements Serializable {
    private final long activityCount;
    private final Date joinDate;
    private final Long memberId;
    private final String screenName;
    private final int points;
    private final MemberCategory memberCategory;

    public MemberItem(Member member, String memberCategoryParam) {

        memberId = member.getId_();
        activityCount = ActivitiesClientUtil.countActivities(member.getId_(), null);
        screenName = member.getDisplayName();
        joinDate = member.getCreateDate();
        points = MembersClient.getMemberMaterializedPoints(memberId);

        if (StringUtils.isNotEmpty(memberCategoryParam)) {
            memberCategory = MembersClient.getMemberCategory(memberCategoryParam);
        } else {
            List<Role_> roles = MembersClient.getMemberRoles(memberId);
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

    public Long getMemberId() {
        return memberId;
    }
    
    public String getScreenName() {
    	return screenName;
    }
}

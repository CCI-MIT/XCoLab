package org.xcolab.view.pages.members.users.utils;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Role_;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MemberItem implements Serializable {
    private MemberRole memberRole;
    private long activityCount;
    private Date joinDate;
    private Long userId;
    private String screenName;
    private int points;

    public MemberItem(Member user, String memberCategoryParam) {

        userId = user.getId_();
        activityCount = MembersClient.getMemberActivityCount(userId);
        screenName = user.getScreenName();
        joinDate = user.getCreateDate();
        points = MembersClient.getMemberMaterializedPoints(userId);

        if (memberCategoryParam != null && memberCategoryParam.compareTo("") != 0) {
            switch (memberCategoryParam){
                case "Member":
                    memberRole = MemberRole.MEMBER;
                    break;
                case "Catalyst":
                    memberRole = MemberRole.CATALYST;
                    break;
                case "Fellow":
                    memberRole = MemberRole.FELLOW;
                    break;
                case "Impact Assessment Fellow":
                    memberRole = MemberRole.IMPACT_ASSESSMENT_FELLOW;
                    break;
                case "Advisor":
                    memberRole = MemberRole.ADVISOR;
                    break;
                case "Expert":
                    memberRole = MemberRole.EXPERT;
                    break;
                case "Contest Manager":
                    memberRole = MemberRole.CONTEST_MANAGER;
                    break;
                case "Judge":
                case "Judges":
                    memberRole = MemberRole.JUDGE;
                    break;
                case "Staff":
                    memberRole = MemberRole.STAFF;
            }
        } else {
            List<Role_> roles = MembersClient.getMemberRoles(userId);
            try {
                memberRole = MemberRole.getHighestRole(roles);
            } catch (MemberRole.NoSuchMemberRoleException ignored) {
            }
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
    
    public MemberRole getMemberRole() {
        return memberRole;
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
                return memberRole;
            case MEMBER_SINCE:
                return joinDate;
            default:
                return screenName;
        }
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public String getScreenName() {
    	return screenName;
    }
}

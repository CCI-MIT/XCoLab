package org.xcolab.portlets.users.utils;

import com.ext.portlet.service.PointsLocalServiceUtil;
import com.ext.portlet.service.Xcolab_UserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.enums.MemberRole;
import org.xcolab.pojo.User_;

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

    public MemberItem(User_ user, String memberCategoryParam) throws PortalException, SystemException {

        userId = user.getUserId();
        activityCount = Xcolab_UserLocalServiceUtil.getUserActivityCount(userId);
        screenName = user.getScreenName();
        joinDate = user.getCreateDate();
        points = PointsLocalServiceUtil.getUserMaterializedPoints(userId);

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
            List<Role> roles = UserLocalServiceUtil.getUser(userId).getRoles();
            memberRole = MemberRole.getHighestRole(roles);
        }
    }

    public long getActivityCount() throws SystemException {
        return activityCount;
    }

    public String getActivityCountFormatted() throws SystemException {
        return String.format("%,d", activityCount);
    }

    public int getPoints() {
        return points;
    }

    public String getPointsFormatted() throws SystemException {
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

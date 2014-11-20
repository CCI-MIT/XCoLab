package org.xcolab.portlets.users.utils;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class UserItem implements Serializable {
    private MemberCategory category;
    private int activityCount;
    private Date joinDate;
    private Long userId;
    private String screenName;

    public UserItem(User user) throws PortalException, SystemException {

        userId = user.getUserId();
        activityCount = ActivityUtil.getActivitiesCount(userId);
        screenName = user.getScreenName();
        joinDate = user.getCreateDate();

        if (user.getRoles().size() > 0) {

            MemberCategory currentCat = MemberCategory.MEMBER;
            category = MemberCategory.MEMBER;

            for (Role role: user.getRoles()) {
                String roleName = role.getName();

                for (MemberCategory memberCategory : MemberCategory.values())

                    if (Arrays.asList(memberCategory.getRoleNames()).contains(roleName)) {
                        currentCat = memberCategory;
                        break;
                    }

                if (currentCat.ordinal() > category.ordinal()) {
                    category = currentCat;
                }
            }

            if (category == MemberCategory.MODERATOR) category = MemberCategory.STAFF;

        }
    }

    public int getActivityCount() throws SystemException {
        return activityCount;
    }
    
    public MemberCategory getCategory() {
        return category;    
    }
    
    public String getMemberSince() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        return simpleDateFormat.format(joinDate);
    }
    
    public Comparable getColumnVal(MembersListColumns column) {
        switch (column) {
            case ACTIVITY:
                return activityCount;
            case MEMBER_CATEGORY:
                return category;
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

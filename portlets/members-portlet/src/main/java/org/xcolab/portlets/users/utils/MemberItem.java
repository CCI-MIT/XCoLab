package org.xcolab.portlets.users.utils;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.model.Role_;
import com.ext.portlet.model.User_;
import com.ext.portlet.service.Role_LocalServiceUtil;
import com.ext.portlet.service.User_LocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MemberItem implements Serializable {
    private MemberCategory category;
    private int activityCount;
    private Date joinDate;
    private Long userId;
    private String screenName;

    public MemberItem(User_ user, String memberCategoryParam) throws PortalException, SystemException {

        userId = user.getUserId();
        activityCount = User_LocalServiceUtil.getUserActivityCount(userId).get(0).intValue();
        screenName = user.getScreenName();
        joinDate = user.getCreateDate();


        if (memberCategoryParam!=null && memberCategoryParam.compareTo("")!=0)
        {
            switch (memberCategoryParam){
                case "Member":
                    category=MemberCategory.MEMBER;
                    break;
                case "Catalyst":
                    category=MemberCategory.CATALYST;
                    break;
                case "Fellow":
                    category=MemberCategory.FELLOW;
                    break;
                case "Advisor":
                    category=MemberCategory.ADVISOR;
                    break;
                case "Expert":
                    category=MemberCategory.EXPERT;
                    break;
                case "Judges":
                    category=MemberCategory.JUDGES;
                    break;
                case "Staff":
                    category=MemberCategory.STAFF;
            }
        }

        else {

            List<Role> roles = UserLocalServiceUtil.getUser(userId).getRoles();
            if (roles.size() > 0) {

                MemberCategory currentCat = MemberCategory.MEMBER;
                category = MemberCategory.MEMBER;


                for (Role role: roles) {
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
    }

    public int getActivityCount() throws SystemException {
        return activityCount;
    }
    
    public MemberCategory getCategory() {
        return category;    
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

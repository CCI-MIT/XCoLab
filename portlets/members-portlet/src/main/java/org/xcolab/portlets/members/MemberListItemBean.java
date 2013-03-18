package org.xcolab.portlets.members;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class MemberListItemBean {
    private MemberCategory category;
    private String realName;
    private int activityCount;
    private Date joinDate;
    private Long userId;
    
    public MemberListItemBean(Document userDoc) throws SystemException, NumberFormatException, PortalException, ParseException {
        userId = Long.parseLong(userDoc.get("userId"));
        activityCount = SocialActivityLocalServiceUtil.getUserActivitiesCount(userId);
        realName = userDoc.get("realName");
        String screenName = userDoc.get("screenName");
        String firstName = userDoc.get("firstName");

        String firstPart = realName.substring(0, realName.length() / 2).trim();
        String secondPart = realName.substring(realName.length() / 2).trim();
        if (firstPart.equals(secondPart)) {
            realName = firstPart;
        }
        
        if (StringUtils.isBlank(realName)) {
            if (StringUtils.isNotBlank(firstName)) {
                realName = firstName;
            }
            else {
                realName = screenName;
            }
        }
        
        joinDate = userDoc.getDate("joinDate");

        if (userDoc.getValues("memberCategory").length > 0) {
            try {
            	String[] categoriesStr = userDoc.getValues("memberCategory");
            	MemberCategory currentCat = MemberCategory.MEMBER;
            	category = MemberCategory.MEMBER;
            	for (String categoryStr: userDoc.getValues("memberCategory")) {
            		currentCat = MemberCategory.valueOf(categoryStr);
            		if (currentCat.ordinal() > category.ordinal()) {
            			category = currentCat;
            		}
            	}
            	
            	if (category == MemberCategory.MODERATOR) category = MemberCategory.STAFF;
            }
            catch (java.lang.IllegalArgumentException e) {
                // ignore
            }
        }
    }
    
    public MemberListItemBean(Document userDoc, MemberCategory categoryFilter) throws NumberFormatException, 
    SystemException, PortalException, ParseException {
        this(userDoc);
        category = categoryFilter;
    }

    public String getRealName() {
        return realName;
    }
    
    public int getActivityCount() throws SystemException {
        return activityCount;
    }
    
    public MemberCategory getCategory() {
        return category;    
    }
    
    public Date getMemberSince() {
        return joinDate;
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
                return realName;
        }
    }
    
    public Long getUserId() {
        return userId;
    }
    

}

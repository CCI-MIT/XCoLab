package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.contests.ContestStatus;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.xcolab.enums.MemberRole;
import org.xcolab.interfaces.TabContext;
import org.xcolab.interfaces.TabPermissions;
import org.xcolab.wrapper.ContestWrapper;

import javax.portlet.PortletRequest;
import java.util.Date;
import java.util.List;
/**
 * Created by Thomas on 2/9/2015.
 */
public class ContestPermissions implements TabPermissions {
    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final String primKey;
    private final User user;
    private final long scopeGroupId;
    private ContestWrapper contestWrapper;
    private boolean isUserNotLoggedIn;

    public ContestPermissions(PortletRequest request, Contest contest) throws PortalException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        permissionChecker = themeDisplay.getPermissionChecker();
        portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
        primKey = themeDisplay.getPortletDisplay().getResourcePK();
        scopeGroupId = themeDisplay.getScopeGroupId();
        user = themeDisplay.getUser();
        isUserNotLoggedIn = user.isDefaultUser();
        contestWrapper = new ContestWrapper(contest);
    }

    public boolean getCanRole(MemberRole role){
        if(isUserNotLoggedIn) return false;

        try {
            return contestWrapper.getHasUserRoleInContest(user, role.getPrintName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getIsOwner(){
        if(isUserNotLoggedIn) return false;

        if(contestWrapper != null && user != null) {
            return contestWrapper.getAuthorId() == user.getUserId();
        }
        return false;
    }

    public boolean getCanAdmin() {
        if(isUserNotLoggedIn) return false;
        return permissionChecker.isOmniadmin();
    }

    public boolean getCanStaff() {
        if(isUserNotLoggedIn) return false;
        try {
            return RoleLocalServiceUtil.hasUserRole(user.getUserId(), MemberRole.STAFF.getRoleId());
        } catch (Exception e) {
        }
        return false;
    }

    public boolean getCanEdit(){
        if(isUserNotLoggedIn) return false;
        // guests aren't allowed to edit
        if (user.isDefaultUser()) 
            return false;
        
        if (getCanAdminAll()) 
            return true;

        return true;
    }
    
    public boolean getCanDelete(){
        if(isUserNotLoggedIn) return false;
        if (user.isDefaultUser()) 
            return false;
        
        if (getCanAdminAll()) 
            return true;

        return true;
    }
    
    public boolean getCanCreate() {
        // TODO check who needs this
        if(isUserNotLoggedIn) return false;
        // guests aren't allowed to edit
        if (user.isDefaultUser())
            return false;

        return true;
    }


    public boolean getCanAdminAll() {
        return permissionChecker.hasPermission(scopeGroupId, portletId, primKey, "ADMIN_ALL");
    }

}

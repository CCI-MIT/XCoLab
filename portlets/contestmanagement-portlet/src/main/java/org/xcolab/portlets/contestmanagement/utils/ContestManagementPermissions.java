package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.enums.MemberRole;
import org.xcolab.interfaces.TabPermissions;
import org.xcolab.wrapper.ContestWrapper;

import javax.portlet.PortletRequest;

/**
 * Created by Thomas on 2/9/2015.
 */
public class ContestManagementPermissions implements TabPermissions {
    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final String primKey;
    private final User user;
    private final long scopeGroupId;
    private boolean isUserNotLoggedIn;

    public ContestManagementPermissions(PortletRequest request) throws PortalException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        permissionChecker = themeDisplay.getPermissionChecker();
        portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
        primKey = themeDisplay.getPortletDisplay().getResourcePK();
        scopeGroupId = themeDisplay.getScopeGroupId();
        user = themeDisplay.getUser();
        isUserNotLoggedIn = user.isDefaultUser();
    }

    public boolean getCanRole(MemberRole role){
        if(isUserNotLoggedIn) return false;
        try {
            return RoleLocalServiceUtil.hasUserRole(user.getUserId(), role.getRoleId());
        } catch (Exception e) {
        }
        return false;
    }

    public boolean getIsOwner(){
        // TODO check who needs this
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
        // TODO check who needs this
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

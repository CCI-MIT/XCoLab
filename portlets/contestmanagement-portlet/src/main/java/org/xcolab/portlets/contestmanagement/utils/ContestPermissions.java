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
import org.xcolab.wrappers.BaseContestWrapper;

import javax.portlet.PortletRequest;
/**
 * Created by Thomas on 2/9/2015.
 */
public class ContestPermissions implements TabPermissions {
    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final String primKey;
    private final User user;
    private final long scopeGroupId;
    private final BaseContestWrapper contestWrapper;
    private final boolean isUserNotLoggedIn;

    public ContestPermissions(PortletRequest request, Contest contest) throws PortalException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        permissionChecker = themeDisplay.getPermissionChecker();
        portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
        primKey = themeDisplay.getPortletDisplay().getResourcePK();
        scopeGroupId = themeDisplay.getScopeGroupId();
        user = themeDisplay.getUser();
        isUserNotLoggedIn = user.isDefaultUser();
        contestWrapper = new BaseContestWrapper(contest);
    }

    @Override
    public boolean getCanRole(MemberRole role){
        if (isUserNotLoggedIn) {
            return false;
        }

        try {
            return contestWrapper.getHasUserRoleInContest(user, role.getPrintName());
        } catch (SystemException | PortalException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean getIsOwner() {
        return !isUserNotLoggedIn && user != null && contestWrapper.getAuthorId() == user.getUserId();

    }

    @Override
    public boolean getCanAdmin() {
        return !isUserNotLoggedIn && permissionChecker.isOmniadmin();
    }

    @Override
    public boolean getCanStaff() {
        if (isUserNotLoggedIn) {
            return false;
        }
        try {
            return RoleLocalServiceUtil.hasUserRole(user.getUserId(), MemberRole.STAFF.getRoleId());
        } catch (SystemException ignored) { }
        return false;
    }

    public boolean getCanEdit(){
        if (isUserNotLoggedIn) {
            return false;
        }
        // guests aren't allowed to edit
        return !user.isDefaultUser();
    }
    
    @Override
    public boolean getCanDelete() {
        return !isUserNotLoggedIn && !user.isDefaultUser();
    }
    
    @Override
    public boolean getCanCreate() {
        // TODO check who needs this
        if (isUserNotLoggedIn) {
            return false;
        }
        // guests aren't allowed to edit
        return !user.isDefaultUser();
    }

    public boolean getCanAdminAll() {
        return permissionChecker.hasPermission(scopeGroupId, portletId, primKey, "ADMIN_ALL");
    }
}

package org.xcolab.portlets.contestmanagement.utils;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.enums.MemberRole;
import org.xcolab.interfaces.TabPermissions;

import javax.portlet.PortletRequest;

public class ContestManagementPermissions implements TabPermissions {
    private final User user;
    private final boolean isUserNotLoggedIn;

    public ContestManagementPermissions(PortletRequest request) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        user = themeDisplay.getUser();
        isUserNotLoggedIn = user.isDefaultUser();
    }

    @Override
    public boolean getCanRole(MemberRole role) {
        if (isUserNotLoggedIn) {
            return false;
        }
        try {
            return RoleLocalServiceUtil.hasUserRole(user.getUserId(), role.getRoleId());
        } catch (SystemException ignored) { }
        return false;
    }

    @Override
    public boolean getIsOwner() {
        // TODO check who needs this
        return false;
    }

    @Override
    public boolean getCanAdmin() {
        return !isUserNotLoggedIn && PermissionsClient.canAdminAll(user.getUserId());
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

    public boolean getCanEdit() {
        // TODO check who needs this
        return !isUserNotLoggedIn && !user.isDefaultUser();
    }

    @Override
    public boolean getCanDelete() {
        return !isUserNotLoggedIn && !user.isDefaultUser();
    }

    @Override
    public boolean getCanCreate() {
        // TODO check who needs this
        return !isUserNotLoggedIn && !user.isDefaultUser();
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(user.getUserId());
    }
}

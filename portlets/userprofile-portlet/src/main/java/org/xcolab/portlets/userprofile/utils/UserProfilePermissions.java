package org.xcolab.portlets.userprofile.utils;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;

/**
 * Created by johannes on 11/20/15.
 */
public class UserProfilePermissions {

    private final PermissionChecker permissionChecker;
    private final ThemeDisplay themeDisplay;

    public UserProfilePermissions(PortletRequest request) {
        themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        permissionChecker = themeDisplay.getPermissionChecker();
    }

    public boolean getCanAdmin() {
        return permissionChecker.isOmniadmin();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdmin();
    }

    public boolean getCanAdminProfile(long userId) {
        return themeDisplay.getUserId() == userId || getCanAdmin();
    }

    public User getCurrentUser() {
        return themeDisplay.getUser();
    }

    public void checkCanAdminSpamReports() throws UserProfileAuthorizationException {
        if (!getCanAdminSpamReports()) {
            throw new UserProfileAuthorizationException("Admin Spam Report permissions required");
        }
    }
}

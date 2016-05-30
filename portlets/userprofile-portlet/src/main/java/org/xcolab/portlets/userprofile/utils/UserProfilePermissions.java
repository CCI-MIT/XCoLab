package org.xcolab.portlets.userprofile.utils;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.members.PermissionsClient;

import javax.portlet.PortletRequest;

public class UserProfilePermissions {


    private final ThemeDisplay themeDisplay;

    public UserProfilePermissions(PortletRequest request) {
        themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public boolean getCanAdminProfile(long userId) {
        return themeDisplay.getUserId() == userId || getCanAdmin();
    }

    public boolean getCanAdmin() {
        return PermissionsClient.canAdminAll(getCurrentUser().getUserId());
    }

    public User getCurrentUser() {
        return themeDisplay.getUser();
    }

    public void checkCanAdminSpamReports() throws UserProfileAuthorizationException {
        if (!getCanAdminSpamReports()) {
            throw new UserProfileAuthorizationException("Admin Spam Report permissions required");
        }
    }

    public boolean getCanEditMemberProfile(long memberId) {
        return memberId == themeDisplay.getUserId()
                || getCanAdmin();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdmin();
    }
}

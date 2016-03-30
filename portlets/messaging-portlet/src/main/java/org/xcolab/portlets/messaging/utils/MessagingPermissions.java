package org.xcolab.portlets.messaging.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.utils.MessageLimitManager;

import javax.portlet.PortletRequest;

public class MessagingPermissions {
    private final PermissionChecker permissionChecker;
    private final User user;

    public MessagingPermissions(PortletRequest request)
            throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        permissionChecker = themeDisplay.getPermissionChecker();
        user = themeDisplay.getUser();
    }

    public boolean getCanSendMessage() {
        try {
            return MessageLimitManager.canSendMessages(1, user) || getCanAdminAll();
        } catch (PortalException | SystemException e) {
            return getCanAdminAll();
        }
    }

    /**
     * Returns true if user is admin (not only proposal contributor)
     */
    public boolean getCanAdminAll() {
        return permissionChecker.isOmniadmin();
    }

    public User getUser() {
        return user;
    }
}

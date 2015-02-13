package org.xcolab.jspTags.discussion;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;


public class DiscussionPermission {

    private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();

    private TabWrapper tabWrapper;
    private User currentUser;
    private PermissionChecker portletPermissionChecker;
    private String primKey;
    private long groupId;


    public DiscussionPermission(PortletRequest request, DiscussionCategoryGroup discussion) throws
            SystemException, PortalException{

        primKey = String.valueOf(discussion.getId());

        PortletSession session = request.getPortletSession();
        tabWrapper = (TabWrapper) session.getAttribute("tabWrapper");

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        portletPermissionChecker = themeDisplay.getPermissionChecker();
        groupId = themeDisplay.getScopeGroupId();
        currentUser = themeDisplay.getUser();

    }

    public boolean getCanAddComment() {
        return !currentUser.isDefaultUser() && tabWrapper.getCanAddComment();
    }
    
    public boolean getCanAdminMessages() {
        return getCanAdmin() || portletPermissionChecker.hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_MESSAGES.name());
    }

    public boolean getCanAdmin() {
        return portletPermissionChecker.hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN.name())
                || portletPermissionChecker.isGroupAdmin(groupId)
                || portletPermissionChecker.isCompanyAdmin();
    }

}

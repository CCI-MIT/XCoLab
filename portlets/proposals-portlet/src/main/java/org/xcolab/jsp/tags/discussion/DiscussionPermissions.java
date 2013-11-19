package org.xcolab.jsp.tags.discussion;

import javax.portlet.PortletRequest;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;

public class DiscussionPermissions {

    private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();
    
    private User user;
    private DiscussionCategoryGroup discussion;
    private PermissionChecker permissionChecker;
    private String primKey;
    private long groupId;
    
    public DiscussionPermissions(PortletRequest request, DiscussionCategoryGroup discussion) {
        this.discussion = discussion;
        
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.permissionChecker = themeDisplay.getPermissionChecker();
        primKey = String.valueOf(discussion.getId());
        groupId = themeDisplay.getScopeGroupId();
        
        user = themeDisplay.getUser()
    }
    
    
    public boolean getCanSeeAddCommentButton() {
        return true;
    }
    
    public boolean getCanAddComment() {
        return ! user.isDefaultUser();
    }
    
    public boolean getCanAdminMessages() {
        return getCanAdmin() || permissionChecker.hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_MESSAGES.name());
    }

    public boolean getCanAdmin() {
        return permissionChecker.hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN.name()) 
                || permissionChecker.isGroupAdmin(groupId) 
                || permissionChecker.isCompanyAdmin();
    }
}

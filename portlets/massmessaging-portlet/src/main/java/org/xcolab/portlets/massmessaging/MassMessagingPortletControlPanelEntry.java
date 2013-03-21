package org.xcolab.portlets.massmessaging;

import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.BaseControlPanelEntry;

public class MassMessagingPortletControlPanelEntry extends BaseControlPanelEntry {
    
    private String ACTION_SEND_MESSAGES = "SEND_MASS_MESSAGES";

    public boolean isVisible(PermissionChecker permissionChecker, Portlet portlet) throws Exception {
        Long groupId = GroupLocalServiceUtil.getGroup(permissionChecker.getCompanyId(), "Control Panel").getGroupId();
        
        return permissionChecker.hasPermission(groupId, portlet.getRootPortletId(), portlet.getPortletId(), ACTION_SEND_MESSAGES);
        
        
    }

    public boolean isVisible(Portlet portlet, String category, ThemeDisplay themeDisplay) throws Exception {
        
        PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
        Long groupId = GroupLocalServiceUtil.getGroup(permissionChecker.getCompanyId(), "Control Panel").getGroupId();
        
        return permissionChecker.hasPermission(groupId, portlet.getRootPortletId(), portlet.getPortletId(), ACTION_SEND_MESSAGES);
    }

}

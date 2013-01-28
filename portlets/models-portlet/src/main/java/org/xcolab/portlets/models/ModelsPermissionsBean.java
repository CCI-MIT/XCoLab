package org.xcolab.portlets.models;


import com.liferay.portal.security.permission.PermissionChecker;

public class ModelsPermissionsBean {
    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final long groupId;
    private final String primKey;
    
    public ModelsPermissionsBean() {
        permissionChecker = Helper.getPermissionChecker();
        groupId = Helper.getThemeDisplay().getScopeGroupId();
        primKey = Helper.getThemeDisplay().getPortletDisplay().getResourcePK();
        portletId = Helper.getThemeDisplay().getPortletDisplay().getRootPortletId();
    }
    
    public boolean getCanView() {
        return permissionChecker.hasPermission(groupId, portletId, primKey, ModelsActions.CAN_VIEW);
    }
    
    
    public boolean getCanEditModel() {
        boolean x = permissionChecker.hasPermission(groupId, portletId, primKey, ModelsActions.CAN_EDIT_MODEL);
        return permissionChecker.hasPermission(groupId, portletId, primKey, ModelsActions.CAN_EDIT_MODEL);
    }
    
}

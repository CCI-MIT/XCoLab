package org.xcolab.portlets.contestmanagement.views;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.enums.MemberRole;
import org.xcolab.interfaces.TabPermissions;

import javax.portlet.PortletRequest;
import java.util.Date;
import java.util.List;
/**
 * Created by Thomas on 2/9/2015.
 */
public class ContestPermissions implements TabPermissions {
    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final String primKey;
    private boolean contestIsEditable;
    private final User user;
    private final long scopeGroupId;


    public ContestPermissions(PortletRequest request, Contest contest) throws PortalException, SystemException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        permissionChecker = themeDisplay.getPermissionChecker();
        portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
        primKey = themeDisplay.getPortletDisplay().getResourcePK();
        scopeGroupId = themeDisplay.getScopeGroupId();
        user = themeDisplay.getUser();

    }

    public boolean getCanRole(MemberRole role){
        return true;
    }


    public boolean getIsOwner(){
        return true;
    }

    public boolean getCanAdmin() {
        return getCanAdminAll(); // || getIsOwner();
    }

    public boolean getCanStaff() {
            // TODO implement
            return true;
    }

    public boolean getCanEdit(){
        // guests aren't allowed to edit
        if (user.isDefaultUser()) 
            return false;
        
        if (getCanAdminAll()) 
            return true;

        return true;
    }
    
    public boolean getCanDelete(){
        if (user.isDefaultUser()) 
            return false;
        
        if (getCanAdminAll()) 
            return true;

        return true;
    }
    
    public boolean getCanCreate() {
        // guests aren't allowed to edit
        if (user.isDefaultUser())
            return false;

        return true;
    }

    public boolean getCanManageUsers() throws SystemException, PortalException {
    	return getCanAdmin();
    }


    public boolean getCanAdminAll() {
        return permissionChecker.hasPermission(scopeGroupId, portletId, primKey, "ADMIN_ALL");
    }

}

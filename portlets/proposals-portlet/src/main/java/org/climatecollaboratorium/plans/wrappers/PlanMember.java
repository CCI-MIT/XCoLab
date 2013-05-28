package org.climatecollaboratorium.plans.wrappers;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.plans.Helper;
import org.climatecollaboratorium.plans.PlanMembershipBean;
import org.climatecollaboratorium.plans.PlansPermissionsBean;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public class PlanMember implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlanItem plan;
    private User user;
    private PlanUserPermission planUserPermission;
    private boolean member;
    private boolean owner;
    private boolean permissionChanged;
    private PlanMembershipBean planMembershipBean;
    private PlansPermissionsBean permissions;
    

    public PlanMember(PlanItem plan, User user, PlanMembershipBean planMembershipBean, PlansPermissionsBean permissions) throws SystemException {
        this.plan = plan;
        this.user = user;
        this.planMembershipBean = planMembershipBean;
        this.permissions = permissions;
        
    }

    public String getScreenName() {
        return user.getScreenName();
    }

    public boolean isMember() throws SystemException {
        return PlanItemLocalServiceUtil.isUserAMember(plan, user.getUserId());
    }

    public boolean isOwner() throws PortalException, SystemException {
        return PlanItemLocalServiceUtil.isOwner(plan, user.getUserId());
    }

    public boolean isAdmin() throws PortalException, SystemException {
        return PlanItemLocalServiceUtil.isAdmin(plan, user.getUserId());
    }
    
    public PlanUserPermission getPlanUserPermission() throws PortalException, SystemException {
        if (planUserPermission == null) {
            if (isOwner()) {
                planUserPermission = PlanUserPermission.OWNER; 
            }
            else if (isAdmin()) {
                //planUserPermission = PlanUserPermission.ADMIN;
                // everyone is an admin
                planUserPermission = PlanUserPermission.MEMBER;
            }
            else {
                planUserPermission = PlanUserPermission.MEMBER;
            }
        }
        return planUserPermission;
    }
    
    public String getPlanUserPermissionStr() throws PortalException, SystemException {
        return getPlanUserPermission().name();
    }
    
    public void setPlanUserPermissionStr(String planUserPermissionStr) throws PortalException, SystemException {
        PlanUserPermission tmp = PlanUserPermission.valueOf(planUserPermissionStr);
        if (tmp != planUserPermission && permissions.getCanAdmin()) {
            if (tmp.compareTo(planUserPermission) > 0 && ! (permissions.getPlanOwner() || permissions.getCanAdminAll())) {
                // only admin can downgrade a user
                return;
            }
            planUserPermission = tmp;
            
            PlanItemLocalServiceUtil.setUserPermission(plan, user.getUserId(), planUserPermission.name(), Helper.getLiferayUser().getUserId());
        }
    }
    
    public void remove(ActionEvent e) throws SystemException, PortalException {
        PlanItemLocalServiceUtil.removeMember(plan, user.getUserId(), Helper.getLiferayUser().getUserId());
        planMembershipBean.removeMember(this);
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public boolean isPermissionChanged() {
        return permissionChanged;
    }
    
    public Long getPortraitId() {
        return user.getPortraitId();
    }
    
    
}
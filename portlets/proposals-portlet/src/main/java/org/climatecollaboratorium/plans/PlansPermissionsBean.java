package org.climatecollaboratorium.plans;

import java.io.Serializable;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;

public class PlansPermissionsBean implements Serializable {

    private final PermissionChecker permissionChecker;
    private final String portletId;
    private final long groupId;
    private final String primKey;
    private PlanItem plan;
    private boolean planIsEditable = false;
    private PlanUserPermission planUserPermission;
    private Long planGroupId;
    private Long userId = 0L;

    public PlansPermissionsBean() throws SystemException, PortalException {
        permissionChecker = Helper.getPermissionChecker();
        groupId = Helper.getGroupId();
        primKey = Helper.getPrimKey();
        portletId = Helper.getPortletId();
        if (Helper.isUserLoggedIn()) {
            userId = Helper.getLiferayUser().getUserId();
        }

        planGroupId = groupId;
        updatePlanUserPermission();
    }
    
    private void updatePlanUserPermission() throws SystemException, PortalException {
        if (getPlanOwner()) {
            planUserPermission = PlanUserPermission.OWNER;
        }
        else if (getCanAdmin()) {
            planUserPermission = PlanUserPermission.ADMIN;
        }
        else if (getPlanMember()) {
            planUserPermission = PlanUserPermission.MEMBER;
        }
    }

    public PlansPermissionsBean(PlanItem plan) throws SystemException, PortalException {
        this();
        setPlan(plan);
    }

    public boolean getCanView() {
        return true;
    }

    // when plan is published only site admin can modify it
    public boolean getCanEdit() throws SystemException, PortalException {
        return (planIsEditable && (
                        getPlanOpen() && Helper.isUserLoggedIn()
                        || getPlanMember()
                        || getCanAdmin()
                    )
                ) || getCanAdminAll();
    }

    // when plan is published only site admin can admin it
    public boolean getCanAdmin() throws SystemException, PortalException {
        return (planIsEditable && (
                        //(getPlanMember() && permissionChecker.isCommunityAdmin(planGroupId)) 
                        //|| getPlanOwner()
                        getPlanMember()
                        || getPlanOwner()
                    )
                ) || getCanAdminAll();
                
    }

    public boolean getCanAdminAll() {
        boolean adminAll =  permissionChecker.hasPermission(groupId, portletId, primKey, PlansActions.CAN_ADMIN_ALL);
        return adminAll;
    }

    public boolean getCanDelete() throws SystemException, PortalException {
        return getCanAdmin();
    }

    public boolean getPlanOwner() {
        boolean owner =  permissionChecker.isCommunityOwner(planGroupId);
        return owner;
    }

    public boolean getPlanMember() throws SystemException, NumberFormatException, PortalException {
        if (Helper.isUserLoggedIn() && plan != null) {
            boolean x = GroupLocalServiceUtil.hasUserGroup(Helper.getLiferayUser().getUserId(), planGroupId);
            return x;
        }
        return false;
    }

    public boolean getPlanGuest() throws SystemException, NumberFormatException, PortalException {
        return !getPlanOwner() && !getPlanMember();
    }

    public void setPlan(PlanItem plan) throws SystemException, PortalException {
        this.plan = plan;
        if (plan!=null) {
            // use group id from plans community
            planGroupId = PlanItemLocalServiceUtil.getPlanGroupId(plan);
            ContestPhase phase = PlanItemLocalServiceUtil.getContestPhase(plan);
            String status = 
                    ContestPhaseTypeLocalServiceUtil.getContestPhaseType(phase.getContestPhaseType()).getStatus();
            planIsEditable = ContestStatus.valueOf(status).isCanEdit() && ContestPhaseLocalServiceUtil.getPhaseActive(phase);
            updatePlanUserPermission();
        }
    }

    public PlanItem getPlan() {
        return plan;
    }
    
    public PlanUserPermission getPlanUserPermission() {
        return planUserPermission;
    }
    
    public boolean getPlanOpen() throws SystemException {
        return plan == null ? false : PlanItemLocalServiceUtil.getOpen(plan);
    }
    
    public boolean getLoggedIn() {
        return Helper.isUserLoggedIn();
    }

    public boolean isPlanEditable() {
        return planIsEditable;
    }
}
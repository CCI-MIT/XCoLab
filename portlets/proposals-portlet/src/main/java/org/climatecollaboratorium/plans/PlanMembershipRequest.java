package org.climatecollaboratorium.plans;

import java.util.Date;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class PlanMembershipRequest {
    private MembershipRequest request;
    private User user;
    private String response;
    private PlanItem plan;
    private PlansPermissionsBean permissions;
    private boolean approve = true;
    private PlanBean planBean;
    private ThemeDisplay td = Helper.getThemeDisplay();

    public PlanMembershipRequest(MembershipRequest request, PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws PortalException, SystemException {
        this.request = request;
        this.user = UserLocalServiceUtil.getUser(request.getUserId());
        this.plan = plan;
        this.permissions = permissions;
        this.planBean = planBean;
    }

    public String getScreenName() {
        return user.getScreenName();
    }

    public Date getRequestDate() {
        return request.getCreateDate();
    }

    public String getComment() {
        return request.getComments();
    }

    public Long getId() {
        return request.getMembershipRequestId();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean getApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public void update(ActionEvent e) throws PortalException, SystemException {
        if (permissions.getCanAdmin()) {
            String responseStr = response == null || response.trim().length() == 0 ? "no comments" : response; 
            if (approve) {
                
                PlanItemLocalServiceUtil.approveMembershipRequest(plan, user.getUserId(), request, responseStr, Helper.getLiferayUser().getUserId());

                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                        PlanItem.class.getName(), plan.getPlanId(), PlanActivityKeys.USER_ADDED_TO_PLAN.id(),null, 0);
            }
            else {
                PlanItemLocalServiceUtil.dennyMembershipRequest(plan, user.getUserId(), request, responseStr, Helper.getLiferayUser().getUserId());
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                        PlanItem.class.getName(), plan.getPlanId(), PlanActivityKeys.USER_REMOVED_FROM_PLAN.id(),null, 0);
            }
        }
        planBean.refresh();
    }
    
    public Long getUserId() {
        return user.getUserId();
    }

}
package org.climatecollaboratorium.plans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.mail.internet.AddressException;

import org.climatecollaboratorium.plans.wrappers.PlanMember;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.User;
import com.liferay.util.mail.MailEngineException;

public class PlanMembershipBean {
    private static final String MSG_MEMBERSHIP_REQUEST_SUBJECT = "%s wants to join your proposal %s";
    private static final String MSG_MEMBERSHIP_REQUEST_CONTENT = "User %s has requested to join your proposal %s. Click <a href='%s'>here</a> to respond to it.";
    private static final String PROPOSAL_URL = "%s/web/guest/plans/-/plans/contestId/%d/planId/%d#plans%%3Dtab%%3AADMIN";
    private PlanItem plan;
    private PlanBean planBean;
    private List<PlanMember> planMembers;
    private List<PlanMember> planMembersHalf1;
    private List<PlanMember> planMembersHalf2;
    private List<PlanMembershipRequest> planMembershipRequests;
    private String comment;
    private PlansPermissionsBean permissions;
    private boolean requesting;

    public PlanMembershipBean(PlanItem plan, PlanBean planBean, PlansPermissionsBean permissions) throws PortalException, SystemException {
        this.plan = plan;
        this.planBean = planBean;
        this.permissions = permissions;

        planMembers = new ArrayList<PlanMember>();
        planMembersHalf1 = new ArrayList<PlanMember>();
        planMembersHalf2 = new ArrayList<PlanMember>();


        planMembershipRequests = new ArrayList<PlanMembershipRequest>();

    }


    public List<PlanMember> getPlanMembers() throws SystemException {
        if (planMembers.size() == 0) {
            planMembers.clear();
            for (User user: PlanItemLocalServiceUtil.getMembers(plan)) {
                planMembers.add(new PlanMember(plan, user, this, permissions));
            }
        }
        return planMembers;
    }
    
    public List<PlanMember> getPlanMembersHalf1() throws SystemException {
        /*
        if (planMembersHalf1.size() == 0) {
            planMembersHalf1.addAll(getPlanMembersSublist(0, (plan.getMembers().size() / 2) + (plan.getMembers().size() % 2)));
        }
        return planMembersHalf1;
        */
        return getPlanMembersSublist(0, (PlanItemLocalServiceUtil.getMembers(plan).size() / 2) + (PlanItemLocalServiceUtil.getMembers(plan).size() % 2)); 
    }
    
    public List<PlanMember> getPlanMembersHalf2() throws SystemException {
        /*
        if (planMembersHalf2.size() == 0) {
            planMembersHalf2.addAll(getPlanMembersSublist((plan.getMembers().size() / 2) + (plan.getMembers().size() % 2), plan.getMembers().size()));
        }
        return planMembersHalf2;
        */
        return getPlanMembersSublist((PlanItemLocalServiceUtil.getMembers(plan).size() / 2) + (PlanItemLocalServiceUtil.getMembers(plan).size() % 2), PlanItemLocalServiceUtil.getMembers(plan).size());
    }
    
    private List<PlanMember> getPlanMembersSublist(int from, int to) throws SystemException {
        List<PlanMember> ret = new ArrayList<PlanMember>();
        for (int i=from; i < to; i++) {
            ret.add(new PlanMember(plan, PlanItemLocalServiceUtil.getMembers(plan).get(i), this, permissions));
        }
        return ret;
    }
    

    public List<PlanMembershipRequest> getPlanMembershipRequests() throws SystemException, PortalException {
        planMembershipRequests.clear();
        for (MembershipRequest request: PlanItemLocalServiceUtil.getMembershipRequests(plan)) {
            planMembershipRequests.add(new PlanMembershipRequest(request, plan, planBean, permissions));
        }
        return planMembershipRequests;
    }

    public void requestMembership(ActionEvent e) throws PortalException, SystemException, AddressException, MailEngineException {
        if (Helper.isUserLoggedIn()) {
            PlanItemLocalServiceUtil.addMembershipRequest(plan, Helper.getLiferayUser().getUserId(), comment.length() == 0 ? "No comments" : comment);
            planBean.refresh();
            List<Long> receipients = new ArrayList<Long>();
            receipients.add(PlanItemLocalServiceUtil.getAuthorId(plan));
            
            String requestUrl = Helper.getRequest().getRequestURL().toString();
            
            
            
            
            String proposalUrl = String.format(PROPOSAL_URL, requestUrl.substring(0, requestUrl.indexOf("/", 10)), 
                    PlanItemLocalServiceUtil.getContest(plan).getContestPK(), plan.getPlanId());
            String subject = String.format(MSG_MEMBERSHIP_REQUEST_SUBJECT, Helper.getLiferayUser().getFullName(), PlanItemLocalServiceUtil.getName(plan));
            String content = String.format(MSG_MEMBERSHIP_REQUEST_CONTENT, Helper.getLiferayUser().getFullName(), PlanItemLocalServiceUtil.getName(plan), proposalUrl);
            
            MessageUtil.sendMessage(subject, content, 
                    Helper.getLiferayUser().getUserId(), 
                    Helper.getLiferayUser().getUserId(), receipients, null);
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public void toggleRequestingOrJoin(ActionEvent e) throws SystemException, PortalException {
        if (PlanItemLocalServiceUtil.getOpen(plan)) {
            PlanItemLocalServiceUtil.joinIfNotAMember(plan, Helper.getLiferayUser().getUserId());
        } else {
            requesting = !requesting;
        }
    }


    public boolean isRequesting() {
        return requesting;
    }
    
    public List<SelectItem> getAvailableUserRoles() throws SystemException, PortalException {
        List<SelectItem> items = new ArrayList<SelectItem>();

        if (permissions.getCanAdminAll()) {
            items.add(new SelectItem(PlanUserPermission.OWNER, PlanUserPermission.OWNER.getDescription()));
        }
        if (permissions.getCanAdmin()) {
            items.add(new SelectItem(PlanUserPermission.MEMBER, PlanUserPermission.MEMBER.getDescription()));
            items.add(new SelectItem(PlanUserPermission.ADMIN, PlanUserPermission.ADMIN.getDescription()));
        }
        return items;
        
    }


    public void removeMember(PlanMember planMember) {
        planMembers.remove(planMembers.indexOf(planMember));
    }
    
    public void removeCurrentUser(ActionEvent e) throws SystemException, PortalException, IllegalUIConfigurationException, IOException {
        if (Helper.isUserLoggedIn()) {
            PlanItemLocalServiceUtil.removeMember(plan, Helper.getLiferayUser().getUserId(), Helper.getLiferayUser().getUserId());
            planBean.refreshFull();
        }
    }

}
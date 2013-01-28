package org.climatecollaboratorium.plans;

import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.events.EventBus;
import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.events.PlanCreatedEvent;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;
import org.climatecollaboratorium.plans.wrappers.PlanItemWrapper;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class CreatePlanBean {

    private static Log _log = LogFactoryUtil.getLog(CreatePlanBean.class);

    private PlansIndexBean plansIndexBean;
    private String name;
    private PlanBean planBean;
    private Long planId;
    private boolean navigateToPlan;
    private PlansPreferencesBean preferences;
    private EventBus eventBus;

    private ContestWrapper contestWrapper;
    
    private final static String PLANS_SOURCE = "plans"; 
    private final static String CREATE_PLAN_PARAM = "createPlan"; 
    

    public CreatePlanBean(PlansIndexBean plansIndexBean) throws SystemException {
        this.plansIndexBean = plansIndexBean;
        name = "";
        preferences = new PlansPreferencesBean();
    }

    public CreatePlanBean(PlanBean planBean) {
        this.planBean = planBean;
        name = "";
    }
    
    public CreatePlanBean(ContestWrapper contestWrapper) {
        this.contestWrapper = contestWrapper;
        name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void createPlan(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            PlanItem planItem = null;
            
            
            Object contestIdObj = e.getComponent().getAttributes().get("contestId");
            if (contestIdObj != null) {
                Long contestId = Long.parseLong(contestIdObj.toString());
                Contest contest = ContestLocalServiceUtil.getContest(contestId);
                
                String defaultDescription = contest.getDefaultPlanDescription();
                planItem = PlanItemLocalServiceUtil.createPlan(ContestLocalServiceUtil.getActivePhase(contest), Helper.getLiferayUser().getUserId());

                PlanItemLocalServiceUtil.setDescription(planItem, defaultDescription, Helper.getLiferayUser().getUserId());
            }
            else if (contestWrapper != null) {
                String defaultDescription = contestWrapper.getContest().getDefaultPlanDescription();
                
                planItem = PlanItemLocalServiceUtil.createPlan(ContestLocalServiceUtil.getActivePhase(contestWrapper.getContest()), Helper.getLiferayUser().getUserId());
                PlanItemLocalServiceUtil.setDescription(planItem, defaultDescription, Helper.getLiferayUser().getUserId());
            } else if (plansIndexBean != null) {
                // get default description
                String defaultDescription = ContestPhaseLocalServiceUtil.getContest(plansIndexBean.getContestPhase().getPhase()).getDefaultPlanDescription();
                planItem = PlanItemLocalServiceUtil.createPlan(plansIndexBean.getContestPhase().getPhase(), Helper.getLiferayUser().getUserId());
                PlanItemLocalServiceUtil.setDescription(planItem, defaultDescription, Helper.getLiferayUser().getUserId());
            }
            else if (planBean != null) {
                // we need to create a plan based on a plan that is currently visible
                PlanItemWrapper wrapper = planBean.getPlan();
                PlanItem item = wrapper.getWrapped();
                ContestPhase phase = PlanItemLocalServiceUtil.getContestPhase(item);
                if (!ContestPhaseLocalServiceUtil.getContestStatus(phase).isCanEdit()) {
                    List<ContestPhase> active = ContestLocalServiceUtil.getActivePhases(ContestPhaseLocalServiceUtil.getContest(phase));
                    if (active == null || active.isEmpty()) {
                        _log.warn("Connect create plan ");
                        return;
                    } else {
                        phase = active.get(0);
                    }
                }
                planItem = PlanItemLocalServiceUtil.createPlan(planBean.getPlan().getWrapped(), phase, Helper.getLiferayUser().getUserId());
                PlanItemLocalServiceUtil.setName(planItem, PlanItemLocalServiceUtil.getName(planItem)+"(copy of "+ PlanItemLocalServiceUtil.getName(planBean.getPlan().getWrapped())+")",Helper.getLiferayUser().getUserId());
            } else {

                //i don't think this is actually called.

                plansIndexBean = (PlansIndexBean) e.getComponent().getAttributes().get("plansIndexBean");
                String defaultDescription = preferences.getDefaultDescription();
                planItem = PlanItemLocalServiceUtil.createPlan(plansIndexBean.getContestPhase().getPhase(), Helper.getLiferayUser().getUserId());
                PlanItemLocalServiceUtil.setDescription(planItem, defaultDescription, Helper.getLiferayUser().getUserId());

            }
            eventBus.fireEvent(new PlanCreatedEvent(planItem));
            
            if (PlanItemLocalServiceUtil.getContest(planItem).getPlansOpenByDefault()) {
                PlanItemLocalServiceUtil.setOpen(planItem, true);
            }
            else {
                PlanItemLocalServiceUtil.setOpen(planItem, false);
            }
            
            // subscribe plan
            
            
            
            // fetch all users subscribed to current contest, and subscribe them to this proposal too
            for (ActivitySubscription subscription: ActivitySubscriptionLocalServiceUtil.getActivitySubscriptions(Contest.class, 
                    PlanItemLocalServiceUtil.getContest(planItem).getContestPK(), 0, "")) {
            	//subscription.getReceiverId();
                ActivitySubscriptionLocalServiceUtil.addSubscription(PlanItem.class, planItem.getPlanId(), 0, "",
                        subscription.getReceiverId());

                ActivitySubscriptionLocalServiceUtil.addSubscription(DiscussionCategoryGroup.class, 
                        PlanItemLocalServiceUtil.getCategoryGroupId(planItem), 
                        null, "", subscription.getReceiverId());
            }

            ActivitySubscriptionLocalServiceUtil.addSubscription(PlanItem.class, planItem.getPlanId(), 0, "", Helper.getLiferayUser().getUserId());
            // subscribe to comments
            ActivitySubscriptionLocalServiceUtil.addSubscription(DiscussionCategoryGroup.class, 
                    PlanItemLocalServiceUtil.getCategoryGroupId(planItem), 0, "", 
                    PlanItemLocalServiceUtil.getAuthorId(planItem));
            planId = planItem.getPlanId();
            navigateToPlan = true;
        }
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    // redirection should occur only once
    public boolean isNavigateToPlan() {
        if (navigateToPlan) {
            navigateToPlan = false;
            return true;
        }
        return false;
    }

    public void setNavigateToPlan(boolean navigateToPlan) {
        this.navigateToPlan = navigateToPlan;
    }

    public void setPlansIndex(PlansIndexBean bean) {
        this.plansIndexBean=bean;
    }

    public PlansIndexBean getPlansIndex() {
        return plansIndexBean;
    }

    public void init(NavigationEvent event) throws SystemException, PortalException {
        Map<String, String> params = event.getParameters(PLANS_SOURCE);
        if (params != null) {
            String createPlanStr = params.get(CREATE_PLAN_PARAM);
            if ("true".equals(createPlanStr)) {
                planBean = null;
                createPlan(null);
            }
        }
    }
    
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }


}
package org.climatecollaboratorium.plans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.navigation.NavigationEvent;
import org.climatecollaboratorium.plans.exceptions.BeanInitializationException;
import org.climatecollaboratorium.plans.wrappers.ContestPhaseWrapper;
import org.climatecollaboratorium.plans.wrappers.ContestWrapper;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import edu.mit.cci.simulation.client.Simulation;

public class ContestBean {
    
    private ContestWrapper contest;
    private boolean activeContest = true; 
    private ContestSubView subView = ContestSubView.PROPOSALS;
    private ContestPhaseWrapper currentPhase;
    private ContestState contestState = ContestState.NOT_STARTED;
    
    private final static String PLANS_SOURCE = "plans"; 
    private final static String SUBVIEW_PARAM = "subview";
    private final static String CONTESTS_PARAM = "contests";
    private final static String PAST_CONTESTS_PARAM_VAL = "past";
    private static final String PHASE_PARAM = "phase";
    

    public List<SelectItem> availableModels = new ArrayList<SelectItem>();
    public Long modelId;
    public boolean flag = true;

    private final static Log _log = LogFactoryUtil.getLog(ContestBean.class);
    
    public ContestBean(Map<String, String> params) throws SystemException, PortalException, BeanInitializationException, NumberFormatException, IOException {
        Long contestId = null;
        if (params.containsKey("contestId")) {
            try {
                contestId = Long.parseLong(params.get("contestId"));
                contest = new ContestWrapper(ContestLocalServiceUtil.getContest(contestId));
            }
            catch (NumberFormatException e) {
                throw new BeanInitializationException("Can't parse contest id", e);
            }
        }
        else {
            contest = new ContestWrapper(ContestLocalServiceUtil.getContestByActiveFlag(true));
        }
        if (contest.getContest().getContestActive() && contest.getContest().isActive()) {
            currentPhase = new ContestPhaseWrapper(contest, contest.getContest().getActivePhase());
            contestState = ContestState.ACTIVE;
        }
        else {
            List<ContestPhase> phases = contest.getContest().getPhases();
            currentPhase = new ContestPhaseWrapper(contest, phases.get(phases.size()-1));
            if (currentPhase.getStartDate().after(new Date())) {
                contestState = ContestState.NOT_STARTED;
            }
            else {
                contestState = ContestState.ENDED;
            }
        }
        
        activeContest = contest.isContestActive();
        initModels();
    }

   
    
    public void init(NavigationEvent event) throws SystemException, NoSuchContestPhaseException, PortalException {
        Map<String, String> params = event.getParameters(PLANS_SOURCE);
        if (params == null) {
            return;
        }
        if (params.containsKey(SUBVIEW_PARAM)) {
            ContestSubView tmp = ContestSubView.valueOf(params.get(SUBVIEW_PARAM).toUpperCase());
            if (tmp != subView) {
                subView = tmp;
            }
        }
        if (params.containsKey(PHASE_PARAM)) {
            try {
                Long phaseId = Long.parseLong(params.get(PHASE_PARAM));
                for (ContestPhaseWrapper phase: contest.getPhases()) {
                    if (phase.getPhaseId().equals(phaseId)) {
                        currentPhase = phase;
                    }
                }
            } 
            catch (NumberFormatException e) {
                _log.error("Error when parsing phase id: " + params.get(PHASE_PARAM), e);
            }
        }
        //contest.init(this, event);
    }
    

    public void initModels() throws SystemException, PortalException, NumberFormatException, IOException {
        modelId = contest.getContest().getPlanType().getDefaultModelId();
            availableModels.clear();
            try {
            for (Simulation sim:contest.getContest().getPlanType().getAvailableModels()) {

                availableModels.add(new SelectItem(sim.getId(), sim.getName()));
            }
            }
            catch (Exception e) {
                _log.error(e);
            }
    }

    public ContestWrapper getContest() {
        return contest;
    }
    
    public boolean isActiveContest() {
        return activeContest;
    }
    
    public ContestSubView getSubView() {
        return subView;
    }
    
    public ContestPhaseWrapper getCurrentPhase() throws SystemException, NoSuchContestPhaseException {
        return currentPhase;
    }

    public List<SelectItem> getAvailableModels() {
        return availableModels;
    }
    
    public Long getModelId() throws PortalException, SystemException {
        return modelId;
    }

    public void setModelId(Long id) {
        this.modelId = id;
    }
    
    public Long getContestId() {
        return contest.getContest().getContestPK();
    }
    
    public boolean getHasModel() throws PortalException, SystemException {
        return contest.getHasModel();
    }
    
    public PlansIndexBean getPlansIndex() {
        return contest.getPlansIndex();
    }
    
    
    public void setContestState(ContestState contestState) {
        this.contestState = contestState;
    }

    public ContestState getContestState() {
        return contestState;
    }
    
    public boolean isUserSubscribed() throws PortalException, SystemException {
        if (Helper.isUserLoggedIn()) {
            boolean subscribed =  ActivitySubscriptionLocalServiceUtil.isSubscribed(Helper.getLiferayUser().getUserId(),
                    Contest.class, contest.getContestId(), null, "");
            return subscribed;
        }
        return false;
    }
    
    public void subscribe(ActionEvent e) throws PortalException, SystemException {
    	if (Helper.isUserLoggedIn()) {
    		ActivitySubscriptionLocalServiceUtil.addSubscription(Contest.class, contest.getContestId(), null, "", Helper.getLiferayUser().getUserId());
    		
    		// add subscription to each proposal and it's comments
    		for (PlanItem planItem: PlanItemLocalServiceUtil.getPlansByContest(contest.getContest().getContestPK())) {
                ActivitySubscriptionLocalServiceUtil.addSubscription(PlanItem.class, planItem.getPlanId(), null, "",
                        Helper.getLiferayUser().getUserId());

                ActivitySubscriptionLocalServiceUtil.addSubscription(DiscussionCategoryGroup.class, planItem.getCategoryGroupId(), 
                        null, "", Helper.getLiferayUser().getUserId());
    		}
    	}
    }
    
    public void unsubscribe(ActionEvent e) throws SystemException, PortalException {
    	if (Helper.isUserLoggedIn()) {
    		ActivitySubscriptionLocalServiceUtil.deleteSubscription(Helper.getLiferayUser().getUserId(), Contest.class, contest.getContestId(), null, "");
    		
    		// add subscription to each proposal and it's comments
    		for (PlanItem planItem: PlanItemLocalServiceUtil.getPlansByContest(contest.getContest().getContestPK())) {
                ActivitySubscriptionLocalServiceUtil.deleteSubscription(Helper.getLiferayUser().getUserId(), PlanItem.class, planItem.getPlanId(), null, "");

                ActivitySubscriptionLocalServiceUtil.deleteSubscription(Helper.getLiferayUser().getUserId(), DiscussionCategoryGroup.class, planItem.getCategoryGroupId(), 
                        null, "");
    		}
    	}
    }
    


    public enum ContestState {
        NOT_STARTED,
        ACTIVE,
        ENDED
    };
}

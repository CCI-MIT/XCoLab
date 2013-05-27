package org.climatecollaboratorium.plans.migration;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanCopyTool {
    private Long sourceContestPhase;
    private Long targetContestPhase;
    private List<SelectItem> availableContestPhases = new ArrayList<SelectItem>();
    private boolean readyForCopy;
    private boolean addSemiFinalistRibbon;
    private String planAdvancedText = "Plan advanced to next phase";

    private List<PlanCopyItem> planCopyItems;
    
    public PlanCopyTool() throws PortalException, SystemException {
        availableContestPhases.add(new SelectItem(-1, "-- Select --"));
        for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            for (ContestPhase phase: ContestLocalServiceUtil.getPhases(contest)) {
                availableContestPhases.add(
                        new SelectItem(phase.getContestPhasePK(),
                                ContestPhaseLocalServiceUtil.getContest(phase).getContestShortName() + ": " + ContestPhaseLocalServiceUtil.getName(phase)));
            }
        }
    }

    public List<SelectItem> getAvailableContestPhases() {
        return availableContestPhases;
    }

    public boolean isReadyForCopy() {
        return readyForCopy;
    }

    public Long getSourceContestPhase() {
        return sourceContestPhase;
    }

    public void setSourceContestPhase(Long sourceContestPhase) throws PortalException, SystemException {
        this.sourceContestPhase = sourceContestPhase;
        updateReady();
    }

    public Long getTargetContestPhase() {
        return targetContestPhase;
    }

    public void setTargetContestPhase(Long targetContestPhase) throws PortalException, SystemException {
        this.targetContestPhase = targetContestPhase;
        updateReady();
    }
    
    
    private void updateReady() throws PortalException, SystemException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (sourceContestPhase != null && targetContestPhase != null) {
            if (sourceContestPhase > 0 && targetContestPhase > 0) {
                readyForCopy = true;
                
                // check if contest phases have the same plan types
                ContestPhase sourcePhase = ContestPhaseLocalServiceUtil.getContestPhase(sourceContestPhase);
                ContestPhase targetPhase = ContestPhaseLocalServiceUtil.getContestPhase(targetContestPhase);
                if (ContestPhaseLocalServiceUtil.getContest(targetPhase).getPlanTypeId() != ContestPhaseLocalServiceUtil.getContest(sourcePhase).getPlanTypeId()) {
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contest phases are incompatible", ""));
                    readyForCopy = false;
                }
                else {
                    readyForCopy = true;
                    planCopyItems = new ArrayList<PlanCopyItem>();
                    for (PlanItem plan: ContestPhaseLocalServiceUtil.getPlans(sourcePhase)) {
                        if (plan.getVersion() > 1 && !plan.getState().equals("DELETED")) {
                            planCopyItems.add(new PlanCopyItem(plan));
                        }
                    }
                }
            }
            else {
                readyForCopy = false;
            }
        }
        else {
            readyForCopy = false;
        }
    }
    
    
    public List<PlanCopyItem> getPlanCopyItems() {
        return planCopyItems;
    }
    
    public void copy(ActionEvent e) throws PortalException, SystemException {
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        // check if contest phases have the same plan types
        ContestPhase sourcePhase = ContestPhaseLocalServiceUtil.getContestPhase(sourceContestPhase);
        ContestPhase targetPhase = ContestPhaseLocalServiceUtil.getContestPhase(targetContestPhase);
        if (ContestPhaseLocalServiceUtil.getContest(targetPhase).getPlanTypeId() != ContestPhaseLocalServiceUtil.getContest(sourcePhase).getPlanTypeId()) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contest phases are incompatible", ""));
            return;
        }
        
        //Set<Long> plansToBeCopiedIds = new HashSet<Long>();
        List<PlanItem> plansToBeCopied = new ArrayList<PlanItem>();
        /*
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlanItems(0, Integer.MAX_VALUE)) {
            if (plan.getContestPhase() != null && 
                    plan.getContestPhase().getContestPhasePK().equals(sourceContestPhase) && 
                    ! plansToBeCopiedIds.contains(plan.getPlanId()) && 
                    plan.getVersion() > 1 && 
                    !plan.getState().equals("DELETED")) {
                // get latest version of a plan
                plansToBeCopied.add(PlanItemLocalServiceUtil.getPlan(plan.getPlanId()));
                plansToBeCopiedIds.add(plan.getPlanId());
            }
        }*/
        for (PlanCopyItem item: planCopyItems) {
            if (item.isSelected()) {
                plansToBeCopied.add(item.getPlan());
            }
        }   
        
        PlanItemLocalServiceUtil.promotePlans(plansToBeCopied, targetContestPhase);
        
        
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Plans copied", ""));
        
        return;
    }

    public void setAddSemiFinalistRibbon(boolean addSemiFinalistRibbon) {
        this.addSemiFinalistRibbon = addSemiFinalistRibbon;
    }

    public boolean isAddSemiFinalistRibbon() {
        return addSemiFinalistRibbon;
    }

    public void setPlanAdvancedText(String planAdvancedText) {
        this.planAdvancedText = planAdvancedText;
    }

    public String getPlanAdvancedText() {
        return planAdvancedText;
    }
    

}

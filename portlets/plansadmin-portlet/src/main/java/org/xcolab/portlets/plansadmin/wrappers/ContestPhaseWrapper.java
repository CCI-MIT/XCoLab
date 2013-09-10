package org.xcolab.portlets.plansadmin.wrappers;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ContestPhaseWrapper {
    private ContestPhase contestPhase;
    private ContestWrapper contestWrapper;

    public ContestPhaseWrapper(ContestPhase contestPhase, ContestWrapper contestWrapper) throws SystemException {
        super();
        if (contestPhase == null) {
            contestPhase = ContestPhaseLocalServiceUtil.createContestPhase(0);
        }   
        this.contestPhase = contestPhase;
        this.contestWrapper = contestWrapper;
    }

    public long getContestPhaseType() {
        return contestPhase.getContestPhaseType();
    }

    public void setContestPhaseType(long ContestPhaseType) {
        contestPhase.setContestPhaseType(ContestPhaseType);
    }

    public String getContestPhaseAutopromote() {
        return contestPhase.getContestPhaseAutopromote();
    }

    public void setContestPhaseAutopromote(String contestPhaseAutopromote) {
        contestPhase.setContestPhaseAutopromote(contestPhaseAutopromote);
    }

    public String getContestPhaseDescriptionOverride() {
        return contestPhase.getContestPhaseDescriptionOverride();
    }

    public void setContestPhaseDescriptionOverride(String ContestPhaseDescriptionOverride) {
        contestPhase.setContestPhaseDescriptionOverride(ContestPhaseDescriptionOverride);
    }

    public boolean getPhaseActiveOverride() {
        return contestPhase.getPhaseActiveOverride();
    }

    public void setPhaseActiveOverride(boolean phaseActiveOverride) {
        contestPhase.setPhaseActiveOverride(phaseActiveOverride);
    }

    public boolean getPhaseInactiveOverride() {
        return contestPhase.getPhaseInactiveOverride();
    }

    public void setPhaseInactiveOverride(boolean phaseInactiveOverride) {
        contestPhase.setPhaseInactiveOverride(phaseInactiveOverride);
    }

    public Date getPhaseStartDate() {
        return contestPhase.getPhaseStartDate();
    }

    public void setPhaseStartDate(Date PhaseStartDate) {
        contestPhase.setPhaseStartDate(PhaseStartDate);
    }

    public Date getPhaseEndDate() {
        return contestPhase.getPhaseEndDate();
    }

    public void setPhaseEndDate(Date PhaseEndDate) {
        contestPhase.setPhaseEndDate(PhaseEndDate);
    }
    
    public ContestPhaseType getType() throws PortalException, SystemException {
        ContestPhaseType type = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhase.getContestPhaseType());
        return type;
        
    }
    public void save(ActionEvent e) throws SystemException {
        contestPhase.setContestPK(contestWrapper.getContest().getContestPK());
        if (contestPhase.getContestPhasePK() <= 0) {
            long id = CounterLocalServiceUtil.increment(ContestPhase.class.getName());
            if (id < 100) {
                id = CounterLocalServiceUtil.increment(ContestPhase.class.getName(), 100);
            }
            contestPhase.setContestPhasePK(id);
            ContestPhaseLocalServiceUtil.addContestPhase(contestPhase);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contest phase added"));
        }
        else {FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contest phase updated"));
            ContestPhaseLocalServiceUtil.updateContestPhase(contestPhase);
        }
        contestWrapper.refresh();
    }
    
    public boolean isNew() {
        return contestPhase.getContestPhasePK() <= 0;
    }
    
    public Long getContestPhasePK() {
        return contestPhase.getContestPhasePK();
    }
    
    public Long getTypeId() {
        return contestPhase.getContestPhaseType();
    }
    
    public void setTypeId(Long id) {
        contestPhase.setContestPhaseType(id);
    }
    
    
    

}

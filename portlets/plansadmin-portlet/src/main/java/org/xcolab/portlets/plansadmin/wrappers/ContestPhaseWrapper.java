package org.xcolab.portlets.plansadmin.wrappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.enums.ContestPhasePromoteType;

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

    public List<SelectItem> getAutopromoteItems() {
        List<SelectItem> selectItems = new ArrayList<>();
        for (ContestPhasePromoteType promoteType : ContestPhasePromoteType.values()) {
            selectItems.add(new SelectItem(promoteType.getIndex(), promoteType.getValue(), promoteType.getDescription()));
        }

        return selectItems;
    }

    public void setAutopromoteItem(Integer index) {
        setContestPhaseAutopromote(ContestPhasePromoteType.getPromoteType(index).getValue());
    }

    public Integer getAutopromoteItem() {
        return ContestPhasePromoteType.getPromoteType(getContestPhaseAutopromote()).getIndex();
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

    public void save(ActionEvent e) throws SystemException, PortalException {
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
    
    public void delete(ActionEvent e) throws SystemException, PortalException {
        if (contestPhase.getContestPhasePK() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can't remove contest phase that doesn't exist", ""));
            return;
        }
        if (! ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getContestPhasePK()).isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Can't remove phase, there are plans associated with it", ""));   
            return;
        }
        ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
        contestWrapper.refresh();
        
    }
    
    public boolean isNew() {
        return contestPhase.getContestPhasePK() <= 0;
    }
    
    public boolean getIsNew() {
        return isNew();
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

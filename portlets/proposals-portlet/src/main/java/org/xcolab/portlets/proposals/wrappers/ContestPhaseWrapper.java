package org.xcolab.portlets.proposals.wrappers;

import java.util.Date;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ContestPhaseWrapper {
    private ContestPhase contestPhase;
    private ContestStatus status;
    
    public ContestPhaseWrapper(ContestPhase contestPhase) {
        this.contestPhase = contestPhase;
    }

    public long getContestPhasePK() {
        return contestPhase.getContestPhasePK();
    }

    public void setContestPhasePK(long ContestPhasePK) {
        contestPhase.setContestPhasePK(ContestPhasePK);
    }

    public long getContestPK() {
        return contestPhase.getContestPK();
    }

    public void setContestPK(long ContestPK) {
        contestPhase.setContestPK(ContestPK);
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

    public Date getPhaseStartDate() {
        return contestPhase.getPhaseStartDate();
    }

    public Date getPhaseEndDate() {
        return contestPhase.getPhaseEndDate();
    }

    public Date getCreated() {
        return contestPhase.getCreated();
    }

    public void setCreated(Date created) {
        contestPhase.setCreated(created);
    }

    public long getAuthorId() {
        return contestPhase.getAuthorId();
    }

    public void setAuthorId(long authorId) {
        contestPhase.setAuthorId(authorId);
    }
    
    public ContestStatus getStatus() throws PortalException, SystemException {
        if (status == null) {
            String statusStr = ContestPhaseLocalServiceUtil.getContestStatusStr(contestPhase);
            System.out.println(statusStr);
            if (statusStr != null) {
                status = ContestStatus.valueOf(statusStr);
            }
        }
        return status;
    }

    public boolean getCanVote() throws PortalException, SystemException {
        return getStatus().isCanVote();
    }

    public boolean getCanEdit() throws PortalException, SystemException {
        return getStatus().isCanEdit();
    }

}

/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans.wrappers;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;


/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 6, 2010
 * Time: 2:53:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestPhaseWrapper {
    private ContestPhase phase;
    private ContestWrapper contestWrapper;
    private boolean last;

    public ContestPhaseWrapper(ContestWrapper wrapper, ContestPhase phase, boolean last) {
        this.phase = phase;
        this.contestWrapper = wrapper;
        this.last = last;
    }

    public ContestPhase getPhase() {
        return phase;
    }

    public PlanType getPlanType() throws SystemException, PortalException {
        return ContestLocalServiceUtil.getPlanType(ContestPhaseLocalServiceUtil.getContest(phase));
    }

    public String getName() throws PortalException, SystemException {
        return ContestPhaseLocalServiceUtil.getName(phase);
    }

    public Date getStartDate() {
        return phase.getPhaseStartDate();
    }

    public Date getEndDate() {
       return phase.getPhaseEndDate();
       // return new Date();
    }
    
    public boolean isAlreadyStarted() {
    	return phase.getPhaseStartDate().before(new Date());
    }

    public ContestStatus getStatus() throws PortalException, SystemException {
        String status = ContestPhaseLocalServiceUtil.getContestStatusStr(phase);
        if (status != null) {
            return ContestStatus.valueOf(status);
        }
        return null;
    }

    public boolean getCanVote() throws PortalException, SystemException {
        return getStatus().isCanVote();
    }

    public boolean getCanEdit() throws PortalException, SystemException {
        return getStatus().isCanEdit();
    }

    public Long getPhaseId() {
        return phase.getContestPhasePK();
    }

    public ContestWrapper getContest() {
        return contestWrapper;
    }
    
    public String getDescription() throws PortalException, SystemException {
        return getPhaseStatusDescription();
    }

    public List<PlanItem> getPlans() throws SystemException, PortalException {

        try {
            List<PlanItem> result =  ContestPhaseLocalServiceUtil.getPlans(phase);
            return result;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return Collections.emptyList();
    }

   
    public boolean isFirstPhase() {
        for (ContestPhaseWrapper phase: contestWrapper.getPhases()) {
            if (phase.getStartDate().compareTo(getStartDate()) < 0) {
                return false ;
            }
        }
        return true;
    }
    
    public boolean isActive() {
        return ContestPhaseLocalServiceUtil.getPhaseActive(phase);
    }
    

    
    public boolean getHasNextPhase() {
        boolean hasNext = false;
        boolean currentFound = false;
        for (ContestPhaseWrapper phase: contestWrapper.getPhases()) {
            if (currentFound) {
                hasNext = true;
            }
            if (phase.getPhaseId().equals(this.phase.getContestPhasePK())) {
                currentFound = true;
            }
        }
        return hasNext;
    }
    
    public long getDaysTillEnd() {
        //Interval i = new Interval(arg0, arg1)
        long nowTime = new Date().getTime();
        if (phase.getPhaseEndDate() != null) {
            long endTime = phase.getPhaseEndDate().getTime();
            return (endTime - nowTime) / (24 * 60 * 60 * 1000);
        }
        return 0;
    }
    
    public long getHoursTillEnd() {
        //Interval i = new Interval(arg0, arg1)
        long nowTime = new Date().getTime();
        if (phase.getPhaseEndDate() != null) {
            long endTime = phase.getPhaseEndDate().getTime();
            return (endTime - nowTime) / (60 * 60 * 1000);
        }
        return 0;
    }
    
    public long getMinutesTillEnd() {
        //Interval i = new Interval(arg0, arg1)
        long nowTime = new Date().getTime();
        if (phase.getPhaseEndDate() != null) {
            long endTime = phase.getPhaseEndDate().getTime();
            return (endTime - nowTime) / (60 * 1000);
        }
        return 0;
    }
    
    public boolean isLast() {
    	return last;
    }
    
    public boolean isEnded() {
    	if (phase.getPhaseEndDate() != null) {
    		return phase.getPhaseEndDate().before(new Date());
    	}
    	return false;
    }
    
    public String getPhaseStatusDescription() throws PortalException, SystemException {
    	String descriptionOverride = phase.getContestPhaseDescriptionOverride();
    	if (StringUtils.isBlank(descriptionOverride)) {
    		try {
    			return ContestPhaseTypeLocalServiceUtil.getContestPhaseType(phase.getContestPhaseType()).getDescription();
    		}
    		catch (NoSuchContestPhaseException e) {
    			// ignore
    		}
    		return null;
    	}
    	return descriptionOverride;
    }
    
    public ContestStatus getContestPhaseStatus() throws PortalException, SystemException {
        return getStatus();
    }
}

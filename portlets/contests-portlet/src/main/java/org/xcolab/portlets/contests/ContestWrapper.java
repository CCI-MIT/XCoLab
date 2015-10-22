package org.xcolab.portlets.contests;


import java.io.Serializable;

import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 6, 2010
 * Time: 2:53:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contest contest;
    
    public boolean flag;
    
     public void setFlag(boolean b) {
        flag = b;
    }

    public boolean getFlag() {
        return flag;
    }

    public ContestWrapper(Contest contest) throws SystemException, PortalException {
        this.contest = contest;
    }

    public String getName() {
        return contest.getContestName();

    }


    public Contest getContest() {
        return contest;
    }

    public String getShortName() {
        return contest.getContestShortName();
    }
    
    public String getLogo() throws PortalException, SystemException {
        //return Helper.getThemeDisplay().getPathImage() + ContestLocalServiceUtil.getLogoPath(contest);
    	return null;
    }
    
    public boolean isFeatured() {
        return contest.getFlagText().toLowerCase().equals("featured");
    }
   
    
    public boolean isContestActive() {
        return contest.getContestActive();
    }

    public Long getContestId() {
        return contest.getContestPK();
    }

    
    public long getProposalsCount() throws SystemException, PortalException {
        return ContestLocalServiceUtil.getProposalsCount(contest);
    }
    
    public long getTotalCommentsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getTotalCommentsCount(contest);
    }
    
    public long getCommentsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getCommentsCount(contest);
    }
    
    public long getPrimaryKey() {
        return contest.getPrimaryKey();
    }
    
    public String getLogoPath() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getLogoPath(contest);
    }
    
    public String getContestShortName() {
        return contest.getContestShortName();
    }
    
    public String getContestName() {
        return contest.getContestName();
    }
    
    public long getTotalComments() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getTotalComments(contest);
    }
    
    public boolean getContestInVotingPhase() throws SystemException, PortalException {
        ContestPhase phase = ContestLocalServiceUtil.getActivePhase(contest);
        if (phase == null) return false;

        String status = ContestPhaseLocalServiceUtil.getContestStatusStr(phase);
        if (status != null) {
            return ContestStatus.valueOf(status).isCanVote();
        }
        return false;
    }
    
    public long getVotesCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getVotesCount(contest);
    }
}


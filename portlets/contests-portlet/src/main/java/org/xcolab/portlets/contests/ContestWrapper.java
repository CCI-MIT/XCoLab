package org.xcolab.portlets.contests;


import javax.faces.event.ActionEvent;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Aug 6, 2010
 * Time: 2:53:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestWrapper {
    private Contest contest;
    private String debatesIdsStr = null;
    
    public boolean flag;
    
     public void setFlag(boolean b) {
        flag = b;
    }

    public boolean getFlag() {
        return flag;
    }

    public void test(ActionEvent e) {
        setFlag(!flag);
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
        return Helper.getThemeDisplay().getPathImage() + ContestLocalServiceUtil.getLogoPath(contest);
    }
    
    public boolean isFeatured() {
        return contest.getFlagText().toLowerCase().equals("featured");
    }
   
    
    public boolean isContestActive() {
        return contest.getContestActive();
    }
    
    public Long getModelId() throws PortalException, SystemException {
        
        return ContestLocalServiceUtil.getPlanType(contest).getDefaultModelId();
    }
    
    
    public Long getContestId() {
        return contest.getContestPK();
    }
    
    public boolean getHasModel() throws PortalException, SystemException {
        Long modelId = ContestLocalServiceUtil.getPlanType(contest).getDefaultModelId();
        return modelId != null && modelId > 0;
    }
    
    public long getProposalsCount() throws SystemException, PortalException {
        return ContestLocalServiceUtil.getProposalsCount(contest);
    }
    
    public long getCommentsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getTotalComments(contest);
        
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
    
}

package org.xcolab.portlets.plansadmin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.liferay.portal.kernel.exception.PortalException;

import org.xcolab.portlets.plansadmin.wrappers.ContestWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.PointsLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class ContestsAdmin {
    private ContestWrapper editedContest;
    
    public List<ContestWrapper> getContests() throws SystemException, PortalException {
        List<ContestWrapper> ret = new ArrayList<ContestWrapper>();
        
        for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            ret.add(new ContestWrapper(contest));
        }
        
        return ret;
    }
    
    public void editContestActionListener(ActionEvent e) {
        editedContest = (ContestWrapper) e.getComponent().getAttributes().get("contest");
    }
    
    public String addContest() throws Exception {
        editedContest = new ContestWrapper(ContestLocalServiceUtil.createNewContest(10144L, "created contest"));
        return "editContest";
    }
    
    public String editContest() {
        return "editContest";
    }
    

    public String recalculatePoints() throws PortalException, SystemException {
    	Contest contest = editedContest.getContest();
    	PointsLocalServiceUtil.distributePoints(contest.getContestPK());
    	return null;
    }

    public void setEditedContest(ContestWrapper editedContest) {
        this.editedContest = editedContest;
    }

    public ContestWrapper getEditedContest() {
        return editedContest;
    }

}

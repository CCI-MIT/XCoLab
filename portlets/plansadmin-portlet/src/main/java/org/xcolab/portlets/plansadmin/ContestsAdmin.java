package org.xcolab.portlets.plansadmin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.xcolab.portlets.plansadmin.wrappers.ContestWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class ContestsAdmin {
    private ContestWrapper editedContest;
    
    public List<ContestWrapper> getContests() throws SystemException {
        List<ContestWrapper> ret = new ArrayList<ContestWrapper>();
        
        for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            ret.add(new ContestWrapper(contest));
        }
        
        return ret;
    }
    
    public void editCotnestActionListener(ActionEvent e) {
        editedContest = (ContestWrapper) e.getComponent().getAttributes().get("contest");
    }
    
    public String addContest() throws SystemException {
        editedContest = new ContestWrapper(ContestLocalServiceUtil.createContest(0));
        return "editContest";
    }
    
    public String editContest() {
        return "editContest";
    }

    public void setEditedContest(ContestWrapper editedContest) {
        this.editedContest = editedContest;
    }

    public ContestWrapper getEditedContest() {
        return editedContest;
    }

}

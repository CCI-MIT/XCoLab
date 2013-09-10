package org.xcolab.portlets.plansadmin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.xcolab.portlets.plansadmin.wrappers.ContestPhaseTypeWrapper;

import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class ContestPhaseTypeBean {
    private List<ContestPhaseTypeWrapper> contestPhaseTypes = new ArrayList<>();
    private List<SelectItem> contestPhaseSelectItems = new ArrayList<>();
    private ContestPhaseTypeWrapper newContestPhaseType;
    
    public void refresh() throws SystemException {
        contestPhaseTypes.clear();
        contestPhaseSelectItems.clear();
        for (ContestPhaseType cpt: ContestPhaseTypeLocalServiceUtil.getContestPhaseTypes(0, Integer.MAX_VALUE)) {
            contestPhaseTypes.add(new ContestPhaseTypeWrapper(cpt, this));
            contestPhaseSelectItems.add(new SelectItem(cpt.getId(), cpt.getId() + " - " + cpt.getName()));
        }
        newContestPhaseType = new ContestPhaseTypeWrapper(ContestPhaseTypeLocalServiceUtil.createContestPhaseType(0), this);
        
    }
    
    public ContestPhaseTypeBean() throws SystemException {
        refresh();
    }

    public List<ContestPhaseTypeWrapper> getContestPhaseTypes() {
        return contestPhaseTypes;
    }
    
    public ContestPhaseTypeWrapper getNewContestPhaseType() {
        return newContestPhaseType;
    }

    public List<SelectItem> getContestPhaseSelectItems() {
        return contestPhaseSelectItems;
    }

    
    
}


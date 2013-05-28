package org.xcolab.portlets.plansadmin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.xcolab.portlets.plansadmin.wrappers.PlanSectionDefinitionWrapper;

import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanSectionDefinitionsAdmin implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlanSectionDefinitionWrapper edited;
    
    public List<PlanSectionDefinitionWrapper> getSectionDefinitions() throws SystemException {
        List<PlanSectionDefinitionWrapper> ret = new ArrayList<PlanSectionDefinitionWrapper>();
        
        for (PlanSectionDefinition def: PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitions(0, Integer.MAX_VALUE)) {
            ret.add(new PlanSectionDefinitionWrapper(def));
        }
        
        return ret;
    }
    
    public void editDefinitionActionListener(ActionEvent e) {
        edited = (PlanSectionDefinitionWrapper) e.getComponent().getAttributes().get("definition");
    }
    
    public String createNew() {
        edited = new PlanSectionDefinitionWrapper(PlanSectionDefinitionLocalServiceUtil.createPlanSectionDefinition(0));
        
        return "editPlanSectionDefinition";
    }
    
    

    public void setEdited(PlanSectionDefinitionWrapper edited) {
        this.edited = edited;
    }

    public PlanSectionDefinitionWrapper getEdited() {
        return edited;
    }
    

    

}

package org.xcolab.portlets.plansadmin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.xcolab.portlets.plansadmin.wrappers.PlanTemplateWrapper;

import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanTemplatesAdmin implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlanTemplateWrapper edited;
    
    public List<PlanTemplateWrapper> getPlanTemplates() throws SystemException, PortalException {
        List<PlanTemplateWrapper> ret = new ArrayList<PlanTemplateWrapper>();
        
        for (PlanTemplate def: PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE)) {
            ret.add(new PlanTemplateWrapper(def));
        }
        
        return ret;
    }
    
    public void edit(ActionEvent e) {
        edited = (PlanTemplateWrapper) e.getComponent().getAttributes().get("template");
    }
    
    public String createNew() throws PortalException, SystemException {
        edited = new PlanTemplateWrapper(PlanTemplateLocalServiceUtil.createPlanTemplate(0));
        
        return "editPlanTemplate";
    }
    
    

    public void setEdited(PlanTemplateWrapper edited) {
        this.edited = edited;
    }

    public PlanTemplateWrapper getEdited() {
        return edited;
    }

}

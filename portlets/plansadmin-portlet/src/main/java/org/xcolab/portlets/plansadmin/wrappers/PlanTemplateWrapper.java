package org.xcolab.portlets.plansadmin.wrappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.PlanTemplateSection;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanTemplateWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlanTemplate template;
    private PlanSectionDefinition newSection = null;

    public PlanTemplateWrapper(PlanTemplate template) throws PortalException, SystemException {
        this.template = template;
    }
    

    public void setTemplate(PlanTemplate template) {
        this.template = template;
    }

    public PlanTemplate getTemplate() {
        return template;
        
    }
    
    public String getName() {
        return template.getName();
    }
    
    public List<PlanSectionDefinitionWrapper> getSections() throws PortalException, SystemException {
        List<PlanSectionDefinitionWrapper> ret = new ArrayList<>();
        
        for (PlanSectionDefinition def: PlanTemplateLocalServiceUtil.getSections(template)) {
            ret.add(new PlanSectionDefinitionWrapper(def));
        }
        return ret;
    }
    
    

    public String save() throws SystemException {
        PlanTemplateLocalServiceUtil.store(template);
        
        return "backToIndex";
    }
    
    
    public List<SelectItem> getAvailableSections() throws SystemException, PortalException {
        List<PlanSectionDefinition> available = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinitions(0, Integer.MAX_VALUE);
        List<PlanSectionDefinition> used = PlanTemplateLocalServiceUtil.getSections(template);
        
        List<SelectItem> ret = new ArrayList<SelectItem>();
        for (PlanSectionDefinition av: available) {
            if (used.contains(av)) continue;
            ret.add(new SelectItem(av.getId(), av.getAdminTitle() + " - " + av.getTitle()));
        }
        
        return ret;
    }


    public void setNewSection(PlanSectionDefinition newSection) {
        this.newSection = newSection;
    }
    
    public void addSection(ActionEvent e) throws PortalException, SystemException {
        if (newSection != null) PlanTemplateLocalServiceUtil.addSection(template, newSection);
    }


    public PlanSectionDefinition getNewSection() {
        return newSection;
    }
    
    public void newSection(ValueChangeEvent e) throws NumberFormatException, PortalException, SystemException {
        if (e.getNewValue() == null || e.getNewValue().toString().trim().equals("")) return;
        newSection = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(Long.parseLong(e.getNewValue().toString()));
    }
    
    public void reorderSection(ActionEvent e) throws SystemException {
        PlanSectionDefinitionWrapper def = (PlanSectionDefinitionWrapper) e.getComponent().getAttributes().get("section");
        int direction = Integer.parseInt(e.getComponent().getAttributes().get("direction").toString());
        
        PlanTemplateSection prev = null, next = null, current = null;
        boolean found = false;
        for (PlanTemplateSection pts: PlanTemplateSectionLocalServiceUtil.findByPlanTemplateId(template.getId())) {
            if (pts.getPlanSectionId() == def.getDefinition().getId()) {
                found = true;
                current = pts;
            }
            else {
                if (!found) prev = pts;
                else {
                    next = pts;
                    break;
                }
            }
        }

        int tmp = current.getWeight();
        if (direction < 0 && prev != null) {
            current.setWeight(prev.getWeight());
            prev.setWeight(tmp);
            
            PlanTemplateSectionLocalServiceUtil.store(current);
            PlanTemplateSectionLocalServiceUtil.store(prev);
        }
        if (direction > 0 && next != null) {
            current.setWeight(next.getWeight());
            next.setWeight(tmp);
            
            PlanTemplateSectionLocalServiceUtil.store(next);
            PlanTemplateSectionLocalServiceUtil.store(current);
        }
        
    }
    
    public void removeSection(ActionEvent e) throws SystemException, PortalException {
        PlanSectionDefinitionWrapper def = (PlanSectionDefinitionWrapper) e.getComponent().getAttributes().get("section");
        PlanTemplateLocalServiceUtil.removeSection(template, def.getDefinition());
    }
    
    public void setName(String name) {
        template.setName(name);
    }

}

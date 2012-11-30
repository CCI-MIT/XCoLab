package org.climatecollaboratorium.plans.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.climatecollaboratorium.plans.Helper;
import org.climatecollaboratorium.plans.utils.ContentFilteringHelper;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanSectionWrapper {
    private PlanSection section;
    private boolean editing;
    private PlanItemWrapper piw;
    private List<Long> referencedPlans;
    private String oryginalContent;
    private boolean last;
    

    public PlanSectionWrapper(PlanSection section, PlanItemWrapper planItemWrapper, boolean last) throws NoSuchPlanItemException, SystemException {
        this.section = section;
        piw = planItemWrapper;
        referencedPlans = new ArrayList<Long>();
        for (PlanItem plan: section.getReferencedPlans()) {
            referencedPlans.add(plan.getId());
        }
        oryginalContent = section.getContent();
        
        this.setLast(last);
        
    }

    public PlanSection getSection() {
        return section;
    }

    public void setSection(PlanSection section) {
        this.section = section;
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public void toggleEditing(ActionEvent e) {
        editing = !editing;
    }
    
    public boolean save(ActionEvent e) throws NoSuchPlanItemException, PortalException, SystemException {
        boolean changed = false;
        if (Helper.isUserLoggedIn() && !section.getContent().trim().equals(oryginalContent)) {
            
            PlanItemLocalServiceUtil.getPlan(section.getPlanId())
                    .setSectionContent(section.getDefinition(), ContentFilteringHelper.removeStylingFromHTMLContent(section.getContent()), referencedPlans, Helper.getLiferayUser().getUserId());
            changed = true;
        }
        toggleEditing(e);
        
        return changed;
        
    }
    
    public List<SelectItem> getPlansForReference() throws PortalException, SystemException {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        if (section.getDefinition().getFocusArea() != null) {
            for (PlanItem plan: PlanItemLocalServiceUtil.findPlansForFocusArea(section.getDefinition().getFocusArea())) {
                ret.add(new SelectItem(plan.getPlanId(), plan.getName()));
            }
        }

        return ret;
    }
    
    public List<Long> getReferencedPlanIds() throws NoSuchPlanItemException, SystemException {
        return referencedPlans;
    }
    
    public void setReferencedPlanIds(List<String> plans) {
        referencedPlans.clear();
        for (String planId: plans) {
            referencedPlans.add(Long.parseLong(planId));
        }
        
        
    }

    public void setOryginalContent(String oryginalContent) {
        this.oryginalContent = oryginalContent;
    }

    public String getOriginalContent() {
        return oryginalContent;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isLast() {
        return last;
    }
    
    
    

}

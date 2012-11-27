package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.service.PlanTemplateSectionLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the PlanTemplateSection service. Represents a row in the &quot;plansProposalsFacade_PlanTemplateSection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanTemplateSection} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanTemplateSectionImpl extends PlanTemplateSectionBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan template section model instance should use the {@link com.ext.portlet.plans.model.PlanTemplateSection} interface instead.
     */
    public PlanTemplateSectionImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(this);
        else  PlanTemplateSectionLocalServiceUtil.updatePlanTemplateSection(this);
    }
    
    public void remove() throws SystemException {
        PlanTemplateSectionLocalServiceUtil.deletePlanTemplateSection(this);
    }
    
}

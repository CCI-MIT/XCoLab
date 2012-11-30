package com.ext.portlet.plans.model.impl;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the PlanSectionDefinition service. Represents a row in the &quot;plansProposalsFacade_PlanSectionDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanSectionDefinition} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanSectionDefinitionImpl extends PlanSectionDefinitionBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan section definition model instance should use the {@link com.ext.portlet.plans.model.PlanSectionDefinition} interface instead.
     */
    public PlanSectionDefinitionImpl() {
    }

    public void store() throws SystemException {
        if (isNew()) {
            if (getId() == null || getId() <= 0) {
                this.setId(CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName()));
            }
            
            PlanSectionDefinitionLocalServiceUtil.addPlanSectionDefinition(this);
        }
        else {
            PlanSectionDefinitionLocalServiceUtil.updatePlanSectionDefinition(this);
        }
    }
    
    public FocusArea getFocusArea() throws PortalException, SystemException {
        if (getFocusAreaId() != null) {
            return FocusAreaLocalServiceUtil.getFocusArea(getFocusAreaId());
        }
        return null;
    }
}

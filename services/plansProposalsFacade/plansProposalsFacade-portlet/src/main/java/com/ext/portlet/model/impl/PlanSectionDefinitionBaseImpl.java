package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the PlanSectionDefinition service. Represents a row in the &quot;xcolab_PlanSectionDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PlanSectionDefinitionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinitionImpl
 * @see com.ext.portlet.model.PlanSectionDefinition
 * @generated
 */
public abstract class PlanSectionDefinitionBaseImpl
    extends PlanSectionDefinitionModelImpl implements PlanSectionDefinition {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a plan section definition model instance should use the {@link PlanSectionDefinition} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanSectionDefinitionLocalServiceUtil.addPlanSectionDefinition(this);
        } else {
            PlanSectionDefinitionLocalServiceUtil.updatePlanSectionDefinition(this);
        }
    }
}

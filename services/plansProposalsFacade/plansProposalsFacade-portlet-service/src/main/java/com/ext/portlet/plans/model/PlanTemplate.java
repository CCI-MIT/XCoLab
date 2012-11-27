package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlanTemplate service. Represents a row in the &quot;Plans_PlanTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateModel
 * @see com.ext.portlet.plans.model.impl.PlanTemplateImpl
 * @see com.ext.portlet.plans.model.impl.PlanTemplateModelImpl
 * @generated
 */
public interface PlanTemplate extends PlanTemplateModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlanTemplateImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> getSections()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addSection(
        com.ext.portlet.plans.model.PlanSectionDefinition section)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void removeSection(
        com.ext.portlet.plans.model.PlanSectionDefinition section)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void updateSectionWeight(
        com.ext.portlet.plans.model.PlanSectionDefinition section, int weight)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}

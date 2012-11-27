package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlanSection service. Represents a row in the &quot;Plans_PlanSection&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionModel
 * @see com.ext.portlet.plans.model.impl.PlanSectionImpl
 * @see com.ext.portlet.plans.model.impl.PlanSectionModelImpl
 * @generated
 */
public interface PlanSection extends PlanSectionModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlanSectionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanSectionDefinition getDefinition()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addPlanReference(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getReferencedPlans()
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException;
}

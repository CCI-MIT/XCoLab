package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlanType service. Represents a row in the &quot;Plans_PlanType&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeModel
 * @see com.ext.portlet.plans.model.impl.PlanTypeImpl
 * @see com.ext.portlet.plans.model.impl.PlanTypeModelImpl
 * @generated
 */
public interface PlanType extends PlanTypeModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlanTypeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.util.List<edu.mit.cci.simulation.client.Simulation> getAvailableModels()
        throws com.liferay.portal.kernel.exception.SystemException;

    public edu.mit.cci.simulation.client.Simulation getDefaultModel()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeColumn> getColumns()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getAttributes()
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean isRegional()
        throws com.liferay.portal.kernel.exception.SystemException;
}

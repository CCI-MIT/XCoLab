package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlansUserSettings service. Represents a row in the &quot;Plans_PlansUserSettings&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettingsModel
 * @see com.ext.portlet.plans.model.impl.PlansUserSettingsImpl
 * @see com.ext.portlet.plans.model.impl.PlansUserSettingsModelImpl
 * @generated
 */
public interface PlansUserSettings extends PlansUserSettingsModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlansUserSettingsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public com.ext.portlet.plans.model.PlanColumnSettings getColumnSettings(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings settings);

    public java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getUpdatedColumnSettings();

    /**
    * Getter for positionsIds.
    *
    * @return the positionsIds
    */
    public java.util.List<java.lang.Long> getPositionsIds();

    /**
    * Setter for positionsIds.
    *
    * @param positionsIds the positionsIds to set
    */
    public void setPositionsIds(java.util.List<java.lang.Long> positionsIds);

    public com.ext.portlet.plans.model.PlanAttributeFilter getAttributeFilter(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addPlanAttributeFilter(
        com.ext.portlet.plans.model.PlanAttributeFilter filter);

    @java.lang.SuppressWarnings(value = "unchecked")
    public java.util.List<com.ext.portlet.plans.model.PlanAttributeFilter> getUpdatedPlanAttributeFilters();

    public com.ext.portlet.plans.model.PlanPropertyFilter getPropertyFilter(
        java.lang.String name)
        throws com.ext.portlet.plans.NoSuchPlanPropertyFilterException,
            com.liferay.portal.kernel.exception.SystemException;

    public void addPlanPropertyFilter(
        com.ext.portlet.plans.model.PlanPropertyFilter filter);

    @java.lang.SuppressWarnings(value = "unchecked")
    public java.util.List<com.ext.portlet.plans.model.PlanPropertyFilter> getUpdatedPlanPropertyFilters();

    public boolean isFiltersDefined();
}

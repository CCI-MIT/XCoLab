package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanAttributeFilter}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanAttributeFilter
 * @generated
 */
public class PlanAttributeFilterWrapper implements PlanAttributeFilter,
    ModelWrapper<PlanAttributeFilter> {
    private PlanAttributeFilter _planAttributeFilter;

    public PlanAttributeFilterWrapper(PlanAttributeFilter planAttributeFilter) {
        _planAttributeFilter = planAttributeFilter;
    }

    public Class<?> getModelClass() {
        return PlanAttributeFilter.class;
    }

    public String getModelClassName() {
        return PlanAttributeFilter.class.getName();
    }

    /**
    * Returns the primary key of this plan attribute filter.
    *
    * @return the primary key of this plan attribute filter
    */
    public long getPrimaryKey() {
        return _planAttributeFilter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan attribute filter.
    *
    * @param primaryKey the primary key of this plan attribute filter
    */
    public void setPrimaryKey(long primaryKey) {
        _planAttributeFilter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan attribute filter ID of this plan attribute filter.
    *
    * @return the plan attribute filter ID of this plan attribute filter
    */
    public long getPlanAttributeFilterId() {
        return _planAttributeFilter.getPlanAttributeFilterId();
    }

    /**
    * Sets the plan attribute filter ID of this plan attribute filter.
    *
    * @param planAttributeFilterId the plan attribute filter ID of this plan attribute filter
    */
    public void setPlanAttributeFilterId(long planAttributeFilterId) {
        _planAttributeFilter.setPlanAttributeFilterId(planAttributeFilterId);
    }

    /**
    * Returns the attribute name of this plan attribute filter.
    *
    * @return the attribute name of this plan attribute filter
    */
    public java.lang.String getAttributeName() {
        return _planAttributeFilter.getAttributeName();
    }

    /**
    * Sets the attribute name of this plan attribute filter.
    *
    * @param attributeName the attribute name of this plan attribute filter
    */
    public void setAttributeName(java.lang.String attributeName) {
        _planAttributeFilter.setAttributeName(attributeName);
    }

    /**
    * Returns the plan user settings ID of this plan attribute filter.
    *
    * @return the plan user settings ID of this plan attribute filter
    */
    public long getPlanUserSettingsId() {
        return _planAttributeFilter.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plan attribute filter.
    *
    * @param planUserSettingsId the plan user settings ID of this plan attribute filter
    */
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planAttributeFilter.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the max of this plan attribute filter.
    *
    * @return the max of this plan attribute filter
    */
    public java.lang.Double getMax() {
        return _planAttributeFilter.getMax();
    }

    /**
    * Sets the max of this plan attribute filter.
    *
    * @param max the max of this plan attribute filter
    */
    public void setMax(java.lang.Double max) {
        _planAttributeFilter.setMax(max);
    }

    /**
    * Returns the min of this plan attribute filter.
    *
    * @return the min of this plan attribute filter
    */
    public java.lang.Double getMin() {
        return _planAttributeFilter.getMin();
    }

    /**
    * Sets the min of this plan attribute filter.
    *
    * @param min the min of this plan attribute filter
    */
    public void setMin(java.lang.Double min) {
        _planAttributeFilter.setMin(min);
    }

    /**
    * Returns the string val of this plan attribute filter.
    *
    * @return the string val of this plan attribute filter
    */
    public java.lang.String getStringVal() {
        return _planAttributeFilter.getStringVal();
    }

    /**
    * Sets the string val of this plan attribute filter.
    *
    * @param stringVal the string val of this plan attribute filter
    */
    public void setStringVal(java.lang.String stringVal) {
        _planAttributeFilter.setStringVal(stringVal);
    }

    public boolean isNew() {
        return _planAttributeFilter.isNew();
    }

    public void setNew(boolean n) {
        _planAttributeFilter.setNew(n);
    }

    public boolean isCachedModel() {
        return _planAttributeFilter.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planAttributeFilter.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planAttributeFilter.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planAttributeFilter.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planAttributeFilter.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planAttributeFilter.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planAttributeFilter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanAttributeFilterWrapper((PlanAttributeFilter) _planAttributeFilter.clone());
    }

    public int compareTo(PlanAttributeFilter planAttributeFilter) {
        return _planAttributeFilter.compareTo(planAttributeFilter);
    }

    @Override
    public int hashCode() {
        return _planAttributeFilter.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanAttributeFilter> toCacheModel() {
        return _planAttributeFilter.toCacheModel();
    }

    public PlanAttributeFilter toEscapedModel() {
        return new PlanAttributeFilterWrapper(_planAttributeFilter.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planAttributeFilter.toString();
    }

    public java.lang.String toXmlString() {
        return _planAttributeFilter.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planAttributeFilter.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanAttributeFilter getWrappedPlanAttributeFilter() {
        return _planAttributeFilter;
    }

    public PlanAttributeFilter getWrappedModel() {
        return _planAttributeFilter;
    }

    public void resetOriginalValues() {
        _planAttributeFilter.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanPropertyFilter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPropertyFilter
 * @generated
 */
public class PlanPropertyFilterWrapper implements PlanPropertyFilter,
    ModelWrapper<PlanPropertyFilter> {
    private PlanPropertyFilter _planPropertyFilter;

    public PlanPropertyFilterWrapper(PlanPropertyFilter planPropertyFilter) {
        _planPropertyFilter = planPropertyFilter;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanPropertyFilter.class;
    }

    @Override
    public String getModelClassName() {
        return PlanPropertyFilter.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planPropertyFilterId", getPlanPropertyFilterId());
        attributes.put("propertyName", getPropertyName());
        attributes.put("planUserSettingsId", getPlanUserSettingsId());
        attributes.put("value", getValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planPropertyFilterId = (Long) attributes.get(
                "planPropertyFilterId");

        if (planPropertyFilterId != null) {
            setPlanPropertyFilterId(planPropertyFilterId);
        }

        String propertyName = (String) attributes.get("propertyName");

        if (propertyName != null) {
            setPropertyName(propertyName);
        }

        Long planUserSettingsId = (Long) attributes.get("planUserSettingsId");

        if (planUserSettingsId != null) {
            setPlanUserSettingsId(planUserSettingsId);
        }

        String value = (String) attributes.get("value");

        if (value != null) {
            setValue(value);
        }
    }

    /**
    * Returns the primary key of this plan property filter.
    *
    * @return the primary key of this plan property filter
    */
    @Override
    public long getPrimaryKey() {
        return _planPropertyFilter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan property filter.
    *
    * @param primaryKey the primary key of this plan property filter
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planPropertyFilter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan property filter ID of this plan property filter.
    *
    * @return the plan property filter ID of this plan property filter
    */
    @Override
    public long getPlanPropertyFilterId() {
        return _planPropertyFilter.getPlanPropertyFilterId();
    }

    /**
    * Sets the plan property filter ID of this plan property filter.
    *
    * @param planPropertyFilterId the plan property filter ID of this plan property filter
    */
    @Override
    public void setPlanPropertyFilterId(long planPropertyFilterId) {
        _planPropertyFilter.setPlanPropertyFilterId(planPropertyFilterId);
    }

    /**
    * Returns the property name of this plan property filter.
    *
    * @return the property name of this plan property filter
    */
    @Override
    public java.lang.String getPropertyName() {
        return _planPropertyFilter.getPropertyName();
    }

    /**
    * Sets the property name of this plan property filter.
    *
    * @param propertyName the property name of this plan property filter
    */
    @Override
    public void setPropertyName(java.lang.String propertyName) {
        _planPropertyFilter.setPropertyName(propertyName);
    }

    /**
    * Returns the plan user settings ID of this plan property filter.
    *
    * @return the plan user settings ID of this plan property filter
    */
    @Override
    public long getPlanUserSettingsId() {
        return _planPropertyFilter.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plan property filter.
    *
    * @param planUserSettingsId the plan user settings ID of this plan property filter
    */
    @Override
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planPropertyFilter.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the value of this plan property filter.
    *
    * @return the value of this plan property filter
    */
    @Override
    public java.lang.String getValue() {
        return _planPropertyFilter.getValue();
    }

    /**
    * Sets the value of this plan property filter.
    *
    * @param value the value of this plan property filter
    */
    @Override
    public void setValue(java.lang.String value) {
        _planPropertyFilter.setValue(value);
    }

    @Override
    public boolean isNew() {
        return _planPropertyFilter.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planPropertyFilter.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planPropertyFilter.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planPropertyFilter.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planPropertyFilter.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planPropertyFilter.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planPropertyFilter.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planPropertyFilter.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planPropertyFilter.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planPropertyFilter.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planPropertyFilter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanPropertyFilterWrapper((PlanPropertyFilter) _planPropertyFilter.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.PlanPropertyFilter planPropertyFilter) {
        return _planPropertyFilter.compareTo(planPropertyFilter);
    }

    @Override
    public int hashCode() {
        return _planPropertyFilter.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanPropertyFilter> toCacheModel() {
        return _planPropertyFilter.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanPropertyFilter toEscapedModel() {
        return new PlanPropertyFilterWrapper(_planPropertyFilter.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanPropertyFilter toUnescapedModel() {
        return new PlanPropertyFilterWrapper(_planPropertyFilter.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planPropertyFilter.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planPropertyFilter.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPropertyFilter.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanPropertyFilterWrapper)) {
            return false;
        }

        PlanPropertyFilterWrapper planPropertyFilterWrapper = (PlanPropertyFilterWrapper) obj;

        if (Validator.equals(_planPropertyFilter,
                    planPropertyFilterWrapper._planPropertyFilter)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanPropertyFilter getWrappedPlanPropertyFilter() {
        return _planPropertyFilter;
    }

    @Override
    public PlanPropertyFilter getWrappedModel() {
        return _planPropertyFilter;
    }

    @Override
    public void resetOriginalValues() {
        _planPropertyFilter.resetOriginalValues();
    }
}

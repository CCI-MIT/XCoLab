package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanAttributeFilter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilter
 * @generated
 */
public class PlanAttributeFilterWrapper implements PlanAttributeFilter,
    ModelWrapper<PlanAttributeFilter> {
    private PlanAttributeFilter _planAttributeFilter;

    public PlanAttributeFilterWrapper(PlanAttributeFilter planAttributeFilter) {
        _planAttributeFilter = planAttributeFilter;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanAttributeFilter.class;
    }

    @Override
    public String getModelClassName() {
        return PlanAttributeFilter.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planAttributeFilterId", getPlanAttributeFilterId());
        attributes.put("attributeName", getAttributeName());
        attributes.put("planUserSettingsId", getPlanUserSettingsId());
        attributes.put("max", getMax());
        attributes.put("min", getMin());
        attributes.put("stringVal", getStringVal());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planAttributeFilterId = (Long) attributes.get(
                "planAttributeFilterId");

        if (planAttributeFilterId != null) {
            setPlanAttributeFilterId(planAttributeFilterId);
        }

        String attributeName = (String) attributes.get("attributeName");

        if (attributeName != null) {
            setAttributeName(attributeName);
        }

        Long planUserSettingsId = (Long) attributes.get("planUserSettingsId");

        if (planUserSettingsId != null) {
            setPlanUserSettingsId(planUserSettingsId);
        }

        Double max = (Double) attributes.get("max");

        if (max != null) {
            setMax(max);
        }

        Double min = (Double) attributes.get("min");

        if (min != null) {
            setMin(min);
        }

        String stringVal = (String) attributes.get("stringVal");

        if (stringVal != null) {
            setStringVal(stringVal);
        }
    }

    /**
    * Returns the primary key of this plan attribute filter.
    *
    * @return the primary key of this plan attribute filter
    */
    @Override
    public long getPrimaryKey() {
        return _planAttributeFilter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan attribute filter.
    *
    * @param primaryKey the primary key of this plan attribute filter
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planAttributeFilter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan attribute filter ID of this plan attribute filter.
    *
    * @return the plan attribute filter ID of this plan attribute filter
    */
    @Override
    public long getPlanAttributeFilterId() {
        return _planAttributeFilter.getPlanAttributeFilterId();
    }

    /**
    * Sets the plan attribute filter ID of this plan attribute filter.
    *
    * @param planAttributeFilterId the plan attribute filter ID of this plan attribute filter
    */
    @Override
    public void setPlanAttributeFilterId(long planAttributeFilterId) {
        _planAttributeFilter.setPlanAttributeFilterId(planAttributeFilterId);
    }

    /**
    * Returns the attribute name of this plan attribute filter.
    *
    * @return the attribute name of this plan attribute filter
    */
    @Override
    public java.lang.String getAttributeName() {
        return _planAttributeFilter.getAttributeName();
    }

    /**
    * Sets the attribute name of this plan attribute filter.
    *
    * @param attributeName the attribute name of this plan attribute filter
    */
    @Override
    public void setAttributeName(java.lang.String attributeName) {
        _planAttributeFilter.setAttributeName(attributeName);
    }

    /**
    * Returns the plan user settings ID of this plan attribute filter.
    *
    * @return the plan user settings ID of this plan attribute filter
    */
    @Override
    public long getPlanUserSettingsId() {
        return _planAttributeFilter.getPlanUserSettingsId();
    }

    /**
    * Sets the plan user settings ID of this plan attribute filter.
    *
    * @param planUserSettingsId the plan user settings ID of this plan attribute filter
    */
    @Override
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planAttributeFilter.setPlanUserSettingsId(planUserSettingsId);
    }

    /**
    * Returns the max of this plan attribute filter.
    *
    * @return the max of this plan attribute filter
    */
    @Override
    public java.lang.Double getMax() {
        return _planAttributeFilter.getMax();
    }

    /**
    * Sets the max of this plan attribute filter.
    *
    * @param max the max of this plan attribute filter
    */
    @Override
    public void setMax(java.lang.Double max) {
        _planAttributeFilter.setMax(max);
    }

    /**
    * Returns the min of this plan attribute filter.
    *
    * @return the min of this plan attribute filter
    */
    @Override
    public java.lang.Double getMin() {
        return _planAttributeFilter.getMin();
    }

    /**
    * Sets the min of this plan attribute filter.
    *
    * @param min the min of this plan attribute filter
    */
    @Override
    public void setMin(java.lang.Double min) {
        _planAttributeFilter.setMin(min);
    }

    /**
    * Returns the string val of this plan attribute filter.
    *
    * @return the string val of this plan attribute filter
    */
    @Override
    public java.lang.String getStringVal() {
        return _planAttributeFilter.getStringVal();
    }

    /**
    * Sets the string val of this plan attribute filter.
    *
    * @param stringVal the string val of this plan attribute filter
    */
    @Override
    public void setStringVal(java.lang.String stringVal) {
        _planAttributeFilter.setStringVal(stringVal);
    }

    @Override
    public boolean isNew() {
        return _planAttributeFilter.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planAttributeFilter.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planAttributeFilter.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planAttributeFilter.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planAttributeFilter.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planAttributeFilter.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planAttributeFilter.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planAttributeFilter.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planAttributeFilter.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planAttributeFilter.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planAttributeFilter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanAttributeFilterWrapper((PlanAttributeFilter) _planAttributeFilter.clone());
    }

    @Override
    public int compareTo(PlanAttributeFilter planAttributeFilter) {
        return _planAttributeFilter.compareTo(planAttributeFilter);
    }

    @Override
    public int hashCode() {
        return _planAttributeFilter.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanAttributeFilter> toCacheModel() {
        return _planAttributeFilter.toCacheModel();
    }

    @Override
    public PlanAttributeFilter toEscapedModel() {
        return new PlanAttributeFilterWrapper(_planAttributeFilter.toEscapedModel());
    }

    @Override
    public PlanAttributeFilter toUnescapedModel() {
        return new PlanAttributeFilterWrapper(_planAttributeFilter.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planAttributeFilter.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planAttributeFilter.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planAttributeFilter.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanAttributeFilterWrapper)) {
            return false;
        }

        PlanAttributeFilterWrapper planAttributeFilterWrapper = (PlanAttributeFilterWrapper) obj;

        if (Validator.equals(_planAttributeFilter,
                    planAttributeFilterWrapper._planAttributeFilter)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanAttributeFilter getWrappedPlanAttributeFilter() {
        return _planAttributeFilter;
    }

    @Override
    public PlanAttributeFilter getWrappedModel() {
        return _planAttributeFilter;
    }

    @Override
    public void resetOriginalValues() {
        _planAttributeFilter.resetOriginalValues();
    }
}

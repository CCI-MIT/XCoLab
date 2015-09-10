package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeAttribute}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttribute
 * @generated
 */
public class PlanTypeAttributeWrapper implements PlanTypeAttribute,
    ModelWrapper<PlanTypeAttribute> {
    private PlanTypeAttribute _planTypeAttribute;

    public PlanTypeAttributeWrapper(PlanTypeAttribute planTypeAttribute) {
        _planTypeAttribute = planTypeAttribute;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTypeAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTypeAttribute.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planTypeAttributeId", getPlanTypeAttributeId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("attributeName", getAttributeName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planTypeAttributeId = (Long) attributes.get("planTypeAttributeId");

        if (planTypeAttributeId != null) {
            setPlanTypeAttributeId(planTypeAttributeId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        String attributeName = (String) attributes.get("attributeName");

        if (attributeName != null) {
            setAttributeName(attributeName);
        }
    }

    /**
    * Returns the primary key of this plan type attribute.
    *
    * @return the primary key of this plan type attribute
    */
    @Override
    public long getPrimaryKey() {
        return _planTypeAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan type attribute.
    *
    * @param primaryKey the primary key of this plan type attribute
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planTypeAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan type attribute ID of this plan type attribute.
    *
    * @return the plan type attribute ID of this plan type attribute
    */
    @Override
    public long getPlanTypeAttributeId() {
        return _planTypeAttribute.getPlanTypeAttributeId();
    }

    /**
    * Sets the plan type attribute ID of this plan type attribute.
    *
    * @param planTypeAttributeId the plan type attribute ID of this plan type attribute
    */
    @Override
    public void setPlanTypeAttributeId(long planTypeAttributeId) {
        _planTypeAttribute.setPlanTypeAttributeId(planTypeAttributeId);
    }

    /**
    * Returns the plan type ID of this plan type attribute.
    *
    * @return the plan type ID of this plan type attribute
    */
    @Override
    public long getPlanTypeId() {
        return _planTypeAttribute.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan type attribute.
    *
    * @param planTypeId the plan type ID of this plan type attribute
    */
    @Override
    public void setPlanTypeId(long planTypeId) {
        _planTypeAttribute.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the attribute name of this plan type attribute.
    *
    * @return the attribute name of this plan type attribute
    */
    @Override
    public java.lang.String getAttributeName() {
        return _planTypeAttribute.getAttributeName();
    }

    /**
    * Sets the attribute name of this plan type attribute.
    *
    * @param attributeName the attribute name of this plan type attribute
    */
    @Override
    public void setAttributeName(java.lang.String attributeName) {
        _planTypeAttribute.setAttributeName(attributeName);
    }

    @Override
    public boolean isNew() {
        return _planTypeAttribute.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planTypeAttribute.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planTypeAttribute.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planTypeAttribute.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planTypeAttribute.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planTypeAttribute.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTypeAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTypeAttribute.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planTypeAttribute.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planTypeAttribute.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTypeAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTypeAttributeWrapper((PlanTypeAttribute) _planTypeAttribute.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.PlanTypeAttribute planTypeAttribute) {
        return _planTypeAttribute.compareTo(planTypeAttribute);
    }

    @Override
    public int hashCode() {
        return _planTypeAttribute.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanTypeAttribute> toCacheModel() {
        return _planTypeAttribute.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanTypeAttribute toEscapedModel() {
        return new PlanTypeAttributeWrapper(_planTypeAttribute.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanTypeAttribute toUnescapedModel() {
        return new PlanTypeAttributeWrapper(_planTypeAttribute.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTypeAttribute.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planTypeAttribute.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTypeAttribute.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanTypeAttributeWrapper)) {
            return false;
        }

        PlanTypeAttributeWrapper planTypeAttributeWrapper = (PlanTypeAttributeWrapper) obj;

        if (Validator.equals(_planTypeAttribute,
                    planTypeAttributeWrapper._planTypeAttribute)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanTypeAttribute getWrappedPlanTypeAttribute() {
        return _planTypeAttribute;
    }

    @Override
    public PlanTypeAttribute getWrappedModel() {
        return _planTypeAttribute;
    }

    @Override
    public void resetOriginalValues() {
        _planTypeAttribute.resetOriginalValues();
    }
}

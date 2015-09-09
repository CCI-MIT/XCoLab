package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanAttribute}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttribute
 * @generated
 */
public class PlanAttributeWrapper implements PlanAttribute,
    ModelWrapper<PlanAttribute> {
    private PlanAttribute _planAttribute;

    public PlanAttributeWrapper(PlanAttribute planAttribute) {
        _planAttribute = planAttribute;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return PlanAttribute.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("attributeId", getAttributeId());
        attributes.put("planId", getPlanId());
        attributes.put("attributeName", getAttributeName());
        attributes.put("attributeValue", getAttributeValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long attributeId = (Long) attributes.get("attributeId");

        if (attributeId != null) {
            setAttributeId(attributeId);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        String attributeName = (String) attributes.get("attributeName");

        if (attributeName != null) {
            setAttributeName(attributeName);
        }

        String attributeValue = (String) attributes.get("attributeValue");

        if (attributeValue != null) {
            setAttributeValue(attributeValue);
        }
    }

    /**
    * Returns the primary key of this plan attribute.
    *
    * @return the primary key of this plan attribute
    */
    @Override
    public long getPrimaryKey() {
        return _planAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan attribute.
    *
    * @param primaryKey the primary key of this plan attribute
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the attribute ID of this plan attribute.
    *
    * @return the attribute ID of this plan attribute
    */
    @Override
    public long getAttributeId() {
        return _planAttribute.getAttributeId();
    }

    /**
    * Sets the attribute ID of this plan attribute.
    *
    * @param attributeId the attribute ID of this plan attribute
    */
    @Override
    public void setAttributeId(long attributeId) {
        _planAttribute.setAttributeId(attributeId);
    }

    /**
    * Returns the plan ID of this plan attribute.
    *
    * @return the plan ID of this plan attribute
    */
    @Override
    public long getPlanId() {
        return _planAttribute.getPlanId();
    }

    /**
    * Sets the plan ID of this plan attribute.
    *
    * @param planId the plan ID of this plan attribute
    */
    @Override
    public void setPlanId(long planId) {
        _planAttribute.setPlanId(planId);
    }

    /**
    * Returns the attribute name of this plan attribute.
    *
    * @return the attribute name of this plan attribute
    */
    @Override
    public java.lang.String getAttributeName() {
        return _planAttribute.getAttributeName();
    }

    /**
    * Sets the attribute name of this plan attribute.
    *
    * @param attributeName the attribute name of this plan attribute
    */
    @Override
    public void setAttributeName(java.lang.String attributeName) {
        _planAttribute.setAttributeName(attributeName);
    }

    /**
    * Returns the attribute value of this plan attribute.
    *
    * @return the attribute value of this plan attribute
    */
    @Override
    public java.lang.String getAttributeValue() {
        return _planAttribute.getAttributeValue();
    }

    /**
    * Sets the attribute value of this plan attribute.
    *
    * @param attributeValue the attribute value of this plan attribute
    */
    @Override
    public void setAttributeValue(java.lang.String attributeValue) {
        _planAttribute.setAttributeValue(attributeValue);
    }

    @Override
    public boolean isNew() {
        return _planAttribute.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planAttribute.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planAttribute.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planAttribute.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planAttribute.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planAttribute.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planAttribute.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planAttribute.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planAttribute.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanAttributeWrapper((PlanAttribute) _planAttribute.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlanAttribute planAttribute) {
        return _planAttribute.compareTo(planAttribute);
    }

    @Override
    public int hashCode() {
        return _planAttribute.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanAttribute> toCacheModel() {
        return _planAttribute.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanAttribute toEscapedModel() {
        return new PlanAttributeWrapper(_planAttribute.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanAttribute toUnescapedModel() {
        return new PlanAttributeWrapper(_planAttribute.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planAttribute.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planAttribute.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planAttribute.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanAttributeWrapper)) {
            return false;
        }

        PlanAttributeWrapper planAttributeWrapper = (PlanAttributeWrapper) obj;

        if (Validator.equals(_planAttribute, planAttributeWrapper._planAttribute)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanAttribute getWrappedPlanAttribute() {
        return _planAttribute;
    }

    @Override
    public PlanAttribute getWrappedModel() {
        return _planAttribute;
    }

    @Override
    public void resetOriginalValues() {
        _planAttribute.resetOriginalValues();
    }
}

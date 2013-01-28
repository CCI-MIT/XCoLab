package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanAttribute}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanAttribute
 * @generated
 */
public class PlanAttributeWrapper implements PlanAttribute,
    ModelWrapper<PlanAttribute> {
    private PlanAttribute _planAttribute;

    public PlanAttributeWrapper(PlanAttribute planAttribute) {
        _planAttribute = planAttribute;
    }

    public Class<?> getModelClass() {
        return PlanAttribute.class;
    }

    public String getModelClassName() {
        return PlanAttribute.class.getName();
    }

    /**
    * Returns the primary key of this plan attribute.
    *
    * @return the primary key of this plan attribute
    */
    public long getPrimaryKey() {
        return _planAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan attribute.
    *
    * @param primaryKey the primary key of this plan attribute
    */
    public void setPrimaryKey(long primaryKey) {
        _planAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the attribute ID of this plan attribute.
    *
    * @return the attribute ID of this plan attribute
    */
    public long getAttributeId() {
        return _planAttribute.getAttributeId();
    }

    /**
    * Sets the attribute ID of this plan attribute.
    *
    * @param attributeId the attribute ID of this plan attribute
    */
    public void setAttributeId(long attributeId) {
        _planAttribute.setAttributeId(attributeId);
    }

    /**
    * Returns the plan ID of this plan attribute.
    *
    * @return the plan ID of this plan attribute
    */
    public long getPlanId() {
        return _planAttribute.getPlanId();
    }

    /**
    * Sets the plan ID of this plan attribute.
    *
    * @param planId the plan ID of this plan attribute
    */
    public void setPlanId(long planId) {
        _planAttribute.setPlanId(planId);
    }

    /**
    * Returns the attribute name of this plan attribute.
    *
    * @return the attribute name of this plan attribute
    */
    public java.lang.String getAttributeName() {
        return _planAttribute.getAttributeName();
    }

    /**
    * Sets the attribute name of this plan attribute.
    *
    * @param attributeName the attribute name of this plan attribute
    */
    public void setAttributeName(java.lang.String attributeName) {
        _planAttribute.setAttributeName(attributeName);
    }

    /**
    * Returns the attribute value of this plan attribute.
    *
    * @return the attribute value of this plan attribute
    */
    public java.lang.String getAttributeValue() {
        return _planAttribute.getAttributeValue();
    }

    /**
    * Sets the attribute value of this plan attribute.
    *
    * @param attributeValue the attribute value of this plan attribute
    */
    public void setAttributeValue(java.lang.String attributeValue) {
        _planAttribute.setAttributeValue(attributeValue);
    }

    public boolean isNew() {
        return _planAttribute.isNew();
    }

    public void setNew(boolean n) {
        _planAttribute.setNew(n);
    }

    public boolean isCachedModel() {
        return _planAttribute.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planAttribute.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planAttribute.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planAttribute.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planAttribute.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanAttributeWrapper((PlanAttribute) _planAttribute.clone());
    }

    public int compareTo(PlanAttribute planAttribute) {
        return _planAttribute.compareTo(planAttribute);
    }

    @Override
    public int hashCode() {
        return _planAttribute.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanAttribute> toCacheModel() {
        return _planAttribute.toCacheModel();
    }

    public PlanAttribute toEscapedModel() {
        return new PlanAttributeWrapper(_planAttribute.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planAttribute.toString();
    }

    public java.lang.String toXmlString() {
        return _planAttribute.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planAttribute.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanAttribute getWrappedPlanAttribute() {
        return _planAttribute;
    }

    public PlanAttribute getWrappedModel() {
        return _planAttribute;
    }

    public void resetOriginalValues() {
        _planAttribute.resetOriginalValues();
    }
}

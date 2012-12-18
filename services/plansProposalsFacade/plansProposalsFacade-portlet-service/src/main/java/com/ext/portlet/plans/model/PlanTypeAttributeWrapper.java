package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeAttribute}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTypeAttribute
 * @generated
 */
public class PlanTypeAttributeWrapper implements PlanTypeAttribute,
    ModelWrapper<PlanTypeAttribute> {
    private PlanTypeAttribute _planTypeAttribute;

    public PlanTypeAttributeWrapper(PlanTypeAttribute planTypeAttribute) {
        _planTypeAttribute = planTypeAttribute;
    }

    public Class<?> getModelClass() {
        return PlanTypeAttribute.class;
    }

    public String getModelClassName() {
        return PlanTypeAttribute.class.getName();
    }

    /**
    * Returns the primary key of this plan type attribute.
    *
    * @return the primary key of this plan type attribute
    */
    public long getPrimaryKey() {
        return _planTypeAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan type attribute.
    *
    * @param primaryKey the primary key of this plan type attribute
    */
    public void setPrimaryKey(long primaryKey) {
        _planTypeAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan type attribute ID of this plan type attribute.
    *
    * @return the plan type attribute ID of this plan type attribute
    */
    public long getPlanTypeAttributeId() {
        return _planTypeAttribute.getPlanTypeAttributeId();
    }

    /**
    * Sets the plan type attribute ID of this plan type attribute.
    *
    * @param planTypeAttributeId the plan type attribute ID of this plan type attribute
    */
    public void setPlanTypeAttributeId(long planTypeAttributeId) {
        _planTypeAttribute.setPlanTypeAttributeId(planTypeAttributeId);
    }

    /**
    * Returns the plan type ID of this plan type attribute.
    *
    * @return the plan type ID of this plan type attribute
    */
    public long getPlanTypeId() {
        return _planTypeAttribute.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan type attribute.
    *
    * @param planTypeId the plan type ID of this plan type attribute
    */
    public void setPlanTypeId(long planTypeId) {
        _planTypeAttribute.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the attribute name of this plan type attribute.
    *
    * @return the attribute name of this plan type attribute
    */
    public java.lang.String getAttributeName() {
        return _planTypeAttribute.getAttributeName();
    }

    /**
    * Sets the attribute name of this plan type attribute.
    *
    * @param attributeName the attribute name of this plan type attribute
    */
    public void setAttributeName(java.lang.String attributeName) {
        _planTypeAttribute.setAttributeName(attributeName);
    }

    public boolean isNew() {
        return _planTypeAttribute.isNew();
    }

    public void setNew(boolean n) {
        _planTypeAttribute.setNew(n);
    }

    public boolean isCachedModel() {
        return _planTypeAttribute.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planTypeAttribute.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planTypeAttribute.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planTypeAttribute.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTypeAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTypeAttribute.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTypeAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTypeAttributeWrapper((PlanTypeAttribute) _planTypeAttribute.clone());
    }

    public int compareTo(PlanTypeAttribute planTypeAttribute) {
        return _planTypeAttribute.compareTo(planTypeAttribute);
    }

    @Override
    public int hashCode() {
        return _planTypeAttribute.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanTypeAttribute> toCacheModel() {
        return _planTypeAttribute.toCacheModel();
    }

    public PlanTypeAttribute toEscapedModel() {
        return new PlanTypeAttributeWrapper(_planTypeAttribute.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTypeAttribute.toString();
    }

    public java.lang.String toXmlString() {
        return _planTypeAttribute.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTypeAttribute.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanTypeAttribute getWrappedPlanTypeAttribute() {
        return _planTypeAttribute;
    }

    public PlanTypeAttribute getWrappedModel() {
        return _planTypeAttribute;
    }

    public void resetOriginalValues() {
        _planTypeAttribute.resetOriginalValues();
    }
}

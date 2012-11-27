package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanFan}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanFan
 * @generated
 */
public class PlanFanWrapper implements PlanFan, ModelWrapper<PlanFan> {
    private PlanFan _planFan;

    public PlanFanWrapper(PlanFan planFan) {
        _planFan = planFan;
    }

    public Class<?> getModelClass() {
        return PlanFan.class;
    }

    public String getModelClassName() {
        return PlanFan.class.getName();
    }

    /**
    * Returns the primary key of this plan fan.
    *
    * @return the primary key of this plan fan
    */
    public java.lang.Long getPrimaryKey() {
        return _planFan.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan fan.
    *
    * @param primaryKey the primary key of this plan fan
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _planFan.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan fan.
    *
    * @return the ID of this plan fan
    */
    public java.lang.Long getId() {
        return _planFan.getId();
    }

    /**
    * Sets the ID of this plan fan.
    *
    * @param id the ID of this plan fan
    */
    public void setId(java.lang.Long id) {
        _planFan.setId(id);
    }

    /**
    * Returns the user ID of this plan fan.
    *
    * @return the user ID of this plan fan
    */
    public java.lang.Long getUserId() {
        return _planFan.getUserId();
    }

    /**
    * Sets the user ID of this plan fan.
    *
    * @param userId the user ID of this plan fan
    */
    public void setUserId(java.lang.Long userId) {
        _planFan.setUserId(userId);
    }

    /**
    * Returns the plan ID of this plan fan.
    *
    * @return the plan ID of this plan fan
    */
    public java.lang.Long getPlanId() {
        return _planFan.getPlanId();
    }

    /**
    * Sets the plan ID of this plan fan.
    *
    * @param planId the plan ID of this plan fan
    */
    public void setPlanId(java.lang.Long planId) {
        _planFan.setPlanId(planId);
    }

    /**
    * Returns the created of this plan fan.
    *
    * @return the created of this plan fan
    */
    public java.util.Date getCreated() {
        return _planFan.getCreated();
    }

    /**
    * Sets the created of this plan fan.
    *
    * @param created the created of this plan fan
    */
    public void setCreated(java.util.Date created) {
        _planFan.setCreated(created);
    }

    /**
    * Returns the deleted of this plan fan.
    *
    * @return the deleted of this plan fan
    */
    public java.util.Date getDeleted() {
        return _planFan.getDeleted();
    }

    /**
    * Sets the deleted of this plan fan.
    *
    * @param deleted the deleted of this plan fan
    */
    public void setDeleted(java.util.Date deleted) {
        _planFan.setDeleted(deleted);
    }

    public boolean isNew() {
        return _planFan.isNew();
    }

    public void setNew(boolean n) {
        _planFan.setNew(n);
    }

    public boolean isCachedModel() {
        return _planFan.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planFan.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planFan.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planFan.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planFan.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planFan.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planFan.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanFanWrapper((PlanFan) _planFan.clone());
    }

    public int compareTo(PlanFan planFan) {
        return _planFan.compareTo(planFan);
    }

    @Override
    public int hashCode() {
        return _planFan.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanFan> toCacheModel() {
        return _planFan.toCacheModel();
    }

    public PlanFan toEscapedModel() {
        return new PlanFanWrapper(_planFan.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planFan.toString();
    }

    public java.lang.String toXmlString() {
        return _planFan.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planFan.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planFan.store();
    }

    public com.liferay.portal.model.User getUser()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planFan.getUser();
    }

    public com.ext.portlet.plans.model.PlanItem getPlan()
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planFan.getPlan();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanFan getWrappedPlanFan() {
        return _planFan;
    }

    public PlanFan getWrappedModel() {
        return _planFan;
    }

    public void resetOriginalValues() {
        _planFan.resetOriginalValues();
    }
}

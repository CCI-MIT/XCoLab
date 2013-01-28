package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansFilterPosition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansFilterPosition
 * @generated
 */
public class PlansFilterPositionWrapper implements PlansFilterPosition,
    ModelWrapper<PlansFilterPosition> {
    private PlansFilterPosition _plansFilterPosition;

    public PlansFilterPositionWrapper(PlansFilterPosition plansFilterPosition) {
        _plansFilterPosition = plansFilterPosition;
    }

    public Class<?> getModelClass() {
        return PlansFilterPosition.class;
    }

    public String getModelClassName() {
        return PlansFilterPosition.class.getName();
    }

    /**
    * Returns the primary key of this plans filter position.
    *
    * @return the primary key of this plans filter position
    */
    public com.ext.portlet.service.persistence.PlansFilterPositionPK getPrimaryKey() {
        return _plansFilterPosition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plans filter position.
    *
    * @param primaryKey the primary key of this plans filter position
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlansFilterPositionPK primaryKey) {
        _plansFilterPosition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this plans filter position.
    *
    * @return the user ID of this plans filter position
    */
    public long getUserId() {
        return _plansFilterPosition.getUserId();
    }

    /**
    * Sets the user ID of this plans filter position.
    *
    * @param userId the user ID of this plans filter position
    */
    public void setUserId(long userId) {
        _plansFilterPosition.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plans filter position.
    *
    * @return the user uuid of this plans filter position
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPosition.getUserUuid();
    }

    /**
    * Sets the user uuid of this plans filter position.
    *
    * @param userUuid the user uuid of this plans filter position
    */
    public void setUserUuid(java.lang.String userUuid) {
        _plansFilterPosition.setUserUuid(userUuid);
    }

    /**
    * Returns the plan type ID of this plans filter position.
    *
    * @return the plan type ID of this plans filter position
    */
    public long getPlanTypeId() {
        return _plansFilterPosition.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plans filter position.
    *
    * @param planTypeId the plan type ID of this plans filter position
    */
    public void setPlanTypeId(long planTypeId) {
        _plansFilterPosition.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the position ID of this plans filter position.
    *
    * @return the position ID of this plans filter position
    */
    public long getPositionId() {
        return _plansFilterPosition.getPositionId();
    }

    /**
    * Sets the position ID of this plans filter position.
    *
    * @param positionId the position ID of this plans filter position
    */
    public void setPositionId(long positionId) {
        _plansFilterPosition.setPositionId(positionId);
    }

    public boolean isNew() {
        return _plansFilterPosition.isNew();
    }

    public void setNew(boolean n) {
        _plansFilterPosition.setNew(n);
    }

    public boolean isCachedModel() {
        return _plansFilterPosition.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _plansFilterPosition.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _plansFilterPosition.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _plansFilterPosition.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _plansFilterPosition.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _plansFilterPosition.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _plansFilterPosition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlansFilterPositionWrapper((PlansFilterPosition) _plansFilterPosition.clone());
    }

    public int compareTo(PlansFilterPosition plansFilterPosition) {
        return _plansFilterPosition.compareTo(plansFilterPosition);
    }

    @Override
    public int hashCode() {
        return _plansFilterPosition.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlansFilterPosition> toCacheModel() {
        return _plansFilterPosition.toCacheModel();
    }

    public PlansFilterPosition toEscapedModel() {
        return new PlansFilterPositionWrapper(_plansFilterPosition.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _plansFilterPosition.toString();
    }

    public java.lang.String toXmlString() {
        return _plansFilterPosition.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansFilterPosition.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlansFilterPosition getWrappedPlansFilterPosition() {
        return _plansFilterPosition;
    }

    public PlansFilterPosition getWrappedModel() {
        return _plansFilterPosition;
    }

    public void resetOriginalValues() {
        _plansFilterPosition.resetOriginalValues();
    }
}

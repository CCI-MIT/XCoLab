package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPosition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPosition
 * @generated
 */
public class PlanPositionWrapper implements PlanPosition,
    ModelWrapper<PlanPosition> {
    private PlanPosition _planPosition;

    public PlanPositionWrapper(PlanPosition planPosition) {
        _planPosition = planPosition;
    }

    public Class<?> getModelClass() {
        return PlanPosition.class;
    }

    public String getModelClassName() {
        return PlanPosition.class.getName();
    }

    /**
    * Returns the primary key of this plan position.
    *
    * @return the primary key of this plan position
    */
    public com.ext.portlet.plans.service.persistence.PlanPositionPK getPrimaryKey() {
        return _planPosition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan position.
    *
    * @param primaryKey the primary key of this plan position
    */
    public void setPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlanPositionPK primaryKey) {
        _planPosition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan ID of this plan position.
    *
    * @return the plan ID of this plan position
    */
    public long getPlanId() {
        return _planPosition.getPlanId();
    }

    /**
    * Sets the plan ID of this plan position.
    *
    * @param planId the plan ID of this plan position
    */
    public void setPlanId(long planId) {
        _planPosition.setPlanId(planId);
    }

    /**
    * Returns the position ID of this plan position.
    *
    * @return the position ID of this plan position
    */
    public long getPositionId() {
        return _planPosition.getPositionId();
    }

    /**
    * Sets the position ID of this plan position.
    *
    * @param positionId the position ID of this plan position
    */
    public void setPositionId(long positionId) {
        _planPosition.setPositionId(positionId);
    }

    /**
    * Returns the user ID of this plan position.
    *
    * @return the user ID of this plan position
    */
    public long getUserId() {
        return _planPosition.getUserId();
    }

    /**
    * Sets the user ID of this plan position.
    *
    * @param userId the user ID of this plan position
    */
    public void setUserId(long userId) {
        _planPosition.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plan position.
    *
    * @return the user uuid of this plan position
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPosition.getUserUuid();
    }

    /**
    * Sets the user uuid of this plan position.
    *
    * @param userUuid the user uuid of this plan position
    */
    public void setUserUuid(java.lang.String userUuid) {
        _planPosition.setUserUuid(userUuid);
    }

    /**
    * Returns the user name of this plan position.
    *
    * @return the user name of this plan position
    */
    public java.lang.String getUserName() {
        return _planPosition.getUserName();
    }

    /**
    * Sets the user name of this plan position.
    *
    * @param userName the user name of this plan position
    */
    public void setUserName(java.lang.String userName) {
        _planPosition.setUserName(userName);
    }

    /**
    * Returns the create date of this plan position.
    *
    * @return the create date of this plan position
    */
    public java.util.Date getCreateDate() {
        return _planPosition.getCreateDate();
    }

    /**
    * Sets the create date of this plan position.
    *
    * @param createDate the create date of this plan position
    */
    public void setCreateDate(java.util.Date createDate) {
        _planPosition.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this plan position.
    *
    * @return the modified date of this plan position
    */
    public java.util.Date getModifiedDate() {
        return _planPosition.getModifiedDate();
    }

    /**
    * Sets the modified date of this plan position.
    *
    * @param modifiedDate the modified date of this plan position
    */
    public void setModifiedDate(java.util.Date modifiedDate) {
        _planPosition.setModifiedDate(modifiedDate);
    }

    public boolean isNew() {
        return _planPosition.isNew();
    }

    public void setNew(boolean n) {
        _planPosition.setNew(n);
    }

    public boolean isCachedModel() {
        return _planPosition.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planPosition.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planPosition.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planPosition.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planPosition.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planPosition.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planPosition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanPositionWrapper((PlanPosition) _planPosition.clone());
    }

    public int compareTo(PlanPosition planPosition) {
        return _planPosition.compareTo(planPosition);
    }

    @Override
    public int hashCode() {
        return _planPosition.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanPosition> toCacheModel() {
        return _planPosition.toCacheModel();
    }

    public PlanPosition toEscapedModel() {
        return new PlanPositionWrapper(_planPosition.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planPosition.toString();
    }

    public java.lang.String toXmlString() {
        return _planPosition.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPosition.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanPosition getWrappedPlanPosition() {
        return _planPosition;
    }

    public PlanPosition getWrappedModel() {
        return _planPosition;
    }

    public void resetOriginalValues() {
        _planPosition.resetOriginalValues();
    }
}

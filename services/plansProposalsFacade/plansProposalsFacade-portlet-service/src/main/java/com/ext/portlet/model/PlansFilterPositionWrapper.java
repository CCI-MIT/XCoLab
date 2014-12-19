package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlansFilterPosition}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPosition
 * @generated
 */
public class PlansFilterPositionWrapper implements PlansFilterPosition,
    ModelWrapper<PlansFilterPosition> {
    private PlansFilterPosition _plansFilterPosition;

    public PlansFilterPositionWrapper(PlansFilterPosition plansFilterPosition) {
        _plansFilterPosition = plansFilterPosition;
    }

    @Override
    public Class<?> getModelClass() {
        return PlansFilterPosition.class;
    }

    @Override
    public String getModelClassName() {
        return PlansFilterPosition.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("positionId", getPositionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        Long positionId = (Long) attributes.get("positionId");

        if (positionId != null) {
            setPositionId(positionId);
        }
    }

    /**
    * Returns the primary key of this plans filter position.
    *
    * @return the primary key of this plans filter position
    */
    @Override
    public com.ext.portlet.service.persistence.PlansFilterPositionPK getPrimaryKey() {
        return _plansFilterPosition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plans filter position.
    *
    * @param primaryKey the primary key of this plans filter position
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlansFilterPositionPK primaryKey) {
        _plansFilterPosition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this plans filter position.
    *
    * @return the user ID of this plans filter position
    */
    @Override
    public long getUserId() {
        return _plansFilterPosition.getUserId();
    }

    /**
    * Sets the user ID of this plans filter position.
    *
    * @param userId the user ID of this plans filter position
    */
    @Override
    public void setUserId(long userId) {
        _plansFilterPosition.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plans filter position.
    *
    * @return the user uuid of this plans filter position
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPosition.getUserUuid();
    }

    /**
    * Sets the user uuid of this plans filter position.
    *
    * @param userUuid the user uuid of this plans filter position
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _plansFilterPosition.setUserUuid(userUuid);
    }

    /**
    * Returns the plan type ID of this plans filter position.
    *
    * @return the plan type ID of this plans filter position
    */
    @Override
    public long getPlanTypeId() {
        return _plansFilterPosition.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plans filter position.
    *
    * @param planTypeId the plan type ID of this plans filter position
    */
    @Override
    public void setPlanTypeId(long planTypeId) {
        _plansFilterPosition.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the position ID of this plans filter position.
    *
    * @return the position ID of this plans filter position
    */
    @Override
    public long getPositionId() {
        return _plansFilterPosition.getPositionId();
    }

    /**
    * Sets the position ID of this plans filter position.
    *
    * @param positionId the position ID of this plans filter position
    */
    @Override
    public void setPositionId(long positionId) {
        _plansFilterPosition.setPositionId(positionId);
    }

    @Override
    public boolean isNew() {
        return _plansFilterPosition.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _plansFilterPosition.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _plansFilterPosition.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _plansFilterPosition.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _plansFilterPosition.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _plansFilterPosition.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _plansFilterPosition.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _plansFilterPosition.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _plansFilterPosition.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _plansFilterPosition.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _plansFilterPosition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlansFilterPositionWrapper((PlansFilterPosition) _plansFilterPosition.clone());
    }

    @Override
    public int compareTo(PlansFilterPosition plansFilterPosition) {
        return _plansFilterPosition.compareTo(plansFilterPosition);
    }

    @Override
    public int hashCode() {
        return _plansFilterPosition.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlansFilterPosition> toCacheModel() {
        return _plansFilterPosition.toCacheModel();
    }

    @Override
    public PlansFilterPosition toEscapedModel() {
        return new PlansFilterPositionWrapper(_plansFilterPosition.toEscapedModel());
    }

    @Override
    public PlansFilterPosition toUnescapedModel() {
        return new PlansFilterPositionWrapper(_plansFilterPosition.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _plansFilterPosition.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _plansFilterPosition.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansFilterPosition.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlansFilterPositionWrapper)) {
            return false;
        }

        PlansFilterPositionWrapper plansFilterPositionWrapper = (PlansFilterPositionWrapper) obj;

        if (Validator.equals(_plansFilterPosition,
                    plansFilterPositionWrapper._plansFilterPosition)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlansFilterPosition getWrappedPlansFilterPosition() {
        return _plansFilterPosition;
    }

    @Override
    public PlansFilterPosition getWrappedModel() {
        return _plansFilterPosition;
    }

    @Override
    public void resetOriginalValues() {
        _plansFilterPosition.resetOriginalValues();
    }
}

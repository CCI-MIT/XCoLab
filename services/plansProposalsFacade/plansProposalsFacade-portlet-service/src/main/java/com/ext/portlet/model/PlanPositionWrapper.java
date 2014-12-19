package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanPosition}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPosition
 * @generated
 */
public class PlanPositionWrapper implements PlanPosition,
    ModelWrapper<PlanPosition> {
    private PlanPosition _planPosition;

    public PlanPositionWrapper(PlanPosition planPosition) {
        _planPosition = planPosition;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanPosition.class;
    }

    @Override
    public String getModelClassName() {
        return PlanPosition.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planId", getPlanId());
        attributes.put("positionId", getPositionId());
        attributes.put("userId", getUserId());
        attributes.put("userName", getUserName());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long positionId = (Long) attributes.get("positionId");

        if (positionId != null) {
            setPositionId(positionId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String userName = (String) attributes.get("userName");

        if (userName != null) {
            setUserName(userName);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this plan position.
    *
    * @return the primary key of this plan position
    */
    @Override
    public com.ext.portlet.service.persistence.PlanPositionPK getPrimaryKey() {
        return _planPosition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan position.
    *
    * @param primaryKey the primary key of this plan position
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanPositionPK primaryKey) {
        _planPosition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan ID of this plan position.
    *
    * @return the plan ID of this plan position
    */
    @Override
    public long getPlanId() {
        return _planPosition.getPlanId();
    }

    /**
    * Sets the plan ID of this plan position.
    *
    * @param planId the plan ID of this plan position
    */
    @Override
    public void setPlanId(long planId) {
        _planPosition.setPlanId(planId);
    }

    /**
    * Returns the position ID of this plan position.
    *
    * @return the position ID of this plan position
    */
    @Override
    public long getPositionId() {
        return _planPosition.getPositionId();
    }

    /**
    * Sets the position ID of this plan position.
    *
    * @param positionId the position ID of this plan position
    */
    @Override
    public void setPositionId(long positionId) {
        _planPosition.setPositionId(positionId);
    }

    /**
    * Returns the user ID of this plan position.
    *
    * @return the user ID of this plan position
    */
    @Override
    public long getUserId() {
        return _planPosition.getUserId();
    }

    /**
    * Sets the user ID of this plan position.
    *
    * @param userId the user ID of this plan position
    */
    @Override
    public void setUserId(long userId) {
        _planPosition.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plan position.
    *
    * @return the user uuid of this plan position
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPosition.getUserUuid();
    }

    /**
    * Sets the user uuid of this plan position.
    *
    * @param userUuid the user uuid of this plan position
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _planPosition.setUserUuid(userUuid);
    }

    /**
    * Returns the user name of this plan position.
    *
    * @return the user name of this plan position
    */
    @Override
    public java.lang.String getUserName() {
        return _planPosition.getUserName();
    }

    /**
    * Sets the user name of this plan position.
    *
    * @param userName the user name of this plan position
    */
    @Override
    public void setUserName(java.lang.String userName) {
        _planPosition.setUserName(userName);
    }

    /**
    * Returns the create date of this plan position.
    *
    * @return the create date of this plan position
    */
    @Override
    public java.util.Date getCreateDate() {
        return _planPosition.getCreateDate();
    }

    /**
    * Sets the create date of this plan position.
    *
    * @param createDate the create date of this plan position
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _planPosition.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this plan position.
    *
    * @return the modified date of this plan position
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _planPosition.getModifiedDate();
    }

    /**
    * Sets the modified date of this plan position.
    *
    * @param modifiedDate the modified date of this plan position
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _planPosition.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _planPosition.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planPosition.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planPosition.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planPosition.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planPosition.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planPosition.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planPosition.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planPosition.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planPosition.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planPosition.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planPosition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanPositionWrapper((PlanPosition) _planPosition.clone());
    }

    @Override
    public int compareTo(PlanPosition planPosition) {
        return _planPosition.compareTo(planPosition);
    }

    @Override
    public int hashCode() {
        return _planPosition.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanPosition> toCacheModel() {
        return _planPosition.toCacheModel();
    }

    @Override
    public PlanPosition toEscapedModel() {
        return new PlanPositionWrapper(_planPosition.toEscapedModel());
    }

    @Override
    public PlanPosition toUnescapedModel() {
        return new PlanPositionWrapper(_planPosition.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planPosition.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planPosition.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPosition.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanPositionWrapper)) {
            return false;
        }

        PlanPositionWrapper planPositionWrapper = (PlanPositionWrapper) obj;

        if (Validator.equals(_planPosition, planPositionWrapper._planPosition)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanPosition getWrappedPlanPosition() {
        return _planPosition;
    }

    @Override
    public PlanPosition getWrappedModel() {
        return _planPosition;
    }

    @Override
    public void resetOriginalValues() {
        _planPosition.resetOriginalValues();
    }
}

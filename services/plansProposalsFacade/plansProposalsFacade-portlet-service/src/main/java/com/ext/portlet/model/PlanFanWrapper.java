package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanFan}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanFan
 * @generated
 */
public class PlanFanWrapper implements PlanFan, ModelWrapper<PlanFan> {
    private PlanFan _planFan;

    public PlanFanWrapper(PlanFan planFan) {
        _planFan = planFan;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanFan.class;
    }

    @Override
    public String getModelClassName() {
        return PlanFan.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("userId", getUserId());
        attributes.put("planId", getPlanId());
        attributes.put("created", getCreated());
        attributes.put("deleted", getDeleted());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Date deleted = (Date) attributes.get("deleted");

        if (deleted != null) {
            setDeleted(deleted);
        }
    }

    /**
    * Returns the primary key of this plan fan.
    *
    * @return the primary key of this plan fan
    */
    @Override
    public long getPrimaryKey() {
        return _planFan.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan fan.
    *
    * @param primaryKey the primary key of this plan fan
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planFan.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan fan.
    *
    * @return the ID of this plan fan
    */
    @Override
    public long getId() {
        return _planFan.getId();
    }

    /**
    * Sets the ID of this plan fan.
    *
    * @param id the ID of this plan fan
    */
    @Override
    public void setId(long id) {
        _planFan.setId(id);
    }

    /**
    * Returns the user ID of this plan fan.
    *
    * @return the user ID of this plan fan
    */
    @Override
    public long getUserId() {
        return _planFan.getUserId();
    }

    /**
    * Sets the user ID of this plan fan.
    *
    * @param userId the user ID of this plan fan
    */
    @Override
    public void setUserId(long userId) {
        _planFan.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plan fan.
    *
    * @return the user uuid of this plan fan
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFan.getUserUuid();
    }

    /**
    * Sets the user uuid of this plan fan.
    *
    * @param userUuid the user uuid of this plan fan
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _planFan.setUserUuid(userUuid);
    }

    /**
    * Returns the plan ID of this plan fan.
    *
    * @return the plan ID of this plan fan
    */
    @Override
    public long getPlanId() {
        return _planFan.getPlanId();
    }

    /**
    * Sets the plan ID of this plan fan.
    *
    * @param planId the plan ID of this plan fan
    */
    @Override
    public void setPlanId(long planId) {
        _planFan.setPlanId(planId);
    }

    /**
    * Returns the created of this plan fan.
    *
    * @return the created of this plan fan
    */
    @Override
    public java.util.Date getCreated() {
        return _planFan.getCreated();
    }

    /**
    * Sets the created of this plan fan.
    *
    * @param created the created of this plan fan
    */
    @Override
    public void setCreated(java.util.Date created) {
        _planFan.setCreated(created);
    }

    /**
    * Returns the deleted of this plan fan.
    *
    * @return the deleted of this plan fan
    */
    @Override
    public java.util.Date getDeleted() {
        return _planFan.getDeleted();
    }

    /**
    * Sets the deleted of this plan fan.
    *
    * @param deleted the deleted of this plan fan
    */
    @Override
    public void setDeleted(java.util.Date deleted) {
        _planFan.setDeleted(deleted);
    }

    @Override
    public boolean isNew() {
        return _planFan.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planFan.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planFan.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planFan.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planFan.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planFan.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planFan.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planFan.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planFan.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planFan.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planFan.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanFanWrapper((PlanFan) _planFan.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlanFan planFan) {
        return _planFan.compareTo(planFan);
    }

    @Override
    public int hashCode() {
        return _planFan.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanFan> toCacheModel() {
        return _planFan.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanFan toEscapedModel() {
        return new PlanFanWrapper(_planFan.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanFan toUnescapedModel() {
        return new PlanFanWrapper(_planFan.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planFan.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planFan.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planFan.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanFanWrapper)) {
            return false;
        }

        PlanFanWrapper planFanWrapper = (PlanFanWrapper) obj;

        if (Validator.equals(_planFan, planFanWrapper._planFan)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanFan getWrappedPlanFan() {
        return _planFan;
    }

    @Override
    public PlanFan getWrappedModel() {
        return _planFan;
    }

    @Override
    public void resetOriginalValues() {
        _planFan.resetOriginalValues();
    }
}

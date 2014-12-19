package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ActivitySubscription}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscription
 * @generated
 */
public class ActivitySubscriptionWrapper implements ActivitySubscription,
    ModelWrapper<ActivitySubscription> {
    private ActivitySubscription _activitySubscription;

    public ActivitySubscriptionWrapper(
        ActivitySubscription activitySubscription) {
        _activitySubscription = activitySubscription;
    }

    @Override
    public Class<?> getModelClass() {
        return ActivitySubscription.class;
    }

    @Override
    public String getModelClassName() {
        return ActivitySubscription.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("pk", getPk());
        attributes.put("classNameId", getClassNameId());
        attributes.put("classPK", getClassPK());
        attributes.put("type", getType());
        attributes.put("automaticSubscriptionCounter",
            getAutomaticSubscriptionCounter());
        attributes.put("extraData", getExtraData());
        attributes.put("receiverId", getReceiverId());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long classNameId = (Long) attributes.get("classNameId");

        if (classNameId != null) {
            setClassNameId(classNameId);
        }

        Long classPK = (Long) attributes.get("classPK");

        if (classPK != null) {
            setClassPK(classPK);
        }

        Integer type = (Integer) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        Integer automaticSubscriptionCounter = (Integer) attributes.get(
                "automaticSubscriptionCounter");

        if (automaticSubscriptionCounter != null) {
            setAutomaticSubscriptionCounter(automaticSubscriptionCounter);
        }

        String extraData = (String) attributes.get("extraData");

        if (extraData != null) {
            setExtraData(extraData);
        }

        Long receiverId = (Long) attributes.get("receiverId");

        if (receiverId != null) {
            setReceiverId(receiverId);
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
    * Returns the primary key of this activity subscription.
    *
    * @return the primary key of this activity subscription
    */
    @Override
    public long getPrimaryKey() {
        return _activitySubscription.getPrimaryKey();
    }

    /**
    * Sets the primary key of this activity subscription.
    *
    * @param primaryKey the primary key of this activity subscription
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _activitySubscription.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this activity subscription.
    *
    * @return the pk of this activity subscription
    */
    @Override
    public long getPk() {
        return _activitySubscription.getPk();
    }

    /**
    * Sets the pk of this activity subscription.
    *
    * @param pk the pk of this activity subscription
    */
    @Override
    public void setPk(long pk) {
        _activitySubscription.setPk(pk);
    }

    /**
    * Returns the fully qualified class name of this activity subscription.
    *
    * @return the fully qualified class name of this activity subscription
    */
    @Override
    public java.lang.String getClassName() {
        return _activitySubscription.getClassName();
    }

    @Override
    public void setClassName(java.lang.String className) {
        _activitySubscription.setClassName(className);
    }

    /**
    * Returns the class name ID of this activity subscription.
    *
    * @return the class name ID of this activity subscription
    */
    @Override
    public long getClassNameId() {
        return _activitySubscription.getClassNameId();
    }

    /**
    * Sets the class name ID of this activity subscription.
    *
    * @param classNameId the class name ID of this activity subscription
    */
    @Override
    public void setClassNameId(long classNameId) {
        _activitySubscription.setClassNameId(classNameId);
    }

    /**
    * Returns the class p k of this activity subscription.
    *
    * @return the class p k of this activity subscription
    */
    @Override
    public long getClassPK() {
        return _activitySubscription.getClassPK();
    }

    /**
    * Sets the class p k of this activity subscription.
    *
    * @param classPK the class p k of this activity subscription
    */
    @Override
    public void setClassPK(long classPK) {
        _activitySubscription.setClassPK(classPK);
    }

    /**
    * Returns the type of this activity subscription.
    *
    * @return the type of this activity subscription
    */
    @Override
    public int getType() {
        return _activitySubscription.getType();
    }

    /**
    * Sets the type of this activity subscription.
    *
    * @param type the type of this activity subscription
    */
    @Override
    public void setType(int type) {
        _activitySubscription.setType(type);
    }

    /**
    * Returns the automatic subscription counter of this activity subscription.
    *
    * @return the automatic subscription counter of this activity subscription
    */
    @Override
    public int getAutomaticSubscriptionCounter() {
        return _activitySubscription.getAutomaticSubscriptionCounter();
    }

    /**
    * Sets the automatic subscription counter of this activity subscription.
    *
    * @param automaticSubscriptionCounter the automatic subscription counter of this activity subscription
    */
    @Override
    public void setAutomaticSubscriptionCounter(
        int automaticSubscriptionCounter) {
        _activitySubscription.setAutomaticSubscriptionCounter(automaticSubscriptionCounter);
    }

    /**
    * Returns the extra data of this activity subscription.
    *
    * @return the extra data of this activity subscription
    */
    @Override
    public java.lang.String getExtraData() {
        return _activitySubscription.getExtraData();
    }

    /**
    * Sets the extra data of this activity subscription.
    *
    * @param extraData the extra data of this activity subscription
    */
    @Override
    public void setExtraData(java.lang.String extraData) {
        _activitySubscription.setExtraData(extraData);
    }

    /**
    * Returns the receiver ID of this activity subscription.
    *
    * @return the receiver ID of this activity subscription
    */
    @Override
    public long getReceiverId() {
        return _activitySubscription.getReceiverId();
    }

    /**
    * Sets the receiver ID of this activity subscription.
    *
    * @param receiverId the receiver ID of this activity subscription
    */
    @Override
    public void setReceiverId(long receiverId) {
        _activitySubscription.setReceiverId(receiverId);
    }

    /**
    * Returns the create date of this activity subscription.
    *
    * @return the create date of this activity subscription
    */
    @Override
    public java.util.Date getCreateDate() {
        return _activitySubscription.getCreateDate();
    }

    /**
    * Sets the create date of this activity subscription.
    *
    * @param createDate the create date of this activity subscription
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _activitySubscription.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this activity subscription.
    *
    * @return the modified date of this activity subscription
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _activitySubscription.getModifiedDate();
    }

    /**
    * Sets the modified date of this activity subscription.
    *
    * @param modifiedDate the modified date of this activity subscription
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _activitySubscription.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _activitySubscription.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _activitySubscription.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _activitySubscription.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _activitySubscription.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _activitySubscription.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _activitySubscription.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _activitySubscription.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _activitySubscription.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _activitySubscription.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _activitySubscription.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _activitySubscription.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ActivitySubscriptionWrapper((ActivitySubscription) _activitySubscription.clone());
    }

    @Override
    public int compareTo(ActivitySubscription activitySubscription) {
        return _activitySubscription.compareTo(activitySubscription);
    }

    @Override
    public int hashCode() {
        return _activitySubscription.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ActivitySubscription> toCacheModel() {
        return _activitySubscription.toCacheModel();
    }

    @Override
    public ActivitySubscription toEscapedModel() {
        return new ActivitySubscriptionWrapper(_activitySubscription.toEscapedModel());
    }

    @Override
    public ActivitySubscription toUnescapedModel() {
        return new ActivitySubscriptionWrapper(_activitySubscription.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _activitySubscription.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _activitySubscription.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscription.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ActivitySubscriptionWrapper)) {
            return false;
        }

        ActivitySubscriptionWrapper activitySubscriptionWrapper = (ActivitySubscriptionWrapper) obj;

        if (Validator.equals(_activitySubscription,
                    activitySubscriptionWrapper._activitySubscription)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ActivitySubscription getWrappedActivitySubscription() {
        return _activitySubscription;
    }

    @Override
    public ActivitySubscription getWrappedModel() {
        return _activitySubscription;
    }

    @Override
    public void resetOriginalValues() {
        _activitySubscription.resetOriginalValues();
    }
}

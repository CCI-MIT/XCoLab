package com.ext.portlet.Activity.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ActivitySubscription}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ActivitySubscription
 * @generated
 */
public class ActivitySubscriptionWrapper implements ActivitySubscription,
    ModelWrapper<ActivitySubscription> {
    private ActivitySubscription _activitySubscription;

    public ActivitySubscriptionWrapper(
        ActivitySubscription activitySubscription) {
        _activitySubscription = activitySubscription;
    }

    public Class<?> getModelClass() {
        return ActivitySubscription.class;
    }

    public String getModelClassName() {
        return ActivitySubscription.class.getName();
    }

    /**
    * Returns the primary key of this activity subscription.
    *
    * @return the primary key of this activity subscription
    */
    public java.lang.Long getPrimaryKey() {
        return _activitySubscription.getPrimaryKey();
    }

    /**
    * Sets the primary key of this activity subscription.
    *
    * @param primaryKey the primary key of this activity subscription
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _activitySubscription.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this activity subscription.
    *
    * @return the pk of this activity subscription
    */
    public java.lang.Long getPk() {
        return _activitySubscription.getPk();
    }

    /**
    * Sets the pk of this activity subscription.
    *
    * @param pk the pk of this activity subscription
    */
    public void setPk(java.lang.Long pk) {
        _activitySubscription.setPk(pk);
    }

    /**
    * Returns the fully qualified class name of this activity subscription.
    *
    * @return the fully qualified class name of this activity subscription
    */
    public java.lang.String getClassName() {
        return _activitySubscription.getClassName();
    }

    /**
    * Returns the class name ID of this activity subscription.
    *
    * @return the class name ID of this activity subscription
    */
    public java.lang.Long getClassNameId() {
        return _activitySubscription.getClassNameId();
    }

    /**
    * Sets the class name ID of this activity subscription.
    *
    * @param classNameId the class name ID of this activity subscription
    */
    public void setClassNameId(java.lang.Long classNameId) {
        _activitySubscription.setClassNameId(classNameId);
    }

    /**
    * Returns the class p k of this activity subscription.
    *
    * @return the class p k of this activity subscription
    */
    public java.lang.Long getClassPK() {
        return _activitySubscription.getClassPK();
    }

    /**
    * Sets the class p k of this activity subscription.
    *
    * @param classPK the class p k of this activity subscription
    */
    public void setClassPK(java.lang.Long classPK) {
        _activitySubscription.setClassPK(classPK);
    }

    /**
    * Returns the type of this activity subscription.
    *
    * @return the type of this activity subscription
    */
    public java.lang.Integer getType() {
        return _activitySubscription.getType();
    }

    /**
    * Sets the type of this activity subscription.
    *
    * @param type the type of this activity subscription
    */
    public void setType(java.lang.Integer type) {
        _activitySubscription.setType(type);
    }

    /**
    * Returns the extra data of this activity subscription.
    *
    * @return the extra data of this activity subscription
    */
    public java.lang.String getExtraData() {
        return _activitySubscription.getExtraData();
    }

    /**
    * Sets the extra data of this activity subscription.
    *
    * @param extraData the extra data of this activity subscription
    */
    public void setExtraData(java.lang.String extraData) {
        _activitySubscription.setExtraData(extraData);
    }

    /**
    * Returns the receiver ID of this activity subscription.
    *
    * @return the receiver ID of this activity subscription
    */
    public java.lang.Long getReceiverId() {
        return _activitySubscription.getReceiverId();
    }

    /**
    * Sets the receiver ID of this activity subscription.
    *
    * @param receiverId the receiver ID of this activity subscription
    */
    public void setReceiverId(java.lang.Long receiverId) {
        _activitySubscription.setReceiverId(receiverId);
    }

    /**
    * Returns the create date of this activity subscription.
    *
    * @return the create date of this activity subscription
    */
    public java.util.Date getCreateDate() {
        return _activitySubscription.getCreateDate();
    }

    /**
    * Sets the create date of this activity subscription.
    *
    * @param createDate the create date of this activity subscription
    */
    public void setCreateDate(java.util.Date createDate) {
        _activitySubscription.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this activity subscription.
    *
    * @return the modified date of this activity subscription
    */
    public java.util.Date getModifiedDate() {
        return _activitySubscription.getModifiedDate();
    }

    /**
    * Sets the modified date of this activity subscription.
    *
    * @param modifiedDate the modified date of this activity subscription
    */
    public void setModifiedDate(java.util.Date modifiedDate) {
        _activitySubscription.setModifiedDate(modifiedDate);
    }

    public boolean isNew() {
        return _activitySubscription.isNew();
    }

    public void setNew(boolean n) {
        _activitySubscription.setNew(n);
    }

    public boolean isCachedModel() {
        return _activitySubscription.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _activitySubscription.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _activitySubscription.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _activitySubscription.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _activitySubscription.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _activitySubscription.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _activitySubscription.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ActivitySubscriptionWrapper((ActivitySubscription) _activitySubscription.clone());
    }

    public int compareTo(ActivitySubscription activitySubscription) {
        return _activitySubscription.compareTo(activitySubscription);
    }

    @Override
    public int hashCode() {
        return _activitySubscription.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ActivitySubscription> toCacheModel() {
        return _activitySubscription.toCacheModel();
    }

    public ActivitySubscription toEscapedModel() {
        return new ActivitySubscriptionWrapper(_activitySubscription.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _activitySubscription.toString();
    }

    public java.lang.String toXmlString() {
        return _activitySubscription.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscription.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscription.store();
    }

    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreter() {
        return _activitySubscription.getInterpreter();
    }

    public java.lang.String getName() {
        return _activitySubscription.getName();
    }

    public com.ext.portlet.Activity.SubscriptionType getSubscriptionType() {
        return _activitySubscription.getSubscriptionType();
    }

    public void delete()
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscription.delete();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ActivitySubscription getWrappedActivitySubscription() {
        return _activitySubscription;
    }

    public ActivitySubscription getWrappedModel() {
        return _activitySubscription;
    }

    public void resetOriginalValues() {
        _activitySubscription.resetOriginalValues();
    }
}

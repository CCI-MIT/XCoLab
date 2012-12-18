package com.ext.portlet.discussions.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionMessageFlag}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionMessageFlag
 * @generated
 */
public class DiscussionMessageFlagWrapper implements DiscussionMessageFlag,
    ModelWrapper<DiscussionMessageFlag> {
    private DiscussionMessageFlag _discussionMessageFlag;

    public DiscussionMessageFlagWrapper(
        DiscussionMessageFlag discussionMessageFlag) {
        _discussionMessageFlag = discussionMessageFlag;
    }

    public Class<?> getModelClass() {
        return DiscussionMessageFlag.class;
    }

    public String getModelClassName() {
        return DiscussionMessageFlag.class.getName();
    }

    /**
    * Returns the primary key of this discussion message flag.
    *
    * @return the primary key of this discussion message flag
    */
    public long getPrimaryKey() {
        return _discussionMessageFlag.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion message flag.
    *
    * @param primaryKey the primary key of this discussion message flag
    */
    public void setPrimaryKey(long primaryKey) {
        _discussionMessageFlag.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this discussion message flag.
    *
    * @return the pk of this discussion message flag
    */
    public long getPk() {
        return _discussionMessageFlag.getPk();
    }

    /**
    * Sets the pk of this discussion message flag.
    *
    * @param pk the pk of this discussion message flag
    */
    public void setPk(long pk) {
        _discussionMessageFlag.setPk(pk);
    }

    /**
    * Returns the message ID of this discussion message flag.
    *
    * @return the message ID of this discussion message flag
    */
    public long getMessageId() {
        return _discussionMessageFlag.getMessageId();
    }

    /**
    * Sets the message ID of this discussion message flag.
    *
    * @param messageId the message ID of this discussion message flag
    */
    public void setMessageId(long messageId) {
        _discussionMessageFlag.setMessageId(messageId);
    }

    /**
    * Returns the flag type of this discussion message flag.
    *
    * @return the flag type of this discussion message flag
    */
    public java.lang.String getFlagType() {
        return _discussionMessageFlag.getFlagType();
    }

    /**
    * Sets the flag type of this discussion message flag.
    *
    * @param flagType the flag type of this discussion message flag
    */
    public void setFlagType(java.lang.String flagType) {
        _discussionMessageFlag.setFlagType(flagType);
    }

    /**
    * Returns the data of this discussion message flag.
    *
    * @return the data of this discussion message flag
    */
    public java.lang.String getData() {
        return _discussionMessageFlag.getData();
    }

    /**
    * Sets the data of this discussion message flag.
    *
    * @param data the data of this discussion message flag
    */
    public void setData(java.lang.String data) {
        _discussionMessageFlag.setData(data);
    }

    /**
    * Returns the created of this discussion message flag.
    *
    * @return the created of this discussion message flag
    */
    public java.util.Date getCreated() {
        return _discussionMessageFlag.getCreated();
    }

    /**
    * Sets the created of this discussion message flag.
    *
    * @param created the created of this discussion message flag
    */
    public void setCreated(java.util.Date created) {
        _discussionMessageFlag.setCreated(created);
    }

    /**
    * Returns the user ID of this discussion message flag.
    *
    * @return the user ID of this discussion message flag
    */
    public long getUserId() {
        return _discussionMessageFlag.getUserId();
    }

    /**
    * Sets the user ID of this discussion message flag.
    *
    * @param userId the user ID of this discussion message flag
    */
    public void setUserId(long userId) {
        _discussionMessageFlag.setUserId(userId);
    }

    /**
    * Returns the user uuid of this discussion message flag.
    *
    * @return the user uuid of this discussion message flag
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlag.getUserUuid();
    }

    /**
    * Sets the user uuid of this discussion message flag.
    *
    * @param userUuid the user uuid of this discussion message flag
    */
    public void setUserUuid(java.lang.String userUuid) {
        _discussionMessageFlag.setUserUuid(userUuid);
    }

    public boolean isNew() {
        return _discussionMessageFlag.isNew();
    }

    public void setNew(boolean n) {
        _discussionMessageFlag.setNew(n);
    }

    public boolean isCachedModel() {
        return _discussionMessageFlag.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _discussionMessageFlag.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _discussionMessageFlag.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _discussionMessageFlag.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _discussionMessageFlag.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _discussionMessageFlag.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _discussionMessageFlag.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DiscussionMessageFlagWrapper((DiscussionMessageFlag) _discussionMessageFlag.clone());
    }

    public int compareTo(DiscussionMessageFlag discussionMessageFlag) {
        return _discussionMessageFlag.compareTo(discussionMessageFlag);
    }

    @Override
    public int hashCode() {
        return _discussionMessageFlag.hashCode();
    }

    public com.liferay.portal.model.CacheModel<DiscussionMessageFlag> toCacheModel() {
        return _discussionMessageFlag.toCacheModel();
    }

    public DiscussionMessageFlag toEscapedModel() {
        return new DiscussionMessageFlagWrapper(_discussionMessageFlag.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _discussionMessageFlag.toString();
    }

    public java.lang.String toXmlString() {
        return _discussionMessageFlag.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageFlag.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public DiscussionMessageFlag getWrappedDiscussionMessageFlag() {
        return _discussionMessageFlag;
    }

    public DiscussionMessageFlag getWrappedModel() {
        return _discussionMessageFlag;
    }

    public void resetOriginalValues() {
        _discussionMessageFlag.resetOriginalValues();
    }
}

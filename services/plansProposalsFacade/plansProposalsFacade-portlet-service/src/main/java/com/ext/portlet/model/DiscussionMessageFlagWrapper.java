package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionMessageFlag}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlag
 * @generated
 */
public class DiscussionMessageFlagWrapper implements DiscussionMessageFlag,
    ModelWrapper<DiscussionMessageFlag> {
    private DiscussionMessageFlag _discussionMessageFlag;

    public DiscussionMessageFlagWrapper(
        DiscussionMessageFlag discussionMessageFlag) {
        _discussionMessageFlag = discussionMessageFlag;
    }

    @Override
    public Class<?> getModelClass() {
        return DiscussionMessageFlag.class;
    }

    @Override
    public String getModelClassName() {
        return DiscussionMessageFlag.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("pk", getPk());
        attributes.put("messageId", getMessageId());
        attributes.put("flagType", getFlagType());
        attributes.put("data", getData());
        attributes.put("created", getCreated());
        attributes.put("userId", getUserId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        String flagType = (String) attributes.get("flagType");

        if (flagType != null) {
            setFlagType(flagType);
        }

        String data = (String) attributes.get("data");

        if (data != null) {
            setData(data);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }
    }

    /**
    * Returns the primary key of this discussion message flag.
    *
    * @return the primary key of this discussion message flag
    */
    @Override
    public long getPrimaryKey() {
        return _discussionMessageFlag.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion message flag.
    *
    * @param primaryKey the primary key of this discussion message flag
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _discussionMessageFlag.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this discussion message flag.
    *
    * @return the pk of this discussion message flag
    */
    @Override
    public long getPk() {
        return _discussionMessageFlag.getPk();
    }

    /**
    * Sets the pk of this discussion message flag.
    *
    * @param pk the pk of this discussion message flag
    */
    @Override
    public void setPk(long pk) {
        _discussionMessageFlag.setPk(pk);
    }

    /**
    * Returns the message ID of this discussion message flag.
    *
    * @return the message ID of this discussion message flag
    */
    @Override
    public long getMessageId() {
        return _discussionMessageFlag.getMessageId();
    }

    /**
    * Sets the message ID of this discussion message flag.
    *
    * @param messageId the message ID of this discussion message flag
    */
    @Override
    public void setMessageId(long messageId) {
        _discussionMessageFlag.setMessageId(messageId);
    }

    /**
    * Returns the flag type of this discussion message flag.
    *
    * @return the flag type of this discussion message flag
    */
    @Override
    public java.lang.String getFlagType() {
        return _discussionMessageFlag.getFlagType();
    }

    /**
    * Sets the flag type of this discussion message flag.
    *
    * @param flagType the flag type of this discussion message flag
    */
    @Override
    public void setFlagType(java.lang.String flagType) {
        _discussionMessageFlag.setFlagType(flagType);
    }

    /**
    * Returns the data of this discussion message flag.
    *
    * @return the data of this discussion message flag
    */
    @Override
    public java.lang.String getData() {
        return _discussionMessageFlag.getData();
    }

    /**
    * Sets the data of this discussion message flag.
    *
    * @param data the data of this discussion message flag
    */
    @Override
    public void setData(java.lang.String data) {
        _discussionMessageFlag.setData(data);
    }

    /**
    * Returns the created of this discussion message flag.
    *
    * @return the created of this discussion message flag
    */
    @Override
    public java.util.Date getCreated() {
        return _discussionMessageFlag.getCreated();
    }

    /**
    * Sets the created of this discussion message flag.
    *
    * @param created the created of this discussion message flag
    */
    @Override
    public void setCreated(java.util.Date created) {
        _discussionMessageFlag.setCreated(created);
    }

    /**
    * Returns the user ID of this discussion message flag.
    *
    * @return the user ID of this discussion message flag
    */
    @Override
    public long getUserId() {
        return _discussionMessageFlag.getUserId();
    }

    /**
    * Sets the user ID of this discussion message flag.
    *
    * @param userId the user ID of this discussion message flag
    */
    @Override
    public void setUserId(long userId) {
        _discussionMessageFlag.setUserId(userId);
    }

    /**
    * Returns the user uuid of this discussion message flag.
    *
    * @return the user uuid of this discussion message flag
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageFlag.getUserUuid();
    }

    /**
    * Sets the user uuid of this discussion message flag.
    *
    * @param userUuid the user uuid of this discussion message flag
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _discussionMessageFlag.setUserUuid(userUuid);
    }

    @Override
    public boolean isNew() {
        return _discussionMessageFlag.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _discussionMessageFlag.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _discussionMessageFlag.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _discussionMessageFlag.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _discussionMessageFlag.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _discussionMessageFlag.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _discussionMessageFlag.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _discussionMessageFlag.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _discussionMessageFlag.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _discussionMessageFlag.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _discussionMessageFlag.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DiscussionMessageFlagWrapper((DiscussionMessageFlag) _discussionMessageFlag.clone());
    }

    @Override
    public int compareTo(DiscussionMessageFlag discussionMessageFlag) {
        return _discussionMessageFlag.compareTo(discussionMessageFlag);
    }

    @Override
    public int hashCode() {
        return _discussionMessageFlag.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<DiscussionMessageFlag> toCacheModel() {
        return _discussionMessageFlag.toCacheModel();
    }

    @Override
    public DiscussionMessageFlag toEscapedModel() {
        return new DiscussionMessageFlagWrapper(_discussionMessageFlag.toEscapedModel());
    }

    @Override
    public DiscussionMessageFlag toUnescapedModel() {
        return new DiscussionMessageFlagWrapper(_discussionMessageFlag.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _discussionMessageFlag.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _discussionMessageFlag.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageFlag.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DiscussionMessageFlagWrapper)) {
            return false;
        }

        DiscussionMessageFlagWrapper discussionMessageFlagWrapper = (DiscussionMessageFlagWrapper) obj;

        if (Validator.equals(_discussionMessageFlag,
                    discussionMessageFlagWrapper._discussionMessageFlag)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DiscussionMessageFlag getWrappedDiscussionMessageFlag() {
        return _discussionMessageFlag;
    }

    @Override
    public DiscussionMessageFlag getWrappedModel() {
        return _discussionMessageFlag;
    }

    @Override
    public void resetOriginalValues() {
        _discussionMessageFlag.resetOriginalValues();
    }
}

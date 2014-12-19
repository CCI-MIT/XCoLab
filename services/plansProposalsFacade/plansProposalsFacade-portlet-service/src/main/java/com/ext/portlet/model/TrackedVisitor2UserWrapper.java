package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TrackedVisitor2User}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitor2User
 * @generated
 */
public class TrackedVisitor2UserWrapper implements TrackedVisitor2User,
    ModelWrapper<TrackedVisitor2User> {
    private TrackedVisitor2User _trackedVisitor2User;

    public TrackedVisitor2UserWrapper(TrackedVisitor2User trackedVisitor2User) {
        _trackedVisitor2User = trackedVisitor2User;
    }

    @Override
    public Class<?> getModelClass() {
        return TrackedVisitor2User.class;
    }

    @Override
    public String getModelClassName() {
        return TrackedVisitor2User.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("uuid", getUuid());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this tracked visitor2 user.
    *
    * @return the primary key of this tracked visitor2 user
    */
    @Override
    public long getPrimaryKey() {
        return _trackedVisitor2User.getPrimaryKey();
    }

    /**
    * Sets the primary key of this tracked visitor2 user.
    *
    * @param primaryKey the primary key of this tracked visitor2 user
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _trackedVisitor2User.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this tracked visitor2 user.
    *
    * @return the ID of this tracked visitor2 user
    */
    @Override
    public long getId() {
        return _trackedVisitor2User.getId();
    }

    /**
    * Sets the ID of this tracked visitor2 user.
    *
    * @param id the ID of this tracked visitor2 user
    */
    @Override
    public void setId(long id) {
        _trackedVisitor2User.setId(id);
    }

    /**
    * Returns the uuid of this tracked visitor2 user.
    *
    * @return the uuid of this tracked visitor2 user
    */
    @Override
    public java.lang.String getUuid() {
        return _trackedVisitor2User.getUuid();
    }

    /**
    * Sets the uuid of this tracked visitor2 user.
    *
    * @param uuid the uuid of this tracked visitor2 user
    */
    @Override
    public void setUuid(java.lang.String uuid) {
        _trackedVisitor2User.setUuid(uuid);
    }

    /**
    * Returns the user ID of this tracked visitor2 user.
    *
    * @return the user ID of this tracked visitor2 user
    */
    @Override
    public long getUserId() {
        return _trackedVisitor2User.getUserId();
    }

    /**
    * Sets the user ID of this tracked visitor2 user.
    *
    * @param userId the user ID of this tracked visitor2 user
    */
    @Override
    public void setUserId(long userId) {
        _trackedVisitor2User.setUserId(userId);
    }

    /**
    * Returns the user uuid of this tracked visitor2 user.
    *
    * @return the user uuid of this tracked visitor2 user
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitor2User.getUserUuid();
    }

    /**
    * Sets the user uuid of this tracked visitor2 user.
    *
    * @param userUuid the user uuid of this tracked visitor2 user
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _trackedVisitor2User.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this tracked visitor2 user.
    *
    * @return the create date of this tracked visitor2 user
    */
    @Override
    public java.util.Date getCreateDate() {
        return _trackedVisitor2User.getCreateDate();
    }

    /**
    * Sets the create date of this tracked visitor2 user.
    *
    * @param createDate the create date of this tracked visitor2 user
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _trackedVisitor2User.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _trackedVisitor2User.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _trackedVisitor2User.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _trackedVisitor2User.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _trackedVisitor2User.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _trackedVisitor2User.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _trackedVisitor2User.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _trackedVisitor2User.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _trackedVisitor2User.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _trackedVisitor2User.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _trackedVisitor2User.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _trackedVisitor2User.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new TrackedVisitor2UserWrapper((TrackedVisitor2User) _trackedVisitor2User.clone());
    }

    @Override
    public int compareTo(TrackedVisitor2User trackedVisitor2User) {
        return _trackedVisitor2User.compareTo(trackedVisitor2User);
    }

    @Override
    public int hashCode() {
        return _trackedVisitor2User.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<TrackedVisitor2User> toCacheModel() {
        return _trackedVisitor2User.toCacheModel();
    }

    @Override
    public TrackedVisitor2User toEscapedModel() {
        return new TrackedVisitor2UserWrapper(_trackedVisitor2User.toEscapedModel());
    }

    @Override
    public TrackedVisitor2User toUnescapedModel() {
        return new TrackedVisitor2UserWrapper(_trackedVisitor2User.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _trackedVisitor2User.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _trackedVisitor2User.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _trackedVisitor2User.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TrackedVisitor2UserWrapper)) {
            return false;
        }

        TrackedVisitor2UserWrapper trackedVisitor2UserWrapper = (TrackedVisitor2UserWrapper) obj;

        if (Validator.equals(_trackedVisitor2User,
                    trackedVisitor2UserWrapper._trackedVisitor2User)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public TrackedVisitor2User getWrappedTrackedVisitor2User() {
        return _trackedVisitor2User;
    }

    @Override
    public TrackedVisitor2User getWrappedModel() {
        return _trackedVisitor2User;
    }

    @Override
    public void resetOriginalValues() {
        _trackedVisitor2User.resetOriginalValues();
    }
}

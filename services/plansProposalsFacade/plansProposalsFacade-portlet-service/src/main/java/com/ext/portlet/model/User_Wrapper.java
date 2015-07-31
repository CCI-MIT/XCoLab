package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link User_}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see User_
 * @generated
 */
public class User_Wrapper implements User_, ModelWrapper<User_> {
    private User_ _user_;

    public User_Wrapper(User_ user_) {
        _user_ = user_;
    }

    @Override
    public Class<?> getModelClass() {
        return User_.class;
    }

    @Override
    public String getModelClassName() {
        return User_.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());
        attributes.put("screenName", getScreenName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }
    }

    /**
    * Returns the primary key of this user_.
    *
    * @return the primary key of this user_
    */
    @Override
    public long getPrimaryKey() {
        return _user_.getPrimaryKey();
    }

    /**
    * Sets the primary key of this user_.
    *
    * @param primaryKey the primary key of this user_
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _user_.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this user_.
    *
    * @return the user ID of this user_
    */
    @Override
    public long getUserId() {
        return _user_.getUserId();
    }

    /**
    * Sets the user ID of this user_.
    *
    * @param userId the user ID of this user_
    */
    @Override
    public void setUserId(long userId) {
        _user_.setUserId(userId);
    }

    /**
    * Returns the user uuid of this user_.
    *
    * @return the user uuid of this user_
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_.getUserUuid();
    }

    /**
    * Sets the user uuid of this user_.
    *
    * @param userUuid the user uuid of this user_
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _user_.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this user_.
    *
    * @return the create date of this user_
    */
    @Override
    public java.util.Date getCreateDate() {
        return _user_.getCreateDate();
    }

    /**
    * Sets the create date of this user_.
    *
    * @param createDate the create date of this user_
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _user_.setCreateDate(createDate);
    }

    /**
    * Returns the screen name of this user_.
    *
    * @return the screen name of this user_
    */
    @Override
    public java.lang.String getScreenName() {
        return _user_.getScreenName();
    }

    /**
    * Sets the screen name of this user_.
    *
    * @param screenName the screen name of this user_
    */
    @Override
    public void setScreenName(java.lang.String screenName) {
        _user_.setScreenName(screenName);
    }

    @Override
    public boolean isNew() {
        return _user_.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _user_.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _user_.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _user_.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _user_.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _user_.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _user_.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _user_.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _user_.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _user_.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _user_.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new User_Wrapper((User_) _user_.clone());
    }

    @Override
    public int compareTo(User_ user_) {
        return _user_.compareTo(user_);
    }

    @Override
    public int hashCode() {
        return _user_.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<User_> toCacheModel() {
        return _user_.toCacheModel();
    }

    @Override
    public User_ toEscapedModel() {
        return new User_Wrapper(_user_.toEscapedModel());
    }

    @Override
    public User_ toUnescapedModel() {
        return new User_Wrapper(_user_.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _user_.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _user_.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof User_Wrapper)) {
            return false;
        }

        User_Wrapper user_Wrapper = (User_Wrapper) obj;

        if (Validator.equals(_user_, user_Wrapper._user_)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public User_ getWrappedUser_() {
        return _user_;
    }

    @Override
    public User_ getWrappedModel() {
        return _user_;
    }

    @Override
    public void resetOriginalValues() {
        _user_.resetOriginalValues();
    }
}

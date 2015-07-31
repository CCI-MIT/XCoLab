package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Users_Roles}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Users_Roles
 * @generated
 */
public class Users_RolesWrapper implements Users_Roles,
    ModelWrapper<Users_Roles> {
    private Users_Roles _users_Roles;

    public Users_RolesWrapper(Users_Roles users_Roles) {
        _users_Roles = users_Roles;
    }

    @Override
    public Class<?> getModelClass() {
        return Users_Roles.class;
    }

    @Override
    public String getModelClassName() {
        return Users_Roles.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("roleId", getRoleId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long roleId = (Long) attributes.get("roleId");

        if (roleId != null) {
            setRoleId(roleId);
        }
    }

    /**
    * Returns the primary key of this users_ roles.
    *
    * @return the primary key of this users_ roles
    */
    @Override
    public com.ext.portlet.service.persistence.Users_RolesPK getPrimaryKey() {
        return _users_Roles.getPrimaryKey();
    }

    /**
    * Sets the primary key of this users_ roles.
    *
    * @param primaryKey the primary key of this users_ roles
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.Users_RolesPK primaryKey) {
        _users_Roles.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this users_ roles.
    *
    * @return the user ID of this users_ roles
    */
    @Override
    public long getUserId() {
        return _users_Roles.getUserId();
    }

    /**
    * Sets the user ID of this users_ roles.
    *
    * @param userId the user ID of this users_ roles
    */
    @Override
    public void setUserId(long userId) {
        _users_Roles.setUserId(userId);
    }

    /**
    * Returns the user uuid of this users_ roles.
    *
    * @return the user uuid of this users_ roles
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _users_Roles.getUserUuid();
    }

    /**
    * Sets the user uuid of this users_ roles.
    *
    * @param userUuid the user uuid of this users_ roles
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _users_Roles.setUserUuid(userUuid);
    }

    /**
    * Returns the role ID of this users_ roles.
    *
    * @return the role ID of this users_ roles
    */
    @Override
    public long getRoleId() {
        return _users_Roles.getRoleId();
    }

    /**
    * Sets the role ID of this users_ roles.
    *
    * @param roleId the role ID of this users_ roles
    */
    @Override
    public void setRoleId(long roleId) {
        _users_Roles.setRoleId(roleId);
    }

    @Override
    public boolean isNew() {
        return _users_Roles.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _users_Roles.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _users_Roles.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _users_Roles.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _users_Roles.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _users_Roles.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _users_Roles.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _users_Roles.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _users_Roles.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _users_Roles.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _users_Roles.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new Users_RolesWrapper((Users_Roles) _users_Roles.clone());
    }

    @Override
    public int compareTo(Users_Roles users_Roles) {
        return _users_Roles.compareTo(users_Roles);
    }

    @Override
    public int hashCode() {
        return _users_Roles.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<Users_Roles> toCacheModel() {
        return _users_Roles.toCacheModel();
    }

    @Override
    public Users_Roles toEscapedModel() {
        return new Users_RolesWrapper(_users_Roles.toEscapedModel());
    }

    @Override
    public Users_Roles toUnescapedModel() {
        return new Users_RolesWrapper(_users_Roles.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _users_Roles.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _users_Roles.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _users_Roles.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Users_RolesWrapper)) {
            return false;
        }

        Users_RolesWrapper users_RolesWrapper = (Users_RolesWrapper) obj;

        if (Validator.equals(_users_Roles, users_RolesWrapper._users_Roles)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Users_Roles getWrappedUsers_Roles() {
        return _users_Roles;
    }

    @Override
    public Users_Roles getWrappedModel() {
        return _users_Roles;
    }

    @Override
    public void resetOriginalValues() {
        _users_Roles.resetOriginalValues();
    }
}

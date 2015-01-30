package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Role_}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Role_
 * @generated
 */
public class Role_Wrapper implements Role_, ModelWrapper<Role_> {
    private Role_ _role_;

    public Role_Wrapper(Role_ role_) {
        _role_ = role_;
    }

    @Override
    public Class<?> getModelClass() {
        return Role_.class;
    }

    @Override
    public String getModelClassName() {
        return Role_.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("roleId", getRoleId());
        attributes.put("name", getName());
        attributes.put("categoryName", getCategoryName());
        attributes.put("roleOrdinal", getRoleOrdinal());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long roleId = (Long) attributes.get("roleId");

        if (roleId != null) {
            setRoleId(roleId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String categoryName = (String) attributes.get("categoryName");

        if (categoryName != null) {
            setCategoryName(categoryName);
        }

        Integer roleOrdinal = (Integer) attributes.get("roleOrdinal");

        if (roleOrdinal != null) {
            setRoleOrdinal(roleOrdinal);
        }
    }

    /**
    * Returns the primary key of this role_.
    *
    * @return the primary key of this role_
    */
    @Override
    public long getPrimaryKey() {
        return _role_.getPrimaryKey();
    }

    /**
    * Sets the primary key of this role_.
    *
    * @param primaryKey the primary key of this role_
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _role_.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the role ID of this role_.
    *
    * @return the role ID of this role_
    */
    @Override
    public long getRoleId() {
        return _role_.getRoleId();
    }

    /**
    * Sets the role ID of this role_.
    *
    * @param roleId the role ID of this role_
    */
    @Override
    public void setRoleId(long roleId) {
        _role_.setRoleId(roleId);
    }

    /**
    * Returns the name of this role_.
    *
    * @return the name of this role_
    */
    @Override
    public java.lang.String getName() {
        return _role_.getName();
    }

    /**
    * Sets the name of this role_.
    *
    * @param name the name of this role_
    */
    @Override
    public void setName(java.lang.String name) {
        _role_.setName(name);
    }

    /**
    * Returns the category name of this role_.
    *
    * @return the category name of this role_
    */
    @Override
    public java.lang.String getCategoryName() {
        return _role_.getCategoryName();
    }

    /**
    * Sets the category name of this role_.
    *
    * @param categoryName the category name of this role_
    */
    @Override
    public void setCategoryName(java.lang.String categoryName) {
        _role_.setCategoryName(categoryName);
    }

    /**
    * Returns the role ordinal of this role_.
    *
    * @return the role ordinal of this role_
    */
    @Override
    public int getRoleOrdinal() {
        return _role_.getRoleOrdinal();
    }

    /**
    * Sets the role ordinal of this role_.
    *
    * @param roleOrdinal the role ordinal of this role_
    */
    @Override
    public void setRoleOrdinal(int roleOrdinal) {
        _role_.setRoleOrdinal(roleOrdinal);
    }

    @Override
    public boolean isNew() {
        return _role_.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _role_.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _role_.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _role_.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _role_.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _role_.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _role_.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _role_.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _role_.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _role_.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _role_.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new Role_Wrapper((Role_) _role_.clone());
    }

    @Override
    public int compareTo(Role_ role_) {
        return _role_.compareTo(role_);
    }

    @Override
    public int hashCode() {
        return _role_.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<Role_> toCacheModel() {
        return _role_.toCacheModel();
    }

    @Override
    public Role_ toEscapedModel() {
        return new Role_Wrapper(_role_.toEscapedModel());
    }

    @Override
    public Role_ toUnescapedModel() {
        return new Role_Wrapper(_role_.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _role_.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _role_.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Role_Wrapper)) {
            return false;
        }

        Role_Wrapper role_Wrapper = (Role_Wrapper) obj;

        if (Validator.equals(_role_, role_Wrapper._role_)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Role_ getWrappedRole_() {
        return _role_;
    }

    @Override
    public Role_ getWrappedModel() {
        return _role_;
    }

    @Override
    public void resetOriginalValues() {
        _role_.resetOriginalValues();
    }
}

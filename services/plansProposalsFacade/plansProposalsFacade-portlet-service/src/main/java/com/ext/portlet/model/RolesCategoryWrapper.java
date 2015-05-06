package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RolesCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RolesCategory
 * @generated
 */
public class RolesCategoryWrapper implements RolesCategory,
    ModelWrapper<RolesCategory> {
    private RolesCategory _rolesCategory;

    public RolesCategoryWrapper(RolesCategory rolesCategory) {
        _rolesCategory = rolesCategory;
    }

    @Override
    public Class<?> getModelClass() {
        return RolesCategory.class;
    }

    @Override
    public String getModelClassName() {
        return RolesCategory.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("roleId", getRoleId());
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
    * Returns the primary key of this roles category.
    *
    * @return the primary key of this roles category
    */
    @Override
    public long getPrimaryKey() {
        return _rolesCategory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this roles category.
    *
    * @param primaryKey the primary key of this roles category
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _rolesCategory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the role ID of this roles category.
    *
    * @return the role ID of this roles category
    */
    @Override
    public long getRoleId() {
        return _rolesCategory.getRoleId();
    }

    /**
    * Sets the role ID of this roles category.
    *
    * @param roleId the role ID of this roles category
    */
    @Override
    public void setRoleId(long roleId) {
        _rolesCategory.setRoleId(roleId);
    }

    /**
    * Returns the category name of this roles category.
    *
    * @return the category name of this roles category
    */
    @Override
    public java.lang.String getCategoryName() {
        return _rolesCategory.getCategoryName();
    }

    /**
    * Sets the category name of this roles category.
    *
    * @param categoryName the category name of this roles category
    */
    @Override
    public void setCategoryName(java.lang.String categoryName) {
        _rolesCategory.setCategoryName(categoryName);
    }

    /**
    * Returns the role ordinal of this roles category.
    *
    * @return the role ordinal of this roles category
    */
    @Override
    public int getRoleOrdinal() {
        return _rolesCategory.getRoleOrdinal();
    }

    /**
    * Sets the role ordinal of this roles category.
    *
    * @param roleOrdinal the role ordinal of this roles category
    */
    @Override
    public void setRoleOrdinal(int roleOrdinal) {
        _rolesCategory.setRoleOrdinal(roleOrdinal);
    }

    @Override
    public boolean isNew() {
        return _rolesCategory.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rolesCategory.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rolesCategory.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rolesCategory.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rolesCategory.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rolesCategory.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rolesCategory.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rolesCategory.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _rolesCategory.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rolesCategory.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _rolesCategory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RolesCategoryWrapper((RolesCategory) _rolesCategory.clone());
    }

    @Override
    public int compareTo(RolesCategory rolesCategory) {
        return _rolesCategory.compareTo(rolesCategory);
    }

    @Override
    public int hashCode() {
        return _rolesCategory.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<RolesCategory> toCacheModel() {
        return _rolesCategory.toCacheModel();
    }

    @Override
    public RolesCategory toEscapedModel() {
        return new RolesCategoryWrapper(_rolesCategory.toEscapedModel());
    }

    @Override
    public RolesCategory toUnescapedModel() {
        return new RolesCategoryWrapper(_rolesCategory.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rolesCategory.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rolesCategory.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _rolesCategory.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RolesCategoryWrapper)) {
            return false;
        }

        RolesCategoryWrapper rolesCategoryWrapper = (RolesCategoryWrapper) obj;

        if (Validator.equals(_rolesCategory, rolesCategoryWrapper._rolesCategory)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RolesCategory getWrappedRolesCategory() {
        return _rolesCategory;
    }

    @Override
    public RolesCategory getWrappedModel() {
        return _rolesCategory;
    }

    @Override
    public void resetOriginalValues() {
        _rolesCategory.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestTeamMemberRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberRole
 * @generated
 */
public class ContestTeamMemberRoleWrapper implements ContestTeamMemberRole,
    ModelWrapper<ContestTeamMemberRole> {
    private ContestTeamMemberRole _contestTeamMemberRole;

    public ContestTeamMemberRoleWrapper(
        ContestTeamMemberRole contestTeamMemberRole) {
        _contestTeamMemberRole = contestTeamMemberRole;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestTeamMemberRole.class;
    }

    @Override
    public String getModelClassName() {
        return ContestTeamMemberRole.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("role", getRole());
        attributes.put("sort", getSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String role = (String) attributes.get("role");

        if (role != null) {
            setRole(role);
        }

        Integer sort = (Integer) attributes.get("sort");

        if (sort != null) {
            setSort(sort);
        }
    }

    /**
    * Returns the primary key of this contest team member role.
    *
    * @return the primary key of this contest team member role
    */
    @Override
    public long getPrimaryKey() {
        return _contestTeamMemberRole.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest team member role.
    *
    * @param primaryKey the primary key of this contest team member role
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestTeamMemberRole.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest team member role.
    *
    * @return the ID of this contest team member role
    */
    @Override
    public long getId() {
        return _contestTeamMemberRole.getId();
    }

    /**
    * Sets the ID of this contest team member role.
    *
    * @param id the ID of this contest team member role
    */
    @Override
    public void setId(long id) {
        _contestTeamMemberRole.setId(id);
    }

    /**
    * Returns the role of this contest team member role.
    *
    * @return the role of this contest team member role
    */
    @Override
    public java.lang.String getRole() {
        return _contestTeamMemberRole.getRole();
    }

    /**
    * Sets the role of this contest team member role.
    *
    * @param role the role of this contest team member role
    */
    @Override
    public void setRole(java.lang.String role) {
        _contestTeamMemberRole.setRole(role);
    }

    /**
    * Returns the sort of this contest team member role.
    *
    * @return the sort of this contest team member role
    */
    @Override
    public int getSort() {
        return _contestTeamMemberRole.getSort();
    }

    /**
    * Sets the sort of this contest team member role.
    *
    * @param sort the sort of this contest team member role
    */
    @Override
    public void setSort(int sort) {
        _contestTeamMemberRole.setSort(sort);
    }

    @Override
    public boolean isNew() {
        return _contestTeamMemberRole.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestTeamMemberRole.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestTeamMemberRole.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestTeamMemberRole.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestTeamMemberRole.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestTeamMemberRole.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestTeamMemberRole.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestTeamMemberRole.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestTeamMemberRole.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestTeamMemberRole.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestTeamMemberRole.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestTeamMemberRoleWrapper((ContestTeamMemberRole) _contestTeamMemberRole.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole) {
        return _contestTeamMemberRole.compareTo(contestTeamMemberRole);
    }

    @Override
    public int hashCode() {
        return _contestTeamMemberRole.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ContestTeamMemberRole> toCacheModel() {
        return _contestTeamMemberRole.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ContestTeamMemberRole toEscapedModel() {
        return new ContestTeamMemberRoleWrapper(_contestTeamMemberRole.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ContestTeamMemberRole toUnescapedModel() {
        return new ContestTeamMemberRoleWrapper(_contestTeamMemberRole.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestTeamMemberRole.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestTeamMemberRole.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestTeamMemberRole.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestTeamMemberRoleWrapper)) {
            return false;
        }

        ContestTeamMemberRoleWrapper contestTeamMemberRoleWrapper = (ContestTeamMemberRoleWrapper) obj;

        if (Validator.equals(_contestTeamMemberRole,
                    contestTeamMemberRoleWrapper._contestTeamMemberRole)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestTeamMemberRole getWrappedContestTeamMemberRole() {
        return _contestTeamMemberRole;
    }

    @Override
    public ContestTeamMemberRole getWrappedModel() {
        return _contestTeamMemberRole;
    }

    @Override
    public void resetOriginalValues() {
        _contestTeamMemberRole.resetOriginalValues();
    }
}

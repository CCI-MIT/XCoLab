package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StaffMember}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffMember
 * @generated
 */
public class StaffMemberWrapper implements StaffMember,
    ModelWrapper<StaffMember> {
    private StaffMember _staffMember;

    public StaffMemberWrapper(StaffMember staffMember) {
        _staffMember = staffMember;
    }

    @Override
    public Class<?> getModelClass() {
        return StaffMember.class;
    }

    @Override
    public String getModelClassName() {
        return StaffMember.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("userId", getUserId());
        attributes.put("categoryId", getCategoryId());
        attributes.put("firstNames", getFirstNames());
        attributes.put("lastName", getLastName());
        attributes.put("url", getUrl());
        attributes.put("photoUrl", getPhotoUrl());
        attributes.put("role", getRole());
        attributes.put("organization", getOrganization());
        attributes.put("sort", getSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long categoryId = (Long) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        String firstNames = (String) attributes.get("firstNames");

        if (firstNames != null) {
            setFirstNames(firstNames);
        }

        String lastName = (String) attributes.get("lastName");

        if (lastName != null) {
            setLastName(lastName);
        }

        String url = (String) attributes.get("url");

        if (url != null) {
            setUrl(url);
        }

        String photoUrl = (String) attributes.get("photoUrl");

        if (photoUrl != null) {
            setPhotoUrl(photoUrl);
        }

        String role = (String) attributes.get("role");

        if (role != null) {
            setRole(role);
        }

        String organization = (String) attributes.get("organization");

        if (organization != null) {
            setOrganization(organization);
        }

        Integer sort = (Integer) attributes.get("sort");

        if (sort != null) {
            setSort(sort);
        }
    }

    /**
    * Returns the primary key of this staff member.
    *
    * @return the primary key of this staff member
    */
    @Override
    public long getPrimaryKey() {
        return _staffMember.getPrimaryKey();
    }

    /**
    * Sets the primary key of this staff member.
    *
    * @param primaryKey the primary key of this staff member
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _staffMember.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this staff member.
    *
    * @return the ID of this staff member
    */
    @Override
    public long getId() {
        return _staffMember.getId();
    }

    /**
    * Sets the ID of this staff member.
    *
    * @param id the ID of this staff member
    */
    @Override
    public void setId(long id) {
        _staffMember.setId(id);
    }

    /**
    * Returns the user ID of this staff member.
    *
    * @return the user ID of this staff member
    */
    @Override
    public long getUserId() {
        return _staffMember.getUserId();
    }

    /**
    * Sets the user ID of this staff member.
    *
    * @param userId the user ID of this staff member
    */
    @Override
    public void setUserId(long userId) {
        _staffMember.setUserId(userId);
    }

    /**
    * Returns the user uuid of this staff member.
    *
    * @return the user uuid of this staff member
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _staffMember.getUserUuid();
    }

    /**
    * Sets the user uuid of this staff member.
    *
    * @param userUuid the user uuid of this staff member
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _staffMember.setUserUuid(userUuid);
    }

    /**
    * Returns the category ID of this staff member.
    *
    * @return the category ID of this staff member
    */
    @Override
    public long getCategoryId() {
        return _staffMember.getCategoryId();
    }

    /**
    * Sets the category ID of this staff member.
    *
    * @param categoryId the category ID of this staff member
    */
    @Override
    public void setCategoryId(long categoryId) {
        _staffMember.setCategoryId(categoryId);
    }

    /**
    * Returns the first names of this staff member.
    *
    * @return the first names of this staff member
    */
    @Override
    public java.lang.String getFirstNames() {
        return _staffMember.getFirstNames();
    }

    /**
    * Sets the first names of this staff member.
    *
    * @param firstNames the first names of this staff member
    */
    @Override
    public void setFirstNames(java.lang.String firstNames) {
        _staffMember.setFirstNames(firstNames);
    }

    /**
    * Returns the last name of this staff member.
    *
    * @return the last name of this staff member
    */
    @Override
    public java.lang.String getLastName() {
        return _staffMember.getLastName();
    }

    /**
    * Sets the last name of this staff member.
    *
    * @param lastName the last name of this staff member
    */
    @Override
    public void setLastName(java.lang.String lastName) {
        _staffMember.setLastName(lastName);
    }

    /**
    * Returns the url of this staff member.
    *
    * @return the url of this staff member
    */
    @Override
    public java.lang.String getUrl() {
        return _staffMember.getUrl();
    }

    /**
    * Sets the url of this staff member.
    *
    * @param url the url of this staff member
    */
    @Override
    public void setUrl(java.lang.String url) {
        _staffMember.setUrl(url);
    }

    /**
    * Returns the photo url of this staff member.
    *
    * @return the photo url of this staff member
    */
    @Override
    public java.lang.String getPhotoUrl() {
        return _staffMember.getPhotoUrl();
    }

    /**
    * Sets the photo url of this staff member.
    *
    * @param photoUrl the photo url of this staff member
    */
    @Override
    public void setPhotoUrl(java.lang.String photoUrl) {
        _staffMember.setPhotoUrl(photoUrl);
    }

    /**
    * Returns the role of this staff member.
    *
    * @return the role of this staff member
    */
    @Override
    public java.lang.String getRole() {
        return _staffMember.getRole();
    }

    /**
    * Sets the role of this staff member.
    *
    * @param role the role of this staff member
    */
    @Override
    public void setRole(java.lang.String role) {
        _staffMember.setRole(role);
    }

    /**
    * Returns the organization of this staff member.
    *
    * @return the organization of this staff member
    */
    @Override
    public java.lang.String getOrganization() {
        return _staffMember.getOrganization();
    }

    /**
    * Sets the organization of this staff member.
    *
    * @param organization the organization of this staff member
    */
    @Override
    public void setOrganization(java.lang.String organization) {
        _staffMember.setOrganization(organization);
    }

    /**
    * Returns the sort of this staff member.
    *
    * @return the sort of this staff member
    */
    @Override
    public int getSort() {
        return _staffMember.getSort();
    }

    /**
    * Sets the sort of this staff member.
    *
    * @param sort the sort of this staff member
    */
    @Override
    public void setSort(int sort) {
        _staffMember.setSort(sort);
    }

    @Override
    public boolean isNew() {
        return _staffMember.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _staffMember.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _staffMember.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _staffMember.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _staffMember.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _staffMember.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _staffMember.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _staffMember.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _staffMember.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _staffMember.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _staffMember.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StaffMemberWrapper((StaffMember) _staffMember.clone());
    }

    @Override
    public int compareTo(StaffMember staffMember) {
        return _staffMember.compareTo(staffMember);
    }

    @Override
    public int hashCode() {
        return _staffMember.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<StaffMember> toCacheModel() {
        return _staffMember.toCacheModel();
    }

    @Override
    public StaffMember toEscapedModel() {
        return new StaffMemberWrapper(_staffMember.toEscapedModel());
    }

    @Override
    public StaffMember toUnescapedModel() {
        return new StaffMemberWrapper(_staffMember.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _staffMember.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _staffMember.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _staffMember.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StaffMemberWrapper)) {
            return false;
        }

        StaffMemberWrapper staffMemberWrapper = (StaffMemberWrapper) obj;

        if (Validator.equals(_staffMember, staffMemberWrapper._staffMember)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StaffMember getWrappedStaffMember() {
        return _staffMember;
    }

    @Override
    public StaffMember getWrappedModel() {
        return _staffMember;
    }

    @Override
    public void resetOriginalValues() {
        _staffMember.resetOriginalValues();
    }
}

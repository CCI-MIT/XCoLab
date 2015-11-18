package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MemberCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberCategory
 * @generated
 */
public class MemberCategoryWrapper implements MemberCategory,
    ModelWrapper<MemberCategory> {
    private MemberCategory _memberCategory;

    public MemberCategoryWrapper(MemberCategory memberCategory) {
        _memberCategory = memberCategory;
    }

    @Override
    public Class<?> getModelClass() {
        return MemberCategory.class;
    }

    @Override
    public String getModelClassName() {
        return MemberCategory.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("roleId", getRoleId());
        attributes.put("displayName", getDisplayName());
        attributes.put("categoryName", getCategoryName());
        attributes.put("sortOrder", getSortOrder());
        attributes.put("showInList", getShowInList());
        attributes.put("imageName", getImageName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long roleId = (Long) attributes.get("roleId");

        if (roleId != null) {
            setRoleId(roleId);
        }

        String displayName = (String) attributes.get("displayName");

        if (displayName != null) {
            setDisplayName(displayName);
        }

        String categoryName = (String) attributes.get("categoryName");

        if (categoryName != null) {
            setCategoryName(categoryName);
        }

        Long sortOrder = (Long) attributes.get("sortOrder");

        if (sortOrder != null) {
            setSortOrder(sortOrder);
        }

        Boolean showInList = (Boolean) attributes.get("showInList");

        if (showInList != null) {
            setShowInList(showInList);
        }

        String imageName = (String) attributes.get("imageName");

        if (imageName != null) {
            setImageName(imageName);
        }
    }

    /**
    * Returns the primary key of this member category.
    *
    * @return the primary key of this member category
    */
    @Override
    public long getPrimaryKey() {
        return _memberCategory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this member category.
    *
    * @param primaryKey the primary key of this member category
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _memberCategory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the role ID of this member category.
    *
    * @return the role ID of this member category
    */
    @Override
    public long getRoleId() {
        return _memberCategory.getRoleId();
    }

    /**
    * Sets the role ID of this member category.
    *
    * @param roleId the role ID of this member category
    */
    @Override
    public void setRoleId(long roleId) {
        _memberCategory.setRoleId(roleId);
    }

    /**
    * Returns the display name of this member category.
    *
    * @return the display name of this member category
    */
    @Override
    public java.lang.String getDisplayName() {
        return _memberCategory.getDisplayName();
    }

    /**
    * Sets the display name of this member category.
    *
    * @param displayName the display name of this member category
    */
    @Override
    public void setDisplayName(java.lang.String displayName) {
        _memberCategory.setDisplayName(displayName);
    }

    /**
    * Returns the category name of this member category.
    *
    * @return the category name of this member category
    */
    @Override
    public java.lang.String getCategoryName() {
        return _memberCategory.getCategoryName();
    }

    /**
    * Sets the category name of this member category.
    *
    * @param categoryName the category name of this member category
    */
    @Override
    public void setCategoryName(java.lang.String categoryName) {
        _memberCategory.setCategoryName(categoryName);
    }

    /**
    * Returns the sort order of this member category.
    *
    * @return the sort order of this member category
    */
    @Override
    public long getSortOrder() {
        return _memberCategory.getSortOrder();
    }

    /**
    * Sets the sort order of this member category.
    *
    * @param sortOrder the sort order of this member category
    */
    @Override
    public void setSortOrder(long sortOrder) {
        _memberCategory.setSortOrder(sortOrder);
    }

    /**
    * Returns the show in list of this member category.
    *
    * @return the show in list of this member category
    */
    @Override
    public boolean getShowInList() {
        return _memberCategory.getShowInList();
    }

    /**
    * Returns <code>true</code> if this member category is show in list.
    *
    * @return <code>true</code> if this member category is show in list; <code>false</code> otherwise
    */
    @Override
    public boolean isShowInList() {
        return _memberCategory.isShowInList();
    }

    /**
    * Sets whether this member category is show in list.
    *
    * @param showInList the show in list of this member category
    */
    @Override
    public void setShowInList(boolean showInList) {
        _memberCategory.setShowInList(showInList);
    }

    /**
    * Returns the image name of this member category.
    *
    * @return the image name of this member category
    */
    @Override
    public java.lang.String getImageName() {
        return _memberCategory.getImageName();
    }

    /**
    * Sets the image name of this member category.
    *
    * @param imageName the image name of this member category
    */
    @Override
    public void setImageName(java.lang.String imageName) {
        _memberCategory.setImageName(imageName);
    }

    @Override
    public boolean isNew() {
        return _memberCategory.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _memberCategory.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _memberCategory.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _memberCategory.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _memberCategory.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _memberCategory.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _memberCategory.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _memberCategory.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _memberCategory.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _memberCategory.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _memberCategory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MemberCategoryWrapper((MemberCategory) _memberCategory.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.MemberCategory memberCategory) {
        return _memberCategory.compareTo(memberCategory);
    }

    @Override
    public int hashCode() {
        return _memberCategory.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.MemberCategory> toCacheModel() {
        return _memberCategory.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.MemberCategory toEscapedModel() {
        return new MemberCategoryWrapper(_memberCategory.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.MemberCategory toUnescapedModel() {
        return new MemberCategoryWrapper(_memberCategory.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _memberCategory.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _memberCategory.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _memberCategory.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MemberCategoryWrapper)) {
            return false;
        }

        MemberCategoryWrapper memberCategoryWrapper = (MemberCategoryWrapper) obj;

        if (Validator.equals(_memberCategory,
                    memberCategoryWrapper._memberCategory)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MemberCategory getWrappedMemberCategory() {
        return _memberCategory;
    }

    @Override
    public MemberCategory getWrappedModel() {
        return _memberCategory;
    }

    @Override
    public void resetOriginalValues() {
        _memberCategory.resetOriginalValues();
    }
}

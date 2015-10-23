package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestType
 * @generated
 */
public class ContestTypeWrapper implements ContestType,
    ModelWrapper<ContestType> {
    private ContestType _contestType;

    public ContestTypeWrapper(ContestType contestType) {
        _contestType = contestType;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestType.class;
    }

    @Override
    public String getModelClassName() {
        return ContestType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("contestName", getContestName());
        attributes.put("contestNamePlural", getContestNamePlural());
        attributes.put("proposalName", getProposalName());
        attributes.put("proposalNamePlural", getProposalNamePlural());
        attributes.put("portletName", getPortletName());
        attributes.put("portletUrl", getPortletUrl());
        attributes.put("menuItemName", getMenuItemName());
        attributes.put("hasDiscussion", getHasDiscussion());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String contestName = (String) attributes.get("contestName");

        if (contestName != null) {
            setContestName(contestName);
        }

        String contestNamePlural = (String) attributes.get("contestNamePlural");

        if (contestNamePlural != null) {
            setContestNamePlural(contestNamePlural);
        }

        String proposalName = (String) attributes.get("proposalName");

        if (proposalName != null) {
            setProposalName(proposalName);
        }

        String proposalNamePlural = (String) attributes.get(
                "proposalNamePlural");

        if (proposalNamePlural != null) {
            setProposalNamePlural(proposalNamePlural);
        }

        String portletName = (String) attributes.get("portletName");

        if (portletName != null) {
            setPortletName(portletName);
        }

        String portletUrl = (String) attributes.get("portletUrl");

        if (portletUrl != null) {
            setPortletUrl(portletUrl);
        }

        String menuItemName = (String) attributes.get("menuItemName");

        if (menuItemName != null) {
            setMenuItemName(menuItemName);
        }

        Boolean hasDiscussion = (Boolean) attributes.get("hasDiscussion");

        if (hasDiscussion != null) {
            setHasDiscussion(hasDiscussion);
        }
    }

    /**
    * Returns the primary key of this contest type.
    *
    * @return the primary key of this contest type
    */
    @Override
    public long getPrimaryKey() {
        return _contestType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest type.
    *
    * @param primaryKey the primary key of this contest type
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest type.
    *
    * @return the ID of this contest type
    */
    @Override
    public long getId() {
        return _contestType.getId();
    }

    /**
    * Sets the ID of this contest type.
    *
    * @param id the ID of this contest type
    */
    @Override
    public void setId(long id) {
        _contestType.setId(id);
    }

    /**
    * Returns the contest name of this contest type.
    *
    * @return the contest name of this contest type
    */
    @Override
    public java.lang.String getContestName() {
        return _contestType.getContestName();
    }

    /**
    * Sets the contest name of this contest type.
    *
    * @param contestName the contest name of this contest type
    */
    @Override
    public void setContestName(java.lang.String contestName) {
        _contestType.setContestName(contestName);
    }

    /**
    * Returns the contest name plural of this contest type.
    *
    * @return the contest name plural of this contest type
    */
    @Override
    public java.lang.String getContestNamePlural() {
        return _contestType.getContestNamePlural();
    }

    /**
    * Sets the contest name plural of this contest type.
    *
    * @param contestNamePlural the contest name plural of this contest type
    */
    @Override
    public void setContestNamePlural(java.lang.String contestNamePlural) {
        _contestType.setContestNamePlural(contestNamePlural);
    }

    /**
    * Returns the proposal name of this contest type.
    *
    * @return the proposal name of this contest type
    */
    @Override
    public java.lang.String getProposalName() {
        return _contestType.getProposalName();
    }

    /**
    * Sets the proposal name of this contest type.
    *
    * @param proposalName the proposal name of this contest type
    */
    @Override
    public void setProposalName(java.lang.String proposalName) {
        _contestType.setProposalName(proposalName);
    }

    /**
    * Returns the proposal name plural of this contest type.
    *
    * @return the proposal name plural of this contest type
    */
    @Override
    public java.lang.String getProposalNamePlural() {
        return _contestType.getProposalNamePlural();
    }

    /**
    * Sets the proposal name plural of this contest type.
    *
    * @param proposalNamePlural the proposal name plural of this contest type
    */
    @Override
    public void setProposalNamePlural(java.lang.String proposalNamePlural) {
        _contestType.setProposalNamePlural(proposalNamePlural);
    }

    /**
    * Returns the portlet name of this contest type.
    *
    * @return the portlet name of this contest type
    */
    @Override
    public java.lang.String getPortletName() {
        return _contestType.getPortletName();
    }

    /**
    * Sets the portlet name of this contest type.
    *
    * @param portletName the portlet name of this contest type
    */
    @Override
    public void setPortletName(java.lang.String portletName) {
        _contestType.setPortletName(portletName);
    }

    /**
    * Returns the portlet url of this contest type.
    *
    * @return the portlet url of this contest type
    */
    @Override
    public java.lang.String getPortletUrl() {
        return _contestType.getPortletUrl();
    }

    /**
    * Sets the portlet url of this contest type.
    *
    * @param portletUrl the portlet url of this contest type
    */
    @Override
    public void setPortletUrl(java.lang.String portletUrl) {
        _contestType.setPortletUrl(portletUrl);
    }

    /**
    * Returns the menu item name of this contest type.
    *
    * @return the menu item name of this contest type
    */
    @Override
    public java.lang.String getMenuItemName() {
        return _contestType.getMenuItemName();
    }

    /**
    * Sets the menu item name of this contest type.
    *
    * @param menuItemName the menu item name of this contest type
    */
    @Override
    public void setMenuItemName(java.lang.String menuItemName) {
        _contestType.setMenuItemName(menuItemName);
    }

    /**
    * Returns the has discussion of this contest type.
    *
    * @return the has discussion of this contest type
    */
    @Override
    public boolean getHasDiscussion() {
        return _contestType.getHasDiscussion();
    }

    /**
    * Returns <code>true</code> if this contest type is has discussion.
    *
    * @return <code>true</code> if this contest type is has discussion; <code>false</code> otherwise
    */
    @Override
    public boolean isHasDiscussion() {
        return _contestType.isHasDiscussion();
    }

    /**
    * Sets whether this contest type is has discussion.
    *
    * @param hasDiscussion the has discussion of this contest type
    */
    @Override
    public void setHasDiscussion(boolean hasDiscussion) {
        _contestType.setHasDiscussion(hasDiscussion);
    }

    @Override
    public boolean isNew() {
        return _contestType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestTypeWrapper((ContestType) _contestType.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.ContestType contestType) {
        return _contestType.compareTo(contestType);
    }

    @Override
    public int hashCode() {
        return _contestType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ContestType> toCacheModel() {
        return _contestType.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ContestType toEscapedModel() {
        return new ContestTypeWrapper(_contestType.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ContestType toUnescapedModel() {
        return new ContestTypeWrapper(_contestType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestTypeWrapper)) {
            return false;
        }

        ContestTypeWrapper contestTypeWrapper = (ContestTypeWrapper) obj;

        if (Validator.equals(_contestType, contestTypeWrapper._contestType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestType getWrappedContestType() {
        return _contestType;
    }

    @Override
    public ContestType getWrappedModel() {
        return _contestType;
    }

    @Override
    public void resetOriginalValues() {
        _contestType.resetOriginalValues();
    }
}

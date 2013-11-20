package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalAttributeType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeType
 * @generated
 */
public class ProposalAttributeTypeWrapper implements ProposalAttributeType,
    ModelWrapper<ProposalAttributeType> {
    private ProposalAttributeType _proposalAttributeType;

    public ProposalAttributeTypeWrapper(
        ProposalAttributeType proposalAttributeType) {
        _proposalAttributeType = proposalAttributeType;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalAttributeType.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalAttributeType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("name", getName());
        attributes.put("visibleInVersionHistory", getVisibleInVersionHistory());
        attributes.put("copyOnPromote", getCopyOnPromote());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Boolean visibleInVersionHistory = (Boolean) attributes.get(
                "visibleInVersionHistory");

        if (visibleInVersionHistory != null) {
            setVisibleInVersionHistory(visibleInVersionHistory);
        }

        Boolean copyOnPromote = (Boolean) attributes.get("copyOnPromote");

        if (copyOnPromote != null) {
            setCopyOnPromote(copyOnPromote);
        }
    }

    /**
    * Returns the primary key of this proposal attribute type.
    *
    * @return the primary key of this proposal attribute type
    */
    @Override
    public java.lang.String getPrimaryKey() {
        return _proposalAttributeType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal attribute type.
    *
    * @param primaryKey the primary key of this proposal attribute type
    */
    @Override
    public void setPrimaryKey(java.lang.String primaryKey) {
        _proposalAttributeType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the name of this proposal attribute type.
    *
    * @return the name of this proposal attribute type
    */
    @Override
    public java.lang.String getName() {
        return _proposalAttributeType.getName();
    }

    /**
    * Sets the name of this proposal attribute type.
    *
    * @param name the name of this proposal attribute type
    */
    @Override
    public void setName(java.lang.String name) {
        _proposalAttributeType.setName(name);
    }

    /**
    * Returns the visible in version history of this proposal attribute type.
    *
    * @return the visible in version history of this proposal attribute type
    */
    @Override
    public boolean getVisibleInVersionHistory() {
        return _proposalAttributeType.getVisibleInVersionHistory();
    }

    /**
    * Returns <code>true</code> if this proposal attribute type is visible in version history.
    *
    * @return <code>true</code> if this proposal attribute type is visible in version history; <code>false</code> otherwise
    */
    @Override
    public boolean isVisibleInVersionHistory() {
        return _proposalAttributeType.isVisibleInVersionHistory();
    }

    /**
    * Sets whether this proposal attribute type is visible in version history.
    *
    * @param visibleInVersionHistory the visible in version history of this proposal attribute type
    */
    @Override
    public void setVisibleInVersionHistory(boolean visibleInVersionHistory) {
        _proposalAttributeType.setVisibleInVersionHistory(visibleInVersionHistory);
    }

    /**
    * Returns the copy on promote of this proposal attribute type.
    *
    * @return the copy on promote of this proposal attribute type
    */
    @Override
    public boolean getCopyOnPromote() {
        return _proposalAttributeType.getCopyOnPromote();
    }

    /**
    * Returns <code>true</code> if this proposal attribute type is copy on promote.
    *
    * @return <code>true</code> if this proposal attribute type is copy on promote; <code>false</code> otherwise
    */
    @Override
    public boolean isCopyOnPromote() {
        return _proposalAttributeType.isCopyOnPromote();
    }

    /**
    * Sets whether this proposal attribute type is copy on promote.
    *
    * @param copyOnPromote the copy on promote of this proposal attribute type
    */
    @Override
    public void setCopyOnPromote(boolean copyOnPromote) {
        _proposalAttributeType.setCopyOnPromote(copyOnPromote);
    }

    @Override
    public boolean isNew() {
        return _proposalAttributeType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalAttributeType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalAttributeType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalAttributeType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalAttributeType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalAttributeType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalAttributeType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalAttributeType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalAttributeType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalAttributeType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalAttributeType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalAttributeTypeWrapper((ProposalAttributeType) _proposalAttributeType.clone());
    }

    @Override
    public int compareTo(ProposalAttributeType proposalAttributeType) {
        return _proposalAttributeType.compareTo(proposalAttributeType);
    }

    @Override
    public int hashCode() {
        return _proposalAttributeType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ProposalAttributeType> toCacheModel() {
        return _proposalAttributeType.toCacheModel();
    }

    @Override
    public ProposalAttributeType toEscapedModel() {
        return new ProposalAttributeTypeWrapper(_proposalAttributeType.toEscapedModel());
    }

    @Override
    public ProposalAttributeType toUnescapedModel() {
        return new ProposalAttributeTypeWrapper(_proposalAttributeType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalAttributeType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalAttributeType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalAttributeType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalAttributeTypeWrapper)) {
            return false;
        }

        ProposalAttributeTypeWrapper proposalAttributeTypeWrapper = (ProposalAttributeTypeWrapper) obj;

        if (Validator.equals(_proposalAttributeType,
                    proposalAttributeTypeWrapper._proposalAttributeType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalAttributeType getWrappedProposalAttributeType() {
        return _proposalAttributeType;
    }

    @Override
    public ProposalAttributeType getWrappedModel() {
        return _proposalAttributeType;
    }

    @Override
    public void resetOriginalValues() {
        _proposalAttributeType.resetOriginalValues();
    }
}

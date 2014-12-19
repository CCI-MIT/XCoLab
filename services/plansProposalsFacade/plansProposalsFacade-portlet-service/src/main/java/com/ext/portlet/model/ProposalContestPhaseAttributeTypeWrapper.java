package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalContestPhaseAttributeType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeType
 * @generated
 */
public class ProposalContestPhaseAttributeTypeWrapper
    implements ProposalContestPhaseAttributeType,
        ModelWrapper<ProposalContestPhaseAttributeType> {
    private ProposalContestPhaseAttributeType _proposalContestPhaseAttributeType;

    public ProposalContestPhaseAttributeTypeWrapper(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        _proposalContestPhaseAttributeType = proposalContestPhaseAttributeType;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalContestPhaseAttributeType.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalContestPhaseAttributeType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("name", getName());
        attributes.put("copyOnPromote", getCopyOnPromote());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Boolean copyOnPromote = (Boolean) attributes.get("copyOnPromote");

        if (copyOnPromote != null) {
            setCopyOnPromote(copyOnPromote);
        }
    }

    /**
    * Returns the primary key of this proposal contest phase attribute type.
    *
    * @return the primary key of this proposal contest phase attribute type
    */
    @Override
    public java.lang.String getPrimaryKey() {
        return _proposalContestPhaseAttributeType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal contest phase attribute type.
    *
    * @param primaryKey the primary key of this proposal contest phase attribute type
    */
    @Override
    public void setPrimaryKey(java.lang.String primaryKey) {
        _proposalContestPhaseAttributeType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the name of this proposal contest phase attribute type.
    *
    * @return the name of this proposal contest phase attribute type
    */
    @Override
    public java.lang.String getName() {
        return _proposalContestPhaseAttributeType.getName();
    }

    /**
    * Sets the name of this proposal contest phase attribute type.
    *
    * @param name the name of this proposal contest phase attribute type
    */
    @Override
    public void setName(java.lang.String name) {
        _proposalContestPhaseAttributeType.setName(name);
    }

    /**
    * Returns the copy on promote of this proposal contest phase attribute type.
    *
    * @return the copy on promote of this proposal contest phase attribute type
    */
    @Override
    public boolean getCopyOnPromote() {
        return _proposalContestPhaseAttributeType.getCopyOnPromote();
    }

    /**
    * Returns <code>true</code> if this proposal contest phase attribute type is copy on promote.
    *
    * @return <code>true</code> if this proposal contest phase attribute type is copy on promote; <code>false</code> otherwise
    */
    @Override
    public boolean isCopyOnPromote() {
        return _proposalContestPhaseAttributeType.isCopyOnPromote();
    }

    /**
    * Sets whether this proposal contest phase attribute type is copy on promote.
    *
    * @param copyOnPromote the copy on promote of this proposal contest phase attribute type
    */
    @Override
    public void setCopyOnPromote(boolean copyOnPromote) {
        _proposalContestPhaseAttributeType.setCopyOnPromote(copyOnPromote);
    }

    @Override
    public boolean isNew() {
        return _proposalContestPhaseAttributeType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalContestPhaseAttributeType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalContestPhaseAttributeType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalContestPhaseAttributeType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalContestPhaseAttributeType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalContestPhaseAttributeType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalContestPhaseAttributeType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalContestPhaseAttributeType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalContestPhaseAttributeType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalContestPhaseAttributeType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalContestPhaseAttributeType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalContestPhaseAttributeTypeWrapper((ProposalContestPhaseAttributeType) _proposalContestPhaseAttributeType.clone());
    }

    @Override
    public int compareTo(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        return _proposalContestPhaseAttributeType.compareTo(proposalContestPhaseAttributeType);
    }

    @Override
    public int hashCode() {
        return _proposalContestPhaseAttributeType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ProposalContestPhaseAttributeType> toCacheModel() {
        return _proposalContestPhaseAttributeType.toCacheModel();
    }

    @Override
    public ProposalContestPhaseAttributeType toEscapedModel() {
        return new ProposalContestPhaseAttributeTypeWrapper(_proposalContestPhaseAttributeType.toEscapedModel());
    }

    @Override
    public ProposalContestPhaseAttributeType toUnescapedModel() {
        return new ProposalContestPhaseAttributeTypeWrapper(_proposalContestPhaseAttributeType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalContestPhaseAttributeType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalContestPhaseAttributeType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalContestPhaseAttributeTypeWrapper)) {
            return false;
        }

        ProposalContestPhaseAttributeTypeWrapper proposalContestPhaseAttributeTypeWrapper =
            (ProposalContestPhaseAttributeTypeWrapper) obj;

        if (Validator.equals(_proposalContestPhaseAttributeType,
                    proposalContestPhaseAttributeTypeWrapper._proposalContestPhaseAttributeType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalContestPhaseAttributeType getWrappedProposalContestPhaseAttributeType() {
        return _proposalContestPhaseAttributeType;
    }

    @Override
    public ProposalContestPhaseAttributeType getWrappedModel() {
        return _proposalContestPhaseAttributeType;
    }

    @Override
    public void resetOriginalValues() {
        _proposalContestPhaseAttributeType.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalVersion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVersion
 * @generated
 */
public class ProposalVersionWrapper implements ProposalVersion,
    ModelWrapper<ProposalVersion> {
    private ProposalVersion _proposalVersion;

    public ProposalVersionWrapper(ProposalVersion proposalVersion) {
        _proposalVersion = proposalVersion;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalVersion.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalVersion.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("version", getVersion());
        attributes.put("authorId", getAuthorId());
        attributes.put("createDate", getCreateDate());
        attributes.put("updateType", getUpdateType());
        attributes.put("updateAdditionalId", getUpdateAdditionalId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Integer version = (Integer) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        String updateType = (String) attributes.get("updateType");

        if (updateType != null) {
            setUpdateType(updateType);
        }

        Long updateAdditionalId = (Long) attributes.get("updateAdditionalId");

        if (updateAdditionalId != null) {
            setUpdateAdditionalId(updateAdditionalId);
        }
    }

    /**
    * Returns the primary key of this proposal version.
    *
    * @return the primary key of this proposal version
    */
    @Override
    public com.ext.portlet.service.persistence.ProposalVersionPK getPrimaryKey() {
        return _proposalVersion.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal version.
    *
    * @param primaryKey the primary key of this proposal version
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ProposalVersionPK primaryKey) {
        _proposalVersion.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal version.
    *
    * @return the proposal ID of this proposal version
    */
    @Override
    public long getProposalId() {
        return _proposalVersion.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal version.
    *
    * @param proposalId the proposal ID of this proposal version
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposalVersion.setProposalId(proposalId);
    }

    /**
    * Returns the version of this proposal version.
    *
    * @return the version of this proposal version
    */
    @Override
    public int getVersion() {
        return _proposalVersion.getVersion();
    }

    /**
    * Sets the version of this proposal version.
    *
    * @param version the version of this proposal version
    */
    @Override
    public void setVersion(int version) {
        _proposalVersion.setVersion(version);
    }

    /**
    * Returns the author ID of this proposal version.
    *
    * @return the author ID of this proposal version
    */
    @Override
    public long getAuthorId() {
        return _proposalVersion.getAuthorId();
    }

    /**
    * Sets the author ID of this proposal version.
    *
    * @param authorId the author ID of this proposal version
    */
    @Override
    public void setAuthorId(long authorId) {
        _proposalVersion.setAuthorId(authorId);
    }

    /**
    * Returns the create date of this proposal version.
    *
    * @return the create date of this proposal version
    */
    @Override
    public java.util.Date getCreateDate() {
        return _proposalVersion.getCreateDate();
    }

    /**
    * Sets the create date of this proposal version.
    *
    * @param createDate the create date of this proposal version
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _proposalVersion.setCreateDate(createDate);
    }

    /**
    * Returns the update type of this proposal version.
    *
    * @return the update type of this proposal version
    */
    @Override
    public java.lang.String getUpdateType() {
        return _proposalVersion.getUpdateType();
    }

    /**
    * Sets the update type of this proposal version.
    *
    * @param updateType the update type of this proposal version
    */
    @Override
    public void setUpdateType(java.lang.String updateType) {
        _proposalVersion.setUpdateType(updateType);
    }

    /**
    * Returns the update additional ID of this proposal version.
    *
    * @return the update additional ID of this proposal version
    */
    @Override
    public long getUpdateAdditionalId() {
        return _proposalVersion.getUpdateAdditionalId();
    }

    /**
    * Sets the update additional ID of this proposal version.
    *
    * @param updateAdditionalId the update additional ID of this proposal version
    */
    @Override
    public void setUpdateAdditionalId(long updateAdditionalId) {
        _proposalVersion.setUpdateAdditionalId(updateAdditionalId);
    }

    @Override
    public boolean isNew() {
        return _proposalVersion.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalVersion.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalVersion.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalVersion.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalVersion.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalVersion.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalVersion.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalVersion.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalVersion.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalVersion.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalVersion.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalVersionWrapper((ProposalVersion) _proposalVersion.clone());
    }

    @Override
    public int compareTo(ProposalVersion proposalVersion) {
        return _proposalVersion.compareTo(proposalVersion);
    }

    @Override
    public int hashCode() {
        return _proposalVersion.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ProposalVersion> toCacheModel() {
        return _proposalVersion.toCacheModel();
    }

    @Override
    public ProposalVersion toEscapedModel() {
        return new ProposalVersionWrapper(_proposalVersion.toEscapedModel());
    }

    @Override
    public ProposalVersion toUnescapedModel() {
        return new ProposalVersionWrapper(_proposalVersion.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalVersion.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalVersion.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalVersion.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalVersionWrapper)) {
            return false;
        }

        ProposalVersionWrapper proposalVersionWrapper = (ProposalVersionWrapper) obj;

        if (Validator.equals(_proposalVersion,
                    proposalVersionWrapper._proposalVersion)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalVersion getWrappedProposalVersion() {
        return _proposalVersion;
    }

    @Override
    public ProposalVersion getWrappedModel() {
        return _proposalVersion;
    }

    @Override
    public void resetOriginalValues() {
        _proposalVersion.resetOriginalValues();
    }
}

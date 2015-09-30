package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalSupporter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalSupporter
 * @generated
 */
public class ProposalSupporterWrapper implements ProposalSupporter,
    ModelWrapper<ProposalSupporter> {
    private ProposalSupporter _proposalSupporter;

    public ProposalSupporterWrapper(ProposalSupporter proposalSupporter) {
        _proposalSupporter = proposalSupporter;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalSupporter.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalSupporter.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this proposal supporter.
    *
    * @return the primary key of this proposal supporter
    */
    @Override
    public com.ext.portlet.service.persistence.ProposalSupporterPK getPrimaryKey() {
        return _proposalSupporter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal supporter.
    *
    * @param primaryKey the primary key of this proposal supporter
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ProposalSupporterPK primaryKey) {
        _proposalSupporter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal supporter.
    *
    * @return the proposal ID of this proposal supporter
    */
    @Override
    public long getProposalId() {
        return _proposalSupporter.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal supporter.
    *
    * @param proposalId the proposal ID of this proposal supporter
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposalSupporter.setProposalId(proposalId);
    }

    /**
    * Returns the user ID of this proposal supporter.
    *
    * @return the user ID of this proposal supporter
    */
    @Override
    public long getUserId() {
        return _proposalSupporter.getUserId();
    }

    /**
    * Sets the user ID of this proposal supporter.
    *
    * @param userId the user ID of this proposal supporter
    */
    @Override
    public void setUserId(long userId) {
        _proposalSupporter.setUserId(userId);
    }

    /**
    * Returns the user uuid of this proposal supporter.
    *
    * @return the user uuid of this proposal supporter
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalSupporter.getUserUuid();
    }

    /**
    * Sets the user uuid of this proposal supporter.
    *
    * @param userUuid the user uuid of this proposal supporter
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _proposalSupporter.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this proposal supporter.
    *
    * @return the create date of this proposal supporter
    */
    @Override
    public java.util.Date getCreateDate() {
        return _proposalSupporter.getCreateDate();
    }

    /**
    * Sets the create date of this proposal supporter.
    *
    * @param createDate the create date of this proposal supporter
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _proposalSupporter.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _proposalSupporter.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalSupporter.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalSupporter.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalSupporter.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalSupporter.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalSupporter.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalSupporter.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalSupporter.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalSupporter.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalSupporter.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalSupporter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalSupporterWrapper((ProposalSupporter) _proposalSupporter.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ProposalSupporter proposalSupporter) {
        return _proposalSupporter.compareTo(proposalSupporter);
    }

    @Override
    public int hashCode() {
        return _proposalSupporter.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ProposalSupporter> toCacheModel() {
        return _proposalSupporter.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ProposalSupporter toEscapedModel() {
        return new ProposalSupporterWrapper(_proposalSupporter.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ProposalSupporter toUnescapedModel() {
        return new ProposalSupporterWrapper(_proposalSupporter.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalSupporter.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalSupporter.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalSupporter.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalSupporterWrapper)) {
            return false;
        }

        ProposalSupporterWrapper proposalSupporterWrapper = (ProposalSupporterWrapper) obj;

        if (Validator.equals(_proposalSupporter,
                    proposalSupporterWrapper._proposalSupporter)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalSupporter getWrappedProposalSupporter() {
        return _proposalSupporter;
    }

    @Override
    public ProposalSupporter getWrappedModel() {
        return _proposalSupporter;
    }

    @Override
    public void resetOriginalValues() {
        _proposalSupporter.resetOriginalValues();
    }
}

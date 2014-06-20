package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalRatingType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingType
 * @generated
 */
public class ProposalRatingTypeWrapper implements ProposalRatingType,
    ModelWrapper<ProposalRatingType> {
    private ProposalRatingType _proposalRatingType;

    public ProposalRatingTypeWrapper(ProposalRatingType proposalRatingType) {
        _proposalRatingType = proposalRatingType;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalRatingType.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalRatingType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ratingTypeId", getRatingTypeId());
        attributes.put("label", getLabel());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ratingTypeId = (Long) attributes.get("ratingTypeId");

        if (ratingTypeId != null) {
            setRatingTypeId(ratingTypeId);
        }

        String label = (String) attributes.get("label");

        if (label != null) {
            setLabel(label);
        }
    }

    /**
    * Returns the primary key of this proposal rating type.
    *
    * @return the primary key of this proposal rating type
    */
    @Override
    public long getPrimaryKey() {
        return _proposalRatingType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal rating type.
    *
    * @param primaryKey the primary key of this proposal rating type
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _proposalRatingType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the rating type ID of this proposal rating type.
    *
    * @return the rating type ID of this proposal rating type
    */
    @Override
    public long getRatingTypeId() {
        return _proposalRatingType.getRatingTypeId();
    }

    /**
    * Sets the rating type ID of this proposal rating type.
    *
    * @param ratingTypeId the rating type ID of this proposal rating type
    */
    @Override
    public void setRatingTypeId(long ratingTypeId) {
        _proposalRatingType.setRatingTypeId(ratingTypeId);
    }

    /**
    * Returns the label of this proposal rating type.
    *
    * @return the label of this proposal rating type
    */
    @Override
    public java.lang.String getLabel() {
        return _proposalRatingType.getLabel();
    }

    /**
    * Sets the label of this proposal rating type.
    *
    * @param label the label of this proposal rating type
    */
    @Override
    public void setLabel(java.lang.String label) {
        _proposalRatingType.setLabel(label);
    }

    @Override
    public boolean isNew() {
        return _proposalRatingType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalRatingType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalRatingType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalRatingType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalRatingType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalRatingType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalRatingType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalRatingType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalRatingType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalRatingType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalRatingType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalRatingTypeWrapper((ProposalRatingType) _proposalRatingType.clone());
    }

    @Override
    public int compareTo(ProposalRatingType proposalRatingType) {
        return _proposalRatingType.compareTo(proposalRatingType);
    }

    @Override
    public int hashCode() {
        return _proposalRatingType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ProposalRatingType> toCacheModel() {
        return _proposalRatingType.toCacheModel();
    }

    @Override
    public ProposalRatingType toEscapedModel() {
        return new ProposalRatingTypeWrapper(_proposalRatingType.toEscapedModel());
    }

    @Override
    public ProposalRatingType toUnescapedModel() {
        return new ProposalRatingTypeWrapper(_proposalRatingType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalRatingType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalRatingType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalRatingType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalRatingTypeWrapper)) {
            return false;
        }

        ProposalRatingTypeWrapper proposalRatingTypeWrapper = (ProposalRatingTypeWrapper) obj;

        if (Validator.equals(_proposalRatingType,
                    proposalRatingTypeWrapper._proposalRatingType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalRatingType getWrappedProposalRatingType() {
        return _proposalRatingType;
    }

    @Override
    public ProposalRatingType getWrappedModel() {
        return _proposalRatingType;
    }

    @Override
    public void resetOriginalValues() {
        _proposalRatingType.resetOriginalValues();
    }
}

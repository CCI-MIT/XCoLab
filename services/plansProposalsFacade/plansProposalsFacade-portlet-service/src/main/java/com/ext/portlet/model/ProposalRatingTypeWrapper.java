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

        attributes.put("id", getId());
        attributes.put("label", getLabel());
        attributes.put("description", getDescription());
        attributes.put("judgeType", getJudgeType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String label = (String) attributes.get("label");

        if (label != null) {
            setLabel(label);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Integer judgeType = (Integer) attributes.get("judgeType");

        if (judgeType != null) {
            setJudgeType(judgeType);
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
    * Returns the ID of this proposal rating type.
    *
    * @return the ID of this proposal rating type
    */
    @Override
    public long getId() {
        return _proposalRatingType.getId();
    }

    /**
    * Sets the ID of this proposal rating type.
    *
    * @param id the ID of this proposal rating type
    */
    @Override
    public void setId(long id) {
        _proposalRatingType.setId(id);
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

    /**
    * Returns the description of this proposal rating type.
    *
    * @return the description of this proposal rating type
    */
    @Override
    public java.lang.String getDescription() {
        return _proposalRatingType.getDescription();
    }

    /**
    * Sets the description of this proposal rating type.
    *
    * @param description the description of this proposal rating type
    */
    @Override
    public void setDescription(java.lang.String description) {
        _proposalRatingType.setDescription(description);
    }

    /**
    * Returns the judge type of this proposal rating type.
    *
    * @return the judge type of this proposal rating type
    */
    @Override
    public int getJudgeType() {
        return _proposalRatingType.getJudgeType();
    }

    /**
    * Sets the judge type of this proposal rating type.
    *
    * @param judgeType the judge type of this proposal rating type
    */
    @Override
    public void setJudgeType(int judgeType) {
        _proposalRatingType.setJudgeType(judgeType);
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
    public java.util.List<com.ext.portlet.model.ProposalRatingValue> getRatingValues()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingType.getRatingValues();
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

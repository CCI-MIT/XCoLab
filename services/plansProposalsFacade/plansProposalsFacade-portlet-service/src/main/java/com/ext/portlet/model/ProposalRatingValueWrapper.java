package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalRatingValue}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValue
 * @generated
 */
public class ProposalRatingValueWrapper implements ProposalRatingValue,
    ModelWrapper<ProposalRatingValue> {
    private ProposalRatingValue _proposalRatingValue;

    public ProposalRatingValueWrapper(ProposalRatingValue proposalRatingValue) {
        _proposalRatingValue = proposalRatingValue;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalRatingValue.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalRatingValue.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("ratingTypeId", getRatingTypeId());
        attributes.put("value", getValue());
        attributes.put("name", getName());
        attributes.put("description", getDescription());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long ratingTypeId = (Long) attributes.get("ratingTypeId");

        if (ratingTypeId != null) {
            setRatingTypeId(ratingTypeId);
        }

        Long value = (Long) attributes.get("value");

        if (value != null) {
            setValue(value);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }
    }

    /**
    * Returns the primary key of this proposal rating value.
    *
    * @return the primary key of this proposal rating value
    */
    @Override
    public long getPrimaryKey() {
        return _proposalRatingValue.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal rating value.
    *
    * @param primaryKey the primary key of this proposal rating value
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _proposalRatingValue.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this proposal rating value.
    *
    * @return the ID of this proposal rating value
    */
    @Override
    public long getId() {
        return _proposalRatingValue.getId();
    }

    /**
    * Sets the ID of this proposal rating value.
    *
    * @param id the ID of this proposal rating value
    */
    @Override
    public void setId(long id) {
        _proposalRatingValue.setId(id);
    }

    /**
    * Returns the rating type ID of this proposal rating value.
    *
    * @return the rating type ID of this proposal rating value
    */
    @Override
    public long getRatingTypeId() {
        return _proposalRatingValue.getRatingTypeId();
    }

    /**
    * Sets the rating type ID of this proposal rating value.
    *
    * @param ratingTypeId the rating type ID of this proposal rating value
    */
    @Override
    public void setRatingTypeId(long ratingTypeId) {
        _proposalRatingValue.setRatingTypeId(ratingTypeId);
    }

    /**
    * Returns the value of this proposal rating value.
    *
    * @return the value of this proposal rating value
    */
    @Override
    public long getValue() {
        return _proposalRatingValue.getValue();
    }

    /**
    * Sets the value of this proposal rating value.
    *
    * @param value the value of this proposal rating value
    */
    @Override
    public void setValue(long value) {
        _proposalRatingValue.setValue(value);
    }

    /**
    * Returns the name of this proposal rating value.
    *
    * @return the name of this proposal rating value
    */
    @Override
    public java.lang.String getName() {
        return _proposalRatingValue.getName();
    }

    /**
    * Sets the name of this proposal rating value.
    *
    * @param name the name of this proposal rating value
    */
    @Override
    public void setName(java.lang.String name) {
        _proposalRatingValue.setName(name);
    }

    /**
    * Returns the description of this proposal rating value.
    *
    * @return the description of this proposal rating value
    */
    @Override
    public java.lang.String getDescription() {
        return _proposalRatingValue.getDescription();
    }

    /**
    * Sets the description of this proposal rating value.
    *
    * @param description the description of this proposal rating value
    */
    @Override
    public void setDescription(java.lang.String description) {
        _proposalRatingValue.setDescription(description);
    }

    @Override
    public boolean isNew() {
        return _proposalRatingValue.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalRatingValue.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalRatingValue.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalRatingValue.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalRatingValue.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalRatingValue.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalRatingValue.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalRatingValue.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalRatingValue.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalRatingValue.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalRatingValue.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalRatingValueWrapper((ProposalRatingValue) _proposalRatingValue.clone());
    }

    @Override
    public int compareTo(ProposalRatingValue proposalRatingValue) {
        return _proposalRatingValue.compareTo(proposalRatingValue);
    }

    @Override
    public int hashCode() {
        return _proposalRatingValue.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ProposalRatingValue> toCacheModel() {
        return _proposalRatingValue.toCacheModel();
    }

    @Override
    public ProposalRatingValue toEscapedModel() {
        return new ProposalRatingValueWrapper(_proposalRatingValue.toEscapedModel());
    }

    @Override
    public ProposalRatingValue toUnescapedModel() {
        return new ProposalRatingValueWrapper(_proposalRatingValue.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalRatingValue.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalRatingValue.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalRatingValue.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalRatingValueWrapper)) {
            return false;
        }

        ProposalRatingValueWrapper proposalRatingValueWrapper = (ProposalRatingValueWrapper) obj;

        if (Validator.equals(_proposalRatingValue,
                    proposalRatingValueWrapper._proposalRatingValue)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalRatingValue getWrappedProposalRatingValue() {
        return _proposalRatingValue;
    }

    @Override
    public ProposalRatingValue getWrappedModel() {
        return _proposalRatingValue;
    }

    @Override
    public void resetOriginalValues() {
        _proposalRatingValue.resetOriginalValues();
    }
}

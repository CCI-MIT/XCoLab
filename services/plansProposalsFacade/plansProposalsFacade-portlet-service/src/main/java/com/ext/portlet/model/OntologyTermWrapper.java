package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OntologyTerm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTerm
 * @generated
 */
public class OntologyTermWrapper implements OntologyTerm,
    ModelWrapper<OntologyTerm> {
    private OntologyTerm _ontologyTerm;

    public OntologyTermWrapper(OntologyTerm ontologyTerm) {
        _ontologyTerm = ontologyTerm;
    }

    @Override
    public Class<?> getModelClass() {
        return OntologyTerm.class;
    }

    @Override
    public String getModelClassName() {
        return OntologyTerm.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("parentId", getParentId());
        attributes.put("ontologySpaceId", getOntologySpaceId());
        attributes.put("name", getName());
        attributes.put("descriptionUrl", getDescriptionUrl());
        attributes.put("order_", getOrder_());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long parentId = (Long) attributes.get("parentId");

        if (parentId != null) {
            setParentId(parentId);
        }

        Long ontologySpaceId = (Long) attributes.get("ontologySpaceId");

        if (ontologySpaceId != null) {
            setOntologySpaceId(ontologySpaceId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String descriptionUrl = (String) attributes.get("descriptionUrl");

        if (descriptionUrl != null) {
            setDescriptionUrl(descriptionUrl);
        }

        Integer order_ = (Integer) attributes.get("order_");

        if (order_ != null) {
            setOrder_(order_);
        }
    }

    /**
    * Returns the primary key of this ontology term.
    *
    * @return the primary key of this ontology term
    */
    @Override
    public long getPrimaryKey() {
        return _ontologyTerm.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ontology term.
    *
    * @param primaryKey the primary key of this ontology term
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _ontologyTerm.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this ontology term.
    *
    * @return the ID of this ontology term
    */
    @Override
    public long getId() {
        return _ontologyTerm.getId();
    }

    /**
    * Sets the ID of this ontology term.
    *
    * @param id the ID of this ontology term
    */
    @Override
    public void setId(long id) {
        _ontologyTerm.setId(id);
    }

    /**
    * Returns the parent ID of this ontology term.
    *
    * @return the parent ID of this ontology term
    */
    @Override
    public long getParentId() {
        return _ontologyTerm.getParentId();
    }

    /**
    * Sets the parent ID of this ontology term.
    *
    * @param parentId the parent ID of this ontology term
    */
    @Override
    public void setParentId(long parentId) {
        _ontologyTerm.setParentId(parentId);
    }

    /**
    * Returns the ontology space ID of this ontology term.
    *
    * @return the ontology space ID of this ontology term
    */
    @Override
    public long getOntologySpaceId() {
        return _ontologyTerm.getOntologySpaceId();
    }

    /**
    * Sets the ontology space ID of this ontology term.
    *
    * @param ontologySpaceId the ontology space ID of this ontology term
    */
    @Override
    public void setOntologySpaceId(long ontologySpaceId) {
        _ontologyTerm.setOntologySpaceId(ontologySpaceId);
    }

    /**
    * Returns the name of this ontology term.
    *
    * @return the name of this ontology term
    */
    @Override
    public java.lang.String getName() {
        return _ontologyTerm.getName();
    }

    /**
    * Sets the name of this ontology term.
    *
    * @param name the name of this ontology term
    */
    @Override
    public void setName(java.lang.String name) {
        _ontologyTerm.setName(name);
    }

    /**
    * Returns the description url of this ontology term.
    *
    * @return the description url of this ontology term
    */
    @Override
    public java.lang.String getDescriptionUrl() {
        return _ontologyTerm.getDescriptionUrl();
    }

    /**
    * Sets the description url of this ontology term.
    *
    * @param descriptionUrl the description url of this ontology term
    */
    @Override
    public void setDescriptionUrl(java.lang.String descriptionUrl) {
        _ontologyTerm.setDescriptionUrl(descriptionUrl);
    }

    /**
    * Returns the order_ of this ontology term.
    *
    * @return the order_ of this ontology term
    */
    @Override
    public int getOrder_() {
        return _ontologyTerm.getOrder_();
    }

    /**
    * Sets the order_ of this ontology term.
    *
    * @param order_ the order_ of this ontology term
    */
    @Override
    public void setOrder_(int order_) {
        _ontologyTerm.setOrder_(order_);
    }

    @Override
    public boolean isNew() {
        return _ontologyTerm.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ontologyTerm.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ontologyTerm.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ontologyTerm.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ontologyTerm.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ontologyTerm.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ontologyTerm.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ontologyTerm.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _ontologyTerm.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ontologyTerm.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ontologyTerm.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new OntologyTermWrapper((OntologyTerm) _ontologyTerm.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.OntologyTerm ontologyTerm) {
        return _ontologyTerm.compareTo(ontologyTerm);
    }

    @Override
    public int hashCode() {
        return _ontologyTerm.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.OntologyTerm> toCacheModel() {
        return _ontologyTerm.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.OntologyTerm toEscapedModel() {
        return new OntologyTermWrapper(_ontologyTerm.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.OntologyTerm toUnescapedModel() {
        return new OntologyTermWrapper(_ontologyTerm.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ontologyTerm.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ontologyTerm.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTerm.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OntologyTermWrapper)) {
            return false;
        }

        OntologyTermWrapper ontologyTermWrapper = (OntologyTermWrapper) obj;

        if (Validator.equals(_ontologyTerm, ontologyTermWrapper._ontologyTerm)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public OntologyTerm getWrappedOntologyTerm() {
        return _ontologyTerm;
    }

    @Override
    public OntologyTerm getWrappedModel() {
        return _ontologyTerm;
    }

    @Override
    public void resetOriginalValues() {
        _ontologyTerm.resetOriginalValues();
    }
}

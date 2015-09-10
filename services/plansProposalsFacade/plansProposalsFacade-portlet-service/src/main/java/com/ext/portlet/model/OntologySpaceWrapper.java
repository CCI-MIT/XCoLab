package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OntologySpace}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologySpace
 * @generated
 */
public class OntologySpaceWrapper implements OntologySpace,
    ModelWrapper<OntologySpace> {
    private OntologySpace _ontologySpace;

    public OntologySpaceWrapper(OntologySpace ontologySpace) {
        _ontologySpace = ontologySpace;
    }

    @Override
    public Class<?> getModelClass() {
        return OntologySpace.class;
    }

    @Override
    public String getModelClassName() {
        return OntologySpace.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("order", getOrder());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Integer order = (Integer) attributes.get("order");

        if (order != null) {
            setOrder(order);
        }
    }

    /**
    * Returns the primary key of this ontology space.
    *
    * @return the primary key of this ontology space
    */
    @Override
    public long getPrimaryKey() {
        return _ontologySpace.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ontology space.
    *
    * @param primaryKey the primary key of this ontology space
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _ontologySpace.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this ontology space.
    *
    * @return the ID of this ontology space
    */
    @Override
    public long getId() {
        return _ontologySpace.getId();
    }

    /**
    * Sets the ID of this ontology space.
    *
    * @param id the ID of this ontology space
    */
    @Override
    public void setId(long id) {
        _ontologySpace.setId(id);
    }

    /**
    * Returns the name of this ontology space.
    *
    * @return the name of this ontology space
    */
    @Override
    public java.lang.String getName() {
        return _ontologySpace.getName();
    }

    /**
    * Sets the name of this ontology space.
    *
    * @param name the name of this ontology space
    */
    @Override
    public void setName(java.lang.String name) {
        _ontologySpace.setName(name);
    }

    /**
    * Returns the description of this ontology space.
    *
    * @return the description of this ontology space
    */
    @Override
    public java.lang.String getDescription() {
        return _ontologySpace.getDescription();
    }

    /**
    * Sets the description of this ontology space.
    *
    * @param description the description of this ontology space
    */
    @Override
    public void setDescription(java.lang.String description) {
        _ontologySpace.setDescription(description);
    }

    /**
    * Returns the order of this ontology space.
    *
    * @return the order of this ontology space
    */
    @Override
    public int getOrder() {
        return _ontologySpace.getOrder();
    }

    /**
    * Sets the order of this ontology space.
    *
    * @param order the order of this ontology space
    */
    @Override
    public void setOrder(int order) {
        _ontologySpace.setOrder(order);
    }

    @Override
    public boolean isNew() {
        return _ontologySpace.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ontologySpace.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ontologySpace.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ontologySpace.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ontologySpace.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ontologySpace.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ontologySpace.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ontologySpace.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _ontologySpace.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ontologySpace.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ontologySpace.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new OntologySpaceWrapper((OntologySpace) _ontologySpace.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.OntologySpace ontologySpace) {
        return _ontologySpace.compareTo(ontologySpace);
    }

    @Override
    public int hashCode() {
        return _ontologySpace.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.OntologySpace> toCacheModel() {
        return _ontologySpace.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.OntologySpace toEscapedModel() {
        return new OntologySpaceWrapper(_ontologySpace.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.OntologySpace toUnescapedModel() {
        return new OntologySpaceWrapper(_ontologySpace.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ontologySpace.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ontologySpace.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologySpace.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OntologySpaceWrapper)) {
            return false;
        }

        OntologySpaceWrapper ontologySpaceWrapper = (OntologySpaceWrapper) obj;

        if (Validator.equals(_ontologySpace, ontologySpaceWrapper._ontologySpace)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public OntologySpace getWrappedOntologySpace() {
        return _ontologySpace;
    }

    @Override
    public OntologySpace getWrappedModel() {
        return _ontologySpace;
    }

    @Override
    public void resetOriginalValues() {
        _ontologySpace.resetOriginalValues();
    }
}

package com.ext.portlet.ontology.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OntologySpace}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OntologySpace
 * @generated
 */
public class OntologySpaceWrapper implements OntologySpace,
    ModelWrapper<OntologySpace> {
    private OntologySpace _ontologySpace;

    public OntologySpaceWrapper(OntologySpace ontologySpace) {
        _ontologySpace = ontologySpace;
    }

    public Class<?> getModelClass() {
        return OntologySpace.class;
    }

    public String getModelClassName() {
        return OntologySpace.class.getName();
    }

    /**
    * Returns the primary key of this ontology space.
    *
    * @return the primary key of this ontology space
    */
    public long getPrimaryKey() {
        return _ontologySpace.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ontology space.
    *
    * @param primaryKey the primary key of this ontology space
    */
    public void setPrimaryKey(long primaryKey) {
        _ontologySpace.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this ontology space.
    *
    * @return the ID of this ontology space
    */
    public long getId() {
        return _ontologySpace.getId();
    }

    /**
    * Sets the ID of this ontology space.
    *
    * @param id the ID of this ontology space
    */
    public void setId(long id) {
        _ontologySpace.setId(id);
    }

    /**
    * Returns the name of this ontology space.
    *
    * @return the name of this ontology space
    */
    public java.lang.String getName() {
        return _ontologySpace.getName();
    }

    /**
    * Sets the name of this ontology space.
    *
    * @param name the name of this ontology space
    */
    public void setName(java.lang.String name) {
        _ontologySpace.setName(name);
    }

    /**
    * Returns the description of this ontology space.
    *
    * @return the description of this ontology space
    */
    public java.lang.String getDescription() {
        return _ontologySpace.getDescription();
    }

    /**
    * Sets the description of this ontology space.
    *
    * @param description the description of this ontology space
    */
    public void setDescription(java.lang.String description) {
        _ontologySpace.setDescription(description);
    }

    public boolean isNew() {
        return _ontologySpace.isNew();
    }

    public void setNew(boolean n) {
        _ontologySpace.setNew(n);
    }

    public boolean isCachedModel() {
        return _ontologySpace.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _ontologySpace.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _ontologySpace.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _ontologySpace.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ontologySpace.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ontologySpace.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ontologySpace.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new OntologySpaceWrapper((OntologySpace) _ontologySpace.clone());
    }

    public int compareTo(OntologySpace ontologySpace) {
        return _ontologySpace.compareTo(ontologySpace);
    }

    @Override
    public int hashCode() {
        return _ontologySpace.hashCode();
    }

    public com.liferay.portal.model.CacheModel<OntologySpace> toCacheModel() {
        return _ontologySpace.toCacheModel();
    }

    public OntologySpace toEscapedModel() {
        return new OntologySpaceWrapper(_ontologySpace.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ontologySpace.toString();
    }

    public java.lang.String toXmlString() {
        return _ontologySpace.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologySpace.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public OntologySpace getWrappedOntologySpace() {
        return _ontologySpace;
    }

    public OntologySpace getWrappedModel() {
        return _ontologySpace;
    }

    public void resetOriginalValues() {
        _ontologySpace.resetOriginalValues();
    }
}

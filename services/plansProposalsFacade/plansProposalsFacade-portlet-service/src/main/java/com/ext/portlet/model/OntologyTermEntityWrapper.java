package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OntologyTermEntity}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OntologyTermEntity
 * @generated
 */
public class OntologyTermEntityWrapper implements OntologyTermEntity,
    ModelWrapper<OntologyTermEntity> {
    private OntologyTermEntity _ontologyTermEntity;

    public OntologyTermEntityWrapper(OntologyTermEntity ontologyTermEntity) {
        _ontologyTermEntity = ontologyTermEntity;
    }

    public Class<?> getModelClass() {
        return OntologyTermEntity.class;
    }

    public String getModelClassName() {
        return OntologyTermEntity.class.getName();
    }

    /**
    * Returns the primary key of this ontology term entity.
    *
    * @return the primary key of this ontology term entity
    */
    public long getPrimaryKey() {
        return _ontologyTermEntity.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ontology term entity.
    *
    * @param primaryKey the primary key of this ontology term entity
    */
    public void setPrimaryKey(long primaryKey) {
        _ontologyTermEntity.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this ontology term entity.
    *
    * @return the ID of this ontology term entity
    */
    public long getId() {
        return _ontologyTermEntity.getId();
    }

    /**
    * Sets the ID of this ontology term entity.
    *
    * @param id the ID of this ontology term entity
    */
    public void setId(long id) {
        _ontologyTermEntity.setId(id);
    }

    /**
    * Returns the term ID of this ontology term entity.
    *
    * @return the term ID of this ontology term entity
    */
    public long getTermId() {
        return _ontologyTermEntity.getTermId();
    }

    /**
    * Sets the term ID of this ontology term entity.
    *
    * @param termId the term ID of this ontology term entity
    */
    public void setTermId(long termId) {
        _ontologyTermEntity.setTermId(termId);
    }

    /**
    * Returns the fully qualified class name of this ontology term entity.
    *
    * @return the fully qualified class name of this ontology term entity
    */
    public java.lang.String getClassName() {
        return _ontologyTermEntity.getClassName();
    }

    /**
    * Returns the class name ID of this ontology term entity.
    *
    * @return the class name ID of this ontology term entity
    */
    public long getClassNameId() {
        return _ontologyTermEntity.getClassNameId();
    }

    /**
    * Sets the class name ID of this ontology term entity.
    *
    * @param classNameId the class name ID of this ontology term entity
    */
    public void setClassNameId(long classNameId) {
        _ontologyTermEntity.setClassNameId(classNameId);
    }

    /**
    * Returns the class p k of this ontology term entity.
    *
    * @return the class p k of this ontology term entity
    */
    public long getClassPK() {
        return _ontologyTermEntity.getClassPK();
    }

    /**
    * Sets the class p k of this ontology term entity.
    *
    * @param classPK the class p k of this ontology term entity
    */
    public void setClassPK(long classPK) {
        _ontologyTermEntity.setClassPK(classPK);
    }

    public boolean isNew() {
        return _ontologyTermEntity.isNew();
    }

    public void setNew(boolean n) {
        _ontologyTermEntity.setNew(n);
    }

    public boolean isCachedModel() {
        return _ontologyTermEntity.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _ontologyTermEntity.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _ontologyTermEntity.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _ontologyTermEntity.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ontologyTermEntity.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ontologyTermEntity.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ontologyTermEntity.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new OntologyTermEntityWrapper((OntologyTermEntity) _ontologyTermEntity.clone());
    }

    public int compareTo(OntologyTermEntity ontologyTermEntity) {
        return _ontologyTermEntity.compareTo(ontologyTermEntity);
    }

    @Override
    public int hashCode() {
        return _ontologyTermEntity.hashCode();
    }

    public com.liferay.portal.model.CacheModel<OntologyTermEntity> toCacheModel() {
        return _ontologyTermEntity.toCacheModel();
    }

    public OntologyTermEntity toEscapedModel() {
        return new OntologyTermEntityWrapper(_ontologyTermEntity.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ontologyTermEntity.toString();
    }

    public java.lang.String toXmlString() {
        return _ontologyTermEntity.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermEntity.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public OntologyTermEntity getWrappedOntologyTermEntity() {
        return _ontologyTermEntity;
    }

    public OntologyTermEntity getWrappedModel() {
        return _ontologyTermEntity;
    }

    public void resetOriginalValues() {
        _ontologyTermEntity.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OntologyTermEntity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologyTermEntity
 * @generated
 */
public class OntologyTermEntityWrapper implements OntologyTermEntity,
    ModelWrapper<OntologyTermEntity> {
    private OntologyTermEntity _ontologyTermEntity;

    public OntologyTermEntityWrapper(OntologyTermEntity ontologyTermEntity) {
        _ontologyTermEntity = ontologyTermEntity;
    }

    @Override
    public Class<?> getModelClass() {
        return OntologyTermEntity.class;
    }

    @Override
    public String getModelClassName() {
        return OntologyTermEntity.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("termId", getTermId());
        attributes.put("classNameId", getClassNameId());
        attributes.put("classPK", getClassPK());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long termId = (Long) attributes.get("termId");

        if (termId != null) {
            setTermId(termId);
        }

        Long classNameId = (Long) attributes.get("classNameId");

        if (classNameId != null) {
            setClassNameId(classNameId);
        }

        Long classPK = (Long) attributes.get("classPK");

        if (classPK != null) {
            setClassPK(classPK);
        }
    }

    /**
    * Returns the primary key of this ontology term entity.
    *
    * @return the primary key of this ontology term entity
    */
    @Override
    public long getPrimaryKey() {
        return _ontologyTermEntity.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ontology term entity.
    *
    * @param primaryKey the primary key of this ontology term entity
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _ontologyTermEntity.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this ontology term entity.
    *
    * @return the ID of this ontology term entity
    */
    @Override
    public long getId() {
        return _ontologyTermEntity.getId();
    }

    /**
    * Sets the ID of this ontology term entity.
    *
    * @param id the ID of this ontology term entity
    */
    @Override
    public void setId(long id) {
        _ontologyTermEntity.setId(id);
    }

    /**
    * Returns the term ID of this ontology term entity.
    *
    * @return the term ID of this ontology term entity
    */
    @Override
    public long getTermId() {
        return _ontologyTermEntity.getTermId();
    }

    /**
    * Sets the term ID of this ontology term entity.
    *
    * @param termId the term ID of this ontology term entity
    */
    @Override
    public void setTermId(long termId) {
        _ontologyTermEntity.setTermId(termId);
    }

    /**
    * Returns the fully qualified class name of this ontology term entity.
    *
    * @return the fully qualified class name of this ontology term entity
    */
    @Override
    public java.lang.String getClassName() {
        return _ontologyTermEntity.getClassName();
    }

    @Override
    public void setClassName(java.lang.String className) {
        _ontologyTermEntity.setClassName(className);
    }

    /**
    * Returns the class name ID of this ontology term entity.
    *
    * @return the class name ID of this ontology term entity
    */
    @Override
    public long getClassNameId() {
        return _ontologyTermEntity.getClassNameId();
    }

    /**
    * Sets the class name ID of this ontology term entity.
    *
    * @param classNameId the class name ID of this ontology term entity
    */
    @Override
    public void setClassNameId(long classNameId) {
        _ontologyTermEntity.setClassNameId(classNameId);
    }

    /**
    * Returns the class p k of this ontology term entity.
    *
    * @return the class p k of this ontology term entity
    */
    @Override
    public long getClassPK() {
        return _ontologyTermEntity.getClassPK();
    }

    /**
    * Sets the class p k of this ontology term entity.
    *
    * @param classPK the class p k of this ontology term entity
    */
    @Override
    public void setClassPK(long classPK) {
        _ontologyTermEntity.setClassPK(classPK);
    }

    @Override
    public boolean isNew() {
        return _ontologyTermEntity.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ontologyTermEntity.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ontologyTermEntity.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ontologyTermEntity.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ontologyTermEntity.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ontologyTermEntity.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ontologyTermEntity.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ontologyTermEntity.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _ontologyTermEntity.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ontologyTermEntity.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _ontologyTermEntity.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new OntologyTermEntityWrapper((OntologyTermEntity) _ontologyTermEntity.clone());
    }

    @Override
    public int compareTo(OntologyTermEntity ontologyTermEntity) {
        return _ontologyTermEntity.compareTo(ontologyTermEntity);
    }

    @Override
    public int hashCode() {
        return _ontologyTermEntity.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<OntologyTermEntity> toCacheModel() {
        return _ontologyTermEntity.toCacheModel();
    }

    @Override
    public OntologyTermEntity toEscapedModel() {
        return new OntologyTermEntityWrapper(_ontologyTermEntity.toEscapedModel());
    }

    @Override
    public OntologyTermEntity toUnescapedModel() {
        return new OntologyTermEntityWrapper(_ontologyTermEntity.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ontologyTermEntity.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ontologyTermEntity.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermEntity.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OntologyTermEntityWrapper)) {
            return false;
        }

        OntologyTermEntityWrapper ontologyTermEntityWrapper = (OntologyTermEntityWrapper) obj;

        if (Validator.equals(_ontologyTermEntity,
                    ontologyTermEntityWrapper._ontologyTermEntity)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public OntologyTermEntity getWrappedOntologyTermEntity() {
        return _ontologyTermEntity;
    }

    @Override
    public OntologyTermEntity getWrappedModel() {
        return _ontologyTermEntity;
    }

    @Override
    public void resetOriginalValues() {
        _ontologyTermEntity.resetOriginalValues();
    }
}

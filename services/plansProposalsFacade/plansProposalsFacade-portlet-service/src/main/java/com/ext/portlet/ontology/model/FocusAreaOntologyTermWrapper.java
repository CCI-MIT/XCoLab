package com.ext.portlet.ontology.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FocusAreaOntologyTerm}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FocusAreaOntologyTerm
 * @generated
 */
public class FocusAreaOntologyTermWrapper implements FocusAreaOntologyTerm,
    ModelWrapper<FocusAreaOntologyTerm> {
    private FocusAreaOntologyTerm _focusAreaOntologyTerm;

    public FocusAreaOntologyTermWrapper(
        FocusAreaOntologyTerm focusAreaOntologyTerm) {
        _focusAreaOntologyTerm = focusAreaOntologyTerm;
    }

    public Class<?> getModelClass() {
        return FocusAreaOntologyTerm.class;
    }

    public String getModelClassName() {
        return FocusAreaOntologyTerm.class.getName();
    }

    /**
    * Returns the primary key of this focus area ontology term.
    *
    * @return the primary key of this focus area ontology term
    */
    public com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK getPrimaryKey() {
        return _focusAreaOntologyTerm.getPrimaryKey();
    }

    /**
    * Sets the primary key of this focus area ontology term.
    *
    * @param primaryKey the primary key of this focus area ontology term
    */
    public void setPrimaryKey(
        com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK primaryKey) {
        _focusAreaOntologyTerm.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the focus area ID of this focus area ontology term.
    *
    * @return the focus area ID of this focus area ontology term
    */
    public java.lang.Long getFocusAreaId() {
        return _focusAreaOntologyTerm.getFocusAreaId();
    }

    /**
    * Sets the focus area ID of this focus area ontology term.
    *
    * @param focusAreaId the focus area ID of this focus area ontology term
    */
    public void setFocusAreaId(java.lang.Long focusAreaId) {
        _focusAreaOntologyTerm.setFocusAreaId(focusAreaId);
    }

    /**
    * Returns the ontology term ID of this focus area ontology term.
    *
    * @return the ontology term ID of this focus area ontology term
    */
    public java.lang.Long getOntologyTermId() {
        return _focusAreaOntologyTerm.getOntologyTermId();
    }

    /**
    * Sets the ontology term ID of this focus area ontology term.
    *
    * @param ontologyTermId the ontology term ID of this focus area ontology term
    */
    public void setOntologyTermId(java.lang.Long ontologyTermId) {
        _focusAreaOntologyTerm.setOntologyTermId(ontologyTermId);
    }

    public boolean isNew() {
        return _focusAreaOntologyTerm.isNew();
    }

    public void setNew(boolean n) {
        _focusAreaOntologyTerm.setNew(n);
    }

    public boolean isCachedModel() {
        return _focusAreaOntologyTerm.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _focusAreaOntologyTerm.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _focusAreaOntologyTerm.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _focusAreaOntologyTerm.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _focusAreaOntologyTerm.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _focusAreaOntologyTerm.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _focusAreaOntologyTerm.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FocusAreaOntologyTermWrapper((FocusAreaOntologyTerm) _focusAreaOntologyTerm.clone());
    }

    public int compareTo(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        return _focusAreaOntologyTerm.compareTo(focusAreaOntologyTerm);
    }

    @Override
    public int hashCode() {
        return _focusAreaOntologyTerm.hashCode();
    }

    public com.liferay.portal.model.CacheModel<FocusAreaOntologyTerm> toCacheModel() {
        return _focusAreaOntologyTerm.toCacheModel();
    }

    public FocusAreaOntologyTerm toEscapedModel() {
        return new FocusAreaOntologyTermWrapper(_focusAreaOntologyTerm.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _focusAreaOntologyTerm.toString();
    }

    public java.lang.String toXmlString() {
        return _focusAreaOntologyTerm.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusAreaOntologyTerm.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusAreaOntologyTerm.store();
    }

    public com.ext.portlet.ontology.model.OntologyTerm getTerm()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTerm.getTerm();
    }

    public com.ext.portlet.ontology.model.FocusArea getArea()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTerm.getArea();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public FocusAreaOntologyTerm getWrappedFocusAreaOntologyTerm() {
        return _focusAreaOntologyTerm;
    }

    public FocusAreaOntologyTerm getWrappedModel() {
        return _focusAreaOntologyTerm;
    }

    public void resetOriginalValues() {
        _focusAreaOntologyTerm.resetOriginalValues();
    }
}

package com.ext.portlet.ontology.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FocusArea}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FocusArea
 * @generated
 */
public class FocusAreaWrapper implements FocusArea, ModelWrapper<FocusArea> {
    private FocusArea _focusArea;

    public FocusAreaWrapper(FocusArea focusArea) {
        _focusArea = focusArea;
    }

    public Class<?> getModelClass() {
        return FocusArea.class;
    }

    public String getModelClassName() {
        return FocusArea.class.getName();
    }

    /**
    * Returns the primary key of this focus area.
    *
    * @return the primary key of this focus area
    */
    public java.lang.Long getPrimaryKey() {
        return _focusArea.getPrimaryKey();
    }

    /**
    * Sets the primary key of this focus area.
    *
    * @param primaryKey the primary key of this focus area
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _focusArea.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this focus area.
    *
    * @return the ID of this focus area
    */
    public java.lang.Long getId() {
        return _focusArea.getId();
    }

    /**
    * Sets the ID of this focus area.
    *
    * @param id the ID of this focus area
    */
    public void setId(java.lang.Long id) {
        _focusArea.setId(id);
    }

    /**
    * Returns the name of this focus area.
    *
    * @return the name of this focus area
    */
    public java.lang.String getName() {
        return _focusArea.getName();
    }

    /**
    * Sets the name of this focus area.
    *
    * @param name the name of this focus area
    */
    public void setName(java.lang.String name) {
        _focusArea.setName(name);
    }

    public boolean isNew() {
        return _focusArea.isNew();
    }

    public void setNew(boolean n) {
        _focusArea.setNew(n);
    }

    public boolean isCachedModel() {
        return _focusArea.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _focusArea.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _focusArea.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _focusArea.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _focusArea.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _focusArea.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _focusArea.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FocusAreaWrapper((FocusArea) _focusArea.clone());
    }

    public int compareTo(FocusArea focusArea) {
        return _focusArea.compareTo(focusArea);
    }

    @Override
    public int hashCode() {
        return _focusArea.hashCode();
    }

    public com.liferay.portal.model.CacheModel<FocusArea> toCacheModel() {
        return _focusArea.toCacheModel();
    }

    public FocusArea toEscapedModel() {
        return new FocusAreaWrapper(_focusArea.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _focusArea.toString();
    }

    public java.lang.String toXmlString() {
        return _focusArea.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusArea.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusArea.store();
    }

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getTerms()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusArea.getTerms();
    }

    public void removeTerm(java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusArea.removeTerm(termId);
    }

    public void addTerm(java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusArea.addTerm(termId);
    }

    public void tagClass(java.lang.Class clasz, java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusArea.tagClass(clasz, pk);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public FocusArea getWrappedFocusArea() {
        return _focusArea;
    }

    public FocusArea getWrappedModel() {
        return _focusArea;
    }

    public void resetOriginalValues() {
        _focusArea.resetOriginalValues();
    }
}

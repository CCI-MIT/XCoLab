package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FocusAreaOntologyTerm}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTerm
 * @generated
 */
public class FocusAreaOntologyTermWrapper implements FocusAreaOntologyTerm,
    ModelWrapper<FocusAreaOntologyTerm> {
    private FocusAreaOntologyTerm _focusAreaOntologyTerm;

    public FocusAreaOntologyTermWrapper(
        FocusAreaOntologyTerm focusAreaOntologyTerm) {
        _focusAreaOntologyTerm = focusAreaOntologyTerm;
    }

    @Override
    public Class<?> getModelClass() {
        return FocusAreaOntologyTerm.class;
    }

    @Override
    public String getModelClassName() {
        return FocusAreaOntologyTerm.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("focusAreaId", getFocusAreaId());
        attributes.put("ontologyTermId", getOntologyTermId());
        attributes.put("order", getOrder());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long focusAreaId = (Long) attributes.get("focusAreaId");

        if (focusAreaId != null) {
            setFocusAreaId(focusAreaId);
        }

        Long ontologyTermId = (Long) attributes.get("ontologyTermId");

        if (ontologyTermId != null) {
            setOntologyTermId(ontologyTermId);
        }

        Integer order = (Integer) attributes.get("order");

        if (order != null) {
            setOrder(order);
        }
    }

    /**
    * Returns the primary key of this focus area ontology term.
    *
    * @return the primary key of this focus area ontology term
    */
    @Override
    public com.ext.portlet.service.persistence.FocusAreaOntologyTermPK getPrimaryKey() {
        return _focusAreaOntologyTerm.getPrimaryKey();
    }

    /**
    * Sets the primary key of this focus area ontology term.
    *
    * @param primaryKey the primary key of this focus area ontology term
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK primaryKey) {
        _focusAreaOntologyTerm.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the focus area ID of this focus area ontology term.
    *
    * @return the focus area ID of this focus area ontology term
    */
    @Override
    public long getFocusAreaId() {
        return _focusAreaOntologyTerm.getFocusAreaId();
    }

    /**
    * Sets the focus area ID of this focus area ontology term.
    *
    * @param focusAreaId the focus area ID of this focus area ontology term
    */
    @Override
    public void setFocusAreaId(long focusAreaId) {
        _focusAreaOntologyTerm.setFocusAreaId(focusAreaId);
    }

    /**
    * Returns the ontology term ID of this focus area ontology term.
    *
    * @return the ontology term ID of this focus area ontology term
    */
    @Override
    public long getOntologyTermId() {
        return _focusAreaOntologyTerm.getOntologyTermId();
    }

    /**
    * Sets the ontology term ID of this focus area ontology term.
    *
    * @param ontologyTermId the ontology term ID of this focus area ontology term
    */
    @Override
    public void setOntologyTermId(long ontologyTermId) {
        _focusAreaOntologyTerm.setOntologyTermId(ontologyTermId);
    }

    /**
    * Returns the order of this focus area ontology term.
    *
    * @return the order of this focus area ontology term
    */
    @Override
    public int getOrder() {
        return _focusAreaOntologyTerm.getOrder();
    }

    /**
    * Sets the order of this focus area ontology term.
    *
    * @param order the order of this focus area ontology term
    */
    @Override
    public void setOrder(int order) {
        _focusAreaOntologyTerm.setOrder(order);
    }

    @Override
    public boolean isNew() {
        return _focusAreaOntologyTerm.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _focusAreaOntologyTerm.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _focusAreaOntologyTerm.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _focusAreaOntologyTerm.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _focusAreaOntologyTerm.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _focusAreaOntologyTerm.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _focusAreaOntologyTerm.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _focusAreaOntologyTerm.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _focusAreaOntologyTerm.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _focusAreaOntologyTerm.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _focusAreaOntologyTerm.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FocusAreaOntologyTermWrapper((FocusAreaOntologyTerm) _focusAreaOntologyTerm.clone());
    }

    @Override
    public int compareTo(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        return _focusAreaOntologyTerm.compareTo(focusAreaOntologyTerm);
    }

    @Override
    public int hashCode() {
        return _focusAreaOntologyTerm.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<FocusAreaOntologyTerm> toCacheModel() {
        return _focusAreaOntologyTerm.toCacheModel();
    }

    @Override
    public FocusAreaOntologyTerm toEscapedModel() {
        return new FocusAreaOntologyTermWrapper(_focusAreaOntologyTerm.toEscapedModel());
    }

    @Override
    public FocusAreaOntologyTerm toUnescapedModel() {
        return new FocusAreaOntologyTermWrapper(_focusAreaOntologyTerm.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _focusAreaOntologyTerm.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _focusAreaOntologyTerm.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusAreaOntologyTerm.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FocusAreaOntologyTermWrapper)) {
            return false;
        }

        FocusAreaOntologyTermWrapper focusAreaOntologyTermWrapper = (FocusAreaOntologyTermWrapper) obj;

        if (Validator.equals(_focusAreaOntologyTerm,
                    focusAreaOntologyTermWrapper._focusAreaOntologyTerm)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public FocusAreaOntologyTerm getWrappedFocusAreaOntologyTerm() {
        return _focusAreaOntologyTerm;
    }

    @Override
    public FocusAreaOntologyTerm getWrappedModel() {
        return _focusAreaOntologyTerm;
    }

    @Override
    public void resetOriginalValues() {
        _focusAreaOntologyTerm.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImpactTemplateMaxFocusArea}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateMaxFocusArea
 * @generated
 */
public class ImpactTemplateMaxFocusAreaWrapper
    implements ImpactTemplateMaxFocusArea,
        ModelWrapper<ImpactTemplateMaxFocusArea> {
    private ImpactTemplateMaxFocusArea _impactTemplateMaxFocusArea;

    public ImpactTemplateMaxFocusAreaWrapper(
        ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea) {
        _impactTemplateMaxFocusArea = impactTemplateMaxFocusArea;
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactTemplateMaxFocusArea.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactTemplateMaxFocusArea.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("focusAreaListId", getFocusAreaListId());
        attributes.put("focusAreaId", getFocusAreaId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long focusAreaListId = (Long) attributes.get("focusAreaListId");

        if (focusAreaListId != null) {
            setFocusAreaListId(focusAreaListId);
        }

        Long focusAreaId = (Long) attributes.get("focusAreaId");

        if (focusAreaId != null) {
            setFocusAreaId(focusAreaId);
        }
    }

    /**
    * Returns the primary key of this impact template max focus area.
    *
    * @return the primary key of this impact template max focus area
    */
    @Override
    public com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK getPrimaryKey() {
        return _impactTemplateMaxFocusArea.getPrimaryKey();
    }

    /**
    * Sets the primary key of this impact template max focus area.
    *
    * @param primaryKey the primary key of this impact template max focus area
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK primaryKey) {
        _impactTemplateMaxFocusArea.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the focus area list ID of this impact template max focus area.
    *
    * @return the focus area list ID of this impact template max focus area
    */
    @Override
    public long getFocusAreaListId() {
        return _impactTemplateMaxFocusArea.getFocusAreaListId();
    }

    /**
    * Sets the focus area list ID of this impact template max focus area.
    *
    * @param focusAreaListId the focus area list ID of this impact template max focus area
    */
    @Override
    public void setFocusAreaListId(long focusAreaListId) {
        _impactTemplateMaxFocusArea.setFocusAreaListId(focusAreaListId);
    }

    /**
    * Returns the focus area ID of this impact template max focus area.
    *
    * @return the focus area ID of this impact template max focus area
    */
    @Override
    public long getFocusAreaId() {
        return _impactTemplateMaxFocusArea.getFocusAreaId();
    }

    /**
    * Sets the focus area ID of this impact template max focus area.
    *
    * @param focusAreaId the focus area ID of this impact template max focus area
    */
    @Override
    public void setFocusAreaId(long focusAreaId) {
        _impactTemplateMaxFocusArea.setFocusAreaId(focusAreaId);
    }

    @Override
    public boolean isNew() {
        return _impactTemplateMaxFocusArea.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _impactTemplateMaxFocusArea.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _impactTemplateMaxFocusArea.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _impactTemplateMaxFocusArea.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _impactTemplateMaxFocusArea.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _impactTemplateMaxFocusArea.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _impactTemplateMaxFocusArea.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _impactTemplateMaxFocusArea.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _impactTemplateMaxFocusArea.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _impactTemplateMaxFocusArea.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _impactTemplateMaxFocusArea.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImpactTemplateMaxFocusAreaWrapper((ImpactTemplateMaxFocusArea) _impactTemplateMaxFocusArea.clone());
    }

    @Override
    public int compareTo(ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea) {
        return _impactTemplateMaxFocusArea.compareTo(impactTemplateMaxFocusArea);
    }

    @Override
    public int hashCode() {
        return _impactTemplateMaxFocusArea.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ImpactTemplateMaxFocusArea> toCacheModel() {
        return _impactTemplateMaxFocusArea.toCacheModel();
    }

    @Override
    public ImpactTemplateMaxFocusArea toEscapedModel() {
        return new ImpactTemplateMaxFocusAreaWrapper(_impactTemplateMaxFocusArea.toEscapedModel());
    }

    @Override
    public ImpactTemplateMaxFocusArea toUnescapedModel() {
        return new ImpactTemplateMaxFocusAreaWrapper(_impactTemplateMaxFocusArea.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _impactTemplateMaxFocusArea.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _impactTemplateMaxFocusArea.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _impactTemplateMaxFocusArea.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactTemplateMaxFocusAreaWrapper)) {
            return false;
        }

        ImpactTemplateMaxFocusAreaWrapper impactTemplateMaxFocusAreaWrapper = (ImpactTemplateMaxFocusAreaWrapper) obj;

        if (Validator.equals(_impactTemplateMaxFocusArea,
                    impactTemplateMaxFocusAreaWrapper._impactTemplateMaxFocusArea)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImpactTemplateMaxFocusArea getWrappedImpactTemplateMaxFocusArea() {
        return _impactTemplateMaxFocusArea;
    }

    @Override
    public ImpactTemplateMaxFocusArea getWrappedModel() {
        return _impactTemplateMaxFocusArea;
    }

    @Override
    public void resetOriginalValues() {
        _impactTemplateMaxFocusArea.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImpactDefaultSeries}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeries
 * @generated
 */
public class ImpactDefaultSeriesWrapper implements ImpactDefaultSeries,
    ModelWrapper<ImpactDefaultSeries> {
    private ImpactDefaultSeries _impactDefaultSeries;

    public ImpactDefaultSeriesWrapper(ImpactDefaultSeries impactDefaultSeries) {
        _impactDefaultSeries = impactDefaultSeries;
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactDefaultSeries.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactDefaultSeries.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("seriesId", getSeriesId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("focusAreaId", getFocusAreaId());
        attributes.put("visible", getVisible());
        attributes.put("editable", getEditable());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long seriesId = (Long) attributes.get("seriesId");

        if (seriesId != null) {
            setSeriesId(seriesId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Long focusAreaId = (Long) attributes.get("focusAreaId");

        if (focusAreaId != null) {
            setFocusAreaId(focusAreaId);
        }

        Boolean visible = (Boolean) attributes.get("visible");

        if (visible != null) {
            setVisible(visible);
        }

        Boolean editable = (Boolean) attributes.get("editable");

        if (editable != null) {
            setEditable(editable);
        }
    }

    /**
    * Returns the primary key of this impact default series.
    *
    * @return the primary key of this impact default series
    */
    @Override
    public com.ext.portlet.service.persistence.ImpactDefaultSeriesPK getPrimaryKey() {
        return _impactDefaultSeries.getPrimaryKey();
    }

    /**
    * Sets the primary key of this impact default series.
    *
    * @param primaryKey the primary key of this impact default series
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesPK primaryKey) {
        _impactDefaultSeries.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the series ID of this impact default series.
    *
    * @return the series ID of this impact default series
    */
    @Override
    public long getSeriesId() {
        return _impactDefaultSeries.getSeriesId();
    }

    /**
    * Sets the series ID of this impact default series.
    *
    * @param seriesId the series ID of this impact default series
    */
    @Override
    public void setSeriesId(long seriesId) {
        _impactDefaultSeries.setSeriesId(seriesId);
    }

    /**
    * Returns the name of this impact default series.
    *
    * @return the name of this impact default series
    */
    @Override
    public java.lang.String getName() {
        return _impactDefaultSeries.getName();
    }

    /**
    * Sets the name of this impact default series.
    *
    * @param name the name of this impact default series
    */
    @Override
    public void setName(java.lang.String name) {
        _impactDefaultSeries.setName(name);
    }

    /**
    * Returns the description of this impact default series.
    *
    * @return the description of this impact default series
    */
    @Override
    public java.lang.String getDescription() {
        return _impactDefaultSeries.getDescription();
    }

    /**
    * Sets the description of this impact default series.
    *
    * @param description the description of this impact default series
    */
    @Override
    public void setDescription(java.lang.String description) {
        _impactDefaultSeries.setDescription(description);
    }

    /**
    * Returns the focus area ID of this impact default series.
    *
    * @return the focus area ID of this impact default series
    */
    @Override
    public long getFocusAreaId() {
        return _impactDefaultSeries.getFocusAreaId();
    }

    /**
    * Sets the focus area ID of this impact default series.
    *
    * @param focusAreaId the focus area ID of this impact default series
    */
    @Override
    public void setFocusAreaId(long focusAreaId) {
        _impactDefaultSeries.setFocusAreaId(focusAreaId);
    }

    /**
    * Returns the visible of this impact default series.
    *
    * @return the visible of this impact default series
    */
    @Override
    public boolean getVisible() {
        return _impactDefaultSeries.getVisible();
    }

    /**
    * Returns <code>true</code> if this impact default series is visible.
    *
    * @return <code>true</code> if this impact default series is visible; <code>false</code> otherwise
    */
    @Override
    public boolean isVisible() {
        return _impactDefaultSeries.isVisible();
    }

    /**
    * Sets whether this impact default series is visible.
    *
    * @param visible the visible of this impact default series
    */
    @Override
    public void setVisible(boolean visible) {
        _impactDefaultSeries.setVisible(visible);
    }

    /**
    * Returns the editable of this impact default series.
    *
    * @return the editable of this impact default series
    */
    @Override
    public boolean getEditable() {
        return _impactDefaultSeries.getEditable();
    }

    /**
    * Returns <code>true</code> if this impact default series is editable.
    *
    * @return <code>true</code> if this impact default series is editable; <code>false</code> otherwise
    */
    @Override
    public boolean isEditable() {
        return _impactDefaultSeries.isEditable();
    }

    /**
    * Sets whether this impact default series is editable.
    *
    * @param editable the editable of this impact default series
    */
    @Override
    public void setEditable(boolean editable) {
        _impactDefaultSeries.setEditable(editable);
    }

    @Override
    public boolean isNew() {
        return _impactDefaultSeries.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _impactDefaultSeries.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _impactDefaultSeries.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _impactDefaultSeries.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _impactDefaultSeries.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _impactDefaultSeries.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _impactDefaultSeries.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _impactDefaultSeries.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _impactDefaultSeries.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _impactDefaultSeries.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _impactDefaultSeries.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImpactDefaultSeriesWrapper((ImpactDefaultSeries) _impactDefaultSeries.clone());
    }

    @Override
    public int compareTo(ImpactDefaultSeries impactDefaultSeries) {
        return _impactDefaultSeries.compareTo(impactDefaultSeries);
    }

    @Override
    public int hashCode() {
        return _impactDefaultSeries.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ImpactDefaultSeries> toCacheModel() {
        return _impactDefaultSeries.toCacheModel();
    }

    @Override
    public ImpactDefaultSeries toEscapedModel() {
        return new ImpactDefaultSeriesWrapper(_impactDefaultSeries.toEscapedModel());
    }

    @Override
    public ImpactDefaultSeries toUnescapedModel() {
        return new ImpactDefaultSeriesWrapper(_impactDefaultSeries.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _impactDefaultSeries.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _impactDefaultSeries.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _impactDefaultSeries.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactDefaultSeriesWrapper)) {
            return false;
        }

        ImpactDefaultSeriesWrapper impactDefaultSeriesWrapper = (ImpactDefaultSeriesWrapper) obj;

        if (Validator.equals(_impactDefaultSeries,
                    impactDefaultSeriesWrapper._impactDefaultSeries)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImpactDefaultSeries getWrappedImpactDefaultSeries() {
        return _impactDefaultSeries;
    }

    @Override
    public ImpactDefaultSeries getWrappedModel() {
        return _impactDefaultSeries;
    }

    @Override
    public void resetOriginalValues() {
        _impactDefaultSeries.resetOriginalValues();
    }
}

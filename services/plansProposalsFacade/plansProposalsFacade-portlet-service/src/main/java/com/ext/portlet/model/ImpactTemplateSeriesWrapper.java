package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImpactTemplateSeries}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateSeries
 * @generated
 */
public class ImpactTemplateSeriesWrapper implements ImpactTemplateSeries,
    ModelWrapper<ImpactTemplateSeries> {
    private ImpactTemplateSeries _impactTemplateSeries;

    public ImpactTemplateSeriesWrapper(
        ImpactTemplateSeries impactTemplateSeries) {
        _impactTemplateSeries = impactTemplateSeries;
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactTemplateSeries.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactTemplateSeries.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("seriesId", getSeriesId());
        attributes.put("iterationId", getIterationId());
        attributes.put("name", getName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long seriesId = (Long) attributes.get("seriesId");

        if (seriesId != null) {
            setSeriesId(seriesId);
        }

        Long iterationId = (Long) attributes.get("iterationId");

        if (iterationId != null) {
            setIterationId(iterationId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }
    }

    /**
    * Returns the primary key of this impact template series.
    *
    * @return the primary key of this impact template series
    */
    @Override
    public long getPrimaryKey() {
        return _impactTemplateSeries.getPrimaryKey();
    }

    /**
    * Sets the primary key of this impact template series.
    *
    * @param primaryKey the primary key of this impact template series
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _impactTemplateSeries.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the series ID of this impact template series.
    *
    * @return the series ID of this impact template series
    */
    @Override
    public long getSeriesId() {
        return _impactTemplateSeries.getSeriesId();
    }

    /**
    * Sets the series ID of this impact template series.
    *
    * @param seriesId the series ID of this impact template series
    */
    @Override
    public void setSeriesId(long seriesId) {
        _impactTemplateSeries.setSeriesId(seriesId);
    }

    /**
    * Returns the iteration ID of this impact template series.
    *
    * @return the iteration ID of this impact template series
    */
    @Override
    public long getIterationId() {
        return _impactTemplateSeries.getIterationId();
    }

    /**
    * Sets the iteration ID of this impact template series.
    *
    * @param iterationId the iteration ID of this impact template series
    */
    @Override
    public void setIterationId(long iterationId) {
        _impactTemplateSeries.setIterationId(iterationId);
    }

    /**
    * Returns the name of this impact template series.
    *
    * @return the name of this impact template series
    */
    @Override
    public java.lang.String getName() {
        return _impactTemplateSeries.getName();
    }

    /**
    * Sets the name of this impact template series.
    *
    * @param name the name of this impact template series
    */
    @Override
    public void setName(java.lang.String name) {
        _impactTemplateSeries.setName(name);
    }

    @Override
    public boolean isNew() {
        return _impactTemplateSeries.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _impactTemplateSeries.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _impactTemplateSeries.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _impactTemplateSeries.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _impactTemplateSeries.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _impactTemplateSeries.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _impactTemplateSeries.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _impactTemplateSeries.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _impactTemplateSeries.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _impactTemplateSeries.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _impactTemplateSeries.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImpactTemplateSeriesWrapper((ImpactTemplateSeries) _impactTemplateSeries.clone());
    }

    @Override
    public int compareTo(ImpactTemplateSeries impactTemplateSeries) {
        return _impactTemplateSeries.compareTo(impactTemplateSeries);
    }

    @Override
    public int hashCode() {
        return _impactTemplateSeries.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ImpactTemplateSeries> toCacheModel() {
        return _impactTemplateSeries.toCacheModel();
    }

    @Override
    public ImpactTemplateSeries toEscapedModel() {
        return new ImpactTemplateSeriesWrapper(_impactTemplateSeries.toEscapedModel());
    }

    @Override
    public ImpactTemplateSeries toUnescapedModel() {
        return new ImpactTemplateSeriesWrapper(_impactTemplateSeries.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _impactTemplateSeries.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _impactTemplateSeries.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _impactTemplateSeries.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactTemplateSeriesWrapper)) {
            return false;
        }

        ImpactTemplateSeriesWrapper impactTemplateSeriesWrapper = (ImpactTemplateSeriesWrapper) obj;

        if (Validator.equals(_impactTemplateSeries,
                    impactTemplateSeriesWrapper._impactTemplateSeries)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImpactTemplateSeries getWrappedImpactTemplateSeries() {
        return _impactTemplateSeries;
    }

    @Override
    public ImpactTemplateSeries getWrappedModel() {
        return _impactTemplateSeries;
    }

    @Override
    public void resetOriginalValues() {
        _impactTemplateSeries.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImpactDefaultSeriesData}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesData
 * @generated
 */
public class ImpactDefaultSeriesDataWrapper implements ImpactDefaultSeriesData,
    ModelWrapper<ImpactDefaultSeriesData> {
    private ImpactDefaultSeriesData _impactDefaultSeriesData;

    public ImpactDefaultSeriesDataWrapper(
        ImpactDefaultSeriesData impactDefaultSeriesData) {
        _impactDefaultSeriesData = impactDefaultSeriesData;
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactDefaultSeriesData.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactDefaultSeriesData.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("seriesId", getSeriesId());
        attributes.put("year", getYear());
        attributes.put("value", getValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long seriesId = (Long) attributes.get("seriesId");

        if (seriesId != null) {
            setSeriesId(seriesId);
        }

        Integer year = (Integer) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        Double value = (Double) attributes.get("value");

        if (value != null) {
            setValue(value);
        }
    }

    /**
    * Returns the primary key of this impact default series data.
    *
    * @return the primary key of this impact default series data
    */
    @Override
    public com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK getPrimaryKey() {
        return _impactDefaultSeriesData.getPrimaryKey();
    }

    /**
    * Sets the primary key of this impact default series data.
    *
    * @param primaryKey the primary key of this impact default series data
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK primaryKey) {
        _impactDefaultSeriesData.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the series ID of this impact default series data.
    *
    * @return the series ID of this impact default series data
    */
    @Override
    public long getSeriesId() {
        return _impactDefaultSeriesData.getSeriesId();
    }

    /**
    * Sets the series ID of this impact default series data.
    *
    * @param seriesId the series ID of this impact default series data
    */
    @Override
    public void setSeriesId(long seriesId) {
        _impactDefaultSeriesData.setSeriesId(seriesId);
    }

    /**
    * Returns the year of this impact default series data.
    *
    * @return the year of this impact default series data
    */
    @Override
    public int getYear() {
        return _impactDefaultSeriesData.getYear();
    }

    /**
    * Sets the year of this impact default series data.
    *
    * @param year the year of this impact default series data
    */
    @Override
    public void setYear(int year) {
        _impactDefaultSeriesData.setYear(year);
    }

    /**
    * Returns the value of this impact default series data.
    *
    * @return the value of this impact default series data
    */
    @Override
    public double getValue() {
        return _impactDefaultSeriesData.getValue();
    }

    /**
    * Sets the value of this impact default series data.
    *
    * @param value the value of this impact default series data
    */
    @Override
    public void setValue(double value) {
        _impactDefaultSeriesData.setValue(value);
    }

    @Override
    public boolean isNew() {
        return _impactDefaultSeriesData.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _impactDefaultSeriesData.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _impactDefaultSeriesData.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _impactDefaultSeriesData.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _impactDefaultSeriesData.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _impactDefaultSeriesData.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _impactDefaultSeriesData.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _impactDefaultSeriesData.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _impactDefaultSeriesData.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _impactDefaultSeriesData.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _impactDefaultSeriesData.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImpactDefaultSeriesDataWrapper((ImpactDefaultSeriesData) _impactDefaultSeriesData.clone());
    }

    @Override
    public int compareTo(ImpactDefaultSeriesData impactDefaultSeriesData) {
        return _impactDefaultSeriesData.compareTo(impactDefaultSeriesData);
    }

    @Override
    public int hashCode() {
        return _impactDefaultSeriesData.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ImpactDefaultSeriesData> toCacheModel() {
        return _impactDefaultSeriesData.toCacheModel();
    }

    @Override
    public ImpactDefaultSeriesData toEscapedModel() {
        return new ImpactDefaultSeriesDataWrapper(_impactDefaultSeriesData.toEscapedModel());
    }

    @Override
    public ImpactDefaultSeriesData toUnescapedModel() {
        return new ImpactDefaultSeriesDataWrapper(_impactDefaultSeriesData.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _impactDefaultSeriesData.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _impactDefaultSeriesData.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _impactDefaultSeriesData.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactDefaultSeriesDataWrapper)) {
            return false;
        }

        ImpactDefaultSeriesDataWrapper impactDefaultSeriesDataWrapper = (ImpactDefaultSeriesDataWrapper) obj;

        if (Validator.equals(_impactDefaultSeriesData,
                    impactDefaultSeriesDataWrapper._impactDefaultSeriesData)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImpactDefaultSeriesData getWrappedImpactDefaultSeriesData() {
        return _impactDefaultSeriesData;
    }

    @Override
    public ImpactDefaultSeriesData getWrappedModel() {
        return _impactDefaultSeriesData;
    }

    @Override
    public void resetOriginalValues() {
        _impactDefaultSeriesData.resetOriginalValues();
    }
}

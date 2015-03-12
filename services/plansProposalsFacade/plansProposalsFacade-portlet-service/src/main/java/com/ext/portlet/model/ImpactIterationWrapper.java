package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImpactIteration}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactIteration
 * @generated
 */
public class ImpactIterationWrapper implements ImpactIteration,
    ModelWrapper<ImpactIteration> {
    private ImpactIteration _impactIteration;

    public ImpactIterationWrapper(ImpactIteration impactIteration) {
        _impactIteration = impactIteration;
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactIteration.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactIteration.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("iterationId", getIterationId());
        attributes.put("year", getYear());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long iterationId = (Long) attributes.get("iterationId");

        if (iterationId != null) {
            setIterationId(iterationId);
        }

        Integer year = (Integer) attributes.get("year");

        if (year != null) {
            setYear(year);
        }
    }

    /**
    * Returns the primary key of this impact iteration.
    *
    * @return the primary key of this impact iteration
    */
    @Override
    public com.ext.portlet.service.persistence.ImpactIterationPK getPrimaryKey() {
        return _impactIteration.getPrimaryKey();
    }

    /**
    * Sets the primary key of this impact iteration.
    *
    * @param primaryKey the primary key of this impact iteration
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ImpactIterationPK primaryKey) {
        _impactIteration.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the iteration ID of this impact iteration.
    *
    * @return the iteration ID of this impact iteration
    */
    @Override
    public long getIterationId() {
        return _impactIteration.getIterationId();
    }

    /**
    * Sets the iteration ID of this impact iteration.
    *
    * @param iterationId the iteration ID of this impact iteration
    */
    @Override
    public void setIterationId(long iterationId) {
        _impactIteration.setIterationId(iterationId);
    }

    /**
    * Returns the year of this impact iteration.
    *
    * @return the year of this impact iteration
    */
    @Override
    public int getYear() {
        return _impactIteration.getYear();
    }

    /**
    * Sets the year of this impact iteration.
    *
    * @param year the year of this impact iteration
    */
    @Override
    public void setYear(int year) {
        _impactIteration.setYear(year);
    }

    @Override
    public boolean isNew() {
        return _impactIteration.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _impactIteration.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _impactIteration.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _impactIteration.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _impactIteration.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _impactIteration.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _impactIteration.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _impactIteration.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _impactIteration.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _impactIteration.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _impactIteration.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImpactIterationWrapper((ImpactIteration) _impactIteration.clone());
    }

    @Override
    public int compareTo(ImpactIteration impactIteration) {
        return _impactIteration.compareTo(impactIteration);
    }

    @Override
    public int hashCode() {
        return _impactIteration.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ImpactIteration> toCacheModel() {
        return _impactIteration.toCacheModel();
    }

    @Override
    public ImpactIteration toEscapedModel() {
        return new ImpactIterationWrapper(_impactIteration.toEscapedModel());
    }

    @Override
    public ImpactIteration toUnescapedModel() {
        return new ImpactIterationWrapper(_impactIteration.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _impactIteration.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _impactIteration.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _impactIteration.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactIterationWrapper)) {
            return false;
        }

        ImpactIterationWrapper impactIterationWrapper = (ImpactIterationWrapper) obj;

        if (Validator.equals(_impactIteration,
                    impactIterationWrapper._impactIteration)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImpactIteration getWrappedImpactIteration() {
        return _impactIteration;
    }

    @Override
    public ImpactIteration getWrappedModel() {
        return _impactIteration;
    }

    @Override
    public void resetOriginalValues() {
        _impactIteration.resetOriginalValues();
    }
}

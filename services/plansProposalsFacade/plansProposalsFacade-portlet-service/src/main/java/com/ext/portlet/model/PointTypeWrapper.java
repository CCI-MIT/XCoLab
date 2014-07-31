package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PointType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointType
 * @generated
 */
public class PointTypeWrapper implements PointType, ModelWrapper<PointType> {
    private PointType _pointType;

    public PointTypeWrapper(PointType pointType) {
        _pointType = pointType;
    }

    @Override
    public Class<?> getModelClass() {
        return PointType.class;
    }

    @Override
    public String getModelClassName() {
        return PointType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("parentPointTypeId", getParentPointTypeId());
        attributes.put("percentageOfParent", getPercentageOfParent());
        attributes.put("distributionStrategy", getDistributionStrategy());
        attributes.put("receiverLimitationStrategy",
            getReceiverLimitationStrategy());
        attributes.put("name", getName());
        attributes.put("sort", getSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long parentPointTypeId = (Long) attributes.get("parentPointTypeId");

        if (parentPointTypeId != null) {
            setParentPointTypeId(parentPointTypeId);
        }

        Double percentageOfParent = (Double) attributes.get(
                "percentageOfParent");

        if (percentageOfParent != null) {
            setPercentageOfParent(percentageOfParent);
        }

        String distributionStrategy = (String) attributes.get(
                "distributionStrategy");

        if (distributionStrategy != null) {
            setDistributionStrategy(distributionStrategy);
        }

        String receiverLimitationStrategy = (String) attributes.get(
                "receiverLimitationStrategy");

        if (receiverLimitationStrategy != null) {
            setReceiverLimitationStrategy(receiverLimitationStrategy);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long sort = (Long) attributes.get("sort");

        if (sort != null) {
            setSort(sort);
        }
    }

    /**
    * Returns the primary key of this point type.
    *
    * @return the primary key of this point type
    */
    @Override
    public long getPrimaryKey() {
        return _pointType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this point type.
    *
    * @param primaryKey the primary key of this point type
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _pointType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this point type.
    *
    * @return the ID of this point type
    */
    @Override
    public long getId() {
        return _pointType.getId();
    }

    /**
    * Sets the ID of this point type.
    *
    * @param id the ID of this point type
    */
    @Override
    public void setId(long id) {
        _pointType.setId(id);
    }

    /**
    * Returns the parent point type ID of this point type.
    *
    * @return the parent point type ID of this point type
    */
    @Override
    public long getParentPointTypeId() {
        return _pointType.getParentPointTypeId();
    }

    /**
    * Sets the parent point type ID of this point type.
    *
    * @param parentPointTypeId the parent point type ID of this point type
    */
    @Override
    public void setParentPointTypeId(long parentPointTypeId) {
        _pointType.setParentPointTypeId(parentPointTypeId);
    }

    /**
    * Returns the percentage of parent of this point type.
    *
    * @return the percentage of parent of this point type
    */
    @Override
    public double getPercentageOfParent() {
        return _pointType.getPercentageOfParent();
    }

    /**
    * Sets the percentage of parent of this point type.
    *
    * @param percentageOfParent the percentage of parent of this point type
    */
    @Override
    public void setPercentageOfParent(double percentageOfParent) {
        _pointType.setPercentageOfParent(percentageOfParent);
    }

    /**
    * Returns the distribution strategy of this point type.
    *
    * @return the distribution strategy of this point type
    */
    @Override
    public java.lang.String getDistributionStrategy() {
        return _pointType.getDistributionStrategy();
    }

    /**
    * Sets the distribution strategy of this point type.
    *
    * @param distributionStrategy the distribution strategy of this point type
    */
    @Override
    public void setDistributionStrategy(java.lang.String distributionStrategy) {
        _pointType.setDistributionStrategy(distributionStrategy);
    }

    /**
    * Returns the receiver limitation strategy of this point type.
    *
    * @return the receiver limitation strategy of this point type
    */
    @Override
    public java.lang.String getReceiverLimitationStrategy() {
        return _pointType.getReceiverLimitationStrategy();
    }

    /**
    * Sets the receiver limitation strategy of this point type.
    *
    * @param receiverLimitationStrategy the receiver limitation strategy of this point type
    */
    @Override
    public void setReceiverLimitationStrategy(
        java.lang.String receiverLimitationStrategy) {
        _pointType.setReceiverLimitationStrategy(receiverLimitationStrategy);
    }

    /**
    * Returns the name of this point type.
    *
    * @return the name of this point type
    */
    @Override
    public java.lang.String getName() {
        return _pointType.getName();
    }

    /**
    * Sets the name of this point type.
    *
    * @param name the name of this point type
    */
    @Override
    public void setName(java.lang.String name) {
        _pointType.setName(name);
    }

    /**
    * Returns the sort of this point type.
    *
    * @return the sort of this point type
    */
    @Override
    public long getSort() {
        return _pointType.getSort();
    }

    /**
    * Sets the sort of this point type.
    *
    * @param sort the sort of this point type
    */
    @Override
    public void setSort(long sort) {
        _pointType.setSort(sort);
    }

    @Override
    public boolean isNew() {
        return _pointType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _pointType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _pointType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _pointType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _pointType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _pointType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _pointType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _pointType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _pointType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _pointType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _pointType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PointTypeWrapper((PointType) _pointType.clone());
    }

    @Override
    public int compareTo(PointType pointType) {
        return _pointType.compareTo(pointType);
    }

    @Override
    public int hashCode() {
        return _pointType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PointType> toCacheModel() {
        return _pointType.toCacheModel();
    }

    @Override
    public PointType toEscapedModel() {
        return new PointTypeWrapper(_pointType.toEscapedModel());
    }

    @Override
    public PointType toUnescapedModel() {
        return new PointTypeWrapper(_pointType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _pointType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _pointType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _pointType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PointTypeWrapper)) {
            return false;
        }

        PointTypeWrapper pointTypeWrapper = (PointTypeWrapper) obj;

        if (Validator.equals(_pointType, pointTypeWrapper._pointType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PointType getWrappedPointType() {
        return _pointType;
    }

    @Override
    public PointType getWrappedModel() {
        return _pointType;
    }

    @Override
    public void resetOriginalValues() {
        _pointType.resetOriginalValues();
    }
}

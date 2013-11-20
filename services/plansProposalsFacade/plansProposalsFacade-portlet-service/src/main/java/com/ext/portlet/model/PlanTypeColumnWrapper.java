package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeColumn}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeColumn
 * @generated
 */
public class PlanTypeColumnWrapper implements PlanTypeColumn,
    ModelWrapper<PlanTypeColumn> {
    private PlanTypeColumn _planTypeColumn;

    public PlanTypeColumnWrapper(PlanTypeColumn planTypeColumn) {
        _planTypeColumn = planTypeColumn;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTypeColumn.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTypeColumn.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planTypeColumnId", getPlanTypeColumnId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("weight", getWeight());
        attributes.put("columnName", getColumnName());
        attributes.put("visibleByDefault", getVisibleByDefault());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planTypeColumnId = (Long) attributes.get("planTypeColumnId");

        if (planTypeColumnId != null) {
            setPlanTypeColumnId(planTypeColumnId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        Integer weight = (Integer) attributes.get("weight");

        if (weight != null) {
            setWeight(weight);
        }

        String columnName = (String) attributes.get("columnName");

        if (columnName != null) {
            setColumnName(columnName);
        }

        Boolean visibleByDefault = (Boolean) attributes.get("visibleByDefault");

        if (visibleByDefault != null) {
            setVisibleByDefault(visibleByDefault);
        }
    }

    /**
    * Returns the primary key of this plan type column.
    *
    * @return the primary key of this plan type column
    */
    @Override
    public long getPrimaryKey() {
        return _planTypeColumn.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan type column.
    *
    * @param primaryKey the primary key of this plan type column
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planTypeColumn.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan type column ID of this plan type column.
    *
    * @return the plan type column ID of this plan type column
    */
    @Override
    public long getPlanTypeColumnId() {
        return _planTypeColumn.getPlanTypeColumnId();
    }

    /**
    * Sets the plan type column ID of this plan type column.
    *
    * @param planTypeColumnId the plan type column ID of this plan type column
    */
    @Override
    public void setPlanTypeColumnId(long planTypeColumnId) {
        _planTypeColumn.setPlanTypeColumnId(planTypeColumnId);
    }

    /**
    * Returns the plan type ID of this plan type column.
    *
    * @return the plan type ID of this plan type column
    */
    @Override
    public long getPlanTypeId() {
        return _planTypeColumn.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan type column.
    *
    * @param planTypeId the plan type ID of this plan type column
    */
    @Override
    public void setPlanTypeId(long planTypeId) {
        _planTypeColumn.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the weight of this plan type column.
    *
    * @return the weight of this plan type column
    */
    @Override
    public int getWeight() {
        return _planTypeColumn.getWeight();
    }

    /**
    * Sets the weight of this plan type column.
    *
    * @param weight the weight of this plan type column
    */
    @Override
    public void setWeight(int weight) {
        _planTypeColumn.setWeight(weight);
    }

    /**
    * Returns the column name of this plan type column.
    *
    * @return the column name of this plan type column
    */
    @Override
    public java.lang.String getColumnName() {
        return _planTypeColumn.getColumnName();
    }

    /**
    * Sets the column name of this plan type column.
    *
    * @param columnName the column name of this plan type column
    */
    @Override
    public void setColumnName(java.lang.String columnName) {
        _planTypeColumn.setColumnName(columnName);
    }

    /**
    * Returns the visible by default of this plan type column.
    *
    * @return the visible by default of this plan type column
    */
    @Override
    public boolean getVisibleByDefault() {
        return _planTypeColumn.getVisibleByDefault();
    }

    /**
    * Returns <code>true</code> if this plan type column is visible by default.
    *
    * @return <code>true</code> if this plan type column is visible by default; <code>false</code> otherwise
    */
    @Override
    public boolean isVisibleByDefault() {
        return _planTypeColumn.isVisibleByDefault();
    }

    /**
    * Sets whether this plan type column is visible by default.
    *
    * @param visibleByDefault the visible by default of this plan type column
    */
    @Override
    public void setVisibleByDefault(boolean visibleByDefault) {
        _planTypeColumn.setVisibleByDefault(visibleByDefault);
    }

    @Override
    public boolean isNew() {
        return _planTypeColumn.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planTypeColumn.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planTypeColumn.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planTypeColumn.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planTypeColumn.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planTypeColumn.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTypeColumn.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTypeColumn.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planTypeColumn.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planTypeColumn.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTypeColumn.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTypeColumnWrapper((PlanTypeColumn) _planTypeColumn.clone());
    }

    @Override
    public int compareTo(PlanTypeColumn planTypeColumn) {
        return _planTypeColumn.compareTo(planTypeColumn);
    }

    @Override
    public int hashCode() {
        return _planTypeColumn.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanTypeColumn> toCacheModel() {
        return _planTypeColumn.toCacheModel();
    }

    @Override
    public PlanTypeColumn toEscapedModel() {
        return new PlanTypeColumnWrapper(_planTypeColumn.toEscapedModel());
    }

    @Override
    public PlanTypeColumn toUnescapedModel() {
        return new PlanTypeColumnWrapper(_planTypeColumn.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTypeColumn.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planTypeColumn.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTypeColumn.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanTypeColumnWrapper)) {
            return false;
        }

        PlanTypeColumnWrapper planTypeColumnWrapper = (PlanTypeColumnWrapper) obj;

        if (Validator.equals(_planTypeColumn,
                    planTypeColumnWrapper._planTypeColumn)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanTypeColumn getWrappedPlanTypeColumn() {
        return _planTypeColumn;
    }

    @Override
    public PlanTypeColumn getWrappedModel() {
        return _planTypeColumn;
    }

    @Override
    public void resetOriginalValues() {
        _planTypeColumn.resetOriginalValues();
    }
}

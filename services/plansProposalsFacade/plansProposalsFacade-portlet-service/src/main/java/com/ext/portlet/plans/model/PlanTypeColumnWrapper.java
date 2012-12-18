package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeColumn}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTypeColumn
 * @generated
 */
public class PlanTypeColumnWrapper implements PlanTypeColumn,
    ModelWrapper<PlanTypeColumn> {
    private PlanTypeColumn _planTypeColumn;

    public PlanTypeColumnWrapper(PlanTypeColumn planTypeColumn) {
        _planTypeColumn = planTypeColumn;
    }

    public Class<?> getModelClass() {
        return PlanTypeColumn.class;
    }

    public String getModelClassName() {
        return PlanTypeColumn.class.getName();
    }

    /**
    * Returns the primary key of this plan type column.
    *
    * @return the primary key of this plan type column
    */
    public long getPrimaryKey() {
        return _planTypeColumn.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan type column.
    *
    * @param primaryKey the primary key of this plan type column
    */
    public void setPrimaryKey(long primaryKey) {
        _planTypeColumn.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan type column ID of this plan type column.
    *
    * @return the plan type column ID of this plan type column
    */
    public long getPlanTypeColumnId() {
        return _planTypeColumn.getPlanTypeColumnId();
    }

    /**
    * Sets the plan type column ID of this plan type column.
    *
    * @param planTypeColumnId the plan type column ID of this plan type column
    */
    public void setPlanTypeColumnId(long planTypeColumnId) {
        _planTypeColumn.setPlanTypeColumnId(planTypeColumnId);
    }

    /**
    * Returns the plan type ID of this plan type column.
    *
    * @return the plan type ID of this plan type column
    */
    public long getPlanTypeId() {
        return _planTypeColumn.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan type column.
    *
    * @param planTypeId the plan type ID of this plan type column
    */
    public void setPlanTypeId(long planTypeId) {
        _planTypeColumn.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the weight of this plan type column.
    *
    * @return the weight of this plan type column
    */
    public int getWeight() {
        return _planTypeColumn.getWeight();
    }

    /**
    * Sets the weight of this plan type column.
    *
    * @param weight the weight of this plan type column
    */
    public void setWeight(int weight) {
        _planTypeColumn.setWeight(weight);
    }

    /**
    * Returns the column name of this plan type column.
    *
    * @return the column name of this plan type column
    */
    public java.lang.String getColumnName() {
        return _planTypeColumn.getColumnName();
    }

    /**
    * Sets the column name of this plan type column.
    *
    * @param columnName the column name of this plan type column
    */
    public void setColumnName(java.lang.String columnName) {
        _planTypeColumn.setColumnName(columnName);
    }

    /**
    * Returns the visible by default of this plan type column.
    *
    * @return the visible by default of this plan type column
    */
    public boolean getVisibleByDefault() {
        return _planTypeColumn.getVisibleByDefault();
    }

    /**
    * Returns <code>true</code> if this plan type column is visible by default.
    *
    * @return <code>true</code> if this plan type column is visible by default; <code>false</code> otherwise
    */
    public boolean isVisibleByDefault() {
        return _planTypeColumn.isVisibleByDefault();
    }

    /**
    * Sets whether this plan type column is visible by default.
    *
    * @param visibleByDefault the visible by default of this plan type column
    */
    public void setVisibleByDefault(boolean visibleByDefault) {
        _planTypeColumn.setVisibleByDefault(visibleByDefault);
    }

    public boolean isNew() {
        return _planTypeColumn.isNew();
    }

    public void setNew(boolean n) {
        _planTypeColumn.setNew(n);
    }

    public boolean isCachedModel() {
        return _planTypeColumn.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planTypeColumn.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planTypeColumn.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planTypeColumn.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTypeColumn.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTypeColumn.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTypeColumn.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTypeColumnWrapper((PlanTypeColumn) _planTypeColumn.clone());
    }

    public int compareTo(PlanTypeColumn planTypeColumn) {
        return _planTypeColumn.compareTo(planTypeColumn);
    }

    @Override
    public int hashCode() {
        return _planTypeColumn.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanTypeColumn> toCacheModel() {
        return _planTypeColumn.toCacheModel();
    }

    public PlanTypeColumn toEscapedModel() {
        return new PlanTypeColumnWrapper(_planTypeColumn.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTypeColumn.toString();
    }

    public java.lang.String toXmlString() {
        return _planTypeColumn.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTypeColumn.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanTypeColumn getWrappedPlanTypeColumn() {
        return _planTypeColumn;
    }

    public PlanTypeColumn getWrappedModel() {
        return _planTypeColumn;
    }

    public void resetOriginalValues() {
        _planTypeColumn.resetOriginalValues();
    }
}

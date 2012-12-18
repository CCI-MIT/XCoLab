package com.ext.portlet.contests.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseColumn}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseColumn
 * @generated
 */
public class ContestPhaseColumnWrapper implements ContestPhaseColumn,
    ModelWrapper<ContestPhaseColumn> {
    private ContestPhaseColumn _contestPhaseColumn;

    public ContestPhaseColumnWrapper(ContestPhaseColumn contestPhaseColumn) {
        _contestPhaseColumn = contestPhaseColumn;
    }

    public Class<?> getModelClass() {
        return ContestPhaseColumn.class;
    }

    public String getModelClassName() {
        return ContestPhaseColumn.class.getName();
    }

    /**
    * Returns the primary key of this contest phase column.
    *
    * @return the primary key of this contest phase column
    */
    public long getPrimaryKey() {
        return _contestPhaseColumn.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest phase column.
    *
    * @param primaryKey the primary key of this contest phase column
    */
    public void setPrimaryKey(long primaryKey) {
        _contestPhaseColumn.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest phase column.
    *
    * @return the ID of this contest phase column
    */
    public long getId() {
        return _contestPhaseColumn.getId();
    }

    /**
    * Sets the ID of this contest phase column.
    *
    * @param id the ID of this contest phase column
    */
    public void setId(long id) {
        _contestPhaseColumn.setId(id);
    }

    /**
    * Returns the contest phase p k of this contest phase column.
    *
    * @return the contest phase p k of this contest phase column
    */
    public long getContestPhasePK() {
        return _contestPhaseColumn.getContestPhasePK();
    }

    /**
    * Sets the contest phase p k of this contest phase column.
    *
    * @param ContestPhasePK the contest phase p k of this contest phase column
    */
    public void setContestPhasePK(long ContestPhasePK) {
        _contestPhaseColumn.setContestPhasePK(ContestPhasePK);
    }

    /**
    * Returns the column name of this contest phase column.
    *
    * @return the column name of this contest phase column
    */
    public java.lang.String getColumnName() {
        return _contestPhaseColumn.getColumnName();
    }

    /**
    * Sets the column name of this contest phase column.
    *
    * @param columnName the column name of this contest phase column
    */
    public void setColumnName(java.lang.String columnName) {
        _contestPhaseColumn.setColumnName(columnName);
    }

    /**
    * Returns the column weight of this contest phase column.
    *
    * @return the column weight of this contest phase column
    */
    public int getColumnWeight() {
        return _contestPhaseColumn.getColumnWeight();
    }

    /**
    * Sets the column weight of this contest phase column.
    *
    * @param columnWeight the column weight of this contest phase column
    */
    public void setColumnWeight(int columnWeight) {
        _contestPhaseColumn.setColumnWeight(columnWeight);
    }

    /**
    * Returns the default sort of this contest phase column.
    *
    * @return the default sort of this contest phase column
    */
    public boolean getDefaultSort() {
        return _contestPhaseColumn.getDefaultSort();
    }

    /**
    * Returns <code>true</code> if this contest phase column is default sort.
    *
    * @return <code>true</code> if this contest phase column is default sort; <code>false</code> otherwise
    */
    public boolean isDefaultSort() {
        return _contestPhaseColumn.isDefaultSort();
    }

    /**
    * Sets whether this contest phase column is default sort.
    *
    * @param defaultSort the default sort of this contest phase column
    */
    public void setDefaultSort(boolean defaultSort) {
        _contestPhaseColumn.setDefaultSort(defaultSort);
    }

    public boolean isNew() {
        return _contestPhaseColumn.isNew();
    }

    public void setNew(boolean n) {
        _contestPhaseColumn.setNew(n);
    }

    public boolean isCachedModel() {
        return _contestPhaseColumn.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _contestPhaseColumn.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _contestPhaseColumn.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _contestPhaseColumn.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestPhaseColumn.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestPhaseColumn.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestPhaseColumn.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestPhaseColumnWrapper((ContestPhaseColumn) _contestPhaseColumn.clone());
    }

    public int compareTo(ContestPhaseColumn contestPhaseColumn) {
        return _contestPhaseColumn.compareTo(contestPhaseColumn);
    }

    @Override
    public int hashCode() {
        return _contestPhaseColumn.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ContestPhaseColumn> toCacheModel() {
        return _contestPhaseColumn.toCacheModel();
    }

    public ContestPhaseColumn toEscapedModel() {
        return new ContestPhaseColumnWrapper(_contestPhaseColumn.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestPhaseColumn.toString();
    }

    public java.lang.String toXmlString() {
        return _contestPhaseColumn.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseColumn.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ContestPhaseColumn getWrappedContestPhaseColumn() {
        return _contestPhaseColumn;
    }

    public ContestPhaseColumn getWrappedModel() {
        return _contestPhaseColumn;
    }

    public void resetOriginalValues() {
        _contestPhaseColumn.resetOriginalValues();
    }
}

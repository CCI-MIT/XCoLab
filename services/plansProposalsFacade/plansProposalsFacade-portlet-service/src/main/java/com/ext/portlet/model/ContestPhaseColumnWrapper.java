package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseColumn}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseColumn
 * @generated
 */
public class ContestPhaseColumnWrapper implements ContestPhaseColumn,
    ModelWrapper<ContestPhaseColumn> {
    private ContestPhaseColumn _contestPhaseColumn;

    public ContestPhaseColumnWrapper(ContestPhaseColumn contestPhaseColumn) {
        _contestPhaseColumn = contestPhaseColumn;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestPhaseColumn.class;
    }

    @Override
    public String getModelClassName() {
        return ContestPhaseColumn.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("ContestPhasePK", getContestPhasePK());
        attributes.put("columnName", getColumnName());
        attributes.put("columnWeight", getColumnWeight());
        attributes.put("defaultSort", getDefaultSort());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long ContestPhasePK = (Long) attributes.get("ContestPhasePK");

        if (ContestPhasePK != null) {
            setContestPhasePK(ContestPhasePK);
        }

        String columnName = (String) attributes.get("columnName");

        if (columnName != null) {
            setColumnName(columnName);
        }

        Integer columnWeight = (Integer) attributes.get("columnWeight");

        if (columnWeight != null) {
            setColumnWeight(columnWeight);
        }

        Boolean defaultSort = (Boolean) attributes.get("defaultSort");

        if (defaultSort != null) {
            setDefaultSort(defaultSort);
        }
    }

    /**
    * Returns the primary key of this contest phase column.
    *
    * @return the primary key of this contest phase column
    */
    @Override
    public long getPrimaryKey() {
        return _contestPhaseColumn.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest phase column.
    *
    * @param primaryKey the primary key of this contest phase column
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestPhaseColumn.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest phase column.
    *
    * @return the ID of this contest phase column
    */
    @Override
    public long getId() {
        return _contestPhaseColumn.getId();
    }

    /**
    * Sets the ID of this contest phase column.
    *
    * @param id the ID of this contest phase column
    */
    @Override
    public void setId(long id) {
        _contestPhaseColumn.setId(id);
    }

    /**
    * Returns the contest phase p k of this contest phase column.
    *
    * @return the contest phase p k of this contest phase column
    */
    @Override
    public long getContestPhasePK() {
        return _contestPhaseColumn.getContestPhasePK();
    }

    /**
    * Sets the contest phase p k of this contest phase column.
    *
    * @param ContestPhasePK the contest phase p k of this contest phase column
    */
    @Override
    public void setContestPhasePK(long ContestPhasePK) {
        _contestPhaseColumn.setContestPhasePK(ContestPhasePK);
    }

    /**
    * Returns the column name of this contest phase column.
    *
    * @return the column name of this contest phase column
    */
    @Override
    public java.lang.String getColumnName() {
        return _contestPhaseColumn.getColumnName();
    }

    /**
    * Sets the column name of this contest phase column.
    *
    * @param columnName the column name of this contest phase column
    */
    @Override
    public void setColumnName(java.lang.String columnName) {
        _contestPhaseColumn.setColumnName(columnName);
    }

    /**
    * Returns the column weight of this contest phase column.
    *
    * @return the column weight of this contest phase column
    */
    @Override
    public int getColumnWeight() {
        return _contestPhaseColumn.getColumnWeight();
    }

    /**
    * Sets the column weight of this contest phase column.
    *
    * @param columnWeight the column weight of this contest phase column
    */
    @Override
    public void setColumnWeight(int columnWeight) {
        _contestPhaseColumn.setColumnWeight(columnWeight);
    }

    /**
    * Returns the default sort of this contest phase column.
    *
    * @return the default sort of this contest phase column
    */
    @Override
    public boolean getDefaultSort() {
        return _contestPhaseColumn.getDefaultSort();
    }

    /**
    * Returns <code>true</code> if this contest phase column is default sort.
    *
    * @return <code>true</code> if this contest phase column is default sort; <code>false</code> otherwise
    */
    @Override
    public boolean isDefaultSort() {
        return _contestPhaseColumn.isDefaultSort();
    }

    /**
    * Sets whether this contest phase column is default sort.
    *
    * @param defaultSort the default sort of this contest phase column
    */
    @Override
    public void setDefaultSort(boolean defaultSort) {
        _contestPhaseColumn.setDefaultSort(defaultSort);
    }

    @Override
    public boolean isNew() {
        return _contestPhaseColumn.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestPhaseColumn.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestPhaseColumn.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestPhaseColumn.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestPhaseColumn.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestPhaseColumn.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestPhaseColumn.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestPhaseColumn.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestPhaseColumn.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestPhaseColumn.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestPhaseColumn.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestPhaseColumnWrapper((ContestPhaseColumn) _contestPhaseColumn.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn) {
        return _contestPhaseColumn.compareTo(contestPhaseColumn);
    }

    @Override
    public int hashCode() {
        return _contestPhaseColumn.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ContestPhaseColumn> toCacheModel() {
        return _contestPhaseColumn.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ContestPhaseColumn toEscapedModel() {
        return new ContestPhaseColumnWrapper(_contestPhaseColumn.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ContestPhaseColumn toUnescapedModel() {
        return new ContestPhaseColumnWrapper(_contestPhaseColumn.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestPhaseColumn.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestPhaseColumn.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseColumn.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestPhaseColumnWrapper)) {
            return false;
        }

        ContestPhaseColumnWrapper contestPhaseColumnWrapper = (ContestPhaseColumnWrapper) obj;

        if (Validator.equals(_contestPhaseColumn,
                    contestPhaseColumnWrapper._contestPhaseColumn)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestPhaseColumn getWrappedContestPhaseColumn() {
        return _contestPhaseColumn;
    }

    @Override
    public ContestPhaseColumn getWrappedModel() {
        return _contestPhaseColumn;
    }

    @Override
    public void resetOriginalValues() {
        _contestPhaseColumn.resetOriginalValues();
    }
}

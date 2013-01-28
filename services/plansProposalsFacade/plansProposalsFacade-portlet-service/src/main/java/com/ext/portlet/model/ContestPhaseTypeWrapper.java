package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseType}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseType
 * @generated
 */
public class ContestPhaseTypeWrapper implements ContestPhaseType,
    ModelWrapper<ContestPhaseType> {
    private ContestPhaseType _contestPhaseType;

    public ContestPhaseTypeWrapper(ContestPhaseType contestPhaseType) {
        _contestPhaseType = contestPhaseType;
    }

    public Class<?> getModelClass() {
        return ContestPhaseType.class;
    }

    public String getModelClassName() {
        return ContestPhaseType.class.getName();
    }

    /**
    * Returns the primary key of this contest phase type.
    *
    * @return the primary key of this contest phase type
    */
    public long getPrimaryKey() {
        return _contestPhaseType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest phase type.
    *
    * @param primaryKey the primary key of this contest phase type
    */
    public void setPrimaryKey(long primaryKey) {
        _contestPhaseType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest phase type.
    *
    * @return the ID of this contest phase type
    */
    public long getId() {
        return _contestPhaseType.getId();
    }

    /**
    * Sets the ID of this contest phase type.
    *
    * @param id the ID of this contest phase type
    */
    public void setId(long id) {
        _contestPhaseType.setId(id);
    }

    /**
    * Returns the name of this contest phase type.
    *
    * @return the name of this contest phase type
    */
    public java.lang.String getName() {
        return _contestPhaseType.getName();
    }

    /**
    * Sets the name of this contest phase type.
    *
    * @param name the name of this contest phase type
    */
    public void setName(java.lang.String name) {
        _contestPhaseType.setName(name);
    }

    /**
    * Returns the description of this contest phase type.
    *
    * @return the description of this contest phase type
    */
    public java.lang.String getDescription() {
        return _contestPhaseType.getDescription();
    }

    /**
    * Sets the description of this contest phase type.
    *
    * @param description the description of this contest phase type
    */
    public void setDescription(java.lang.String description) {
        _contestPhaseType.setDescription(description);
    }

    /**
    * Returns the status of this contest phase type.
    *
    * @return the status of this contest phase type
    */
    public java.lang.String getStatus() {
        return _contestPhaseType.getStatus();
    }

    /**
    * Sets the status of this contest phase type.
    *
    * @param status the status of this contest phase type
    */
    public void setStatus(java.lang.String status) {
        _contestPhaseType.setStatus(status);
    }

    public boolean isNew() {
        return _contestPhaseType.isNew();
    }

    public void setNew(boolean n) {
        _contestPhaseType.setNew(n);
    }

    public boolean isCachedModel() {
        return _contestPhaseType.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _contestPhaseType.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _contestPhaseType.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _contestPhaseType.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestPhaseType.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestPhaseType.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestPhaseType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestPhaseTypeWrapper((ContestPhaseType) _contestPhaseType.clone());
    }

    public int compareTo(ContestPhaseType contestPhaseType) {
        return _contestPhaseType.compareTo(contestPhaseType);
    }

    @Override
    public int hashCode() {
        return _contestPhaseType.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ContestPhaseType> toCacheModel() {
        return _contestPhaseType.toCacheModel();
    }

    public ContestPhaseType toEscapedModel() {
        return new ContestPhaseTypeWrapper(_contestPhaseType.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestPhaseType.toString();
    }

    public java.lang.String toXmlString() {
        return _contestPhaseType.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseType.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ContestPhaseType getWrappedContestPhaseType() {
        return _contestPhaseType;
    }

    public ContestPhaseType getWrappedModel() {
        return _contestPhaseType;
    }

    public void resetOriginalValues() {
        _contestPhaseType.resetOriginalValues();
    }
}

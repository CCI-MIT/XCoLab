package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseRibbonType}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseRibbonType
 * @generated
 */
public class ContestPhaseRibbonTypeWrapper implements ContestPhaseRibbonType,
    ModelWrapper<ContestPhaseRibbonType> {
    private ContestPhaseRibbonType _contestPhaseRibbonType;

    public ContestPhaseRibbonTypeWrapper(
        ContestPhaseRibbonType contestPhaseRibbonType) {
        _contestPhaseRibbonType = contestPhaseRibbonType;
    }

    public Class<?> getModelClass() {
        return ContestPhaseRibbonType.class;
    }

    public String getModelClassName() {
        return ContestPhaseRibbonType.class.getName();
    }

    /**
    * Returns the primary key of this contest phase ribbon type.
    *
    * @return the primary key of this contest phase ribbon type
    */
    public long getPrimaryKey() {
        return _contestPhaseRibbonType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest phase ribbon type.
    *
    * @param primaryKey the primary key of this contest phase ribbon type
    */
    public void setPrimaryKey(long primaryKey) {
        _contestPhaseRibbonType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest phase ribbon type.
    *
    * @return the ID of this contest phase ribbon type
    */
    public long getId() {
        return _contestPhaseRibbonType.getId();
    }

    /**
    * Sets the ID of this contest phase ribbon type.
    *
    * @param id the ID of this contest phase ribbon type
    */
    public void setId(long id) {
        _contestPhaseRibbonType.setId(id);
    }

    /**
    * Returns the ribbon of this contest phase ribbon type.
    *
    * @return the ribbon of this contest phase ribbon type
    */
    public int getRibbon() {
        return _contestPhaseRibbonType.getRibbon();
    }

    /**
    * Sets the ribbon of this contest phase ribbon type.
    *
    * @param ribbon the ribbon of this contest phase ribbon type
    */
    public void setRibbon(int ribbon) {
        _contestPhaseRibbonType.setRibbon(ribbon);
    }

    /**
    * Returns the hover text of this contest phase ribbon type.
    *
    * @return the hover text of this contest phase ribbon type
    */
    public java.lang.String getHoverText() {
        return _contestPhaseRibbonType.getHoverText();
    }

    /**
    * Sets the hover text of this contest phase ribbon type.
    *
    * @param hoverText the hover text of this contest phase ribbon type
    */
    public void setHoverText(java.lang.String hoverText) {
        _contestPhaseRibbonType.setHoverText(hoverText);
    }

    /**
    * Returns the description of this contest phase ribbon type.
    *
    * @return the description of this contest phase ribbon type
    */
    public java.lang.String getDescription() {
        return _contestPhaseRibbonType.getDescription();
    }

    /**
    * Sets the description of this contest phase ribbon type.
    *
    * @param description the description of this contest phase ribbon type
    */
    public void setDescription(java.lang.String description) {
        _contestPhaseRibbonType.setDescription(description);
    }

    /**
    * Returns the copy on promote of this contest phase ribbon type.
    *
    * @return the copy on promote of this contest phase ribbon type
    */
    public boolean getCopyOnPromote() {
        return _contestPhaseRibbonType.getCopyOnPromote();
    }

    /**
    * Returns <code>true</code> if this contest phase ribbon type is copy on promote.
    *
    * @return <code>true</code> if this contest phase ribbon type is copy on promote; <code>false</code> otherwise
    */
    public boolean isCopyOnPromote() {
        return _contestPhaseRibbonType.isCopyOnPromote();
    }

    /**
    * Sets whether this contest phase ribbon type is copy on promote.
    *
    * @param copyOnPromote the copy on promote of this contest phase ribbon type
    */
    public void setCopyOnPromote(boolean copyOnPromote) {
        _contestPhaseRibbonType.setCopyOnPromote(copyOnPromote);
    }

    public boolean isNew() {
        return _contestPhaseRibbonType.isNew();
    }

    public void setNew(boolean n) {
        _contestPhaseRibbonType.setNew(n);
    }

    public boolean isCachedModel() {
        return _contestPhaseRibbonType.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _contestPhaseRibbonType.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _contestPhaseRibbonType.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _contestPhaseRibbonType.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestPhaseRibbonType.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestPhaseRibbonType.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestPhaseRibbonType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestPhaseRibbonTypeWrapper((ContestPhaseRibbonType) _contestPhaseRibbonType.clone());
    }

    public int compareTo(ContestPhaseRibbonType contestPhaseRibbonType) {
        return _contestPhaseRibbonType.compareTo(contestPhaseRibbonType);
    }

    @Override
    public int hashCode() {
        return _contestPhaseRibbonType.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ContestPhaseRibbonType> toCacheModel() {
        return _contestPhaseRibbonType.toCacheModel();
    }

    public ContestPhaseRibbonType toEscapedModel() {
        return new ContestPhaseRibbonTypeWrapper(_contestPhaseRibbonType.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestPhaseRibbonType.toString();
    }

    public java.lang.String toXmlString() {
        return _contestPhaseRibbonType.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseRibbonType.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ContestPhaseRibbonType getWrappedContestPhaseRibbonType() {
        return _contestPhaseRibbonType;
    }

    public ContestPhaseRibbonType getWrappedModel() {
        return _contestPhaseRibbonType;
    }

    public void resetOriginalValues() {
        _contestPhaseRibbonType.resetOriginalValues();
    }
}

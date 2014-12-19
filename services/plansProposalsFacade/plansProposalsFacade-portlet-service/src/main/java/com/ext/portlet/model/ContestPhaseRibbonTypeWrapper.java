package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseRibbonType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonType
 * @generated
 */
public class ContestPhaseRibbonTypeWrapper implements ContestPhaseRibbonType,
    ModelWrapper<ContestPhaseRibbonType> {
    private ContestPhaseRibbonType _contestPhaseRibbonType;

    public ContestPhaseRibbonTypeWrapper(
        ContestPhaseRibbonType contestPhaseRibbonType) {
        _contestPhaseRibbonType = contestPhaseRibbonType;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestPhaseRibbonType.class;
    }

    @Override
    public String getModelClassName() {
        return ContestPhaseRibbonType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("ribbon", getRibbon());
        attributes.put("hoverText", getHoverText());
        attributes.put("description", getDescription());
        attributes.put("copyOnPromote", getCopyOnPromote());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Integer ribbon = (Integer) attributes.get("ribbon");

        if (ribbon != null) {
            setRibbon(ribbon);
        }

        String hoverText = (String) attributes.get("hoverText");

        if (hoverText != null) {
            setHoverText(hoverText);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Boolean copyOnPromote = (Boolean) attributes.get("copyOnPromote");

        if (copyOnPromote != null) {
            setCopyOnPromote(copyOnPromote);
        }
    }

    /**
    * Returns the primary key of this contest phase ribbon type.
    *
    * @return the primary key of this contest phase ribbon type
    */
    @Override
    public long getPrimaryKey() {
        return _contestPhaseRibbonType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest phase ribbon type.
    *
    * @param primaryKey the primary key of this contest phase ribbon type
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestPhaseRibbonType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest phase ribbon type.
    *
    * @return the ID of this contest phase ribbon type
    */
    @Override
    public long getId() {
        return _contestPhaseRibbonType.getId();
    }

    /**
    * Sets the ID of this contest phase ribbon type.
    *
    * @param id the ID of this contest phase ribbon type
    */
    @Override
    public void setId(long id) {
        _contestPhaseRibbonType.setId(id);
    }

    /**
    * Returns the ribbon of this contest phase ribbon type.
    *
    * @return the ribbon of this contest phase ribbon type
    */
    @Override
    public int getRibbon() {
        return _contestPhaseRibbonType.getRibbon();
    }

    /**
    * Sets the ribbon of this contest phase ribbon type.
    *
    * @param ribbon the ribbon of this contest phase ribbon type
    */
    @Override
    public void setRibbon(int ribbon) {
        _contestPhaseRibbonType.setRibbon(ribbon);
    }

    /**
    * Returns the hover text of this contest phase ribbon type.
    *
    * @return the hover text of this contest phase ribbon type
    */
    @Override
    public java.lang.String getHoverText() {
        return _contestPhaseRibbonType.getHoverText();
    }

    /**
    * Sets the hover text of this contest phase ribbon type.
    *
    * @param hoverText the hover text of this contest phase ribbon type
    */
    @Override
    public void setHoverText(java.lang.String hoverText) {
        _contestPhaseRibbonType.setHoverText(hoverText);
    }

    /**
    * Returns the description of this contest phase ribbon type.
    *
    * @return the description of this contest phase ribbon type
    */
    @Override
    public java.lang.String getDescription() {
        return _contestPhaseRibbonType.getDescription();
    }

    /**
    * Sets the description of this contest phase ribbon type.
    *
    * @param description the description of this contest phase ribbon type
    */
    @Override
    public void setDescription(java.lang.String description) {
        _contestPhaseRibbonType.setDescription(description);
    }

    /**
    * Returns the copy on promote of this contest phase ribbon type.
    *
    * @return the copy on promote of this contest phase ribbon type
    */
    @Override
    public boolean getCopyOnPromote() {
        return _contestPhaseRibbonType.getCopyOnPromote();
    }

    /**
    * Returns <code>true</code> if this contest phase ribbon type is copy on promote.
    *
    * @return <code>true</code> if this contest phase ribbon type is copy on promote; <code>false</code> otherwise
    */
    @Override
    public boolean isCopyOnPromote() {
        return _contestPhaseRibbonType.isCopyOnPromote();
    }

    /**
    * Sets whether this contest phase ribbon type is copy on promote.
    *
    * @param copyOnPromote the copy on promote of this contest phase ribbon type
    */
    @Override
    public void setCopyOnPromote(boolean copyOnPromote) {
        _contestPhaseRibbonType.setCopyOnPromote(copyOnPromote);
    }

    @Override
    public boolean isNew() {
        return _contestPhaseRibbonType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestPhaseRibbonType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestPhaseRibbonType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestPhaseRibbonType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestPhaseRibbonType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestPhaseRibbonType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestPhaseRibbonType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestPhaseRibbonType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestPhaseRibbonType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestPhaseRibbonType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestPhaseRibbonType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestPhaseRibbonTypeWrapper((ContestPhaseRibbonType) _contestPhaseRibbonType.clone());
    }

    @Override
    public int compareTo(ContestPhaseRibbonType contestPhaseRibbonType) {
        return _contestPhaseRibbonType.compareTo(contestPhaseRibbonType);
    }

    @Override
    public int hashCode() {
        return _contestPhaseRibbonType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ContestPhaseRibbonType> toCacheModel() {
        return _contestPhaseRibbonType.toCacheModel();
    }

    @Override
    public ContestPhaseRibbonType toEscapedModel() {
        return new ContestPhaseRibbonTypeWrapper(_contestPhaseRibbonType.toEscapedModel());
    }

    @Override
    public ContestPhaseRibbonType toUnescapedModel() {
        return new ContestPhaseRibbonTypeWrapper(_contestPhaseRibbonType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestPhaseRibbonType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestPhaseRibbonType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseRibbonType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestPhaseRibbonTypeWrapper)) {
            return false;
        }

        ContestPhaseRibbonTypeWrapper contestPhaseRibbonTypeWrapper = (ContestPhaseRibbonTypeWrapper) obj;

        if (Validator.equals(_contestPhaseRibbonType,
                    contestPhaseRibbonTypeWrapper._contestPhaseRibbonType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestPhaseRibbonType getWrappedContestPhaseRibbonType() {
        return _contestPhaseRibbonType;
    }

    @Override
    public ContestPhaseRibbonType getWrappedModel() {
        return _contestPhaseRibbonType;
    }

    @Override
    public void resetOriginalValues() {
        _contestPhaseRibbonType.resetOriginalValues();
    }
}

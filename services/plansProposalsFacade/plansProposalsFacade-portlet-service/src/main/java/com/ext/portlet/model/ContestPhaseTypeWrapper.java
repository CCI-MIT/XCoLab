package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseType
 * @generated
 */
public class ContestPhaseTypeWrapper implements ContestPhaseType,
    ModelWrapper<ContestPhaseType> {
    private ContestPhaseType _contestPhaseType;

    public ContestPhaseTypeWrapper(ContestPhaseType contestPhaseType) {
        _contestPhaseType = contestPhaseType;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestPhaseType.class;
    }

    @Override
    public String getModelClassName() {
        return ContestPhaseType.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("status", getStatus());
        attributes.put("invisible", getInvisible());
        attributes.put("pointsAccessible", getPointsAccessible());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Boolean invisible = (Boolean) attributes.get("invisible");

        if (invisible != null) {
            setInvisible(invisible);
        }

        Integer pointsAccessible = (Integer) attributes.get("pointsAccessible");

        if (pointsAccessible != null) {
            setPointsAccessible(pointsAccessible);
        }
    }

    /**
    * Returns the primary key of this contest phase type.
    *
    * @return the primary key of this contest phase type
    */
    @Override
    public long getPrimaryKey() {
        return _contestPhaseType.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest phase type.
    *
    * @param primaryKey the primary key of this contest phase type
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestPhaseType.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest phase type.
    *
    * @return the ID of this contest phase type
    */
    @Override
    public long getId() {
        return _contestPhaseType.getId();
    }

    /**
    * Sets the ID of this contest phase type.
    *
    * @param id the ID of this contest phase type
    */
    @Override
    public void setId(long id) {
        _contestPhaseType.setId(id);
    }

    /**
    * Returns the name of this contest phase type.
    *
    * @return the name of this contest phase type
    */
    @Override
    public java.lang.String getName() {
        return _contestPhaseType.getName();
    }

    /**
    * Sets the name of this contest phase type.
    *
    * @param name the name of this contest phase type
    */
    @Override
    public void setName(java.lang.String name) {
        _contestPhaseType.setName(name);
    }

    /**
    * Returns the description of this contest phase type.
    *
    * @return the description of this contest phase type
    */
    @Override
    public java.lang.String getDescription() {
        return _contestPhaseType.getDescription();
    }

    /**
    * Sets the description of this contest phase type.
    *
    * @param description the description of this contest phase type
    */
    @Override
    public void setDescription(java.lang.String description) {
        _contestPhaseType.setDescription(description);
    }

    /**
    * Returns the status of this contest phase type.
    *
    * @return the status of this contest phase type
    */
    @Override
    public java.lang.String getStatus() {
        return _contestPhaseType.getStatus();
    }

    /**
    * Sets the status of this contest phase type.
    *
    * @param status the status of this contest phase type
    */
    @Override
    public void setStatus(java.lang.String status) {
        _contestPhaseType.setStatus(status);
    }

    /**
    * Returns the invisible of this contest phase type.
    *
    * @return the invisible of this contest phase type
    */
    @Override
    public boolean getInvisible() {
        return _contestPhaseType.getInvisible();
    }

    /**
    * Returns <code>true</code> if this contest phase type is invisible.
    *
    * @return <code>true</code> if this contest phase type is invisible; <code>false</code> otherwise
    */
    @Override
    public boolean isInvisible() {
        return _contestPhaseType.isInvisible();
    }

    /**
    * Sets whether this contest phase type is invisible.
    *
    * @param invisible the invisible of this contest phase type
    */
    @Override
    public void setInvisible(boolean invisible) {
        _contestPhaseType.setInvisible(invisible);
    }

    /**
    * Returns the points accessible of this contest phase type.
    *
    * @return the points accessible of this contest phase type
    */
    @Override
    public int getPointsAccessible() {
        return _contestPhaseType.getPointsAccessible();
    }

    /**
    * Sets the points accessible of this contest phase type.
    *
    * @param pointsAccessible the points accessible of this contest phase type
    */
    @Override
    public void setPointsAccessible(int pointsAccessible) {
        _contestPhaseType.setPointsAccessible(pointsAccessible);
    }

    @Override
    public boolean isNew() {
        return _contestPhaseType.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestPhaseType.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestPhaseType.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestPhaseType.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestPhaseType.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestPhaseType.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestPhaseType.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestPhaseType.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestPhaseType.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestPhaseType.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestPhaseType.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestPhaseTypeWrapper((ContestPhaseType) _contestPhaseType.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ContestPhaseType contestPhaseType) {
        return _contestPhaseType.compareTo(contestPhaseType);
    }

    @Override
    public int hashCode() {
        return _contestPhaseType.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ContestPhaseType> toCacheModel() {
        return _contestPhaseType.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ContestPhaseType toEscapedModel() {
        return new ContestPhaseTypeWrapper(_contestPhaseType.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ContestPhaseType toUnescapedModel() {
        return new ContestPhaseTypeWrapper(_contestPhaseType.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestPhaseType.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestPhaseType.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseType.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestPhaseTypeWrapper)) {
            return false;
        }

        ContestPhaseTypeWrapper contestPhaseTypeWrapper = (ContestPhaseTypeWrapper) obj;

        if (Validator.equals(_contestPhaseType,
                    contestPhaseTypeWrapper._contestPhaseType)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestPhaseType getWrappedContestPhaseType() {
        return _contestPhaseType;
    }

    @Override
    public ContestPhaseType getWrappedModel() {
        return _contestPhaseType;
    }

    @Override
    public void resetOriginalValues() {
        _contestPhaseType.resetOriginalValues();
    }
}

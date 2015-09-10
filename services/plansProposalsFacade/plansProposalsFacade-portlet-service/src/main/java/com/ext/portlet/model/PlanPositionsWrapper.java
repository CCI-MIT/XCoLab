package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositions}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositions
 * @generated
 */
public class PlanPositionsWrapper implements PlanPositions,
    ModelWrapper<PlanPositions> {
    private PlanPositions _planPositions;

    public PlanPositionsWrapper(PlanPositions planPositions) {
        _planPositions = planPositions;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanPositions.class;
    }

    @Override
    public String getModelClassName() {
        return PlanPositions.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("planId", getPlanId());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("version", getVersion());
        attributes.put("created", getCreated());
        attributes.put("updateAuthorId", getUpdateAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }
    }

    /**
    * Returns the primary key of this plan positions.
    *
    * @return the primary key of this plan positions
    */
    @Override
    public long getPrimaryKey() {
        return _planPositions.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan positions.
    *
    * @param primaryKey the primary key of this plan positions
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planPositions.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan positions.
    *
    * @return the ID of this plan positions
    */
    @Override
    public long getId() {
        return _planPositions.getId();
    }

    /**
    * Sets the ID of this plan positions.
    *
    * @param id the ID of this plan positions
    */
    @Override
    public void setId(long id) {
        _planPositions.setId(id);
    }

    /**
    * Returns the plan ID of this plan positions.
    *
    * @return the plan ID of this plan positions
    */
    @Override
    public long getPlanId() {
        return _planPositions.getPlanId();
    }

    /**
    * Sets the plan ID of this plan positions.
    *
    * @param planId the plan ID of this plan positions
    */
    @Override
    public void setPlanId(long planId) {
        _planPositions.setPlanId(planId);
    }

    /**
    * Returns the plan version of this plan positions.
    *
    * @return the plan version of this plan positions
    */
    @Override
    public long getPlanVersion() {
        return _planPositions.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan positions.
    *
    * @param planVersion the plan version of this plan positions
    */
    @Override
    public void setPlanVersion(long planVersion) {
        _planPositions.setPlanVersion(planVersion);
    }

    /**
    * Returns the version of this plan positions.
    *
    * @return the version of this plan positions
    */
    @Override
    public long getVersion() {
        return _planPositions.getVersion();
    }

    /**
    * Sets the version of this plan positions.
    *
    * @param version the version of this plan positions
    */
    @Override
    public void setVersion(long version) {
        _planPositions.setVersion(version);
    }

    /**
    * Returns the created of this plan positions.
    *
    * @return the created of this plan positions
    */
    @Override
    public java.util.Date getCreated() {
        return _planPositions.getCreated();
    }

    /**
    * Sets the created of this plan positions.
    *
    * @param created the created of this plan positions
    */
    @Override
    public void setCreated(java.util.Date created) {
        _planPositions.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan positions.
    *
    * @return the update author ID of this plan positions
    */
    @Override
    public long getUpdateAuthorId() {
        return _planPositions.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan positions.
    *
    * @param updateAuthorId the update author ID of this plan positions
    */
    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _planPositions.setUpdateAuthorId(updateAuthorId);
    }

    @Override
    public boolean isNew() {
        return _planPositions.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planPositions.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planPositions.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planPositions.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planPositions.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planPositions.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planPositions.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planPositions.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planPositions.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planPositions.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planPositions.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanPositionsWrapper((PlanPositions) _planPositions.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlanPositions planPositions) {
        return _planPositions.compareTo(planPositions);
    }

    @Override
    public int hashCode() {
        return _planPositions.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanPositions> toCacheModel() {
        return _planPositions.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanPositions toEscapedModel() {
        return new PlanPositionsWrapper(_planPositions.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanPositions toUnescapedModel() {
        return new PlanPositionsWrapper(_planPositions.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planPositions.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planPositions.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPositions.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanPositionsWrapper)) {
            return false;
        }

        PlanPositionsWrapper planPositionsWrapper = (PlanPositionsWrapper) obj;

        if (Validator.equals(_planPositions, planPositionsWrapper._planPositions)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanPositions getWrappedPlanPositions() {
        return _planPositions;
    }

    @Override
    public PlanPositions getWrappedModel() {
        return _planPositions;
    }

    @Override
    public void resetOriginalValues() {
        _planPositions.resetOriginalValues();
    }
}

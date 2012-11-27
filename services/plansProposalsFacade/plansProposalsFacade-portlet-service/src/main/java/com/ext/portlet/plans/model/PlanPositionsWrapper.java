package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositions}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPositions
 * @generated
 */
public class PlanPositionsWrapper implements PlanPositions,
    ModelWrapper<PlanPositions> {
    private PlanPositions _planPositions;

    public PlanPositionsWrapper(PlanPositions planPositions) {
        _planPositions = planPositions;
    }

    public Class<?> getModelClass() {
        return PlanPositions.class;
    }

    public String getModelClassName() {
        return PlanPositions.class.getName();
    }

    /**
    * Returns the primary key of this plan positions.
    *
    * @return the primary key of this plan positions
    */
    public java.lang.Long getPrimaryKey() {
        return _planPositions.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan positions.
    *
    * @param primaryKey the primary key of this plan positions
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _planPositions.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan positions.
    *
    * @return the ID of this plan positions
    */
    public java.lang.Long getId() {
        return _planPositions.getId();
    }

    /**
    * Sets the ID of this plan positions.
    *
    * @param id the ID of this plan positions
    */
    public void setId(java.lang.Long id) {
        _planPositions.setId(id);
    }

    /**
    * Returns the plan ID of this plan positions.
    *
    * @return the plan ID of this plan positions
    */
    public java.lang.Long getPlanId() {
        return _planPositions.getPlanId();
    }

    /**
    * Sets the plan ID of this plan positions.
    *
    * @param planId the plan ID of this plan positions
    */
    public void setPlanId(java.lang.Long planId) {
        _planPositions.setPlanId(planId);
    }

    /**
    * Returns the plan version of this plan positions.
    *
    * @return the plan version of this plan positions
    */
    public java.lang.Long getPlanVersion() {
        return _planPositions.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan positions.
    *
    * @param planVersion the plan version of this plan positions
    */
    public void setPlanVersion(java.lang.Long planVersion) {
        _planPositions.setPlanVersion(planVersion);
    }

    /**
    * Returns the version of this plan positions.
    *
    * @return the version of this plan positions
    */
    public java.lang.Long getVersion() {
        return _planPositions.getVersion();
    }

    /**
    * Sets the version of this plan positions.
    *
    * @param version the version of this plan positions
    */
    public void setVersion(java.lang.Long version) {
        _planPositions.setVersion(version);
    }

    /**
    * Returns the created of this plan positions.
    *
    * @return the created of this plan positions
    */
    public java.util.Date getCreated() {
        return _planPositions.getCreated();
    }

    /**
    * Sets the created of this plan positions.
    *
    * @param created the created of this plan positions
    */
    public void setCreated(java.util.Date created) {
        _planPositions.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan positions.
    *
    * @return the update author ID of this plan positions
    */
    public java.lang.Long getUpdateAuthorId() {
        return _planPositions.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan positions.
    *
    * @param updateAuthorId the update author ID of this plan positions
    */
    public void setUpdateAuthorId(java.lang.Long updateAuthorId) {
        _planPositions.setUpdateAuthorId(updateAuthorId);
    }

    public boolean isNew() {
        return _planPositions.isNew();
    }

    public void setNew(boolean n) {
        _planPositions.setNew(n);
    }

    public boolean isCachedModel() {
        return _planPositions.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planPositions.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planPositions.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planPositions.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planPositions.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planPositions.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planPositions.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanPositionsWrapper((PlanPositions) _planPositions.clone());
    }

    public int compareTo(PlanPositions planPositions) {
        return _planPositions.compareTo(planPositions);
    }

    @Override
    public int hashCode() {
        return _planPositions.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanPositions> toCacheModel() {
        return _planPositions.toCacheModel();
    }

    public PlanPositions toEscapedModel() {
        return new PlanPositionsWrapper(_planPositions.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planPositions.toString();
    }

    public java.lang.String toXmlString() {
        return _planPositions.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPositions.persist();
    }

    public java.util.List<java.lang.Long> getPositionsIds()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositions.getPositionsIds();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPositions.store();
    }

    public void setPositionsIds(java.util.List<java.lang.Long> positionsIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planPositions.setPositionsIds(positionsIds);
    }

    public com.liferay.portal.model.User getUpdateAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositions.getUpdateAuthor();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanPositions getWrappedPlanPositions() {
        return _planPositions;
    }

    public PlanPositions getWrappedModel() {
        return _planPositions;
    }

    public void resetOriginalValues() {
        _planPositions.resetOriginalValues();
    }
}

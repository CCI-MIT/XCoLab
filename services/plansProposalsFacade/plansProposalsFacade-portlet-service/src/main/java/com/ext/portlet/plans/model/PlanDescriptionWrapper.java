package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanDescription}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanDescription
 * @generated
 */
public class PlanDescriptionWrapper implements PlanDescription,
    ModelWrapper<PlanDescription> {
    private PlanDescription _planDescription;

    public PlanDescriptionWrapper(PlanDescription planDescription) {
        _planDescription = planDescription;
    }

    public Class<?> getModelClass() {
        return PlanDescription.class;
    }

    public String getModelClassName() {
        return PlanDescription.class.getName();
    }

    /**
    * Returns the primary key of this plan description.
    *
    * @return the primary key of this plan description
    */
    public java.lang.Long getPrimaryKey() {
        return _planDescription.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan description.
    *
    * @param primaryKey the primary key of this plan description
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _planDescription.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan description.
    *
    * @return the ID of this plan description
    */
    public java.lang.Long getId() {
        return _planDescription.getId();
    }

    /**
    * Sets the ID of this plan description.
    *
    * @param id the ID of this plan description
    */
    public void setId(java.lang.Long id) {
        _planDescription.setId(id);
    }

    /**
    * Returns the plan ID of this plan description.
    *
    * @return the plan ID of this plan description
    */
    public java.lang.Long getPlanId() {
        return _planDescription.getPlanId();
    }

    /**
    * Sets the plan ID of this plan description.
    *
    * @param planId the plan ID of this plan description
    */
    public void setPlanId(java.lang.Long planId) {
        _planDescription.setPlanId(planId);
    }

    /**
    * Returns the name of this plan description.
    *
    * @return the name of this plan description
    */
    public java.lang.String getName() {
        return _planDescription.getName();
    }

    /**
    * Sets the name of this plan description.
    *
    * @param name the name of this plan description
    */
    public void setName(java.lang.String name) {
        _planDescription.setName(name);
    }

    /**
    * Returns the description of this plan description.
    *
    * @return the description of this plan description
    */
    public java.lang.String getDescription() {
        return _planDescription.getDescription();
    }

    /**
    * Sets the description of this plan description.
    *
    * @param description the description of this plan description
    */
    public void setDescription(java.lang.String description) {
        _planDescription.setDescription(description);
    }

    /**
    * Returns the version of this plan description.
    *
    * @return the version of this plan description
    */
    public java.lang.Long getVersion() {
        return _planDescription.getVersion();
    }

    /**
    * Sets the version of this plan description.
    *
    * @param version the version of this plan description
    */
    public void setVersion(java.lang.Long version) {
        _planDescription.setVersion(version);
    }

    /**
    * Returns the plan version of this plan description.
    *
    * @return the plan version of this plan description
    */
    public java.lang.Long getPlanVersion() {
        return _planDescription.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan description.
    *
    * @param planVersion the plan version of this plan description
    */
    public void setPlanVersion(java.lang.Long planVersion) {
        _planDescription.setPlanVersion(planVersion);
    }

    /**
    * Returns the created of this plan description.
    *
    * @return the created of this plan description
    */
    public java.util.Date getCreated() {
        return _planDescription.getCreated();
    }

    /**
    * Sets the created of this plan description.
    *
    * @param created the created of this plan description
    */
    public void setCreated(java.util.Date created) {
        _planDescription.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan description.
    *
    * @return the update author ID of this plan description
    */
    public java.lang.Long getUpdateAuthorId() {
        return _planDescription.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan description.
    *
    * @param updateAuthorId the update author ID of this plan description
    */
    public void setUpdateAuthorId(java.lang.Long updateAuthorId) {
        _planDescription.setUpdateAuthorId(updateAuthorId);
    }

    /**
    * Returns the image of this plan description.
    *
    * @return the image of this plan description
    */
    public java.lang.Long getImage() {
        return _planDescription.getImage();
    }

    /**
    * Sets the image of this plan description.
    *
    * @param image the image of this plan description
    */
    public void setImage(java.lang.Long image) {
        _planDescription.setImage(image);
    }

    /**
    * Returns the pitch of this plan description.
    *
    * @return the pitch of this plan description
    */
    public java.lang.String getPitch() {
        return _planDescription.getPitch();
    }

    /**
    * Sets the pitch of this plan description.
    *
    * @param pitch the pitch of this plan description
    */
    public void setPitch(java.lang.String pitch) {
        _planDescription.setPitch(pitch);
    }

    public boolean isNew() {
        return _planDescription.isNew();
    }

    public void setNew(boolean n) {
        _planDescription.setNew(n);
    }

    public boolean isCachedModel() {
        return _planDescription.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planDescription.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planDescription.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planDescription.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planDescription.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planDescription.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planDescription.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanDescriptionWrapper((PlanDescription) _planDescription.clone());
    }

    public int compareTo(PlanDescription planDescription) {
        return _planDescription.compareTo(planDescription);
    }

    @Override
    public int hashCode() {
        return _planDescription.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanDescription> toCacheModel() {
        return _planDescription.toCacheModel();
    }

    public PlanDescription toEscapedModel() {
        return new PlanDescriptionWrapper(_planDescription.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planDescription.toString();
    }

    public java.lang.String toXmlString() {
        return _planDescription.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planDescription.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planDescription.store();
    }

    public com.liferay.portal.model.User getUpdateAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planDescription.getUpdateAuthor();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanDescription getWrappedPlanDescription() {
        return _planDescription;
    }

    public PlanDescription getWrappedModel() {
        return _planDescription;
    }

    public void resetOriginalValues() {
        _planDescription.resetOriginalValues();
    }
}

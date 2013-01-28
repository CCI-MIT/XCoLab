package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanRelated}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanRelated
 * @generated
 */
public class PlanRelatedWrapper implements PlanRelated,
    ModelWrapper<PlanRelated> {
    private PlanRelated _planRelated;

    public PlanRelatedWrapper(PlanRelated planRelated) {
        _planRelated = planRelated;
    }

    public Class<?> getModelClass() {
        return PlanRelated.class;
    }

    public String getModelClassName() {
        return PlanRelated.class.getName();
    }

    /**
    * Returns the primary key of this plan related.
    *
    * @return the primary key of this plan related
    */
    public com.ext.portlet.service.persistence.PlanRelatedPK getPrimaryKey() {
        return _planRelated.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan related.
    *
    * @param primaryKey the primary key of this plan related
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanRelatedPK primaryKey) {
        _planRelated.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the section ID of this plan related.
    *
    * @return the section ID of this plan related
    */
    public long getSectionId() {
        return _planRelated.getSectionId();
    }

    /**
    * Sets the section ID of this plan related.
    *
    * @param sectionId the section ID of this plan related
    */
    public void setSectionId(long sectionId) {
        _planRelated.setSectionId(sectionId);
    }

    /**
    * Returns the related plan ID of this plan related.
    *
    * @return the related plan ID of this plan related
    */
    public long getRelatedPlanId() {
        return _planRelated.getRelatedPlanId();
    }

    /**
    * Sets the related plan ID of this plan related.
    *
    * @param relatedPlanId the related plan ID of this plan related
    */
    public void setRelatedPlanId(long relatedPlanId) {
        _planRelated.setRelatedPlanId(relatedPlanId);
    }

    public boolean isNew() {
        return _planRelated.isNew();
    }

    public void setNew(boolean n) {
        _planRelated.setNew(n);
    }

    public boolean isCachedModel() {
        return _planRelated.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planRelated.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planRelated.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planRelated.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planRelated.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planRelated.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planRelated.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanRelatedWrapper((PlanRelated) _planRelated.clone());
    }

    public int compareTo(PlanRelated planRelated) {
        return _planRelated.compareTo(planRelated);
    }

    @Override
    public int hashCode() {
        return _planRelated.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanRelated> toCacheModel() {
        return _planRelated.toCacheModel();
    }

    public PlanRelated toEscapedModel() {
        return new PlanRelatedWrapper(_planRelated.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planRelated.toString();
    }

    public java.lang.String toXmlString() {
        return _planRelated.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planRelated.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanRelated getWrappedPlanRelated() {
        return _planRelated;
    }

    public PlanRelated getWrappedModel() {
        return _planRelated;
    }

    public void resetOriginalValues() {
        _planRelated.resetOriginalValues();
    }
}

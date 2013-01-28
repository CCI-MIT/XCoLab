package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItem}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItem
 * @generated
 */
public class PlanItemWrapper implements PlanItem, ModelWrapper<PlanItem> {
    private PlanItem _planItem;

    public PlanItemWrapper(PlanItem planItem) {
        _planItem = planItem;
    }

    public Class<?> getModelClass() {
        return PlanItem.class;
    }

    public String getModelClassName() {
        return PlanItem.class.getName();
    }

    /**
    * Returns the primary key of this plan item.
    *
    * @return the primary key of this plan item
    */
    public long getPrimaryKey() {
        return _planItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan item.
    *
    * @param primaryKey the primary key of this plan item
    */
    public void setPrimaryKey(long primaryKey) {
        _planItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan item.
    *
    * @return the ID of this plan item
    */
    public long getId() {
        return _planItem.getId();
    }

    /**
    * Sets the ID of this plan item.
    *
    * @param id the ID of this plan item
    */
    public void setId(long id) {
        _planItem.setId(id);
    }

    /**
    * Returns the plan ID of this plan item.
    *
    * @return the plan ID of this plan item
    */
    public long getPlanId() {
        return _planItem.getPlanId();
    }

    /**
    * Sets the plan ID of this plan item.
    *
    * @param planId the plan ID of this plan item
    */
    public void setPlanId(long planId) {
        _planItem.setPlanId(planId);
    }

    /**
    * Returns the state of this plan item.
    *
    * @return the state of this plan item
    */
    public java.lang.String getState() {
        return _planItem.getState();
    }

    /**
    * Sets the state of this plan item.
    *
    * @param state the state of this plan item
    */
    public void setState(java.lang.String state) {
        _planItem.setState(state);
    }

    /**
    * Returns the updated of this plan item.
    *
    * @return the updated of this plan item
    */
    public java.util.Date getUpdated() {
        return _planItem.getUpdated();
    }

    /**
    * Sets the updated of this plan item.
    *
    * @param updated the updated of this plan item
    */
    public void setUpdated(java.util.Date updated) {
        _planItem.setUpdated(updated);
    }

    /**
    * Returns the update author ID of this plan item.
    *
    * @return the update author ID of this plan item
    */
    public long getUpdateAuthorId() {
        return _planItem.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan item.
    *
    * @param updateAuthorId the update author ID of this plan item
    */
    public void setUpdateAuthorId(long updateAuthorId) {
        _planItem.setUpdateAuthorId(updateAuthorId);
    }

    /**
    * Returns the update type of this plan item.
    *
    * @return the update type of this plan item
    */
    public java.lang.String getUpdateType() {
        return _planItem.getUpdateType();
    }

    /**
    * Sets the update type of this plan item.
    *
    * @param updateType the update type of this plan item
    */
    public void setUpdateType(java.lang.String updateType) {
        _planItem.setUpdateType(updateType);
    }

    /**
    * Returns the version of this plan item.
    *
    * @return the version of this plan item
    */
    public long getVersion() {
        return _planItem.getVersion();
    }

    /**
    * Sets the version of this plan item.
    *
    * @param version the version of this plan item
    */
    public void setVersion(long version) {
        _planItem.setVersion(version);
    }

    public boolean isNew() {
        return _planItem.isNew();
    }

    public void setNew(boolean n) {
        _planItem.setNew(n);
    }

    public boolean isCachedModel() {
        return _planItem.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planItem.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planItem.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planItem.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planItem.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planItem.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanItemWrapper((PlanItem) _planItem.clone());
    }

    public int compareTo(PlanItem planItem) {
        return _planItem.compareTo(planItem);
    }

    @Override
    public int hashCode() {
        return _planItem.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanItem> toCacheModel() {
        return _planItem.toCacheModel();
    }

    public PlanItem toEscapedModel() {
        return new PlanItemWrapper(_planItem.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planItem.toString();
    }

    public java.lang.String toXmlString() {
        return _planItem.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanItem getWrappedPlanItem() {
        return _planItem;
    }

    public PlanItem getWrappedModel() {
        return _planItem;
    }

    public void resetOriginalValues() {
        _planItem.resetOriginalValues();
    }
}

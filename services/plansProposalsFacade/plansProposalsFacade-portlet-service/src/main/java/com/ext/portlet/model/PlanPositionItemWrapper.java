package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositionItem}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPositionItem
 * @generated
 */
public class PlanPositionItemWrapper implements PlanPositionItem,
    ModelWrapper<PlanPositionItem> {
    private PlanPositionItem _planPositionItem;

    public PlanPositionItemWrapper(PlanPositionItem planPositionItem) {
        _planPositionItem = planPositionItem;
    }

    public Class<?> getModelClass() {
        return PlanPositionItem.class;
    }

    public String getModelClassName() {
        return PlanPositionItem.class.getName();
    }

    /**
    * Returns the primary key of this plan position item.
    *
    * @return the primary key of this plan position item
    */
    public com.ext.portlet.service.persistence.PlanPositionItemPK getPrimaryKey() {
        return _planPositionItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan position item.
    *
    * @param primaryKey the primary key of this plan position item
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanPositionItemPK primaryKey) {
        _planPositionItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan positions ID of this plan position item.
    *
    * @return the plan positions ID of this plan position item
    */
    public long getPlanPositionsId() {
        return _planPositionItem.getPlanPositionsId();
    }

    /**
    * Sets the plan positions ID of this plan position item.
    *
    * @param planPositionsId the plan positions ID of this plan position item
    */
    public void setPlanPositionsId(long planPositionsId) {
        _planPositionItem.setPlanPositionsId(planPositionsId);
    }

    /**
    * Returns the position ID of this plan position item.
    *
    * @return the position ID of this plan position item
    */
    public long getPositionId() {
        return _planPositionItem.getPositionId();
    }

    /**
    * Sets the position ID of this plan position item.
    *
    * @param positionId the position ID of this plan position item
    */
    public void setPositionId(long positionId) {
        _planPositionItem.setPositionId(positionId);
    }

    public boolean isNew() {
        return _planPositionItem.isNew();
    }

    public void setNew(boolean n) {
        _planPositionItem.setNew(n);
    }

    public boolean isCachedModel() {
        return _planPositionItem.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planPositionItem.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planPositionItem.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planPositionItem.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planPositionItem.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planPositionItem.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planPositionItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanPositionItemWrapper((PlanPositionItem) _planPositionItem.clone());
    }

    public int compareTo(PlanPositionItem planPositionItem) {
        return _planPositionItem.compareTo(planPositionItem);
    }

    @Override
    public int hashCode() {
        return _planPositionItem.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanPositionItem> toCacheModel() {
        return _planPositionItem.toCacheModel();
    }

    public PlanPositionItem toEscapedModel() {
        return new PlanPositionItemWrapper(_planPositionItem.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planPositionItem.toString();
    }

    public java.lang.String toXmlString() {
        return _planPositionItem.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPositionItem.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanPositionItem getWrappedPlanPositionItem() {
        return _planPositionItem;
    }

    public PlanPositionItem getWrappedModel() {
        return _planPositionItem;
    }

    public void resetOriginalValues() {
        _planPositionItem.resetOriginalValues();
    }
}

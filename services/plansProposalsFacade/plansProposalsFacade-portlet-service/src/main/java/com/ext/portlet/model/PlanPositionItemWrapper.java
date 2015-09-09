package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositionItem}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItem
 * @generated
 */
public class PlanPositionItemWrapper implements PlanPositionItem,
    ModelWrapper<PlanPositionItem> {
    private PlanPositionItem _planPositionItem;

    public PlanPositionItemWrapper(PlanPositionItem planPositionItem) {
        _planPositionItem = planPositionItem;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanPositionItem.class;
    }

    @Override
    public String getModelClassName() {
        return PlanPositionItem.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planPositionsId", getPlanPositionsId());
        attributes.put("positionId", getPositionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planPositionsId = (Long) attributes.get("planPositionsId");

        if (planPositionsId != null) {
            setPlanPositionsId(planPositionsId);
        }

        Long positionId = (Long) attributes.get("positionId");

        if (positionId != null) {
            setPositionId(positionId);
        }
    }

    /**
    * Returns the primary key of this plan position item.
    *
    * @return the primary key of this plan position item
    */
    @Override
    public com.ext.portlet.service.persistence.PlanPositionItemPK getPrimaryKey() {
        return _planPositionItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan position item.
    *
    * @param primaryKey the primary key of this plan position item
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanPositionItemPK primaryKey) {
        _planPositionItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan positions ID of this plan position item.
    *
    * @return the plan positions ID of this plan position item
    */
    @Override
    public long getPlanPositionsId() {
        return _planPositionItem.getPlanPositionsId();
    }

    /**
    * Sets the plan positions ID of this plan position item.
    *
    * @param planPositionsId the plan positions ID of this plan position item
    */
    @Override
    public void setPlanPositionsId(long planPositionsId) {
        _planPositionItem.setPlanPositionsId(planPositionsId);
    }

    /**
    * Returns the position ID of this plan position item.
    *
    * @return the position ID of this plan position item
    */
    @Override
    public long getPositionId() {
        return _planPositionItem.getPositionId();
    }

    /**
    * Sets the position ID of this plan position item.
    *
    * @param positionId the position ID of this plan position item
    */
    @Override
    public void setPositionId(long positionId) {
        _planPositionItem.setPositionId(positionId);
    }

    @Override
    public boolean isNew() {
        return _planPositionItem.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planPositionItem.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planPositionItem.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planPositionItem.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planPositionItem.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planPositionItem.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planPositionItem.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planPositionItem.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planPositionItem.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planPositionItem.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planPositionItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanPositionItemWrapper((PlanPositionItem) _planPositionItem.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.PlanPositionItem planPositionItem) {
        return _planPositionItem.compareTo(planPositionItem);
    }

    @Override
    public int hashCode() {
        return _planPositionItem.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanPositionItem> toCacheModel() {
        return _planPositionItem.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanPositionItem toEscapedModel() {
        return new PlanPositionItemWrapper(_planPositionItem.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanPositionItem toUnescapedModel() {
        return new PlanPositionItemWrapper(_planPositionItem.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planPositionItem.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planPositionItem.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPositionItem.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanPositionItemWrapper)) {
            return false;
        }

        PlanPositionItemWrapper planPositionItemWrapper = (PlanPositionItemWrapper) obj;

        if (Validator.equals(_planPositionItem,
                    planPositionItemWrapper._planPositionItem)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanPositionItem getWrappedPlanPositionItem() {
        return _planPositionItem;
    }

    @Override
    public PlanPositionItem getWrappedModel() {
        return _planPositionItem;
    }

    @Override
    public void resetOriginalValues() {
        _planPositionItem.resetOriginalValues();
    }
}

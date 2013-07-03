package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItemGroup}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItemGroup
 * @generated
 */
public class PlanItemGroupWrapper implements PlanItemGroup,
    ModelWrapper<PlanItemGroup> {
    private PlanItemGroup _planItemGroup;

    public PlanItemGroupWrapper(PlanItemGroup planItemGroup) {
        _planItemGroup = planItemGroup;
    }

    public Class<?> getModelClass() {
        return PlanItemGroup.class;
    }

    public String getModelClassName() {
        return PlanItemGroup.class.getName();
    }

    /**
    * Returns the primary key of this plan item group.
    *
    * @return the primary key of this plan item group
    */
    public long getPrimaryKey() {
        return _planItemGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan item group.
    *
    * @param primaryKey the primary key of this plan item group
    */
    public void setPrimaryKey(long primaryKey) {
        _planItemGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan ID of this plan item group.
    *
    * @return the plan ID of this plan item group
    */
    public long getPlanId() {
        return _planItemGroup.getPlanId();
    }

    /**
    * Sets the plan ID of this plan item group.
    *
    * @param planId the plan ID of this plan item group
    */
    public void setPlanId(long planId) {
        _planItemGroup.setPlanId(planId);
    }

    /**
    * Returns the group ID of this plan item group.
    *
    * @return the group ID of this plan item group
    */
    public long getGroupId() {
        return _planItemGroup.getGroupId();
    }

    /**
    * Sets the group ID of this plan item group.
    *
    * @param groupId the group ID of this plan item group
    */
    public void setGroupId(long groupId) {
        _planItemGroup.setGroupId(groupId);
    }

    public boolean isNew() {
        return _planItemGroup.isNew();
    }

    public void setNew(boolean n) {
        _planItemGroup.setNew(n);
    }

    public boolean isCachedModel() {
        return _planItemGroup.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planItemGroup.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planItemGroup.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planItemGroup.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planItemGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planItemGroup.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planItemGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanItemGroupWrapper((PlanItemGroup) _planItemGroup.clone());
    }

    public int compareTo(PlanItemGroup planItemGroup) {
        return _planItemGroup.compareTo(planItemGroup);
    }

    @Override
    public int hashCode() {
        return _planItemGroup.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanItemGroup> toCacheModel() {
        return _planItemGroup.toCacheModel();
    }

    public PlanItemGroup toEscapedModel() {
        return new PlanItemGroupWrapper(_planItemGroup.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planItemGroup.toString();
    }

    public java.lang.String toXmlString() {
        return _planItemGroup.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemGroup.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanItemGroup getWrappedPlanItemGroup() {
        return _planItemGroup;
    }

    public PlanItemGroup getWrappedModel() {
        return _planItemGroup;
    }

    public void resetOriginalValues() {
        _planItemGroup.resetOriginalValues();
    }
}

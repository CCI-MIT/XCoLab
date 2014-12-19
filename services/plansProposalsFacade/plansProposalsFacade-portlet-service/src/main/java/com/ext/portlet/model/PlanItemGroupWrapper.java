package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanItemGroup}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemGroup
 * @generated
 */
public class PlanItemGroupWrapper implements PlanItemGroup,
    ModelWrapper<PlanItemGroup> {
    private PlanItemGroup _planItemGroup;

    public PlanItemGroupWrapper(PlanItemGroup planItemGroup) {
        _planItemGroup = planItemGroup;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanItemGroup.class;
    }

    @Override
    public String getModelClassName() {
        return PlanItemGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planId", getPlanId());
        attributes.put("groupId", getGroupId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long groupId = (Long) attributes.get("groupId");

        if (groupId != null) {
            setGroupId(groupId);
        }
    }

    /**
    * Returns the primary key of this plan item group.
    *
    * @return the primary key of this plan item group
    */
    @Override
    public long getPrimaryKey() {
        return _planItemGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan item group.
    *
    * @param primaryKey the primary key of this plan item group
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planItemGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the plan ID of this plan item group.
    *
    * @return the plan ID of this plan item group
    */
    @Override
    public long getPlanId() {
        return _planItemGroup.getPlanId();
    }

    /**
    * Sets the plan ID of this plan item group.
    *
    * @param planId the plan ID of this plan item group
    */
    @Override
    public void setPlanId(long planId) {
        _planItemGroup.setPlanId(planId);
    }

    /**
    * Returns the group ID of this plan item group.
    *
    * @return the group ID of this plan item group
    */
    @Override
    public long getGroupId() {
        return _planItemGroup.getGroupId();
    }

    /**
    * Sets the group ID of this plan item group.
    *
    * @param groupId the group ID of this plan item group
    */
    @Override
    public void setGroupId(long groupId) {
        _planItemGroup.setGroupId(groupId);
    }

    @Override
    public boolean isNew() {
        return _planItemGroup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planItemGroup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planItemGroup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planItemGroup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planItemGroup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planItemGroup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planItemGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planItemGroup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planItemGroup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planItemGroup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planItemGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanItemGroupWrapper((PlanItemGroup) _planItemGroup.clone());
    }

    @Override
    public int compareTo(PlanItemGroup planItemGroup) {
        return _planItemGroup.compareTo(planItemGroup);
    }

    @Override
    public int hashCode() {
        return _planItemGroup.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanItemGroup> toCacheModel() {
        return _planItemGroup.toCacheModel();
    }

    @Override
    public PlanItemGroup toEscapedModel() {
        return new PlanItemGroupWrapper(_planItemGroup.toEscapedModel());
    }

    @Override
    public PlanItemGroup toUnescapedModel() {
        return new PlanItemGroupWrapper(_planItemGroup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planItemGroup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planItemGroup.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemGroup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanItemGroupWrapper)) {
            return false;
        }

        PlanItemGroupWrapper planItemGroupWrapper = (PlanItemGroupWrapper) obj;

        if (Validator.equals(_planItemGroup, planItemGroupWrapper._planItemGroup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanItemGroup getWrappedPlanItemGroup() {
        return _planItemGroup;
    }

    @Override
    public PlanItemGroup getWrappedModel() {
        return _planItemGroup;
    }

    @Override
    public void resetOriginalValues() {
        _planItemGroup.resetOriginalValues();
    }
}

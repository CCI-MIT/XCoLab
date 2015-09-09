package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanItem}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItem
 * @generated
 */
public class PlanItemWrapper implements PlanItem, ModelWrapper<PlanItem> {
    private PlanItem _planItem;

    public PlanItemWrapper(PlanItem planItem) {
        _planItem = planItem;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanItem.class;
    }

    @Override
    public String getModelClassName() {
        return PlanItem.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("planId", getPlanId());
        attributes.put("state", getState());
        attributes.put("updated", getUpdated());
        attributes.put("updateAuthorId", getUpdateAuthorId());
        attributes.put("updateType", getUpdateType());
        attributes.put("version", getVersion());

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

        String state = (String) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        Date updated = (Date) attributes.get("updated");

        if (updated != null) {
            setUpdated(updated);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }

        String updateType = (String) attributes.get("updateType");

        if (updateType != null) {
            setUpdateType(updateType);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }
    }

    /**
    * Returns the primary key of this plan item.
    *
    * @return the primary key of this plan item
    */
    @Override
    public long getPrimaryKey() {
        return _planItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan item.
    *
    * @param primaryKey the primary key of this plan item
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan item.
    *
    * @return the ID of this plan item
    */
    @Override
    public long getId() {
        return _planItem.getId();
    }

    /**
    * Sets the ID of this plan item.
    *
    * @param id the ID of this plan item
    */
    @Override
    public void setId(long id) {
        _planItem.setId(id);
    }

    /**
    * Returns the plan ID of this plan item.
    *
    * @return the plan ID of this plan item
    */
    @Override
    public long getPlanId() {
        return _planItem.getPlanId();
    }

    /**
    * Sets the plan ID of this plan item.
    *
    * @param planId the plan ID of this plan item
    */
    @Override
    public void setPlanId(long planId) {
        _planItem.setPlanId(planId);
    }

    /**
    * Returns the state of this plan item.
    *
    * @return the state of this plan item
    */
    @Override
    public java.lang.String getState() {
        return _planItem.getState();
    }

    /**
    * Sets the state of this plan item.
    *
    * @param state the state of this plan item
    */
    @Override
    public void setState(java.lang.String state) {
        _planItem.setState(state);
    }

    /**
    * Returns the updated of this plan item.
    *
    * @return the updated of this plan item
    */
    @Override
    public java.util.Date getUpdated() {
        return _planItem.getUpdated();
    }

    /**
    * Sets the updated of this plan item.
    *
    * @param updated the updated of this plan item
    */
    @Override
    public void setUpdated(java.util.Date updated) {
        _planItem.setUpdated(updated);
    }

    /**
    * Returns the update author ID of this plan item.
    *
    * @return the update author ID of this plan item
    */
    @Override
    public long getUpdateAuthorId() {
        return _planItem.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan item.
    *
    * @param updateAuthorId the update author ID of this plan item
    */
    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _planItem.setUpdateAuthorId(updateAuthorId);
    }

    /**
    * Returns the update type of this plan item.
    *
    * @return the update type of this plan item
    */
    @Override
    public java.lang.String getUpdateType() {
        return _planItem.getUpdateType();
    }

    /**
    * Sets the update type of this plan item.
    *
    * @param updateType the update type of this plan item
    */
    @Override
    public void setUpdateType(java.lang.String updateType) {
        _planItem.setUpdateType(updateType);
    }

    /**
    * Returns the version of this plan item.
    *
    * @return the version of this plan item
    */
    @Override
    public long getVersion() {
        return _planItem.getVersion();
    }

    /**
    * Sets the version of this plan item.
    *
    * @param version the version of this plan item
    */
    @Override
    public void setVersion(long version) {
        _planItem.setVersion(version);
    }

    @Override
    public boolean isNew() {
        return _planItem.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planItem.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planItem.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planItem.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planItem.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planItem.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planItem.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planItem.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planItem.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planItem.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanItemWrapper((PlanItem) _planItem.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlanItem planItem) {
        return _planItem.compareTo(planItem);
    }

    @Override
    public int hashCode() {
        return _planItem.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanItem> toCacheModel() {
        return _planItem.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanItem toEscapedModel() {
        return new PlanItemWrapper(_planItem.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanItem toUnescapedModel() {
        return new PlanItemWrapper(_planItem.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planItem.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planItem.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanItemWrapper)) {
            return false;
        }

        PlanItemWrapper planItemWrapper = (PlanItemWrapper) obj;

        if (Validator.equals(_planItem, planItemWrapper._planItem)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanItem getWrappedPlanItem() {
        return _planItem;
    }

    @Override
    public PlanItem getWrappedModel() {
        return _planItem;
    }

    @Override
    public void resetOriginalValues() {
        _planItem.resetOriginalValues();
    }
}

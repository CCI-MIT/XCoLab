package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanTeamHistory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistory
 * @generated
 */
public class PlanTeamHistoryWrapper implements PlanTeamHistory,
    ModelWrapper<PlanTeamHistory> {
    private PlanTeamHistory _planTeamHistory;

    public PlanTeamHistoryWrapper(PlanTeamHistory planTeamHistory) {
        _planTeamHistory = planTeamHistory;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTeamHistory.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTeamHistory.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("planId", getPlanId());
        attributes.put("userId", getUserId());
        attributes.put("action", getAction());
        attributes.put("payload", getPayload());
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

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String action = (String) attributes.get("action");

        if (action != null) {
            setAction(action);
        }

        String payload = (String) attributes.get("payload");

        if (payload != null) {
            setPayload(payload);
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
    * Returns the primary key of this plan team history.
    *
    * @return the primary key of this plan team history
    */
    @Override
    public long getPrimaryKey() {
        return _planTeamHistory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan team history.
    *
    * @param primaryKey the primary key of this plan team history
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planTeamHistory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan team history.
    *
    * @return the ID of this plan team history
    */
    @Override
    public long getId() {
        return _planTeamHistory.getId();
    }

    /**
    * Sets the ID of this plan team history.
    *
    * @param id the ID of this plan team history
    */
    @Override
    public void setId(long id) {
        _planTeamHistory.setId(id);
    }

    /**
    * Returns the plan ID of this plan team history.
    *
    * @return the plan ID of this plan team history
    */
    @Override
    public long getPlanId() {
        return _planTeamHistory.getPlanId();
    }

    /**
    * Sets the plan ID of this plan team history.
    *
    * @param planId the plan ID of this plan team history
    */
    @Override
    public void setPlanId(long planId) {
        _planTeamHistory.setPlanId(planId);
    }

    /**
    * Returns the user ID of this plan team history.
    *
    * @return the user ID of this plan team history
    */
    @Override
    public long getUserId() {
        return _planTeamHistory.getUserId();
    }

    /**
    * Sets the user ID of this plan team history.
    *
    * @param userId the user ID of this plan team history
    */
    @Override
    public void setUserId(long userId) {
        _planTeamHistory.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plan team history.
    *
    * @return the user uuid of this plan team history
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistory.getUserUuid();
    }

    /**
    * Sets the user uuid of this plan team history.
    *
    * @param userUuid the user uuid of this plan team history
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _planTeamHistory.setUserUuid(userUuid);
    }

    /**
    * Returns the action of this plan team history.
    *
    * @return the action of this plan team history
    */
    @Override
    public java.lang.String getAction() {
        return _planTeamHistory.getAction();
    }

    /**
    * Sets the action of this plan team history.
    *
    * @param action the action of this plan team history
    */
    @Override
    public void setAction(java.lang.String action) {
        _planTeamHistory.setAction(action);
    }

    /**
    * Returns the payload of this plan team history.
    *
    * @return the payload of this plan team history
    */
    @Override
    public java.lang.String getPayload() {
        return _planTeamHistory.getPayload();
    }

    /**
    * Sets the payload of this plan team history.
    *
    * @param payload the payload of this plan team history
    */
    @Override
    public void setPayload(java.lang.String payload) {
        _planTeamHistory.setPayload(payload);
    }

    /**
    * Returns the created of this plan team history.
    *
    * @return the created of this plan team history
    */
    @Override
    public java.util.Date getCreated() {
        return _planTeamHistory.getCreated();
    }

    /**
    * Sets the created of this plan team history.
    *
    * @param created the created of this plan team history
    */
    @Override
    public void setCreated(java.util.Date created) {
        _planTeamHistory.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan team history.
    *
    * @return the update author ID of this plan team history
    */
    @Override
    public long getUpdateAuthorId() {
        return _planTeamHistory.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan team history.
    *
    * @param updateAuthorId the update author ID of this plan team history
    */
    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _planTeamHistory.setUpdateAuthorId(updateAuthorId);
    }

    @Override
    public boolean isNew() {
        return _planTeamHistory.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planTeamHistory.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planTeamHistory.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planTeamHistory.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planTeamHistory.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planTeamHistory.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTeamHistory.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTeamHistory.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planTeamHistory.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planTeamHistory.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTeamHistory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTeamHistoryWrapper((PlanTeamHistory) _planTeamHistory.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlanTeamHistory planTeamHistory) {
        return _planTeamHistory.compareTo(planTeamHistory);
    }

    @Override
    public int hashCode() {
        return _planTeamHistory.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanTeamHistory> toCacheModel() {
        return _planTeamHistory.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanTeamHistory toEscapedModel() {
        return new PlanTeamHistoryWrapper(_planTeamHistory.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanTeamHistory toUnescapedModel() {
        return new PlanTeamHistoryWrapper(_planTeamHistory.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTeamHistory.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planTeamHistory.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTeamHistory.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanTeamHistoryWrapper)) {
            return false;
        }

        PlanTeamHistoryWrapper planTeamHistoryWrapper = (PlanTeamHistoryWrapper) obj;

        if (Validator.equals(_planTeamHistory,
                    planTeamHistoryWrapper._planTeamHistory)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanTeamHistory getWrappedPlanTeamHistory() {
        return _planTeamHistory;
    }

    @Override
    public PlanTeamHistory getWrappedModel() {
        return _planTeamHistory;
    }

    @Override
    public void resetOriginalValues() {
        _planTeamHistory.resetOriginalValues();
    }
}

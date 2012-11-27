package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTeamHistory}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTeamHistory
 * @generated
 */
public class PlanTeamHistoryWrapper implements PlanTeamHistory,
    ModelWrapper<PlanTeamHistory> {
    private PlanTeamHistory _planTeamHistory;

    public PlanTeamHistoryWrapper(PlanTeamHistory planTeamHistory) {
        _planTeamHistory = planTeamHistory;
    }

    public Class<?> getModelClass() {
        return PlanTeamHistory.class;
    }

    public String getModelClassName() {
        return PlanTeamHistory.class.getName();
    }

    /**
    * Returns the primary key of this plan team history.
    *
    * @return the primary key of this plan team history
    */
    public java.lang.Long getPrimaryKey() {
        return _planTeamHistory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan team history.
    *
    * @param primaryKey the primary key of this plan team history
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _planTeamHistory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan team history.
    *
    * @return the ID of this plan team history
    */
    public java.lang.Long getId() {
        return _planTeamHistory.getId();
    }

    /**
    * Sets the ID of this plan team history.
    *
    * @param id the ID of this plan team history
    */
    public void setId(java.lang.Long id) {
        _planTeamHistory.setId(id);
    }

    /**
    * Returns the plan ID of this plan team history.
    *
    * @return the plan ID of this plan team history
    */
    public java.lang.Long getPlanId() {
        return _planTeamHistory.getPlanId();
    }

    /**
    * Sets the plan ID of this plan team history.
    *
    * @param planId the plan ID of this plan team history
    */
    public void setPlanId(java.lang.Long planId) {
        _planTeamHistory.setPlanId(planId);
    }

    /**
    * Returns the user ID of this plan team history.
    *
    * @return the user ID of this plan team history
    */
    public java.lang.Long getUserId() {
        return _planTeamHistory.getUserId();
    }

    /**
    * Sets the user ID of this plan team history.
    *
    * @param userId the user ID of this plan team history
    */
    public void setUserId(java.lang.Long userId) {
        _planTeamHistory.setUserId(userId);
    }

    /**
    * Returns the action of this plan team history.
    *
    * @return the action of this plan team history
    */
    public java.lang.String getAction() {
        return _planTeamHistory.getAction();
    }

    /**
    * Sets the action of this plan team history.
    *
    * @param action the action of this plan team history
    */
    public void setAction(java.lang.String action) {
        _planTeamHistory.setAction(action);
    }

    /**
    * Returns the payload of this plan team history.
    *
    * @return the payload of this plan team history
    */
    public java.lang.String getPayload() {
        return _planTeamHistory.getPayload();
    }

    /**
    * Sets the payload of this plan team history.
    *
    * @param payload the payload of this plan team history
    */
    public void setPayload(java.lang.String payload) {
        _planTeamHistory.setPayload(payload);
    }

    /**
    * Returns the created of this plan team history.
    *
    * @return the created of this plan team history
    */
    public java.util.Date getCreated() {
        return _planTeamHistory.getCreated();
    }

    /**
    * Sets the created of this plan team history.
    *
    * @param created the created of this plan team history
    */
    public void setCreated(java.util.Date created) {
        _planTeamHistory.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan team history.
    *
    * @return the update author ID of this plan team history
    */
    public java.lang.Long getUpdateAuthorId() {
        return _planTeamHistory.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan team history.
    *
    * @param updateAuthorId the update author ID of this plan team history
    */
    public void setUpdateAuthorId(java.lang.Long updateAuthorId) {
        _planTeamHistory.setUpdateAuthorId(updateAuthorId);
    }

    public boolean isNew() {
        return _planTeamHistory.isNew();
    }

    public void setNew(boolean n) {
        _planTeamHistory.setNew(n);
    }

    public boolean isCachedModel() {
        return _planTeamHistory.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planTeamHistory.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planTeamHistory.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planTeamHistory.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planTeamHistory.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planTeamHistory.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planTeamHistory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanTeamHistoryWrapper((PlanTeamHistory) _planTeamHistory.clone());
    }

    public int compareTo(PlanTeamHistory planTeamHistory) {
        return _planTeamHistory.compareTo(planTeamHistory);
    }

    @Override
    public int hashCode() {
        return _planTeamHistory.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanTeamHistory> toCacheModel() {
        return _planTeamHistory.toCacheModel();
    }

    public PlanTeamHistory toEscapedModel() {
        return new PlanTeamHistoryWrapper(_planTeamHistory.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planTeamHistory.toString();
    }

    public java.lang.String toXmlString() {
        return _planTeamHistory.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTeamHistory.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTeamHistory.store();
    }

    public com.liferay.portal.model.User getUser()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistory.getUser();
    }

    public com.ext.portlet.plans.model.PlanItem getPlan()
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistory.getPlan();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanTeamHistory getWrappedPlanTeamHistory() {
        return _planTeamHistory;
    }

    public PlanTeamHistory getWrappedModel() {
        return _planTeamHistory;
    }

    public void resetOriginalValues() {
        _planTeamHistory.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanMeta}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanMeta
 * @generated
 */
public class PlanMetaWrapper implements PlanMeta, ModelWrapper<PlanMeta> {
    private PlanMeta _planMeta;

    public PlanMetaWrapper(PlanMeta planMeta) {
        _planMeta = planMeta;
    }

    public Class<?> getModelClass() {
        return PlanMeta.class;
    }

    public String getModelClassName() {
        return PlanMeta.class.getName();
    }

    /**
    * Returns the primary key of this plan meta.
    *
    * @return the primary key of this plan meta
    */
    public long getPrimaryKey() {
        return _planMeta.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan meta.
    *
    * @param primaryKey the primary key of this plan meta
    */
    public void setPrimaryKey(long primaryKey) {
        _planMeta.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan meta.
    *
    * @return the ID of this plan meta
    */
    public long getId() {
        return _planMeta.getId();
    }

    /**
    * Sets the ID of this plan meta.
    *
    * @param id the ID of this plan meta
    */
    public void setId(long id) {
        _planMeta.setId(id);
    }

    /**
    * Returns the plan ID of this plan meta.
    *
    * @return the plan ID of this plan meta
    */
    public long getPlanId() {
        return _planMeta.getPlanId();
    }

    /**
    * Sets the plan ID of this plan meta.
    *
    * @param planId the plan ID of this plan meta
    */
    public void setPlanId(long planId) {
        _planMeta.setPlanId(planId);
    }

    /**
    * Returns the plan type ID of this plan meta.
    *
    * @return the plan type ID of this plan meta
    */
    public long getPlanTypeId() {
        return _planMeta.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan meta.
    *
    * @param planTypeId the plan type ID of this plan meta
    */
    public void setPlanTypeId(long planTypeId) {
        _planMeta.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the plan created of this plan meta.
    *
    * @return the plan created of this plan meta
    */
    public long getPlanCreated() {
        return _planMeta.getPlanCreated();
    }

    /**
    * Sets the plan created of this plan meta.
    *
    * @param planCreated the plan created of this plan meta
    */
    public void setPlanCreated(long planCreated) {
        _planMeta.setPlanCreated(planCreated);
    }

    /**
    * Returns the author ID of this plan meta.
    *
    * @return the author ID of this plan meta
    */
    public long getAuthorId() {
        return _planMeta.getAuthorId();
    }

    /**
    * Sets the author ID of this plan meta.
    *
    * @param authorId the author ID of this plan meta
    */
    public void setAuthorId(long authorId) {
        _planMeta.setAuthorId(authorId);
    }

    /**
    * Returns the votes of this plan meta.
    *
    * @return the votes of this plan meta
    */
    public int getVotes() {
        return _planMeta.getVotes();
    }

    /**
    * Sets the votes of this plan meta.
    *
    * @param votes the votes of this plan meta
    */
    public void setVotes(int votes) {
        _planMeta.setVotes(votes);
    }

    /**
    * Returns the plan group ID of this plan meta.
    *
    * @return the plan group ID of this plan meta
    */
    public long getPlanGroupId() {
        return _planMeta.getPlanGroupId();
    }

    /**
    * Sets the plan group ID of this plan meta.
    *
    * @param planGroupId the plan group ID of this plan meta
    */
    public void setPlanGroupId(long planGroupId) {
        _planMeta.setPlanGroupId(planGroupId);
    }

    /**
    * Returns the open of this plan meta.
    *
    * @return the open of this plan meta
    */
    public boolean getOpen() {
        return _planMeta.getOpen();
    }

    /**
    * Returns <code>true</code> if this plan meta is open.
    *
    * @return <code>true</code> if this plan meta is open; <code>false</code> otherwise
    */
    public boolean isOpen() {
        return _planMeta.isOpen();
    }

    /**
    * Sets whether this plan meta is open.
    *
    * @param open the open of this plan meta
    */
    public void setOpen(boolean open) {
        _planMeta.setOpen(open);
    }

    /**
    * Returns the status of this plan meta.
    *
    * @return the status of this plan meta
    */
    public java.lang.String getStatus() {
        return _planMeta.getStatus();
    }

    /**
    * Sets the status of this plan meta.
    *
    * @param status the status of this plan meta
    */
    public void setStatus(java.lang.String status) {
        _planMeta.setStatus(status);
    }

    /**
    * Returns the mb category ID of this plan meta.
    *
    * @return the mb category ID of this plan meta
    */
    public long getMbCategoryId() {
        return _planMeta.getMbCategoryId();
    }

    /**
    * Sets the mb category ID of this plan meta.
    *
    * @param mbCategoryId the mb category ID of this plan meta
    */
    public void setMbCategoryId(long mbCategoryId) {
        _planMeta.setMbCategoryId(mbCategoryId);
    }

    /**
    * Returns the category group ID of this plan meta.
    *
    * @return the category group ID of this plan meta
    */
    public long getCategoryGroupId() {
        return _planMeta.getCategoryGroupId();
    }

    /**
    * Sets the category group ID of this plan meta.
    *
    * @param categoryGroupId the category group ID of this plan meta
    */
    public void setCategoryGroupId(long categoryGroupId) {
        _planMeta.setCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the version of this plan meta.
    *
    * @return the version of this plan meta
    */
    public long getVersion() {
        return _planMeta.getVersion();
    }

    /**
    * Sets the version of this plan meta.
    *
    * @param version the version of this plan meta
    */
    public void setVersion(long version) {
        _planMeta.setVersion(version);
    }

    /**
    * Returns the plan version of this plan meta.
    *
    * @return the plan version of this plan meta
    */
    public long getPlanVersion() {
        return _planMeta.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan meta.
    *
    * @param planVersion the plan version of this plan meta
    */
    public void setPlanVersion(long planVersion) {
        _planMeta.setPlanVersion(planVersion);
    }

    /**
    * Returns the created of this plan meta.
    *
    * @return the created of this plan meta
    */
    public java.util.Date getCreated() {
        return _planMeta.getCreated();
    }

    /**
    * Sets the created of this plan meta.
    *
    * @param created the created of this plan meta
    */
    public void setCreated(java.util.Date created) {
        _planMeta.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan meta.
    *
    * @return the update author ID of this plan meta
    */
    public long getUpdateAuthorId() {
        return _planMeta.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan meta.
    *
    * @param updateAuthorId the update author ID of this plan meta
    */
    public void setUpdateAuthorId(long updateAuthorId) {
        _planMeta.setUpdateAuthorId(updateAuthorId);
    }

    /**
    * Returns the model ID of this plan meta.
    *
    * @return the model ID of this plan meta
    */
    public long getModelId() {
        return _planMeta.getModelId();
    }

    /**
    * Sets the model ID of this plan meta.
    *
    * @param modelId the model ID of this plan meta
    */
    public void setModelId(long modelId) {
        _planMeta.setModelId(modelId);
    }

    /**
    * Returns the promoted of this plan meta.
    *
    * @return the promoted of this plan meta
    */
    public boolean getPromoted() {
        return _planMeta.getPromoted();
    }

    /**
    * Returns <code>true</code> if this plan meta is promoted.
    *
    * @return <code>true</code> if this plan meta is promoted; <code>false</code> otherwise
    */
    public boolean isPromoted() {
        return _planMeta.isPromoted();
    }

    /**
    * Sets whether this plan meta is promoted.
    *
    * @param promoted the promoted of this plan meta
    */
    public void setPromoted(boolean promoted) {
        _planMeta.setPromoted(promoted);
    }

    /**
    * Returns the previous contest phase of this plan meta.
    *
    * @return the previous contest phase of this plan meta
    */
    public long getPreviousContestPhase() {
        return _planMeta.getPreviousContestPhase();
    }

    /**
    * Sets the previous contest phase of this plan meta.
    *
    * @param previousContestPhase the previous contest phase of this plan meta
    */
    public void setPreviousContestPhase(long previousContestPhase) {
        _planMeta.setPreviousContestPhase(previousContestPhase);
    }

    /**
    * Returns the contest phase of this plan meta.
    *
    * @return the contest phase of this plan meta
    */
    public long getContestPhase() {
        return _planMeta.getContestPhase();
    }

    /**
    * Sets the contest phase of this plan meta.
    *
    * @param contestPhase the contest phase of this plan meta
    */
    public void setContestPhase(long contestPhase) {
        _planMeta.setContestPhase(contestPhase);
    }

    public boolean isNew() {
        return _planMeta.isNew();
    }

    public void setNew(boolean n) {
        _planMeta.setNew(n);
    }

    public boolean isCachedModel() {
        return _planMeta.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planMeta.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planMeta.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planMeta.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planMeta.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planMeta.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planMeta.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanMetaWrapper((PlanMeta) _planMeta.clone());
    }

    public int compareTo(PlanMeta planMeta) {
        return _planMeta.compareTo(planMeta);
    }

    @Override
    public int hashCode() {
        return _planMeta.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanMeta> toCacheModel() {
        return _planMeta.toCacheModel();
    }

    public PlanMeta toEscapedModel() {
        return new PlanMetaWrapper(_planMeta.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planMeta.toString();
    }

    public java.lang.String toXmlString() {
        return _planMeta.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planMeta.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanMeta getWrappedPlanMeta() {
        return _planMeta;
    }

    public PlanMeta getWrappedModel() {
        return _planMeta;
    }

    public void resetOriginalValues() {
        _planMeta.resetOriginalValues();
    }
}

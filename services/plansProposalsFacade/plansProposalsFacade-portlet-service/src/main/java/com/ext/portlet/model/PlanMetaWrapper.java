package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanMeta}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanMeta
 * @generated
 */
public class PlanMetaWrapper implements PlanMeta, ModelWrapper<PlanMeta> {
    private PlanMeta _planMeta;

    public PlanMetaWrapper(PlanMeta planMeta) {
        _planMeta = planMeta;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanMeta.class;
    }

    @Override
    public String getModelClassName() {
        return PlanMeta.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("planId", getPlanId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("planCreated", getPlanCreated());
        attributes.put("authorId", getAuthorId());
        attributes.put("votes", getVotes());
        attributes.put("planGroupId", getPlanGroupId());
        attributes.put("open", getOpen());
        attributes.put("status", getStatus());
        attributes.put("mbCategoryId", getMbCategoryId());
        attributes.put("categoryGroupId", getCategoryGroupId());
        attributes.put("version", getVersion());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("created", getCreated());
        attributes.put("updateAuthorId", getUpdateAuthorId());
        attributes.put("modelId", getModelId());
        attributes.put("promoted", getPromoted());
        attributes.put("previousContestPhase", getPreviousContestPhase());
        attributes.put("contestPhase", getContestPhase());

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

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        Long planCreated = (Long) attributes.get("planCreated");

        if (planCreated != null) {
            setPlanCreated(planCreated);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        Integer votes = (Integer) attributes.get("votes");

        if (votes != null) {
            setVotes(votes);
        }

        Long planGroupId = (Long) attributes.get("planGroupId");

        if (planGroupId != null) {
            setPlanGroupId(planGroupId);
        }

        Boolean open = (Boolean) attributes.get("open");

        if (open != null) {
            setOpen(open);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Long mbCategoryId = (Long) attributes.get("mbCategoryId");

        if (mbCategoryId != null) {
            setMbCategoryId(mbCategoryId);
        }

        Long categoryGroupId = (Long) attributes.get("categoryGroupId");

        if (categoryGroupId != null) {
            setCategoryGroupId(categoryGroupId);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Boolean promoted = (Boolean) attributes.get("promoted");

        if (promoted != null) {
            setPromoted(promoted);
        }

        Long previousContestPhase = (Long) attributes.get(
                "previousContestPhase");

        if (previousContestPhase != null) {
            setPreviousContestPhase(previousContestPhase);
        }

        Long contestPhase = (Long) attributes.get("contestPhase");

        if (contestPhase != null) {
            setContestPhase(contestPhase);
        }
    }

    /**
    * Returns the primary key of this plan meta.
    *
    * @return the primary key of this plan meta
    */
    @Override
    public long getPrimaryKey() {
        return _planMeta.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan meta.
    *
    * @param primaryKey the primary key of this plan meta
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planMeta.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan meta.
    *
    * @return the ID of this plan meta
    */
    @Override
    public long getId() {
        return _planMeta.getId();
    }

    /**
    * Sets the ID of this plan meta.
    *
    * @param id the ID of this plan meta
    */
    @Override
    public void setId(long id) {
        _planMeta.setId(id);
    }

    /**
    * Returns the plan ID of this plan meta.
    *
    * @return the plan ID of this plan meta
    */
    @Override
    public long getPlanId() {
        return _planMeta.getPlanId();
    }

    /**
    * Sets the plan ID of this plan meta.
    *
    * @param planId the plan ID of this plan meta
    */
    @Override
    public void setPlanId(long planId) {
        _planMeta.setPlanId(planId);
    }

    /**
    * Returns the plan type ID of this plan meta.
    *
    * @return the plan type ID of this plan meta
    */
    @Override
    public long getPlanTypeId() {
        return _planMeta.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plan meta.
    *
    * @param planTypeId the plan type ID of this plan meta
    */
    @Override
    public void setPlanTypeId(long planTypeId) {
        _planMeta.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the plan created of this plan meta.
    *
    * @return the plan created of this plan meta
    */
    @Override
    public long getPlanCreated() {
        return _planMeta.getPlanCreated();
    }

    /**
    * Sets the plan created of this plan meta.
    *
    * @param planCreated the plan created of this plan meta
    */
    @Override
    public void setPlanCreated(long planCreated) {
        _planMeta.setPlanCreated(planCreated);
    }

    /**
    * Returns the author ID of this plan meta.
    *
    * @return the author ID of this plan meta
    */
    @Override
    public long getAuthorId() {
        return _planMeta.getAuthorId();
    }

    /**
    * Sets the author ID of this plan meta.
    *
    * @param authorId the author ID of this plan meta
    */
    @Override
    public void setAuthorId(long authorId) {
        _planMeta.setAuthorId(authorId);
    }

    /**
    * Returns the votes of this plan meta.
    *
    * @return the votes of this plan meta
    */
    @Override
    public int getVotes() {
        return _planMeta.getVotes();
    }

    /**
    * Sets the votes of this plan meta.
    *
    * @param votes the votes of this plan meta
    */
    @Override
    public void setVotes(int votes) {
        _planMeta.setVotes(votes);
    }

    /**
    * Returns the plan group ID of this plan meta.
    *
    * @return the plan group ID of this plan meta
    */
    @Override
    public long getPlanGroupId() {
        return _planMeta.getPlanGroupId();
    }

    /**
    * Sets the plan group ID of this plan meta.
    *
    * @param planGroupId the plan group ID of this plan meta
    */
    @Override
    public void setPlanGroupId(long planGroupId) {
        _planMeta.setPlanGroupId(planGroupId);
    }

    /**
    * Returns the open of this plan meta.
    *
    * @return the open of this plan meta
    */
    @Override
    public boolean getOpen() {
        return _planMeta.getOpen();
    }

    /**
    * Returns <code>true</code> if this plan meta is open.
    *
    * @return <code>true</code> if this plan meta is open; <code>false</code> otherwise
    */
    @Override
    public boolean isOpen() {
        return _planMeta.isOpen();
    }

    /**
    * Sets whether this plan meta is open.
    *
    * @param open the open of this plan meta
    */
    @Override
    public void setOpen(boolean open) {
        _planMeta.setOpen(open);
    }

    /**
    * Returns the status of this plan meta.
    *
    * @return the status of this plan meta
    */
    @Override
    public java.lang.String getStatus() {
        return _planMeta.getStatus();
    }

    /**
    * Sets the status of this plan meta.
    *
    * @param status the status of this plan meta
    */
    @Override
    public void setStatus(java.lang.String status) {
        _planMeta.setStatus(status);
    }

    /**
    * Returns the mb category ID of this plan meta.
    *
    * @return the mb category ID of this plan meta
    */
    @Override
    public long getMbCategoryId() {
        return _planMeta.getMbCategoryId();
    }

    /**
    * Sets the mb category ID of this plan meta.
    *
    * @param mbCategoryId the mb category ID of this plan meta
    */
    @Override
    public void setMbCategoryId(long mbCategoryId) {
        _planMeta.setMbCategoryId(mbCategoryId);
    }

    /**
    * Returns the category group ID of this plan meta.
    *
    * @return the category group ID of this plan meta
    */
    @Override
    public long getCategoryGroupId() {
        return _planMeta.getCategoryGroupId();
    }

    /**
    * Sets the category group ID of this plan meta.
    *
    * @param categoryGroupId the category group ID of this plan meta
    */
    @Override
    public void setCategoryGroupId(long categoryGroupId) {
        _planMeta.setCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the version of this plan meta.
    *
    * @return the version of this plan meta
    */
    @Override
    public long getVersion() {
        return _planMeta.getVersion();
    }

    /**
    * Sets the version of this plan meta.
    *
    * @param version the version of this plan meta
    */
    @Override
    public void setVersion(long version) {
        _planMeta.setVersion(version);
    }

    /**
    * Returns the plan version of this plan meta.
    *
    * @return the plan version of this plan meta
    */
    @Override
    public long getPlanVersion() {
        return _planMeta.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan meta.
    *
    * @param planVersion the plan version of this plan meta
    */
    @Override
    public void setPlanVersion(long planVersion) {
        _planMeta.setPlanVersion(planVersion);
    }

    /**
    * Returns the created of this plan meta.
    *
    * @return the created of this plan meta
    */
    @Override
    public java.util.Date getCreated() {
        return _planMeta.getCreated();
    }

    /**
    * Sets the created of this plan meta.
    *
    * @param created the created of this plan meta
    */
    @Override
    public void setCreated(java.util.Date created) {
        _planMeta.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan meta.
    *
    * @return the update author ID of this plan meta
    */
    @Override
    public long getUpdateAuthorId() {
        return _planMeta.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan meta.
    *
    * @param updateAuthorId the update author ID of this plan meta
    */
    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _planMeta.setUpdateAuthorId(updateAuthorId);
    }

    /**
    * Returns the model ID of this plan meta.
    *
    * @return the model ID of this plan meta
    */
    @Override
    public long getModelId() {
        return _planMeta.getModelId();
    }

    /**
    * Sets the model ID of this plan meta.
    *
    * @param modelId the model ID of this plan meta
    */
    @Override
    public void setModelId(long modelId) {
        _planMeta.setModelId(modelId);
    }

    /**
    * Returns the promoted of this plan meta.
    *
    * @return the promoted of this plan meta
    */
    @Override
    public boolean getPromoted() {
        return _planMeta.getPromoted();
    }

    /**
    * Returns <code>true</code> if this plan meta is promoted.
    *
    * @return <code>true</code> if this plan meta is promoted; <code>false</code> otherwise
    */
    @Override
    public boolean isPromoted() {
        return _planMeta.isPromoted();
    }

    /**
    * Sets whether this plan meta is promoted.
    *
    * @param promoted the promoted of this plan meta
    */
    @Override
    public void setPromoted(boolean promoted) {
        _planMeta.setPromoted(promoted);
    }

    /**
    * Returns the previous contest phase of this plan meta.
    *
    * @return the previous contest phase of this plan meta
    */
    @Override
    public long getPreviousContestPhase() {
        return _planMeta.getPreviousContestPhase();
    }

    /**
    * Sets the previous contest phase of this plan meta.
    *
    * @param previousContestPhase the previous contest phase of this plan meta
    */
    @Override
    public void setPreviousContestPhase(long previousContestPhase) {
        _planMeta.setPreviousContestPhase(previousContestPhase);
    }

    /**
    * Returns the contest phase of this plan meta.
    *
    * @return the contest phase of this plan meta
    */
    @Override
    public long getContestPhase() {
        return _planMeta.getContestPhase();
    }

    /**
    * Sets the contest phase of this plan meta.
    *
    * @param contestPhase the contest phase of this plan meta
    */
    @Override
    public void setContestPhase(long contestPhase) {
        _planMeta.setContestPhase(contestPhase);
    }

    @Override
    public boolean isNew() {
        return _planMeta.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planMeta.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planMeta.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planMeta.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planMeta.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planMeta.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planMeta.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planMeta.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planMeta.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planMeta.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planMeta.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanMetaWrapper((PlanMeta) _planMeta.clone());
    }

    @Override
    public int compareTo(PlanMeta planMeta) {
        return _planMeta.compareTo(planMeta);
    }

    @Override
    public int hashCode() {
        return _planMeta.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanMeta> toCacheModel() {
        return _planMeta.toCacheModel();
    }

    @Override
    public PlanMeta toEscapedModel() {
        return new PlanMetaWrapper(_planMeta.toEscapedModel());
    }

    @Override
    public PlanMeta toUnescapedModel() {
        return new PlanMetaWrapper(_planMeta.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planMeta.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planMeta.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planMeta.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanMetaWrapper)) {
            return false;
        }

        PlanMetaWrapper planMetaWrapper = (PlanMetaWrapper) obj;

        if (Validator.equals(_planMeta, planMetaWrapper._planMeta)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanMeta getWrappedPlanMeta() {
        return _planMeta;
    }

    @Override
    public PlanMeta getWrappedModel() {
        return _planMeta;
    }

    @Override
    public void resetOriginalValues() {
        _planMeta.resetOriginalValues();
    }
}

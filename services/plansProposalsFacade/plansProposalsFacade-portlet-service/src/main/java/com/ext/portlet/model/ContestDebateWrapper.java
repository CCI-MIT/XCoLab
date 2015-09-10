package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestDebate}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDebate
 * @generated
 */
public class ContestDebateWrapper implements ContestDebate,
    ModelWrapper<ContestDebate> {
    private ContestDebate _contestDebate;

    public ContestDebateWrapper(ContestDebate contestDebate) {
        _contestDebate = contestDebate;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestDebate.class;
    }

    @Override
    public String getModelClassName() {
        return ContestDebate.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("debateId", getDebateId());
        attributes.put("ContestPK", getContestPK());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long debateId = (Long) attributes.get("debateId");

        if (debateId != null) {
            setDebateId(debateId);
        }

        Long ContestPK = (Long) attributes.get("ContestPK");

        if (ContestPK != null) {
            setContestPK(ContestPK);
        }
    }

    /**
    * Returns the primary key of this contest debate.
    *
    * @return the primary key of this contest debate
    */
    @Override
    public long getPrimaryKey() {
        return _contestDebate.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest debate.
    *
    * @param primaryKey the primary key of this contest debate
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestDebate.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest debate.
    *
    * @return the ID of this contest debate
    */
    @Override
    public long getId() {
        return _contestDebate.getId();
    }

    /**
    * Sets the ID of this contest debate.
    *
    * @param id the ID of this contest debate
    */
    @Override
    public void setId(long id) {
        _contestDebate.setId(id);
    }

    /**
    * Returns the debate ID of this contest debate.
    *
    * @return the debate ID of this contest debate
    */
    @Override
    public long getDebateId() {
        return _contestDebate.getDebateId();
    }

    /**
    * Sets the debate ID of this contest debate.
    *
    * @param debateId the debate ID of this contest debate
    */
    @Override
    public void setDebateId(long debateId) {
        _contestDebate.setDebateId(debateId);
    }

    /**
    * Returns the contest p k of this contest debate.
    *
    * @return the contest p k of this contest debate
    */
    @Override
    public long getContestPK() {
        return _contestDebate.getContestPK();
    }

    /**
    * Sets the contest p k of this contest debate.
    *
    * @param ContestPK the contest p k of this contest debate
    */
    @Override
    public void setContestPK(long ContestPK) {
        _contestDebate.setContestPK(ContestPK);
    }

    @Override
    public boolean isNew() {
        return _contestDebate.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestDebate.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestDebate.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestDebate.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestDebate.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestDebate.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestDebate.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestDebate.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestDebate.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestDebate.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestDebate.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestDebateWrapper((ContestDebate) _contestDebate.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.ContestDebate contestDebate) {
        return _contestDebate.compareTo(contestDebate);
    }

    @Override
    public int hashCode() {
        return _contestDebate.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ContestDebate> toCacheModel() {
        return _contestDebate.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ContestDebate toEscapedModel() {
        return new ContestDebateWrapper(_contestDebate.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ContestDebate toUnescapedModel() {
        return new ContestDebateWrapper(_contestDebate.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestDebate.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestDebate.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestDebate.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestDebateWrapper)) {
            return false;
        }

        ContestDebateWrapper contestDebateWrapper = (ContestDebateWrapper) obj;

        if (Validator.equals(_contestDebate, contestDebateWrapper._contestDebate)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestDebate getWrappedContestDebate() {
        return _contestDebate;
    }

    @Override
    public ContestDebate getWrappedModel() {
        return _contestDebate;
    }

    @Override
    public void resetOriginalValues() {
        _contestDebate.resetOriginalValues();
    }
}

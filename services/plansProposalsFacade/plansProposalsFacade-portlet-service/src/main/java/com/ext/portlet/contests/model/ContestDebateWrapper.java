package com.ext.portlet.contests.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestDebate}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestDebate
 * @generated
 */
public class ContestDebateWrapper implements ContestDebate,
    ModelWrapper<ContestDebate> {
    private ContestDebate _contestDebate;

    public ContestDebateWrapper(ContestDebate contestDebate) {
        _contestDebate = contestDebate;
    }

    public Class<?> getModelClass() {
        return ContestDebate.class;
    }

    public String getModelClassName() {
        return ContestDebate.class.getName();
    }

    /**
    * Returns the primary key of this contest debate.
    *
    * @return the primary key of this contest debate
    */
    public java.lang.Long getPrimaryKey() {
        return _contestDebate.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest debate.
    *
    * @param primaryKey the primary key of this contest debate
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _contestDebate.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest debate.
    *
    * @return the ID of this contest debate
    */
    public java.lang.Long getId() {
        return _contestDebate.getId();
    }

    /**
    * Sets the ID of this contest debate.
    *
    * @param id the ID of this contest debate
    */
    public void setId(java.lang.Long id) {
        _contestDebate.setId(id);
    }

    /**
    * Returns the debate ID of this contest debate.
    *
    * @return the debate ID of this contest debate
    */
    public java.lang.Long getDebateId() {
        return _contestDebate.getDebateId();
    }

    /**
    * Sets the debate ID of this contest debate.
    *
    * @param debateId the debate ID of this contest debate
    */
    public void setDebateId(java.lang.Long debateId) {
        _contestDebate.setDebateId(debateId);
    }

    /**
    * Returns the contest p k of this contest debate.
    *
    * @return the contest p k of this contest debate
    */
    public java.lang.Long getContestPK() {
        return _contestDebate.getContestPK();
    }

    /**
    * Sets the contest p k of this contest debate.
    *
    * @param ContestPK the contest p k of this contest debate
    */
    public void setContestPK(java.lang.Long ContestPK) {
        _contestDebate.setContestPK(ContestPK);
    }

    public boolean isNew() {
        return _contestDebate.isNew();
    }

    public void setNew(boolean n) {
        _contestDebate.setNew(n);
    }

    public boolean isCachedModel() {
        return _contestDebate.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _contestDebate.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _contestDebate.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _contestDebate.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestDebate.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestDebate.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestDebate.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestDebateWrapper((ContestDebate) _contestDebate.clone());
    }

    public int compareTo(ContestDebate contestDebate) {
        return _contestDebate.compareTo(contestDebate);
    }

    @Override
    public int hashCode() {
        return _contestDebate.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ContestDebate> toCacheModel() {
        return _contestDebate.toCacheModel();
    }

    public ContestDebate toEscapedModel() {
        return new ContestDebateWrapper(_contestDebate.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestDebate.toString();
    }

    public java.lang.String toXmlString() {
        return _contestDebate.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestDebate.persist();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestDebate.store();
    }

    public void delete()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestDebate.delete();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ContestDebate getWrappedContestDebate() {
        return _contestDebate;
    }

    public ContestDebate getWrappedModel() {
        return _contestDebate;
    }

    public void resetOriginalValues() {
        _contestDebate.resetOriginalValues();
    }
}

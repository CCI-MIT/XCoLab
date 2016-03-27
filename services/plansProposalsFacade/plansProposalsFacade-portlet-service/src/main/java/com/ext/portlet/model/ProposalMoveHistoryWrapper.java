package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalMoveHistory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalMoveHistory
 * @generated
 */
public class ProposalMoveHistoryWrapper implements ProposalMoveHistory,
    ModelWrapper<ProposalMoveHistory> {
    private ProposalMoveHistory _proposalMoveHistory;

    public ProposalMoveHistoryWrapper(ProposalMoveHistory proposalMoveHistory) {
        _proposalMoveHistory = proposalMoveHistory;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalMoveHistory.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalMoveHistory.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id_", getId_());
        attributes.put("sourceProposalId", getSourceProposalId());
        attributes.put("sourceContestId", getSourceContestId());
        attributes.put("sourcePhaseId", getSourcePhaseId());
        attributes.put("targetProposalId", getTargetProposalId());
        attributes.put("targetContestId", getTargetContestId());
        attributes.put("targetPhaseId", getTargetPhaseId());
        attributes.put("movingUserId", getMovingUserId());
        attributes.put("moveDate", getMoveDate());
        attributes.put("moveType", getMoveType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id_ = (Long) attributes.get("id_");

        if (id_ != null) {
            setId_(id_);
        }

        Long sourceProposalId = (Long) attributes.get("sourceProposalId");

        if (sourceProposalId != null) {
            setSourceProposalId(sourceProposalId);
        }

        Long sourceContestId = (Long) attributes.get("sourceContestId");

        if (sourceContestId != null) {
            setSourceContestId(sourceContestId);
        }

        Long sourcePhaseId = (Long) attributes.get("sourcePhaseId");

        if (sourcePhaseId != null) {
            setSourcePhaseId(sourcePhaseId);
        }

        Long targetProposalId = (Long) attributes.get("targetProposalId");

        if (targetProposalId != null) {
            setTargetProposalId(targetProposalId);
        }

        Long targetContestId = (Long) attributes.get("targetContestId");

        if (targetContestId != null) {
            setTargetContestId(targetContestId);
        }

        Long targetPhaseId = (Long) attributes.get("targetPhaseId");

        if (targetPhaseId != null) {
            setTargetPhaseId(targetPhaseId);
        }

        Long movingUserId = (Long) attributes.get("movingUserId");

        if (movingUserId != null) {
            setMovingUserId(movingUserId);
        }

        Date moveDate = (Date) attributes.get("moveDate");

        if (moveDate != null) {
            setMoveDate(moveDate);
        }

        String moveType = (String) attributes.get("moveType");

        if (moveType != null) {
            setMoveType(moveType);
        }
    }

    /**
    * Returns the primary key of this proposal move history.
    *
    * @return the primary key of this proposal move history
    */
    @Override
    public long getPrimaryKey() {
        return _proposalMoveHistory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal move history.
    *
    * @param primaryKey the primary key of this proposal move history
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _proposalMoveHistory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the id_ of this proposal move history.
    *
    * @return the id_ of this proposal move history
    */
    @Override
    public long getId_() {
        return _proposalMoveHistory.getId_();
    }

    /**
    * Sets the id_ of this proposal move history.
    *
    * @param id_ the id_ of this proposal move history
    */
    @Override
    public void setId_(long id_) {
        _proposalMoveHistory.setId_(id_);
    }

    /**
    * Returns the source proposal ID of this proposal move history.
    *
    * @return the source proposal ID of this proposal move history
    */
    @Override
    public long getSourceProposalId() {
        return _proposalMoveHistory.getSourceProposalId();
    }

    /**
    * Sets the source proposal ID of this proposal move history.
    *
    * @param sourceProposalId the source proposal ID of this proposal move history
    */
    @Override
    public void setSourceProposalId(long sourceProposalId) {
        _proposalMoveHistory.setSourceProposalId(sourceProposalId);
    }

    /**
    * Returns the source contest ID of this proposal move history.
    *
    * @return the source contest ID of this proposal move history
    */
    @Override
    public long getSourceContestId() {
        return _proposalMoveHistory.getSourceContestId();
    }

    /**
    * Sets the source contest ID of this proposal move history.
    *
    * @param sourceContestId the source contest ID of this proposal move history
    */
    @Override
    public void setSourceContestId(long sourceContestId) {
        _proposalMoveHistory.setSourceContestId(sourceContestId);
    }

    /**
    * Returns the source phase ID of this proposal move history.
    *
    * @return the source phase ID of this proposal move history
    */
    @Override
    public long getSourcePhaseId() {
        return _proposalMoveHistory.getSourcePhaseId();
    }

    /**
    * Sets the source phase ID of this proposal move history.
    *
    * @param sourcePhaseId the source phase ID of this proposal move history
    */
    @Override
    public void setSourcePhaseId(long sourcePhaseId) {
        _proposalMoveHistory.setSourcePhaseId(sourcePhaseId);
    }

    /**
    * Returns the target proposal ID of this proposal move history.
    *
    * @return the target proposal ID of this proposal move history
    */
    @Override
    public long getTargetProposalId() {
        return _proposalMoveHistory.getTargetProposalId();
    }

    /**
    * Sets the target proposal ID of this proposal move history.
    *
    * @param targetProposalId the target proposal ID of this proposal move history
    */
    @Override
    public void setTargetProposalId(long targetProposalId) {
        _proposalMoveHistory.setTargetProposalId(targetProposalId);
    }

    /**
    * Returns the target contest ID of this proposal move history.
    *
    * @return the target contest ID of this proposal move history
    */
    @Override
    public long getTargetContestId() {
        return _proposalMoveHistory.getTargetContestId();
    }

    /**
    * Sets the target contest ID of this proposal move history.
    *
    * @param targetContestId the target contest ID of this proposal move history
    */
    @Override
    public void setTargetContestId(long targetContestId) {
        _proposalMoveHistory.setTargetContestId(targetContestId);
    }

    /**
    * Returns the target phase ID of this proposal move history.
    *
    * @return the target phase ID of this proposal move history
    */
    @Override
    public long getTargetPhaseId() {
        return _proposalMoveHistory.getTargetPhaseId();
    }

    /**
    * Sets the target phase ID of this proposal move history.
    *
    * @param targetPhaseId the target phase ID of this proposal move history
    */
    @Override
    public void setTargetPhaseId(long targetPhaseId) {
        _proposalMoveHistory.setTargetPhaseId(targetPhaseId);
    }

    /**
    * Returns the moving user ID of this proposal move history.
    *
    * @return the moving user ID of this proposal move history
    */
    @Override
    public long getMovingUserId() {
        return _proposalMoveHistory.getMovingUserId();
    }

    /**
    * Sets the moving user ID of this proposal move history.
    *
    * @param movingUserId the moving user ID of this proposal move history
    */
    @Override
    public void setMovingUserId(long movingUserId) {
        _proposalMoveHistory.setMovingUserId(movingUserId);
    }

    /**
    * Returns the moving user uuid of this proposal move history.
    *
    * @return the moving user uuid of this proposal move history
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getMovingUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistory.getMovingUserUuid();
    }

    /**
    * Sets the moving user uuid of this proposal move history.
    *
    * @param movingUserUuid the moving user uuid of this proposal move history
    */
    @Override
    public void setMovingUserUuid(java.lang.String movingUserUuid) {
        _proposalMoveHistory.setMovingUserUuid(movingUserUuid);
    }

    /**
    * Returns the move date of this proposal move history.
    *
    * @return the move date of this proposal move history
    */
    @Override
    public java.util.Date getMoveDate() {
        return _proposalMoveHistory.getMoveDate();
    }

    /**
    * Sets the move date of this proposal move history.
    *
    * @param moveDate the move date of this proposal move history
    */
    @Override
    public void setMoveDate(java.util.Date moveDate) {
        _proposalMoveHistory.setMoveDate(moveDate);
    }

    /**
    * Returns the move type of this proposal move history.
    *
    * @return the move type of this proposal move history
    */
    @Override
    public java.lang.String getMoveType() {
        return _proposalMoveHistory.getMoveType();
    }

    /**
    * Sets the move type of this proposal move history.
    *
    * @param moveType the move type of this proposal move history
    */
    @Override
    public void setMoveType(java.lang.String moveType) {
        _proposalMoveHistory.setMoveType(moveType);
    }

    @Override
    public boolean isNew() {
        return _proposalMoveHistory.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalMoveHistory.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalMoveHistory.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalMoveHistory.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalMoveHistory.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalMoveHistory.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalMoveHistory.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalMoveHistory.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalMoveHistory.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalMoveHistory.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalMoveHistory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalMoveHistoryWrapper((ProposalMoveHistory) _proposalMoveHistory.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory) {
        return _proposalMoveHistory.compareTo(proposalMoveHistory);
    }

    @Override
    public int hashCode() {
        return _proposalMoveHistory.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ProposalMoveHistory> toCacheModel() {
        return _proposalMoveHistory.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ProposalMoveHistory toEscapedModel() {
        return new ProposalMoveHistoryWrapper(_proposalMoveHistory.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ProposalMoveHistory toUnescapedModel() {
        return new ProposalMoveHistoryWrapper(_proposalMoveHistory.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalMoveHistory.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalMoveHistory.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalMoveHistory.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalMoveHistoryWrapper)) {
            return false;
        }

        ProposalMoveHistoryWrapper proposalMoveHistoryWrapper = (ProposalMoveHistoryWrapper) obj;

        if (Validator.equals(_proposalMoveHistory,
                    proposalMoveHistoryWrapper._proposalMoveHistory)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalMoveHistory getWrappedProposalMoveHistory() {
        return _proposalMoveHistory;
    }

    @Override
    public ProposalMoveHistory getWrappedModel() {
        return _proposalMoveHistory;
    }

    @Override
    public void resetOriginalValues() {
        _proposalMoveHistory.resetOriginalValues();
    }
}

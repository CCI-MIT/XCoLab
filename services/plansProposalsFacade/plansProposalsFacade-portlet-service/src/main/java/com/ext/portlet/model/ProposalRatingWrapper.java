package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProposalRating}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRating
 * @generated
 */
public class ProposalRatingWrapper implements ProposalRating,
    ModelWrapper<ProposalRating> {
    private ProposalRating _proposalRating;

    public ProposalRatingWrapper(ProposalRating proposalRating) {
        _proposalRating = proposalRating;
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalRating.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalRating.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("proposalId", getProposalId());
        attributes.put("contestPhaseId", getContestPhaseId());
        attributes.put("userId", getUserId());
        attributes.put("ratingValueId", getRatingValueId());
        attributes.put("comment", getComment());
        attributes.put("commentEnabled", getCommentEnabled());
        attributes.put("otherDataString", getOtherDataString());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long contestPhaseId = (Long) attributes.get("contestPhaseId");

        if (contestPhaseId != null) {
            setContestPhaseId(contestPhaseId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long ratingValueId = (Long) attributes.get("ratingValueId");

        if (ratingValueId != null) {
            setRatingValueId(ratingValueId);
        }

        String comment = (String) attributes.get("comment");

        if (comment != null) {
            setComment(comment);
        }

        Boolean commentEnabled = (Boolean) attributes.get("commentEnabled");

        if (commentEnabled != null) {
            setCommentEnabled(commentEnabled);
        }

        String otherDataString = (String) attributes.get("otherDataString");

        if (otherDataString != null) {
            setOtherDataString(otherDataString);
        }
    }

    /**
    * Returns the primary key of this proposal rating.
    *
    * @return the primary key of this proposal rating
    */
    @Override
    public long getPrimaryKey() {
        return _proposalRating.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal rating.
    *
    * @param primaryKey the primary key of this proposal rating
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _proposalRating.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this proposal rating.
    *
    * @return the ID of this proposal rating
    */
    @Override
    public long getId() {
        return _proposalRating.getId();
    }

    /**
    * Sets the ID of this proposal rating.
    *
    * @param id the ID of this proposal rating
    */
    @Override
    public void setId(long id) {
        _proposalRating.setId(id);
    }

    /**
    * Returns the proposal ID of this proposal rating.
    *
    * @return the proposal ID of this proposal rating
    */
    @Override
    public long getProposalId() {
        return _proposalRating.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal rating.
    *
    * @param proposalId the proposal ID of this proposal rating
    */
    @Override
    public void setProposalId(long proposalId) {
        _proposalRating.setProposalId(proposalId);
    }

    /**
    * Returns the contest phase ID of this proposal rating.
    *
    * @return the contest phase ID of this proposal rating
    */
    @Override
    public long getContestPhaseId() {
        return _proposalRating.getContestPhaseId();
    }

    /**
    * Sets the contest phase ID of this proposal rating.
    *
    * @param contestPhaseId the contest phase ID of this proposal rating
    */
    @Override
    public void setContestPhaseId(long contestPhaseId) {
        _proposalRating.setContestPhaseId(contestPhaseId);
    }

    /**
    * Returns the user ID of this proposal rating.
    *
    * @return the user ID of this proposal rating
    */
    @Override
    public long getUserId() {
        return _proposalRating.getUserId();
    }

    /**
    * Sets the user ID of this proposal rating.
    *
    * @param userId the user ID of this proposal rating
    */
    @Override
    public void setUserId(long userId) {
        _proposalRating.setUserId(userId);
    }

    /**
    * Returns the user uuid of this proposal rating.
    *
    * @return the user uuid of this proposal rating
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRating.getUserUuid();
    }

    /**
    * Sets the user uuid of this proposal rating.
    *
    * @param userUuid the user uuid of this proposal rating
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _proposalRating.setUserUuid(userUuid);
    }

    /**
    * Returns the rating value ID of this proposal rating.
    *
    * @return the rating value ID of this proposal rating
    */
    @Override
    public long getRatingValueId() {
        return _proposalRating.getRatingValueId();
    }

    /**
    * Sets the rating value ID of this proposal rating.
    *
    * @param ratingValueId the rating value ID of this proposal rating
    */
    @Override
    public void setRatingValueId(long ratingValueId) {
        _proposalRating.setRatingValueId(ratingValueId);
    }

    /**
    * Returns the comment of this proposal rating.
    *
    * @return the comment of this proposal rating
    */
    @Override
    public java.lang.String getComment() {
        return _proposalRating.getComment();
    }

    /**
    * Sets the comment of this proposal rating.
    *
    * @param comment the comment of this proposal rating
    */
    @Override
    public void setComment(java.lang.String comment) {
        _proposalRating.setComment(comment);
    }

    /**
    * Returns the comment enabled of this proposal rating.
    *
    * @return the comment enabled of this proposal rating
    */
    @Override
    public boolean getCommentEnabled() {
        return _proposalRating.getCommentEnabled();
    }

    /**
    * Returns <code>true</code> if this proposal rating is comment enabled.
    *
    * @return <code>true</code> if this proposal rating is comment enabled; <code>false</code> otherwise
    */
    @Override
    public boolean isCommentEnabled() {
        return _proposalRating.isCommentEnabled();
    }

    /**
    * Sets whether this proposal rating is comment enabled.
    *
    * @param commentEnabled the comment enabled of this proposal rating
    */
    @Override
    public void setCommentEnabled(boolean commentEnabled) {
        _proposalRating.setCommentEnabled(commentEnabled);
    }

    /**
    * Returns the other data string of this proposal rating.
    *
    * @return the other data string of this proposal rating
    */
    @Override
    public java.lang.String getOtherDataString() {
        return _proposalRating.getOtherDataString();
    }

    /**
    * Sets the other data string of this proposal rating.
    *
    * @param otherDataString the other data string of this proposal rating
    */
    @Override
    public void setOtherDataString(java.lang.String otherDataString) {
        _proposalRating.setOtherDataString(otherDataString);
    }

    @Override
    public boolean isNew() {
        return _proposalRating.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _proposalRating.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _proposalRating.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _proposalRating.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _proposalRating.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalRating.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalRating.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalRating.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _proposalRating.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _proposalRating.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalRating.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalRatingWrapper((ProposalRating) _proposalRating.clone());
    }

    @Override
    public int compareTo(ProposalRating proposalRating) {
        return _proposalRating.compareTo(proposalRating);
    }

    @Override
    public int hashCode() {
        return _proposalRating.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ProposalRating> toCacheModel() {
        return _proposalRating.toCacheModel();
    }

    @Override
    public ProposalRating toEscapedModel() {
        return new ProposalRatingWrapper(_proposalRating.toEscapedModel());
    }

    @Override
    public ProposalRating toUnescapedModel() {
        return new ProposalRatingWrapper(_proposalRating.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalRating.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _proposalRating.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalRating.persist();
    }

    @Override
    public boolean isRatingComplete() {
        return _proposalRating.isRatingComplete();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalRatingWrapper)) {
            return false;
        }

        ProposalRatingWrapper proposalRatingWrapper = (ProposalRatingWrapper) obj;

        if (Validator.equals(_proposalRating,
                    proposalRatingWrapper._proposalRating)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ProposalRating getWrappedProposalRating() {
        return _proposalRating;
    }

    @Override
    public ProposalRating getWrappedModel() {
        return _proposalRating;
    }

    @Override
    public void resetOriginalValues() {
        _proposalRating.resetOriginalValues();
    }
}

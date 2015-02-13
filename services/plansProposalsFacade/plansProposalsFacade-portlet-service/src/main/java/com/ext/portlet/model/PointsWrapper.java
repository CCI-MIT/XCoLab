package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Points}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Points
 * @generated
 */
public class PointsWrapper implements Points, ModelWrapper<Points> {
    private Points _points;

    public PointsWrapper(Points points) {
        _points = points;
    }

    @Override
    public Class<?> getModelClass() {
        return Points.class;
    }

    @Override
    public String getModelClassName() {
        return Points.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("proposalId", getProposalId());
        attributes.put("userId", getUserId());
        attributes.put("materializedPoints", getMaterializedPoints());
        attributes.put("hypotheticalPoints", getHypotheticalPoints());
        attributes.put("pointsSourceId", getPointsSourceId());
        attributes.put("originatingContestPK", getOriginatingContestPK());
        attributes.put("originatingProposalId", getOriginatingProposalId());

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

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Double materializedPoints = (Double) attributes.get(
                "materializedPoints");

        if (materializedPoints != null) {
            setMaterializedPoints(materializedPoints);
        }

        Double hypotheticalPoints = (Double) attributes.get(
                "hypotheticalPoints");

        if (hypotheticalPoints != null) {
            setHypotheticalPoints(hypotheticalPoints);
        }

        Long pointsSourceId = (Long) attributes.get("pointsSourceId");

        if (pointsSourceId != null) {
            setPointsSourceId(pointsSourceId);
        }

        Long originatingContestPK = (Long) attributes.get(
                "originatingContestPK");

        if (originatingContestPK != null) {
            setOriginatingContestPK(originatingContestPK);
        }

        Long originatingProposalId = (Long) attributes.get(
                "originatingProposalId");

        if (originatingProposalId != null) {
            setOriginatingProposalId(originatingProposalId);
        }
    }

    /**
    * Returns the primary key of this points.
    *
    * @return the primary key of this points
    */
    @Override
    public long getPrimaryKey() {
        return _points.getPrimaryKey();
    }

    /**
    * Sets the primary key of this points.
    *
    * @param primaryKey the primary key of this points
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _points.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this points.
    *
    * @return the ID of this points
    */
    @Override
    public long getId() {
        return _points.getId();
    }

    /**
    * Sets the ID of this points.
    *
    * @param id the ID of this points
    */
    @Override
    public void setId(long id) {
        _points.setId(id);
    }

    /**
    * Returns the proposal ID of this points.
    *
    * @return the proposal ID of this points
    */
    @Override
    public long getProposalId() {
        return _points.getProposalId();
    }

    /**
    * Sets the proposal ID of this points.
    *
    * @param proposalId the proposal ID of this points
    */
    @Override
    public void setProposalId(long proposalId) {
        _points.setProposalId(proposalId);
    }

    /**
    * Returns the user ID of this points.
    *
    * @return the user ID of this points
    */
    @Override
    public long getUserId() {
        return _points.getUserId();
    }

    /**
    * Sets the user ID of this points.
    *
    * @param userId the user ID of this points
    */
    @Override
    public void setUserId(long userId) {
        _points.setUserId(userId);
    }

    /**
    * Returns the user uuid of this points.
    *
    * @return the user uuid of this points
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _points.getUserUuid();
    }

    /**
    * Sets the user uuid of this points.
    *
    * @param userUuid the user uuid of this points
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _points.setUserUuid(userUuid);
    }

    /**
    * Returns the materialized points of this points.
    *
    * @return the materialized points of this points
    */
    @Override
    public double getMaterializedPoints() {
        return _points.getMaterializedPoints();
    }

    /**
    * Sets the materialized points of this points.
    *
    * @param materializedPoints the materialized points of this points
    */
    @Override
    public void setMaterializedPoints(double materializedPoints) {
        _points.setMaterializedPoints(materializedPoints);
    }

    /**
    * Returns the hypothetical points of this points.
    *
    * @return the hypothetical points of this points
    */
    @Override
    public double getHypotheticalPoints() {
        return _points.getHypotheticalPoints();
    }

    /**
    * Sets the hypothetical points of this points.
    *
    * @param hypotheticalPoints the hypothetical points of this points
    */
    @Override
    public void setHypotheticalPoints(double hypotheticalPoints) {
        _points.setHypotheticalPoints(hypotheticalPoints);
    }

    /**
    * Returns the points source ID of this points.
    *
    * @return the points source ID of this points
    */
    @Override
    public long getPointsSourceId() {
        return _points.getPointsSourceId();
    }

    /**
    * Sets the points source ID of this points.
    *
    * @param pointsSourceId the points source ID of this points
    */
    @Override
    public void setPointsSourceId(long pointsSourceId) {
        _points.setPointsSourceId(pointsSourceId);
    }

    /**
    * Returns the originating contest p k of this points.
    *
    * @return the originating contest p k of this points
    */
    @Override
    public long getOriginatingContestPK() {
        return _points.getOriginatingContestPK();
    }

    /**
    * Sets the originating contest p k of this points.
    *
    * @param originatingContestPK the originating contest p k of this points
    */
    @Override
    public void setOriginatingContestPK(long originatingContestPK) {
        _points.setOriginatingContestPK(originatingContestPK);
    }

    /**
    * Returns the originating proposal ID of this points.
    *
    * @return the originating proposal ID of this points
    */
    @Override
    public long getOriginatingProposalId() {
        return _points.getOriginatingProposalId();
    }

    /**
    * Sets the originating proposal ID of this points.
    *
    * @param originatingProposalId the originating proposal ID of this points
    */
    @Override
    public void setOriginatingProposalId(long originatingProposalId) {
        _points.setOriginatingProposalId(originatingProposalId);
    }

    @Override
    public boolean isNew() {
        return _points.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _points.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _points.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _points.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _points.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _points.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _points.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _points.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _points.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _points.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _points.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PointsWrapper((Points) _points.clone());
    }

    @Override
    public int compareTo(Points points) {
        return _points.compareTo(points);
    }

    @Override
    public int hashCode() {
        return _points.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<Points> toCacheModel() {
        return _points.toCacheModel();
    }

    @Override
    public Points toEscapedModel() {
        return new PointsWrapper(_points.toEscapedModel());
    }

    @Override
    public Points toUnescapedModel() {
        return new PointsWrapper(_points.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _points.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _points.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _points.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PointsWrapper)) {
            return false;
        }

        PointsWrapper pointsWrapper = (PointsWrapper) obj;

        if (Validator.equals(_points, pointsWrapper._points)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Points getWrappedPoints() {
        return _points;
    }

    @Override
    public Points getWrappedModel() {
        return _points;
    }

    @Override
    public void resetOriginalValues() {
        _points.resetOriginalValues();
    }
}

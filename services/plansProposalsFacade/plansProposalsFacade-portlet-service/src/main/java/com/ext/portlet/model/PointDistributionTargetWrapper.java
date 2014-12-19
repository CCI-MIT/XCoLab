package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PointDistributionTarget}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointDistributionTarget
 * @generated
 */
public class PointDistributionTargetWrapper implements PointDistributionTarget,
    ModelWrapper<PointDistributionTarget> {
    private PointDistributionTarget _pointDistributionTarget;

    public PointDistributionTargetWrapper(
        PointDistributionTarget pointDistributionTarget) {
        _pointDistributionTarget = pointDistributionTarget;
    }

    @Override
    public Class<?> getModelClass() {
        return PointDistributionTarget.class;
    }

    @Override
    public String getModelClassName() {
        return PointDistributionTarget.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("contestId", getContestId());
        attributes.put("proposalId", getProposalId());
        attributes.put("numberOfPoints", getNumberOfPoints());
        attributes.put("pointTypeOverride", getPointTypeOverride());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long contestId = (Long) attributes.get("contestId");

        if (contestId != null) {
            setContestId(contestId);
        }

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Double numberOfPoints = (Double) attributes.get("numberOfPoints");

        if (numberOfPoints != null) {
            setNumberOfPoints(numberOfPoints);
        }

        Long pointTypeOverride = (Long) attributes.get("pointTypeOverride");

        if (pointTypeOverride != null) {
            setPointTypeOverride(pointTypeOverride);
        }
    }

    /**
    * Returns the primary key of this point distribution target.
    *
    * @return the primary key of this point distribution target
    */
    @Override
    public long getPrimaryKey() {
        return _pointDistributionTarget.getPrimaryKey();
    }

    /**
    * Sets the primary key of this point distribution target.
    *
    * @param primaryKey the primary key of this point distribution target
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _pointDistributionTarget.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this point distribution target.
    *
    * @return the ID of this point distribution target
    */
    @Override
    public long getId() {
        return _pointDistributionTarget.getId();
    }

    /**
    * Sets the ID of this point distribution target.
    *
    * @param id the ID of this point distribution target
    */
    @Override
    public void setId(long id) {
        _pointDistributionTarget.setId(id);
    }

    /**
    * Returns the contest ID of this point distribution target.
    *
    * @return the contest ID of this point distribution target
    */
    @Override
    public long getContestId() {
        return _pointDistributionTarget.getContestId();
    }

    /**
    * Sets the contest ID of this point distribution target.
    *
    * @param contestId the contest ID of this point distribution target
    */
    @Override
    public void setContestId(long contestId) {
        _pointDistributionTarget.setContestId(contestId);
    }

    /**
    * Returns the proposal ID of this point distribution target.
    *
    * @return the proposal ID of this point distribution target
    */
    @Override
    public long getProposalId() {
        return _pointDistributionTarget.getProposalId();
    }

    /**
    * Sets the proposal ID of this point distribution target.
    *
    * @param proposalId the proposal ID of this point distribution target
    */
    @Override
    public void setProposalId(long proposalId) {
        _pointDistributionTarget.setProposalId(proposalId);
    }

    /**
    * Returns the number of points of this point distribution target.
    *
    * @return the number of points of this point distribution target
    */
    @Override
    public double getNumberOfPoints() {
        return _pointDistributionTarget.getNumberOfPoints();
    }

    /**
    * Sets the number of points of this point distribution target.
    *
    * @param numberOfPoints the number of points of this point distribution target
    */
    @Override
    public void setNumberOfPoints(double numberOfPoints) {
        _pointDistributionTarget.setNumberOfPoints(numberOfPoints);
    }

    /**
    * Returns the point type override of this point distribution target.
    *
    * @return the point type override of this point distribution target
    */
    @Override
    public long getPointTypeOverride() {
        return _pointDistributionTarget.getPointTypeOverride();
    }

    /**
    * Sets the point type override of this point distribution target.
    *
    * @param pointTypeOverride the point type override of this point distribution target
    */
    @Override
    public void setPointTypeOverride(long pointTypeOverride) {
        _pointDistributionTarget.setPointTypeOverride(pointTypeOverride);
    }

    @Override
    public boolean isNew() {
        return _pointDistributionTarget.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _pointDistributionTarget.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _pointDistributionTarget.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _pointDistributionTarget.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _pointDistributionTarget.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _pointDistributionTarget.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _pointDistributionTarget.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _pointDistributionTarget.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _pointDistributionTarget.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _pointDistributionTarget.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _pointDistributionTarget.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PointDistributionTargetWrapper((PointDistributionTarget) _pointDistributionTarget.clone());
    }

    @Override
    public int compareTo(PointDistributionTarget pointDistributionTarget) {
        return _pointDistributionTarget.compareTo(pointDistributionTarget);
    }

    @Override
    public int hashCode() {
        return _pointDistributionTarget.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PointDistributionTarget> toCacheModel() {
        return _pointDistributionTarget.toCacheModel();
    }

    @Override
    public PointDistributionTarget toEscapedModel() {
        return new PointDistributionTargetWrapper(_pointDistributionTarget.toEscapedModel());
    }

    @Override
    public PointDistributionTarget toUnescapedModel() {
        return new PointDistributionTargetWrapper(_pointDistributionTarget.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _pointDistributionTarget.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _pointDistributionTarget.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _pointDistributionTarget.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PointDistributionTargetWrapper)) {
            return false;
        }

        PointDistributionTargetWrapper pointDistributionTargetWrapper = (PointDistributionTargetWrapper) obj;

        if (Validator.equals(_pointDistributionTarget,
                    pointDistributionTargetWrapper._pointDistributionTarget)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PointDistributionTarget getWrappedPointDistributionTarget() {
        return _pointDistributionTarget;
    }

    @Override
    public PointDistributionTarget getWrappedModel() {
        return _pointDistributionTarget;
    }

    @Override
    public void resetOriginalValues() {
        _pointDistributionTarget.resetOriginalValues();
    }
}

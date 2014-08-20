package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PointsDistributionConfiguration}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointsDistributionConfiguration
 * @generated
 */
public class PointsDistributionConfigurationWrapper
    implements PointsDistributionConfiguration,
        ModelWrapper<PointsDistributionConfiguration> {
    private PointsDistributionConfiguration _pointsDistributionConfiguration;

    public PointsDistributionConfigurationWrapper(
        PointsDistributionConfiguration pointsDistributionConfiguration) {
        _pointsDistributionConfiguration = pointsDistributionConfiguration;
    }

    @Override
    public Class<?> getModelClass() {
        return PointsDistributionConfiguration.class;
    }

    @Override
    public String getModelClassName() {
        return PointsDistributionConfiguration.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("proposalId", getProposalId());
        attributes.put("pointTypeId", getPointTypeId());
        attributes.put("targetUserId", getTargetUserId());
        attributes.put("targetSubProposalId", getTargetSubProposalId());
        attributes.put("percentage", getPercentage());
        attributes.put("creator", getCreator());
        attributes.put("createDate", getCreateDate());

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

        Long pointTypeId = (Long) attributes.get("pointTypeId");

        if (pointTypeId != null) {
            setPointTypeId(pointTypeId);
        }

        Long targetUserId = (Long) attributes.get("targetUserId");

        if (targetUserId != null) {
            setTargetUserId(targetUserId);
        }

        Long targetSubProposalId = (Long) attributes.get("targetSubProposalId");

        if (targetSubProposalId != null) {
            setTargetSubProposalId(targetSubProposalId);
        }

        Double percentage = (Double) attributes.get("percentage");

        if (percentage != null) {
            setPercentage(percentage);
        }

        Long creator = (Long) attributes.get("creator");

        if (creator != null) {
            setCreator(creator);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this points distribution configuration.
    *
    * @return the primary key of this points distribution configuration
    */
    @Override
    public long getPrimaryKey() {
        return _pointsDistributionConfiguration.getPrimaryKey();
    }

    /**
    * Sets the primary key of this points distribution configuration.
    *
    * @param primaryKey the primary key of this points distribution configuration
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _pointsDistributionConfiguration.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this points distribution configuration.
    *
    * @return the ID of this points distribution configuration
    */
    @Override
    public long getId() {
        return _pointsDistributionConfiguration.getId();
    }

    /**
    * Sets the ID of this points distribution configuration.
    *
    * @param id the ID of this points distribution configuration
    */
    @Override
    public void setId(long id) {
        _pointsDistributionConfiguration.setId(id);
    }

    /**
    * Returns the proposal ID of this points distribution configuration.
    *
    * @return the proposal ID of this points distribution configuration
    */
    @Override
    public long getProposalId() {
        return _pointsDistributionConfiguration.getProposalId();
    }

    /**
    * Sets the proposal ID of this points distribution configuration.
    *
    * @param proposalId the proposal ID of this points distribution configuration
    */
    @Override
    public void setProposalId(long proposalId) {
        _pointsDistributionConfiguration.setProposalId(proposalId);
    }

    /**
    * Returns the point type ID of this points distribution configuration.
    *
    * @return the point type ID of this points distribution configuration
    */
    @Override
    public long getPointTypeId() {
        return _pointsDistributionConfiguration.getPointTypeId();
    }

    /**
    * Sets the point type ID of this points distribution configuration.
    *
    * @param pointTypeId the point type ID of this points distribution configuration
    */
    @Override
    public void setPointTypeId(long pointTypeId) {
        _pointsDistributionConfiguration.setPointTypeId(pointTypeId);
    }

    /**
    * Returns the target user ID of this points distribution configuration.
    *
    * @return the target user ID of this points distribution configuration
    */
    @Override
    public long getTargetUserId() {
        return _pointsDistributionConfiguration.getTargetUserId();
    }

    /**
    * Sets the target user ID of this points distribution configuration.
    *
    * @param targetUserId the target user ID of this points distribution configuration
    */
    @Override
    public void setTargetUserId(long targetUserId) {
        _pointsDistributionConfiguration.setTargetUserId(targetUserId);
    }

    /**
    * Returns the target user uuid of this points distribution configuration.
    *
    * @return the target user uuid of this points distribution configuration
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getTargetUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfiguration.getTargetUserUuid();
    }

    /**
    * Sets the target user uuid of this points distribution configuration.
    *
    * @param targetUserUuid the target user uuid of this points distribution configuration
    */
    @Override
    public void setTargetUserUuid(java.lang.String targetUserUuid) {
        _pointsDistributionConfiguration.setTargetUserUuid(targetUserUuid);
    }

    /**
    * Returns the target sub proposal ID of this points distribution configuration.
    *
    * @return the target sub proposal ID of this points distribution configuration
    */
    @Override
    public long getTargetSubProposalId() {
        return _pointsDistributionConfiguration.getTargetSubProposalId();
    }

    /**
    * Sets the target sub proposal ID of this points distribution configuration.
    *
    * @param targetSubProposalId the target sub proposal ID of this points distribution configuration
    */
    @Override
    public void setTargetSubProposalId(long targetSubProposalId) {
        _pointsDistributionConfiguration.setTargetSubProposalId(targetSubProposalId);
    }

    /**
    * Returns the percentage of this points distribution configuration.
    *
    * @return the percentage of this points distribution configuration
    */
    @Override
    public double getPercentage() {
        return _pointsDistributionConfiguration.getPercentage();
    }

    /**
    * Sets the percentage of this points distribution configuration.
    *
    * @param percentage the percentage of this points distribution configuration
    */
    @Override
    public void setPercentage(double percentage) {
        _pointsDistributionConfiguration.setPercentage(percentage);
    }

    /**
    * Returns the creator of this points distribution configuration.
    *
    * @return the creator of this points distribution configuration
    */
    @Override
    public long getCreator() {
        return _pointsDistributionConfiguration.getCreator();
    }

    /**
    * Sets the creator of this points distribution configuration.
    *
    * @param creator the creator of this points distribution configuration
    */
    @Override
    public void setCreator(long creator) {
        _pointsDistributionConfiguration.setCreator(creator);
    }

    /**
    * Returns the create date of this points distribution configuration.
    *
    * @return the create date of this points distribution configuration
    */
    @Override
    public java.util.Date getCreateDate() {
        return _pointsDistributionConfiguration.getCreateDate();
    }

    /**
    * Sets the create date of this points distribution configuration.
    *
    * @param createDate the create date of this points distribution configuration
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _pointsDistributionConfiguration.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _pointsDistributionConfiguration.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _pointsDistributionConfiguration.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _pointsDistributionConfiguration.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _pointsDistributionConfiguration.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _pointsDistributionConfiguration.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _pointsDistributionConfiguration.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _pointsDistributionConfiguration.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _pointsDistributionConfiguration.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _pointsDistributionConfiguration.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _pointsDistributionConfiguration.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _pointsDistributionConfiguration.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PointsDistributionConfigurationWrapper((PointsDistributionConfiguration) _pointsDistributionConfiguration.clone());
    }

    @Override
    public int compareTo(
        PointsDistributionConfiguration pointsDistributionConfiguration) {
        return _pointsDistributionConfiguration.compareTo(pointsDistributionConfiguration);
    }

    @Override
    public int hashCode() {
        return _pointsDistributionConfiguration.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PointsDistributionConfiguration> toCacheModel() {
        return _pointsDistributionConfiguration.toCacheModel();
    }

    @Override
    public PointsDistributionConfiguration toEscapedModel() {
        return new PointsDistributionConfigurationWrapper(_pointsDistributionConfiguration.toEscapedModel());
    }

    @Override
    public PointsDistributionConfiguration toUnescapedModel() {
        return new PointsDistributionConfigurationWrapper(_pointsDistributionConfiguration.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _pointsDistributionConfiguration.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _pointsDistributionConfiguration.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _pointsDistributionConfiguration.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PointsDistributionConfigurationWrapper)) {
            return false;
        }

        PointsDistributionConfigurationWrapper pointsDistributionConfigurationWrapper =
            (PointsDistributionConfigurationWrapper) obj;

        if (Validator.equals(_pointsDistributionConfiguration,
                    pointsDistributionConfigurationWrapper._pointsDistributionConfiguration)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PointsDistributionConfiguration getWrappedPointsDistributionConfiguration() {
        return _pointsDistributionConfiguration;
    }

    @Override
    public PointsDistributionConfiguration getWrappedModel() {
        return _pointsDistributionConfiguration;
    }

    @Override
    public void resetOriginalValues() {
        _pointsDistributionConfiguration.resetOriginalValues();
    }
}

package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PointsDistributionConfigurationServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PointsDistributionConfigurationServiceSoap
 * @generated
 */
public class PointsDistributionConfigurationSoap implements Serializable {
    private long _id;
    private long _proposalId;
    private long _pointTypeId;
    private long _targetUserId;
    private long _targetSubProposalId;
    private long _targetPlanSectionDefinitionId;
    private double _percentage;
    private long _creator;
    private Date _createDate;

    public PointsDistributionConfigurationSoap() {
    }

    public static PointsDistributionConfigurationSoap toSoapModel(
        PointsDistributionConfiguration model) {
        PointsDistributionConfigurationSoap soapModel = new PointsDistributionConfigurationSoap();

        soapModel.setId(model.getId());
        soapModel.setProposalId(model.getProposalId());
        soapModel.setPointTypeId(model.getPointTypeId());
        soapModel.setTargetUserId(model.getTargetUserId());
        soapModel.setTargetSubProposalId(model.getTargetSubProposalId());
        soapModel.setTargetPlanSectionDefinitionId(model.getTargetPlanSectionDefinitionId());
        soapModel.setPercentage(model.getPercentage());
        soapModel.setCreator(model.getCreator());
        soapModel.setCreateDate(model.getCreateDate());

        return soapModel;
    }

    public static PointsDistributionConfigurationSoap[] toSoapModels(
        PointsDistributionConfiguration[] models) {
        PointsDistributionConfigurationSoap[] soapModels = new PointsDistributionConfigurationSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PointsDistributionConfigurationSoap[][] toSoapModels(
        PointsDistributionConfiguration[][] models) {
        PointsDistributionConfigurationSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PointsDistributionConfigurationSoap[models.length][models[0].length];
        } else {
            soapModels = new PointsDistributionConfigurationSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PointsDistributionConfigurationSoap[] toSoapModels(
        List<PointsDistributionConfiguration> models) {
        List<PointsDistributionConfigurationSoap> soapModels = new ArrayList<PointsDistributionConfigurationSoap>(models.size());

        for (PointsDistributionConfiguration model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PointsDistributionConfigurationSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long pk) {
        setId(pk);
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public long getPointTypeId() {
        return _pointTypeId;
    }

    public void setPointTypeId(long pointTypeId) {
        _pointTypeId = pointTypeId;
    }

    public long getTargetUserId() {
        return _targetUserId;
    }

    public void setTargetUserId(long targetUserId) {
        _targetUserId = targetUserId;
    }

    public long getTargetSubProposalId() {
        return _targetSubProposalId;
    }

    public void setTargetSubProposalId(long targetSubProposalId) {
        _targetSubProposalId = targetSubProposalId;
    }

    public long getTargetPlanSectionDefinitionId() {
        return _targetPlanSectionDefinitionId;
    }

    public void setTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId) {
        _targetPlanSectionDefinitionId = targetPlanSectionDefinitionId;
    }

    public double getPercentage() {
        return _percentage;
    }

    public void setPercentage(double percentage) {
        _percentage = percentage;
    }

    public long getCreator() {
        return _creator;
    }

    public void setCreator(long creator) {
        _creator = creator;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }
}

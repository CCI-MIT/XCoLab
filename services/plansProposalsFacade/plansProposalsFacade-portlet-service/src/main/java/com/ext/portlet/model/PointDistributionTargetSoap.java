package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PointDistributionTargetServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PointDistributionTargetServiceSoap
 * @generated
 */
public class PointDistributionTargetSoap implements Serializable {
    private long _id;
    private long _contestId;
    private long _proposalId;
    private double _numberOfPoints;
    private long _pointTypeOverride;

    public PointDistributionTargetSoap() {
    }

    public static PointDistributionTargetSoap toSoapModel(
        PointDistributionTarget model) {
        PointDistributionTargetSoap soapModel = new PointDistributionTargetSoap();

        soapModel.setId(model.getId());
        soapModel.setContestId(model.getContestId());
        soapModel.setProposalId(model.getProposalId());
        soapModel.setNumberOfPoints(model.getNumberOfPoints());
        soapModel.setPointTypeOverride(model.getPointTypeOverride());

        return soapModel;
    }

    public static PointDistributionTargetSoap[] toSoapModels(
        PointDistributionTarget[] models) {
        PointDistributionTargetSoap[] soapModels = new PointDistributionTargetSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PointDistributionTargetSoap[][] toSoapModels(
        PointDistributionTarget[][] models) {
        PointDistributionTargetSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PointDistributionTargetSoap[models.length][models[0].length];
        } else {
            soapModels = new PointDistributionTargetSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PointDistributionTargetSoap[] toSoapModels(
        List<PointDistributionTarget> models) {
        List<PointDistributionTargetSoap> soapModels = new ArrayList<PointDistributionTargetSoap>(models.size());

        for (PointDistributionTarget model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PointDistributionTargetSoap[soapModels.size()]);
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

    public long getContestId() {
        return _contestId;
    }

    public void setContestId(long contestId) {
        _contestId = contestId;
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public double getNumberOfPoints() {
        return _numberOfPoints;
    }

    public void setNumberOfPoints(double numberOfPoints) {
        _numberOfPoints = numberOfPoints;
    }

    public long getPointTypeOverride() {
        return _pointTypeOverride;
    }

    public void setPointTypeOverride(long pointTypeOverride) {
        _pointTypeOverride = pointTypeOverride;
    }
}

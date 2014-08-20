package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PointsServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PointsServiceSoap
 * @generated
 */
public class PointsSoap implements Serializable {
    private long _id;
    private long _proposalId;
    private long _userId;
    private double _materializedPoints;
    private double _hypotheticalPoints;
    private long _pointsSourceId;
    private long _originatingContestPK;

    public PointsSoap() {
    }

    public static PointsSoap toSoapModel(Points model) {
        PointsSoap soapModel = new PointsSoap();

        soapModel.setId(model.getId());
        soapModel.setProposalId(model.getProposalId());
        soapModel.setUserId(model.getUserId());
        soapModel.setMaterializedPoints(model.getMaterializedPoints());
        soapModel.setHypotheticalPoints(model.getHypotheticalPoints());
        soapModel.setPointsSourceId(model.getPointsSourceId());
        soapModel.setOriginatingContestPK(model.getOriginatingContestPK());

        return soapModel;
    }

    public static PointsSoap[] toSoapModels(Points[] models) {
        PointsSoap[] soapModels = new PointsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PointsSoap[][] toSoapModels(Points[][] models) {
        PointsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PointsSoap[models.length][models[0].length];
        } else {
            soapModels = new PointsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PointsSoap[] toSoapModels(List<Points> models) {
        List<PointsSoap> soapModels = new ArrayList<PointsSoap>(models.size());

        for (Points model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PointsSoap[soapModels.size()]);
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

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public double getMaterializedPoints() {
        return _materializedPoints;
    }

    public void setMaterializedPoints(double materializedPoints) {
        _materializedPoints = materializedPoints;
    }

    public double getHypotheticalPoints() {
        return _hypotheticalPoints;
    }

    public void setHypotheticalPoints(double hypotheticalPoints) {
        _hypotheticalPoints = hypotheticalPoints;
    }

    public long getPointsSourceId() {
        return _pointsSourceId;
    }

    public void setPointsSourceId(long pointsSourceId) {
        _pointsSourceId = pointsSourceId;
    }

    public long getOriginatingContestPK() {
        return _originatingContestPK;
    }

    public void setOriginatingContestPK(long originatingContestPK) {
        _originatingContestPK = originatingContestPK;
    }
}

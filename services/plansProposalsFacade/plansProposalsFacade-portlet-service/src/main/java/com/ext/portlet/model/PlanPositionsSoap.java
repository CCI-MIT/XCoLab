package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanPositionsServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanPositionsServiceSoap
 * @generated
 */
public class PlanPositionsSoap implements Serializable {
    private long _id;
    private long _planId;
    private long _planVersion;
    private long _version;
    private Date _created;
    private long _updateAuthorId;

    public PlanPositionsSoap() {
    }

    public static PlanPositionsSoap toSoapModel(PlanPositions model) {
        PlanPositionsSoap soapModel = new PlanPositionsSoap();

        soapModel.setId(model.getId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setPlanVersion(model.getPlanVersion());
        soapModel.setVersion(model.getVersion());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdateAuthorId(model.getUpdateAuthorId());

        return soapModel;
    }

    public static PlanPositionsSoap[] toSoapModels(PlanPositions[] models) {
        PlanPositionsSoap[] soapModels = new PlanPositionsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionsSoap[][] toSoapModels(PlanPositions[][] models) {
        PlanPositionsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanPositionsSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanPositionsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionsSoap[] toSoapModels(List<PlanPositions> models) {
        List<PlanPositionsSoap> soapModels = new ArrayList<PlanPositionsSoap>(models.size());

        for (PlanPositions model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanPositionsSoap[soapModels.size()]);
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

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }
}

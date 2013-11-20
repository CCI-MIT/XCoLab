package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanModelRunServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanModelRunServiceSoap
 * @generated
 */
public class PlanModelRunSoap implements Serializable {
    private long _id;
    private long _planId;
    private long _scenarioId;
    private long _planVersion;
    private long _version;
    private Date _created;
    private long _updateAuthorId;

    public PlanModelRunSoap() {
    }

    public static PlanModelRunSoap toSoapModel(PlanModelRun model) {
        PlanModelRunSoap soapModel = new PlanModelRunSoap();

        soapModel.setId(model.getId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setScenarioId(model.getScenarioId());
        soapModel.setPlanVersion(model.getPlanVersion());
        soapModel.setVersion(model.getVersion());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdateAuthorId(model.getUpdateAuthorId());

        return soapModel;
    }

    public static PlanModelRunSoap[] toSoapModels(PlanModelRun[] models) {
        PlanModelRunSoap[] soapModels = new PlanModelRunSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanModelRunSoap[][] toSoapModels(PlanModelRun[][] models) {
        PlanModelRunSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanModelRunSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanModelRunSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanModelRunSoap[] toSoapModels(List<PlanModelRun> models) {
        List<PlanModelRunSoap> soapModels = new ArrayList<PlanModelRunSoap>(models.size());

        for (PlanModelRun model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanModelRunSoap[soapModels.size()]);
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

    public long getScenarioId() {
        return _scenarioId;
    }

    public void setScenarioId(long scenarioId) {
        _scenarioId = scenarioId;
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

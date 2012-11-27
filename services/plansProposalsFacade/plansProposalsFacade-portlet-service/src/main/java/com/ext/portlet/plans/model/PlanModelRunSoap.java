package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanModelRunServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanModelRunServiceSoap
 * @generated
 */
public class PlanModelRunSoap implements Serializable {
    private Long _id;
    private Long _planId;
    private Long _scenarioId;
    private Long _planVersion;
    private Long _version;
    private Date _created;
    private Long _updateAuthorId;

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

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public Long getScenarioId() {
        return _scenarioId;
    }

    public void setScenarioId(Long scenarioId) {
        _scenarioId = scenarioId;
    }

    public Long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(Long planVersion) {
        _planVersion = planVersion;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }
}

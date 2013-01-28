package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanTeamHistoryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanTeamHistoryServiceSoap
 * @generated
 */
public class PlanTeamHistorySoap implements Serializable {
    private long _id;
    private long _planId;
    private long _userId;
    private String _action;
    private String _payload;
    private Date _created;
    private long _updateAuthorId;

    public PlanTeamHistorySoap() {
    }

    public static PlanTeamHistorySoap toSoapModel(PlanTeamHistory model) {
        PlanTeamHistorySoap soapModel = new PlanTeamHistorySoap();

        soapModel.setId(model.getId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setUserId(model.getUserId());
        soapModel.setAction(model.getAction());
        soapModel.setPayload(model.getPayload());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdateAuthorId(model.getUpdateAuthorId());

        return soapModel;
    }

    public static PlanTeamHistorySoap[] toSoapModels(PlanTeamHistory[] models) {
        PlanTeamHistorySoap[] soapModels = new PlanTeamHistorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTeamHistorySoap[][] toSoapModels(
        PlanTeamHistory[][] models) {
        PlanTeamHistorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTeamHistorySoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTeamHistorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTeamHistorySoap[] toSoapModels(
        List<PlanTeamHistory> models) {
        List<PlanTeamHistorySoap> soapModels = new ArrayList<PlanTeamHistorySoap>(models.size());

        for (PlanTeamHistory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTeamHistorySoap[soapModels.size()]);
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

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getAction() {
        return _action;
    }

    public void setAction(String action) {
        _action = action;
    }

    public String getPayload() {
        return _payload;
    }

    public void setPayload(String payload) {
        _payload = payload;
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

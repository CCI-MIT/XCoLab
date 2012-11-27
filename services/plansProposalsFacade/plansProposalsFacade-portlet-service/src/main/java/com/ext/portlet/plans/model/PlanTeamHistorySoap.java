package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanTeamHistoryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanTeamHistoryServiceSoap
 * @generated
 */
public class PlanTeamHistorySoap implements Serializable {
    private Long _id;
    private Long _planId;
    private Long _userId;
    private String _action;
    private String _payload;
    private Date _created;
    private Long _updateAuthorId;

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

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
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

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }
}

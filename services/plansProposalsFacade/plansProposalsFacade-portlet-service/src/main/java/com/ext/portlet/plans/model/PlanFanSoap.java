package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanFanServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanFanServiceSoap
 * @generated
 */
public class PlanFanSoap implements Serializable {
    private long _id;
    private long _userId;
    private long _planId;
    private Date _created;
    private Date _deleted;

    public PlanFanSoap() {
    }

    public static PlanFanSoap toSoapModel(PlanFan model) {
        PlanFanSoap soapModel = new PlanFanSoap();

        soapModel.setId(model.getId());
        soapModel.setUserId(model.getUserId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setCreated(model.getCreated());
        soapModel.setDeleted(model.getDeleted());

        return soapModel;
    }

    public static PlanFanSoap[] toSoapModels(PlanFan[] models) {
        PlanFanSoap[] soapModels = new PlanFanSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanFanSoap[][] toSoapModels(PlanFan[][] models) {
        PlanFanSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanFanSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanFanSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanFanSoap[] toSoapModels(List<PlanFan> models) {
        List<PlanFanSoap> soapModels = new ArrayList<PlanFanSoap>(models.size());

        for (PlanFan model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanFanSoap[soapModels.size()]);
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

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }
}
